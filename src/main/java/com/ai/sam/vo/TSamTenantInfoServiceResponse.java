package com.ai.sam.vo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ai.sam.utils.ServiceConstant;
import com.ai.sam.utils.TxidUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper=false)
@Data
@ApiModel
public class TSamTenantInfoServiceResponse  extends ServiceResponseParent {
    private static Logger logger = LoggerFactory.getLogger(TSamTenantInfoServiceResponse.class);
    @ApiModelProperty(name = "RSP", value = "服务返回业务数据", required = true)
    @JSONField(name = "RSP")
	@JsonProperty("RSP")
	private TSamTenantInfoResponse rsp ;
    public TSamTenantInfoServiceResponse() {
        try {
            this.txid = TxidUtils.generateTxid();
        } catch (Exception e) {
            logger.error("can not get PtxId:", e);
        }
    }
    public TSamTenantInfoServiceResponse getSuccessResponse(TSamTenantInfoResponse response) {
        this.setStatus(ServiceConstant.STATUS_SUCCESS);
        this.setMsg(ServiceConstant.MSG_SUCCESS);
        this.setRsp(response);
        this.setTxid(TxidUtils.generateTxid());
        return this;
    }
    public TSamTenantInfoServiceResponse getErrorResponse(String errorStatus, String errorMsg, TSamTenantInfoResponse response) {
        this.setStatus(errorStatus);
        this.setMsg(errorMsg);
        this.setRsp(response);
        this.setTxid(TxidUtils.generateTxid());
        return this;
    }
}
