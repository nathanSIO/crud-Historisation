package accolade.test.historisation.service;

import java.util.List;

import accolade.test.historisation.entity.Personnel;

// @Service
public interface PersonnelService {
    
    Personnel savePersonnel(Personnel personnel);

    void deletePersonnelById(Long id);

    Personnel getPersonnelById(Long id);

    List<Personnel> getAllPersonnel();
}
