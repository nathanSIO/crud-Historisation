package accolade.test.historisation.Service;

import java.util.List;

import accolade.test.historisation.Entity.Personnel;

// @Service
public interface PersonnelService {
    
    Personnel savePersonnel(Personnel personnel);

    void deletePersonnelById(Long id);

    Personnel getPersonnelById(Long id);

    List<Personnel> getAllPersonnel();
}
