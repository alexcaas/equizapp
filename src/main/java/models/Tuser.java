/*
 */

package models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;import javax.persistence.Transient;
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
    @NamedQuery(name = "Tuser.findByUseradmin", query = "SELECT t FROM Tuser t WHERE t.useradmin = :useradmin"),
    @NamedQuery(name = "Tuser.findByUserid", query = "SELECT t FROM Tuser t WHERE t.userid = :userid"),
    @NamedQuery(name = "Tuser.findByUserlastmodif", query = "SELECT t FROM Tuser t WHERE t.userlastmodif = :userlastmodif")})
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="@id")
public class Tuser implements Serializable {
    
    @Transient
    private Boolean changes;
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "userid")
    private Long userid;
    @Basic(optional = false)
    @Size(min = 1, max = 254)
    @Column(name = "useremail")
    private String useremail;
    @Basic(optional = false)
    @Size(min = 1, max = 254)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Size(min = 1, max = 254)
    @Column(name = "userlastname")
    private String userlastname;
    @Basic(optional = false)
    @Size(min = 1, max = 254)
    @Column(name = "userpassword")
    private String userpassword;
    @Basic(optional = false)
    @Column(name = "useradmin")
    private boolean useradmin;
    @Column(name = "userlastmodif")
    @Temporal(TemporalType.TIMESTAMP)
    private Date userlastmodif;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tuser", fetch=FetchType.EAGER)
    private Collection<Tusergroup> tusergroupCollection;


    public Tuser() {
        this.changes = false;
    }

    public Tuser(String useremail, String username, String userlastname, String userpassword, boolean useradmin) {
        this.useremail = useremail;
        this.username = username;
        this.userlastname = userlastname;
        this.userpassword = userpassword;
        this.useradmin = useradmin;
        this.changes = false;
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

    public boolean getUseradmin() {
        return useradmin;
    }

    public void setUseradmin(boolean useradmin) {
        this.useradmin = useradmin;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Date getUserlastmodif() {
        return userlastmodif;
    }

    public void setUserlastmodif(Date userlastmodif) {
        this.userlastmodif = userlastmodif;
    }

    public Collection<Tusergroup> getTusergroupCollection() {
        return tusergroupCollection;
    }

    public void setTusergroupCollection(Collection<Tusergroup> tusergroupCollection) {
        this.tusergroupCollection = tusergroupCollection;
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
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tuser)) {
            return false;
        }
        Tuser other = (Tuser) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Tuser[ userid=" + userid + " ]";
    }
    
}
