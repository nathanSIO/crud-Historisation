package accolade.test.historisation.Entity;

public enum ActionType {
    
    Ajout(1),
    Mise_a_jour(2),
    Suppression(3);

    private final int id;

    private ActionType(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public static ActionType getActionTypefromId(int id) {
        for (ActionType type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Aucun type d'action avec l'id : " + id);
    }
}
