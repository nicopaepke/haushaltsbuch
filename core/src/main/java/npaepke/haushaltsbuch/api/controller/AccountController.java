package npaepke.haushaltsbuch.api.controller;

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
import npaepke.haushaltsbuch.api.dtos.AccountDTO;
import npaepke.haushaltsbuch.dm.services.AccountService;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAccounts(@RequestHeader HttpHeaders httpHeaders) {
        List<AccountDTO> accounts = accountService.findAll();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping(path = "/{uuid}")
    public ResponseEntity<AccountDTO> getAccount(@RequestHeader HttpHeaders httpHeaders, @PathVariable UUID uuid) {
        Optional<AccountDTO> account = accountService.findById(uuid);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<AccountDTO> createAccount(@RequestHeader HttpHeaders httpHeaders, @RequestBody AccountDTO account) {
        AccountDTO createAccount = accountService.save(account);
        return ResponseEntity.ok(createAccount);
    }

    @PutMapping(path = "/{uuid}")
    public ResponseEntity<AccountDTO> updateAccount(@RequestHeader HttpHeaders httpHeaders, @PathVariable UUID uuid,
        @RequestBody AccountDTO account) {
        if (!uuid.equals(account.getUuid())) {
            return ResponseEntity.badRequest().build();
        }
        AccountDTO createAccount = accountService.save(account);
        return ResponseEntity.ok(createAccount);
    }

    @DeleteMapping(path = "/{uuid}")
    public void deleteAccount(@RequestHeader HttpHeaders httpHeaders, @PathVariable UUID uuid) {
        accountService.deleteById(uuid);
    }
}
