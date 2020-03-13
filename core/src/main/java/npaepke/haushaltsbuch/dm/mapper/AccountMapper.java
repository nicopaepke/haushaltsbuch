package npaepke.haushaltsbuch.dm.mapper;

import java.util.List;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import npaepke.haushaltsbuch.api.dtos.AccountDTO;
import npaepke.haushaltsbuch.dm.models.Account;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AccountMapper {

    AccountDTO convertAccount(Account account);

    List<AccountDTO> convertAccounts(List<Account> accounts);

    Account convertAccount(AccountDTO account);
}
