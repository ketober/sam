package com.ai.sam.domain;

import com.ai.sam.common.Page;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "`t_sam_group_info`")
@ApiModel
public class TSamGroupInfo extends Page {
    /**
     * 工作组编号
     */
    @Id
    @Column(name = "`GROUP_ID`")
    @ApiModelProperty(name = "GROUPID", value = "", required = true, example = "")
    @JsonProperty("GROUPID")
    private String groupId;

    /**
     * 工作组名称
     */
    @Column(name = "`GROUP_NAME`")
    @ApiModelProperty(name = "GROUPNAME", value = "", required = true, example = "")
    @JsonProperty("GROUPNAME")
    private String groupName;

    /**
     * 工作组类型编号
     */
    @Column(name = "`GROUP_TYPE_ID`")
    @ApiModelProperty(name = "GROUPTYPEID", value = "", required = true, example = "")
    @JsonProperty("GROUPTYPEID")
    private String groupTypeId;

    /**
     * 节点编号
     */
    @Column(name = "`GROUP_CODE`")
    @ApiModelProperty(name = "GROUPCODE", value = "", required = true, example = "")
    @JsonProperty("GROUPCODE")
    private String groupCode;

    /**
     * 父节点编号
     */
    @Column(name = "`SUPER_GROUP_CODE`")
    @ApiModelProperty(name = "SUPERGROUPCODE", value = "", required = true, example = "")
    @JsonProperty("SUPERGROUPCODE")
    private String superGroupCode;

    /**
     * 工作组描述
     */
    @Column(name = "`GROUP_DESC`")
    @ApiModelProperty(name = "GROUPDESC", value = "", required = true, example = "")
    @JsonProperty("GROUPDESC")
    private String groupDesc;

    /**
     * 省份ID
     */
    @Column(name = "`PROV_ID`")
    @ApiModelProperty(name = "PROVID", value = "", required = true, example = "")
    @JsonProperty("PROVID")
    private String provId;

    /**
     * 城市ID
     */
    @Column(name = "`CITY_ID`")
    @ApiModelProperty(name = "CITYID", value = "", required = true, example = "")
    @JsonProperty("CITYID")
    private String cityId;

    /**
     * 租户ID
     */
    @Column(name = "`TENANT_ID`")
    @ApiModelProperty(name = "TENANTID", value = "", required = true, example = "")
    @JsonProperty("TENANTID")
    private String tenantId;


    @Column(name = "`GROUP_TYPE_NAME`")
    @ApiModelProperty(name = "GROUP_TYPE_NAME", value = "", required = true, example = "")
    @JsonProperty("GROUP_TYPE_NAME")
    private String groupTypeName;

    public String getGroupTypeName() {
        return groupTypeName;
    }

    public void setGroupTypeName(String groupTypeName) {
        this.groupTypeName = groupTypeName;
    }

    /**
     * 获取工作组编号
     *
     * @return GROUP_ID - 工作组编号
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 设置工作组编号
     *
     * @param groupId 工作组编号
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    /**
     * 获取工作组名称
     *
     * @return GROUP_NAME - 工作组名称
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置工作组名称
     *
     * @param groupName 工作组名称
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    /**
     * 获取工作组类型编号
     *
     * @return GROUP_TYPE_ID - 工作组类型编号
     */
    public String getGroupTypeId() {
        return groupTypeId;
    }

    /**
     * 设置工作组类型编号
     *
     * @param groupTypeId 工作组类型编号
     */
    public void setGroupTypeId(String groupTypeId) {
        this.groupTypeId = groupTypeId == null ? null : groupTypeId.trim();
    }

    /**
     * 获取节点编号
     *
     * @return GROUP_CODE - 节点编号
     */
    public String getGroupCode() {
        return groupCode;
    }

    /**
     * 设置节点编号
     *
     * @param groupCode 节点编号
     */
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode == null ? null : groupCode.trim();
    }

    /**
     * 获取父节点编号
     *
     * @return SUPER_GROUP_CODE - 父节点编号
     */
    public String getSuperGroupCode() {
        return superGroupCode;
    }

    /**
     * 设置父节点编号
     *
     * @param superGroupCode 父节点编号
     */
    public void setSuperGroupCode(String superGroupCode) {
        this.superGroupCode = superGroupCode == null ? null : superGroupCode.trim();
    }

    /**
     * 获取工作组描述
     *
     * @return GROUP_DESC - 工作组描述
     */
    public String getGroupDesc() {
        return groupDesc;
    }

    /**
     * 设置工作组描述
     *
     * @param groupDesc 工作组描述
     */
    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc == null ? null : groupDesc.trim();
    }

    /**
     * 获取省份ID
     *
     * @return PROV_ID - 省份ID
     */
    public String getProvId() {
        return provId;
    }

    /**
     * 设置省份ID
     *
     * @param provId 省份ID
     */
    public void setProvId(String provId) {
        this.provId = provId == null ? null : provId.trim();
    }

    /**
     * 获取城市ID
     *
     * @return CITY_ID - 城市ID
     */
    public String getCityId() {
        return cityId;
    }

    /**
     * 设置城市ID
     *
     * @param cityId 城市ID
     */
    public void setCityId(String cityId) {
        this.cityId = cityId == null ? null : cityId.trim();
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
