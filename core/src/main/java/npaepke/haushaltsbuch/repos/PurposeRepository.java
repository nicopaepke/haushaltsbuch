package npaepke.haushaltsbuch.repos;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import npaepke.haushaltsbuch.models.Purpose;

public interface PurposeRepository extends JpaRepository<Purpose, UUID> {

    List<Purpose> findAllByParentIsNull();
}
