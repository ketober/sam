package com.ai.sam.domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Table(name = "`t_sam_service_system`")
@ApiModel
public class TSamServiceSystem {
    @Column(name = "`SERVICE_ID`")
    @ApiModelProperty(name = "SERVICEID", value = "", required = true, example = "")
    @JsonProperty("SERVICEID")
    private String serviceId;

    @Column(name = "`SERVICE_NAME`")
    @ApiModelProperty(name = "SERVICENAME", value = "", required = true, example = "")
    @JsonProperty("SERVICENAME")
    private String serviceName;

    @Column(name = "`SERVICE_DESC`")
    @ApiModelProperty(name = "SERVICEDESC", value = "", required = true, example = "")
    @JsonProperty("SERVICEDESC")
    private String serviceDesc;

    @Column(name = "`CLIENT_ID`")
    @ApiModelProperty(name = "CLIENTID", value = "", required = true, example = "")
    @JsonProperty("CLIENTID")
    private String clientId;

    @Column(name = "`STATE`")
    @ApiModelProperty(name = "STATE", value = "", required = true, example = "")
    @JsonProperty("STATE")
    private String state;

    /**
     * @return SERVICE_ID
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * @param serviceId
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }

    /**
     * @return SERVICE_NAME
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * @param serviceName
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName == null ? null : serviceName.trim();
    }

    /**
     * @return SERVICE_DESC
     */
    public String getServiceDesc() {
        return serviceDesc;
    }

    /**
     * @param serviceDesc
     */
    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc == null ? null : serviceDesc.trim();
    }

    /**
     * @return CLIENT_ID
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * @param clientId
     */
    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
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
