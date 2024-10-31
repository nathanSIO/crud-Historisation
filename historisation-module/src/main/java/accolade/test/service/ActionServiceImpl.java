package accolade.test.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import accolade.test.entity.Action;
import accolade.test.repository.ActionRepository;


@Service
public class ActionServiceImpl implements ActionService{
    
    @Autowired
    private ActionRepository actionRepository;

    // public ActionServiceImpl(ActionRepository actionRepository) {
    //     this.actionRepository = actionRepository;
    // }

    @Override
    public void saveAction(Long id, String comm, int actionType){

        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        // Calendar calendar = new GregorianCalendar();
        // Date date = calendar.getTime();
        LocalDateTime localDateTime = LocalDateTime.now();

        Action action = new Action(id, localDateTime, actionType, comm);
        actionRepository.save(action);
    }

    @Override
    public void deleteActions(){
        actionRepository.deleteActionsBefore(LocalDateTime.now().minusMinutes(3));
    }
}
