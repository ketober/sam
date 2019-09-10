package com.ai.sam.domain;
import com.ai.sam.common.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "`t_sam_staff_info`")
@ApiModel
public class TSamStaffInfo extends Page {
    /**
     * 人员编号。
     */
    @Id
    @Column(name = "`STAFF_ID`")
    @ApiModelProperty(name = "STAFFID", value = "", required = true, example = "")
    @JsonProperty("STAFFID")
    private String staffId;

    /**
     * 人员姓名。
     */
    @Column(name = "`STAFF_NAME`")
    @ApiModelProperty(name = "STAFFNAME", value = "", required = true, example = "")
    @JsonProperty("STAFFNAME")
    private String staffName;

    /**
     * 人员状态。取值含义：0：离职 1：在职
     */
    @Column(name = "`STAFF_STATE`")
    @ApiModelProperty(name = "STAFFSTATE", value = "", required = true, example = "")
    @JsonProperty("STAFFSTATE")
    private String staffState;

    /**
     * 帐号状态。取值含义：01：正常 02：停用 03：作废 04：锁定 05：解锁 06：失效 07：未启用
     */
    @Column(name = "`STAFF_ID_STATUS`")
    @ApiModelProperty(name = "STAFFIDSTATUS", value = "", required = true, example = "")
    @JsonProperty("STAFFIDSTATUS")
    private String staffIdStatus;

    /**
     * 职级编号。
     */
    @Column(name = "`DLEVEL_ID`")
    @ApiModelProperty(name = "DLEVELID", value = "", required = true, example = "")
    @JsonProperty("DLEVELID")
    private String dlevelId;

    /**
     * 职务。
     */
    @Column(name = "`DUTY_ID`")
    @ApiModelProperty(name = "DUTYID", value = "", required = true, example = "")
    @JsonProperty("DUTYID")
    private String dutyId;

    /**
     * 附加职务。
     */
    @Column(name = "`SECOND_DUTY`")
    @ApiModelProperty(name = "SECONDDUTY", value = "", required = true, example = "")
    @JsonProperty("SECONDDUTY")
    private String secondDuty;

    /**
     * 组织机构编号。
     */
    @Column(name = "`ORGA_ID`")
    @ApiModelProperty(name = "ORGAID", value = "", required = true, example = "")
    @JsonProperty("ORGAID")
    private String orgaId;

    /**
     * 人员岗位。
     */
    @Column(name = "`POST_ID`")
    @ApiModelProperty(name = "POSTID", value = "", required = true, example = "")
    @JsonProperty("POSTID")
    private String postId;

    /**
     * 附加岗位。
     */
    @Column(name = "`SECOND_POST`")
    @ApiModelProperty(name = "SECONDPOST", value = "", required = true, example = "")
    @JsonProperty("SECONDPOST")
    private String secondPost;

    /**
     * 修改时间。
     */
    @Column(name = "`UPDA_TETIME`")
    @ApiModelProperty(name = "UPDATETIME", value = "", required = true, example = "")
    @JsonProperty("UPDATETIME")
    private Date updaTetime;

    /**
     * 入职时间
     */
    @Column(name = "`JOIN_DATE`")
    @ApiModelProperty(name = "JOINDATE", value = "", required = true, example = "")
    @JsonProperty("JOINDATE")
    private Date joinDate;

    /**
     * 移动电话号码，在提醒方式配置 功能中使用
     */
    @Column(name = "`MOBILE_PHONE`")
    @ApiModelProperty(name = "MOBILEPHONE", value = "", required = true, example = "")
    @JsonProperty("MOBILEPHONE")
    private String mobilePhone;

    /**
     * Email地址，在提醒方式配 置功能中使用
     */
    @Column(name = "`EMAIL_ADDRESS`")
    @ApiModelProperty(name = "EMAILADDRESS", value = "", required = true, example = "")
    @JsonProperty("EMAILADDRESS")
    private String emailAddress;

    /**
     * 地市编号
     */
    @Column(name = "`CITY_ID`")
    @ApiModelProperty(name = "CITYID", value = "", required = true, example = "")
    @JsonProperty("CITYID")
    private String cityId;

