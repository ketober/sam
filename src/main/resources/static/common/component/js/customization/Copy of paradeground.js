/*
 * paradeground 
 * @date 2015-09-19
 * @auth AnNing
 */
define(["cmp"], function(cmp) {
	
	/*
	 * 点将台
	 */
	UI.ParadeGround = $.inherit(UI.Component, {
		
		/*
		 * 默认配置项
		 */
		defConf: {},
		
		/*
		 * 类名
		 */
		className: 'UI.ParadeGround',
		
		/*
		 * 模板
		 */
		tpl: "<div id='{id}' class='ui-paradeGround {className}'></div>",
		
		/*
		 * 初始化
		 */
		init: function() {
			
			var conf = this.conf;
			
			conf.data && this.setData(conf.data);
			
			this.initEvent();
			
		},
		
		/*
		 * 注册事件
		 */
		initEvent: function() {
			
			/*
			 * 展开折起
			 */
			this.dom.on("click", "i.folder", function() {
				
				var $this = $(this);
				
				$this.siblings("ul").stop().animate({height: $this.toggleClass("active").hasClass("active") ? "hide" : "show"}, "fast");
				
			});
			
		},
		
		/*
		 * 塞入数据
		 * target	//插入目标的name, 小于等于0时,作为根节点插入
		 * data		//为集合时,作为子节点循环插入
		 */
		insertNode: function(target, data, flag) {
			
			var d = [].concat(data);
			
			if (target <= 0) {
				
				this.dom.empty().html([
				    "<div class='paradeGround-root'>",
				    	"<p name='1'>", d[0], "</p>",
				    "</div>"
				].join(""));
				
				return 1;		//根节点id为1
				
			}
			
			var $target = this.dom.find("p[name='" + target + "']");
				
			if (!$target.size()) return false;
			
			if (target == 1) {
				
				var $div = $target.siblings("div.paradeGround-clusters");
				
				if (!$div.size()) {
					
					$div = $("<div class='paradeGround-clusters'></div>");
					
					$target.after($div);
					
				}
				
				var arr = [],
					idArr = [],
					len = $div.children().size();
				
				var id;
				
				d.each(function(i, item) {
					
					id = target + "-" + (len + i);
					
					arr.push([
					    "<div class='paradeGround-cluster'>",
					    	"<p name='", id, "'>", item, "</p>",
					    "</div>"
					].join(""));
					
					idArr.push(id);
					
				});
				
				$div.append(arr.join(""));
				
				var w = $div.children().size() * 200;
				
				$div.parent().css({width: w});
				
				$div.children().removeClass("last-child").last().addClass("last-child");
				
				return idArr.join(",");
				
			} else {
				
				var $parent = $target.parent(),
					$ul = $target.siblings("ul");
				
				if (!$ul.size()) $ul = $("<ul></ul>").appendTo($parent);
					
				if ($parent.is("li")) {
					
					$parent.find(">i").addClass("folder");
					
				}
				
				var arr = [],
					len = $ul.children().size();
				
				if (flag) {
					
					d.each(function(i, item) {
						
						arr.push([
						    "<li>",
						    	"<i></i>",
						    	"<span>", item, "</span>",
						    "</li>"
						].join(""));
						
					});
					
				} else {
					
					var id,
						idArr = [];
					
					d.each(function(i, item) {
						
						id = target + "-" + (len + i);
						
						arr.push([
						    "<li>",
						    	"<i></i>",
						    	"<p name='", id, "'>", item, "</p>",
						    "</li>"
						].join(""));
						
						idArr.push(id);
						
					});
					
				}
				
				$ul.append(arr.join("")).children().removeClass("last-child").last().addClass("last-child");
				
				return flag ? undefined :idArr.join(",");
				
			}
		},
		
		/*
		 * 清空组件
		 */
		empty: function() {
			
			this.dom.empty();
			
		}
		
	});
	
	return UI.ParadeGround;
	
});