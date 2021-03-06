/*
 */

package models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "titem")
@NamedQueries({
    @NamedQuery(name = "Titem.findAll", query = "SELECT t FROM Titem t"),
    @NamedQuery(name = "Titem.findByItemid", query = "SELECT t FROM Titem t WHERE t.itemid = :itemid"),
    @NamedQuery(name = "Titem.findByItemstring", query = "SELECT t FROM Titem t WHERE t.itemstring = :itemstring"),
    @NamedQuery(name = "Titem.findByItemdifficulty", query = "SELECT t FROM Titem t WHERE t.itemdifficulty = :itemdifficulty"),
    @NamedQuery(name = "Titem.findByGroupcode", query = "SELECT t FROM Titem t WHERE t.tgroup = :tgroup")})
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="@id")
public class Titem implements Serializable {
    
    @Transient
    private Boolean changes;
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "itemid")
    private Long itemid;
    @Basic(optional = false)
    @Size(min = 1, max = 2147483647)
    @Column(name = "itemstring")
    private String itemstring;
    @Basic(optional = false)
    @Column(name = "itemdifficulty")
    private short itemdifficulty;
    @JoinColumn(name = "groupcode", referencedColumnName = "groupcode")
    @ManyToOne(optional = false, fetch=FetchType.EAGER)
    private Tgroup tgroup;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "titem", fetch=FetchType.EAGER, orphanRemoval=true)
    private Collection<Tanswer> tanswerCollection;
    

    public Titem() {
        this.changes = false;
    }

    public Titem(String itemstring, short itemdifficulty , Tgroup group) {
        this.itemstring = itemstring;
        this.itemdifficulty = itemdifficulty;
        this.tgroup = group;
        this.changes = false;
    }

    public Long getItemid() {
        return itemid;
    }

    public void setItemid(Long itemid) {
        this.itemid = itemid;
    }

    public String getItemstring() {
        return itemstring;
    }

    public void setItemstring(String itemstring) {
        this.itemstring = itemstring;
    }

    public short getItemdifficulty() {
        return itemdifficulty;
    }

    public void setItemdifficulty(short itemdifficulty) {
        this.itemdifficulty = itemdifficulty;
    }

    public Tgroup getGroup() {
        return tgroup;
    }

    public void setGroup(Tgroup group) {
        this.tgroup = group;
    }

    public Collection<Tanswer> getTanswerCollection() {
        return tanswerCollection;
    }

    public void setTanswerCollection(Collection<Tanswer> tanswerCollection) {
        this.tanswerCollection = tanswerCollection;
    }
    
    public Boolean isChanges() {
        return changes;
    }

    public void setChanges(Boolean changes) {
        this.changes = changes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemid != null ? itemid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Titem)) {
            return false;
        }
        Titem other = (Titem) object;
        if ((this.itemid == null && other.itemid != null) || (this.itemid != null && !this.itemid.equals(other.itemid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Titem[ itemid=" + itemid + " ]";
    }
    
}
