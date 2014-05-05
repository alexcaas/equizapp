/*
 */

package models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    @NamedQuery(name = "Tanswer.findByAnswercorrect", query = "SELECT t FROM Tanswer t WHERE t.answercorrect = :answercorrect"),
    @NamedQuery(name = "Tanswer.findByItemid", query = "SELECT t FROM Tanswer t WHERE t.titem = :titem")})
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="@id")
public class Tanswer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "answerid")
    private Long answerid;
    @Basic(optional = false)
    @Size(min = 1, max = 2147483647)
    @Column(name = "answerstring")
    private String answerstring;
    @Basic(optional = false)
    @Column(name = "answercorrect")
    private boolean answercorrect;
    @JoinColumn(name = "itemid", referencedColumnName = "itemid")
    @ManyToOne(optional = false, fetch=FetchType.EAGER)
    private Titem titem;

    public Tanswer() {
    }

    public Tanswer(Titem item, String answerstring, boolean answercorrect) {
        this.titem = item;
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

    public Titem getItem() {
        return titem;
    }

    public void setItem(Titem item) {
        this.titem = item;
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
