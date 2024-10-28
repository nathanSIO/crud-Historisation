package accolade.test.historisation.Service;

import accolade.test.historisation.Entity.ActionType;
import accolade.test.historisation.Entity.Personnel;

public interface ActionService {
    
    void saveAction(Personnel personnel, ActionType actionType);

    void deleteActions();
}
