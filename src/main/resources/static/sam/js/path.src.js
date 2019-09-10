/*
 * 引用路径
 * @date 2015-09-07
 * @auth AnNing
 */
window._PATH_ = {
	
	/* core */
	"jquery": "../../../../../sam/common/jquery/jquery-1.11.3.min",
    "crm": "../core/crm",
    "core": "../core/core",
    "backspace": "../backspace",
    "ajaxUtils": "../ajaxUtils",

	/* core */

	/* request */
	"request": "../request/request",
	/* request */

	/* plugin */
    "easyui-core": "../../../common/easyui/jquery.easyui.min",
	"easyui": "../../../common/easyui/locale/easyui-lang-zh_CN",
	/* plugin */
	
	/* component */
	"cmp": "../../../common/component/js/component",
	"prototype": "../../../common/component/js/prototype",
	"dialog": "../../../common/component/js/base/dialog",
	"tab": "../../../common/component/js/base/tab",
	"tree": "../../../common/component/js/base/tree",
	"grid": "../../../common/component/js/base/grid",
	"select": "../../../common/component/js/base/select",
	"date": "../../../common/component/js/base/datechooser",
	"textinput": "../../../common/component/js/base/textinput",
	"pagination": "../../../common/component/js/base/pagination",
	"swapgrid": "../../../common/component/js/customization/swapgrid",
	"magictree": "../../../common/component/js/customization/magictree",
	"paradeground": "../../../common/component/js/customization/paradeground",
	/* component */

	/*easyui*/
	"tabs":"../../../common/easyui/src/jquery.tabs",
	/*easyui*/

	/*echarts*/
    "echarts":"../../../common/echarts/echarts.min",
	/*echarts*/

	/*zTree*/
	"ztree-core":"../../../common/ztree/js/jquery.ztree.core",
	"ztree-exedit":"../../../common/ztree/js/jquery.ztree.exedit",
    "ztree-excheck":"../../../common/ztree/js/jquery.ztree.excheck",
	/*zTree*/

	/* common */
	"common-enum": "../common/common-enum",
	"common-data": "../common/common-data",
	"common-date": "../common/common-date",
	"common-event": "../common/common-event",
	/* common */

	/* highcharts */
	"highcharts": "../../../common/highcharts/highcharts",
    "exporting": "../../../common/highcharts/modules/exporting",

	/* highcharts */


	/* service */
	// "homepage": "service/homepage/homepage",
	/* service */

	/*ajaxfileupload begin*/
	"ajaxFileupload":"../../../common/ajaxfileupload",
	/*ajaxfileupload end*/

	"json": "../../js/lib/json2/1.0.0/json2",

	"ZeroClipboard":"../../../common/uedit/third-party/zeroclipboard/ZeroClipboard.min",

	 "bootstrap": "../../../common/bootstrap/bootstrap.min",

    "metisMenu": "../../../common/metisMenu/jquery.metisMenu",
    "slimscroll": "../../../common/slimscroll/jquery.slimscroll.min",
    "contabs": "../../../common/contabs/contabs.min",

    "url": "../url",
    "sideMenu":"../service/sideMenu",
    "load":"../service/load"
    /*----IR START---------------------------------------------------------------*/


    /*----IR END---------------------------------------------------------------*/

};

/**
 * 避免data-main指定的js来自缓存
 */
var require = {
    urlArgs : "bust="+new Date().getDate()
    // urlArgs : "bust="+Math.random()
};