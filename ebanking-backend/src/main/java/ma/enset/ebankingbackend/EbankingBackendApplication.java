package ma.enset.ebankingbackend;

import ma.enset.ebankingbackend.entities.AccountOperation;
import ma.enset.ebankingbackend.entities.CurrentAccount;
import ma.enset.ebankingbackend.entities.Customer;
import ma.enset.ebankingbackend.entities.SavingAccount;
import ma.enset.ebankingbackend.enums.AccountStatus;
import ma.enset.ebankingbackend.enums.OperationType;
import ma.enset.ebankingbackend.repositories.AccountOperationRepository;
import ma.enset.ebankingbackend.repositories.BankAccountRepository;
import ma.enset.ebankingbackend.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankingBackendApplication {


	public static void main(String[] args) {
		SpringApplication.run(EbankingBackendApplication.class, args);
	}
	//Tests
         CommandLineRunner start(AccountOperationRepository accountOperationRepository,
								 BankAccountRepository bankAccountRepository,
								 CustomerRepository customerRepository) {
            return args -> {
                Stream.of("ayoub", "adil", "ahmad", "yasser", "imane", "yousra", "yousef", "amine", "laila" )
                        .forEach(name -> {
                            Customer customer = new Customer();
                            customer.setName(name);
                            customer.setEmail(name + "@gmail.com");
                            customerRepository.save(customer);
                        });
                customerRepository.findAll().forEach(customer -> {
                    CurrentAccount currentAccount = new CurrentAccount();
                    currentAccount.setID(UUID.randomUUID().toString());
                    currentAccount.setRIB("0012" + customer.getId());
                    currentAccount.setBalance(Math.random() * 9000);
                    currentAccount.setCreatedAt(new java.util.Date());
                    currentAccount.setStatus(AccountStatus.CREATED);
                    currentAccount.setCustomer(customer);
                    currentAccount.setOverDraft(1000);
                    bankAccountRepository.save(currentAccount);

                    SavingAccount savingAccount = new SavingAccount();
                    savingAccount.setID(UUID.randomUUID().toString());
                    savingAccount.setRIB("0022" + customer.getId());
                    savingAccount.setBalance(Math.random() * 9000);
                    savingAccount.setCreatedAt(new java.util.Date());
                    savingAccount.setStatus(AccountStatus.CREATED);
                    savingAccount.setCustomer(customer);
                    savingAccount.setInterestRate(3.5);
                    bankAccountRepository.save(savingAccount);
                });


                //AccountOperation : ID, amount, date, type, bankAccount
                bankAccountRepository.findAll().forEach(bankAccount -> {
                    for (int i=0; i<10; i++) {
                        AccountOperation accountOperation = new AccountOperation();
                        accountOperation.setAmount(Math.random() * 9000);
                        accountOperation.setOperationDate(new java.util.Date());
                        accountOperation.setType(Math.random() > 0.5 ? OperationType.DEBIT : OperationType.CREDIT);
                        accountOperation.setBankAccount(bankAccount);
                        accountOperationRepository.save(accountOperation);
                    }
                });
            };
        }

}
