package npaepke.haushaltsbuch.dm.repos;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import npaepke.haushaltsbuch.dm.models.Purpose;

public interface PurposeRepository extends JpaRepository<Purpose, UUID> {

    List<Purpose> findAllByParentIsNull();
}
