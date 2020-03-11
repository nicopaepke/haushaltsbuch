package npaepke.haushaltsbuch.repos;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import npaepke.haushaltsbuch.models.timeseries.TimeSeries;

public interface TimeSeriesRepository extends JpaRepository<TimeSeries, UUID> {

    Optional<TimeSeries> findTimeSeriesByReferenceUUID(UUID referenceUUID);
}
