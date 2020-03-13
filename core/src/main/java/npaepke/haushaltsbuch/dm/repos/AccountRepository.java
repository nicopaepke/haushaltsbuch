package npaepke.haushaltsbuch.dm.repos;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import npaepke.haushaltsbuch.dm.models.Account;

public interface AccountRepository extends JpaRepository<Account, UUID> {
}
