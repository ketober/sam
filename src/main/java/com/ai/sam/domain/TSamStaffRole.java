package com.ai.sam.domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Table(name = "`t_sam_staff_role`")
@ApiModel
public class TSamStaffRole {
    /**
     * 双中心ID
     */
    @Id
    @Column(name = "`REL_ID`")
    @ApiModelProperty(name = "RELID", value = "", required = true, example = "")
    @JsonProperty("RELID")
    private String relId;

    /**
     * 角色编号。
     */
    @Column(name = "`ROLE_ID`")
    @ApiModelProperty(name = "ROLEID", value = "", required = true, example = "")
    @JsonProperty("ROLEID")
    private String roleId;

    /**
     * 人员编号。
     */
    @Column(name = "`STAFF_ID`")
    @ApiModelProperty(name = "STAFFID", value = "", required = true, example = "")
    @JsonProperty("STAFFID")
    private String staffId;

    /**
     * 获取双中心ID
     *
     * @return REL_ID - 双中心ID
     */
    public String getRelId() {
        return relId;
    }

    /**
     * 设置双中心ID
     *
     * @param relId 双中心ID
     */
    public void setRelId(String relId) {
        this.relId = relId == null ? null : relId.trim();
    }

    /**
     * 获取角色编号。
     *
     * @return ROLE_ID - 角色编号。
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置角色编号。
     *
     * @param roleId 角色编号。
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    /**
     * 获取人员编号。
     *
     * @return STAFF_ID - 人员编号。
     */
    public String getStaffId() {
        return staffId;
    }

    /**
     * 设置人员编号。
     *
     * @param staffId 人员编号。
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId == null ? null : staffId.trim();
    }
}
