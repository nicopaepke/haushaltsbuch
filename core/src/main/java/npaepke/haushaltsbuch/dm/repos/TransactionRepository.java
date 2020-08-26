package npaepke.haushaltsbuch.dm.repos;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import npaepke.haushaltsbuch.dm.models.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    @EntityGraph(value = "Transaction.Values", type = EntityGraph.EntityGraphType.LOAD)
    List<Transaction> findAll();

}
