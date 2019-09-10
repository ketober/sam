package com.ai.sam.domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Table(name = "`t_sam_post_info`")
@ApiModel
public class TSamPostInfo {
    /**
     * 岗位编号
     */
    @Id
    @Column(name = "`POST_ID`")
    @ApiModelProperty(name = "POSTID", value = "", required = true, example = "")
    @JsonProperty("POSTID")
    private String postId;

    /**
     * 岗位名称
     */
    @Column(name = "`POST_NAME`")
    @ApiModelProperty(name = "POSTNAME", value = "", required = true, example = "")
    @JsonProperty("POSTNAME")
    private String postName;

    /**
     * 岗位编码
     */
    @Column(name = "`POST_CODE`")
    @ApiModelProperty(name = "POSTCODE", value = "", required = true, example = "")
    @JsonProperty("POSTCODE")
    private String postCode;

    /**
     * 父岗位编码
     */
    @Column(name = "`SUPER_POST_CODE`")
    @ApiModelProperty(name = "SUPERPOSTCODE", value = "", required = true, example = "")
    @JsonProperty("SUPERPOSTCODE")
    private String superPostCode;

    /**
     * 岗位状态,取值含义：0：废弃 1：在用
     */
    @Column(name = "`POST_STATE`")
    @ApiModelProperty(name = "POSTSTATE", value = "", required = true, example = "")
    @JsonProperty("POSTSTATE")
    private String postState;

    /**
     * 岗位描述
     */
    @Column(name = "`POST_DESC`")
    @ApiModelProperty(name = "POSTDESC", value = "", required = true, example = "")
    @JsonProperty("POSTDESC")
    private String postDesc;

    /**
     * 岗位说明书编号
     */
    @Column(name = "`POST_DESC_ID`")
    @ApiModelProperty(name = "POSTDESCID", value = "", required = true, example = "")
    @JsonProperty("POSTDESCID")
    private String postDescId;

    /**
     * 职级编号
     */
    @Column(name = "`DUTY_LEVEL_ID`")
    @ApiModelProperty(name = "DUTYLEVELID", value = "", required = true, example = "")
    @JsonProperty("DUTYLEVELID")
    private String dutyLevelId;

    /**
     * 租户ID
     */
    @Column(name = "`TENANT_ID`")
    @ApiModelProperty(name = "TENANTID", value = "", required = true, example = "")
    @JsonProperty("TENANTID")
    private String tenantId;

    /**
     * 获取岗位编号
     *
     * @return POST_ID - 岗位编号
     */
    public String getPostId() {
        return postId;
    }

    /**
     * 设置岗位编号
     *
     * @param postId 岗位编号
     */
    public void setPostId(String postId) {
        this.postId = postId == null ? null : postId.trim();
    }

    /**
     * 获取岗位名称
     *
     * @return POST_NAME - 岗位名称
     */
    public String getPostName() {
        return postName;
    }

    /**
     * 设置岗位名称
     *
     * @param postName 岗位名称
     */
    public void setPostName(String postName) {
        this.postName = postName == null ? null : postName.trim();
    }

    /**
     * 获取岗位编码
     *
     * @return POST_CODE - 岗位编码
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * 设置岗位编码
     *
     * @param postCode 岗位编码
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    /**
     * 获取父岗位编码
     *
     * @return SUPER_POST_CODE - 父岗位编码
     */
    public String getSuperPostCode() {
        return superPostCode;
    }

    /**
     * 设置父岗位编码
     *
     * @param superPostCode 父岗位编码
     */
    public void setSuperPostCode(String superPostCode) {
        this.superPostCode = superPostCode == null ? null : superPostCode.trim();
    }

    /**
     * 获取岗位状态,取值含义：0：废弃 1：在用
     *
     * @return POST_STATE - 岗位状态,取值含义：0：废弃 1：在用
     */
    public String getPostState() {
        return postState;
    }

    /**
     * 设置岗位状态,取值含义：0：废弃 1：在用
     *
     * @param postState 岗位状态,取值含义：0：废弃 1：在用
     */
    public void setPostState(String postState) {
        this.postState = postState == null ? null : postState.trim();
    }

    /**
     * 获取岗位描述
     *
     * @return POST_DESC - 岗位描述
     */
    public String getPostDesc() {
        return postDesc;
    }

    /**
     * 设置岗位描述
     *
     * @param postDesc 岗位描述
     */
    public void setPostDesc(String postDesc) {
        this.postDesc = postDesc == null ? null : postDesc.trim();
    }

    /**
     * 获取岗位说明书编号
     *
     * @return POST_DESC_ID - 岗位说明书编号
     */
    public String getPostDescId() {
        return postDescId;
    }

    /**
     * 设置岗位说明书编号
     *
     * @param postDescId 岗位说明书编号
     */
    public void setPostDescId(String postDescId) {
        this.postDescId = postDescId == null ? null : postDescId.trim();
    }

    /**
     * 获取职级编号
     *
     * @return DUTY_LEVEL_ID - 职级编号
     */
    public String getDutyLevelId() {
        return dutyLevelId;
    }

    /**
     * 设置职级编号
     *
     * @param dutyLevelId 职级编号
     */
    public void setDutyLevelId(String dutyLevelId) {
        this.dutyLevelId = dutyLevelId == null ? null : dutyLevelId.trim();
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
