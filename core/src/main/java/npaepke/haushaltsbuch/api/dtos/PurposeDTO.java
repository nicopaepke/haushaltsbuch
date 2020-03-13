package npaepke.haushaltsbuch.api.dtos;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurposeDTO {

    private UUID uuid;

    private String name;

    private List<PurposeDTO> childPurposes;

}
