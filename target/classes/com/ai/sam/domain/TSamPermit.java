package com.ai.sam.domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Table(name = "`t_sam_permit`")
@ApiModel
public class TSamPermit {
    /**
     * 权限编号。
     */
    @Id
    @Column(name = "`PERMIT_ID`")
    @ApiModelProperty(name = "PERMITID", value = "", required = true, example = "")
    @JsonProperty("PERMITID")
    private String permitId;

    /**
     * 授权类型：STAFF、ROLE
     */
    @Column(name = "`PERMIT_TYPE`")
    @ApiModelProperty(name = "PERMITTYPE", value = "", required = true, example = "")
    @JsonProperty("PERMITTYPE")
    private String permitType;

    /**
     * 用户帐号。
     */
    @Column(name = "`ENTITY_ID`")
    @ApiModelProperty(name = "ENTITYID", value = "", required = true, example = "")
    @JsonProperty("ENTITYID")
    private String entityId;

    /**
     * 菜单、功能 权限编码
     */
    @Column(name = "`AUTH_OBJ_ID`")
    @ApiModelProperty(name = "AUTHOBJID", value = "", required = true, example = "")
    @JsonProperty("AUTHOBJID")
    private String authObjId;

    /**
     * 模块编号。
     */
    @Column(name = "`MODULE_ID`")
    @ApiModelProperty(name = "MODULEID", value = "", required = true, example = "")
    @JsonProperty("MODULEID")
    private String moduleId;

    /**
     * 权限应用ID
     */
    @Column(name = "`APPLICATIONID`")
    @ApiModelProperty(name = "APPLICATIONID", value = "", required = true, example = "")
    @JsonProperty("APPLICATIONID")
    private String applicationid;

    /**
     * 租户ID
     */
    @Column(name = "`TENANT_ID`")
    @ApiModelProperty(name = "TENANTID", value = "", required = true, example = "")
    @JsonProperty("TENANTID")
    private String tenantId;

    /**
     * 获取权限编号。
     *
     * @return PERMIT_ID - 权限编号。
     */
    public String getPermitId() {
        return permitId;
    }

    /**
     * 设置权限编号。
     *
     * @param permitId 权限编号。
     */
    public void setPermitId(String permitId) {
        this.permitId = permitId == null ? null : permitId.trim();
    }

    /**
     * 获取授权类型：STAFF、ROLE
     *
     * @return PERMIT_TYPE - 授权类型：STAFF、ROLE
     */
    public String getPermitType() {
        return permitType;
    }

    /**
     * 设置授权类型：STAFF、ROLE
     *
     * @param permitType 授权类型：STAFF、ROLE
     */
    public void setPermitType(String permitType) {
        this.permitType = permitType == null ? null : permitType.trim();
    }

    /**
     * 获取用户帐号。
     *
     * @return ENTITY_ID - 用户帐号。
     */
    public String getEntityId() {
        return entityId;
    }

    /**
     * 设置用户帐号。
     *
     * @param entityId 用户帐号。
     */
    public void setEntityId(String entityId) {
        this.entityId = entityId == null ? null : entityId.trim();
    }

    /**
     * 获取菜单、功能 权限编码
     *
     * @return AUTH_OBJ_ID - 菜单、功能 权限编码
     */
    public String getAuthObjId() {
        return authObjId;
    }

    /**
     * 设置菜单、功能 权限编码
     *
     * @param authObjId 菜单、功能 权限编码
     */
    public void setAuthObjId(String authObjId) {
        this.authObjId = authObjId == null ? null : authObjId.trim();
    }

    /**
     * 获取模块编号。
     *
     * @return MODULE_ID - 模块编号。
     */
    public String getModuleId() {
        return moduleId;
    }

    /**
     * 设置模块编号。
     *
     * @param moduleId 模块编号。
     */
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId == null ? null : moduleId.trim();
    }

    /**
     * 获取权限应用ID
     *
     * @return APPLICATIONID - 权限应用ID
     */
    public String getApplicationid() {
        return applicationid;
    }

    /**
     * 设置权限应用ID
     *
     * @param applicationid 权限应用ID
     */
    public void setApplicationid(String applicationid) {
        this.applicationid = applicationid == null ? null : applicationid.trim();
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
