package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.model.Phone;

// third
@Repository
public interface PhoneRepository extends JpaRepository<Phone, Integer> {
}
