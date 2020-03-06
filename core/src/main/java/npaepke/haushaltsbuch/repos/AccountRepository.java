package npaepke.haushaltsbuch.repos;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import npaepke.haushaltsbuch.models.Account;

public interface AccountRepository extends JpaRepository<Account, UUID> {
}