    /**
     * 省份编号
     */
    @Column(name = "`PROVICE_ID`")
    @ApiModelProperty(name = "PROVICEID", value = "", required = true, example = "")
    @JsonProperty("PROVICEID")
    private String proviceId;

    /**
     * 员工类型
     */
    @Column(name = "`STAFF_TYPE`")
    @ApiModelProperty(name = "STAFFTYPE", value = "", required = true, example = "")
    @JsonProperty("STAFFTYPE")
    private String staffType;

    /**
     * 身份证号码
     */
    @Column(name = "`ID_CARD_NO`")
    @ApiModelProperty(name = "IDCARDNO", value = "", required = true, example = "")
    @JsonProperty("IDCARDNO")
    private String idCardNo;

    /**
     * 渠道ID。渠道定义：融合客服，acmp，众包
     */
    @Column(name = "`CHANNEL_ID`")
    @ApiModelProperty(name = "CHANNELID", value = "", required = true, example = "")
    @JsonProperty("CHANNELID")
    private String channelId;

    /**
     * authen_rule_id
     */
    @Column(name = "`AUTHEN_RULE_ID`")
    @ApiModelProperty(name = "AUTHENRULEID", value = "", required = true, example = "")
    @JsonProperty("AUTHENRULEID")
    private Integer authenRuleId;

    /**
     * 添加时间
     */
    @Column(name = "`INSERT_TIME`")
    @ApiModelProperty(name = "INSERTTIME", value = "", required = true, example = "")
    @JsonProperty("INSERTTIME")
    private Date insertTime;

    /**
     * 员工名称缩写
     */
    @Column(name = "`STAFF_NM_SHORT`")
    @ApiModelProperty(name = "STAFFNMSHORT", value = "", required = true, example = "")
    @JsonProperty("STAFFNMSHORT")
    private String staffNmShort;

    /**
     * 人员渠道类型代码(0自有，1外包，2其他，3劳务派遣，4众包呼入会员，5众包双技能会员)
     */
    @Column(name = "`PRSN_CHNL_TYPE_CD`")
    @ApiModelProperty(name = "PRSNCHNLTYPECD", value = "", required = true, example = "")
    @JsonProperty("PRSNCHNLTYPECD")
    private String prsnChnlTypeCd;

    /**
     * 默认业务类型
     */
    @Column(name = "`DEFAULT_SERVICE_TYPE`")
    @ApiModelProperty(name = "DEFAULTSERVICETYPE", value = "", required = true, example = "")
    @JsonProperty("DEFAULTSERVICETYPE")
    private String defaultServiceType;

    /**
     * 租户ID
     */
    @Column(name = "`TENANT_ID`")
    @ApiModelProperty(name = "TENANTID", value = "", required = true, example = "")
    @JsonProperty("TENANTID")
    private String tenantId;

    private List<TSamGroupInfo> groupInfoList;


    public List<TSamGroupInfo> getGroupInfoList() {
        return groupInfoList;
    }

    public void setGroupInfoList(List<TSamGroupInfo> groupInfoList) {
        this.groupInfoList = groupInfoList;
    }



    /**
     * 是否是管理员
     */
    @JsonProperty("ISPRINCIPAL")
    private String isprincipal;
    public String getIsprincipal() {
        return isprincipal;
    }

    public void setIsprincipal(String isprincipal) {
        this.isprincipal = isprincipal;
    }



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
     * 获取人员姓名。
     *
     * @return STAFF_NAME - 人员姓名。
     */
    public String getStaffName() {
        return staffName;
    }

    /**
     * 设置人员姓名。
     *
     * @param staffName 人员姓名。
     */
    public void setStaffName(String staffName) {
        this.staffName = staffName == null ? null : staffName.trim();
    }

    /**
     * 获取人员状态。取值含义：0：离职 1：在职
     *
     * @return STAFF_STATE - 人员状态。取值含义：0：离职 1：在职
     */
    public String getStaffState() {
        return staffState;
    }

    /**
     * 设置人员状态。取值含义：0：离职 1：在职
     *
     * @param staffState 人员状态。取值含义：0：离职 1：在职
     */
    public void setStaffState(String staffState) {
        this.staffState = staffState == null ? null : staffState.trim();
    }

