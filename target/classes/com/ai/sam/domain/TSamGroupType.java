package com.ai.sam.domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Table(name = "`t_sam_group_type`")
@ApiModel
public class TSamGroupType {
    /**
     * 工作组类别编号。
     */
    @Id
    @Column(name = "`GROUP_TYPE_ID`")
    @ApiModelProperty(name = "GROUPTYPEID", value = "", required = true, example = "")
    @JsonProperty("GROUPTYPEID")
    private String groupTypeId;

    /**
     * 工作组类别名称。
     */
    @Column(name = "`GROUP_TYPE_NAME`")
    @ApiModelProperty(name = "GROUPTYPENAME", value = "", required = true, example = "")
    @JsonProperty("GROUPTYPENAME")
    private String groupTypeName;

    /**
     * 工作组类别描述。
     */
    @Column(name = "`GROUP_TYPE_DESC`")
    @ApiModelProperty(name = "GROUPTYPEDESC", value = "", required = true, example = "")
    @JsonProperty("GROUPTYPEDESC")
    private String groupTypeDesc;

    /**
     * 系统类型
     */
    @Column(name = "`SYS_NO`")
    @ApiModelProperty(name = "SYSNO", value = "", required = true, example = "")
    @JsonProperty("SYSNO")
    private String sysNo;

    /**
     * 获取工作组类别编号。
     *
     * @return GROUP_TYPE_ID - 工作组类别编号。
     */
    public String getGroupTypeId() {
        return groupTypeId;
    }

    /**
     * 设置工作组类别编号。
     *
     * @param groupTypeId 工作组类别编号。
     */
    public void setGroupTypeId(String groupTypeId) {
        this.groupTypeId = groupTypeId == null ? null : groupTypeId.trim();
    }

    /**
     * 获取工作组类别名称。
     *
     * @return GROUP_TYPE_NAME - 工作组类别名称。
     */
    public String getGroupTypeName() {
        return groupTypeName;
    }

    /**
     * 设置工作组类别名称。
     *
     * @param groupTypeName 工作组类别名称。
     */
    public void setGroupTypeName(String groupTypeName) {
        this.groupTypeName = groupTypeName == null ? null : groupTypeName.trim();
    }

    /**
     * 获取工作组类别描述。
     *
     * @return GROUP_TYPE_DESC - 工作组类别描述。
     */
    public String getGroupTypeDesc() {
        return groupTypeDesc;
    }

    /**
     * 设置工作组类别描述。
     *
     * @param groupTypeDesc 工作组类别描述。
     */
    public void setGroupTypeDesc(String groupTypeDesc) {
        this.groupTypeDesc = groupTypeDesc == null ? null : groupTypeDesc.trim();
    }

    /**
     * 获取系统类型
     *
     * @return SYS_NO - 系统类型
     */
    public String getSysNo() {
        return sysNo;
    }

    /**
     * 设置系统类型
     *
     * @param sysNo 系统类型
     */
    public void setSysNo(String sysNo) {
        this.sysNo = sysNo == null ? null : sysNo.trim();
    }
}
