package com.ai.sam.domain;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Table(name = "`t_sam_menu`")
@ApiModel
public class TSamMenu {
    /**
     * 菜单项编号。
     */
    @Id
    @Column(name = "`MENU_ID`")
    @ApiModelProperty(name = "MENUID", value = "", required = true, example = "")
    @JsonProperty("MENUID")
    private String menuId;

    /**
     * 父菜单项编号。
     */
    @Column(name = "`PARENT_ID`")
    @ApiModelProperty(name = "PARENTID", value = "", required = true, example = "")
    @JsonProperty("PARENTID")
    private String parentId;

    /**
     * 模块编号。
     */
    @Column(name = "`MODULE_ID`")
    @ApiModelProperty(name = "MODULEID", value = "", required = true, example = "")
    @JsonProperty("MODULEID")
    private String moduleId;

    /**
     * 菜单项图片地址。
     */
    @Column(name = "`IMAGE_URL`")
    @ApiModelProperty(name = "IMAGEURL", value = "", required = true, example = "")
    @JsonProperty("IMAGEURL")
    private String imageUrl;

    /**
     * 菜单项名称。
     */
    @Column(name = "`MENU_NAME`")
    @ApiModelProperty(name = "MENUNAME", value = "", required = true, example = "")
    @JsonProperty("MENUNAME")
    private String menuName;

    /**
     * 菜单项说明。
     */
    @Column(name = "`MENU_DESCRIPTION`")
    @ApiModelProperty(name = "MENUDESCRIPTION", value = "", required = true, example = "")
    @JsonProperty("MENUDESCRIPTION")
    private String menuDescription;

    /**
     * 菜单项链接。
     */
    @Column(name = "`MENU_URL`")
    @ApiModelProperty(name = "MENUURL", value = "", required = true, example = "")
    @JsonProperty("MENUURL")
    private String menuUrl;

    /**
     * 显示顺序。
     */
    @Column(name = "`DISPLAY_NO`")
    @ApiModelProperty(name = "DISPLAYNO", value = "", required = true, example = "")
    @JsonProperty("DISPLAYNO")
    private Short displayNo;

    /**
     * 打开方式。取值含义：Y：新窗口 N：主页面 
     */
    @Column(name = "`OPEN_MODULE`")
    @ApiModelProperty(name = "OPENMODULE", value = "", required = true, example = "")
    @JsonProperty("OPENMODULE")
    private String openModule;

    /**
     * 点击次数
     */
    @Column(name = "`CLICK_TOTAL`")
    @ApiModelProperty(name = "CLICKTOTAL", value = "", required = true, example = "")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("CLICKTOTAL")
    private Long clickTotal;

    /**
     * 菜单首字母缩写
     */
    @Column(name = "`ABBREVIATION`")
    @ApiModelProperty(name = "ABBREVIATION", value = "", required = true, example = "")
    @JsonProperty("ABBREVIATION")
    private String abbreviation;

    /**
     * 嵌套系统类型
     */
    @Column(name = "`NESTED_SYS_TYPE`")
    @ApiModelProperty(name = "NESTEDSYSTYPE", value = "", required = true, example = "")
    @JsonProperty("NESTEDSYSTYPE")
    private String nestedSysType;

    /**
     * 页面展示宽
     */
    @Column(name = "`MENU_WIDE`")
    @ApiModelProperty(name = "MENUWIDE", value = "", required = true, example = "")
    @JsonProperty("MENUWIDE")
    private String menuWide;

    /**
     * 页面展示高
     */
    @Column(name = "`MENU_HIGH`")
    @ApiModelProperty(name = "MENUHIGH", value = "", required = true, example = "")
    @JsonProperty("MENUHIGH")
    private String menuHigh;

    @Column(name = "`TENANT_ID`")
    @ApiModelProperty(name = "TENANTID", value = "", required = true, example = "")
    @JsonProperty("TENANTID")
    private String tenantId;

    /**
     * 获取菜单项编号。
     *
     * @return MENU_ID - 菜单项编号。
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * 设置菜单项编号。
     *
     * @param menuId 菜单项编号。
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    /**
     * 获取父菜单项编号。
     *
     * @return PARENT_ID - 父菜单项编号。
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置父菜单项编号。
     *
     * @param parentId 父菜单项编号。
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
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
     * 获取菜单项图片地址。
     *
     * @return IMAGE_URL - 菜单项图片地址。
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 设置菜单项图片地址。
     *
     * @param imageUrl 菜单项图片地址。
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    /**
     * 获取菜单项名称。
     *
     * @return MENU_NAME - 菜单项名称。
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置菜单项名称。
     *
     * @param menuName 菜单项名称。
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    /**
     * 获取菜单项说明。
     *
     * @return MENU_DESCRIPTION - 菜单项说明。
     */
    public String getMenuDescription() {
        return menuDescription;
    }

