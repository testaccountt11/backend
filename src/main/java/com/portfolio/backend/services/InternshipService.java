package com.portfolio.backend.services;

import com.portfolio.backend.dto.InternshipApplicationDTO;
import com.portfolio.backend.models.Internship;
import com.portfolio.backend.models.InternshipApplication;
import com.portfolio.backend.models.User;
import com.portfolio.backend.repositories.InternshipApplicationRepository;
import com.portfolio.backend.repositories.InternshipRepository;
import com.portfolio.backend.repositories.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class InternshipService {
    private final InternshipRepository internshipRepository;
    private final UserRepository userRepository;
    private final InternshipApplicationRepository internshipApplicationRepository;
    @Value("${file.upload-dir}")
    private String uploadDir;

    public InternshipService(InternshipRepository internshipRepository, UserRepository userRepository, InternshipApplicationRepository internshipApplicationRepository) {
        this.internshipRepository = internshipRepository;
        this.userRepository = userRepository;
        this.internshipApplicationRepository = internshipApplicationRepository;
    }

    public List<Internship> getAllInternships() {
        return internshipRepository.findAll();
    }

    public List<Internship> getInternshipsByType(String type) {
        return internshipRepository.findByType(type);
    }

    public List<Internship> getInternshipsByLocation(String location) {
        return internshipRepository.findByLocation(location);
    }

    public InternshipApplication submitApplication(InternshipApplicationDTO dto) throws IOException {
        Internship internship = internshipRepository.findById(dto.getInternshipId())
            .orElseThrow(() -> new RuntimeException("Internship not found"));
    
        // Handle file upload
        String fileName = StringUtils.cleanPath(dto.getResume().getOriginalFilename());
        String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(uploadPath);
        Path filePath = uploadPath.resolve(uniqueFileName);
        Files.copy(dto.getResume().getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
    
        InternshipApplication application = new InternshipApplication();
        application.setInternship(internship);
        application.setFullName(dto.getFullName());
        application.setEmail(dto.getEmail());
        application.setPhone(dto.getPhone());
        application.setCoverLetter(dto.getCoverLetter());
        application.setPortfolio(dto.getPortfolio());
        application.setResumePath(uniqueFileName);
        application.setAppliedAt(LocalDateTime.now());
    
        return internshipApplicationRepository.save(application);
    }
}