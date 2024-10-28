package accolade.test.historisation.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import accolade.test.historisation.Entity.Personnel;
import accolade.test.historisation.Repository.PersonnelRepository;

@Service
public class PersonnelServiceImpl implements PersonnelService {

    @Autowired
    PersonnelRepository personnelRepository;
    
    @Override
    public Personnel savePersonnel(Personnel personnel){
        return personnelRepository.save(personnel);
    }

    @Override
    public void deletePersonnelById(Long id){
        personnelRepository.deleteById(id);
    }

    @Override
    public Personnel getPersonnelById(Long id){
        return personnelRepository.findById(id).get();
    }

    @Override
    public List<Personnel> getAllPersonnel(){
        return personnelRepository.findAll();
    }
}
