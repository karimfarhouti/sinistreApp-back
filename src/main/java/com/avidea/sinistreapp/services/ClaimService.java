package com.avidea.sinistreapp.services;

import com.avidea.sinistreapp.domain.Claim;
import com.avidea.sinistreapp.repositories.ClaimRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ClaimService {

    private static final Logger log = LoggerFactory.getLogger(ClaimService.class);
    private final ClaimRepository claimRepository;

    private final FileUploadService fileUploadService;

    public ClaimService(ClaimRepository claimRepository, FileUploadService fileUploadService) {
        this.claimRepository = claimRepository;
        this.fileUploadService = fileUploadService;
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Claim claim) {
        final String imageUrl = claim.getImageUrl();

        claimRepository.delete(claim);

        if (imageUrl != null && !imageUrl.isEmpty()) {
            try {
                fileUploadService.deleteFile(imageUrl);
            } catch (Exception e) {
                log.error("Exception message: {}", e.getMessage());
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteClaimFile(Claim claim) throws Exception {
        final String imageUrl = claim.getImageUrl();

        claim.setImageUrl(null);
        claimRepository.save(claim);

        if (imageUrl != null && !imageUrl.isEmpty())
            fileUploadService.deleteFile(imageUrl);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateClaimFile(Claim claim, MultipartFile file) throws Exception {
        log.debug("update claim file method called with id:{}", claim.getId());
        final String oldImageUrl = claim.getImageUrl();
        final String imageUrl = file.getOriginalFilename();
        log.debug("oldImageUrl:{}", oldImageUrl);
        if (oldImageUrl != null && !oldImageUrl.isEmpty())
            fileUploadService.deleteFile(oldImageUrl);

        claim.setImageUrl(imageUrl);
        claimRepository.save(claim);

        fileUploadService.uploadFile(file);
    }
}
