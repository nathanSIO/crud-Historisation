package accolade.test.historisation.service;

import accolade.test.historisation.entity.Personnel;

public interface IHistorisableService {
    
    String getChanges(Personnel p1, Personnel p2);
}
