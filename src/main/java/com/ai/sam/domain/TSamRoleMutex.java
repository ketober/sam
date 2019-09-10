package com.ai.sam.domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Table(name = "`t_sam_role_mutex`")
@ApiModel
public class TSamRoleMutex {
    @Id
    @Column(name = "`MUTEX_ID`")
    @ApiModelProperty(name = "MUTEXID", value = "", required = true, example = "")
    @JsonProperty("MUTEXID")
    private String mutexId;

    @Column(name = "`ROLEID`")
    @ApiModelProperty(name = "ROLEID", value = "", required = true, example = "")
    @JsonProperty("ROLEID")
    private String roleid;

    @Column(name = "`MUTEX_ROLE_ID`")
    @ApiModelProperty(name = "MUTEXROLEID", value = "", required = true, example = "")
    @JsonProperty("MUTEXROLEID")
    private String mutexRoleId;

    /**
     * @return MUTEX_ID
     */
    public String getMutexId() {
        return mutexId;
    }

    /**
     * @param mutexId
     */
    public void setMutexId(String mutexId) {
        this.mutexId = mutexId == null ? null : mutexId.trim();
    }

    /**
     * @return ROLEID
     */
    public String getRoleid() {
        return roleid;
    }

    /**
     * @param roleid
     */
    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }

    /**
     * @return MUTEX_ROLE_ID
     */
    public String getMutexRoleId() {
        return mutexRoleId;
    }

    /**
     * @param mutexRoleId
     */
    public void setMutexRoleId(String mutexRoleId) {
        this.mutexRoleId = mutexRoleId == null ? null : mutexRoleId.trim();
    }
}
