package pl.kuba.atm.databaseEntities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany
    private List<User> users;
    @OneToMany
    private List<Account> accounts;

    public String getNewUserUUID() {
        String uuid;
        Random rng = new Random();
        int len = 6;
        boolean nonUnique;

        do {

            uuid = "";
            for (int i = 0; i < len; i++) {
                uuid += ((Integer) rng.nextInt(10)).toString();
            }

            nonUnique = false;
            for (User u : this.users) {
                if (uuid.compareTo(u.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }

            }
        } while (nonUnique);

        return uuid;
    }

    public String getNewAccountUUID() {
        String uuid;
        Random rng = new Random();
        int len = 10;
        boolean nonUnique;

        do {

            uuid = "";
            for (int i = 0; i < len; i++) {
                uuid += ((Integer) rng.nextInt(10)).toString();
            }

            nonUnique = false;
            for (Account a : this.accounts) {
                if (uuid.compareTo(a.getUuid()) == 0) {
                    nonUnique = true;
                    break;
                }

            }
        } while (nonUnique);

        return uuid;
    }

    public Bank(String name) {
        this.name = name;
        this.users = new ArrayList<>();
        this.accounts = new ArrayList<>();}
}
