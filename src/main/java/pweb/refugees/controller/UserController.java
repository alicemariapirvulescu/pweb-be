package pweb.refugees.controller;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pweb.refugees.config.AppProperties;
import pweb.refugees.domain.User;
import pweb.refugees.domain.dto.UserDto;
import pweb.refugees.exception.ResourceNotFoundException;
import pweb.refugees.mappers.GeneralMapperComponent;
import pweb.refugees.repository.UserRepository;
import pweb.refugees.security.CurrentUser;
import pweb.refugees.security.UserPrincipal;

import java.util.Date;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Value("${app.auth.tokenSecret}")
    private String jwtSecret;

    @Value("${app.auth.tokenExpirationMsec}")
    private int jwtExpirationMs;


    @GetMapping("/user/me")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_HOST') or hasRole('ROLE_GUEST')")
    public UserDto getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        userDto.setToken(generateJwtToken(user));
        return userDto;
    }

    public String generateJwtToken(User user) {

        return Jwts.builder()
                .setSubject((user.getId().toString()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .claim("roles", user.getRole())
                .compact();
    }

}
