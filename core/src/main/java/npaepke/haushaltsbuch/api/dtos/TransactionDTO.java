package npaepke.haushaltsbuch.api.dtos;

import java.util.UUID;

import org.joda.time.DateTime;

import lombok.Getter;
import lombok.Setter;
import npaepke.haushaltsbuch.dm.models.transaction.ETransactionType;

@Getter
@Setter
public class TransactionDTO {

    private UUID uuid;

    private UUID purposeUUID;

    private String purposeName;

    private UUID sourceAccountUUID;

    private String sourceAccountName;

    private UUID targetAccountUUID;

    private String targetAccountName;

    private ETransactionType transactionType;

    private double value;

    private DateTime startDate;

    private DateTime endDate;

    private String description;

    private int receiptNumber;

    private DateTime creationTimeStamp;
}
