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
        },
        "ztree-excheck": {
            deps: ["ztree-core"],
            exports: "ztree"
        }
    }
});
require(["load", "request", "jquery", 'ajaxUtils', 'crm', 'common-enum', 'easyui', 'ztree-core', 'ztree-exedit', 'backspace', "ztree-excheck"], function (load, request, $, AjaxUtils, CRM, ENUM) {
    load.loadEnd();
    var $page;
    var auth, flag;
    var clickedTreeId, root = "";
    var addStaaff=[],delStaff=[],parents = "",children = "",alreadyDisStaff=[],alreadyDisRole=[];
    var searchNodes, nowSearchNum = -1, nowSearch = "", roleNodes, nowRoleNum = -1, nowRole = "", nowMenuflag = 1;
    var m = [], modules = [];
    //m.push("","请选择模块");
    //modules.push(m);
    $page = $("<div></div>").appendTo($("#page_content"));

    $('#tabs').tabs({
        fit: true
    });

    $(document).find("#layout_content").layout();
    initAllTabsGrid();
    initTab1Event();
    initTab2Event();
    initTab3Event();
    detectZoom();

    function detectZoom() {
        var ratio = 0,
            screen = window.screen,
            ua = navigator.userAgent.toLowerCase();

        if (window.devicePixelRatio !== undefined) {
            ratio = window.devicePixelRatio;
        } else if (~ua.indexOf('msie')) {
            if (screen.deviceXDPI && screen.logicalXDPI) {
                ratio = screen.deviceXDPI / screen.logicalXDPI;
            }
        } else if (window.outerWidth !== undefined && window.innerWidth !== undefined) {
            ratio = window.outerWidth / window.innerWidth;
        }

        console.log(ratio);
    }

    /**
     * 初始化菜单树
     */
    function initMenuTree() {
        var zTreeObj;
        var token = AjaxUtils.getToken();
        var setting = {
            async: {
                dataType: "json",
                enable: true,
                url: "/sam/Auth/qryAuthTree?moduleId=" + root+"&access_token="+token,
                autoParam: ["id", "ELEMENTID"],
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
                onAsyncSuccess: afterAsync
            }
        };
        function zTreeOnClick(event, treeId, treeNode) {
            clickedTreeId = treeNode.id;
            flag = 1;
            imitdata();

            var parentNode = treeNode.getParentNode();
            parents=treeNode.id;
            while(parentNode != null)
            {
                parents=parents+","+parentNode.id;
                parentNode=parentNode.getParentNode();
            }

        }

        function filter(treeId, parentNode, childNodes) {
            if (!childNodes) {
                return null;
            }
            childNodes = childNodes['auths'];

            if (childNodes) {
                for (var i = 0, l = childNodes.length; i < l; i++) {
                    childNodes[i].id = childNodes[i].ELEMENTID;
                    childNodes[i].name = childNodes[i].ELEMENTNAME;
                    childNodes[i].pid = childNodes[i].SUPERELEMENTCODE;
                }
            }
            return childNodes;
        }

        function afterAsync(event, treeId, treeNode) {
            if (!treeNode) {
                zTreeObj.selectNode(zTreeObj.getNodes()[0]);
                zTreeObj.expandNode(zTreeObj.getNodes()[0], true);
            } else if (treeNode.id === clickedTreeId) {
                zTreeObj.selectNode(treeNode);
                if (treeNode.children.length === 0) {
                    treeNode.isParent = false;
                    zTreeObj.updateNode(treeNode);
                }
            } else if (treeNode.id !== clickedTreeId) {
                var newNode = zTreeObj.getNodeByParam("id", clickedTreeId);
                zTreeObj.selectNode(newNode);
            }
            imitdata();
        }

        zTreeObj = $.fn.zTree.init($("#menuTree"), setting, null);
    }

    //重新加载某个节点
    function imitNode() {
        var treeObj = $.fn.zTree.getZTreeObj("menuTree");
        var sNode = treeObj.getSelectedNodes();
        if (nowMenuflag === 1) {
            sNode[0].isParent = true;
            treeObj.reAsyncChildNodes(sNode[0], "refresh", false);
        } else {
            treeObj.reAsyncChildNodes(sNode[0].getParentNode(), "refresh", false);
        }
        treeObj.updateNode(sNode[0]);

    }

    //加载权限分配给角色信息
    function imitRoleTree() {
        var token = AjaxUtils.getToken();
        var treeObj;
        var setting = {
            async: {
                enable: true,
                url: "/sam/tsamrole/selectTSamRoleTr?sync=true&authId=" + clickedTreeId + "&moduleId=" + root + "&access_token=" + token,
                autoParam: ["id"]
            },
            check:{
                enable: true,
                chkStyle:"checkbox"
            },
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pId",
                    rootPId: 0
                }
            },
            view: {
                dblClickExpand: false,
                selectedMulti: false
            },
            // 回调函数
            callback: {
                onAsyncSuccess: afterAsync
            }
        };
        treeObj = $.fn.zTree.init($("#roleTree"), setting);

        function afterAsync() {
            treeObj.expandNode(treeObj.getNodes()[0], true);
            alreadyDisRole = [];
            var nodes = treeObj.getCheckedNodes();
            for(var i=0;i<nodes.length;i++){
                alreadyDisRole.push(nodes[i].id);
            }
            console.log(alreadyDisRole.toString());
        }
    }

    //加载权限分配人员信息
    function imitStaffData() {
        $("#leftData").datagrid({
            columns: [[
                {field: 'ck', checkbox: 'true', width: '5%'},
                {field: 'staffId', title: '账号ID', width: '40%'},
                {field: 'staffName', title: '人员名称', width: '50%'}
            ]],
            fitColumns: true,
            rownumbers: true,
            striped: true,
            nowrap: true,
            pagination: true,
            collapsible: true,
            singleSelect: false,
            height: 490,
            pageSize: 10,
            pageList: [10, 20, 50],
            loader: function (param, success) {
                var start = (param.page - 1) * param.rows + 1;
                var pageNum = param.rows;
                var params = {"startIndex": start, "pageNum": pageNum};

                AjaxUtils.commonAjax('post', '/sam/StaffInfo/qryStaffInfo', params, function (data) {
                    var dd = {"total": data.total, "rows": data.list};
                    success(dd);
                },function () {
                    $.messager.alert("提示","查询待分配人员失败!");
                }, 'json');
            }
        });

        //加载权限分配人员已分配信息
        $("#rightData").datagrid({
            columns: [[
                {field: 'ck', checkbox: 'true', width: '5%'},
                {field: 'staffId', title: '账号ID', width: '40%'},
                {field: 'staffName', title: '人员名称', width: '50%'}
            ]],
            fitColumns: true,
            rownumbers: true,
            striped: true,
            nowrap: true,
            pagination: false,
            collapsible: true,
            singleSelect: false,
            height: 490,
            loader: function (param, success) {
                var params = {"authId": clickedTreeId, "moduleId": root, "permitType": "1"};
                AjaxUtils.commonAjax('post', '/sam/tsampermit/queryEntityByAuthId', params, function (data) {
                    var d = [];
                    alreadyDisStaff = [];
                    for (var i = 0; i < data['resultMsg'].length; ++i) {
                        d.push($.extend({'staffId': data['resultMsg'][i]['entityId']}, {'staffName': data['resultMsg'][i]['staffName']}));
                        alreadyDisStaff.push(data['resultMsg'][i]['entityId']);
                    }
                    var dd = {"total": data.total, "rows": d};
                    success(dd);
                }, function () {
                    $.messager.alert("提示","查询已分配人员失败!");
                },'json');
            }
        });
    }

    function getFormData(formId) {
        var data = {};
        var t = $("#" + formId + "").serializeArray();
        $.each(t, function () {
            data[this.name] = this.value;
        });
        return data;
    }

    //加载数据
    function imitdata() {
        $("#delete").text("删除");
        var treeObj = $.fn.zTree.getZTreeObj("menuTree");
        var nowNode = treeObj.getNodesByParam("id", clickedTreeId)[0];
        $("#nowAuth").val(nowNode.name);
        //加载基本信息

        var treeNode = treeObj.getSelectedNodes();
        children = "";
        var result = "";
        result = getChildNodes(treeNode[0],result);
        children=treeNode[0].id+result;
        var params = {"clickedTreeId":clickedTreeId};
        ajaxLoading();
        AjaxUtils.commonAjax('post', '/sam/Auth/qryAuth', params, function (data) {
            //alert(JSON.stringify(data.data));
            if (data.result === "0000") {
                ajaxLoadEnd();
                auth = "";
                auth = data.data;
                console.log(data.data);
                $page.find("#basicData").form("clear");
                $page.find("#basicData").form("load", auth);
                $page.find("#ELEMENTURL").val(auth.ELEMENTURL);
                $page.find("#DESCRIPTION").val(auth.DESCRIPTION);
                if (auth["MENUFLAG"] === "1") {
                    nowMenuflag = 1;
                    $('#basicData div div input').textbox({disabled: true});
                    $(".forAuth").hide();
                    $("#new").show();
                } else {
                    nowMenuflag = 0;
                    $(".notMust").textbox({
                        disabled: false
                    });
                    $(".forAuth").show();
                    $("#new").hide();
                }
            } else {
                $.messager.alert("提示", data.msg);
            }
        }, function () {
            $.messager.alert("提示","查询权限信息失败!");
        },'json');

        //加载权限分配人员信息
        imitStaffData();

        //加载权限分配角色信息
        imitRoleTree();
    }

    function getParams($document) {
        var param = {};
        $document && $document.find("input").each(function () {
            var $item = $(this);
            param[$item.attr("name")] = $item.val();
        });
        return param;
    }

    function ajaxLoading() {
        $("<div class=\"datagrid-mask\"></div>").css({
            display: "block",
            width: "100%",
            height: $(window).height()
        }).appendTo($page);
        $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo($page).css({
            display: "block",
            left: ($(document.body).outerWidth(true) - 190) / 2,
            top: ($(window).height() - 45) / 2
        });
    }

    function ajaxLoadEnd() {
        $(".datagrid-mask").remove();
        $(".datagrid-mask-msg").remove();
    }

    /**
     * 初始化功能权限各个tab
     */
    function initAllTabsGrid() {
        var token = AjaxUtils.getToken();
        AjaxUtils.commonAjax('post', '/sam/Module/qryModule', {}, function (data) {
            for (var i = 0; i < data['modules'].length; ++i) {
                m = [];
                m.push(data['modules'][i]['MODULEID'], data['modules'][i]['MODULENAME']);
                modules.push(m);
            }
            $("#chooseMenu").combobox({
                data: modules,
                valueField: 0,
                textField: 1,
                panelHeight: 300,
                editable: false,
                onChange: function (newValue, oldValue) {
                    root = newValue;
                    clickedTreeId = "000" + root;
                    initMenuTree();
                },
                onLoadSuccess: function () {
                    var data = $("#chooseMenu").combobox("getData");
                    if (data.length > 0) {
                        $('#chooseMenu').combobox('select', data[0][0]);
                    }
                }
            });
        },function () {
            $.messager.alert("提示","查询模块信息失败!");
        }, 'json');


        $("#orgaId").combotree({
            multiple: true,
            checkbox: true,
            onlyLeafCheck: true,//只能叶子节点才能勾选
            url: "/sam/tsamorgainfo/selectSamOrgaTreeForCombotree?access_token=" + token,
            onBeforeSelect: function (node) {
                // $(this).tree("check", node.target);//控制点击文字时也能勾选
                // return false;
                //返回树对象
                var tree = $(this).tree;
                //选中的节点是否为叶子节点,如果不是叶子节点,清除选中
                var isLeaf = tree('isLeaf', node.target);
                if (!isLeaf) {
                    $("#orgaId").treegrid("unselect");
                }
            },
            onBeforeCheck: function (node, checked) {
                if (checked) {//如果是勾选操作，则把之前选中的节点清除（不勾选）
                    var nodes = $(this).tree("getChecked");
                    if (nodes.length > 0) {
                        for (var i = 0; i < nodes.length; i++) {
                            $(this).tree("uncheck", nodes[i].target);
                        }
                    }
                }
            },
            onLoadSuccess: function (node, data) {
                var cmm_code_id_value = "请选择";
                if (cmm_code_id_value != null && $.trim(cmm_code_id_value) != "") {
                    var comboObj = $("#orgaId");
                    comboObj.combotree("setValue", cmm_code_id_value);
                }
            }
        });

        $("#leftData").datagrid({
            columns: [[
                {field: 'ck', checkbox: 'true', width: '5%'},
                {field: 'staffId', title: '账号ID', width: '40%'},
                {field: 'staffName', title: '人员名称', width: '50%'}
            ]],
            pagination: false,
            height: 490
        });
        $("#rightData").datagrid({
            columns: [[
                {field: 'ck', checkbox: 'true', width: '5%'},
                {field: 'staffId', title: '账号ID', width: '40%'},
                {field: 'staffName', title: '人员名称', width: '50%'}
            ]],
            pagination: false,
            height: 490
        });

        imitRoleTree();
        $("#nowAuth").val("尚未选择模块");
        $(".forAuth").hide();

        $([
            "<div class='panel-search'>",
            "<form id='basicData' method='POST' class='form form-horizontal'>",
            "<div class='row cl'>",
            "<label class='form-label col-2'>权限编号：</label>",
            "<div class='formControls col-2'><input type='text' class='easyui-textbox' id='ELEMENTID' name='ELEMENTID' style='width:90%;height:30px' ><span style='color:red;padding-left:2px'>*</span></div>",
            "<label class='form-label col-2'>模块：</label>",
            "<div class='formControls col-2'><input type='text' class='easyui-textbox' id='MODULEID' name='MODULEID' style='width:90%;height:30px' ><span style='color:red;padding-left:2px'>*</span></div>",
            "<label class='form-label col-2'>父级权限编号：</label>",
            "<div class='formControls col-2'><input type='text' class='easyui-textbox' id='SUPERELEMENTCODE' name='SUPERELEMENTCODE' style='width:90%;height:30px' ><span style='color:red;padding-left:2px'>*</span></div>",
            "</div>",
            "<div class='row cl'>",
            "<label class='form-label col-2'>权限位置：</label>",
            "<div class='formControls col-2'><input type='text' class='easyui-textbox' id='ELEMENTCODE' name='ELEMENTCODE' style='width:90%;height:30px' ><span style='color:red;padding-left:2px'>*</span></div>",
            "<label class='form-label col-2'>菜单标记：</label>",
            "<div class='formControls col-2'><input type='text' class='easyui-textbox' id='MENUFLAG' name='MENUFLAG' style='width:90%;height:30px' ><span style='color:red;padding-left:2px'>*</span></div>",
            "<label class='form-label col-2'>权限名称：</label>",
            "<div class='formControls col-2'><input type='text' class='easyui-textbox notMust' maxlength='15' id='ELEMENTNAME' name='ELEMENTNAME' style='width:90%;height:30px' ><span style='color:red;padding-left:2px'>*</span></div>",
            "</div>",
            "<div class='row cl'>",
            "<label class='form-label col-2'>权限地址</label>" +
            "<div class='formControls col-8'><input  type='text' class='easyui-textbox notMust' data-options='multiline:true' id='ELEMENTURL'  name='ELEMENTURL' style='width:122.5%;height:55px' /></div>" +
            "</div>",
            "<div class='row cl'>",
            "<label class='form-label col-2'>权限说明</label>" +
            "<div class='formControls col-8'><input  type='text' class='easyui-textbox notMust' data-options='multiline:true' id='DESCRIPTION' name='DESCRIPTION' style='width:122.5%;height:55px' /></div>" +
            "</div>",
            "</form>",
            "</div>"
        ].join("")).appendTo($page);
        $([
            "<div class='mt-10 test-c'>",
            "<label class='form-label col-5'></label>",
            "<a href='javascript:void(0)' id='save' class='btn btn-green radius mt-l-20 forAuth' >" +
            "保存</a>",
            "<span>          </span>",
            "<a href='javascript:void(0)' id='delete' class='btn btn-secondary radius mt-l-20 forAuth' >" +
            "删除</a>",
            "</div>",
            "</div>"
        ].join("")).appendTo($page);
        $("#MENUFLAG").combobox({
            data: [['0', '功能'], ['1', '菜单']],
            valueField: 0,
            textField: 1,
            panelHeight: 'auto'
        });
        // $("#ELEMENTURL").textbox({
        //     multiline:true
        // });
        // $("#DESCRIPTION").textbox({
        //     multiline:true
        // });

        $page.find("a.btn").linkbutton();
    }

    function initTab1Event() {
        //下一个
        $("#search").click(function () {
            var str = $("#searchMenu").val();
            if (nowSearch !== str) {
                nowSearch = str;
                nowSearchNum = -1;
            }
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

        //新增
        $("#new").click(function () {
            nowMenuflag = 1;
            $("#delete").text("取消");
            $('#tabs').tabs('select', '基本信息');
            if (root !== "") {
                var treeObj = $.fn.zTree.getZTreeObj("menuTree");
                var node = treeObj.getSelectedNodes()[0];
                if ($page.find("input[name='MENUFLAG']").val() === "0") {
                    $.messager.alert("提示", "菜单标记为“功能”的项不能新增子节点")
                } else {
                    $(".forAuth").show();
                    flag = 0;
                    $(".notMust").textbox({
                        disabled: false
                    });
                    var params = {"clickedTreeId":clickedTreeId};

                    AjaxUtils.commonAjax('post', '/sam/Auth/qryChild', params, function (data) {
                        $("#ELEMENTID").textbox('setValue', data.newId);
                        $("#MODULEID").textbox('setValue', data.module);
                        $("#SUPERELEMENTCODE").textbox('setValue', clickedTreeId);
                        $("#ELEMENTCODE").textbox('setValue', data.newId);
                        $("#MENUFLAG").combobox('setValue', '0');
                        $("#ELEMENTNAME").textbox('setValue', '');
                        $("#ELEMENTURL").textbox('setValue', '');
                        $("#DESCRIPTION").textbox('setValue', '');

                    }, function () {
                        $.messager.alert("提示","查询权限编码信息失败!");
                    },'json')
                }
            } else {
                $.messager.alert("提示", "请先选择模块!");
            }
        });

        //删除
        $page.on("click", "#delete", function () {
            if ($("#delete").text() === "取消") {
                imitdata();
            } else {
                if ($(this).linkbutton("options").disabled) {
                    return;
                }
                //禁用按钮
                $(this).linkbutton({disabled: true});


                if ($page.find("input[name='MENUFLAG']").val() === "1") {
                    $("#delete").linkbutton({disabled: false});
                    $.messager.alert("提示", "错误操作！请前往“菜单项维护”删除菜单项!");
                } else {
                    $.messager.confirm("提示", "确认删除？", function (sure) {
                        if (sure) {
                            var params = {"clickedTreeId":clickedTreeId};
                            ajaxLoading();
                            AjaxUtils.commonAjax('post', '/sam/Auth/delAuth', params, function (data) {
                                ajaxLoadEnd();
                                $("#delete").linkbutton({disabled: false});
                                if (data.result === "0000") {
                                    $.messager.alert('提示', '成功删除数据!');
                                    var treeObj = $.fn.zTree.getZTreeObj("menuTree");
                                    var node = treeObj.getSelectedNodes();
                                    var pNode = node[0].getParentNode();
                                    clickedTreeId = pNode.id;
                                    treeObj.selectNode(pNode);
                                    nowMenuflag = 1;
                                    imitNode();
                                } else {
                                    $.messager.alert('提示', data.msg);
                                }
                            }, function () {
                                $.messager.alert("提示","删除数据失败!");
                            },'json');
                        } else {
                            $("#delete").linkbutton({disabled: false});
                            return false;
                        }
                    });
                }
            }
        });


        //保存
        $page.on("click", "#save", function () {
            if ($(this).linkbutton("options").disabled) {
                return;
            }
            $(this).linkbutton({disabled: true});

            auth = getParams($page);
            if (root === "") {
                $.messager.alert("提示", "请先选择模块!");
            } else if (!$page.find("input[name='MENUFLAG']").val()) {
                $.messager.alert("提示", "请选择菜单标记!");
                $("#save").linkbutton({disabled: false});
            } else if ($page.find("input[name='MENUFLAG']").val() === "1") {
                $.messager.alert("提示", "错误操作！请前往“菜单项维护”修改或新增菜单内容!");
                $("#save").linkbutton({disabled: false});
            } else if (!$page.find("input[name='ELEMENTNAME']").val()) {
                $.messager.alert("提示", "请填写权限名称!");
                $("#save").linkbutton({disabled: false});
            }else if($page.find("input[name='ELEMENTNAME']").val().length>20){
                $.messager.alert("提示", "权限名称长度不能超过20！");
                $("#save").linkbutton({disabled: false});
            }else if($page.find("input[name='DESCRIPTION']").val().length>30){
                $.messager.alert("提示", "权限说明长度不能超过30！");
                $("#save").linkbutton({disabled: false});
            }  else {
                var params = {"flag": flag, "authStr": JSON.stringify(auth)};
                ajaxLoading();
                AjaxUtils.commonAjax('post', '/sam/Auth/addAuth', params, function (data) {
                    $("#save").linkbutton({disabled: false});
                    $("#delete").linkbutton({disabled: false});
                    ajaxLoadEnd();
                    if (data.result === "0000") {
                        $.messager.alert('提示', '保存数据成功!');
                        imitNode();
                    } else {
                        $.messager.alert('提示', data.msg);
                    }
                }, function () {
                    $.messager.alert("提示","保存数据失败!");
                },'json');
            }
        });
    }



    function getChildNodes(treeNode,result){
        if (treeNode.isParent) {
            var childrenNodes = treeNode.children;
            if (childrenNodes) {
                for (var i = 0; i < childrenNodes.length; i++) {
                    result += ',' + childrenNodes[i].id;
                    result = getChildNodes(childrenNodes[i], result);
                }
            }
        }
        return result;
    }

    function initTab2Event() {
        //完成分配
        $("#saveStaff").click(function () {
            var entityIds = [];
            var data = $("#rightData").datagrid('getRows');

            for (var i = 0; i < data.length; i++) {
                entityIds.push(data[i]["staffId"]);
            }
            if(entityIds.length !==0 && alreadyDisRole.length !== 0) {
                for (var k = 0; k < entityIds.length; k++) {
                    for (var l = 0; l < alreadyDisStaff.length; l++) {
                        if (entityIds[k] === alreadyDisStaff[l]) {
                            entityIds.splice(k, 1);
                            alreadyDisStaff.splice(l, 1);
                            k--;
                            l--;
                        }
                    }
                }
            }
            var params = {
                "moduleId": root,
                "permitType": "1",
                "subAuthIds":children,
                "parentAuthIds":parents,
                "addEntityIds":entityIds.toString(),
                "delEntityIds":alreadyDisStaff.toString()
            };

            console.log(JSON.stringify(params));
            AjaxUtils.commonAjax('post', '/sam/tsampermit/updateAuthPermitEntity', params, function (data) {
                if (data.resultVal === "1") {
                    $.messager.alert("提示", "权限分配成功！");
                } else {
                    $.messager.alert("提示", data.resultMsg);
                }
                imitStaffData();
            },function () {
                $.messager.alert("提示","权限分配失败!");
            }, 'json');
        });


        //取消分配
        $("#ccSaveStaff").click(function () {
            imitStaffData();
        });

        //清空人员查询条件
        $("#ccSearchStaff").click(function () {
            $("#searchLeft").form('clear');
            $("#orgaId").combotree("setValue", "请选择");
            $("#staffIdStatus").val("00");
        });

        //查询未分配人员
        $("#searchStaff").click(function () {
            if ($("input[name='orgaId']").val() === "请选择") {
                $("input[name='orgaId']").val("");
            }

            if (root === "") {
                $.messager.alert("提示", "请先选择模块!");
            } else {
                $("#leftData").datagrid({
                    columns: [[
                        {field: 'ck', checkbox: 'true', width: '5%'},
                        {field: 'staffId', title: '账号ID', width: '40%'},
                        {field: 'staffName', title: '人员名称', width: '50%'}
                    ]],
                    fitColumns: true,
                    rownumbers: true,
                    striped: true,
                    nowrap: true,
                    pagination: true,
                    collapsible: true,
                    singleSelect: false,
                    height: 490,
                    pageSize: 10,
                    pageList: [10, 20, 50],
                    loader: function (param, success) {
                        var start = (param.page - 1) * param.rows + 1;
                        var pageNum = param.rows;
                        var params = $.extend({"startIndex": start, "pageNum": pageNum}, getFormData("searchLeft"));
                        AjaxUtils.commonAjax('post', '/sam/StaffInfo/qryStaffInfo', params, function (data) {
                            var dd = {"total": data.total, "rows": data.list};
                            success(dd);
                        }, function () {
                            $.messager.alert("提示","查询待分配人员失败!");
                        },'json');
                    }
                });
            }
        });

        //分配
        $("#toRight").click(function () {
            var selections = $("#leftData").datagrid('getSelections');
            if (selections.length === 0) {
                $.messager.alert("提示", "请选择要分配的人员")
            } else {
                var already = [];
                var isAlready = 0;
                var rightData = $("#rightData").datagrid('getRows');
                for (var i = 0, l = selections.length; i < l; i++) {
                    for (var j = 0, isAlready = 0; j < rightData.length; j++) {
                        if (rightData[j]["staffId"] === selections[i]["staffId"]) {
                            already.push(rightData[j]["staffId"]);
                            isAlready = 1;
                        }
                    }
                    if (isAlready === 0) {
                        $('#rightData').datagrid('insertRow', {
                            index: i,
                            row: selections[i]
                        });
                        var index = $('#leftData').datagrid('getRowIndex', selections[i]);
                        $('#leftData').datagrid('deleteRow', index);
                    }
                }
                if (already.length > 0) {
                    $.messager.alert("提示", "账号ID为" + already + "的人员已经分配过该权限");
                }
            }
        });

        //取消分配
        $("#toLeft").click(function () {
            var selections = $("#rightData").datagrid('getSelections');
            if (selections.length === 0) {
                $.messager.alert("提示", "请选择要取消分配的人员")
            } else {
                for (var i = 0, l = selections.length; i < l; i++) {
                    $('#leftData').datagrid('insertRow', {
                        index: i,
                        row: selections[i]
                    });
                    var index = $('#rightData').datagrid('getRowIndex', selections[i]);
                    $('#rightData').datagrid('deleteRow', index);
                }
            }
        });
    }

    function initTab3Event() {

        //搜索角色
        $("#searchRole").click(function () {
            var str = $("#searchRoleData").val();
            if (nowRole !== str) {
                nowRole = str;
                nowRoleNum = -1;
            }
            if (str != null && str !== "") {
                var treeObj = $.fn.zTree.getZTreeObj("roleTree");
                roleNodes = treeObj.getNodesByParamFuzzy("name", str, null);
                if (JSON.stringify(roleNodes) === "[]") {
                    $.messager.alert("提示", "没有搜索到数据！");
                } else {
                    if (nowRoleNum < roleNodes.length - 1) {
                        nowRoleNum = nowRoleNum + 1;
                    } else {
                        nowRoleNum = 0;
                    }

                    if (roleNodes[nowRoleNum].isParent) {
                        treeObj.expandNode(roleNodes[nowRoleNum], true);
                    } else {
                        treeObj.expandNode(roleNodes[nowRoleNum].getParentNode(), true);
                    }

                    $("#" + roleNodes[nowRoleNum].tId + "_a").click();
                }
            } else {
                $.messager.alert("提示", "请输入要搜索的关键词");
            }
        });

        //完成分配
        $("#saveRole").click(function () {
            if (root === "") {
                $.messager.alert("提示", "请先选择模块！");
            } else {
                var entityIds = [];
                var treeObj = $.fn.zTree.getZTreeObj("roleTree");
                var data = treeObj.getCheckedNodes();
                for (var i = 0; i < data.length; i++) {
                    entityIds.push(data[i].id);
                }
                if(entityIds.length !==0 && alreadyDisRole.length !== 0) {
                    for (var k = 0; k < entityIds.length; k++) {
                        for (var l = 0; l < alreadyDisRole.length; l++) {
                            if (entityIds[k] === alreadyDisRole[l]) {
                                entityIds.splice(k, 1);
                                alreadyDisRole.splice(l, 1);
                                k--;
                                l--;
                            }
                        }
                    }
                }
                var params = {
                    "moduleId": root,
                    "permitType": "2",
                    "subAuthIds":children,
                    "parentAuthIds":parents,
                    "addEntityIds":entityIds.toString(),
                    "delEntityIds":alreadyDisRole.toString()
                };
                console.log(JSON.stringify(params));
                AjaxUtils.commonAjax('post', '/sam/tsampermit/updateAuthPermitEntity', params, function (data) {
                    if (data.resultVal === "1") {
                        $.messager.alert("提示", "权限分配成功！");
                    } else {
                        $.messager.alert("提示", data.resultMsg);
                    }
                    imitRoleTree();
                },function () {
                    $.messager.alert("提示","权限分配失败!");
                }, 'json');
            }
        });


        //取消分配
        $("#ccSaveRole").click(function () {
            imitRoleTree();
        });
    }

});
