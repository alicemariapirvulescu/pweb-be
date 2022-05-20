package pweb.refugees.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pweb.refugees.enums.Provider;
import pweb.refugees.enums.Role;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private String username;

    private String email;

    private Role role;

    private String imageUrl;

    private String token;

    public UserDto(String username, String email, Role role, String imageUrl) {
        this.email = email;
        this.username = username;
        this.role = role;
        this.imageUrl = imageUrl;
    }
}
