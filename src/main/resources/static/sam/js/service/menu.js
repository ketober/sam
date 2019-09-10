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
        },
        "ztree-core": {
            deps: ["jquery"],
            exports: "ztree-core"
        },
        "ztree-exedit": {
            deps: ["ztree-core"],
            exports: "ztree"
        }
    }
});
require(["load", "request", "jquery", "ajaxUtils", 'crm', 'common-enum', 'easyui', 'ztree-core', 'ztree-exedit', 'backspace'], function (load, request, $, AjaxUtils, CRM, ENUM) {
    load.loadEnd();
    var $page, $search;
    var $popWindow;
    var menuId, menu;
    var clickedTreeId = "000";
    var searchNodes, nowSearchNum = -1;

    $page = $("<div></div>").appendTo($("#page_content"));

    $(document).find("#layout_content").layout();
    initMenuTree();
    initSearchGrid();
    initMenuGrid();
    initGlobalEvent();

    /**
     * 初始化菜单树
     */
    function initMenuTree() {
        var token = AjaxUtils.getToken();
        var zTreeObj;
        var setting = {
            view: {
                dblClickExpand: false,
                selectedMulti: false
            },
            async: {
                dataType: "json",
                enable: true,
                url: "/sam/Menu/qryMenuTree?access_token=" + token,
                autoParam: ["id", "MENUID"],
                dataFilter: filter
            },
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pid",
                    rootPId: 0
                }
            },
            callback: {
                onClick: zTreeOnClick,
                onAsyncSuccess: alfterAsync
            }
        };

        zTreeObj = $.fn.zTree.init($("#menuTree"), setting);

        function zTreeOnClick(event, treeId, treeNode) {
            clickedTreeId = treeNode.id;
            $page.find("#menu").datagrid('load');
        }

        function alfterAsync(event, treeId, treeNode) {
            if (!treeNode) {
                treeNode = zTreeObj.getNodes()[0];
                zTreeObj.expandNode(treeNode, true);
            }
            $("#" + treeNode.tId + "_a").click();
            if (treeNode.children.length === 0) {
                treeNode.isParent = false;
                zTreeObj.updateNode(treeNode);
            }
        }

        function filter(treeId, parentNode, childNodes) {
            if (!childNodes) {
                AjaxUtils.commonAjax('post', '/sam/Menu/addRootMenu', {}, function (data) {
                }, function () {
                    $.messager.alert("提示","创建根节点失败!");
                },'json');
            }
            childNodes = childNodes['menus'];

            if (childNodes) {
                for (var i = 0, l = childNodes.length; i < l; i++) {
                    childNodes[i].id = childNodes[i].MENUID;
                    childNodes[i].name = childNodes[i].MENUNAME;
                    childNodes[i].pid = childNodes[i].PARENTID;
                }
                //childNodes.splice(1,1);
            }
            return childNodes;
        }
    }

    /**
     * 初始化查询条件
     */
    function initSearchGrid() {
        $search = $([
            "<div class='panel-search'>",
            "<form class='form form-horizontal'>",
            "<div class='row cl'>",
            "<label class='form-label col-2'>菜单编号：</label>",
            "<div class='formControls col-2'>" +
            "<input type='text' class='easyui-textbox' name='id' style='width:100%;height:30px' >" +
            "</div>",
            "<label class='form-label col-2'>菜单名称：</label>",
            "<div class='formControls col-2'>" +
            "<input type='text' class='easyui-textbox' name='name' style='width:100%;height:30px' >" +
            "</div>",
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

        /*
         * enter键触发查询
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
        return param;
    }

    //显示转化
    function transOpenmodule(data) {
        if (data === "Y") {
            return "新窗口";
        } else if (data === "N") {
            return "主页面"
        } else {
            return data;
        }
    }

    //loding框
    function ajaxLoading() {
        $("<div class=\"datagrid-mask\"></div>").css({
            display: "block",
            width: "100%",
            height: $(window).height()
        }).appendTo($popWindow);
        $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo($popWindow).css({
            display: "block",
            left: ($(document.body).outerWidth(true) - 190) / 2,
            top: ($(window).height() - 45) / 2
        });
    }

    function ajaxLoadEnd() {
        $(".datagrid-mask").remove();
        $(".datagrid-mask-msg").remove();
    }

    function initMenuGrid() {
        $([
            "<div class='cl'>",
            "<div class='panel-tool-box cl' >",
            "<div class='fl text-bold'>菜单项维护</div>",
            "<div class='fr'>",
            "<a id='createMenu'  href='javascript:void(0)'  class='btn btn-secondary radius mt-l-20'>" +
            "</i>新增</a>",
            "<a id='updateMenu'  href='javascript:void(0)'  class='btn btn-secondary radius mt-l-20'>" +
            "</i>修改</a>",
            "<a id='deleteMenu'  href='javascript:void(0)'  class='btn btn-secondary radius mt-l-20'>" +
            "</i>删除</a>",
            "</div>",
            "</div>",
            "</div>",
            "<table id='menu' class='easyui-datagrid'  style=' width:100%;height:245px;'</table>"
        ].join("")).appendTo($page);
        $page.find("#menu").datagrid({
            columns: [[
                {field: 'ck', checkbox: 'true', width: '5%'},
                {field: 'MENUNAME', title: '菜单名称', width: '8%'},
                {field: 'MENUID', title: '菜单编号', width: '8%'},
                {field: 'PARENTID', title: '父菜单编号', width: '8%'},
                {field: 'MODULEID', title: '模块', width: '8%'},
                {field: 'DISPLAYNO', title: '显示顺序号', width: '8%'},
                {field: 'OPENMODULE', title: '打开方式', width: '7%'},
                {field: 'TENANTID', title: '租户ID', width: '7%', hidden: true},
                {field: 'IMAGEURL', title: '图片地址', width: '16%'},
                {field: 'MENUURL', title: '菜单地址', width: '16%'},
                {field: 'MENUDESCRIPTION', title: '菜单说明', width: '16%'}

            ]],
            fitColumns: true,
            rownumbers: true,
            striped: true,
            nowrap: true,
            pagination: true,
            collapsible: true,
            singleSelect: false,
            height: 450,
            pageSize: 10,
            pageList: [5, 10, 15, 20],
            loader: function (param, success) {
                var start = (param.page - 1) * param.rows + 1;
                var pageNum = param.rows;
                var params = $.extend({
                    "startIndex": start,
                    "pageNum": pageNum,
                    "treeId": clickedTreeId
                }, getParams($search));

                AjaxUtils.commonAjax('post', '/sam/Menu/qryMenu', params, function (data) {
                    if (data.result === "0000") {
                        $search.find("a.btn-green").linkbutton({disabled: false});
                        for (var i = 0; i < data.rows.length; i++) {
                            data["rows"][i]["OPENMODULE"] = transOpenmodule(data["rows"][i]["OPENMODULE"]);
                        }
                        success(data);
                        $("#menu").datagrid('doCellTip', {'max-width': '400px'});
                    } else {
                        $.messager.alert("提示", data.msg);
                    }
                },function () {
                    $.messager.alert("提示","查询菜单信息失败!");
                }, 'json');
            }
        });
        $page.find("a.btn").linkbutton();

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
            "<div class='fl text-bold'>菜单信息</div>",
            "</div>",
            "<div class='panel-search'>",
            "<form id='basicData' method='POST' class='form form-horizontal'>",
            "<div style='display:none'>",
            "<input type='hidden'  name='TENANTID' class='easyui-textbox' value='1'/>",
            "</div>",
            "<div class='row cl'>",
            "<label class='form-label col-2'>模块</label>",
            "<div class='formControls col-2'><input  type='text' id='MODULEID' name='MODULEID' class='easyui-textbox'  style='width:90%;height:30px' /><span style='color:red;padding-left:2px'>*</span></div>" +
            "<label class='form-label col-2'>父菜单编号</label>" +
            "<div class='formControls col-2'><input  type='text' id='PARENTID' name='PARENTID' class='easyui-textbox'  style='width:90%;height:30px' /><span style='color:red;padding-left:2px'>*</span></div>" +
            "<label class='form-label col-2'>菜单编号</label>" +
            "<div class='formControls col-2'><input  type='text' id='MENUID' name='MENUID'  class='easyui-textbox'  style='width:90%;height:30px'/><span style='color:red;padding-left:2px'>*</span></div>" +
            "</div>",
            "<div class='row cl'>",
            "<label class='form-label col-2'>显示顺序号</label>",
            "<div class='formControls col-2'><input  type='text' id='DISPLAYNO' name='DISPLAYNO' class='easyui-textbox' style='width:90%;height:30px' /><span style='color:red;padding-left:2px'>*</span></div>" +
            "<label class='form-label col-2'>菜单名称</label>" +
            "<div class='formControls col-2'><input  type='text' name='MENUNAME' maxlength='15' class='easyui-textbox' style='width:90%;height:30px' /><span style='color:red;padding-left:2px'>*</span></div>" +
            "<label class='form-label col-2'>打开方式</label>" +
            "<div class='formControls col-2'><input  type='text' id='OPENMODULE' name='OPENMODULE'  class='easyui-textbox' style='width:90%;height:30px'/><span style='color:red;padding-left:2px'>*</span></div>" +
            "</div>",
            "<div class='row cl'>",
            "<label class='form-label col-2'>图片地址</label>" +
            "<div class='formControls col-8'><input  type='text' name='IMAGEURL' class='easyui-textbox' style='width:122.5%;height:55px' /></div>" +
            "</div>",
            "<div class='row cl'>",
            "<label class='form-label col-2'>菜单地址</label>" +
            "<div class='formControls col-8'><input  type='text'  name='MENUURL' class='easyui-textbox' style='width:122.5%;height:55px' /></div>" +
            "</div>",
            "<div class='row cl'>",
            "<label class='form-label col-2'>菜单说明</label>" +
            "<div class='formControls col-8'><input  type='text' id='MENUDESCRIPTION' name='MENUDESCRIPTION' class='easyui-textbox' style='width:122.5%;height:55px' /></div>" +
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
        if (menu != null) {
            $popWindow.find("#basicData").form("load", menu);
        } else {
            var params = {};

            var treeObj = $.fn.zTree.getZTreeObj("menuTree");
            var node = treeObj.getSelectedNodes()[0];

            AjaxUtils.commonAjax('post', '/sam/Auth/qryChild?clickedTreeId='+clickedTreeId, params, function (data) {
                $("#MENUID").textbox('setValue', data.newId);
                $("#MODULEID").textbox('setValue', data.module);
                $("#PARENTID").textbox('setValue', clickedTreeId);
                $("#DISPLAYNO").textbox('setValue', node.level+1);
            },function () {
                $.messager.alert("提示","查询编码信息失败!");
            }, 'json');
        }
        $('#MODULEID').textbox({
            disabled: true
        });
        $('#PARENTID').textbox({
            disabled: true
        });
        $('#MENUID').textbox({
            disabled: true
        });
        $('#DISPLAYNO').textbox({
            disabled: true
        });
        $("#OPENMODULE").combobox({
            data: [['Y', '新窗口'], ['N', '主页面']],
            editable: false,
            valueField: 0,
            textField: 1,
            panelHeight: 'auto'
        });
        $("#MENUDESCRIPTION").textbox({
            multiline: true
        });
        $popWindow.find("a.btn").linkbutton();
    }

    //重新加载节点;
    function imitNode() {
        var treeObj = $.fn.zTree.getZTreeObj("menuTree");
        var sNode = treeObj.getSelectedNodes();
        if (sNode.length === 0) {
            sNode = treeObj.getNodes();
        }
        treeObj.reAsyncChildNodes(sNode[0], "refresh", true);
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
            $page.find("#menu").datagrid("load");
        });

        $("#search").click(function () {
            var str = $("#searchMenu").val();
            if (str != null && str !== "") {
                var treeObj = $.fn.zTree.getZTreeObj("menuTree");
                searchNodes = treeObj.getNodesByParamFuzzy("name", str, null);
                if (JSON.stringify(searchNodes) === "[]") {
                    $.messager.alert("提示", "没有搜索到数据！");
                } else {
                    if (nowSearchNum < searchNodes.length - 1) {
                        nowSearchNum = nowSearchNum + 1;
                    } else {
                        nowSearchNum = 0;
                    }

                    if (searchNodes[nowSearchNum].isParent) {
                        treeObj.expandNode(searchNodes[nowSearchNum], true);
                    } else {
                        treeObj.expandNode(searchNodes[nowSearchNum].getParentNode(), true);
                    }

                    $("#" + searchNodes[nowSearchNum].tId + "_a").click();
                }
            } else {
                $.messager.alert("提示", "请输入要搜索的关键词");
            }
        });

        /*
         * 清除查询条件
         */
        $search.on("click", "a.btn-default", function () {
            $search.find('form.form').form('clear');

        });

        $page.on("click", "#deleteMenu", function () {
            var target = -1;
            if ($(this).linkbutton("options").disabled) {
                return;
            }
            //禁用按钮
            $(this).linkbutton({disabled: true});
            var ids = [];
            var selections = $page.find("#menu").datagrid("getSelections");
            if ((selections.length === 0)) {
                $.messager.alert("提示", "请选择一个菜单项!");
                $("#deleteMenu").linkbutton({disabled: false});
            } else {
                selections.each(function (index) {
                    ids.push(selections[index]['MENUID']);
                });
                $.messager.confirm("提示", "确认删除？", function (sure) {
                    if (sure) {
                        var params = {};
                        ajaxLoading();
                        AjaxUtils.commonAjax('post', '/sam/Menu/delMenu?ids='+ids, params, function (data) {
                            ajaxLoadEnd();
                            $("#deleteMenu").linkbutton({disabled: false});
                            if (data.result === "0000") {
                                if (data.total === 0 && data.atotal === 0 && data.stotal === 0) {
                                    $.messager.alert('提示', '成功删除数据!');
                                    imitNode();
                                    $page.find("#menu").datagrid('load');
                                } else if (data.total > 0) {
                                    $.messager.alert('提示', '编号为' + data["pids"] + "的菜单有子菜单项，无法直接删除！");
                                    imitNode();
                                    $page.find("#menu").datagrid("load");
                                } else if (data.atotal > 0) {
                                    $.messager.alert('提示', "编号为" + data["aids"] + "的菜单有功能权限子节点，无法删除！");
                                    imitNode();
                                    $page.find("#menu").datagrid("load");
                                } else if (data.stotal > 0) {
                                    $.messager.alert('提示', "编号为" + data["spids"] + "已分配给人员或角色，无法删除！");
                                    imitNode();
                                    $page.find("#menu").datagrid("load");
                                } else {
                                    $.messager.alert('提示', '编号为' + data["pids"] + '的菜单有子菜单项，菜单编号为' + data["aids"] + "的菜单有功能权限子节点，无法删除！");
                                    imitNode();
                                    $page.find("#menu").datagrid("load");
                                }
                            } else {
                                $.messager.alert('提示', data.msg);
                            }
                        }, function () {
                            $.messager.alert("提示","删除数据失败!");
                        },'json');
                    } else {
                        $("#deleteMenu").linkbutton({disabled: false});
                        return false;
                    }
                });
            }
        });
        /*
         * 弹出添加窗口
         */
        $page.on("click", "#createMenu", function () {
            var treeObj = $.fn.zTree.getZTreeObj("menuTree");
            var node = treeObj.getSelectedNodes()[0];
            if (node != null && node.level === 5) {
                $.messager.alert("提示", "菜单树的深度不能超过5级！")
            } else {
                $page.find("#menu").datagrid("clearSelections");
                menu = null;
                menuId = -1;
                $("#win_content").show().window({
                    width: 950,
                    height: 390,
                    modal: true,
                    title: "新增菜单"
                });
                initPopWindow();
                initWindowEvent();
            }
        });

        /*
         * 弹出修改窗口
         */
        $page.on("click", "#updateMenu", function () {
            var selections = $page.find("#menu").datagrid('getSelections');
            if (selections.length > 1) {
                $.messager.alert('提示', '只能选择一个菜单项！');
            } else if (selections.length > 0) {
                menuId = selections[0]['MENUID'];
                menu = selections[0];
                $("#win_content").show().window({
                    width: 950,
                    height: 390,
                    modal: true,
                    title: "修改菜单"
                });
                initPopWindow();
                initWindowEvent();
            } else {
                $.messager.alert('提示', '请选择一个菜单项！');
            }

        });

    }

    /**
     * 初始化弹出窗口事件
     */
    function initWindowEvent() {
        $popWindow.on("click", "#global", function () {
            if ($(this).linkbutton("options").disabled) {
                return;
            }
            $(this).linkbutton({disabled: true});

            menu = getParams($popWindow);
            if (!$popWindow.find("input[name='MENUNAME']").val()) {
                $.messager.alert('提示', '请填写菜单名称！');
                $(this).linkbutton({disabled: false});
            } else {
                var flag = false;
                var menuName = $popWindow.find("input[name='MENUNAME']").val();
                var menuNewId = $popWindow.find("input[name='MENUID']").val();
                var treeObj = $.fn.zTree.getZTreeObj("menuTree");
                var sNode = treeObj.getSelectedNodes()[0];
                if (sNode.isParent) {
                    var cNodes = sNode.children;
                    if (cNodes) {
                        for (var i = 0; i < cNodes.length; i++) {
                            if (menuName === cNodes[i].name && cNodes[i].id !== menuNewId) {
                                flag = true;
                            }
                        }
                    }
                }
                if (flag) {
                    $.messager.alert('提示', '同级目录的名称不可重复！');
                    $(this).linkbutton({disabled: false});
                } else if (!$popWindow.find("input[name='OPENMODULE']").val()) {
                    $.messager.alert('提示', '请选择打开方式！');
                    $(this).linkbutton({disabled: false});
                }else if ($popWindow.find("input[name='MENUDESCRIPTION']").val().length>150) {
                    $.messager.alert('提示', '菜单说明不能超过150字！');
                    $(this).linkbutton({disabled: false});
                } else {
                    var params = {"flag": menuId, "menuStr": JSON.stringify(menu)};
                     ajaxLoading();
                        AjaxUtils.commonAjax('post','/sam/Menu/addMenu',params,function (data) {
                            ajaxLoadEnd();
                            $("#global").linkbutton({disabled: false});
                            if (data.result === "0000") {
                                sNode.isParent = true;
                                $.messager.alert('提示', '操作成功！');
                                $("#win_content").window("close");
                                imitNode();
                                $page.find("#menu").datagrid("load");
                            } else {
                                $.messager.alert('提示', data.msg);
                            }
                        },function () {
                            $.messager.alert("提示","新增或修改失败!");
                        },'json');
                }

            }
        });
        $popWindow.on("click", "#cancel", function () {
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
