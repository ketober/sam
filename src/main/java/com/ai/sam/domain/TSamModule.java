package com.ai.sam.domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Table(name = "`t_sam_module`")
@ApiModel
public class TSamModule {
    /**
     * 模块编号
     */
    @Id
    @Column(name = "`MODULE_ID`")
    @ApiModelProperty(name = "MODULEID", value = "", required = true, example = "")
    @JsonProperty("MODULEID")
    private String moduleId;

    /**
     * 父级模块编号
     */
    @Column(name = "`PARENT_ID`")
    @ApiModelProperty(name = "PARENTID", value = "", required = true, example = "")
    @JsonProperty("PARENTID")
    private String parentId;

    /**
     * 模块名称
     */
    @Column(name = "`MODULE_NAME`")
    @ApiModelProperty(name = "MODULENAME", value = "", required = true, example = "")
    @JsonProperty("MODULENAME")
    private String moduleName;

    /**
     * 显示顺序
     */
    @Column(name = "`DISPLAY_NO`")
    @ApiModelProperty(name = "DISPLAYNO", value = "", required = true, example = "")
    @JsonProperty("DISPLAYNO")
    private Short displayNo;

    @Column(name = "`TENANT_ID`")
    @ApiModelProperty(name = "TENANTID", value = "", required = true, example = "")
    @JsonProperty("TENANTID")
    private String tenantId;

    /**
     * 获取模块编号
     *
     * @return MODULE_ID - 模块编号
     */
    public String getModuleId() {
        return moduleId;
    }

    /**
     * 设置模块编号
     *
     * @param moduleId 模块编号
     */
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId == null ? null : moduleId.trim();
    }

    /**
     * 获取父级模块编号
     *
     * @return PARENT_ID - 父级模块编号
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置父级模块编号
     *
     * @param parentId 父级模块编号
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     * 获取模块名称
     *
     * @return MODULE_NAME - 模块名称
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * 设置模块名称
     *
     * @param moduleName 模块名称
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    /**
     * 获取显示顺序
     *
     * @return DISPLAY_NO - 显示顺序
     */
    public Short getDisplayNo() {
        return displayNo;
    }

    /**
     * 设置显示顺序
     *
     * @param displayNo 显示顺序
     */
    public void setDisplayNo(Short displayNo) {
        this.displayNo = displayNo;
    }

    /**
     * @return TENANT_ID
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * @param tenantId
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }
}
