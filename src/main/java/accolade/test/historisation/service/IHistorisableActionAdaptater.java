package accolade.test.historisation.service;

import accolade.test.historisation.entity.Personnel;
import accolade.test.service.IHistorisable;

public class IHistorisableActionAdaptater implements IHistorisable{

    private Personnel personnel;

    private Object obj;

    public IHistorisableActionAdaptater(Object obj) {
        this.obj = obj;
    }

    public IHistorisableActionAdaptater(Personnel personnel) {
        this.personnel = personnel;
    }

    public String saveCreateAction(){
        return "Création : " + personnel.toString();
    }

    public String saveUpdateAction(){
        return "Mise a jour des champs : " + obj ;
    }

    public String saveDeleteAction(){
        return "Suppression " + personnel.toString();
    }
}
