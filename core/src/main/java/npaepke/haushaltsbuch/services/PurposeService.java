package npaepke.haushaltsbuch.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import npaepke.haushaltsbuch.models.Purpose;
import npaepke.haushaltsbuch.repos.PurposeRepository;
import npaepke.haushaltsbuch.services.dtos.PurposeDTO;
import npaepke.haushaltsbuch.services.mapper.PurposeMapper;

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
