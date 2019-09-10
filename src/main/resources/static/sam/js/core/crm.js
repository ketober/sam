/*
 * 全局变量
 * 放在CRM中,控制变量污染
 * @date 2015-09-07
 * @auth AnNing
 */
define(["prototype"], function() {
	
	var CRM = window.CRM = {};
	
	CRM.WeekMap = [
	    "星期天",
	    "星期一",
	    "星期二",
	    "星期三",
	    "星期四",
	    "星期五",
	    "星期六"
	];
	
    var navLiTpl = [
    	"<li name='{funcId}'>",
    		"<span>{name}</span>",
    		"<dl></dl>",
    		"<i></i>",
    	"</li>"
    ].join("");
    
    var navListLiTpl = "<dd name='{funcId}'>{name}</dd>";
	
	/*
	 * 可以加载的模块,通过{id:path}的键值对存放在这里
	 */
	var MODELS = CRM.MODELS = {
		"0": "homepage",
		"-1": "service/__helper__/suggestion"
	};

	/*
	 * 选择**的按钮
	 */
	CRM.selectBtns = [
	    [
   			"<a class='ui-btn-fill' name='next'>",
   				"<span class='ui-btn-icon icon-next'>继 续</span>",
   			"</a>"
   	    ].join(""),
   	    [
   			"<a class='ui-btn' name='prev'>",
   				"<span class='ui-btn-icon icon-prev'>返 回</span>",
   			"</a>",
   			"<a class='ui-btn-fill disabled' name='confirm'>",
   				"<span>确 定</span>",
   			"</a>"
   	    ].join("")
   	];
	
	/*
	 * 获取参数列表
	 */
	CRM.getParams = function($con) {
		
		var param = {};
		
		$con.find(".textbox-value").each(function() {
			
			var $this = $(this);
			
			param[$this.attr("name")] = $this.val();
			
		});
		
		return param;
		
	};
	
	/*
	 * 开启窗口方法,固定接口,方便以后重写
	 */
	CRM.open = function(suffix) {
		
		window.open(window.location.origin + window.location.pathname + "?" + suffix);
		
	};
	
	/*
	 * 跳转到
	 */
	CRM.jumpTo = function(suffix) {
		
		window.location.href = window.location.origin + window.location.pathname + "?" + suffix;

	};
	
	/*
	 * 关闭窗口
	 */
	CRM.close = function() {
		
		window.close();
		
	};
	
	/*
	 * alert
	 */
	CRM.alert = function(p1, p2) {
		
		var title = "提示",
			text = "",
			fn = function() {};
		
		if (typeof p1 == "string") {
			
			text = p1;
			
			if (p2 && typeof p2 == "function") fn = p2;
			
		} else {
			
			if (p1.title && typeof p1.title == "string") title = p1.title;
			
			if (p1.text && typeof p1.text == "string") text = p1.text;

			if (p1.fn && typeof p1.fn == "function") fn = p1.fn;
			
		}
		
		require(["dialog"], function(dialog) {
			
			new UI.Dialog({
				title: title,
				titleCls: "title-tip",
				width: 500,
				height: 240,
				btns: [{
					label: "确 定",
					name: "confirm",
					handler: function() {
						
						fn();
						
						this.pop(0);
						
					}
				}]
			})
			  .append([
				"<table cellpadding='0' cellspacing='0' width='100%' height='100%'>",
					"<tbody>",
						"<tr>",
							"<td align='center'>", text, "</td>",
						"</tr>",
					"</tbody>",
				"</table>"
			  ].join(""))
			  .pop(true);
			
		});
		
	};
	
	/*
	 * confirm
	 */
	CRM.confirm = function(param) {
		
		var title = param.title || "提示",
			text = param.text || "",
			confirm = param.confirm || function() {},
			cancel = param.cancel || function() {};
			
		require(["dialog"], function(dialog) {
			
			new UI.Dialog({
				title: title,
				titleCls: "title-tip",
				width: 500,
				height: 240,
				btns: [{
					label: "确 定",
					name: "confirm",
					handler: function() {
						
						confirm();
						
						this.pop(0);
						
					}
				}, {
					label: "取 消",
					name: "cancel",
					handler: function() {
						
						cancel();
						
						this.pop(0);
						
					}
				}]
			})
			  .append([
				"<table cellpadding='0' cellspacing='0' width='100%' height='100%'>",
					"<tbody>",
						"<tr>",
							"<td align='center'>", text, "</td>",
						"</tr>",
					"</tbody>",
				"</table>"
			  ].join(""))
			  .pop(true);
			
		});
		
	};
	
	return CRM;
	
});
