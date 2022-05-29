package ru.itis.imare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.imare.models.FileInfo;

import java.util.Optional;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
    Optional<FileInfo> findByStorageFileName(String fileName);
}
