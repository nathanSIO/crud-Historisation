package accolade.test.historisation.Repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import accolade.test.historisation.Entity.Action;

public interface ActionRepository extends JpaRepository<Action,Long>{
    
    @Transactional
    @Modifying
    @Query("DELETE FROM Action a WHERE a.date < :cutoffDate")
    void deleteActionsBefore(@Param("cutoffDate") LocalDateTime cutoffDate);
}
