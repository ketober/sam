package com.ai.sam.domain;
import com.ai.sam.common.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`t_sam_orga_info`")
@ApiModel
public class TSamOrgaInfo extends Page {
    /**
     * 组织机构编号。
     */
    @Id
    @Column(name = "`ORGA_ID`")
    @ApiModelProperty(name = "ORGAID", value = "", required = true, example = "")
    @JsonProperty("ORGAID")
    private String orgaId;

    /**
     * 组织机构名称。
     */
    @Column(name = "`ORGA_NAME`")
    @ApiModelProperty(name = "ORGANAME", value = "", required = true, example = "")
    @JsonProperty("ORGANAME")
    private String orgaName;

    /**
     * 组织机构类型。按级别划分：公司、省份、地市、组织、班组
     */
    @Column(name = "`ORGA_TYPE_ID`")
    @ApiModelProperty(name = "ORGATYPEID", value = "", required = true, example = "")
    @JsonProperty("ORGATYPEID")
    private String orgaTypeId;

    /**
     * 组织机构状态。取值含义：0：在用 1：废弃 2：暂停 缺省值：0
     */
    @Column(name = "`ORGA_STATE`")
    @ApiModelProperty(name = "ORGASTATE", value = "", required = true, example = "")
    @JsonProperty("ORGASTATE")
    private String orgaState;

    /**
     * 组织机构节点编码。
     */
    @Column(name = "`ORGA_CODE`")
    @ApiModelProperty(name = "ORGACODE", value = "", required = true, example = "")
    @JsonProperty("ORGACODE")
    private String orgaCode;

    /**
     * 父组织机构节点编码。
     */
    @Column(name = "`SUPER_ORGA_CODE`")
    @ApiModelProperty(name = "SUPERORGACODE", value = "", required = true, example = "")
    @JsonProperty("SUPERORGACODE")
    private String superOrgaCode;

    /**
     * 组织机构描述信息。
     */
    @Column(name = "`ORGA_DESC`")
    @ApiModelProperty(name = "ORGADESC", value = "", required = true, example = "")
    @JsonProperty("ORGADESC")
    private String orgaDesc;

    /**
     * 更新时间
     */
    @Column(name = "`UPDATE_TIME`")
    @ApiModelProperty(name = "UPDATETIME", value = "", required = true, example = "")
    @JsonProperty("UPDATETIME")
    private Date updateTime;

    /**
     * 添加时间
     */
    @Column(name = "`INSERT_TIME`")
    @ApiModelProperty(name = "INSERTTIME", value = "", required = true, example = "")
    @JsonProperty("INSERTTIME")
    private Date insertTime;

    /**
     * 租户ID
     */
    @Column(name = "`TENANT_ID`")
    @ApiModelProperty(name = "TENANTID", value = "", required = true, example = "")
    @JsonProperty("TENANTID")
    private String tenantId;

    //映射字段
    @ApiModelProperty(name = "ORGA_TYPE_NAME", value = "", required = true, example = "")
    @JsonProperty("ORGA_TYPE_NAME")
    private String orgaTypeName;
    @ApiModelProperty(name = "ORGA_STATE_NAME", value = "", required = true, example = "")
    @JsonProperty("ORGA_STATE_NAME")
    private String orgaStateName;

    public String getOrgaTypeName() {
        return orgaTypeName;
    }

    public void setOrgaTypeName(String orgaTypeName) {
        this.orgaTypeName = orgaTypeName;
    }

    public String getOrgaStateName() {
        return orgaStateName;
    }

    public void setOrgaStateName(String orgaStateName) {
        this.orgaStateName = orgaStateName;
    }

    /**
     * 获取组织机构编号。
     *
     * @return ORGA_ID - 组织机构编号。
     */
    public String getOrgaId() {
        return orgaId;
    }

    /**
     * 设置组织机构编号。
     *
     * @param orgaId 组织机构编号。
     */
    public void setOrgaId(String orgaId) {
        this.orgaId = orgaId == null ? null : orgaId.trim();
    }

    /**
     * 获取组织机构名称。
     *
     * @return ORGA_NAME - 组织机构名称。
     */
    public String getOrgaName() {
        return orgaName;
    }

    /**
     * 设置组织机构名称。
     *
     * @param orgaName 组织机构名称。
     */
    public void setOrgaName(String orgaName) {
        this.orgaName = orgaName == null ? null : orgaName.trim();
    }

    /**
     * 获取组织机构类型。按级别划分：公司、省份、地市、组织、班组
     *
     * @return ORGA_TYPE_ID - 组织机构类型。按级别划分：公司、省份、地市、组织、班组
     */
    public String getOrgaTypeId() {
        return orgaTypeId;
    }

    /**
     * 设置组织机构类型。按级别划分：公司、省份、地市、组织、班组
     *
     * @param orgaTypeId 组织机构类型。按级别划分：公司、省份、地市、组织、班组
     */
    public void setOrgaTypeId(String orgaTypeId) {
        this.orgaTypeId = orgaTypeId == null ? null : orgaTypeId.trim();
    }

    /**
     * 获取组织机构状态。取值含义：0：在用 1：废弃 2：暂停 缺省值：0
     *
     * @return ORGA_STATE - 组织机构状态。取值含义：0：在用 1：废弃 2：暂停 缺省值：0
     */
    public String getOrgaState() {
        return orgaState;
    }

    /**
     * 设置组织机构状态。取值含义：0：在用 1：废弃 2：暂停 缺省值：0
     *
     * @param orgaState 组织机构状态。取值含义：0：在用 1：废弃 2：暂停 缺省值：0
     */
    public void setOrgaState(String orgaState) {
        this.orgaState = orgaState == null ? null : orgaState.trim();
    }

    /**
     * 获取组织机构节点编码。
     *
     * @return ORGA_CODE - 组织机构节点编码。
     */
    public String getOrgaCode() {
        return orgaCode;
    }

    /**
     * 设置组织机构节点编码。
     *
     * @param orgaCode 组织机构节点编码。
     */
    public void setOrgaCode(String orgaCode) {
        this.orgaCode = orgaCode == null ? null : orgaCode.trim();
    }

    /**
     * 获取父组织机构节点编码。
     *
     * @return SUPER_ORGA_CODE - 父组织机构节点编码。
     */
    public String getSuperOrgaCode() {
        return superOrgaCode;
    }

    /**
     * 设置父组织机构节点编码。
     *
     * @param superOrgaCode 父组织机构节点编码。
     */
    public void setSuperOrgaCode(String superOrgaCode) {
        this.superOrgaCode = superOrgaCode == null ? null : superOrgaCode.trim();
    }

    /**
     * 获取组织机构描述信息。
     *
     * @return ORGA_DESC - 组织机构描述信息。
     */
    public String getOrgaDesc() {
        return orgaDesc;
    }

    /**
     * 设置组织机构描述信息。
     *
     * @param orgaDesc 组织机构描述信息。
     */
    public void setOrgaDesc(String orgaDesc) {
        this.orgaDesc = orgaDesc == null ? null : orgaDesc.trim();
    }

    /**
     * 获取更新时间
     *
     * @return UPDATE_TIME - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取添加时间
     *
     * @return INSERT_TIME - 添加时间
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     * 设置添加时间
     *
     * @param insertTime 添加时间
     */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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
