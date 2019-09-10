package com.ai.sam.domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Table(name = "`t_sam_province_info`")
@ApiModel
public class TSamProvinceInfo {
    /**
     * provinceid
     */
    @Id
    @Column(name = "`PROVINCE_ID`")
    @ApiModelProperty(name = "PROVINCEID", value = "", required = true, example = "")
    @JsonProperty("PROVINCEID")
    private String provinceId;

    /**
     * provincename
     */
    @Column(name = "`PROVINCE_NAME`")
    @ApiModelProperty(name = "PROVINCENAME", value = "", required = true, example = "")
    @JsonProperty("PROVINCENAME")
    private String provinceName;

    @Column(name = "`TENANT_ID`")
    @ApiModelProperty(name = "TENANTID", value = "", required = true, example = "")
    @JsonProperty("TENANTID")
    private String tenantId;

    /**
     * 获取provinceid
     *
     * @return PROVINCE_ID - provinceid
     */
    public String getProvinceId() {
        return provinceId;
    }

    /**
     * 设置provinceid
     *
     * @param provinceId provinceid
     */
    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId == null ? null : provinceId.trim();
    }

    /**
     * 获取provincename
     *
     * @return PROVINCE_NAME - provincename
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * 设置provincename
     *
     * @param provinceName provincename
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
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
