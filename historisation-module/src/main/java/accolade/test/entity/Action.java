package accolade.test.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;

@Entity
@Table(name = "Action")
public class Action <T> {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long UserId;

    @Column
    private LocalDateTime date;

    // @Enumerated(EnumType.STRING)
    @Column
    private int actionType;

    @Column
    private String commentaire;

    // @Column
    // @Convert(converter = GenericJsonConverter.class)
    @Column 
    private String obj;

    

    public Action(Long userId, LocalDateTime date, int actionType, String commentaire, String obj) {
        UserId = userId;
        this.date = date;
        this.actionType = actionType;
        this.commentaire = commentaire;
        this.obj = obj;
    }

    public Action(Long userId, LocalDateTime date, int actionType, String commentaire) {
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getActionType() {
        return actionType;
    }

    public void setActionType(int typeAction) {
        this.actionType = typeAction;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}
