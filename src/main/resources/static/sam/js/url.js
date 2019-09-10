/*
 * 所有的请求都在这里咯
 */
define(["request"], function() {
	
	var rootContext = window.rootContext = "/sam";
	/*
	 * init
	 */
	function init() {
		/*
		 * 人员信息维护
		 */
        Request.set("config/query_staffInfo_list", "", rootContext+"/StaffInfo/qryStaffInfo");
        /*
		 * 菜单信息维护-新增/修改菜单
		 */
        Request.set("config/create_menu", "", rootContext+"/Menu/addMenu");
        /*
		 * 菜单信息维护-删除菜单
		 */
        Request.set("config/delete_menu", "", rootContext+"/Menu/delMenu");
        /*
		 * 菜单信息维护-查询菜单
		 */
        Request.set("config/query_menu", "", rootContext+"/Menu/qryMenu");
	}
	
	return {
		init: init
	};
	
});