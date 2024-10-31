package accolade.test.service;

public interface ActionService {
    
    void saveAction(Long id, String comm, int actionType);

    void deleteActions();
}
