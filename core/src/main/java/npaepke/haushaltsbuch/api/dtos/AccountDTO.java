package npaepke.haushaltsbuch.api.dtos;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {

    private UUID uuid;

    private String name;
}
