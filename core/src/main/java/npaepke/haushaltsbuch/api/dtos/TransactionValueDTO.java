package npaepke.haushaltsbuch.api.dtos;

import org.joda.time.DateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionValueDTO {

    private DateTime date;

    private double value;

}