    /**
     * 设置菜单项说明。
     *
     * @param menuDescription 菜单项说明。
     */
    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription == null ? null : menuDescription.trim();
    }

    /**
     * 获取菜单项链接。
     *
     * @return MENU_URL - 菜单项链接。
     */
    public String getMenuUrl() {
        return menuUrl;
    }

    /**
     * 设置菜单项链接。
     *
     * @param menuUrl 菜单项链接。
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    /**
     * 获取显示顺序。
     *
     * @return DISPLAY_NO - 显示顺序。
     */
    public Short getDisplayNo() {
        return displayNo;
    }

    /**
     * 设置显示顺序。
     *
     * @param displayNo 显示顺序。
     */
    public void setDisplayNo(Short displayNo) {
        this.displayNo = displayNo;
    }

    /**
     * 获取打开方式。取值含义：Y：新窗口 N：主页面 
     *
     * @return OPEN_MODULE - 打开方式。取值含义：Y：新窗口 N：主页面 
     */
    public String getOpenModule() {
        return openModule;
    }

    /**
     * 设置打开方式。取值含义：Y：新窗口 N：主页面 
     *
     * @param openModule 打开方式。取值含义：Y：新窗口 N：主页面 
     */
    public void setOpenModule(String openModule) {
        this.openModule = openModule == null ? null : openModule.trim();
    }

    /**
     * 获取点击次数
     *
     * @return CLICK_TOTAL - 点击次数
     */
    public Long getClickTotal() {
        return clickTotal;
    }

    /**
     * 设置点击次数
     *
     * @param clickTotal 点击次数
     */
    public void setClickTotal(Long clickTotal) {
        this.clickTotal = clickTotal;
    }

    /**
     * 获取菜单首字母缩写
     *
     * @return ABBREVIATION - 菜单首字母缩写
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * 设置菜单首字母缩写
     *
     * @param abbreviation 菜单首字母缩写
     */
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation == null ? null : abbreviation.trim();
    }

    /**
     * 获取嵌套系统类型
     *
     * @return NESTED_SYS_TYPE - 嵌套系统类型
     */
    public String getNestedSysType() {
        return nestedSysType;
    }

    /**
     * 设置嵌套系统类型
     *
     * @param nestedSysType 嵌套系统类型
     */
    public void setNestedSysType(String nestedSysType) {
        this.nestedSysType = nestedSysType == null ? null : nestedSysType.trim();
    }

    /**
     * 获取页面展示宽
     *
     * @return MENU_WIDE - 页面展示宽
     */
    public String getMenuWide() {
        return menuWide;
    }

    /**
     * 设置页面展示宽
     *
     * @param menuWide 页面展示宽
     */
    public void setMenuWide(String menuWide) {
        this.menuWide = menuWide == null ? null : menuWide.trim();
    }

    /**
     * 获取页面展示高
     *
     * @return MENU_HIGH - 页面展示高
     */
    public String getMenuHigh() {
        return menuHigh;
    }

    /**
     * 设置页面展示高
     *
     * @param menuHigh 页面展示高
     */
    public void setMenuHigh(String menuHigh) {
        this.menuHigh = menuHigh == null ? null : menuHigh.trim();
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

    @Override
    public String toString() {
        return "TSamMenu{" +
                "menuId='" + menuId + '\'' +
                ", parentId='" + parentId + '\'' +
                ", moduleId='" + moduleId + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", menuName='" + menuName + '\'' +
                ", menuDescription='" + menuDescription + '\'' +
                ", menuUrl='" + menuUrl + '\'' +
                ", displayNo=" + displayNo +
                ", openModule='" + openModule + '\'' +
                ", clickTotal=" + clickTotal +
                ", abbreviation='" + abbreviation + '\'' +
                ", nestedSysType='" + nestedSysType + '\'' +
                ", menuWide='" + menuWide + '\'' +
                ", menuHigh='" + menuHigh + '\'' +
                ", tenantId='" + tenantId + '\'' +
                '}';
    }
}
