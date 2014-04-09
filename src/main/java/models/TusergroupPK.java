/*
 */

package models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author alex
 */
@Embeddable
public class TusergroupPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "groupcode")
    private int groupcode;
    @Basic(optional = false)
    @Column(name = "userid")
    private long userid;

    public TusergroupPK() {
    }

    public TusergroupPK(int groupcode, long userid) {
        this.groupcode = groupcode;
        this.userid = userid;
    }

    public int getGroupcode() {
        return groupcode;
    }

    public void setGroupcode(int groupcode) {
        this.groupcode = groupcode;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) groupcode;
        hash += (int) userid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TusergroupPK)) {
            return false;
        }
        TusergroupPK other = (TusergroupPK) object;
        if (this.groupcode != other.groupcode) {
            return false;
        }
        if (this.userid != other.userid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.TusergroupPK[ groupcode=" + groupcode + ", userid=" + userid + " ]";
    }
    
}
