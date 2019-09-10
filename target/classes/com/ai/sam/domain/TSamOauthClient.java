package com.ai.sam.domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Table(name = "`t_sam_oauth_client`")
@ApiModel
public class TSamOauthClient {
    /**
     * 客户端编码
     */
    @Id
    @Column(name = "`CLIENT_ID`")
    @ApiModelProperty(name = "CLIENTID", value = "", required = true, example = "")
    @JsonProperty("CLIENTID")
    private String clientId;

    /**
     * 客户端名称
     */
    @Column(name = "`CLIENT_NAME`")
    @ApiModelProperty(name = "CLIENTNAME", value = "", required = true, example = "")
    @JsonProperty("CLIENTNAME")
    private String clientName;

    /**
     * 客户端密码
     */
    @Column(name = "`CLIENT_SECRET`")
    @ApiModelProperty(name = "CLIENTSECRET", value = "", required = true, example = "")
    @JsonProperty("CLIENTSECRET")
    private String clientSecret;

    /**
     * 客户端描述
     */
    @Column(name = "`CLIENT_DESC`")
    @ApiModelProperty(name = "CLIENTDESC", value = "", required = true, example = "")
    @JsonProperty("CLIENTDESC")
    private String clientDesc;

    /**
     * 重定向地址
     */
    @Column(name = "`REDIRECT_URI`")
    @ApiModelProperty(name = "REDIRECTURI", value = "", required = true, example = "")
    @JsonProperty("REDIRECTURI")
    private String redirectUri;

    @Column(name = "`ACCESS_TOKEN_VALIDITY`")
    @ApiModelProperty(name = "ACCESSTOKENVALIDITY", value = "", required = true, example = "")
    @JsonProperty("ACCESSTOKENVALIDITY")
    private Integer accessTokenValidity;

    @Column(name = "`REFRESH_TOKEN_VALIDITY`")
    @ApiModelProperty(name = "REFRESHTOKENVALIDITY", value = "", required = true, example = "")
    @JsonProperty("REFRESHTOKENVALIDITY")
    private Integer refreshTokenValidity;

    /**
     * 状态
     */
    @Column(name = "`STATE`")
    @ApiModelProperty(name = "STATE", value = "", required = true, example = "")
    @JsonProperty("STATE")
    private String state;

    @Column(name = "`GRANT_TYPES`")
    @ApiModelProperty(name = "GRANTTYPES", value = "", required = true, example = "")
    @JsonProperty("GRANTTYPES")
    private String grantTypes;

    /**
     * 获取客户端编码
     *
     * @return CLIENT_ID - 客户端编码
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * 设置客户端编码
     *
     * @param clientId 客户端编码
     */
    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    /**
     * 获取客户端名称
     *
     * @return CLIENT_NAME - 客户端名称
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * 设置客户端名称
     *
     * @param clientName 客户端名称
     */
    public void setClientName(String clientName) {
        this.clientName = clientName == null ? null : clientName.trim();
    }

    /**
     * 获取客户端密码
     *
     * @return CLIENT_SECRET - 客户端密码
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * 设置客户端密码
     *
     * @param clientSecret 客户端密码
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret == null ? null : clientSecret.trim();
    }

    /**
     * 获取客户端描述
     *
     * @return CLIENT_DESC - 客户端描述
     */
    public String getClientDesc() {
        return clientDesc;
    }

    /**
     * 设置客户端描述
     *
     * @param clientDesc 客户端描述
     */
    public void setClientDesc(String clientDesc) {
        this.clientDesc = clientDesc == null ? null : clientDesc.trim();
    }

    /**
     * 获取重定向地址
     *
     * @return REDIRECT_URI - 重定向地址
     */
    public String getRedirectUri() {
        return redirectUri;
    }

    /**
     * 设置重定向地址
     *
     * @param redirectUri 重定向地址
     */
    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri == null ? null : redirectUri.trim();
    }

    /**
     * @return ACCESS_TOKEN_VALIDITY
     */
    public Integer getAccessTokenValidity() {
        return accessTokenValidity;
    }

    /**
     * @param accessTokenValidity
     */
    public void setAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    /**
     * @return REFRESH_TOKEN_VALIDITY
     */
    public Integer getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    /**
     * @param refreshTokenValidity
     */
    public void setRefreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    /**
     * 获取状态
     *
     * @return STATE - 状态
     */
    public String getState() {
        return state;
    }

    /**
     * 设置状态
     *
     * @param state 状态
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * @return GRANT_TYPES
     */
    public String getGrantTypes() {
        return grantTypes;
    }

    /**
     * @param grantTypes
     */
    public void setGrantTypes(String grantTypes) {
        this.grantTypes = grantTypes == null ? null : grantTypes.trim();
    }
}
