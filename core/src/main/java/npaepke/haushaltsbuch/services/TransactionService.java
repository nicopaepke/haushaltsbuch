package npaepke.haushaltsbuch.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import npaepke.haushaltsbuch.models.Account;
import npaepke.haushaltsbuch.models.Purpose;
import npaepke.haushaltsbuch.models.transaction.Transaction;
import npaepke.haushaltsbuch.repos.AccountRepository;
import npaepke.haushaltsbuch.repos.PurposeRepository;
import npaepke.haushaltsbuch.repos.TransactionRepository;
import npaepke.haushaltsbuch.services.dtos.TransactionDTO;
import npaepke.haushaltsbuch.services.exceptions.ElementNotFoundException;
import npaepke.haushaltsbuch.services.mapper.TransactionMapper;
import npaepke.haushaltsbuch.services.validation.TransactionValidator;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    private final PurposeRepository purposeRepository;

    private final AccountRepository accountRepository;

    private final TransactionValidator transactionValidator;

    public List<TransactionDTO> getAllTransactions() {
        return this.transactionMapper.convertTransactions(this.transactionRepository.findAll());
    }

    public TransactionDTO updateTransaction(TransactionDTO transaction) {
        Transaction existingTransaction = this.transactionRepository.findById(transaction.getUuid()).orElseThrow();
        return saveTransaction(transaction, existingTransaction);
    }

    public TransactionDTO createTransaction(TransactionDTO transaction) {
        return saveTransaction(transaction, new Transaction());
    }

    private TransactionDTO saveTransaction(TransactionDTO transactionDTO, Transaction existingTransaction) {
        Purpose purpose = this.purposeRepository.findById(transactionDTO.getPurposeUUID()).orElseThrow(ElementNotFoundException::new);
        Account sourceAccount =
            this.accountRepository.findById(transactionDTO.getSourceAccountUUID()).orElseThrow(ElementNotFoundException::new);
        Account targetAccount =
            this.accountRepository.findById(transactionDTO.getTargetAccountUUID()).orElseThrow(ElementNotFoundException::new);
        Transaction transaction =
            this.transactionMapper.convertTransaction(existingTransaction, transactionDTO, purpose, sourceAccount, targetAccount);
        this.validate(transaction);
        transaction = this.transactionRepository.save(transaction);
        return this.transactionMapper.convertTransaction(transaction);
    }

    private void validate(Transaction transaction) {
        this.transactionValidator.validate(transaction);
    }

}
