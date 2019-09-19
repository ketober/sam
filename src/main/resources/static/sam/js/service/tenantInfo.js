window._Config_ = {
    date: new Date()
};
require.config({
    urlArgs: "date=" + _Config_.date.getDate(),
    paths: _PATH_,
    shim: {
        "easyui-core": {
            deps: ["jquery"],
            exports: "easyui-core"
        },
        "easyui": {
            deps: ["easyui-core"],
            exports: "easyui"
        }
    }
});
require(["load", "request", "jquery", 'ajaxUtils','crm', 'common-enum', 'easyui', 'backspace'], function (load, request, $,AjaxUtils, CRM, ENUM) {
    load.loadEnd();
    var $page, $search;  //$head,, $service
    var $popWindow;
    var serviceAccount, serviceId;
    //获取平台账号信息
    var $accountState = CRM.getEnum("PLATFORM_ACCOUNT_STATE@CS_IR");
    $page = $("<div></div>").appendTo($("#page_content"));

    $(document).find("#layout_content").layout();
    initSearchGrid();
    initAccountGrid();
    initGlobalEvent();

    /**
     * 初始化查询条件
     */
    function initSearchGrid() {
        $search = $([
            "<div class='panel-search'>",
            "<form class='form form-horizontal'>",
            "<div class='row cl'>",
            "<label class='form-label col-2 '>租户ID：</label>",
            "<div class='formControls col-2'>" +
            "<input type='text' class='easyui-textbox' name='tenantId' style='width:100%;height:30px' >" +
            "</div>",

            "<label class='form-label col-2'>租户名称：</label>",
            "<div class='formControls col-2'>" +
            "<input type='text' class='easyui-textbox' name='tenantName' style='width:100%;height:30px' >" +
            "</div>",
            "<label class='form-label col-2' >创建人：</label>",
            "<div class='formControls col-2' >" +
            "<input class='easyui-textbox'  name='crtUserId' style='width:100%;height:30px' </div>",
            "</div>",
            "<div class='row cl'>",
            "<div class='mt-10 text-c '>" +
            "<a href='javascript:void(0)' class='btn btn-green radius'><i class='iconfont iconfont-search2'></i>查询</a>",
            "<a href='javascript:void(0)' class='btn btn-default radius mt-l-20 '><i class='iconfont iconfont-zhongzuo'></i>重置</a>",
            "</div>",
            "</div>",
            "</form>",
            "</div>"
        ].join("")).appendTo($page);

        $search.find("input.easyui-textbox").textbox();
        $search.find("input.easyui-datetimebox").datetimebox({
            editable: false
        });
        // $search.find("select.easyui-combobox").combobox();
        $search.find("a.easyui-linkbutton").linkbutton();
        /*
         * enter键触发查询  skillName
         */
        $search.find("input.easyui-textbox").textbox({
            inputEvents: $.extend({}, $.fn.textbox.defaults.inputEvents, {
                keyup: function (event) {
                    if (event.keyCode == 13) {
                        $search.find("a.btn-green").click();
                    }
                }
            })
        });
        /*
         * 清除查询条件
         */
        $search.on("click", "a.btn-default", function () {
            $state = "-1";
            $search.find('form.form').form('clear');
            $("#state").val("00");
            $page.find("#account").datagrid("load");
        });
        $search.find("a.btn").linkbutton();

    }

    /**
     * 获取查询参数
     * easyui组件会将真实的值存放在一个类名为textbox-value的input
     */
    function getParams($document) {
        var param = {};

        $document && $document.find("input").each(function () {
            var $item = $(this);
            param[$item.attr("name")] = $item.val();
        });
        $document && $document.find("select").each(function() {
            var $item  = $(this);
            param[$item.attr("name")] = $item.val();
        });
        return param;
    }

    function initAccountGrid() {
        //var $account=
        $([
            "<div class='cl'>",
            "<div class='panel-tool-box cl' >",
            "<div class='fl text-bold'>租户配置列表</div>",
            "<div class='fr'>",
            "<a id='createAccount'  href='javascript:void(0)'  class='btn btn-secondary radius mt-l-20'>" +
            "<i class='iconfont iconfont-add'></i>添加</a>",
            "<a id='updateAccount'  href='javascript:void(0)'  class='btn btn-secondary radius mt-l-20'>" +
            "<i class='iconfont iconfont-edit'></i>修改</a>",
            "<a id='deleteAccount'  href='javascript:void(0)'  class='btn btn-secondary radius mt-l-20'>" +
            "<i class='iconfont iconfont-del2'></i>删除</a>",
            "</div>",
            "</div>",
            "</div>",
            "<table id='account' class='easyui-datagrid'  style=' width:100%;height:245px;'</table>"
        ].join("")).appendTo($page);
        $page.find("#account").datagrid({
            columns: [[
                {field: 'ck', checkbox: 'true', width: '5%'},
                {field: 'tenantId', title: '租户ID', width: '11%'},
                {field: 'tenantName', title: '租户名称', width: '11%'},
                {field: 'crtUserId', title: '创建人', width: '11%'},
                {field: 'modfUserId', title: '修改人', width: '11%'},
                {field: 'crtTime', title: '创建时间', width: '15%'},
                {field: 'modfTime', title: '修改时间', width: '15%'},
                {field: 'tenantDesc', title: '备注', width: '23%'}
            ]],
            fitColumns: true,
            rownumbers: true,
            striped: true,
            nowrap: true,
            pagination: true,
            collapsible: true,
            singleSelect: false,
            height: 420,
            pageSize: 10,
            pageList: [5, 10, 15, 20],
            loader: function (param, success) {
                var start = (param.page - 1) * param.rows ;
                var pageNum = param.rows;
                var params = $.extend({"startIndex":start, "pageNum":pageNum,"sort": param.sort,
                    "order": param.order}, getParams($search));
                AjaxUtils.commonAjax('post','/sam/tsamtenantinfo/qrytenantinfo',params,function (data) {
                    $search.find("a.btn-green").linkbutton({disabled: false});
                    var dd={"total":data.total,"rows":data.list};
                    success(dd);
                    $("#account").datagrid('doCellTip', {'max-width': '400px'});
                },function () {
                    $.messager.alert("提示","查询业务系统信息失败!");
                },'json');
            }
        });
        $page.find("a.btn").linkbutton();

    }

    function initGlobalEvent() {
        /*
        * 查询事件
        */
        $search.on("click", "a.btn-green", function () {
            if ($(this).linkbutton("options").disabled) {
                return;
            }
            //禁用按钮
            $(this).linkbutton({disabled: true});
            $page.find("#account").datagrid("load");
        });

        $page.on("click", "#deleteAccount", function () {
            if ($(this).linkbutton("options").disabled) {
                return;
            }
            //禁用按钮
            $(this).linkbutton({disabled: true});

            var selections = $page.find("#account").datagrid("getSelections");

            if (selections.length > 0) {
                var ids = [];
                selections.each(function (index) {
                    ids.push(selections[index]['tenantId']);
                });
                $.messager.confirm("提示", "确认删除？", function (sure) {
                    if (sure) {
                        var url ='/sam/tsamtenantinfo/delTenant?ids='+ids;
                        AjaxUtils.commonAjax('post',url,null,function (data) {
                            $("#deleteAccount").linkbutton({disabled: false});
                            $page.find("#account").datagrid('load');
                        },function () {
                            $.messager.alert("提示","删除租户配置信息失败!");
                        },'json');
                    } else {
                        return false;
                    }
                });
            } else {
                $.messager.alert('提示', '请选择要删除的数据!');
                $(this).linkbutton({disabled: false});
            }
        });
        /*
         * 弹出添加窗口
         */
        $page.on("click", "#createAccount", function () {
            $page.find("#account").datagrid("clearSelections");
            serviceAccount = null;
            $("#win_content").show().window({
                width: 850,
                height: 500,
                modal: true,
                title: "新增租户配置"
            });
            initPopWindow();
            initWindowEvent();
        });

        /*
         * 弹出修改窗口
         */
        $page.on("click", "#updateAccount", function () {
            var selections = $page.find("#account").datagrid('getSelections');
            if (selections.length > 1) {
                $.messager.alert('提示', '只能选择一条配置！');
            } else if (selections.length > 0) {
                serviceId = selections[0]['serviceId'];
                serviceAccount = selections[0];
                $("#win_content").show().window({
                    width: 850,
                    height: 500,
                    modal: true,
                    title: "修改租户配置"
                });
                initUpdatePopWindow();
                initUpdateWindowEvent();
            }
            else {
                $.messager.alert('提示', '请选择一项配置！');
            }
        });

    }

    /**
     * 初始化弹出窗口
     */
    function initPopWindow() {
        $("#win_content").empty();
        $popWindow = $("<div></div>").appendTo($("#win_content"));

        //账号配置表单
        // var $winContent =
        $([
            "<div class='panel-tool-box cl' >",
            "<div class='fl text-bold'>基本信息</div>",
            "</div>",
            "<div class='panel-search'>",
            "<form id='createServiceAccount' method='POST' class='form form-horizontal'>",
            "<div style='display:none'>",
            "<input type='hidden'  name='serviceId' class='easyui-textbox' value='0'   />",
            "</div>",
            "<div class='row cl'>",
            "<label class='form-label col-2'>租户ID</label>",
            "<div class='formControls col-2'><input  type='text' id='tenantId' name='tenantId' class='easyui-textbox'  style='width:90%;height:30px' /><span style='color:red;padding-left:2px'>*</span></div>" +
            "<label class='form-label col-2'>租户名称</label>" +
            "<div class='formControls col-2'><input  type='text' id='tenantName' name='tenantName' class='easyui-textbox' style='width:90%;height:30px' /><span style='color:red;padding-left:2px'>*</span></div>" +
            "<label class='form-label col-2'>创建员工</label>" +
            "<div class='formControls col-2'><input  type='text' id='crtUserId' name='crtUserId' class='easyui-textbox' style='width:90%;height:30px' /><span style='color:red;padding-left:2px'>*</span></div>" +
            "</div>",
            "<div class='row cl'>",
            "<label class='form-label col-2'>备注</label>" +
            "<div class='formControls col-8'><input  type='text' id ='tenantDesc' name='tenantDesc' class='easyui-textbox' style='width:100%;height:55px' /></div>" +
            "</div>",
            "</form>",
            "</div>"
        ].join("")).appendTo($popWindow);
        $([
            "<div class='mt-10 test-c'>",
            "<label class='form-label col-5'></label>",
            "<a href='javascript:void(0)' id='global' class='btn btn-green radius mt-l-20' >" +
            "保存</a>",
            "<span>          </span>",
            "<a href='javascript:void(0)' id='cancel' class='btn btn-secondary radius mt-l-20' >" +
            "取消</a>",
            "</div>",
            "</div>"
        ].join("")).appendTo($popWindow);
        //修改时加载已有数据
        if (serviceAccount != null) {
            $popWindow.find("#createServiceAccount").form("load", serviceAccount);

        }
        $("#serviceDesc").textbox({
            multiline: true
        });
        $popWindow.find("a.btn").linkbutton();
    }

    /**
     * 初始化修改弹出窗口
     */
    function initUpdatePopWindow() {
        $("#win_content").empty();
        $popWindow = $("<div></div>").appendTo($("#win_content"));

        //账号配置表单
        // var $winContent =
        $([
            "<div class='panel-tool-box cl' >",
            "<div class='fl text-bold'>基本信息</div>",
            "</div>",
            "<div class='panel-search'>",
            "<form id='createServiceAccount' method='POST' class='form form-horizontal'>",
            "<div style='display:none'>",
            "<input type='hidden'  name='serviceId' class='easyui-textbox' value='0'   />",
            "</div>",
            "<div class='row cl'>",
            "<label class='form-label col-2'>租户ID</label>",
            "<div class='formControls col-2'><input  type='text' id='tenantId' name='tenantId' class='easyui-textbox'  style='width:90%;height:30px' /><span style='color:red;padding-left:2px'>*</span></div>" +
            "<label class='form-label col-2'>租户名称</label>" +
            "<div class='formControls col-2'><input  type='text' id='tenantName' name='tenantName' class='easyui-textbox' style='width:90%;height:30px' /><span style='color:red;padding-left:2px'>*</span></div>" +
            "<label class='form-label col-2'>创建员工</label>" +
            "<div class='formControls col-2'><input  type='text' id='crtUserId' name='crtUserId' class='easyui-textbox' style='width:90%;height:30px' /><span style='color:red;padding-left:2px'>*</span></div>" +
            "</div>",
            "<div class='row cl'>",
            "<label class='form-label col-2'>备注</label>" +
            "<div class='formControls col-8'><input  type='text' id ='tenantDesc' name='tenantDesc' class='easyui-textbox' style='width:100%;height:55px' /></div>" +
            "</div>",
            "</form>",
            "</div>"
        ].join("")).appendTo($popWindow);
        $([
            "<div class='mt-10 test-c'>",
            "<label class='form-label col-5'></label>",
            "<a href='javascript:void(0)' id='globalUpd' class='btn btn-green radius mt-l-20' >" +
            "保存</a>",
            "<span>          </span>",
            "<a href='javascript:void(0)' id='cancelUpd' class='btn btn-secondary radius mt-l-20' >" +
            "取消</a>",
            "</div>",
            "</div>"
        ].join("")).appendTo($popWindow);
        //修改时加载已有数据
        if (serviceAccount != null) {
            $popWindow.find("#createServiceAccount").form("load", serviceAccount);

        }
        $("#serviceDesc").textbox({
            multiline: true
        });
        $popWindow.find("a.btn").linkbutton();
    }

    /**
     * 初始化弹出窗口事件
     */
    function initWindowEvent() {
        $popWindow.on("click", "#global", function () {
            // if($(this).hasClass("disabled")){
            //     return;
            // }
            // //关闭按钮，防止多次提交
            // $(this).addClass("disabled");
            if ($(this).linkbutton("options").disabled) {
                return;
            }
            //禁用按钮
            $(this).linkbutton({disabled: true});

            var tenantId = $popWindow.find("input[id='tenantId']").val().trim();
            var tenantName = $popWindow.find("input[name='tenantName']").val().trim();
            var crtUserId = $popWindow.find("input[name='crtUserId']").val().trim();
            var tenantDesc=$popWindow.find("input[id='tenantDesc']").val().trim();
            if (!tenantId) {
                $.messager.alert('提示', '请填写租户ID！');
                $(this).linkbutton({disabled: false});
            }else if(!tenantName){
                $.messager.alert('提示', '请填写租户名称！');
                $(this).linkbutton({disabled: false});
            }else if(!crtUserId){
                $.messager.alert('提示', '请填写创建员工ID！');
                $(this).linkbutton({disabled: false});
            } else {
                var sd = getParams($popWindow);
                AjaxUtils.commonAjax('post','/sam/tsamtenantinfo/addTenant',sd,function (data) {
                    $("#global").linkbutton({disabled: false});
                    if (data.resultVal === "1") {
                        $.messager.alert('提示', '操作成功！');
                        $("#win_content").window("close");
                        $page.find("#account").datagrid("load");
                    } else {
                        $.messager.alert('提示', data.resultMsg);
                    }
                },function () {
                    $.messager.alert("提示","新增租户配置失败!");
                },'json');
            }
        });
        $popWindow.on("click", "#cancel", function () {
            $popWindow.find('form.form').form('clear');
            $("#win_content").window("close");
        });

    }

    /**
     * 初始化修改弹出窗口事件
     */
    function initUpdateWindowEvent() {
        $popWindow.on("click", "#globalUpd", function () {
            // if($(this).hasClass("disabled")){
            //     return;
            // }
            // //关闭按钮，防止多次提交
            // $(this).addClass("disabled");
            if ($(this).linkbutton("options").disabled) {
                return;
            }
            //禁用按钮
            $(this).linkbutton({disabled: true});


            var tenantId = $popWindow.find("input[name='tenantId']").val().trim();
            var tenantName = $popWindow.find("input[name='tenantName']").val().trim();
            var crtUserId = $popWindow.find("input[name='crtUserId']").val().trim();
            if (!tenantId) {
                $.messager.alert('提示', '请填写租户ID！');
                $(this).linkbutton({disabled: false});
            }else if(!tenantName){
                $.messager.alert('提示', '请填写租户名称！');
                $(this).linkbutton({disabled: false});
            }else if(!crtUserId){
                $.messager.alert('提示', '请填写创建员工ID！');
                $(this).linkbutton({disabled: false});
            } else {
                var sd = getParams($popWindow);
                AjaxUtils.commonAjax('post','/sam/tsamtenantinfo/updateTenant',sd,function (data) {
                    $("#global").linkbutton({disabled: false});

                    if (data.resultVal === "1") {
                        $.messager.alert('提示', '操作成功！');
                        $("#win_content").window("close");
                        $page.find("#account").datagrid("load");
                    } else {
                        $.messager.alert('提示', data.resultMsg);
                    }
                },function () {
                    $.messager.alert("提示","修改租户信息失败!");
                },'json');
            }
        });
        $popWindow.on("click", "#cancelUpd", function () {
            $popWindow.find('form.form').form('clear');
            $("#win_content").window("close");
        });

    }

    /**
     * 鼠标移至单元格展示内容效果
     */

    function currentTime() {
        var d = new Date(), str = '';
        var month = d.getMonth() + 1;
        var date = d.getDate();
        if (month < 10) {
            month = '0' + month;
        }
        if (date < 10) {
            date = '0' + date;
        }

        str += d.getFullYear() + '-';
        str += month + '-';
        str += date;
        return str;
    }

    /**
     datagrid增加ToolTip
     */
    $.extend($.fn.datagrid.methods, {
        /**
         * 开打提示功能
         * @param {} jq
         * @param {} params 提示消息框的样式
         * @return {}
         */
        doCellTip: function (jq, params) {
            function showTip(data, td, e) {
                if ($(td).text() == "") {
                    return;
                }
                data.tooltip.text($(td).text()).css({
                    top: (e.pageY + 10) + 'px',
                    left: (e.pageX + 20) + 'px',
                    'z-index': $.fn.window.defaults.zIndex,
                    display: 'block'
                });
            };
            return jq.each(function () {
                var grid = $(this);
                var options = $(this).data('datagrid');
                if (!options.tooltip) {
                    var panel = grid.datagrid('getPanel').panel('panel');
                    var defaultCls = {
                        'border': '1px solid #333',
                        'padding': '2px',
                        'color': '#333',
                        'background': '#FCFCFC',
                        'position': 'fixed',
                        'max-width': '400px',
                        'border-radius': '4px',
                        '-moz-border-radius': '4px',
                        '-webkit-border-radius': '4px',
                        'display': 'none'
                    }
                    var tooltip = $("<div id='celltip'></div>").appendTo('body');
                    tooltip.css($.extend({}, defaultCls, params.cls));
                    options.tooltip = tooltip;
                    panel.find('.datagrid-body').each(function () {
                        var delegateEle = $(this).find('> div.datagrid-body-inner').length ? $(this).find('> div.datagrid-body-inner')[0] : this;
                        $(delegateEle).undelegate('td', 'mouseover').undelegate('td', 'mouseout').undelegate('td', 'mousemove').delegate('td', {
                            'mouseover': function (e) {
                                if (params.delay) {
                                    if (options.tipDelayTime) {
                                        clearTimeout(options.tipDelayTime);
                                    }
                                    var that = this;
                                    options.tipDelayTime = setTimeout(function () {
                                        showTip(options, that, e);
                                    }, params.delay);
                                }
                                else {
                                    showTip(options, this, e);
                                }

                            },
                            'mouseout': function (e) {
                                if (options.tipDelayTime) {
                                    clearTimeout(options.tipDelayTime);
                                }
                                options.tooltip.css({
                                    'display': 'none'
                                });
                            },
                            'mousemove': function (e) {
                                var that = this;
                                if (options.tipDelayTime) {
                                    clearTimeout(options.tipDelayTime);
                                }
                                //showTip(options, this, e);
                                options.tipDelayTime = setTimeout(function () {
                                    showTip(options, that, e);
                                }, params.delay);
                            }
                        });
                    });

                }

            });
        },

        cancelCellTip: function (jq) {
            return jq.each(function () {
                var data = $(this).data('datagrid');
                if (data.tooltip) {
                    data.tooltip.remove();
                    data.tooltip = null;
                    var panel = $(this).datagrid('getPanel').panel('panel');
                    panel.find('.datagrid-body').undelegate('td', 'mouseover').undelegate('td', 'mouseout').undelegate('td', 'mousemove')
                }
                if (data.tipDelayTime) {
                    clearTimeout(data.tipDelayTime);
                    data.tipDelayTime = null;
                }
            });
        }
    });

});
