package com.ai.sam.vo;
import  com.ai.sam.domain.TSamStaffRole;
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
public class TSamStaffRoleResponse  extends ResponseParent{
	@ApiModelProperty(name = "DATA", value = "", required = true, example = "")
	@JsonProperty("DATA")
	private List<TSamStaffRole> data = new ArrayList<TSamStaffRole>();
	public TSamStaffRoleResponse() {}
	public TSamStaffRoleResponse(Page<TSamStaffRole> pageResult) {
		this.setData(pageResult != null ? pageResult.getResult() : null);
		this.setAttach(new Attach(pageResult != null ? pageResult.getTotal() : 0L));
	}
}
