package com.ai.sam.domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`t_sam_password`")
@ApiModel
public class TSamPassword {
    /**
     * 人员编号。
     */
    @Id
    @Column(name = "`STAFF_ID`")
    @ApiModelProperty(name = "STAFFID", value = "", required = true, example = "")
    @JsonProperty("STAFFID")
    private String staffId;

    /**
     * 加密后的密码。
     */
    @Column(name = "`PASSWORD`")
    @ApiModelProperty(name = "PASSWORD", value = "", required = true, example = "")
    @JsonProperty("PASSWORD")
    private String password;

    /**
     * salt
     */
    @Column(name = "`SALT`")
    @ApiModelProperty(name = "SALT", value = "", required = true, example = "")
    @JsonProperty("SALT")
    private String salt;

    /**
     * 密码起始时间。格式：YYYY
     */
    @Column(name = "`BEGIN_DATE`")
    @ApiModelProperty(name = "BEGINDATE", value = "", required = true, example = "")
    @JsonProperty("BEGINDATE")
    private Date beginDate;

    /**
     * 密码有效结束时间。格式：YYYY
     */
    @Column(name = "`END_DATE`")
    @ApiModelProperty(name = "ENDDATE", value = "", required = true, example = "")
    @JsonProperty("ENDDATE")
    private Date endDate;

    /**
     * 最新修改日期。格式：YYYY
     */
    @Column(name = "`UPDATE_DATE`")
    @ApiModelProperty(name = "UPDATEDATE", value = "", required = true, example = "")
    @JsonProperty("UPDATEDATE")
    private Date updateDate;

    /**
     * 获取人员编号。
     *
     * @return STAFF_ID - 人员编号。
     */
    public String getStaffId() {
        return staffId;
    }

    /**
     * 设置人员编号。
     *
     * @param staffId 人员编号。
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId == null ? null : staffId.trim();
    }

    /**
     * 获取加密后的密码。
     *
     * @return PASSWORD - 加密后的密码。
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置加密后的密码。
     *
     * @param password 加密后的密码。
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取salt
     *
     * @return SALT - salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 设置salt
     *
     * @param salt salt
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    /**
     * 获取密码起始时间。格式：YYYY
     *
     * @return BEGIN_DATE - 密码起始时间。格式：YYYY
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * 设置密码起始时间。格式：YYYY
     *
     * @param beginDate 密码起始时间。格式：YYYY
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * 获取密码有效结束时间。格式：YYYY
     *
     * @return END_DATE - 密码有效结束时间。格式：YYYY
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置密码有效结束时间。格式：YYYY
     *
     * @param endDate 密码有效结束时间。格式：YYYY
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 获取最新修改日期。格式：YYYY
     *
     * @return UPDATE_DATE - 最新修改日期。格式：YYYY
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置最新修改日期。格式：YYYY
     *
     * @param updateDate 最新修改日期。格式：YYYY
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
