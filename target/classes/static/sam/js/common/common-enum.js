/*
 * 枚举值
 */
define(["crm", 'ajaxUtils',"request"], function (CRM, AjaxUtils,request) {
    // request.set("common-enum", "../js/data/common/enum.json", "service?action=getParamValues&isconvert=true");
    // request.set("common-enum", parent.rootContext + "/StaticValueConfig/getParamValues", parent.rootContext + "/StaticValueConfig/getParamValues");

    /*
     * 枚举map
     */
    var ENUM = CRM.ENUM = {
        map: {},
        arr: {}
    };

    /*
     * 获取的值为map型
     */
    CRM.getEnum = function (param) {

        return getEnum(param, "map");

    };

    /*
     * 获取的值为arr型
     */
    CRM.getEnumArr = function (param) {
        return getEnum(param, "arr");
    };

    /**
     * 设置枚举值
     */
    CRM.setEnum = function (param, arr) {
        setEnum(param, arr);
    }

    /**
     * 设置枚举值
     */
    function setEnum(param, arr) {
        ENUM['map'][param] = arr;
    }

    /*
     * 获取枚举值
     */
    function getEnum(param, type) {

        /*
         * 如果该枚举值还未被加载---加载
         */
        if (!ENUM[type][param]) {

            var params = {};
            params.code=param;
            AjaxUtils.commonAjaxAsync('POST','/sam/StaticValueConfig/getParamValues',false,params,function (data) {
                helper(param, data.beans);
            },'json');
            // $.ajax({
            //     // url: request.get("common-enum"),
            //     url: "/sam/StaticValueConfig/getParamValues",
            //     type: "POST",
            //     dataType: "json",
            //     async: false,				//必须是同步请求,也是无可奈何的事情
            //     data: {
            //         code: param
            //     },
            //     success: function (data) {
            //
            //         helper(param, data.beans);
            //
            //     }
            // });

        }

        return ENUM[type][param] || (type == "map" ? {} : []);

    };
    //是否包含
    CRM.containEnum = function (param) {

     if (!ENUM["map"][param]) {
   		 return false;
   	 }
   	 return true;

    }
    /*
     * 转化成需要的数据结构
     */
    function helper(param, data) {

        var map = {};

        (data || []).each(function (i, item) {

            map[item.value] = item.displayValue;

        });

        ENUM.map[param] = map;

        ENUM.arr[param] = data;

    }

    /*
     *
     */
    return {
        getEnum: CRM.getEnum,
        getEnumArr: CRM.getEnumArr
    };

});
