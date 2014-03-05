/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "tgroup")
@NamedQueries({
    @NamedQuery(name = "Tgroup.findAll", query = "SELECT t FROM Tgroup t"),
    @NamedQuery(name = "Tgroup.findByGroupcode", query = "SELECT t FROM Tgroup t WHERE t.groupcode = :groupcode"),
    @NamedQuery(name = "Tgroup.findByGroupname", query = "SELECT t FROM Tgroup t WHERE t.groupname = :groupname"),
    @NamedQuery(name = "Tgroup.findByGroupitemsnumber", query = "SELECT t FROM Tgroup t WHERE t.groupitemsnumber = :groupitemsnumber"),
    @NamedQuery(name = "Tgroup.findByGroupdatecreation", query = "SELECT t FROM Tgroup t WHERE t.groupdatecreation = :groupdatecreation")})
public class Tgroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "groupcode")
    private Integer groupcode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "groupname")
    private String groupname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "groupitemsnumber")
    private short groupitemsnumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "groupdatecreation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date groupdatecreation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupcode")
    private Collection<Titem> titemCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tgroup")
    private Collection<Tusergroup> tusergroupCollection;

    public Tgroup() {
    }

    public Tgroup(Integer groupcode) {
        this.groupcode = groupcode;
    }

    public Tgroup(Integer groupcode, String groupname, short groupitemsnumber, Date groupdatecreation) {
        this.groupcode = groupcode;
        this.groupname = groupname;
        this.groupitemsnumber = groupitemsnumber;
        this.groupdatecreation = groupdatecreation;
    }

    public Integer getGroupcode() {
        return groupcode;
    }

    public void setGroupcode(Integer groupcode) {
        this.groupcode = groupcode;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public short getGroupitemsnumber() {
        return groupitemsnumber;
    }

    public void setGroupitemsnumber(short groupitemsnumber) {
        this.groupitemsnumber = groupitemsnumber;
    }

    public Date getGroupdatecreation() {
        return groupdatecreation;
    }

    public void setGroupdatecreation(Date groupdatecreation) {
        this.groupdatecreation = groupdatecreation;
    }

    public Collection<Titem> getTitemCollection() {
        return titemCollection;
    }

    public void setTitemCollection(Collection<Titem> titemCollection) {
        this.titemCollection = titemCollection;
    }

    public Collection<Tusergroup> getTusergroupCollection() {
        return tusergroupCollection;
    }

    public void setTusergroupCollection(Collection<Tusergroup> tusergroupCollection) {
        this.tusergroupCollection = tusergroupCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupcode != null ? groupcode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tgroup)) {
            return false;
        }
        Tgroup other = (Tgroup) object;
        if ((this.groupcode == null && other.groupcode != null) || (this.groupcode != null && !this.groupcode.equals(other.groupcode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Tgroup[ groupcode=" + groupcode + " ]";
    }
    
}
