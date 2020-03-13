package npaepke.haushaltsbuch.dm.repos;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import npaepke.haushaltsbuch.dm.models.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
