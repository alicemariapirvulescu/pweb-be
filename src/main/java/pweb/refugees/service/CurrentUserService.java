package pweb.refugees.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pweb.refugees.domain.User;
import pweb.refugees.repository.UserRepository;

@Service
public class CurrentUserService {

    @Autowired
    private UserRepository userRepository;

    public User getCurrentUserDetails(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails)principal).getUsername();
        } else {
            email = principal.toString();
        }

        return userRepository.findByEmail(email).get();
    }
}
