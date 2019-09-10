package com.ai.sam.domain;
import com.ai.sam.common.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Table(name = "`t_sam_role`")
@ApiModel
public class TSamRole extends Page {
    /**
     * 角色编号
     */
    @Id
    @Column(name = "`ROLE_ID`")
    @ApiModelProperty(name = "ROLEID", value = "", required = true, example = "")
    @JsonProperty("ROLEID")
    private String roleId;

    /**
     * 角色名称
     */
    @Column(name = "`ROLE_NAME`")
    @ApiModelProperty(name = "ROLENAME", value = "", required = true, example = "")
    @JsonProperty("ROLENAME")
    private String roleName;

    /**
     * 角色编码
     */
    @Column(name = "`ROLE_CODE`")
    @ApiModelProperty(name = "ROLECODE", value = "", required = true, example = "")
    @JsonProperty("ROLECODE")
    private String roleCode;

    /**
     * 上级角色编码
     */
    @Column(name = "`SUPER_CODE`")
    @ApiModelProperty(name = "SUPERCODE", value = "", required = true, example = "")
    @JsonProperty("SUPERCODE")
    private String superCode;

    /**
     * 描述信息
     */
    @Column(name = "`DESCRIPTION`")
    @ApiModelProperty(name = "DESCRIPTION", value = "", required = true, example = "")
    @JsonProperty("DESCRIPTION")
    private String description;

    /**
     * 模块ID
     */
    @Column(name = "`MODULE_ID`")
    @ApiModelProperty(name = "MODULEID", value = "", required = true, example = "")
    @JsonProperty("MODULEID")
    private String moduleId;

    /**
     * 拥有角色员工数
     */
    @Column(name = "`COUNT`")
    @ApiModelProperty(name = "COUNT", value = "", required = true, example = "")
    @JsonProperty("COUNT")
    private String count;

    /**
     * 租户ID
     */
    @Column(name = "`TENANT_ID`")
    @ApiModelProperty(name = "TENANTID", value = "", required = true, example = "")
    @JsonProperty("TENANTID")
    private String tenantId;


    private String moduleName;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }


    /**
     * 获取角色编号
     *
     * @return ROLE_ID - 角色编号
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置角色编号
     *
     * @param roleId 角色编号
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    /**
     * 获取角色名称
     *
     * @return ROLE_NAME - 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * 获取角色编码
     *
     * @return ROLE_CODE - 角色编码
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * 设置角色编码
     *
     * @param roleCode 角色编码
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    /**
     * 获取上级角色编码
     *
     * @return SUPER_CODE - 上级角色编码
     */
    public String getSuperCode() {
        return superCode;
    }

    /**
     * 设置上级角色编码
     *
     * @param superCode 上级角色编码
     */
    public void setSuperCode(String superCode) {
        this.superCode = superCode == null ? null : superCode.trim();
    }

    /**
     * 获取描述信息
     *
     * @return DESCRIPTION - 描述信息
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述信息
     *
     * @param description 描述信息
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取模块ID
     *
     * @return MODULE_ID - 模块ID
     */
    public String getModuleId() {
        return moduleId;
    }

    /**
     * 设置模块ID
     *
     * @param moduleId 模块ID
     */
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId == null ? null : moduleId.trim();
    }

    /**
     * 获取拥有角色员工数
     *
     * @return COUNT - 拥有角色员工数
     */
    public String getCount() {
        return count;
    }

    /**
     * 设置拥有角色员工数
     *
     * @param count 拥有角色员工数
     */
    public void setCount(String count) {
        this.count = count == null ? null : count.trim();
    }

    /**
     * 获取租户ID
     *
     * @return TENANT_ID - 租户ID
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * 设置租户ID
     *
     * @param tenantId 租户ID
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }
}
