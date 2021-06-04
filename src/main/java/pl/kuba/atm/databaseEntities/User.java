package pl.kuba.atm.databaseEntities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Setter
@Getter
@NoArgsConstructor
public class User {

    private String firstName;
    private String lastName;
    @Id
    private String uuid;
    private byte pinHash[]; // MD5 hash of pin number

    @OneToMany
    private List<Account> accounts;

    public User(String firstName, String lastName, String pin, Bank theBank) {
        this.firstName = firstName;
        this.lastName = lastName;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("errpr, cought NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        this.uuid = theBank.getNewUserUUID();

        this.accounts = new ArrayList<Account>();

        //log message

        System.out.printf("New user %s, %s with ID %s created. \n", lastName, firstName, this.uuid);

    }
    public String getUUID() {
        return uuid;
    }

}
