package pl.kuba.atm.databaseEntities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
@Setter
@Getter
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    private LocalDate timestamp;

    private String memo;

    public Transaction(double amount) {
        this.amount = amount;
        this.timestamp = LocalDate.now();
        this.memo = "";
    }

    public Transaction(double amount, String memo) {
        this(amount);
        this.memo = memo;
    }

}
