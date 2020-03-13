package npaepke.haushaltsbuch.dm.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import npaepke.haushaltsbuch.api.dtos.PurposeDTO;
import npaepke.haushaltsbuch.dm.mapper.PurposeMapper;
import npaepke.haushaltsbuch.dm.models.Purpose;
import npaepke.haushaltsbuch.dm.repos.PurposeRepository;

@Service
@RequiredArgsConstructor
public class PurposeService {

    private final PurposeRepository purposeRepository;

    private final PurposeMapper purposeMapper;

    public List<PurposeDTO> getAllPurposes() {
        List<Purpose> allRootPurposes = this.purposeRepository.findAllByParentIsNull();
        return this.purposeMapper.convertPurposes(allRootPurposes);
    }

    public Optional<PurposeDTO> findById(UUID uuid) {
        return Optional.ofNullable(this.purposeMapper.convertPurpose(this.purposeRepository.findById(uuid).orElse(null)));
    }

    public PurposeDTO save(PurposeDTO purpose) {
        Purpose saved = this.purposeRepository.save(this.purposeMapper.convertPurpose(purpose));
        return this.purposeMapper.convertPurpose(saved);
    }

    public void deleteById(UUID uuid) {
        this.purposeRepository.deleteById(uuid);
    }
}
