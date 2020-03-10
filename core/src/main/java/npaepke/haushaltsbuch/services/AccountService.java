package npaepke.haushaltsbuch.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import npaepke.haushaltsbuch.models.Account;
import npaepke.haushaltsbuch.repos.AccountRepository;
import npaepke.haushaltsbuch.services.dtos.AccountDTO;
import npaepke.haushaltsbuch.services.mapper.AccountMapper;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    public List<AccountDTO> findAll() {
        return this.accountMapper.convertAccounts(this.accountRepository.findAll());
    }

    public Optional<AccountDTO> findById(UUID uuid) {
        return Optional.ofNullable(this.accountMapper.convertAccount(this.accountRepository.findById(uuid).orElse(null)));
    }

    public AccountDTO save(AccountDTO account) {
        Account saved = this.accountRepository.save(this.accountMapper.convertAccount(account));
        return this.accountMapper.convertAccount(saved);
    }

    public void deleteById(UUID uuid) {
        this.accountRepository.deleteById(uuid);
    }
}
