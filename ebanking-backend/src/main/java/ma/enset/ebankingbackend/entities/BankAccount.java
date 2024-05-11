package ma.enset.ebankingbackend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.ebankingbackend.enums.AccountStatus;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// @DiscriminatorColumn(name = "account_type", discriminatorType = DiscriminatorType.STRING, length = 2)
@NoArgsConstructor @AllArgsConstructor
public class BankAccount {
    @Id
    private String ID;
    private String RIB;
    private double balance;
    private Date CreatedAt ;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "bankAccount", fetch = FetchType.LAZY)
    private List<AccountOperation> accountOperations;
}
