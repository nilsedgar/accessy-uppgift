package se.nilsedgar.accessy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.nilsedgar.accessy.model.Lock;

import java.util.UUID;

@Repository
public interface LockRepository extends JpaRepository<Lock, UUID> {
    @Override
    Lock getById(UUID uuid);
}
