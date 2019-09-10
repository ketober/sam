package com.ai.sam.domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Table(name = "`t_sam_platform_rel`")
@ApiModel
public class TSamPlatformRel {
    /**
     * 关联ID
     */
    @Id
    @Column(name = "`REL_ID`")
    @ApiModelProperty(name = "RELID", value = "", required = true, example = "")
    @JsonProperty("RELID")
    private String relId;

    /**
     * 业务工号

     */
    @Column(name = "`STAFF_ID`")
    @ApiModelProperty(name = "STAFFID", value = "", required = true, example = "")
    @JsonProperty("STAFFID")
    private String staffId;

    /**
     * 平台工号
     */
    @Column(name = "`PLATFORM_ID`")
    @ApiModelProperty(name = "PLATFORMID", value = "", required = true, example = "")
    @JsonProperty("PLATFORMID")
    private String platformId;

    /**
     * 获取关联ID
     *
     * @return REL_ID - 关联ID
     */
    public String getRelId() {
        return relId;
    }

    /**
     * 设置关联ID
     *
     * @param relId 关联ID
     */
    public void setRelId(String relId) {
        this.relId = relId == null ? null : relId.trim();
    }

    /**
     * 获取业务工号

     *
     * @return STAFF_ID - 业务工号

     */
    public String getStaffId() {
        return staffId;
    }

    /**
     * 设置业务工号

     *
     * @param staffId 业务工号

     */
    public void setStaffId(String staffId) {
        this.staffId = staffId == null ? null : staffId.trim();
    }

    /**
     * 获取平台工号
     *
     * @return PLATFORM_ID - 平台工号
     */
    public String getPlatformId() {
        return platformId;
    }

    /**
     * 设置平台工号
     *
     * @param platformId 平台工号
     */
    public void setPlatformId(String platformId) {
        this.platformId = platformId == null ? null : platformId.trim();
    }
}
