/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
    @NamedQuery(name = "Tusergroup.findByUseremail", query = "SELECT t FROM Tusergroup t WHERE t.tusergroupPK.useremail = :useremail"),
    @NamedQuery(name = "Tusergroup.findByGroupcode", query = "SELECT t FROM Tusergroup t WHERE t.tusergroupPK.groupcode = :groupcode"),
    @NamedQuery(name = "Tusergroup.findByUsertreat", query = "SELECT t FROM Tusergroup t WHERE t.usertreat = :usertreat"),
    @NamedQuery(name = "Tusergroup.findByDatetestdone", query = "SELECT t FROM Tusergroup t WHERE t.datetestdone = :datetestdone")})
public class Tusergroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TusergroupPK tusergroupPK;
    @Column(name = "usertreat")
    private Short usertreat;
    @Column(name = "datetestdone")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetestdone;
    @JoinColumn(name = "useremail", referencedColumnName = "useremail", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tuser tuser;
    @JoinColumn(name = "groupcode", referencedColumnName = "groupcode", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tgroup tgroup;

    public Tusergroup() {
    }

    public Tusergroup(TusergroupPK tusergroupPK) {
        this.tusergroupPK = tusergroupPK;
    }

    public Tusergroup(String useremail, int groupcode) {
        this.tusergroupPK = new TusergroupPK(useremail, groupcode);
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
