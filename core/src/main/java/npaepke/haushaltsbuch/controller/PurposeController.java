package npaepke.haushaltsbuch.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import npaepke.haushaltsbuch.services.PurposeService;
import npaepke.haushaltsbuch.services.dtos.PurposeDTO;
import npaepke.haushaltsbuch.services.mapper.PurposeMapper;

@RestController
@RequestMapping("/purposes")
@RequiredArgsConstructor
public class PurposeController {

    private final PurposeService purposeService;

    private final PurposeMapper purposeMapper;

    @GetMapping
    public ResponseEntity<List<PurposeDTO>> getPurposes(@RequestHeader HttpHeaders httpHeaders) {
        List<PurposeDTO> purposes = purposeService.getAllPurposes();
        return ResponseEntity.ok(purposes);
    }

    @GetMapping(path = "/{uuid}")
    public ResponseEntity<PurposeDTO> getPurpose(@RequestHeader HttpHeaders httpHeaders, @PathVariable UUID uuid) {
        Optional<PurposeDTO> purpose = purposeService.findById(uuid);
        return purpose.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<PurposeDTO> createPurpose(@RequestHeader HttpHeaders httpHeaders, @RequestBody PurposeDTO purpose) {
        PurposeDTO createPurpose = purposeService.save(purpose);
        return ResponseEntity.ok(createPurpose);
    }

    @PutMapping(path = "/{uuid}")
    public ResponseEntity<PurposeDTO> updatePurpose(@RequestHeader HttpHeaders httpHeaders, @PathVariable UUID uuid,
        @RequestBody PurposeDTO purpose) {
        if (!uuid.equals(purpose.getUuid())) {
            return ResponseEntity.badRequest().build();
        }
        PurposeDTO updatedPurpose = purposeService.save(purpose);
        return ResponseEntity.ok(updatedPurpose);
    }

    @DeleteMapping(path = "/{uuid}")
    public void deletePurpose(@RequestHeader HttpHeaders httpHeaders, @PathVariable UUID uuid) {
        purposeService.deleteById(uuid);
    }
}
