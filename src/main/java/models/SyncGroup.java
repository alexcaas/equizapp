/*
 */
package models;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "groupcodestr",
    "operation",
    "usertrait"
})
public class SyncGroup {

    @JsonProperty("groupcodestr")
    private String groupcodestr;
    @JsonProperty("operation")
    private String operation;
    @JsonProperty("usertrait")
    private short usertrait;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("groupcodestr")
    public String getGroupcodestr() {
        return groupcodestr;
    }

    @JsonProperty("groupcodestr")
    public void setGroupcodestr(String groupcodestr) {
        this.groupcodestr = groupcodestr;
    }

    @JsonProperty("operation")
    public String getOperation() {
        return operation;
    }

    @JsonProperty("operation")
    public void setOperation(String operation) {
        this.operation = operation;
    }

    @JsonProperty("usertrait")
    public short getUsertrait() {
        return usertrait;
    }

    @JsonProperty("usertrait")
    public void setUsertrait(short usertrait) {
        this.usertrait = usertrait;
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
