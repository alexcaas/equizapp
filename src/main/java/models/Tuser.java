/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "tuser")
@NamedQueries({
    @NamedQuery(name = "Tuser.findAll", query = "SELECT t FROM Tuser t"),
    @NamedQuery(name = "Tuser.findByUseremail", query = "SELECT t FROM Tuser t WHERE t.useremail = :useremail"),
    @NamedQuery(name = "Tuser.findByUsername", query = "SELECT t FROM Tuser t WHERE t.username = :username"),
    @NamedQuery(name = "Tuser.findByUserlastname", query = "SELECT t FROM Tuser t WHERE t.userlastname = :userlastname"),
    @NamedQuery(name = "Tuser.findByUserpassword", query = "SELECT t FROM Tuser t WHERE t.userpassword = :userpassword"),
    @NamedQuery(name = "Tuser.findByUsersalt", query = "SELECT t FROM Tuser t WHERE t.usersalt = :usersalt"),
    @NamedQuery(name = "Tuser.findByUseradmin", query = "SELECT t FROM Tuser t WHERE t.useradmin = :useradmin")})
public class Tuser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "useremail")
    private String useremail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "userlastname")
    private String userlastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "userpassword")
    private String userpassword;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "usersalt")
    private String usersalt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "useradmin")
    private boolean useradmin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tuser")
    private Collection<Tusergroup> tusergroupCollection;

    public Tuser() {
    }

    public Tuser(String useremail) {
        this.useremail = useremail;
    }

    public Tuser(String useremail, String username, String userlastname, String userpassword, String usersalt, boolean useradmin) {
        this.useremail = useremail;
        this.username = username;
        this.userlastname = userlastname;
        this.userpassword = userpassword;
        this.usersalt = usersalt;
        this.useradmin = useradmin;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserlastname() {
        return userlastname;
    }

    public void setUserlastname(String userlastname) {
        this.userlastname = userlastname;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUsersalt() {
        return usersalt;
    }

    public void setUsersalt(String usersalt) {
        this.usersalt = usersalt;
    }

    public boolean getUseradmin() {
        return useradmin;
    }

    public void setUseradmin(boolean useradmin) {
        this.useradmin = useradmin;
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
        hash += (useremail != null ? useremail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tuser)) {
            return false;
        }
        Tuser other = (Tuser) object;
        if ((this.useremail == null && other.useremail != null) || (this.useremail != null && !this.useremail.equals(other.useremail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Tuser[ useremail=" + useremail + " ]";
    }
    
}
