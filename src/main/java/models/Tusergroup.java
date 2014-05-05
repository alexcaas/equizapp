/*
 */

package models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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

/**
 *
 * @author alex
 */
@Entity
@Table(name = "tusergroup")
@NamedQueries({
    @NamedQuery(name = "Tusergroup.findAll", query = "SELECT t FROM Tusergroup t"),
    @NamedQuery(name = "Tusergroup.findByGroupcode", query = "SELECT t FROM Tusergroup t WHERE t.tusergroupPK.groupcode = :groupcode"),
    @NamedQuery(name = "Tusergroup.findByUsertrait", query = "SELECT t FROM Tusergroup t WHERE t.usertrait = :usertrait"),
    @NamedQuery(name = "Tusergroup.findByDatetestdone", query = "SELECT t FROM Tusergroup t WHERE t.datetestdone = :datetestdone"),
    @NamedQuery(name = "Tusergroup.findByUserid", query = "SELECT t FROM Tusergroup t WHERE t.tusergroupPK.userid = :userid"),
    @NamedQuery(name = "Tusergroup.findByUsertraitlastmodif", query = "SELECT t FROM Tusergroup t WHERE t.usertraitlastmodif = :usertraitlastmodif"),
    @NamedQuery(name = "Tusergroup.findByUseridAndGroupcode", query = "SELECT t FROM Tusergroup t WHERE (t.tusergroupPK.userid = :userid AND t.tusergroupPK.groupcode = :groupcode)")})
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="@id")
public class Tusergroup implements Serializable {    
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TusergroupPK tusergroupPK;
    @Column(name = "usertrait")
    private Short usertrait;
    @Column(name = "datetestdone")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetestdone;
    @Column(name = "usertraitlastmodif")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usertraitlastmodif;
    @JoinColumn(name = "userid", referencedColumnName = "userid", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch=FetchType.EAGER)
    @JsonIgnore
    private Tuser tuser;
    @JoinColumn(name = "groupcode", referencedColumnName = "groupcode", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch=FetchType.EAGER)
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

    public Short getUsertrait() {
        return usertrait;
    }

    public void setUsertrait(Short usertrait) {
        this.usertrait = usertrait;
    }

    public Date getDatetestdone() {
        return datetestdone;
    }

    public void setDatetestdone(Date datetestdone) {
        this.datetestdone = datetestdone;
    }

    public Date getUsertraitlastmodif() {
        return usertraitlastmodif;
    }

    public void setUsertraitlastmodif(Date usertraitlastmodif) {
        this.usertraitlastmodif = usertraitlastmodif;
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
