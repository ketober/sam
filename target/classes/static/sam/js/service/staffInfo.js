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
require(["load","request", "jquery",'ajaxUtils', 'crm','common-enum',  'easyui','ztree-core', 'ztree-exedit','backspace',"ztree-excheck"], function (load,request, $, AjaxUtils,CRM,ENUM) {
    load.loadEnd();
    var $page, $search;  //$head,, $service
    var $popWindow,$psdWindow;
    var staffInfo,staffId,orgaCode,psdStaffId;
    var cmm_code_id;
    var token = AjaxUtils.getToken();

    var statePrioritys=CRM.getEnum("STAFF_STATE@SAM");//人员状态
    var idStatusPrioritys=CRM.getEnum("STAFF_ID_STATUS@SAM");//账号状态
    var channelIdPrioritys=CRM.getEnum("CHANNEL_ID@SAM");//所属渠道
    var postIdPrioritys=CRM.getEnum("POST_ID@SAM");//职位
    // var prsnChnlTypePrioritys=CRM.getEnum("PRSN_CHNL_TYPE_CD@SAM");//人员类型
    $page = $("<div></div>").appendTo($("#page_content"));

    $(document).find("#layout_content").layout();
    initSearchGrid();
    initAccountGrid();
    initGlobalEvent();
    initOrgainfoTree();

    /**
     * 初始化资源树
     */
    function initOrgainfoTree(){
        var setting = {
            async : {
                enable : true, // 设置 zTree是否开启异步加载模式
                // type: "post",
                // contentType: "application/json",
                // headers: {
                //     "Authorization":"Bearer " + token
                // },
                url: "/sam/tsamorgainfo/selectSamOrgaTree?access_token="+token,
                // url: "/tsamorgainfo/selectSamOrgaTree",
                autoParam : [ "id" ]	// 异步加载时自动提交父节点属性的参数,假设父节点 node = {id:1, name:"test"}，异步加载时，提交参数 zId=1
            },
            data:{ // 必须使用data
                simpleData : {
                    enable : true,
                    idKey : "id", // id编号命名 默认
                    pIdKey : "pId", // 父id编号命名 默认
                    rootPId : 0	// 用于修正根节点父节点数据，即 pIdKey 指定的属性值
                }
            },
            // 回调函数
            callback : {
                onClick : function(event, treeId, treeNode, clickFlag) {
                    // 判断是否父节点
                    if(!treeNode.isParent){
                        orgaCode = treeNode.id;
                    }else{
                        orgaCode=treeNode.pId;
                    }
                    // alert(treeNode.id);
                    $page.find("#staff").datagrid('reload');
                },
                //捕获异步加载出现异常错误的事件回调函数 和 成功的回调函数
                onAsyncError : zTreeOnAsyncError,
                onAsyncSuccess : zTreeOnAsyncSuccess
            }
        };

        // 加载错误提示
        function zTreeOnAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
            alert("加载错误：" + XMLHttpRequest);
        };

        function zTreeOnAsyncSuccess(event, treeId, treeNode, msg){
            var treeObj = $.fn.zTree.getZTreeObj(treeId);
            var nodes = treeObj.getNodes();
            if (nodes.length>0) {
                for(var i=0;i<nodes.length;i++){
                    if(nodes[i].pId == "0"){
                        treeObj.expandNode(nodes[i], true, false, false);//默认展开第一级节点
                    }
                }
            }
        };


        // 过滤函数
        function filter(treeId, parentNode, childNodes) {
            if (!childNodes)
                return null;
            for ( var i = 0, l = childNodes.length; i < l; i++) {
                childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
            }
            return childNodes;
        }
        $.fn.zTree.init( $("#menuTree"), setting);
    }

    /**
     * 初始化查询条件
     */
    function initSearchGrid() {
        $search = $([
            "<div class='panel-search'>",
            "<form class='form form-horizontal'>",
            "<div class='row cl'>"+
            "<label class='form-label col-2'>账号ID：</label>",
            "<div class='formControls col-2'><input type='text' name='staffId' class='easyui-textbox'  style='width:90%;height:30px' ></div>",
            "<label class='form-label col-2'>账号区间：</label>",
            "<div class='formControls col-2'><input type='text' name='staffIdMin' class='easyui-textbox' style='width:42%;height:30px'> - <input type='text' name='staffIdMax' class='easyui-textbox' style='width:42%;height:30px'></div>",
            "<label class='form-label col-2'>人员姓名：</label>",
            "<div class='formControls col-2'><input type='text' name='staffName' class='easyui-textbox'  style='width:90%;height:30px' ></div>",
            "</div>",
            "<div class='row cl'>"+
            "<label class='form-label col-2'>手机号码：</label>",
            "<div class='formControls col-2'><input type='text' name='mobilePhone' class='easyui-textbox' style='width:90%;height:30px'></div>",
            // "<label class='form-label col-2'>组织机构：</label>",
            // "<div class='formControls col-2'><input type='text' name='orgaId' class='easyui-textbox'  style='width:90%;height:30px' ></div>",
            "<label class='form-label col-2'>所属渠道：</label>",
            "<div class='formControls col-2'><select class='easyui-combobox' id='channelId1' name='channelId' data-options='panelHeight:\"auto\"' style='width:90%;height:30px'><option value='00'>选择类型</option><option value='01'>融合客服</option><option value='02'>众包</option></select></div>",
            "<label class='form-label col-2'>岗位：</label>"+
            "<div class='formControls col-2'><select class='easyui-combobox' id='postId1' name='postId' data-options='panelHeight:\"auto\"' style='width:90%;height:30px'><option value='00'>选择类型</option><option value='01'>组长</option><option value='02'>职员</option></select></div>",
            "</div>",
            "<div class='row cl'>"+
            "<label class='form-label col-2'>人员状态：</label>",
            "<div class='formControls col-2'><select class='easyui-combobox' id='staffState1' name='staffState' data-options='panelHeight:\"auto\"' style='width:90%;height:30px'><option value='00'>选择类型</option><option value='0'>离职</option><option value='1'>在职</option></select></div>",
            "<label class='form-label col-2'>账号状态：</label>",
            "<div class='formControls col-2'><select class='easyui-combobox' id='staffIdStatus' name='staffIdStatus' data-options='panelHeight:\"auto\"' style='width:90%;height:30px'><option value='00'>选择类型</option><option value='01'>正常</option><option value='02'>停用</option><option value='03'>作废</option><option value='04'>锁定</option><option value='05'>解锁</option><option value='06'>失效</option><option value='07'>未启用</option></select></div>",
            // "<label class='form-label col-2'>岗位：</label>"+
            // "<div class='formControls col-2'><select class='easyui-combobox' id='postId1' name='postId' data-options='panelHeight:\"auto\"' style='width:90%;height:30px'><option value='00'>选择类型</option><option value='01'>组长</option><option value='02'>职员</option></select></div>",
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
         * enter键触发查询
         */
        $search.find("input.easyui-textbox").textbox({
            inputEvents: $.extend({},$.fn.textbox.defaults.inputEvents,{
                keyup: function(event){
                    if(event.keyCode == 13) {
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

        $document && $document.find("input").each(function() {
            var $item  = $(this);
            param[$item.attr("name")] = $item.val();
        });
        $document && $document.find("select").each(function() {
            var $item  = $(this);
            param[$item.attr("name")] = $item.val();
        });
        return param;
    }
    function initAccountGrid(){
        //var $account=
        $([
            "<div class='cl'>",
            "<div class='panel-tool-box cl' >",
            "<div class='fl text-bold'>人员信息维护</div>",
            "<div class='fr'>",
            "<a id='resetPassWord'  href='javascript:void(0)'  class='btn btn-secondary radius mt-l-20'>" +
            "<i class='iconfont iconfont-edit'></i>密码重置</a>",
            "<a id='updatePassWord'  href='javascript:void(0)'  class='btn btn-secondary radius mt-l-20'>" +
            "<i class='iconfont iconfont-edit'></i>修改密码</a>",
            "<a id='createAccount'  href='javascript:void(0)'  class='btn btn-secondary radius mt-l-20'>" +
            "<i class='iconfont iconfont-add'></i>新增</a>",
            // "<a id='updateAccount'  href='javascript:void(0)'  class='btn btn-secondary radius mt-l-20'>" +
            // "<i class='iconfont iconfont-edit'></i>修改</a>",
            "<a id='deleteAccount'  href='javascript:void(0)'  class='btn btn-secondary radius mt-l-20'>" +
            "<i class='iconfont iconfont-del2'></i>删除</a>",
            "</div>",
            "</div>",
            "</div>",
            "<table id='staff' class='easyui-datagrid'  style='width:98%;height:245px;'</table>"
        ].join("")).appendTo($page);
        $page.find("#staff").datagrid({
            columns:[[
                {field: 'CK', checkbox:'true', width: '3%'},
                {field: 'staffId', title: '账号ID', width: '10%'},
                {field: 'staffName', title: '人员姓名', width: '13%'},
                {field: 'staffState', title: '人员状态', width: '8%',
                    formatter:function(value,row,index){
                        return statePrioritys[value];
                    }},
                {field: 'orgaName', title: '组织机构', width: '13%'},
                {field: 'mobilePhone', title: '手机号码', width: '12%'},
                {field: 'channelId', title: '所属渠道', width: '10%',
                    formatter:function(value,row,index){
                        return channelIdPrioritys[value];
                    }},
                {field: 'staffIdStatus', title: '账号状态', width: '8%',
                    formatter:function(value,row,index){
                        return idStatusPrioritys[value];
                    }},
                {field: 'postId', title: '岗位', width: '8%',
                    formatter:function(value,row,index){
                        return postIdPrioritys[value];
                    }},
                {field: 'opera', title: '操作', width: '12%',formatter: rowformater},
            ]],
            fitColumns:true,
            rownumbers: true,
            striped:true,
            nowrap:true,
            pagination: true,
            collapsible:true,
            singleSelect:false,
            height:420,
            pageSize:10,
            pageList: [5, 10, 15, 20],
            loader: function (param, success) {
                var start = (param.page - 1) * param.rows+1;
                var pageNum = param.rows;
                var params = $.extend({"startIndex":start, "pageNum":pageNum,"orgaCode":orgaCode}, getParams($search));
                AjaxUtils.commonAjax('post','/sam/StaffInfo/qryStaffInfo',params,function (data) {
                    $search.find("a.btn-green").linkbutton({disabled:false});
                    var dd={"total":data.total,"rows":data.list};
                    success(dd);
                    $("#staff").datagrid('doCellTip',{'max-width':'400px'});
                },function () {
                    $.messager.alert("提示","查询人员信息失败!");
                },'json');
            }
        });
        function rowformater(value, row, index) {
            var staffId = row.staffId;
            // return  "<a title='查看' onclick='initWinContent();initUpdateWindow(\""+staffId+"\");initUpdateWindowEvent();'>查看</a>&nbsp;&nbsp;&nbsp;<a title='修改' onclick='addTabs(\""+staffId+"\");'>修改</a>";
            return  "<a title='修改' onclick='initWinContent();initUpdateWindow(\""+staffId+"\");'>修改</a>";
            // return  "<a onclick='addTabs(\""+staffId+"\");'>修改</a>";
        }
        $page.find("a.btn").linkbutton();
    }
    function initGlobalEvent(){
        /*
        * 查询事件
        */
        $search.on("click", "a.btn-green", function() {
            if($(this).linkbutton("options").disabled){
                return;
            }
            //禁用按钮
            $(this).linkbutton({disabled:true});
            $page.find("#staff").datagrid("load");
        });

        /*
         * 清除查询条件
         */
        $search.on("click", "a.btn-default", function() {
            $search.find('form.form').form('clear');
            $("#staffState1").val("00");
            $("#staffIdStatus").val("00");
            $("#channelId1").val("00");
            $("#postId1").val("00");
            $page.find("#staff").datagrid("load");
        });

        $page.on("click", "#deleteAccount", function () {
            if($(this).linkbutton("options").disabled){
                return;
            }
            //禁用按钮
            $(this).linkbutton({disabled:true});

            var selections=$page.find("#staff").datagrid("getSelections");

            if(selections.length>0){
                var ids=[];
                selections.each(function(index){
                    ids.push(selections[index]['staffId']);
                });
                $.messager.confirm("提示","确认删除？",function(sure) {
                    if (sure) {
                        var url='/sam/StaffInfo/deleteStaffInfo?ids='+ ids;
                        AjaxUtils.commonAjax('post',url,null,function (data) {
                            $("#deleteAccount").linkbutton({disabled: false});
                            $page.find("#staff").datagrid('load');
                        },function f() {
                            $.messager.alert("提示","删除人员失败!");
                        },'json');
                    } else {
                        return false;
                    }
                });
            }else{
                $.messager.alert('提示','请选择要删除的数据!');
                $(this).linkbutton({disabled:false});
            }
        });
        /*
         * 弹出添加窗口
         */
        $page.on("click", "#createAccount", function () {
            $page.find("#staff").datagrid("clearSelections");
            staffInfo = null;
            $("#win_content").show().window({
                width:850,
                height:500,
                modal:true,
                title:"新增人员信息"
            });
            initPopWindow();
            initWindowEvent();
        });
        //重置密码
        $page.on("click", "#resetPassWord", function () {
            if($(this).linkbutton("options").disabled){
                return;
            }
            //禁用按钮
            $(this).linkbutton({disabled:true});

            var selections=$page.find("#staff").datagrid("getSelections");

            if(selections.length>0){
                var ids=[];
                selections.each(function(index){
                    ids.push(selections[index]['staffId']);
                });
                $.messager.confirm("提示","确认重置密码？",function(sure) {
                    if (sure) {
                        var url='/sam/tsampassword/resetPassWord?ids='+ ids;
                        AjaxUtils.commonAjax('post',url,null,function (data) {
                            if (data.resultVal === "1") {
                                $.messager.alert('提示', '操作成功！');
                                $("#resetPassWord").linkbutton({disabled: false});
                                $page.find("#staff").datagrid('load');
                            } else {
                                $.messager.alert('提示', data.resultMsg);
                            }
                        },function () {
                            $.messager.alert("提示","重置密码失败!");
                        },'json');
                    } else {
                        return false;
                    }
                });
            }else{
                $.messager.alert('提示','请选择要重置的数据!');
                $(this).linkbutton({disabled:false});
            }
        });
        /*
         * 弹出修改密码窗口
         */
        $page.on("click", "#updatePassWord", function () {
            var selections=$page.find("#staff").datagrid('getSelections');
            if(selections.length>1){
                $.messager.alert('提示','只能选择一个账号！');
            }else if(selections.length>0){
                psdStaffId=selections[0]['staffId'];
                $("#win_content").show().window({
                    width:850,
                    height:500,
                    modal:true,
                    title:"修改密码"
                });
                initPsdWindow();
                initPsdEvent();
            }
            else{
                $.messager.alert('提示','请选择一项服务！');
            }
        });
    }
    /**
     * 初始化新增弹出窗口
     */
    function initPopWindow() {
        $("#win_content").empty();
        $popWindow = $("<div></div>").appendTo($("#win_content"));

        //账号配置表单
        var $winContent =
        $([
            "<div class='panel-tool-box cl' >",
            "<div class='fl text-bold'>人员基本信息</div>",
            "</div>",
            "<div class='panel-search'>",
            "<form id='createStaffInfo' method='POST' class='form form-horizontal'>",
            "<div class='row cl'>",
            "<label class='form-label col-2'>账号ID</label>"+
            "<div class='formControls col-2'><input  type='text' name='staffId' class='easyui-textbox'  style='width:90%;height:30px' /><span style='color:red;padding-left:2px'>*</span></div>" +
            "<label class='form-label col-2'>人员姓名</label>"+
            "<div class='formControls col-2'><input  type='text' id='staffName' name='staffName' class='easyui-textbox' style='width:90%;height:30px' /><span style='color:red;padding-left:2px'>*</span></div>" +
            "<label class='form-label col-2'>入职时间</label>"+
            "<div class='formControls col-2'><input type='text' id='joinDate' name='joinDate' class='easyui-datetimebox' style='width:90%;height:30px'></div>",
            "</div>",
            "<div class='row cl'>",
            "<label class='form-label col-2'>人员状态：</label>",
            "<div class='formControls col-2'><select class='easyui-combobox' id='staffState' name='staffState' data-options='panelHeight:\"auto\"' style='width:90%;height:30px'><option value='1'>在职</option><option value='0'>离职</option></select></div>",
            "<label class='form-label col-2'>组织机构</label>"+
            "<div class='formControls col-2'><input  type='text' id='orgaId' name='orgaId' class='easyui-combotree'  style='width:90%;height:30px' /><span style='color:red;padding-left:2px'>*</span></div>" +
            "<label class='form-label col-2'>移动电话</label>"+
            "<div class='formControls col-2'><input  type='text' id='mobilePhone' name='mobilePhone' class='easyui-textbox' style='width:90%;height:30px' /><span style='color:red;padding-left:2px'>*</span></div>" +
            "</div>",
            "<div class='row cl'>",
            "<label class='form-label col-2'>Email地址</label>"+
            "<div class='formControls col-2'><input  type='text' id='emailAddress' name='emailAddress'  class='easyui-textbox' style='width:90%;height:30px'/><span style='color:red;padding-left:2px'>*</span></div>"+
            "<label class='form-label col-2'>所属系统</label>"+
            "<div class='formControls col-2'><input  type='text' id='default_service_type' name='default_service_type'  class='easyui-textbox' style='width:90%;height:30px'/><span style='color:red;padding-left:2px'>*</span></div>"+
            "<label class='form-label col-2'>身份证号</label>"+
            "<div class='formControls col-2'><input  type='text' id='id_card_no' name='id_card_no'  class='easyui-textbox'  style='width:90%;height:30px' /><span style='color:red;padding-left:2px'>*</span></div>" +
            "</div>",
            "<div class='row cl'>",
            "<label class='form-label col-2'>所属渠道</label>"+
            "<div class='formControls col-2'><select class='easyui-combobox' id='channelId' name='channelId' data-options='panelHeight:\"auto\"' style='width:90%;height:30px'><option value='00'>请选择</option><option value='01'>融合客服</option><option value='02'>众包</option></select></div>",
            "<label class='form-label col-2'>人员类型</label>"+
            "<div class='formControls col-2'><select class='easyui-combobox' id='prsn_chnl_type_cd' name='prsn_chnl_type_cd' data-options='panelHeight:\"auto\"' style='width:90%;height:30px'><option value='00'>请选择</option><option value='0'>自有</option><option value='1'>外包</option><option value='3'>劳务派遣</option><option value='2'>其他</option></select></div>",
            "<label class='form-label col-2'>岗位</label>"+
            "<div class='formControls col-2'><select class='easyui-combobox' id='postId' name='postId' data-options='panelHeight:\"auto\"' style='width:90%;height:30px'><option value='00'>请选择</option><option value='01'>组长</option><option value='02'>职员</option></select></div>",
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

        $winContent.find("input.easyui-datetimebox").datetimebox({
            editable: false
        });
        //修改时加载已有数据
        if(staffInfo!=null){
            $popWindow.find("#createStaffInfo").form("load",staffInfo);

        }
        $("#orgaId").combotree({
            multiple: true,
            checkbox : true,
            onlyLeafCheck : true,//只能叶子节点才能勾选
            url : "/sam/tsamorgainfo/selectSamOrgaTreeForCombotree?access_token="+token,
            onBeforeSelect : function(node){
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
            onBeforeCheck : function(node, checked){
                if(checked){//如果是勾选操作，则把之前选中的节点清除（不勾选）
                    var nodes = $(this).tree("getChecked");
                    if(nodes.length > 0){
                        for(var i=0; i<nodes.length; i++){
                            $(this).tree("uncheck", nodes[i].target);
                        }
                    }
                }
            },
            onLoadSuccess : function(node, data){
                var valueOrgi = "请选择";
                if(valueOrgi != null && $.trim(valueOrgi) != ""){
                    var comboObj = $("#orgaId");
                    comboObj.combotree("setValue", valueOrgi);
                }
                var t = $("#orgaId").combotree('tree');
                t.tree('collapseAll');
                var childObj = t.tree('getChildren');
                var node = t.tree('find', childObj[0].id);
                t.tree('expand', node.target);
            }
        });
        $("#staffState").combobox({
            data:[['0','离职'],['1','在职']],
            valueField: 0,
            textField: 1,
            panelHeight:'auto'
        });
        $("#channelId").combobox({
            data:[['00','请选择'],['01','融合客服'],['02','众包']],
            valueField: 0,
            textField: 1,
            panelHeight:'auto'
        });
        $("#postId").combobox({
            data:[['00','请选择'],['01','组长'],['02','职员']],
            valueField: 0,
            textField: 1,
            panelHeight:'auto'
        });
        $("#prsn_chnl_type_cd").combobox({
            data:[['00','请选择'],['0','自有'],['1','外包'],['3','劳务派遣'],['2','其他']],
            valueField: 0,
            textField: 1,
            panelHeight:'auto'
        });
        $("#orgaId3").combotree("loadData", [{
            text: 'Item1',
            state: 'closed',
            children: [{
                text: 'Item11'
            }, {
                text: 'Item12'
            }]
        }, {
            text: 'Item2'
        }]);
        $("#remark1").textbox({
            multiline:true
        });
        $popWindow.find("a.btn").linkbutton();
    }

    /**
     * 初始化新增弹出窗口事件
     */
    function initWindowEvent(){
        $popWindow.on("click", "#global", function() {
            // if($(this).hasClass("disabled")){
            //     return;
            // }
            // //关闭按钮，防止多次提交
            // $(this).addClass("disabled");
            if($(this).linkbutton("options").disabled){
                return;
            }
            //禁用按钮
            $(this).linkbutton({disabled:true});
            var staffId=$popWindow.find("input[name='staffId']").val().trim();
            var staffName=$popWindow.find("input[name='staffName']").val().trim();
            var staffState=$popWindow.find("select[id='staffState']").val().trim();
            var orgaId=$popWindow.find("input[name='orgaId']").val().trim();
            var mobilePhone=$popWindow.find("input[name='mobilePhone']").val().trim();
            var default_service_type=$popWindow.find("input[name='default_service_type']").val().trim();
            var id_card_no=$popWindow.find("input[name='id_card_no']").val().trim();
            var emailAddress=$popWindow.find("input[name='emailAddress']").val().trim();
            if(!staffId) {
                $.messager.alert('提示', '请填写账号ID！');
                $(this).linkbutton({disabled: false});
            }else if(!staffName){
                $.messager.alert('提示','请填写人员姓名！');
                $(this).linkbutton({disabled:false});
            }else if(!staffState){
                $.messager.alert('提示','请选择人员状态！');
                $(this).linkbutton({disabled:false});
            }else if(!orgaId || orgaId==="请选择"){
                $.messager.alert('提示','请选择组织机构！');
                $(this).linkbutton({disabled:false});
            }else if(!mobilePhone){
                $.messager.alert('提示','请填写电话号码！');
                $(this).linkbutton({disabled:false});
            }else if(!emailAddress){
                $.messager.alert('提示','请填写邮箱地址！');
                $(this).linkbutton({disabled:false});
            }else if(!id_card_no){
                $.messager.alert('提示','请填写身份证号！');
                $(this).linkbutton({disabled:false});
            }else if(!default_service_type){
                $.messager.alert('提示','请填写所属系统！');
                $(this).linkbutton({disabled:false});
            }else{
                //手机号码
                var tel = /^0?1[3|4|5|6|7|8|9][0-9]\d{8}$/;
                //邮件
                var email= /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
                //身份证
                var idcard= /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
                //数字
                var num=/^[A-Za-z0-9]{4,16}$/;
                if (!num.test(staffId)) {
                    $.messager.alert('提示','账号ID必须为4-16位数字或字母的组合');
                    $(this).linkbutton({disabled:false});
                }else if (!tel.test(mobilePhone)) {
                    $.messager.alert('提示','手机号码格式不正确');
                    $(this).linkbutton({disabled:false});
                }else if (!email.test(emailAddress)) {
                    $.messager.alert('提示','邮箱地址格式不正确！');
                    $(this).linkbutton({disabled:false});
                }else if (!idcard.test(id_card_no)) {
                    $.messager.alert('提示','身份证号码格式不正确！');
                    $(this).linkbutton({disabled:false});
                }else{
                    var sd=getParams($popWindow);
                    var url='/sam/StaffInfo/createStaffInfo';
                    AjaxUtils.commonAjax('post',url,sd,function (data) {
                        $("#global").linkbutton({disabled:false});
                        if (data.resultVal==="1"){
                            $.messager.alert('提示','操作成功！');
                            $("#win_content").window("close");
                            $page.find("#staff").datagrid("load");
                        } else{
                            $.messager.alert('提示',data.resultMsg);
                        }
                    },function () {
                        $.messager.alert("提示","新增失败!");
                    },'json');
                }
            }
        });
        $popWindow.on("click", "#cancel", function() {
            $popWindow.find('form.form').form('clear');
            $("#win_content").window("close");
        });
    }

    /**
     * 初始化修改密码弹出窗口
     */
    function initPsdWindow() {
        $("#win_content").empty();
        $psdWindow = $("<div></div>").appendTo($("#win_content"));

        //账号配置表单
        var $winContent =
            $([
                "<div class='panel-tool-box cl' >",
                "<div class='fl text-bold'>修改密码</div>",
                "</div>",
                "<div class='panel-search'>",
                "<form id='resetPassWordForm' method='POST' class='form form-horizontal'>",
                "<div class='row cl'>",
                "<label class='form-label col-4'>初始密码：</label>"+
                "<div class='formControls col-6'><input  type='password' name='orgiPassword' class='easyui-passwordbox' prompt='Password' iconWidth='28'  style='width:90%;height:30px' /><span style='color:red;padding-left:2px'>*</span></div>" +
                "</div>",
                "<div class='row cl'>",
                "<label class='form-label col-4'>新密码：</label>"+
                "<div class='formControls col-6'><input type='password' name='newPassword' class='easyui-passwordbox' prompt='Password' iconWidth='28'  style='width:90%;height:30px' /><span style='color:red;padding-left:2px'>*</span></div>" +
                "</div>",
                "<div class='row cl'>",
                "<label class='form-label col-4'>重复新密码：</label>"+
                "<div class='formControls col-6'><input  type='password' name='repetNewPassword' class='easyui-passwordbox' prompt='Password' iconWidth='28'  style='width:90%;height:30px' /><span style='color:red;padding-left:2px'>*</span></div>" +
                "</div>",
                "</form>",
                "</div>"
            ].join("")).appendTo($psdWindow);
        $([
            "<div class='mt-10 test-c'>",
            "<label class='form-label col-5'></label>",
            "<a href='javascript:void(0)' id='globalPsd' class='btn btn-green radius mt-l-20' >" +
            "保存</a>",
            "<span>          </span>",
            "<a href='javascript:void(0)' id='cancelPsd' class='btn btn-secondary radius mt-l-20' >" +
            "取消</a>",
            "</div>",
            "</div>"
        ].join("")).appendTo($psdWindow);

        $winContent.find("input.easyui-datetimebox").datetimebox({
            editable: false
        });
        $psdWindow.find("a.btn").linkbutton();
    }
    /**
     * 初始化修改密码弹出窗口事件
     */
    function initPsdEvent(){
        $psdWindow.on("click", "#globalPsd", function() {
            if($(this).linkbutton("options").disabled){
                return;
            }
            //禁用按钮
            $(this).linkbutton({disabled:true});
            var orgiPassword=$psdWindow.find("input[name='orgiPassword']").val().trim();
            var newPassword=$psdWindow.find("input[name='newPassword']").val().trim();
            var repetNewPassword=$psdWindow.find("input[name='repetNewPassword']").val().trim();
            if(!orgiPassword) {
                $.messager.alert('提示', '请填写初始密码！');
                $(this).linkbutton({disabled: false});
            }else if(!newPassword){
                $.messager.alert('提示','请填写新密码！');
                $(this).linkbutton({disabled:false});
            }else if(!repetNewPassword){
                $.messager.alert('提示','请填写重复新密码！');
                $(this).linkbutton({disabled:false});
            }else if(orgiPassword ===newPassword){
                $.messager.alert('提示','新旧密码不能重复！');
                $(this).linkbutton({disabled:false});
            }else if(newPassword!==repetNewPassword){
                $.messager.alert('提示','输入的两次新密码不一致！');
                $(this).linkbutton({disabled:false});
            }else{
                var sd=getParams($psdWindow);
                var params = {};
                params.staffId =psdStaffId;
                params.orgiPassword =sd.orgiPassword;
                params.newPassword =sd.newPassword;
                AjaxUtils.commonAjax('post','/sam/tsampassword/updatePassWord',params,function (data) {
                    $("#globalPsd").linkbutton({disabled:false});
                    if (data.resultVal==="1"){
                        $.messager.alert('提示','操作成功！');
                        $("#win_content").window("close");
                    } else{
                        $.messager.alert('提示',data.resultMsg);
                    }
                },function () {
                    $.messager.alert("提示","修改密码失败!");
                },'json');
            }
        });
        $psdWindow.on("click", "#cancelPsd", function() {
            $psdWindow.find('form.form').form('clear');
            $("#win_content").window("close");
        });
    }

    //采用jquery easyui loading css效果
    function ajaxLoading(){
        $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo($popWindow);
        $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo($popWindow).css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});
    }
    function ajaxLoadEnd(){
        $(".datagrid-mask").remove();
        $(".datagrid-mask-msg").remove();
    }
    /**
     * 鼠标移至单元格展示内容效果
     */

    function currentTime(){
        var d = new Date(),str = '';
        var month = d.getMonth()+1;
        var date = d.getDate();
        if(month <10){
            month = '0'+month;
        }
        if(date<10){
            date = '0'+date;
        }

        str += d.getFullYear()+'-';
        str  += month+'-';
        str  += date;
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
                if ($(td).text() == ""){
                    return;
                }
                if ($(td).text()=="查看修改"){
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
                                    if (options.tipDelayTime){
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
                                if (options.tipDelayTime){
                                    clearTimeout(options.tipDelayTime);
                                }
                                options.tooltip.css({
                                    'display': 'none'
                                });
                            },
                            'mousemove': function (e) {
                                var that = this;
                                if (options.tipDelayTime){
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


    //修改弹出窗口
    window.initWinContent =function initWinContent(){
        $("#win_content").show().window({
            width:950,
            height:550,
            modal:true,
            title:"修改人员信息"
        });
    };

    //修改页面初始化
    window.initUpdateWindow = function initUpdateWindow(staffId) {
        $("#win_content").empty();
        // $popWindow = $("<div id='tt' class='easyui-tabs' style='fit:true;overflow:auto;'>").appendTo($("#win_content"));
        $popWindow = $("<div id='tt' class='easyui-tabs'>").appendTo($("#win_content"));
        var $winContent =
            $([
                "<div title=\"基本信息\" style=\"padding:20px;\">\n" +
                "        <div data-options=\"region:'center'\" style=\"overflow: auto;\">\n" +
                "            <div id=\"update_page_content\" data-options=\"region:'center'\">\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div title=\"角色分配\"  style=\"padding:20px;\">\n" +
                "        <div data-options=\"region:'center'\" style=\"overflow: auto;\">\n" +
                "            <div id=\"role_page_content\" data-options=\"region:'center'\">\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div title=\"权限配置\"  style=\"overflow:auto;padding:20px;\">\n" +
                "        <div data-options=\"region:'center'\" style=\"overflow: auto;\">\n" +
                "            <div id=\"auth_page_content\" data-options=\"region:'center'\">\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div title=\"工作组配置\" style=\"padding:20px;\">\n" +
                "        <div data-options=\"region:'center'\" style=\"overflow: auto;\">\n" +
                "            <div id=\"group_page_content\" data-options=\"region:'center'\">\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div title=\"平台工号配置\" style=\"padding:20px;\">\n" +
                "        <div data-options=\"region:'center'\" >\n" +
                "            <div id=\"plat_page_content\" data-options=\"region:'center'\">\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div title=\"角色权限查看\" style=\"padding:20px;\">\n" +
                "        <div id=\"cc\" class=\"easyui-layout\" style=\"width:98%;height:280px;\">\n" +
                "            <div data-options=\"region:'west',title:'人员工号',split:true\" style=\"width:50%;\">\n" +
                "                <div id=\"west_page_content\" data-options=\"region:'center'\">\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div data-options=\"region:'center',title:'人员权限树'\" style=\"padding:5px;overflow:auto;\">\n" +
                "                <div>\n" +
                "                    <ul id=\"staffAuthTree\" class=\"ztree1\"></ul>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div id=\"tte\" class=\"easyui-layout\" style=\"width:98%;height:280px;\">\n" +
                "            <div data-options=\"region:'west',title:'人员角色树',split:true\" style=\"width:50%;overflow-x:hidden;overflow-y:auto;\" >\n" +
                "                <div>\n" +
                "                    <ul id=\"roleTree3\" class=\"ztree1\"></ul>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div data-options=\"region:'center',title:'角色权限树'\" style=\"padding:5px;overflow:auto;\">\n" +
                "                <div>\n" +
                "                    <ul id=\"roleAuthTree\" class=\"ztree1\"></ul>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>"
            ].join("")).appendTo($popWindow);
        initConfig(staffId);
    };

    //修改页面tab页初始化及事件
    window.initConfig=function initConfig(staffId){
        var $updatePage, $update,$platSearch;
        var $rolePage;
        var $authPage;
        var $groupPage;
        var $platPage;
        var $westAuthPage;
        var cmm_code_id;
        $updatePage = $("<div></div>").appendTo($("#update_page_content"));
        $rolePage = $("<div></div>").appendTo($("#role_page_content"));
        $authPage = $("<div></div>").appendTo($("#auth_page_content"));
        $groupPage = $("<div></div>").appendTo($("#group_page_content"));
        $platPage = $("<div></div>").appendTo($("#plat_page_content"));
        $westAuthPage = $("<div></div>").appendTo($("#west_page_content"));

        $.parser.parse();
        //权限配置页面
        var root,moduleId;
        var m = [],modules = [];
        var zTreeObj,roleTree,roleTree3,groupTree,staffAuthTree,roleAuthTree;

        var token = AjaxUtils.getToken();
        initSearchGrid();
        initUpdateWindowEvent();
        /**
         * 初始化查询条件
         */
        function initSearchGrid() {
            $update = $([
                "<div class='panel-search'>",
                "<form class='form form-horizontal'>",
                "<div class='row cl'>"+
                "<label class='form-label col-2'>账号ID:</label>",
                "<div class='formControls col-2'><input type='text' id='staffId5' name='staffId5' class='easyui-textbox'  disabled='true' style='width:90%;height:30px' ></div>",
                "<label class='form-label col-2'>人员姓名:</label>"+
                "<div class='formControls col-2'><input  type='text' id='staffName5' name='staffName5' class='easyui-textbox' style='width:90%;height:30px' /><span style='color:red;padding-left:2px'>*</span></div>" +
                "<label class='form-label col-2'>移动电话:</label>",
                "<div class='formControls col-2'><input type='text' id='mobilePhone5' name='mobilePhone5' class='easyui-textbox' style='width:90%;height:30px'><span style='color:red;padding-left:2px'>*</span></div>",
                "</div>",
                "<div class='row cl'>"+
                "<label class='form-label col-2'>Email地址:</label>"+
                "<div class='formControls col-2'><input  type='text' id='emailAddress5' name='emailAddress5'  class='easyui-textbox' style='width:90%;height:30px'/><span style='color:red;padding-left:2px'>*</span></div>"+
                "<label class='form-label col-2'>组织机构</label>"+
                "<div class='formControls col-2'><input  type='text' id='orgaId5' name='orgaId5' class='easyui-combotree'  style='width:90%;height:30px' /><span style='color:red;padding-left:2px'>*</span></div>" +
                "<label class='form-label col-2'>身份证号:</label>"+
                "<div class='formControls col-2'><input  type='text' id='idCardNo5' name='idCardNo5'  class='easyui-textbox'  style='width:90%;height:30px' /><span style='color:red;padding-left:2px'>*</span></div>" +
                "</div>",
                "<div class='row cl'>"+
                "<label class='form-label col-2'>所属系统:</label>"+
                "<div class='formControls col-2'><input  type='text' id='defaultServiceType5' name='defaultServiceType5'  class='easyui-textbox' style='width:90%;height:30px'/><span style='color:red;padding-left:2px'>*</span></div>"+
                "<label class='form-label col-2'>人员状态:</label>",
                "<div class='formControls col-2'><select class='easyui-combobox' id='staffState5' name='staffState5' data-options='panelHeight:\"auto\"' style='width:90%;height:30px'><option value='1'>在职</option><option value='0'>离职</option></select></div>",
                "<label class='form-label col-2'>账号状态:</label>",
                "<div class='formControls col-2'><select class='easyui-combobox' id='staffIdStatus5' name='staffIdStatus5' data-options='panelHeight:\"auto\"' style='width:90%;height:30px'><option value='01'>正常</option><option value='02'>停用</option><option value='03'>作废</option><option value='04'>锁定</option><option value='05'>解锁</option><option value='06'>失效</option><option value='07'>未启用</option></select></div>",
                "</div>",
                "<div class='row cl'>"+
                "<label class='form-label col-2'>所属渠道:</label>"+
                "<div class='formControls col-2'><select class='easyui-combobox' id='channelId5' name='channelId5' data-options='panelHeight:\"auto\"' style='width:90%;height:30px'><option value='01'>融合客服</option><option value='02'>众包</option></select></div>",
                "<label class='form-label col-2'>人员类型:</label>"+
                "<div class='formControls col-2'><select class='easyui-combobox' id='prsnchnltypecd5' name='prsnchnltypecd5' data-options='panelHeight:\"auto\"' style='width:90%;height:30px'><option value='0'>自有</option><option value='1'>外包</option><option value='3'>劳务派遣</option><option value='2'>其他</option></select></div>",
                "<label class='form-label col-2'>岗位:</label>"+
                "<div class='formControls col-2'><select class='easyui-combobox' id='postId5' name='postId5' data-options='panelHeight:\"auto\"' style='width:90%;height:30px'><option value='00'>选择类型</option><option value='01'>组长</option><option value='02'>职员</option></select></div>",
                "</div>",

                "<div class='row cl'>",
                "<div class='mt-10 text-c '>" +
                "<a href='javascript:void(0)' id='global' class='btn btn-green radius mt-l-20' >" +
                "保存</a>",
                "<span>          </span>",
                "<a href='javascript:void(0)' id='cancel' class='btn btn-secondary radius mt-l-20' >" +
                "取消</a>",
                "</div>",
                "</div>",
                "</form>",
                "</div>"
            ].join("")).appendTo($updatePage);

            $update.find("input.easyui-textbox").textbox();
            $update.find("input.easyui-datetimebox").datetimebox({
                editable: false
            });
            $update.find("a.easyui-linkbutton").linkbutton();
            // /*
            //  * enter键触发查询
            //  */
            // $update.find("input.easyui-textbox").textbox({
            //     inputEvents: $.extend({},$.fn.textbox.defaults.inputEvents,{
            //         keyup: function(event){
            //             if(event.keyCode == 13) {
            //                 $update.find("a.btn-green").click();
            //             }
            //         }
            //     })
            // });
            $update.find("a.btn").linkbutton();
            var url ='/sam/StaffInfo/qryStaffInfo?staffId='+ staffId;
            AjaxUtils.commonAjax('post',url,null,function (data) {
                $("#staffId5").textbox("setValue",data.list[0].staffId);
                $("#staffName5").textbox("setValue",data.list[0].staffName);
                $("#staffState5").val(data.list[0].staffState);
                $("#staffIdStatus5").val(data.list[0].staffIdStatus);
                cmm_code_id=data.list[0].orgaId;
                $("#mobilePhone5").textbox("setValue",data.list[0].mobilePhone);
                $("#idCardNo5").textbox("setValue",data.list[0].idCardNo);
                $("#emailAddress5").textbox("setValue",data.list[0].emailAddress);
                $("#defaultServiceType5").textbox("setValue",data.list[0].defaultServiceType);
                $("#channelId5").val(data.list[0].channelId);
                $("#prsnchnltypecd5").val(data.list[0].prsnChnlTypeCd);
                $("#postId5").val(data.list[0].postId);
            },function () {
                $.messager.alert("提示","查询人员信息失败!");
            },'json');
            $update.find("input[id='orgaId5']").combotree({
                multiple: true,
                checkbox : true,
                onlyLeafCheck : true,//只能叶子节点才能勾选
                url : "/sam/tsamorgainfo/selectSamOrgaTreeForCombotree?access_token="+token,
                onBeforeSelect : function(node){
                    // $(this).tree("check", node.target);//控制点击文字时也能勾选
                    // return false;
                    //返回树对象
                    var tree = $(this).tree;
                    //选中的节点是否为叶子节点,如果不是叶子节点,清除选中
                    var isLeaf = tree('isLeaf', node.target);
                    if (!isLeaf) {
                        $("#orgaId5").treegrid("unselect");
                    }
                },
                onBeforeCheck : function(node, checked){
                    if(checked){//如果是勾选操作，则把之前选中的节点清除（不勾选）
                        var nodes = $(this).tree("getChecked");
                        if(nodes.length > 0){
                            for(var i=0; i<nodes.length; i++){
                                $(this).tree("uncheck", nodes[i].target);
                            }
                        }
                    }
                },
                onLoadSuccess : function(node, data){
                    // var cmm_code_id_value = "请选择";
                    if(cmm_code_id != null && $.trim(cmm_code_id) != ""){
                        var comboObj = $("#orgaId5");
                        comboObj.combotree("setValue", cmm_code_id);
                    }
                    var t = $("#orgaId5").combotree('tree');
                    t.tree('collapseAll');
                    var childObj = t.tree('getChildren');
                    var node = t.tree('find', childObj[0].id);
                    t.tree('expand', node.target);
                }
            });
        }
        function initUpdateWindowEvent(){
            $update.on("click", "#global", function() {
                // if($(this).hasClass("disabled")){
                //     return;
                // }
                // //关闭按钮，防止多次提交
                // $(this).addClass("disabled");
                if($(this).linkbutton("options").disabled){
                    return;
                }
                //禁用按钮
                $(this).linkbutton({disabled:true});
                var staffId=$update.find("input[name='staffId5']").val().trim();
                var staffName=$update.find("input[name='staffName5']").val().trim();
                var staffState=$update.find("select[id='staffState5']").val().trim();
                // var staffIdStatus=$update.find("select[id='staffIdStatus5']").val().trim();
                var orgaId=$update.find("input[name='orgaId5']").val().trim();
                var mobilePhone=$update.find("input[name='mobilePhone5']").val().trim();
                var default_service_type=$update.find("input[name='defaultServiceType5']").val().trim();
                var id_card_no=$update.find("input[name='idCardNo5']").val().trim();
                var emailAddress=$update.find("input[name='emailAddress5']").val().trim();
                // var channelid=$update.find("select[id='channelId5']").val().trim();
                // var prsnchnltypecd=$update.find("select[id='prsnchnltypecd5']").val().trim();
                if(!staffId) {
                    $.messager.alert('提示', '请填写账号ID！');
                    $(this).linkbutton({disabled: false});
                }else if(!staffName){
                    $.messager.alert('提示','请填写人员姓名！');
                    $(this).linkbutton({disabled:false});
                }else if(!staffState){
                    $.messager.alert('提示','请选择人员状态！');
                    $(this).linkbutton({disabled:false});
                }else if(!orgaId){
                    $.messager.alert('提示','请选择组织机构！');
                    $(this).linkbutton({disabled:false});
                }else if(!mobilePhone){
                    $.messager.alert('提示','请填写电话号码！');
                    $(this).linkbutton({disabled:false});
                }else if(!emailAddress){
                    $.messager.alert('提示','请填写邮箱地址！');
                    $(this).linkbutton({disabled:false});
                }else if(!id_card_no){
                    $.messager.alert('提示','请填写身份证号！');
                    $(this).linkbutton({disabled:false});
                }else if(!default_service_type){
                    $.messager.alert('提示','请填写所属系统！');
                    $(this).linkbutton({disabled:false});
                }else{
                    //手机号码
                    var tel = /^0?1[3|4|5|6|7|8|9][0-9]\d{8}$/;
                    //邮件
                    var email= /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
                    //身份证
                    var idcard= /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
                    //数字
                    var num=/^[A-Za-z0-9]{4,16}$/;
                    if (!num.test(staffId)) {
                        $.messager.alert('提示','账号ID必须为4-16位数字或字母的组合');
                        $(this).linkbutton({disabled:false});
                    }else if (!tel.test(mobilePhone)) {
                        $.messager.alert('提示','手机号码格式不正确');
                        $(this).linkbutton({disabled:false});
                    }else if (!email.test(emailAddress)) {
                        $.messager.alert('提示','邮箱地址格式不正确！');
                        $(this).linkbutton({disabled:false});
                    }else if (!idcard.test(id_card_no)) {
                        $.messager.alert('提示','身份证号码格式不正确！');
                        $(this).linkbutton({disabled:false});
                    }else{
                        var sd=getParams($update);
                        AjaxUtils.commonAjax('post','/sam/StaffInfo/updateStaffInfo',sd,function (data) {
                            $("#global").linkbutton({disabled:false});
                            if (data.resultVal==="1"){
                                $.messager.alert('提示','操作成功！');
                                var jq = top.jQuery;
                                jq("#staff").datagrid("load");
                            } else{
                                $.messager.alert('提示',data.resultMsg);
                            }
                        },function () {
                            $.messager.alert("提示","修改人员信息失败!");
                        },'json');
                    }
                }
            });
            $update.on("click", "#cancel", function() {
                $("#win_content").window("close");
            });

        }

        initAuthModule();
        initRoleTab();
        initGroupTab();
        initStaffAuthTree();
        initRoleTree3();
        initRoleIdAuthTree(0);
        initRoleAuthTab();
        initPlatSearch();
        initPlatTab();
        initPlatEvent();

        //权限页面
        function initAuthModule() {
            $([
                "<div class='panel-search'>",
                "<form class='form form-horizontal'>",
                "<div class='row cl'>",
                "<label class='form-label col-2'>当前工号：</label>",
                "<div class='formControls col-2'><input type='text' id='staffId1' disabled='true' name='staffId1' class='easyui-textbox'  style='width:90%;height:30px'></div>",
                "<label class='form-label col-2'>模块：</label>",
                "<div class='formControls col-2'><input class='easyui-combobox' id='module' name='module' style='width:90%;height:30px'/><span style='color:red;padding-left:2px'>*</span></div>",
                "</div>",
                "<div>",
                "<ul id='authTree' class='ztree1'></ul>",
                "</div>",
                "<div class='mt-10 test-c'>",
                "<label class='form-label col-5'></label>",
                "<a href='javascript:void(0)' id='global1' class='btn btn-green radius mt-l-20' >保存</a>",
                "<span>          </span>",
                "<a href='javascript:void(0)' id='cancel1' class='btn btn-secondary radius mt-l-20' >" +
                "取消</a>",
                "</div>",
                "</form>",
                "</div>"
            ].join("")).appendTo($authPage);
            $("#staffId1").val(staffId);
            queryXzqh();
        }
        //查询模块信息
        function queryXzqh(){
            AjaxUtils.commonAjax('post','/sam/Module/qryModule','',function (data) {
                for(var i=0;i<data['modules'].length;++i){
                    m = [];
                    m.push(data['modules'][i]['MODULEID'],data['modules'][i]['MODULENAME']);
                    modules.push(m);
                }
                $("#module").combobox({
                    data:modules,
                    valueField: 0,
                    textField: 1,
                    panelHeight:300,
                    editable:false,
                    onChange:function(newValue,oldValue){
                        root = newValue;
                        moduleId = newValue;
                        clickedTreeId = root;
                        initAuthTree();
                    }
                })
            },function () {
                $.messager.alert("提示","查询模块信息失败!");
            },'json');
        };
        //初始化权限树
        function initAuthTree(){
            var setting = {
                async: {
                    dataType:"json",
                    enable: true,
                    url:"/sam/Auth/qryAuthElementTreeByEntityId?moduleId="+root+"&entityId="+staffId+"&permitType=1&access_token="+token,
                    autoParam:["id", "ELEMENTID"],
                    dataFilter: filter
                },
                view: {
                    dblClickExpand: false,
                    selectedMulti: false
                },
                check: {
                    enable: true,
                    autoCheckTrigger: true,
                    chkboxType:{ "Y" : "p", "N" : "ps" }
                },
                data: {
                    simpleData: {
                        enable: true,
                        idKey : "id",
                        pIdKey : "pid",
                        rootPId: 0
                    }
                },
                callback: {
                    onClick: zTreeOnClick,
                    onAsyncSuccess:afterAsync
                }
            };

            function zTreeOnClick(event, treeId, treeNode){
                zTreeObj.expandNode(treeNode);
                clickedTreeId = treeNode.id;
                flag=1;
            }

            function filter(treeId, parentNode, childNodes) {
                if (!childNodes) {
                    return null;
                }
                childNodes = childNodes['auths'];

                if(childNodes){
                    for (var i=0, l=childNodes.length; i<l; i++) {
                        childNodes[i].id = childNodes[i].ELEMENTID;
                        childNodes[i].name = childNodes[i].ELEMENTNAME;
                        childNodes[i].pid = childNodes[i].SUPERELEMENTCODE;
                    }
                }
                return childNodes;
            }
            function afterAsync(event,treeId,treeNode){
                if(!treeNode) {
                    // zTreeObj.selectNode(zTreeObj.getNodes()[0]);
                    zTreeObj.expandNode(zTreeObj.getNodes()[0],true);
                }else{
                    zTreeObj.selectNode(treeNode);
                    if(treeNode.children.length===0){
                        treeNode.isParent = false;
                        zTreeObj.updateNode(treeNode);
                    }
                }
            }

            zTreeObj =  $.fn.zTree.init($("#authTree"), setting,null);

        };

        //权限保存按钮
        var btn1 = document.getElementById("global1");
        btn1.addEventListener('click',function(){
            if(typeof(moduleId) == "undefined"){
                $.messager.alert('提示','请选择模块!');
                return;
            }
            var checked = zTreeObj.getCheckedNodes(true);
            console.log(checked);

            var str="";
            for (var i = 0; i < checked.length; i++) {
                str += checked[i].ELEMENTID+ ",";
            }
            //去掉最后一个逗号(如果不需要去掉，就不用写)
            if (str.length > 0) {
                str = str.substr(0, str.length - 1);
            }
            var params ={};
            params.entityId=staffId;
            params.moduleId=moduleId;
            params.authIds=str;
            params.permitType="1";
            AjaxUtils.commonAjax('post','/sam/tsampermit/updateEntityPermitAuth1',params,function (data) {
                initAuthTree();
                initStaffAuthTree();
                $.messager.alert('提示','操作成功!');
            },function () {
                $.messager.alert("提示","分配权限失败!");
            },'json');
        },false);
        //取消按钮
        var btnC1 = document.getElementById("cancel1");
        btnC1.addEventListener('click',function(){
            // var tab = window.parent.$("#tabs").tabs("getSelected");
            // var index = window.parent.$("#tabs").tabs("getTabIndex", tab);
            // window.parent.$("#tabs").tabs("close", index);
            $("#win_content").window("close");
        },false);
        var btnC2 = document.getElementById("cancel2");
        btnC2.addEventListener('click',function(){
            $("#win_content").window("close");
        },false);
        var btnC3 = document.getElementById("cancel3");
        btnC3.addEventListener('click',function(){
            $("#win_content").window("close");
        },false);
        var btnC4 = document.getElementById("cancel4");
        btnC4.addEventListener('click',function(){
            $("#win_content").window("close");
        },false);

        //角色页面
        function initRoleTab() {
            $([
                "<div class='panel-search'>",
                "<form class='form form-horizontal'>",
                "<div class='row cl'>",
                "<label class='form-label col-2'>当前工号：</label>",
                "<div class='formControls col-2'><input type='text' id='staffId' disabled='true' name='staffId' class='easyui-textbox'  style='width:90%;height:30px' ></div>",
                "</div>",
                "<div>",
                "<ul id='roleTree' class='ztree1'></ul>",
                "</div>",
                "<div class='mt-10 test-c'>",
                "<label class='form-label col-5'></label>",
                "<a href='javascript:void(0)' id='global2' class='btn btn-green radius mt-l-20' >保存</a>",
                "<span>          </span>",
                "<a href='javascript:void(0)' id='cancel2' class='btn btn-secondary radius mt-l-20' >" +
                "取消</a>",
                "</div>",
                "</form>",
                "</div>",
            ].join("")).appendTo($rolePage);
            $("#staffId").val(staffId);
            initRoleTree();
        }
        //角色树
        function initRoleTree(){
            var setting = {
                async : {
                    enable : true, // 设置 zTree是否开启异步加载模式
                    url: "/sam/tsamrole/selectTSamRoleTreeByStaffId?sync=true&staffId="+staffId+"&chkDisabled=false&access_token="+token,
                    autoParam : [ "id" ]	// 异步加载时自动提交父节点属性的参数,假设父节点 node = {id:1, name:"test"}，异步加载时，提交参数 zId=1
                },
                data:{ // 必须使用data
                    simpleData : {
                        enable : true,
                        idKey : "id", // id编号命名 默认
                        pIdKey : "pId", // 父id编号命名 默认
                        rootPId : 0	// 用于修正根节点父节点数据，即 pIdKey 指定的属性值
                    }
                },
                view: {
                    dblClickExpand: false,
                    selectedMulti: false
                },
                check:{
                    enable: true,
                    autoCheckTrigger: true
                },
                // 回调函数
                callback : {
                    onClick : function(event, treeId, treeNode, clickFlag) {
                        // 判断是否父节点
                        // if(!treeNode.isParent){
                        // initRoleGrid(treeNode.id);
                        //}
                    },
                    //捕获异步加载出现异常错误的事件回调函数 和 成功的回调函数
                    onAsyncError : zTreeOnAsyncError,
                    onAsyncSuccess : zTreeOnAsyncSuccess
                }
            };

            // 加载错误提示
            function zTreeOnAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
                alert("加载错误：" + XMLHttpRequest);
            };
            function zTreeOnAsyncSuccess(event, treeId, treeNode, msg){
                var treeObj = $.fn.zTree.getZTreeObj(treeId);
                var nodes = treeObj.getNodes();
                if (nodes.length>0) {
                    for(var i=0;i<nodes.length;i++){
                        if(nodes[i].pId == "0"){
                            treeObj.expandNode(nodes[i], true, false, false);//默认展开第一级节点
                        }
                    }
                }
            }

            roleTree = $.fn.zTree.init( $("#roleTree"), setting);
        };
        //角色树保存按钮
        var btn2 = document.getElementById("global2");
        btn2.addEventListener('click',function(){
            var checked = roleTree.getCheckedNodes(true);
            var str="";
            for (var i = 0; i < checked.length; i++) {
                str += checked[i].id+ ",";
            }
            //去掉最后一个逗号(如果不需要去掉，就不用写)
            if (str.length > 0) {
                str = str.substr(0, str.length - 1);
            }
            var params ={};
            params.staffId=staffId;
            params.roleIds=str;
            AjaxUtils.commonAjax('post','/sam/tsamstaffrole/updateStaffRole',params,function (data) {
                if(data.resultVal==="1"){
                    initRoleTree();
                    initStaffAuthTree();
                    initRoleTree3();
                    initRoleIdAuthTree(0);
                    $.messager.alert('提示','操作成功!');
                }else{
                    $.messager.alert('提示',data.resultMsg);
                }
            },function () {
                $.messager.alert("提示","分配角色失败!");
            },'json');
        },false);

        //工作组页面
        function initGroupTab() {
            $([
                "<div class='panel-search'>",
                "<form class='form form-horizontal'>",
                "<div class='row cl'>",
                "<label class='form-label col-2'>当前工号：</label>",
                "<div class='formControls col-2'><input type='text'id='staffId3' disabled='true' name='staffId3' class='easyui-textbox'  style='width:90%;height:30px' ></div>",
                "</div>",
                "<div>",
                "<ul id='groupTree' class='ztree1'></ul>",
                "</div>",
                "<div class='mt-10 test-c'>",
                "<label class='form-label col-5'></label>",
                "<a href='javascript:void(0)' id='global3' class='btn btn-green radius mt-l-20' >保存</a>",
                "<span>          </span>",
                "<a href='javascript:void(0)' id='cancel3' class='btn btn-secondary radius mt-l-20' >" +
                "取消</a>",
                "</div>",
                "</form>",
                "</div>",
            ].join("")).appendTo($groupPage);
            $("#staffId3").val(staffId);
            initGroupTree();
        }
        //工作组树
        function initGroupTree(){
            var setting = {
                async: {
                    dataType:"json",
                    enable: true,
                    url:"/sam/tsamgroupinfo/selectGroupTreeByStaffId?staffId="+staffId+"&access_token="+token,
                    autoParam:["id"],
                },
                view: {
                    dblClickExpand: false,
                    selectedMulti: false
                },
                check: {
                    enable: true,
                    autoCheckTrigger: true
                },
                data:{ // 必须使用data
                    simpleData : {
                        enable : true,
                        idKey : "id", // id编号命名 默认
                        pIdKey : "pId", // 父id编号命名 默认
                        rootPId : 0	// 用于修正根节点父节点数据，即 pIdKey 指定的属性值
                    }
                },
                // 回调函数
                callback : {
                    onClick : function(event, treeId, treeNode, clickFlag) {
                        // 判断是否父节点
                        // initGroupGrid(treeNode.id);
                    },
                    //捕获异步加载出现异常错误的事件回调函数 和 成功的回调函数
                    onAsyncError : zTreeOnAsyncError,
                    onAsyncSuccess : zTreeOnAsyncSuccess
                }
            };

            // 加载错误提示
            function zTreeOnAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
                alert("加载错误：" + XMLHttpRequest);
            };
            function zTreeOnAsyncSuccess(event, treeId, treeNode, msg){
                var treeObj = $.fn.zTree.getZTreeObj(treeId);
                var nodes = treeObj.getNodes();
                if (nodes.length>0) {
                    for(var i=0;i<nodes.length;i++){
                        if(nodes[i].pId == "0"){
                            treeObj.expandNode(nodes[i], true, false, false);//默认展开第一级节点
                        }
                    }
                }
            }


            // 过滤函数
            function filter(treeId, parentNode, childNodes) {
                if (!childNodes)
                    return null;
                for ( var i = 0, l = childNodes.length; i < l; i++) {
                    childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
                }
                return childNodes;
            }
            groupTree = $.fn.zTree.init( $("#groupTree"), setting);

        };
        //工作组树保存按钮
        var btn3 = document.getElementById("global3");
        btn3.addEventListener('click',function(){
            var checked = groupTree.getCheckedNodes(true);
            console.log(checked);
            var str="";
            for (var i = 0; i < checked.length; i++) {
                str += checked[i].id+ ",";
            }
            //去掉最后一个逗号(如果不需要去掉，就不用写)
            if (str.length > 0) {
                str = str.substr(0, str.length - 1);
            }
            var params ={};
            params.staffId=staffId;
            params.groupIds=str;
            AjaxUtils.commonAjax('post','/sam/tsamgroupmember/updateStaffGroup',params,function (data) {
                initGroupTree();
                $.messager.alert('提示','操作成功!');
            },function () {
                $.messager.alert("提示","分配工作组失败!");
            },'json');
        },false);

        //角色权限查看页面
        function initRoleAuthTab() {
            $([
                "<form class='form form-horizontal'>",
                "<div class='row cl'>",
                "<label class='form-label col-3'>当前工号：</label>",
                "<div class='formControls col-3'><input type='text'id='staffId4' disabled='true' name='staffId4' class='easyui-textbox'  style='width:100%;height:30px' ></div>",
                "</div>",
                "</form>",
            ].join("")).appendTo($westAuthPage);
            $("#staffId4").val(staffId);
        }

        //人员权限树
        function initStaffAuthTree(){
            var setting = {
                async: {
                    dataType:"json",
                    enable: true,
                    url:"/sam/Auth/qryAuthElementTreeByEntityId?moduleId=000&entityId="+staffId+"&permitType=1&access_token="+token,
                    autoParam:["id", "ELEMENTID"],
                    dataFilter: filter
                },
                view: {
                    dblClickExpand: false,
                    selectedMulti: false
                },
                check: {
                    enable: true,
                    autoCheckTrigger: true
                },
                data: {
                    simpleData: {
                        enable: true,
                        idKey : "id",
                        pIdKey : "pid",
                        rootPId: 0
                    }
                },
                callback: {
                    onClick: zTreeOnClick,
                    onAsyncSuccess:afterAsync
                }
            };

            function zTreeOnClick(event, treeId, treeNode){
                staffAuthTree.expandNode(treeNode);
                clickedTreeId = treeNode.id;
                flag=1;
            }

            function filter(treeId, parentNode, childNodes) {
                if (!childNodes) {
                    return null;
                }
                childNodes = childNodes['auths'];

                if(childNodes){
                    for (var i=0, l=childNodes.length; i<l; i++) {
                        childNodes[i].id = childNodes[i].ELEMENTID;
                        childNodes[i].name = childNodes[i].ELEMENTNAME;
                        childNodes[i].pid = childNodes[i].SUPERELEMENTCODE;
                    }
                }
                return childNodes;
            }
            function afterAsync(event,treeId,treeNode){
                if(!treeNode) {
                    // staffAuthTree.selectNode(staffAuthTree.getNodes()[0]);
                    staffAuthTree.expandNode(staffAuthTree.getNodes()[0],true);
                }else{
                    staffAuthTree.selectNode(treeNode);
                    if(treeNode.children.length===0){
                        treeNode.isParent = false;
                        staffAuthTree.updateNode(treeNode);
                    }
                }
            }

            staffAuthTree =  $.fn.zTree.init($("#staffAuthTree"), setting,null);
        };
        //角色树
        function initRoleTree3(){
            var setting = {
                async : {
                    enable : true, // 设置 zTree是否开启异步加载模式
                    url: "/sam/tsamrole/selectTSamRoleTreeByStaffId?sync=true&staffId="+staffId+"&chkDisabled=true&access_token="+token,
                    autoParam : [ "id" ]	// 异步加载时自动提交父节点属性的参数,假设父节点 node = {id:1, name:"test"}，异步加载时，提交参数 zId=1
                },
                data:{ // 必须使用data
                    simpleData : {
                        enable : true,
                        idKey : "id", // id编号命名 默认
                        pIdKey : "pId", // 父id编号命名 默认
                        rootPId : 0	// 用于修正根节点父节点数据，即 pIdKey 指定的属性值
                    }
                },
                view: {
                    dblClickExpand: false,
                    selectedMulti: false
                },
                check: {
                    enable: true,
                    autoCheckTrigger: true,
                    chkDisabled: true
                },
                // 回调函数
                callback : {
                    onClick : function(event, treeId, treeNode, clickFlag) {
                        // 判断是否父节点
                        // if(!treeNode.isParent){
                        // initRoleGrid(treeNode.id);
                        //}
                        if(treeNode.pId===0){
                            initRoleIdAuthTree(0);
                        }else{
                            initRoleIdAuthTree(treeNode.id);
                        }
                    },
                    //捕获异步加载出现异常错误的事件回调函数 和 成功的回调函数
                    onAsyncError : zTreeOnAsyncError,
                    onAsyncSuccess : zTreeOnAsyncSuccess
                }
            };

            // 加载错误提示
            function zTreeOnAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
                alert("加载错误：" + XMLHttpRequest);
            };

            function zTreeOnAsyncSuccess(event, treeId, treeNode, msg){
                var treeObj = $.fn.zTree.getZTreeObj(treeId);
                var nodes = treeObj.getNodes();
                if (nodes.length>0) {
                    for(var i=0;i<nodes.length;i++){
                        if(nodes[i].pId == "0"){
                            treeObj.expandNode(nodes[i], true, false, false);//默认展开第一级节点
                        }
                    }
                }
            };

            roleTree3 = $.fn.zTree.init( $("#roleTree3"), setting);
            var nodes = roleTree3.getSelectedNodes();
            for (var i=0, l=nodes.length; i < l; i++) {
                roleTree3.setChkDisabled(nodes[i], true);
            }
        };
        //角色权限树
        function initRoleIdAuthTree(roleId){
            var urlStr="";
            if (roleId===0) {
                urlStr="/sam/Auth/qryAuthElementTreeByRoleAll?moduleId=000&entityId="+staffId+"&access_token="+token;
            }else{
                urlStr="/sam/Auth/qryAuthElementTreeByEntityId?moduleId=000&entityId="+roleId+"&permitType=2&access_token="+token;
            }
            var setting = {
                async: {
                    dataType:"json",
                    enable: true,
                    url:urlStr,
                    autoParam:["id", "ELEMENTID"],
                    dataFilter: filter
                },
                view: {
                    dblClickExpand: false,
                    selectedMulti: false
                },
                check: {
                    enable: true,
                    autoCheckTrigger: true
                },
                data: {
                    simpleData: {
                        enable: true,
                        idKey : "id",
                        pIdKey : "pid",
                        rootPId: 0
                    }
                },
                callback: {
                    onClick: zTreeOnClick,
                    onAsyncSuccess:afterAsync
                }
            };

            function zTreeOnClick(event, treeId, treeNode){
                roleAuthTree.expandNode(treeNode);
                clickedTreeId = treeNode.id;
                flag=1;
            }

            function filter(treeId, parentNode, childNodes) {
                if (!childNodes) {
                    return null;
                }
                childNodes = childNodes['auths'];

                if(childNodes){
                    for (var i=0, l=childNodes.length; i<l; i++) {
                        childNodes[i].id = childNodes[i].ELEMENTID;
                        childNodes[i].name = childNodes[i].ELEMENTNAME;
                        childNodes[i].pid = childNodes[i].SUPERELEMENTCODE;
                    }
                }
                return childNodes;
            }

            function afterAsync(event,treeId,treeNode){
                if(!treeNode) {
                    // roleAuthTree.selectNode(roleAuthTree.getNodes()[0]);
                    roleAuthTree.expandNode(roleAuthTree.getNodes()[0],true);
                }else{
                    roleAuthTree.selectNode(treeNode);
                    if(treeNode.children.length===0){
                        treeNode.isParent = false;
                        roleAuthTree.updateNode(treeNode);
                    }
                }
            }
            roleAuthTree =  $.fn.zTree.init($("#roleAuthTree"), setting,null);
        };

        /**
         * 平台工号配置tab页搜索模块
         */
        function initPlatSearch() {
            $platSearch = $([
                "<div class='panel-search'>",
                "<form id='platSearchForm' class='form form-horizontal'>",
                "<div class='row cl'>",
                "<label class='form-label col-2'>平台工号ID：</label>",
                "<div class='formControls col-2'><input type='text' name='platformId' class='easyui-textbox staffId2'  style='width:90%;height:30px' ></div>",
                "<label class='form-label col-2'>平台工号区间：</label>",
                "<div class='formControls col-2'><input type='text' name='platIdMin' class='easyui-textbox staffIdMin2' style='width:42%;height:30px'> - <input type='text' name='platIdMax' class='easyui-textbox staffIdMax2' style='width:42%;height:30px'></div>",
                "<label class='form-label col-2'>平台工号名：</label>",
                "<div class='formControls col-2'><input type='text' name='accountName' class='easyui-textbox staffName2'  style='width:90%;height:30px' ></div>",
                "</div>",
                "<div class='row cl'>",
                "<div class='mt-10 text-c '>",
                "<a href='javascript:void(0)'  id='searchPlat' class='btn btn-green radius'><i class='iconfont iconfont-search2'></i>查询</a>",
                "<a href='javascript:void(0)'  class='btn btn-default radius mt-l-20 '><i class='iconfont iconfont-zhongzuo' ></i>重置</a>",
                "</div>",
                "</div>",
                "</form>",
                "</div>"
            ].join("")).appendTo($platPage);
        }
        /**
         * 平台工号配置分配人员模块
         */
        function initPlatTab() {
            $([
                "<div class='col' style='height: 400px;padding-top: 10px;'>",
                "<div style='float:left;width:45%;'>",
                "<div class='easyui-panel' title='待分配平台工号列表'  style='width:100%;height:450px;' >",
                "<table id='platLeftData' class='easyui-datagrid' style='width:100%;height:410px;'>",
                "</table>",
                "</div>",
                "</div>",
                "<div style=' float:left;width:5%; padding-top:100px;padding-left: 15px;'>",
                "<div class='fr'>",
                "<a href='javascript:void(0)' id='moveToRight' style='margin-left:80px;' class='btn btn-secondary radius'><i class='iconfont iconfont-arrow2-right'></i></a>",
                "<a href='javascript:void(0)' id='moveToLeft' style='margin-left:80px;margin-top: 10px;' class='btn btn-secondary radius'><i class='iconfont iconfont-arrow2-left'></i></a>",
                "</div>",
                "</div>",
                "<div style=' float:right;width:45%;'>",
                "<div class='easyui-panel' title='已分配平台工号列表'  style='width:100%;height:450px;' >",
                "<table id='platRightData' class='easyui-datagrid' style='width:100%;height:410px;'>",
                "</table>",
                "</div>",
                "</div>",
                "</div>",
                "<div class='mt-10 test-c'>",
                "<label class='form-label col-5'></label>",
                "<a href='javascript:void(0)'onclick='savePlat()'  id='global4' class='btn btn-green radius mt-l-20 fl' >保存</a>",
                "<span>          </span>",
                "<a href='javascript:void(0)' id='cancel4' class='btn btn-secondary radius mt-l-20 fl' >" +
                "取消</a>",
                "</div>",

            ].join("")).appendTo($platPage);
            $platPage.find("#platLeftData").datagrid({
                columns:[[
                    {field: 'ck', checkbox:'true', width: '5%'},
                    {field: 'platformId', title: '平台工号ID', width: '38%'},
                    {field: 'accountName', title: '平台工号名称', width: '40%'}
                ]],
                fitColumns:true,
                rownumbers: true,
                striped:true,
                nowrap:true,
                pagination: true,
                collapsible:true,
                singleSelect:false,
                height:400,
                pageSize:10,
                pageList: [10, 20, 50],
                loader: function (param, success) {
                    var start = (param.page - 1) * param.rows + 1;
                    var pageNum = param.rows;
                    // var params = {"startIndex":start,"pageNum":pageNum};
                    var params = $.extend({"startIndex":start, "pageNum":pageNum},getFormData("platSearchForm"));
                    AjaxUtils.commonAjax('post','/sam/tsamplatformrel/qryNoDistriPlatform',params,function (data) {
                        var dd = {"total":data.total,"rows":data.list};
                        success(dd);
                    },function () {
                        $.messager.alert("提示","查询平台工号失败!");
                    },'json');
                }
            });
            //加载已分配平台工号信息
            $platPage.find("#platRightData").datagrid({
                columns:[[
                    {field: 'ck', checkbox:'true', width: '5%'},
                    {field: 'platformId', title: '平台工号ID', width: '38%'},
                    {field: 'accountName', title: '平台工号名称', width: '40%'}
                ]],
                fitColumns:true,
                rownumbers: true,
                striped:true,
                nowrap:true,
                pagination: false,
                collapsible:true,
                singleSelect:false,
                height:400,
                loader: function (param, success) {
                    var params ={"staffId":staffId};
                    AjaxUtils.commonAjax('post','/sam/tsamplatformrel/qryStaffPlatform',params,function (data) {
                        var d=[];
                        for(var i=0;i<data['resultMsg'].length;++i){
                            d.push($.extend({'platformId':data['resultMsg'][i]['platformId']},{'accountName':data['resultMsg'][i]['accountName']}));
                        }
                        var dd={"total":data.total,"rows":d};
                        success(dd);
                    },function () {
                        $.messager.alert("提示","查询已分配平台工号信息失败!");
                    },'json');
                }
            });
        }
        /**
         * 平台工号配置tab页事件
         */
        function initPlatEvent(){
            $platPage.on("click", "#moveToRight", function () {
                var selections = $("#platLeftData").datagrid('getSelections');
                if (selections.length === 0) {
                    $.messager.alert("提示", "请选择要分配的数据")
                } else {
                    var already = [];
                    var isAlready = 0;
                    var rightData = $("#platRightData").datagrid('getRows');
                    for (var i = 0, l = selections.length; i < l; i++) {
                        for (var j = 0, isAlready = 0; j < rightData.length; j++) {
                            if (rightData[j]["platformId"] === selections[i]["platformId"]) {
                                already.push(rightData[j]["platformId"]);
                                isAlready = 1;
                            }
                        }
                        if (isAlready === 0) {
                            $('#platRightData').datagrid('insertRow', {
                                index: i,
                                row: selections[i]
                            });
                            var index = $('#platLeftData').datagrid('getRowIndex', selections[i]);
                            $('#platLeftData').datagrid('deleteRow', index);
                        }
                    }
                    if (already.length > 0) {
                        $.messager.alert("提示", already + "已在分配框");
                    }
                }
            });
            $platPage.on("click", "#moveToLeft", function () {
                var selRows = $('#platRightData').datagrid('getChecked');
                if(selRows.length == 0){
                    $.messager.alert("提示","选择要取消分配的数据");
                    return;
                }
                if(selRows.length>0){
                    for(var i = 0;i<selRows.length;i++){
                        if(selRows[i] !=undefined){
                            $('#platLeftData').datagrid('insertRow', {
                                index: i,
                                row: selRows[i]
                            });
                            var index = $('#platRightData').datagrid('getRowIndex', selRows[i]);
                            $('#platRightData').datagrid('deleteRow', index);
                        }else{
                            return;
                        }
                    }
                }
            });

            //查询
            $platSearch.on("click", "#searchPlat", function() {
                $platPage.find("#platLeftData").datagrid("load");
            });

            /*
             * 清除查询条件
             */
            $platSearch.on("click", "a.btn-default", function() {
                $platSearch.find('form.form').form('clear');
                $platPage.find("#platLeftData").datagrid("load");
            });

            //保存
            window.savePlat=function savePlat(){
                var data = $("#platRightData").datagrid("getRows");
                var params ={};
                if (data.length>1){
                    $.messager.alert("提示","最多分配一个平台工号");
                    return;
                }
                params.platformId=data[0].platformId;
                params.staffId=staffId;
                AjaxUtils.commonAjax('post','/sam/tsamplatformrel/updateStaffPlatform',params,function (data) {
                    if(data.resultVal == "1"){
                        $.messager.alert("提示","分配平台工号成功");
                    }else{
                        $.messager.alert("提示","分配平台工号失败"+data.resultMsg);
                    }
                },function () {
                    $.messager.alert("提示","分配平台工号失败!");
                },'json');
            };
        }

        function getFormData(formId){
            var data = {};
            var t = $("#"+formId+"").serializeArray();
            $.each(t, function() {
                data[this.name] = this.value;
            });
            return data;
        }
    };


});
function addTabs(staffId){
    // // window.top.Menus.openMenu("http://10.21.57.137:8086/sam/sam/html/staffInfoUpdate1.html","人员信息修改");
    // // var jq = window.parent.jQuery;
    // var jq = top.jQuery;
    // var title = "人员信息修改";
    // // var content = '<iframe scrolling="auto" frameborder="0"  src="../../../sam/html/staffInfoUpdate.html" style="width:100%;height:100%;"></iframe>';
    // var content = '<iframe scrolling="auto" frameborder="0"  src="../../../sam/sam/html/staffInfoUpdate1.html" style="width:100%;height:100%;"></iframe>';
    // window.parent.document.getElementById('hidInp').value=staffId;
    // if (jq('#tabs').tabs('exists', title)){
    //     jq('#tabs').tabs('select', title);
    // } else {
    //     jq('#tabs').tabs('add',{
    //         title:title,
    //         content:content,
    //         closable:true
    //     });
    //
    // }

    var a ="<a class='level1-a sonnavlink J_menuItem' href='http://10.21.19.15:80/sam/sam/html/samGroupindex.html'>工作组管理</a>";
    parent.tab.openMenu(a);
}
