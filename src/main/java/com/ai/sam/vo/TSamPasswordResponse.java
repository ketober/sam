package com.ai.sam.vo;
import  com.ai.sam.domain.TSamPassword;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper=false)
@Data
@ApiModel
public class TSamPasswordResponse  extends ResponseParent{
	@ApiModelProperty(name = "DATA", value = "", required = true, example = "")
	@JsonProperty("DATA")
	private List<TSamPassword> data = new ArrayList<TSamPassword>();
	public TSamPasswordResponse() {}
	public TSamPasswordResponse(Page<TSamPassword> pageResult) {
		this.setData(pageResult != null ? pageResult.getResult() : null);
		this.setAttach(new Attach(pageResult != null ? pageResult.getTotal() : 0L));
	}
}
