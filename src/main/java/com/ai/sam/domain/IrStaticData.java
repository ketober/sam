package com.ai.sam.domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Table(name = "`ir_static_data`")
@ApiModel
public class IrStaticData {
    @Column(name = "`CODE_TYPE`")
    @ApiModelProperty(name = "CODETYPE", value = "", required = true, example = "")
    @JsonProperty("CODETYPE")
    private String codeType;

    @Column(name = "`CODE_VALUE`")
    @ApiModelProperty(name = "CODEVALUE", value = "", required = true, example = "")
    @JsonProperty("CODEVALUE")
    private String codeValue;

    @Column(name = "`CODE_NAME`")
    @ApiModelProperty(name = "CODENAME", value = "", required = true, example = "")
    @JsonProperty("CODENAME")
    private String codeName;

    @Column(name = "`CODE_DESC`")
    @ApiModelProperty(name = "CODEDESC", value = "", required = true, example = "")
    @JsonProperty("CODEDESC")
    private String codeDesc;

    @Column(name = "`SORT_ID`")
    @ApiModelProperty(name = "SORTID", value = "", required = true, example = "")
    @JsonProperty("SORTID")
    private Short sortId;

    @Column(name = "`STATE`")
    @ApiModelProperty(name = "STATE", value = "", required = true, example = "")
    @JsonProperty("STATE")
    private String state;

    /**
     * @return CODE_TYPE
     */
    public String getCodeType() {
        return codeType;
    }

    /**
     * @param codeType
     */
    public void setCodeType(String codeType) {
        this.codeType = codeType == null ? null : codeType.trim();
    }

    /**
     * @return CODE_VALUE
     */
    public String getCodeValue() {
        return codeValue;
    }

    /**
     * @param codeValue
     */
    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue == null ? null : codeValue.trim();
    }

    /**
     * @return CODE_NAME
     */
    public String getCodeName() {
        return codeName;
    }

    /**
     * @param codeName
     */
    public void setCodeName(String codeName) {
        this.codeName = codeName == null ? null : codeName.trim();
    }

    /**
     * @return CODE_DESC
     */
    public String getCodeDesc() {
        return codeDesc;
    }

    /**
     * @param codeDesc
     */
    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc == null ? null : codeDesc.trim();
    }

    /**
     * @return SORT_ID
     */
    public Short getSortId() {
        return sortId;
    }

    /**
     * @param sortId
     */
    public void setSortId(Short sortId) {
        this.sortId = sortId;
    }

    /**
     * @return STATE
     */
    public String getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}
