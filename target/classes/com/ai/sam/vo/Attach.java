package com.ai.sam.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class Attach {
	@ApiModelProperty(name = "TOTAL", value = "", required = true, example = "")
	@JsonProperty("TOTAL")
	private Long total;

	public Attach() {}
	
	public Attach(Long total) {
		this.total = total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
}
