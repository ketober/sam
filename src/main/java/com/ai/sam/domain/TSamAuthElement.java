package com.ai.sam.domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Table(name = "`t_sam_auth_element`")
@ApiModel
public class TSamAuthElement {
    /**
     * 权限编号，唯一标识。
     */
    @Id
    @Column(name = "`ELEMENT_ID`")
    @ApiModelProperty(name = "ELEMENTID", value = "", required = true, example = "")
    @JsonProperty("ELEMENTID")
    private String elementId;

    /**
     * 权限名称，指权限用于展示的名称，在权限树上展示。
     */
    @Column(name = "`ELEMENT_NAME`")
    @ApiModelProperty(name = "ELEMENTNAME", value = "", required = true, example = "")
    @JsonProperty("ELEMENTNAME")
    private String elementName;

    /**
     * 权限所属模块的编号。
     */
    @Column(name = "`MODULE_ID`")
    @ApiModelProperty(name = "MODULEID", value = "", required = true, example = "")
    @JsonProperty("MODULEID")
    private String moduleId;

    /**
     * 权限编码，与supercode一起定位权限在权限树上的位置，采用3位一级的编码方式。
     */
    @Column(name = "`ELEMENT_CODE`")
    @ApiModelProperty(name = "ELEMENTCODE", value = "", required = true, example = "")
    @JsonProperty("ELEMENTCODE")
    private String elementCode;

    /**
     * 父级权限编码，与authcode一起定位权限在权限树上的位置，采用3位一级的编码方式。
     */
    @Column(name = "`SUPER_ELEMENT_CODE`")
    @ApiModelProperty(name = "SUPERELEMENTCODE", value = "", required = true, example = "")
    @JsonProperty("SUPERELEMENTCODE")
    private String superElementCode;

    /**
     * 菜单项标记。0：功能，1：菜单。
     */
    @Column(name = "`MENU_FLAG`")
    @ApiModelProperty(name = "MENUFLAG", value = "", required = true, example = "")
    @JsonProperty("MENUFLAG")
    private String menuFlag;

    /**
     * 权限的描述信息。
     */
    @Column(name = "`DESCRIPTION`")
    @ApiModelProperty(name = "DESCRIPTION", value = "", required = true, example = "")
    @JsonProperty("DESCRIPTION")
    private String description;

    /**
     * 菜单或按钮对应的url
     */
    @Column(name = "`ELEMENT_URL`")
    @ApiModelProperty(name = "ELEMENTURL", value = "", required = true, example = "")
    @JsonProperty("ELEMENTURL")
    private String elementUrl;

    /**
     * 获取权限编号，唯一标识。
     *
     * @return ELEMENT_ID - 权限编号，唯一标识。
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置权限编号，唯一标识。
     *
     * @param elementId 权限编号，唯一标识。
     */
    public void setElementId(String elementId) {
        this.elementId = elementId == null ? null : elementId.trim();
    }

    /**
     * 获取权限名称，指权限用于展示的名称，在权限树上展示。
     *
     * @return ELEMENT_NAME - 权限名称，指权限用于展示的名称，在权限树上展示。
     */
    public String getElementName() {
        return elementName;
    }

    /**
     * 设置权限名称，指权限用于展示的名称，在权限树上展示。
     *
     * @param elementName 权限名称，指权限用于展示的名称，在权限树上展示。
     */
    public void setElementName(String elementName) {
        this.elementName = elementName == null ? null : elementName.trim();
    }

    /**
     * 获取权限所属模块的编号。
     *
     * @return MODULE_ID - 权限所属模块的编号。
     */
    public String getModuleId() {
        return moduleId;
    }

    /**
     * 设置权限所属模块的编号。
     *
     * @param moduleId 权限所属模块的编号。
     */
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId == null ? null : moduleId.trim();
    }

    /**
     * 获取权限编码，与supercode一起定位权限在权限树上的位置，采用3位一级的编码方式。
     *
     * @return ELEMENT_CODE - 权限编码，与supercode一起定位权限在权限树上的位置，采用3位一级的编码方式。
     */
    public String getElementCode() {
        return elementCode;
    }

    /**
     * 设置权限编码，与supercode一起定位权限在权限树上的位置，采用3位一级的编码方式。
     *
     * @param elementCode 权限编码，与supercode一起定位权限在权限树上的位置，采用3位一级的编码方式。
     */
    public void setElementCode(String elementCode) {
        this.elementCode = elementCode == null ? null : elementCode.trim();
    }

    /**
     * 获取父级权限编码，与authcode一起定位权限在权限树上的位置，采用3位一级的编码方式。
     *
     * @return SUPER_ELEMENT_CODE - 父级权限编码，与authcode一起定位权限在权限树上的位置，采用3位一级的编码方式。
     */
    public String getSuperElementCode() {
        return superElementCode;
    }

    /**
     * 设置父级权限编码，与authcode一起定位权限在权限树上的位置，采用3位一级的编码方式。
     *
     * @param superElementCode 父级权限编码，与authcode一起定位权限在权限树上的位置，采用3位一级的编码方式。
     */
    public void setSuperElementCode(String superElementCode) {
        this.superElementCode = superElementCode == null ? null : superElementCode.trim();
    }

    /**
     * 获取菜单项标记。0：功能，1：菜单。
     *
     * @return MENU_FLAG - 菜单项标记。0：功能，1：菜单。
     */
    public String getMenuFlag() {
        return menuFlag;
    }

    /**
     * 设置菜单项标记。0：功能，1：菜单。
     *
     * @param menuFlag 菜单项标记。0：功能，1：菜单。
     */
    public void setMenuFlag(String menuFlag) {
        this.menuFlag = menuFlag == null ? null : menuFlag.trim();
    }

    /**
     * 获取权限的描述信息。
     *
     * @return DESCRIPTION - 权限的描述信息。
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置权限的描述信息。
     *
     * @param description 权限的描述信息。
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取菜单或按钮对应的url
     *
     * @return ELEMENT_URL - 菜单或按钮对应的url
     */
    public String getElementUrl() {
        return elementUrl;
    }

    /**
     * 设置菜单或按钮对应的url
     *
     * @param elementUrl 菜单或按钮对应的url
     */
    public void setElementUrl(String elementUrl) {
        this.elementUrl = elementUrl == null ? null : elementUrl.trim();
    }
}
