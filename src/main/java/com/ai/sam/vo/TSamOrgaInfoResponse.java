package com.ai.sam.vo;
import  com.ai.sam.domain.TSamOrgaInfo;
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
public class TSamOrgaInfoResponse  extends ResponseParent{
	@ApiModelProperty(name = "DATA", value = "", required = true, example = "")
	@JsonProperty("DATA")
	private List<TSamOrgaInfo> data = new ArrayList<TSamOrgaInfo>();
	public TSamOrgaInfoResponse() {}
	public TSamOrgaInfoResponse(Page<TSamOrgaInfo> pageResult) {
		this.setData(pageResult != null ? pageResult.getResult() : null);
		this.setAttach(new Attach(pageResult != null ? pageResult.getTotal() : 0L));
	}
}
