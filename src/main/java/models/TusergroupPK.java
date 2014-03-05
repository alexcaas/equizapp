/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author alex
 */
@Embeddable
public class TusergroupPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "useremail")
    private String useremail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "groupcode")
    private int groupcode;

    public TusergroupPK() {
    }

    public TusergroupPK(String useremail, int groupcode) {
        this.useremail = useremail;
        this.groupcode = groupcode;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public int getGroupcode() {
        return groupcode;
    }

    public void setGroupcode(int groupcode) {
        this.groupcode = groupcode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (useremail != null ? useremail.hashCode() : 0);
        hash += (int) groupcode;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TusergroupPK)) {
            return false;
        }
        TusergroupPK other = (TusergroupPK) object;
        if ((this.useremail == null && other.useremail != null) || (this.useremail != null && !this.useremail.equals(other.useremail))) {
            return false;
        }
        if (this.groupcode != other.groupcode) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.TusergroupPK[ useremail=" + useremail + ", groupcode=" + groupcode + " ]";
    }
    
}
