package npaepke.haushaltsbuch.dm.services.validation;

import org.springframework.stereotype.Service;

import npaepke.haushaltsbuch.api.exceptions.ValidationException;
import npaepke.haushaltsbuch.dm.models.transaction.Transaction;

@Service
public class TransactionValidator {

    public void validate(Transaction transaction) throws ValidationException {
        if (transaction.getSourceAccount().getUuid().equals(transaction.getTargetAccount().getUuid())) {
            throw new ValidationException("Source account and target account have to be different.");
        }
    }
}
