package com.ai.sam.vo;
import  com.ai.sam.domain.TSamPostInfo;
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
public class TSamPostInfoResponse  extends ResponseParent{
	@ApiModelProperty(name = "DATA", value = "", required = true, example = "")
	@JsonProperty("DATA")
	private List<TSamPostInfo> data = new ArrayList<TSamPostInfo>();
	public TSamPostInfoResponse() {}
	public TSamPostInfoResponse(Page<TSamPostInfo> pageResult) {
		this.setData(pageResult != null ? pageResult.getResult() : null);
		this.setAttach(new Attach(pageResult != null ? pageResult.getTotal() : 0L));
	}
}
