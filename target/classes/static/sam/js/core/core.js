/*
 * 全局变量
 * 放在CORE中,控制变量污染
 * @auth AnNing
 */
define(["request"], function(request) {
	
// 	$.ajax({
//         type: "GET",
//         url: "api/account/session",
//         success: function(data) {
//
//         	if (data.success != "true") {
//
// 	            window.location.href = "login.html";
// 	        }
//
//         }
//     });
//
	Request.type = 1;			//0为本地服务;1为远程服务
//
// 	Request.mockPref = "amber/data/";	//请求管理~本地服务的前缀路径
//
	var CORE = window.CORE = {}; //,
// 		$document = CORE.document = $(document),
// 		$pageBody = CORE.body = $("#page-body"),
// 		$exit = $("#page-exit");
//
// 	/*
// 	 * 方便其他模块查看引用
// 	 */
// 	var pageDataMap = CORE.pageDataMap = {};
//
// 	/*
// 	 * 页面数据
// 	 */
// 	var pageData = CORE.pageData = [{
// 		title: "新建项目",
// 		imgPath: "theme/img/img-001.png",
// 		jsPath: "service/add-project/add-pro"
// 	}, {
// 		title: "新建配置文件",
// 		imgPath: "theme/img/img-002.png",
// 		jsPath: "service/add-configfile/add-configfile"
// 	}, {
// 		title: "一键上传配置",
// 		imgPath: "theme/img/img-003.png",
// 		jsPath: "service/config-upload/config-upload"
// 	}, {
// 		title: "一键下载配置",
// 		imgPath: "theme/img/img-004.png",
// 		jsPath: "service/config-download/config-download"
// 	}, {
// 		title: "一键复制配置",
// 		imgPath: "theme/img/img-005.png",
// 		jsPath: "service/config-copy/config-copy"
// 	}, {
// 		title: "批量数据表更新",
// 		imgPath: "theme/img/img-006.png",
// 		jsPath: "service/data-update/data-update"
// 	}, {
// 		title: "数据模板下载",
// 		imgPath: "theme/img/img-007.png",
// 		jsPath: "service/data-templet/data-templet"
// 	}, {
// 		title: "客户端配置导出",
// 		imgPath: "theme/img/img-008.png",
// 		jsPath: "service/config-export/config-export"
// 	}, {
// 		title: "配置管理",
// 		imgPath: "theme/img/img-009.png",
// 		jsPath: "service/config-manage/config-manage"
// 	}, {
// 		title: "数据源配置",
// 		imgPath: "theme/img/img-010.png",
// 		jsPath: "service/datasource-manage/datasource-manage"
// 	}, {
// 		title: "应用节点配置",
// 		imgPath: "theme/img/img-011.png",
// 		jsPath: "service/config-node/config-node"
// 	}].each(function(i, item) {
//
// 		pageDataMap[item.jsPath.split("/").last()] = item;
//
// 	});
//
// 	pageDataMap.back = {
// 		title: "返回",
// 		imgPath: "theme/img/img-back.png",
// 		jsPath: "back"
// 	};
//
// 	/*
// 	 * 最大高度
// 	 */
// 	var $window = CORE.window = $(window).on("resize", function() {
//
// 		$pageBody.add($pageBody.children()).css({"min-height": $window.height() - 60});
//
// 	});
//
// 	/*
// 	 * 重写ajax
// 	 */
// 	$.oldAjax = $.ajax;
//
// 	$.ajax = function(config) {
//
// 		$.oldAjax($.extend({}, config, {
// 			success: function(data) {
//
// 				if (typeof data == "string") {
//
// 					if (data.indexOf("session_invalid") > 0) {
//
// 						window.location.href = "login.html";
//
// 					}
//
// 				} else if (data.success == "true") {
//
// 					config.success(data);
//
// 				} else {
//
// 					UI.alert(data.info);
//
// 					config.error && config.error();
//
// 				}
//
// 			}
// 		}));
//
// 	};
//
// 	/*
// 	 * 渲染一个页面
// 	 */
// 	CORE.pageRender = function(module, config) {
//
// //		if (module.type) {
// //			module.create();
// //		} else {
// //			CORE.body.empty();
// //			var $page = $("<div></div>").appendTo(CORE.body);
// //			module.create($page);
// //			$window.trigger("resize");
// //		}
// 		CORE.body.empty();
//
// 		var $page = $("<div></div>").appendTo(CORE.body);
//
// 		module.create($page, config);
//
// 		$window.trigger("resize");
//
// 	};
//
// 	/*
// 	 * 提交成功
// 	 */
// 	CORE.submitSuccess = function($win, text, data) {
//
// 		var $content = $win._content;
//
// 		$content.children().animate({width: "hide", opacity: 0}, "fast", function() {
//
// 			$("<div class='common-floating-success'>" + text + "</div>")
// 				.hide()
// 				.appendTo($win._content)
// 				.fadeIn("fast", function() {
//
// 					CORE.anchorRender($("<ul class='page-anchor-list'></ul>").appendTo($win._content), data);
//
// 				});
//
// 		});
//
// 	};
//
// 	/*
// 	 * 获取所有的项目
// 	 */
// 	CORE.getAllApp = function(success) {
//
// 		$.ajax({
// 			url: Request.get("app/list"),
// 			type: "get",
// 			success: success
// 		});
//
// 	};
//
// 	/*
// 	 * 获取所有的环境
// 	 */
// 	CORE.getAllEnv = function(success) {
//
// 		$.ajax({
// 			url: Request.get("env/list"),
// 			type: "get",
// 			success: success
// 		});
//
// 	};
//
// 	/*
// 	 * 通过appId和envId获取version
// 	 */
// 	CORE.getVersion = function(appId, envId, success) {
//
// 		if (!appId || !envId) return;
//
// 		$.ajax({
// 			url: Request.get("web/config/versionList"),
// 			type: "get",
// 			data: {
// 				appId: appId,
// 				envId: envId
// 			},
// 			success: success
// 		});
//
// 	};
//
// 	/*
// 	 * 数据源配置 & 应用节点配置 & 配置管理
// 	 * 渲染文档,绑定事件
// 	 */
// 	CORE.initMenu = function($content, config) {
//
// 		$content.html([
// 		    "<div class='common-app-select'>",
// 		    	"<a class='common-app-select-handler'>项目</a>",
// 		    	"<b class='common-app-select-title'></b>",
// 		    "</div>",
// 		    "<ul class='common-env-list'></ul>"
// 		].join(""));
//
// 		var $appSelect = $content.find("div"),
// 			$menu = $content.find("ul");
//
// 		var status = 2,
// 			$appList = new UI.ScrollBox({cls: "common-app-list", width: 200, height: 200}).appendTo($appSelect);
//
// 		/*
// 		 * 查找所有的项目
// 		 */
// 		CORE.getAllApp(function(data) {
//
// 			$appList.html(data.result.format("<p data-id='{appId}'>{name}</p>"));
//
// 			if (!(--status)) {
//
// 				config && config.appId ? $appList.find("p[data-id=" + config.appId + "]").trigger("click") : $appList.find("p:first-child").trigger("click");
//
// 			}
//
// 		});
//
// 		/*
// 		 * 查找所有的环境
// 		 */
// 		CORE.getAllEnv(function(data) {
//
// 			$menu.html(data.result.format("<li><span data-id='{envId}'>{name}</span></li>"));
//
// 			if (!(--status)) {
//
// 				config && config.appId ? $appList.find("p[data-id=" + config.appId + "]").trigger("click") : $appList.find("p:first-child").trigger("click");
//
// 			}
//
// 		});
//
// 		/*
// 		 * 展开项目列表
// 		 */
// 		$appSelect.on("click", ">a", function() {
//
// 			UI.showPop($appList);
//
// 		});
//
// 		/*
// 		 * 选中一个项目
// 		 */
// 		$appList.on("click", "p", function() {
//
// 			var $this = $(this);
//
// 			$this.addClass("active").siblings().removeClass("active");
//
// 			$appSelect.find("b").text($this.text()).attr({"data-id": $this.attr("data-id")});
//
// 			config && config.envId ? $menu.find("span[data-id=" + config.envId + "]").trigger("click") : $menu.find("li:first-child span").trigger("click");
//
// 		});
//
// 	};
//
// 	/*
// 	 * 右上角锚点
// 	 */
// 	$("#page-anchor").on("click", "span", function() {
//
// 		var $ul = $(this).next().empty();
//
// 		$ul
// 			.show()
// 			.stop()
// 			.css({width: 0, height: 0})
// 			.animate(
// 				{
// 					width: 850,
// 					height: Math.ceil(pageData.length / 5) * 150
// 				},
// 				"fast",
// 				function() {
//
// 					CORE.anchorRender($ul, pageData);
//
// 					UI.showPop($ul);
//
// 				}
// 			);
//
// 	});
//
// 	/*
// 	 * 锚点模板
// 	 */
// 	var anchorTpl = [
// 	    "<li>",
// 	    	"<div>",
// 		    	"<img src='amber/{imgPath}' />",
// 		    	"<p>{title}</p>",
// 		    "</div>",
// 	    "</li>"
// 	].join("");
//
// 	/*
// 	 * 锚点渲染的速度(ms)
// 	 */
// 	var anchorSpeed = 75;
//
// 	/*
// 	 * 渲染锚点列表
// 	 */
// 	CORE.anchorRender = function($ul, data, order) {
//
// 		data.each(function(i, item) {
//
// 			var $li = $(anchorTpl.format(item))
// 				.data("path", item.jsPath)
// 				.appendTo($ul);
//
// 			setTimeout(function() {
//
// 				$li.find("div").fadeIn();
//
// 				$li.find("img").animate({width: 110, height: 110}, "fast", function() {
//
// 					$(this).animate({width: 88, height: 88}, "fast");
//
// 				});
//
// 			}, (order ? order[i] : i) * anchorSpeed);
//
// 		});
//
// 	};
//
// 	/*
// 	 * 鼠标移入锚点
// 	 */
// 	CORE.document.on("mouseenter", "ul.page-anchor-list li", function() {
//
// 		$(this).find("img").stop().animate({width: 110, height: 110}, "fast");
//
// 	});
//
// 	/*
// 	 * 鼠标移出锚点
// 	 */
// 	CORE.document.on("mouseleave", "ul.page-anchor-list li", function() {
//
// 		$(this).find("img").stop().animate({width: 88, height: 88}, "fast");
//
// 	});
//
// 	/*
// 	 * 点击锚点,进入一个模块
// 	 */
// 	CORE.document.on("click", "ul.page-anchor-list li", function() {
//
// 		UI.hidePop();
//
// 		var $this = $(this),
// 			$win = $this.closest(".pageui-floatingWin"),
// 			path = $this.data("path");
//
// 		if ($win.size()) {
//
// 			UI($win)[0].pop(false);
//
// 			if (path == "back") return;
//
// 		}
//
// 		//location.hash = path.split("/").last();
//
// 		require([path], function(obj) {
//
// 			if (obj.type) {
//
// 				obj.create();
//
// 			} else {
//
// 				location.hash = path.split("/").last();
//
// 			}
//
// 		});
//
// 	});
//
// 	CORE.hashChange = window.onhashchange = function() {
//
// 		var obj = pageDataMap[location.hash.slice(1)];
//
// 		if (obj) {
//
// 			require([obj.jsPath], function(o) {
//
// 				CORE.pageRender(o);
//
// 			});
//
// 		} else {
//
// 			CORE.pageRender(require("pageMap"));
//
// 		}
//
// 	};
//
// 	/*
// 	 * 页面等待
// 	 */
// 	CORE.loading = function(flag) {
//
// 		var $loading = $("#page-loading");
//
// 		if ($loading.size()) {
//
// 			flag ? $loading.show() : $loading.remove();
//
// 		} else if (flag) {
//
// 			$("body").append("<div id='page-loading' class='icon-loading'></div>");
//
// 		}
//
// 	};
//
// 	/*
// 	 * html中特殊字符转换
// 	 */
// 	CORE.escapeHtml = function(string) {
//
// 		 var entityMap = {
// 			    "&": "&amp;",
// 			    "<": "&lt;",
// 			    ">": "&gt;",
// 			    '"': '&quot;',
// 			    "'": '&#39;',
// 			    "/": '&#x2F;'
// 		 };
//
// 		 return String(string).replace(/[&<>"'\/]/g, function (s) {
// 		        return entityMap[s];
// 		 });
//
// 	};
	
	return CORE;
	
});