package io.mackness.Repositories;

import io.mackness.Entities.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderRepository extends JpaRepository<Reader, String> {
}
