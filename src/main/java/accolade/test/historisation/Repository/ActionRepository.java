package accolade.test.historisation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import accolade.test.historisation.Entity.Action;

public interface ActionRepository extends JpaRepository<Action,Long>{
    
}
