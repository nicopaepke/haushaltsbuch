package npaepke.haushaltsbuch.models.timeseries;

import org.joda.time.DateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeSeriesDataPoint {

    private DateTime date;

    private double value;

}
