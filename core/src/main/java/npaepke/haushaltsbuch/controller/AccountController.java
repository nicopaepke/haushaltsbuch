package npaepke.haushaltsbuch.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import npaepke.haushaltsbuch.models.Account;
import npaepke.haushaltsbuch.repos.AccountRepository;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountRepository accountRepository;

    @GetMapping
    public ResponseEntity<List<Account>> getAccounts(@RequestHeader HttpHeaders httpHeaders) {
        List<Account> accounts = accountRepository.findAll();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping(path = "/{uuid}")
    public ResponseEntity<Account> getAccount(@RequestHeader HttpHeaders httpHeaders, @PathVariable UUID uuid) {
        Optional<Account> account = accountRepository.findById(uuid);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Account> createAccount(@RequestHeader HttpHeaders httpHeaders, @RequestBody Account account) {
        Account createAccount = accountRepository.save(account);
        return ResponseEntity.ok(createAccount);
    }

    @PutMapping(path = "/{uuid}")
    public ResponseEntity<Account> updateAccount(@RequestHeader HttpHeaders httpHeaders, @PathVariable UUID uuid,
        @RequestBody Account account) {
        if (account.getUuid() != uuid) {
            return ResponseEntity.badRequest().build();
        }
        Account createAccount = accountRepository.save(account);
        return ResponseEntity.ok(createAccount);
    }

    @DeleteMapping(path = "/{uuid}")
    public void updateAccount(@RequestHeader HttpHeaders httpHeaders, @PathVariable UUID uuid) {
        accountRepository.deleteById(uuid);
    }
}
