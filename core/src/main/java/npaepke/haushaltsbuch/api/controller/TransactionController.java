package npaepke.haushaltsbuch.api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import npaepke.haushaltsbuch.api.dtos.TransactionDTO;
import npaepke.haushaltsbuch.dm.services.TransactionService;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions(@RequestHeader HttpHeaders httpHeaders) {
        return ResponseEntity.ok(this.transactionService.getAllTransactions());
    }

    @PostMapping()
    public ResponseEntity<TransactionDTO> createTransaction(@RequestHeader HttpHeaders httpHeaders,
        @RequestBody TransactionDTO transaction) {
        TransactionDTO createdTransaction = this.transactionService.createTransaction(transaction);
        return ResponseEntity.ok(createdTransaction);
    }

    @PutMapping(path = "/{uuid}")
    public ResponseEntity<TransactionDTO> updateTransaction(@RequestHeader HttpHeaders httpHeaders, @PathVariable UUID uuid,
        @RequestBody TransactionDTO transaction) {
        if (!uuid.equals(transaction.getUuid())) {
            return ResponseEntity.badRequest().build();
        }
        TransactionDTO createdTransaction = this.transactionService.updateTransaction(transaction);
        return ResponseEntity.ok(createdTransaction);
    }
}
