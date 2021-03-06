package npaepke.haushaltsbuch.dm.repos;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import npaepke.haushaltsbuch.dm.models.timeseries.TimeSeries;

public interface TimeSeriesRepository extends JpaRepository<TimeSeries, UUID> {

    Optional<TimeSeries> findTimeSeriesByReferenceUUID(UUID referenceUUID);
}
