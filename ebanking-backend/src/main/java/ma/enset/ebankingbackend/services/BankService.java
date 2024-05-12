package ma.enset.ebankingbackend.services;

import ma.enset.ebankingbackend.entities.BankAccount;
import ma.enset.ebankingbackend.entities.CurrentAccount;
import ma.enset.ebankingbackend.entities.SavingAccount;
import ma.enset.ebankingbackend.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BankService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    public void consulter(){
        BankAccount bankAccount = bankAccountRepository.findById("5f13c76a-8321-4b1f-9d84-ec014f4dcb36").orElse(null);
        System.out.println("Balance :" + bankAccount.getBalance());
        System.out.println("Name :"+bankAccount.getCustomer().getName());
        if (bankAccount instanceof CurrentAccount) {
            CurrentAccount currentAccount = (CurrentAccount) bankAccount;
            System.out.println("OverDraft: "+currentAccount.getOverDraft());
        }else if (bankAccount instanceof SavingAccount) {
            SavingAccount savingAccount = (SavingAccount) bankAccount;
            System.out.println("Rate: "+savingAccount.getInterestRate());
        }
        System.out.println("Amount  Type  Date:\n");
        bankAccount.getAccountOperations().forEach(accountOperation -> {
            System.out.println(accountOperation.getAmount()+"  "+accountOperation.getType()+"  "+accountOperation.getOperationDate());
        });
    }
}
