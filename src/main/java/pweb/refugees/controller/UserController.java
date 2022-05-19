package pweb.refugees.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pweb.refugees.domain.User;
import pweb.refugees.domain.dto.UserDto;
import pweb.refugees.exception.ResourceNotFoundException;
import pweb.refugees.mappers.GeneralMapperComponent;
import pweb.refugees.repository.UserRepository;
import pweb.refugees.security.CurrentUser;
import pweb.refugees.security.UserPrincipal;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GeneralMapperComponent generalMapperComponent;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_HOST') or hasRole('ROLE_GUEST')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
