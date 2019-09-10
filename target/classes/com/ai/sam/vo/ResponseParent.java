package com.ai.sam.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class ResponseParent {
	@ApiModelProperty(name = "RSP_CODE", value = "", required = true, example = "")
    @JsonProperty(value = "RSP_CODE")
    private String rspcode;
	@ApiModelProperty(name = "RSP_DESC", value = "", required = true, example = "")
    @JsonProperty(value = "RSP_DESC")
    private String rspdesc;
	
	@ApiModelProperty(name = "ATTACH", value = "", required = true, example = "")
	@JsonProperty(value = "ATTACH")
	private Attach attach;

	
	
	
	
}
