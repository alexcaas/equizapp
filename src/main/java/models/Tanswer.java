/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "tanswer")
@NamedQueries({
    @NamedQuery(name = "Tanswer.findAll", query = "SELECT t FROM Tanswer t"),
    @NamedQuery(name = "Tanswer.findByAnswerid", query = "SELECT t FROM Tanswer t WHERE t.answerid = :answerid"),
    @NamedQuery(name = "Tanswer.findByAnswerstring", query = "SELECT t FROM Tanswer t WHERE t.answerstring = :answerstring"),
    @NamedQuery(name = "Tanswer.findByAnswercorrect", query = "SELECT t FROM Tanswer t WHERE t.answercorrect = :answercorrect")})
public class Tanswer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "answerid")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long answerid;
    @Basic(optional = false)
    @Size(min = 1, max = 2147483647)
    @Column(name = "answerstring")
    private String answerstring;
    @Basic(optional = false)
    @Column(name = "answercorrect")
    private boolean answercorrect;
    @JoinColumn(name = "itemid", referencedColumnName = "itemid")
    @ManyToOne(optional = false)
    private Titem itemid;

    public Tanswer() {
    }

    public Tanswer(Long answerid) {
        this.answerid = answerid;
    }

    public Tanswer(String answerstring, boolean answercorrect) {
        this.answerstring = answerstring;
        this.answercorrect = answercorrect;
    }

    public Long getAnswerid() {
        return answerid;
    }

    public void setAnswerid(Long answerid) {
        this.answerid = answerid;
    }

    public String getAnswerstring() {
        return answerstring;
    }

    public void setAnswerstring(String answerstring) {
        this.answerstring = answerstring;
    }

    public boolean getAnswercorrect() {
        return answercorrect;
    }

    public void setAnswercorrect(boolean answercorrect) {
        this.answercorrect = answercorrect;
    }

    public Titem getItemid() {
        return itemid;
    }

    public void setItemid(Titem itemid) {
        this.itemid = itemid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (answerid != null ? answerid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tanswer)) {
            return false;
        }
        Tanswer other = (Tanswer) object;
        if ((this.answerid == null && other.answerid != null) || (this.answerid != null && !this.answerid.equals(other.answerid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Tanswer[ answerid=" + answerid + " ]";
    }
    
}
