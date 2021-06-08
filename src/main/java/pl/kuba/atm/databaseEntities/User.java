package pl.kuba.atm.databaseEntities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@Setter
@Getter
@NoArgsConstructor
public class User implements UserDetails {

    private String username;
    private String firstName;
    private String lastName;
    @Id
    private String uuid;
    private String pin;

    @OneToMany
    private List<Account> accounts;

    public User(String username, String firstName, String lastName, String pin, Bank theBank) {
      this.username = username;
       this.firstName = firstName;
        this.lastName = lastName;
        this.pin = pin;


        this.uuid = theBank.getNewUserUUID();

        this.accounts = new ArrayList<Account>();

        //log message

        System.out.printf("New user %s, %s with ID %s created. \n", lastName, firstName, this.uuid);

    }
    public String getUUID() {
        return uuid;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public String getPassword() {
        return pin;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
