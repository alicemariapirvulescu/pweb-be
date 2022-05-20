package pweb.refugees.domain;

import lombok.Getter;
import lombok.Setter;
import pweb.refugees.enums.Provider;
import pweb.refugees.enums.Role;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    private String providerId;

    private String imageUrl;

    public User(String username, String email, Provider provider) {
        this.username = username;
        this.email = email;
        this.provider = provider;
    }

    public User() {

    }
}
