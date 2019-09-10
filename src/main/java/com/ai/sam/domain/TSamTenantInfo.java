package com.ai.sam.domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`t_sam_tenant_info`")
@ApiModel
public class TSamTenantInfo {
    /**
     * TENANT_ID
     */
    @Id
    @Column(name = "`TENANT_ID`")
    @ApiModelProperty(name = "TENANTID", value = "", required = true, example = "")
    @JsonProperty("TENANTID")
    private String tenantId;

    /**
     * TENANT_NAME
     */
    @Column(name = "`TENANT_NAME`")
    @ApiModelProperty(name = "TENANTNAME", value = "", required = true, example = "")
    @JsonProperty("TENANTNAME")
    private String tenantName;

    /**
     * CRT_USER_ID
     */
    @Column(name = "`CRT_USER_ID`")
    @ApiModelProperty(name = "CRTUSERID", value = "", required = true, example = "")
    @JsonProperty("CRTUSERID")
    private String crtUserId;

    /**
     * CRT_TIME
     */
    @Column(name = "`CRT_TIME`")
    @ApiModelProperty(name = "CRTTIME", value = "", required = true, example = "")
    @JsonProperty("CRTTIME")
    private Date crtTime;

    /**
     * MODF_USER_ID
     */
    @Column(name = "`MODF_USER_ID`")
    @ApiModelProperty(name = "MODFUSERID", value = "", required = true, example = "")
    @JsonProperty("MODFUSERID")
    private String modfUserId;

    /**
     * MODF_TIME
     */
    @Column(name = "`MODF_TIME`")
    @ApiModelProperty(name = "MODFTIME", value = "", required = true, example = "")
    @JsonProperty("MODFTIME")
    private Date modfTime;

    /**
     * 获取TENANT_ID
     *
     * @return TENANT_ID - TENANT_ID
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * 设置TENANT_ID
     *
     * @param tenantId TENANT_ID
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    /**
     * 获取TENANT_NAME
     *
     * @return TENANT_NAME - TENANT_NAME
     */
    public String getTenantName() {
        return tenantName;
    }

    /**
     * 设置TENANT_NAME
     *
     * @param tenantName TENANT_NAME
     */
    public void setTenantName(String tenantName) {
        this.tenantName = tenantName == null ? null : tenantName.trim();
    }

    /**
     * 获取CRT_USER_ID
     *
     * @return CRT_USER_ID - CRT_USER_ID
     */
    public String getCrtUserId() {
        return crtUserId;
    }

    /**
     * 设置CRT_USER_ID
     *
     * @param crtUserId CRT_USER_ID
     */
    public void setCrtUserId(String crtUserId) {
        this.crtUserId = crtUserId == null ? null : crtUserId.trim();
    }

    /**
     * 获取CRT_TIME
     *
     * @return CRT_TIME - CRT_TIME
     */
    public Date getCrtTime() {
        return crtTime;
    }

    /**
     * 设置CRT_TIME
     *
     * @param crtTime CRT_TIME
     */
    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    /**
     * 获取MODF_USER_ID
     *
     * @return MODF_USER_ID - MODF_USER_ID
     */
    public String getModfUserId() {
        return modfUserId;
    }

    /**
     * 设置MODF_USER_ID
     *
     * @param modfUserId MODF_USER_ID
     */
    public void setModfUserId(String modfUserId) {
        this.modfUserId = modfUserId == null ? null : modfUserId.trim();
    }

    /**
     * 获取MODF_TIME
     *
     * @return MODF_TIME - MODF_TIME
     */
    public Date getModfTime() {
        return modfTime;
    }

    /**
     * 设置MODF_TIME
     *
     * @param modfTime MODF_TIME
     */
    public void setModfTime(Date modfTime) {
        this.modfTime = modfTime;
    }
}
