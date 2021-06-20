package pl.kuba.atm.databaseEntities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Account {

    private String name;

    @Id
    private String uuid;

    @OneToMany
    private List<Transaction> transactions;

    public Account(String name, Bank theBank) {
        this.name = name;
        this.uuid = theBank.getNewAccountUUID();

        //init transactions
        this.transactions = new ArrayList<>();
    }

}
