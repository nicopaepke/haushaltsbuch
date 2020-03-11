package npaepke.haushaltsbuch.models.timeseries;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "HB_Time_Series")
public class TimeSeries {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID uuid;

    @Column(name = "reference_uuid")
    private UUID referenceUUID;

    @Lob
    @Column(name = "data_points")
    @Convert(converter = TimeSeriesConverter.class)
    private List<TimeSeriesDataPoint> dataPoints;
}
