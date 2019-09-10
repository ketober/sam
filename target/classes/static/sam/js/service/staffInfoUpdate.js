// window._Config_ = {
//     date: new Date()
// };
// require.config({
//     urlArgs: "date=" + _Config_.date.getDate(),
//     paths: _PATH_,
//     shim: {
//         "easyui-core": {
//             deps: ["jquery"],
//             exports: "easyui-core"
//         },
//         "easyui": {
//             deps: ["easyui-core"],
//             exports: "easyui"
//         },
//         "ztree-core": {
//             deps: ["jquery"],
//             exports: "ztree-core"
//         },
//         "ztree-exedit": {
//             deps: ["ztree-core"],
//             exports: "ztree"
//         }
//     }
// });
// require(["load","request", "jquery",'crm', 'common-enum',  'easyui','ztree-core', 'ztree-exedit','backspace'], function (load,request, $, CRM,ENUM) {
//     // load.loadEnd();
//     // var $page, $search;  //$head,, $service
//     // var $popWindow,$staffInfo;
//     // var staffInfo,staffId,orgaCode;
//     // initOrgainfoTree();
//     //
//     // /**
//     //  * 初始化资源树
//     //  */
//     // function initOrgainfoTree(){
//     //     var setting = {
//     //         async : {
//     //             enable : true, // 设置 zTree是否开启异步加载模式
//     //             url: "/tsamorgainfo/selectSamOrgaTree",
//     //             autoParam : [ "id" ]	// 异步加载时自动提交父节点属性的参数,假设父节点 node = {id:1, name:"test"}，异步加载时，提交参数 zId=1
//     //         },
//     //         data:{ // 必须使用data
//     //             simpleData : {
//     //                 enable : true,
//     //                 idKey : "id", // id编号命名 默认
//     //                 pIdKey : "pId", // 父id编号命名 默认
//     //                 rootPId : 0	// 用于修正根节点父节点数据，即 pIdKey 指定的属性值
//     //             }
//     //         },
//     //         // 回调函数
//     //         callback : {
//     //             onClick : function(event, treeId, treeNode, clickFlag) {
//     //                 // 判断是否父节点
//     //                 if(!treeNode.isParent){
//     //                     orgaCode = treeNode.id;
//     //                 }
//     //                 // alert(treeNode.id);
//     //                 $page.find("#staff").datagrid('reload');
//     //             },
//     //             //捕获异步加载出现异常错误的事件回调函数 和 成功的回调函数
//     //             onAsyncError : zTreeOnAsyncError,
//     //             onAsyncSuccess : function(event, treeId, treeNode, msg){
//     //
//     //             }
//     //         }
//     //     };
//     //
//     //     // 加载错误提示
//     //     function zTreeOnAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
//     //         alert("加载错误：" + XMLHttpRequest);
//     //     };
//     //
//     //     // 过滤函数
//     //     function filter(treeId, parentNode, childNodes) {
//     //         if (!childNodes)
//     //             return null;
//     //         for ( var i = 0, l = childNodes.length; i < l; i++) {
//     //             childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
//     //         }
//     //         return childNodes;
//     //     }
//     //     $.fn.zTree.init( $("#menuTree"), setting);
//     // }
//
//     /**
//      * 鼠标移至单元格展示内容效果
//      */
//
//     function currentTime(){
//         var d = new Date(),str = '';
//         var month = d.getMonth()+1;
//         var date = d.getDate();
//         if(month <10){
//             month = '0'+month;
//         }
//         if(date<10){
//             date = '0'+date;
//         }
//
//         str += d.getFullYear()+'-';
//         str  += month+'-';
//         str  += date;
//         return str;
//     }
//
//     /**
//      datagrid增加ToolTip
//      */
//     $.extend($.fn.datagrid.methods, {
//         /**
//          * 开打提示功能
//          * @param {} jq
//          * @param {} params 提示消息框的样式
//          * @return {}
//          */
//         doCellTip: function (jq, params) {
//             function showTip(data, td, e) {
//                 if ($(td).text() == ""){
//                     return;
//                 }
//                 data.tooltip.text($(td).text()).css({
//                     top: (e.pageY + 10) + 'px',
//                     left: (e.pageX + 20) + 'px',
//                     'z-index': $.fn.window.defaults.zIndex,
//                     display: 'block'
//                 });
//             };
//             return jq.each(function () {
//                 var grid = $(this);
//                 var options = $(this).data('datagrid');
//                 if (!options.tooltip) {
//                     var panel = grid.datagrid('getPanel').panel('panel');
//                     var defaultCls = {
//                         'border': '1px solid #333',
//                         'padding': '2px',
//                         'color': '#333',
//                         'background': '#FCFCFC',
//                         'position': 'fixed',
//                         'max-width': '400px',
//                         'border-radius': '4px',
//                         '-moz-border-radius': '4px',
//                         '-webkit-border-radius': '4px',
//                         'display': 'none'
//                     }
//                     var tooltip = $("<div id='celltip'></div>").appendTo('body');
//                     tooltip.css($.extend({}, defaultCls, params.cls));
//                     options.tooltip = tooltip;
//                     panel.find('.datagrid-body').each(function () {
//                         var delegateEle = $(this).find('> div.datagrid-body-inner').length ? $(this).find('> div.datagrid-body-inner')[0] : this;
//                         $(delegateEle).undelegate('td', 'mouseover').undelegate('td', 'mouseout').undelegate('td', 'mousemove').delegate('td', {
//                             'mouseover': function (e) {
//                                 if (params.delay) {
//                                     if (options.tipDelayTime){
//                                         clearTimeout(options.tipDelayTime);
//                                     }
//                                     var that = this;
//                                     options.tipDelayTime = setTimeout(function () {
//                                         showTip(options, that, e);
//                                     }, params.delay);
//                                 }
//                                 else {
//                                     showTip(options, this, e);
//                                 }
//
//                             },
//                             'mouseout': function (e) {
//                                 if (options.tipDelayTime){
//                                     clearTimeout(options.tipDelayTime);
//                                 }
//                                 options.tooltip.css({
//                                     'display': 'none'
//                                 });
//                             },
//                             'mousemove': function (e) {
//                                 var that = this;
//                                 if (options.tipDelayTime){
//                                     clearTimeout(options.tipDelayTime);
//                                 }
//                                 //showTip(options, this, e);
//                                 options.tipDelayTime = setTimeout(function () {
//                                     showTip(options, that, e);
//                                 }, params.delay);
//                             }
//                         });
//                     });
//
//                 }
//
//             });
//         },
//
//         cancelCellTip: function (jq) {
//             return jq.each(function () {
//                 var data = $(this).data('datagrid');
//                 if (data.tooltip) {
//                     data.tooltip.remove();
//                     data.tooltip = null;
//                     var panel = $(this).datagrid('getPanel').panel('panel');
//                     panel.find('.datagrid-body').undelegate('td', 'mouseover').undelegate('td', 'mouseout').undelegate('td', 'mousemove')
//                 }
//                 if (data.tipDelayTime) {
//                     clearTimeout(data.tipDelayTime);
//                     data.tipDelayTime = null;
//                 }
//             });
//         }
//     });
//
// });
