package com.avidea.sinistreapp.web.rest;

import com.avidea.sinistreapp.domain.Claim;
import com.avidea.sinistreapp.repositories.ClaimRepository;
import com.avidea.sinistreapp.services.FileUploadService;
import com.avidea.sinistreapp.web.rest.exceptions.ClaimNotFoundException;
import com.avidea.sinistreapp.web.rest.exceptions.EmptyFileException;
import com.avidea.sinistreapp.web.rest.exceptions.EmptyFileNameException;
import com.avidea.sinistreapp.web.rest.exceptions.WrongFileExtensionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class FileStorageResource {

    private static final Logger log = LoggerFactory.getLogger(FileStorageResource.class);
    private final FileUploadService fileUploadService;

    private final ClaimRepository claimRepository;

    public FileStorageResource(FileUploadService fileUploadService, ClaimRepository claimRepository) {
        this.fileUploadService = fileUploadService;
        this.claimRepository = claimRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadClaimImage(@RequestParam("claimId") long claimId,
                                                   @RequestParam("claimImage") MultipartFile claimImage) {
        String fileName = claimImage.getOriginalFilename();
        if (fileName == null || fileName.isEmpty())
            throw new EmptyFileNameException("File name can't be empty");
        if (!fileName.endsWith("jpg") && !fileName.endsWith("png"))
            throw new WrongFileExtensionException("File can either be png or jpg");
        if (claimImage.getSize() == 0)
            throw new EmptyFileException("File can't be empty");
        try {
            final String imageUrl = fileUploadService.uploadFile(claimImage);
            Optional<Claim> maybeClaim = claimRepository.findById(claimId);
            if (!maybeClaim.isPresent())
                throw new ClaimNotFoundException("Claim with number " + claimId + " does not exist");
            Claim claim = maybeClaim.get();
            claim.setImageUrl(imageUrl);
            claimRepository.save(claim);
            return ResponseEntity.ok().body(imageUrl);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(path = "/download/{fileName}")
    public ResponseEntity<ByteArrayResource> uploadFile(@PathVariable String fileName) throws Exception {

        log.debug("Image url: {}", fileName);

        byte[] data = fileUploadService.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);

        return ResponseEntity
                .ok()
                .header("content-type", "application/octet-stream")
                .body(resource);

    }
}
