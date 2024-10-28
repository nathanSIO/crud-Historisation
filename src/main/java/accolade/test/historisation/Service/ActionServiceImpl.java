package accolade.test.historisation.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import accolade.test.historisation.Entity.Action;
import accolade.test.historisation.Entity.ActionType;
import accolade.test.historisation.Entity.Personnel;
import accolade.test.historisation.Repository.ActionRepository;

@Service
public class ActionServiceImpl implements ActionService{
    
    @Autowired
    ActionRepository actionRepository;

    @Override
    public void saveAction(Personnel personnel, ActionType actionType){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        // Calendar calendar = new GregorianCalendar();
        // Date date = calendar.getTime();
        LocalDateTime localDateTime = LocalDateTime.now();

        String commentaire ;

        switch (actionType) {
            case Mise_a_jour:
                commentaire  = "Mise à jour du personnel : " + personnel.getNom() + " " + personnel.getPrenom();
                break;
        
            case Suppression:
                commentaire  = "Suppression du personnel : " + personnel.getNom() + " " + personnel.getPrenom();
                break;

            case Ajout:
                commentaire  = "Ajout du personnel : " + personnel.getNom() + " " + personnel.getPrenom();
                break;

            default:
                commentaire  = "Action sur le personnel : " + personnel.getNom() + " " + personnel.getPrenom();
                break  ;
        }

        Action action = new Action(personnel.getId(), localDateTime, actionType, commentaire);
        actionRepository.save(action);
    }

    @Override
    public void deleteActions(){
        actionRepository.deleteActionsBefore(LocalDateTime.now().minusMinutes(15));
    }
}
