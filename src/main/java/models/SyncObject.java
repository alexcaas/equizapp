/*
 */
package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "useremail",
    "lastsyncdate",
    "SyncGroups"
})
public class SyncObject {

    @JsonProperty("useremail")
    private String useremail;
    @JsonProperty("lastsyncdate")
    private Date lastsyncdate;
    @JsonProperty("SyncGroups")
    private List<SyncGroup> syncGroups = new ArrayList<SyncGroup>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("useremail")
    public String getUseremail() {
        return useremail;
    }

    @JsonProperty("useremail")
    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    @JsonProperty("lastsyncdate")
    public Date getLastsyncdate() {
        return lastsyncdate;
    }

    @JsonProperty("lastsyncdate")
    public void setLastsyncdate(Date lastsyncdate) {
        this.lastsyncdate = lastsyncdate;
    }

    @JsonProperty("SyncGroups")
    public List<SyncGroup> getSyncGroups() {
        return syncGroups;
    }

    @JsonProperty("SyncGroups")
    public void setSyncGroups(List<SyncGroup> syncGroups) {
        this.syncGroups = syncGroups;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