    /**
     * 获取帐号状态。取值含义：01：正常 02：停用 03：作废 04：锁定 05：解锁 06：失效 07：未启用
     *
     * @return STAFF_ID_STATUS - 帐号状态。取值含义：01：正常 02：停用 03：作废 04：锁定 05：解锁 06：失效 07：未启用
     */
    public String getStaffIdStatus() {
        return staffIdStatus;
    }

    /**
     * 设置帐号状态。取值含义：01：正常 02：停用 03：作废 04：锁定 05：解锁 06：失效 07：未启用
     *
     * @param staffIdStatus 帐号状态。取值含义：01：正常 02：停用 03：作废 04：锁定 05：解锁 06：失效 07：未启用
     */
    public void setStaffIdStatus(String staffIdStatus) {
        this.staffIdStatus = staffIdStatus == null ? null : staffIdStatus.trim();
    }

    /**
     * 获取职级编号。
     *
     * @return DLEVEL_ID - 职级编号。
     */
    public String getDlevelId() {
        return dlevelId;
    }

    /**
     * 设置职级编号。
     *
     * @param dlevelId 职级编号。
     */
    public void setDlevelId(String dlevelId) {
        this.dlevelId = dlevelId == null ? null : dlevelId.trim();
    }

    /**
     * 获取职务。
     *
     * @return DUTY_ID - 职务。
     */
    public String getDutyId() {
        return dutyId;
    }

    /**
     * 设置职务。
     *
     * @param dutyId 职务。
     */
    public void setDutyId(String dutyId) {
        this.dutyId = dutyId == null ? null : dutyId.trim();
    }

    /**
     * 获取附加职务。
     *
     * @return SECOND_DUTY - 附加职务。
     */
    public String getSecondDuty() {
        return secondDuty;
    }

    /**
     * 设置附加职务。
     *
     * @param secondDuty 附加职务。
     */
    public void setSecondDuty(String secondDuty) {
        this.secondDuty = secondDuty == null ? null : secondDuty.trim();
    }

    /**
     * 获取组织机构编号。
     *
     * @return ORGA_ID - 组织机构编号。
     */
    public String getOrgaId() {
        return orgaId;
    }

    /**
     * 设置组织机构编号。
     *
     * @param orgaId 组织机构编号。
     */
    public void setOrgaId(String orgaId) {
        this.orgaId = orgaId == null ? null : orgaId.trim();
    }

    /**
     * 获取人员岗位。
     *
     * @return POST_ID - 人员岗位。
     */
    public String getPostId() {
        return postId;
    }

    /**
     * 设置人员岗位。
     *
     * @param postId 人员岗位。
     */
    public void setPostId(String postId) {
        this.postId = postId == null ? null : postId.trim();
    }

    /**
     * 获取附加岗位。
     *
     * @return SECOND_POST - 附加岗位。
     */
    public String getSecondPost() {
        return secondPost;
    }

    /**
     * 设置附加岗位。
     *
     * @param secondPost 附加岗位。
     */
    public void setSecondPost(String secondPost) {
        this.secondPost = secondPost == null ? null : secondPost.trim();
    }

    /**
     * 获取修改时间。
     *
     * @return UPDA_TETIME - 修改时间。
     */
    public Date getUpdaTetime() {
        return updaTetime;
    }

    /**
     * 设置修改时间。
     *
     * @param updaTetime 修改时间。
     */
    public void setUpdaTetime(Date updaTetime) {
        this.updaTetime = updaTetime;
    }

    /**
     * 获取入职时间
     *
     * @return JOIN_DATE - 入职时间
     */
    public Date getJoinDate() {
        return joinDate;
    }

    /**
     * 设置入职时间
     *
     * @param joinDate 入职时间
     */
    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    /**
     * 获取移动电话号码，在提醒方式配置 功能中使用
     *
     * @return MOBILE_PHONE - 移动电话号码，在提醒方式配置 功能中使用
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * 设置移动电话号码，在提醒方式配置 功能中使用
     *
     * @param mobilePhone 移动电话号码，在提醒方式配置 功能中使用
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    /**
     * 获取Email地址，在提醒方式配 置功能中使用
     *
     * @return EMAIL_ADDRESS - Email地址，在提醒方式配 置功能中使用
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * 设置Email地址，在提醒方式配 置功能中使用
     *
     * @param emailAddress Email地址，在提醒方式配 置功能中使用
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress == null ? null : emailAddress.trim();
    }

    /**
     * 获取地市编号
     *
     * @return CITY_ID - 地市编号
     */
    public String getCityId() {
        return cityId;
    }

