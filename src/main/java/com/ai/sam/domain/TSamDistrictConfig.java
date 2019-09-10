package com.ai.sam.domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`t_sam_district_config`")
@ApiModel
public class TSamDistrictConfig {
    /**
     * 地区编号
     */
    @Id
    @Column(name = "`REGN_ID`")
    @ApiModelProperty(name = "REGNID", value = "", required = true, example = "")
    @JsonProperty("REGNID")
    private String regnId;

    /**
     * 地区名称
     */
    @Column(name = "`REGN_NM`")
    @ApiModelProperty(name = "REGNNM", value = "", required = true, example = "")
    @JsonProperty("REGNNM")
    private String regnNm;

    /**
     * 区号
     */
    @Column(name = "`AREA_CODE`")
    @ApiModelProperty(name = "AREACODE", value = "", required = true, example = "")
    @JsonProperty("AREACODE")
    private String areaCode;

    /**
     * 上级地区编号
     */
    @Column(name = "`SUPR_REGN_ID`")
    @ApiModelProperty(name = "SUPRREGNID", value = "", required = true, example = "")
    @JsonProperty("SUPRREGNID")
    private String suprRegnId;

    /**
     * 排列序号
     */
    @Column(name = "`ARGE_SEQNO`")
    @ApiModelProperty(name = "ARGESEQNO", value = "", required = true, example = "")
    @JsonProperty("ARGESEQNO")
    private Integer argeSeqno;

    /**
     * 创建时间
     */
    @Column(name = "`CRT_TIME`")
    @ApiModelProperty(name = "CRTTIME", value = "", required = true, example = "")
    @JsonProperty("CRTTIME")
    private Date crtTime;

    /**
     * 修改时间
     */
    @Column(name = "`MODF_TIME`")
    @ApiModelProperty(name = "MODFTIME", value = "", required = true, example = "")
    @JsonProperty("MODFTIME")
    private Date modfTime;

    /**
     * 操作人员ID
     */
    @Column(name = "`OP_PRSN_ID`")
    @ApiModelProperty(name = "OPPRSNID", value = "", required = true, example = "")
    @JsonProperty("OPPRSNID")
    private String opPrsnId;

    /**
     * 层级序号
     */
    @Column(name = "`HRCY_SEQNO`")
    @ApiModelProperty(name = "HRCYSEQNO", value = "", required = true, example = "")
    @JsonProperty("HRCYSEQNO")
    private Short hrcySeqno;

    /**
     * 字母简写编码
     */
    @Column(name = "`ALPH_SHTN_CODE`")
    @ApiModelProperty(name = "ALPHSHTNCODE", value = "", required = true, example = "")
    @JsonProperty("ALPHSHTNCODE")
    private String alphShtnCode;

    /**
     * 备注
     */
    @Column(name = "`RMK`")
    @ApiModelProperty(name = "RMK", value = "", required = true, example = "")
    @JsonProperty("RMK")
    private String rmk;

    /**
     * 租户ID
     */
    @Column(name = "`TENANT_ID`")
    @ApiModelProperty(name = "TENANTID", value = "", required = true, example = "")
    @JsonProperty("TENANTID")
    private String tenantId;

    /**
     * 获取地区编号
     *
     * @return REGN_ID - 地区编号
     */
    public String getRegnId() {
        return regnId;
    }

    /**
     * 设置地区编号
     *
     * @param regnId 地区编号
     */
    public void setRegnId(String regnId) {
        this.regnId = regnId == null ? null : regnId.trim();
    }

    /**
     * 获取地区名称
     *
     * @return REGN_NM - 地区名称
     */
    public String getRegnNm() {
        return regnNm;
    }

    /**
     * 设置地区名称
     *
     * @param regnNm 地区名称
     */
    public void setRegnNm(String regnNm) {
        this.regnNm = regnNm == null ? null : regnNm.trim();
    }

    /**
     * 获取区号
     *
     * @return AREA_CODE - 区号
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 设置区号
     *
     * @param areaCode 区号
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    /**
     * 获取上级地区编号
     *
     * @return SUPR_REGN_ID - 上级地区编号
     */
    public String getSuprRegnId() {
        return suprRegnId;
    }

    /**
     * 设置上级地区编号
     *
     * @param suprRegnId 上级地区编号
     */
    public void setSuprRegnId(String suprRegnId) {
        this.suprRegnId = suprRegnId == null ? null : suprRegnId.trim();
    }

    /**
     * 获取排列序号
     *
     * @return ARGE_SEQNO - 排列序号
     */
    public Integer getArgeSeqno() {
        return argeSeqno;
    }

    /**
     * 设置排列序号
     *
     * @param argeSeqno 排列序号
     */
    public void setArgeSeqno(Integer argeSeqno) {
        this.argeSeqno = argeSeqno;
    }

    /**
     * 获取创建时间
     *
     * @return CRT_TIME - 创建时间
     */
    public Date getCrtTime() {
        return crtTime;
    }

    /**
     * 设置创建时间
     *
     * @param crtTime 创建时间
     */
    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    /**
     * 获取修改时间
     *
     * @return MODF_TIME - 修改时间
     */
    public Date getModfTime() {
        return modfTime;
    }

    /**
     * 设置修改时间
     *
     * @param modfTime 修改时间
     */
    public void setModfTime(Date modfTime) {
        this.modfTime = modfTime;
    }

    /**
     * 获取操作人员ID
     *
     * @return OP_PRSN_ID - 操作人员ID
     */
    public String getOpPrsnId() {
        return opPrsnId;
    }

    /**
     * 设置操作人员ID
     *
     * @param opPrsnId 操作人员ID
     */
    public void setOpPrsnId(String opPrsnId) {
        this.opPrsnId = opPrsnId == null ? null : opPrsnId.trim();
    }

    /**
     * 获取层级序号
     *
     * @return HRCY_SEQNO - 层级序号
     */
    public Short getHrcySeqno() {
        return hrcySeqno;
    }

    /**
     * 设置层级序号
     *
     * @param hrcySeqno 层级序号
     */
    public void setHrcySeqno(Short hrcySeqno) {
        this.hrcySeqno = hrcySeqno;
    }

    /**
     * 获取字母简写编码
     *
     * @return ALPH_SHTN_CODE - 字母简写编码
     */
    public String getAlphShtnCode() {
        return alphShtnCode;
    }

    /**
     * 设置字母简写编码
     *
     * @param alphShtnCode 字母简写编码
     */
    public void setAlphShtnCode(String alphShtnCode) {
        this.alphShtnCode = alphShtnCode == null ? null : alphShtnCode.trim();
    }

    /**
     * 获取备注
     *
     * @return RMK - 备注
     */
    public String getRmk() {
        return rmk;
    }

    /**
     * 设置备注
     *
     * @param rmk 备注
     */
    public void setRmk(String rmk) {
        this.rmk = rmk == null ? null : rmk.trim();
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
