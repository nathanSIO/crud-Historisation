package accolade.test.historisation.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Action")
public class Action {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long UserId;

    @Column
    private String date;

    @Enumerated(EnumType.STRING)
    @Column
    private ActionType actionType;

    @Column
    private String commentaire;

    

    public Action(Long userId, String date, ActionType actionType, String commentaire) {
        UserId = userId;
        this.date = date;
        this.actionType = actionType;
        this.commentaire = commentaire;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType typeAction) {
        this.actionType = typeAction;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}
