package com.terrain360.terrain360.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "superviseur")
@DiscriminatorValue("SUPERVISEUR")
@PrimaryKeyJoinColumn(name = "employe_id")
public class Superviseur extends Employe {

    @OneToMany(mappedBy = "superviseur", cascade = CascadeType.ALL)
    @JsonManagedReference("superviseur-etudes") // Break circular reference
    private List<Etude> etudes;

    @OneToMany(mappedBy = "superviseur", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CommentaireEnqueteur> commentaires;

    public List<Etude> getEtudes() {
        return etudes;
    }

    public void setEtudes(List<Etude> etudes) {
        this.etudes = etudes;
    }

    public List<CommentaireEnqueteur> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<CommentaireEnqueteur> commentaires) {
        this.commentaires = commentaires;
    }
}