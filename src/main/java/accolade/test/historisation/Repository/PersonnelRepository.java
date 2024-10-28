package accolade.test.historisation.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import accolade.test.historisation.Entity.Personnel;

@Repository
public interface PersonnelRepository extends JpaRepository<Personnel,Long>{
    
    @Query("SELECT p FROM Personnel p WHERE id=?1 ")
    Optional<Personnel> findById(Long id);
}
