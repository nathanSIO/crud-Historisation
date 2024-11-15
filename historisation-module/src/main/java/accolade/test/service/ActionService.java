package accolade.test.service;

public interface ActionService {
    
    void saveAction(Long id, String comm, int actionType);

    void saveActionObject(Long id, String comm, int actionType, Object obj);

    <T> void saveActionGeneric(Long id, String comm, int actionType, T obj);

    <T extends IHistorisable> void saveCreateAction(Long id, String comm, int actionType, T obj);

    <T extends IHistorisable> void saveDeleteAction(Long id, String comm, int actionType, T obj);

    <T extends IHistorisable> void saveUpdateAction(Long id, String comm, int actionType, T obj);

    void deleteActions();
}
