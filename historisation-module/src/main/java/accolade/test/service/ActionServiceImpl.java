package accolade.test.service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// import com.fasterxml.jackson.databind.ObjectMapper;

import accolade.test.entity.Action;
import accolade.test.repository.ActionRepository;


@Service
public class ActionServiceImpl implements ActionService{
    
    @Autowired
    private ActionRepository actionRepository;

    private static ObjectMapper objectMapper = new ObjectMapper();

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
    public void saveActionObject(Long id, String comm, int actionType, Object obj){

        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        // Calendar calendar = new GregorianCalendar();
        // Date date = calendar.getTime();
        System.out.println("JE SUIS AVANT MON OBJET");
        System.out.println(obj);
        LocalDateTime localDateTime = LocalDateTime.now();

        try {
            String objString = objectMapper.writeValueAsString(obj);
            Action action = new Action(id, localDateTime, actionType, comm, objString);
            actionRepository.save(action);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }   
    }

    @Override
    public <T> void saveActionGeneric(Long id, String comm, int actionType, T obj){

        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        // Calendar calendar = new GregorianCalendar();
        // Date date = calendar.getTime();
        System.out.println("JE SUIS AVANT MON OBJET");
        System.out.println(obj.getClass().getMethods());
        LocalDateTime localDateTime = LocalDateTime.now();

        // System.out.println(obj);

        try {
            String objString = objectMapper.writeValueAsString(obj);
            Action action = new Action(id, localDateTime, actionType, comm, objString);
            actionRepository.save(action);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    @Override
    public <T extends IHistorisable> void saveCreateAction(Long id, String comm, int actionType, T obj){
        // System.out.println("JE SUIS AVANT MON OBJET");
        // System.out.println(obj.getClass().getMethods());
        LocalDateTime localDateTime = LocalDateTime.now();

        String objString = obj.saveCreateAction();
        System.out.println(objString);
        Action action = new Action(id, localDateTime, actionType, comm, objString);
        actionRepository.save(action);
    }

    @Override
    public <T extends IHistorisable> void saveDeleteAction(Long id, String comm, int actionType, T obj){
        // System.out.println("JE SUIS AVANT MON OBJET");
        // System.out.println(obj.getClass().getMethods());
        LocalDateTime localDateTime = LocalDateTime.now();

        String objString = obj.saveDeleteAction();
        System.out.println(objString);
        Action action = new Action(id, localDateTime, actionType, comm, objString);
        actionRepository.save(action);
    }

    @Override
    public <T extends IHistorisable> void saveUpdateAction(Long id, String comm, int actionType, T obj){
        LocalDateTime localDateTime = LocalDateTime.now();
        
        String objString = obj.saveUpdateAction();
        System.out.println(objString);
        Action action = new Action(id, localDateTime, actionType, comm, objString);
        actionRepository.save(action);
        // List<String> changes = getChanges(oldObj, newObj);
        // if(!changes.isEmpty()){
        //     for (String change : changes) {
        //         Action action = new Action(id, localDateTime, actionType, comm, change);
        //         actionRepository.save(action);
        //     }
        // }
        // else {
        //     System.out.println("Aucun changement détecté");
        // }
    }

    // public static <T extends IHistorisable> List<String> getChanges(T oldObj, T newObj){
    //     List<String> changes = new ArrayList<>();
    //     // System.out.println(oldObj.toString() + " ///// " + newObj.toString());
    //     // try {
    //     //     Field[] fields = oldObj.getClass().getDeclaredFields();
    //     //     for (Field field : fields){
    //     //         field.setAccessible(true);
    //     //         Object oldValue = field.get(oldObj);
    //     //         Object newValue = field.get(newObj);
    //     //         System.out.println(oldValue.toString() + " ///// " + newValue.toString());
    //     //         if (oldValue != null && !oldValue.equals(newValue) || (oldValue == null && newValue != null)){
    //     //             changes.add("Rubrique " + field.getName() + "change de " + oldValue + " à " + newValue);
    //     //         }
    //     //     }
    //     // } catch (IllegalAccessException e) {
    //     //     e.printStackTrace();
    //     // }    
    //     return changes;
    // }

    @Override
    public void deleteActions(){
        actionRepository.deleteActionsBefore(LocalDateTime.now().minusMinutes(3));
    }
}
