package npaepke.haushaltsbuch.services.validation;

import org.springframework.stereotype.Service;

import npaepke.haushaltsbuch.models.transaction.Transaction;
import npaepke.haushaltsbuch.services.exceptions.ValidationException;

@Service
public class TransactionValidator {

    public void validate(Transaction transaction) throws ValidationException {
        if (transaction.getSourceAccount().getUuid().equals(transaction.getTargetAccount().getUuid())) {
            throw new ValidationException("Source account and target account have to be different.");
        }
    }
}