    /**
     * 设置地市编号
     *
     * @param cityId 地市编号
     */
    public void setCityId(String cityId) {
        this.cityId = cityId == null ? null : cityId.trim();
    }

    /**
     * 获取省份编号
     *
     * @return PROVICE_ID - 省份编号
     */
    public String getProviceId() {
        return proviceId;
    }

    /**
     * 设置省份编号
     *
     * @param proviceId 省份编号
     */
    public void setProviceId(String proviceId) {
        this.proviceId = proviceId == null ? null : proviceId.trim();
    }

    /**
     * 获取员工类型
     *
     * @return STAFF_TYPE - 员工类型
     */
    public String getStaffType() {
        return staffType;
    }

    /**
     * 设置员工类型
     *
     * @param staffType 员工类型
     */
    public void setStaffType(String staffType) {
        this.staffType = staffType == null ? null : staffType.trim();
    }

    /**
     * 获取身份证号码
     *
     * @return ID_CARD_NO - 身份证号码
     */
    public String getIdCardNo() {
        return idCardNo;
    }

    /**
     * 设置身份证号码
     *
     * @param idCardNo 身份证号码
     */
    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo == null ? null : idCardNo.trim();
    }

    /**
     * 获取渠道ID。渠道定义：融合客服，acmp，众包
     *
     * @return CHANNEL_ID - 渠道ID。渠道定义：融合客服，acmp，众包
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * 设置渠道ID。渠道定义：融合客服，acmp，众包
     *
     * @param channelId 渠道ID。渠道定义：融合客服，acmp，众包
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    /**
     * 获取authen_rule_id
     *
     * @return AUTHEN_RULE_ID - authen_rule_id
     */
    public Integer getAuthenRuleId() {
        return authenRuleId;
    }

    /**
     * 设置authen_rule_id
     *
     * @param authenRuleId authen_rule_id
     */
    public void setAuthenRuleId(Integer authenRuleId) {
        this.authenRuleId = authenRuleId;
    }

    /**
     * 获取添加时间
     *
     * @return INSERT_TIME - 添加时间
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     * 设置添加时间
     *
     * @param insertTime 添加时间
     */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    /**
     * 获取员工名称缩写
     *
     * @return STAFF_NM_SHORT - 员工名称缩写
     */
    public String getStaffNmShort() {
        return staffNmShort;
    }

    /**
     * 设置员工名称缩写
     *
     * @param staffNmShort 员工名称缩写
     */
    public void setStaffNmShort(String staffNmShort) {
        this.staffNmShort = staffNmShort == null ? null : staffNmShort.trim();
    }

    /**
     * 获取人员渠道类型代码(0自有，1外包，2其他，3劳务派遣，4众包呼入会员，5众包双技能会员)
     *
     * @return PRSN_CHNL_TYPE_CD - 人员渠道类型代码(0自有，1外包，2其他，3劳务派遣，4众包呼入会员，5众包双技能会员)
     */
    public String getPrsnChnlTypeCd() {
        return prsnChnlTypeCd;
    }

    /**
     * 设置人员渠道类型代码(0自有，1外包，2其他，3劳务派遣，4众包呼入会员，5众包双技能会员)
     *
     * @param prsnChnlTypeCd 人员渠道类型代码(0自有，1外包，2其他，3劳务派遣，4众包呼入会员，5众包双技能会员)
     */
    public void setPrsnChnlTypeCd(String prsnChnlTypeCd) {
        this.prsnChnlTypeCd = prsnChnlTypeCd == null ? null : prsnChnlTypeCd.trim();
    }

    /**
     * 获取默认业务类型
     *
     * @return DEFAULT_SERVICE_TYPE - 默认业务类型
     */
    public String getDefaultServiceType() {
        return defaultServiceType;
    }

    /**
     * 设置默认业务类型
     *
     * @param defaultServiceType 默认业务类型
     */
    public void setDefaultServiceType(String defaultServiceType) {
        this.defaultServiceType = defaultServiceType == null ? null : defaultServiceType.trim();
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
