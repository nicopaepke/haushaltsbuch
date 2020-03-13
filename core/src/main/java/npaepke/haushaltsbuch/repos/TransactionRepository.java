package npaepke.haushaltsbuch.repos;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import npaepke.haushaltsbuch.models.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
