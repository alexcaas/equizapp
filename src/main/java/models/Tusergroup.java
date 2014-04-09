/*
 */

package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "tusergroup")
@NamedQueries({
    @NamedQuery(name = "Tusergroup.findAll", query = "SELECT t FROM Tusergroup t"),
    @NamedQuery(name = "Tusergroup.findByGroupcode", query = "SELECT t FROM Tusergroup t WHERE t.tusergroupPK.groupcode = :groupcode"),
    @NamedQuery(name = "Tusergroup.findByUsertreat", query = "SELECT t FROM Tusergroup t WHERE t.usertreat = :usertreat"),
    @NamedQuery(name = "Tusergroup.findByDatetestdone", query = "SELECT t FROM Tusergroup t WHERE t.datetestdone = :datetestdone"),
    @NamedQuery(name = "Tusergroup.findByUserid", query = "SELECT t FROM Tusergroup t WHERE t.tusergroupPK.userid = :userid"),
    @NamedQuery(name = "Tusergroup.findByUsertreatlastmodif", query = "SELECT t FROM Tusergroup t WHERE t.usertreatlastmodif = :usertreatlastmodif")})
public class Tusergroup implements Serializable {    
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TusergroupPK tusergroupPK;
    @Column(name = "usertreat")
    private Short usertreat;
    @Column(name = "datetestdone")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetestdone;
    @Column(name = "usertreatlastmodif")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usertreatlastmodif;
    @JoinColumn(name = "userid", referencedColumnName = "userid", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch=FetchType.EAGER)
    @JsonBackReference
    private Tuser tuser;
    @JoinColumn(name = "groupcode", referencedColumnName = "groupcode", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch=FetchType.EAGER)
    @JsonManagedReference
    @JsonIgnore
    private Tgroup tgroup;
   

    public Tusergroup() {
    }

    public Tusergroup(TusergroupPK tusergroupPK) {
        this.tusergroupPK = tusergroupPK;
    }

    public Tusergroup(int groupcode, long userid) {
        this.tusergroupPK = new TusergroupPK(groupcode, userid);
    }

    public TusergroupPK getTusergroupPK() {
        return tusergroupPK;
    }

    public void setTusergroupPK(TusergroupPK tusergroupPK) {
        this.tusergroupPK = tusergroupPK;
    }

    public Short getUsertreat() {
        return usertreat;
    }

    public void setUsertreat(Short usertreat) {
        this.usertreat = usertreat;
    }

    public Date getDatetestdone() {
        return datetestdone;
    }

    public void setDatetestdone(Date datetestdone) {
        this.datetestdone = datetestdone;
    }

    public Date getUsertreatlastmodif() {
        return usertreatlastmodif;
    }

    public void setUsertreatlastmodif(Date usertreatlastmodif) {
        this.usertreatlastmodif = usertreatlastmodif;
    }

    public Tuser getTuser() {
        return tuser;
    }

    public void setTuser(Tuser tuser) {
        this.tuser = tuser;
    }

    public Tgroup getTgroup() {
        return tgroup;
    }

    public void setTgroup(Tgroup tgroup) {
        this.tgroup = tgroup;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tusergroupPK != null ? tusergroupPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tusergroup)) {
            return false;
        }
        Tusergroup other = (Tusergroup) object;
        if ((this.tusergroupPK == null && other.tusergroupPK != null) || (this.tusergroupPK != null && !this.tusergroupPK.equals(other.tusergroupPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Tusergroup[ tusergroupPK=" + tusergroupPK + " ]";
    }
    
}
