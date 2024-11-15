package accolade.test.historisation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import accolade.test.historisation.entity.Personnel;

@Service
public class HistorisablePersonnelService implements IHistorisableService{
    
    @Override
    public String getChanges(Personnel oldPersonnel, Personnel updatePersonnel){
        List<String> changes = new ArrayList<>();

        if (!oldPersonnel.getNom().equals(updatePersonnel.getNom())){
            changes.add("Ancien nom : " + oldPersonnel.getNom() + ", Nouveau nom : " + updatePersonnel.getNom() + "; ");
        }
        if (!oldPersonnel.getPrenom().equals(updatePersonnel.getPrenom())){
            changes.add("Ancien prenom : " + oldPersonnel.getPrenom() + ", Nouveau prenom : " + updatePersonnel.getPrenom() + "; ");
        }
        if (!oldPersonnel.getRole().equals(updatePersonnel.getRole())){
            changes.add("Ancien role : " + oldPersonnel.getRole() + ", Nouveau role : " + updatePersonnel.getRole() + "; ");
        }
        return changes.toString();
    }
}
