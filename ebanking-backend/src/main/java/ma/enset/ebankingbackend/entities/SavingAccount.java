package ma.enset.ebankingbackend.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor @AllArgsConstructor
public class SavingAccount extends BankAccount{
    private double interestRate ;
}
