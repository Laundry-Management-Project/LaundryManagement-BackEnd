package project.laundry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.laundry.domain.entity.Business;

public interface BusinessRepository extends JpaRepository<Business, String> {

}
