package npaepke.haushaltsbuch.dm.mapper;

import java.util.List;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import npaepke.haushaltsbuch.api.dtos.TransactionDTO;
import npaepke.haushaltsbuch.dm.models.Account;
import npaepke.haushaltsbuch.dm.models.Purpose;
import npaepke.haushaltsbuch.dm.models.transaction.Transaction;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TransactionMapper {

    @Mappings({@Mapping(target = "purposeUUID", source = "purpose.uuid"), @Mapping(target = "purposeName", source = "purpose.name"),
        @Mapping(target = "sourceAccountUUID", source = "sourceAccount.uuid"),
        @Mapping(target = "sourceAccountName", source = "sourceAccount.name"),
        @Mapping(target = "targetAccountUUID", source = "targetAccount.uuid"),
        @Mapping(target = "targetAccountName", source = "targetAccount.name")
    })
    TransactionDTO convertTransaction(Transaction transaction);

    List<TransactionDTO> convertTransactions(List<Transaction> transactions);

    @Mappings({@Mapping(target = "uuid", source = "transaction.uuid"), @Mapping(target = "transactionValues", ignore = true)
    })
    Transaction convertTransaction(@MappingTarget Transaction target, TransactionDTO transaction, Purpose purpose, Account sourceAccount,
        Account targetAccount);
}
