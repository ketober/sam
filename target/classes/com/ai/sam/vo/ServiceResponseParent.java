package com.ai.sam.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class ServiceResponseParent {

	@ApiModelProperty(name = "STATUS", value = "服务请求结果编码", required = true, example = "0000")
	@JsonProperty("STATUS")
	@JSONField(name = "STATUS")
	protected String status;
	@ApiModelProperty(name = "MSG", value = "服务请求结果描述", required = true, example = "服务调用成功！")
	@JSONField(name = "MSG")
	@JsonProperty("MSG")
	protected String msg;
	@ApiModelProperty(name = "TXID", value = "服务流水号(自动生成)", required = true, example = "b393548b3dc5^1504165518997^70")
	@JSONField(name = "TXID")
	@JsonProperty("TXID")
	protected String txid;


	
}
