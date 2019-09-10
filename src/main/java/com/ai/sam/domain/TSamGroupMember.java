package com.ai.sam.domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Table(name = "`t_sam_group_member`")
@ApiModel
public class TSamGroupMember {
    /**
     * 双中心ID
     */
    @Id
    @Column(name = "`MEMBER_ID`")
    @ApiModelProperty(name = "MEMBERID", value = "", required = true, example = "")
    @JsonProperty("MEMBERID")
    private String memberId;

    /**
     * 工作组编号
     */
    @Column(name = "`GROUP_ID`")
    @ApiModelProperty(name = "GROUPID", value = "", required = true, example = "")
    @JsonProperty("GROUPID")
    private String groupId;

    /**
     * 人员帐号
     */
    @Column(name = "`STAFF_ID`")
    @ApiModelProperty(name = "STAFFID", value = "", required = true, example = "")
    @JsonProperty("STAFFID")
    private String staffId;

    /**
     * 是否是负责人(取值含义：1：是 0：否 )
     */
    @Column(name = "`ISPRINCIPAL`")
    @ApiModelProperty(name = "ISPRINCIPAL", value = "", required = true, example = "")
    @JsonProperty("ISPRINCIPAL")
    private String isprincipal;

    /**
     * 获取双中心ID
     *
     * @return MEMBER_ID - 双中心ID
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * 设置双中心ID
     *
     * @param memberId 双中心ID
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
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
     * 获取人员帐号
     *
     * @return STAFF_ID - 人员帐号
     */
    public String getStaffId() {
        return staffId;
    }

    /**
     * 设置人员帐号
     *
     * @param staffId 人员帐号
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId == null ? null : staffId.trim();
    }

    /**
     * 获取是否是负责人(取值含义：1：是 0：否 )
     *
     * @return ISPRINCIPAL - 是否是负责人(取值含义：1：是 0：否 )
     */
    public String getIsprincipal() {
        return isprincipal;
    }

    /**
     * 设置是否是负责人(取值含义：1：是 0：否 )
     *
     * @param isprincipal 是否是负责人(取值含义：1：是 0：否 )
     */
    public void setIsprincipal(String isprincipal) {
        this.isprincipal = isprincipal == null ? null : isprincipal.trim();
    }
}
