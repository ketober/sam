/**********组织机构维护***************/


/**
 * 初始化资源树
 */
function initOrgainfoTree(){
    $('#tenantId').combobox({
        // url: '../../data/skill-priority.json',
        valueField: 'codeValue',
        textField: 'codeName',
        panelHeight: 'auto',
        editable: true,
        missingMessage: '请选择',
        loader: function (param, success, error) {
            param = {

            };
            $.ajax({
                url: "/sam/tsamtenantinfo/qrytenantinfo",
                dataType: 'json',
                type: "POST",
                data: param,
                success: function (data) {
                    var items = $.map(data.list, function (item, index) {
                        return {
                            codeValue: item["tenantId"],
                            codeName: item["tenantName"]
                        };
                    });
                    success(items);
                },
                error: function () {
                    error.apply(this, arguments);
                }
            });
        },
        onLoadSuccess: function (data) {
        }
    });
    var token = AjaxUtilsTemp.getToken();
    var setting = {
        async : {
            enable : true, // 设置 zTree是否开启异步加载模式
            url: "/sam/dataAuth/dataAuthTree?access_token="+token+"&opStaffId="+AjaxUtilsTemp.getOpStaffId(),
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
                initOrgainfoGrid(treeNode.id);
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

    $.fn.zTree.init( $("#menuTree"), setting);
}

function selectOrga(){
    var zTree = $.fn.zTree.getZTreeObj("menuTree");
    var nodes = zTree.getSelectedNodes();
    if(nodes.length == 0){
        var fristnodes = zTree.getNodes();
        initOrgainfoGrid(fristnodes[0].id);
    }else{
        initOrgainfoGrid(nodes[0].id);

    }

}


/**
 * 初始化Grid
 */
function initOrgainfoGrid(id){
    var params={orgaName:$("#searchOrgaName").val()};
    $('#dg').datagrid({
        rownumbers: false, //行号
        pagination: true, //分页控件
        pageSize: 10,
        pageList: [10, 20, 50],
        striped:true,
        fixRowHeight:600,
        loadMsg:"正在努力加载数据,表格渲染中...",
        columns:[[
            {field :'ck',checkbox : true,width : '20%' },
            {field:'commonid',title:'数据对象ID',width:'40%'},
            {field:'commonname',title:'数据对象名称',width:'40%'},
            {field: 'opera', title: '操作', width: '20%',formatter: rowformater}
        ]],
        onLoadSuccess: function (data) {
            console.log(data);
            if (data == null){
                //自定义页面信息加载框

            }
        },

        loader: function (param, success) {
            var start = (param.page - 1) * param.rows + 1;
            var pageNum = param.rows;
            var params={
                "commonid":$("#searchOrgaName").val(),
                "commonname":$("#searchTenantName").val(),
                "page":pageNum,
                "start":start};
            AjaxUtilsTemp.commonAjax('GET','/sam/dataAuth/commonAuthDataInfo?authconfigid='+id,params,true,'text/plain',function (data) {
                success(jQuery.parseJSON(data));
            },function () {
                $.messager.alert("提示","获取数据异常!");
            },'json');


        },


    });
}


function rowformater(value, row, index) {
    var commonid = row.commonid;
    var zTree = $.fn.zTree.getZTreeObj("menuTree");
    var nodes = zTree.getSelectedNodes();
    var authtypeid;
    if(nodes.length!=0)
    {
        authtypeid = nodes[0].id;
    }
    else {
        authtypeid = "001";
    }
    return  "<a onclick='initWinContent(),initUpdateWindow(\""+commonid+"\",\""+authtypeid+"\");'>分配权限</a>";
}

//修改弹出窗口
window.initWinContent =function initWinContent(){
    $("#win_content").show().window({
        width:950,
        height:550,
        modal:true,
        title:"权限分配"
    });
};

//修改页面初始化
window.initUpdateWindow = function initUpdateWindow(commonid,authtypeid) {
    $("#win_content").empty();
    // $popWindow = $("<div id='tt' class='easyui-tabs' style='fit:true;overflow:auto;'>").appendTo($("#win_content"));
    $popWindow = $("<div id='tt' class='easyui-tabs'>").appendTo($("#win_content"));
    var $winContent =
        $([
           /* "<div title=\"基本信息\" style=\"padding:20px;\">\n" +
            "        <div data-options=\"region:'center'\" style=\"overflow: auto;\">\n" +
            "            <div id=\"baseInfo_page_content\" data-options=\"region:'center'\">\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>\n" +*/
            "<div title=\"人员分配\"  style=\"overflow:auto;padding:20px;\">\n" +
            "        <div data-options=\"region:'center'\" style=\"overflow: auto;\">\n" +
            "            <div id=\"staff_page_content\" data-options=\"region:'center'\">\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "    </div>"+
            "<div title=\"角色分配\"  style=\"overflow:auto;padding:20px;\">\n" +
            "        <div data-options=\"region:'center'\" style=\"overflow: auto;\">\n" +
            "            <div id=\"role_page_content\" data-options=\"region:'center'\">\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "    </div>"
        ].join("")).appendTo($popWindow);
    initConfig(commonid,authtypeid);
};

//修改页面tab页初始化及事件
window.initConfig=function initConfig(commonid,authtypeid){
    var $updatePage;
    var $platPage;
    $updatePage = $("<div></div>").appendTo($("#role_page_content"));
    $platPage = $("<div></div>").appendTo($("#staff_page_content"));
    $.parser.parse();
    var token = AjaxUtilsTemp.getToken();
    initSearchGrid(commonid);
    initPlatSearch();
    initPlatTab(commonid,authtypeid);
    initPlatEvent()
    function initSearchGrid(commonid) {
        $update = $([
            "<div class='panel-search'>",
            "<form class='form form-horizontal'>",
            "<div class='row cl'>",
            "<label class='form-label col-2'>当前数据对象ID：</label>",
            "<div class='formControls col-2'><input type='text' id='commonid' disabled='true' name='commonid' class='easyui-textbox'  style='width:90%;height:30px' ></div>",
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
        ].join("")).appendTo($updatePage);

        $updatePage.find("#commonid").val(commonid);
        initRoleTree(commonid);

    }
    //角色树
    function initRoleTree(commonid){
        var staffId = "";
        var setting = {
            async : {
                enable : true, // 设置 zTree是否开启异步加载模式
                url: "/sam/dataAuth/selectTSamRoleTree?sync=true&authId="+commonid+"&chkDisabled=false&access_token="+token,
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
        params.commonid = commonid;
        params.authtypeid = authtypeid;
        params.roleIds=str;
        AjaxUtilsTemp.commonAjaxAsync('post','/sam/dataAuth/updateRoleDataAuth',true,params,function (data) {
            if(data.resultVal==="1"){
                initRoleTree();
                //initStaffAuthTree();
                //initRoleTree3();
                //initRoleIdAuthTree(0);
                $.messager.alert('提示','操作成功!');
            }else{
                $.messager.alert('提示',data.resultMsg);
            }
        },function () {
            $.messager.alert("提示","分配角色失败!");
        },'json');
    },false);
    /**
     * 平台工号配置tab页搜索模块
     */
    function initPlatSearch() {
        $platSearch = $([
            "<div class='panel-search'>",
            "<form id='platSearchForm' class='form form-horizontal'>",
            "<div class='row cl'>",
            "<label class='form-label col-2'>账号ID：</label>",
            "<div class='formControls col-2'><input type='text' name='staffId' class='easyui-textbox staffId2'  style='width:90%;height:30px' ></div>",
            "<label class='form-label col-2'>账号区间：</label>",
            "<div class='formControls col-2'><input type='text' name='staffIdMin' class='easyui-textbox staffIdMin2' style='width:42%;height:30px'> - <input type='text' name='staffIdMax' class='easyui-textbox staffIdMax2' style='width:42%;height:30px'></div>",
            "<label class='form-label col-2'>人员姓名：</label>",
            "<div class='formControls col-2'><input type='text' name='staffName' class='easyui-textbox staffName2'  style='width:90%;height:30px' ></div>",
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
    function initPlatTab(commonid,authtypeid) {
        $([
            "<div class='col' style='height: 400px;padding-top: 10px;'>",
            "<div style='float:left;width:45%;'>",
            "<div class='easyui-panel' title='待分配员工列表'  style='width:100%;height:450px;' >",
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
            "<div class='easyui-panel' title='已分配员工列表'  style='width:100%;height:450px;' >",
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
                {field: 'staffId', title: '账号ID', width: '38%'},
                {field: 'staffName', title: '人员姓名', width: '40%'}
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
                var data = getFormData("platSearchForm");
              /*  if(data.staffId == "" && data.staffName=="" && data.staffIdMax=="" && data.staffIdMin=="")
                {
                    var dd = {"total":0,"rows":""};
                    success(dd);
                    return;
                }*/
                var params = $.extend({"startIndex":start, "pageNum":pageNum},data);
                /*       AjaxUtilsTemp.commonAjax('post','/sam/StaffInfo/qryStaffInfo',params,function (data) {
                           var dd = {"total":data.total,"rows":data.list};
                           success(dd);
                       },function () {
                           $.messager.alert("提示","查询平台工号失败!");
                       },'json');*/
                $.ajax({
                    headers: {
                        Authorization: "Bearer " + token,
                    },
                    type: "post",
                    url: '/sam/StaffInfo/qryStaffInfoForAssign',
                    data: params,
                    //async: async,
                    //contentType:postType,
                    error: function (request) {
                        $.messager.alert("提示","查询平台工号失败!");
                    },
                    success: function (result) {
                        //console.log("result =====" + JSON.stringify(result));
                        var dd = {"total":result.total,"rows":result.list};
                        success(dd);
                    }
                });
            }
        });
        //加载已分配平台工号信息
        $platPage.find("#platRightData").datagrid({
            columns:[[
                {field: 'ck', checkbox:'true', width: '5%'},
                {field: 'staffId', title: '账号ID', width: '38%'},
                {field: 'staffName', title: '人员姓名', width: '40%'}
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
               /* var start = (param.page - 1) * param.rows;
                var pageNum = param.rows;*/
                var params = {"authobjectid":commonid,"authtypeid":authtypeid};
                /*     AjaxUtilsTemp.commonAjaxAsync('post','/sam/StaffInfo/qryStaffInfo',params,function (data) {
                         var d=[];
                         for(var i=0;i<data['resultMsg'].length;++i){
                             d.push($.extend({'staffId':data['resultMsg'][i]['staffId']},{'staffName':data['resultMsg'][i]['staffName']}));
                         }
                         var dd={"total":data.total,"rows":d};
                         success(dd);
                     },function () {
                         $.messager.alert("提示","查询已分配平台工号信息失败!");
                     },'json');*/

                AjaxUtilsTemp.commonAjaxAsync('post', '/sam/dataAuth/queryDataAuthAssignedStaff', true,params, function (data) {
                    var dd = {"total":data.total,"rows":data.list};
                    success(dd);
                },function () {
                    $.messager.alert("提示","查询待分配人员失败!");
                }, 'json');

/*                $.ajax({
                    headers: {
                        Authorization: "Bearer " + token,
                    },
                    type: "post",
                    url: '/sam/dataAuth/queryDataAuthAssignedStaff',
                    data: JSON.stringify(params),
                    async: true,
                    contentType:'application/json',
                    error: function (request) {
                        $.messager.alert("提示","查询已分配平台工号信息失败!");
                    },
                    success: function (result) {
                        //console.log("result =====" + JSON.stringify(result));
                        var dd = {"total":result.total,"rows":result.list};
                        success(dd);
                    }
                });*/
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
                        if (rightData[j]["staffId"] === selections[i]["staffId"]) {
                            already.push(rightData[j]["staffId"]);
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
            if (data.length<1){
                $.messager.alert("提示","请选择需要分配改组织的账号！");
                return;
            }
            var ids="";
            $.each(data,function(index){
                ids = ids.concat(data[index]['staffId']);
                ids = ids.concat(",");
            });
            ids = ids.slice(0, ids.length - 1);
            var params ={
                "staffid":ids,
                "authobjectid":commonid,
                "authtypeid":authtypeid
            };
            AjaxUtilsTemp.commonAjax('post','/sam/dataAuth/assignDataAuth',JSON.stringify(params),false,'application/json',function (data) {
                if(data.resultVal == "1"){
                    $.messager.alert("提示","分配组织机构工号成功");
                }else{
                    $.messager.alert("提示","分配组织机构工号失败"+data.resultMsg);
                }
            },function () {
                $.messager.alert("提示","分配平台工号失败!");
            },'json');
/*            $.ajax({
                headers: {
                    Authorization: "Bearer " + token,
                },
                type: "post",
                url: '/sam/dataAuth/assignDataAuth',
                data: params,
                //async: async,
                //contentType:postType,
                error: function (request) {
                    $.messager.alert("提示","分配工号组织失败!");
                },
                success: function (result) {
                    if(result.resultVal == "1"){
                        $.messager.alert("提示","分配组织机构工号成功");
                    }else{
                        $.messager.alert("提示","分配组织机构工号失败"+result.resultMsg);
                    }

                }
            });*/
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

}

function  openOrgaEditView(orgaId) {

    $('#pop-window').window('open');
    AjaxUtilsTemp.commonAjax('GET','/sam/tsamorgainfo/'+orgaId,null,true,'text/plain',function (data) {
        $(".orgaId").val(data.ORGAID);
        $(".orgaCode").val(data.ORGACODE);
        $(".superOrgaCode").val(data.SUPERORGACODE);
        $(".orgaName").textbox('setValue', data.ORGANAME);
        $(".orgaTypeId").combobox('setValue', data.ORGATYPEID);
        $(".orgaState").combobox('setValue', data.ORGASTATE);
        $(".orgaDesc").textbox('setValue', data.ORGANAME);
        $("#tenantId").combobox('setText', data.tenantName);
        $("#tenantId").combobox('setValue', data.TENANTID);
    },'json');


}



function resultSearch(){
    $("#searchOrgaName").textbox('setValue',"");
    $("#searchTenantName").textbox('setValue',"");
    var zTree = $.fn.zTree.getZTreeObj("menuTree");
    var nodes = zTree.getSelectedNodes();
    if(nodes.length == 0){
        var fristnodes = zTree.getNodes();
        initOrgainfoGrid(fristnodes[0].id);
    }else{
        initOrgainfoGrid(nodes[0].id);
    }

}


function openOrgaView() {
    //$("#rightTable").attr('title',"asf");
    var zTree = $.fn.zTree.getZTreeObj("menuTree");
    var nodes = zTree.getSelectedNodes();
    if(nodes.length == 0){
        $.messager.alert('提示', '请选择树某个节点进行新增。');
        return;
    }
    if(nodes[0].level == 5){
        $.messager.alert('提示', '树节点深度限制为5级!');
        return;
    }
    if(nodes[0].id=="001")
    {
        $.messager.alert('提示', '根节点不允许新增数据对象!');
        return;
    }
    $('#pop-window1').window('open');
    $(".form").form('clear');
    $("#authconfigid").textbox('setValue', nodes[0].id);
    $("#authconfigid").textbox('textbox').attr('readonly',true);
}
var $popWindow3;
function openOrgaView1() {

    var zTree = $.fn.zTree.getZTreeObj("menuTree");
    var nodes = zTree.getSelectedNodes();
    if(nodes.length == 0){
        $.messager.alert('提示', '请选择树某个节点进行新增。');
        return;
    }
    if(nodes[0].level == 5){
        $.messager.alert('提示', '树节点深度限制为5级!');
        return;
    }
    $("#win_content3").show().window({
        width: 550,
        height: 350,
        modal: true,
        title: "新增数据权限树节点",
    });
    initAddDataElementPopWindow();
    initAddDataElementWindowEvent();
    //父节点
    $popWindow3.find("#rootvalue").textbox('setValue', nodes[0].id);
}
function initAddDataElementWindowEvent() {
    $popWindow3.on("click", "#global3", function () {
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
        var rootvalue = $popWindow3.find("input[name='rootvalue']").val().trim();
        var authtypeid = $popWindow3.find("input[name='authtypeid']").val().trim();
        var authtypename = $popWindow3.find("input[name='authtypename']").val().trim();
        var authtypedesc = $popWindow3.find("input[name='authtypedesc']").val().trim();

        if (!authtypeid) {
            $.messager.alert('提示', '请输入权限ID！');
            $(this).linkbutton({disabled: false});
        }else if(!authtypename){
            $.messager.alert('提示', '请输入权限名称！');
            $(this).linkbutton({disabled: false});
        }else{
            var sd = {
                "rootvalue":rootvalue,
                "authtypeid":authtypeid,
                "authtypename":authtypename,
                "authtypedesc":authtypedesc,
            }
            AjaxUtilsTemp.commonAjaxAsync('post','/sam/dataAuth/addAuthTreeNode',true,sd,function (data) {
                $("#global").linkbutton({disabled: false});

                if (data.resultVal === "1") {
                    $.messager.alert('提示', '操作成功！');
                    $("#win_content3").window("close");
                    $page.find("#account").datagrid("load");
                } else {
                    $.messager.alert('提示', data.resultMsg);
                }
            },function () {
                $.messager.alert("提示","新增数据权限树节点失败!");
            },'json');
        }


    });
    $popWindow3.on("click", "#cancel3", function () {
        $popWindow3.find('form.form').form('clear');
        $("#win_content3").window("close");
    });

}
function initAddDataElementPopWindow() {

    $("#win_content3").empty();
    $popWindow3 = $("<div></div>").appendTo($("#win_content3"));
    $([
        "<div class='panel-tool-box cl' >",
        //"<div class='fl text-bold'>基本信息</div>",
        "</div>",
        "<div class='panel-search'>",
        "<form id='createServiceAccount' method='POST' class='form form-horizontal'>",
        "<div style='display:none'>",
        "<input type='hidden'  name='serviceId' class='easyui-textbox' value='0'   />",
        "</div>",
        "<div class='row cl'>",
        "<label class='form-label col-4'>数据权限父节点：</label>",
        "<div class='formControls col-6'><input  type='text' id='rootvalue' name='rootvalue' class='easyui-textbox'  style='width:90%;height:30px' /><span style='color:red;padding-left:2px'>*</span></div>" +
        "</div>",
        "<div class='row cl'>",
        "<label class='form-label col-4'>数据权限ID：</label>" +
        "<div class='formControls col-6'><input  type='text' id='authtypeid' name='authtypeid' class='easyui-textbox' style='width:90%;height:30px' /><span style='color:red;padding-left:2px'>*</span></div>" +
        "</div>",
        "<div class='row cl'>",
        "<label class='form-label col-4'>数据权限名称：</label>" +
        "<div class='formControls col-6'><input  type='text' id='authtypename' name='authtypename' class='easyui-textbox' style='width:90%;height:30px' /><span style='color:red;padding-left:2px'>*</span></div>" +
        "</div>",
        "<div class='row cl'>",
        "<label class='form-label col-4'>权限描述：</label>" +
        "<div class='formControls col-6'><input  type='text' id ='authtypedesc' name='authtypedesc' class='easyui-textbox' style='width:90%;height:30px' /></div>" +
        "</div>",
        "</form>",
        "</div>"
    ].join("")).appendTo($popWindow3);
    $([
        "<div class='mt-10 test-c'>",
        "<label class='form-label col-5'></label>",
        "<a href='javascript:void(0)' id='global3' class='btn btn-green radius mt-l-20' >" +
        "保存</a>",
        "<span>          </span>",
        "<a href='javascript:void(0)' id='cancel3' class='btn btn-secondary radius mt-l-20' >" +
        "取消</a>",
        "</div>",
        "</div>"
    ].join("")).appendTo($popWindow3);
    //父节点
    $popWindow3.find("input.easyui-textbox").textbox();
    //$("#parentfield").textbox('setValue', asdf);
    $popWindow3.find("#rootvalue").textbox('textbox').attr('readonly',true);
    $popWindow3.find("a.btn").linkbutton();
}
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
function cancle1() {
    //新增成功 往tree节点上append数据。
    var treeObj = $.fn.zTree.getZTreeObj("menuTree");
    var node=treeObj.getNodeByParam('id',$(".orgaId").val());
    node.name=$(".orgaName").val();
    treeObj.updateNode(node);
    $("#win_content").window("close");
}

function ZtreeNode(id, pId, name) {//定义ztree的节点类
    this.id = id;
    this.pId = pId;
    this.name = name;
}




function ajaxLoading(){
    $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("#pop-window");
    $("<div class=\"datagrid-mask-msg\"></div>").html("正在加载数据，请稍候。。。").appendTo("#pop-window").css({display:"block",left:($(document.body).outerWidth(true) - 1100) / 2,top:($(window).height() - 240) / 2});
}
function ajaxLoadEnd(){
    $(".datagrid-mask").remove();
    $(".datagrid-mask-msg").remove();
}


//新增
function saveOrgaInfo(){
    //选中那个节点就绑定在哪个节点下，没有选就默认是最顶级。
    var zTree = $.fn.zTree.getZTreeObj("menuTree");
    var nodes = zTree.getSelectedNodes();
    var authconfigid = $("#authconfigid").textbox('getValue');
    var commonid =     $("#commonid").textbox('getValue');
    var commonname =   $("#commonname").textbox('getValue');
    var commoncode =   $("#commoncode").textbox('getValue');

    if(!authconfigid)
    {
        $.messager.alert("无权限ID！")
        return;
    }
    if(!commonid)
    {
        $.messager.alert("请输入编号！")
        return;
    }
    if(!commonname)
    {
        $.messager.alert("请输入名称！")
        return;
    }
    if(!commoncode)
    {
        $.messager.alert("请输入CODE！")
        return;
    }
    ajaxLoading();
    var data = {
        "authconfigid":authconfigid,"commonid":commonid,"commonname":commonname,"commoncode":commoncode
    };
    AjaxUtilsTemp.commonAjax('POST','/sam/dataAuth/addAuthData',JSON.stringify(data),true,'application/json',function (data) {
        ajaxLoadEnd();
        if(data.resultVal == 1){
            $('#dg').datagrid('reload'); //刷新grid
            $.messager.alert('提示', '新增成功!');
            $('#pop-window1').window('close');

        }else{
            $.messager.alert('提示', '新增失败!');
        }
    },function () {
        $.messager.alert("提示","新增失败!");
    },'json');



    // $.ajax({
    //     type: "POST",
    //     url: "/tsamorgainfo/insterTSamOrgaInfo",
    //     data: JSON.stringify(data),
    //     dataType: "json",
    //     contentType:"application/json",
    //     success: function(data){
    //         ajaxLoadEnd();
    //         if(data.flag == 1){
    //             $('#dg').datagrid('reload'); //刷新grid
    //             //刷新父节点
    //             var parentZNode = zTree.getNodeByParam("id", SUPERORGACODE, null); //获取父节点
    //             zTree.addNodes(parentZNode, new ZtreeNode(data.orgaId,SUPERORGACODE,$(".orgaName").val()), true);
    //             zTree.expandNode(parentZNode,true,true);
    //             $.messager.alert('提示', '新增成功!');
    //             $('#pop-window').window('close');
    //
    //         }else{
    //             $.messager.alert('提示', '新增失败!');
    //         }
    //
    //     }
    // });

}


function selectRepeatOrgaName(orgaName,nodeId){
    var result=0;
    AjaxUtilsTemp.commonAjax('GET','/sam/tsamorgainfo/selectRepeatOrgaName',{"orgaName":orgaName,"nodeId":nodeId},false,'application/json',function (data) {
        if(data.resultVal !='1'){
            $.messager.alert('提示', '获取角色重名异常.');
            return;
        }else{
            result = data.resultMsg;
        }
    },function () {
        $.messager.alert("提示","获取角色重名异常!");
    },'json');
    return result;

}


//修改
function updateOrgaInfo(){


    if($(".orgaName").val() == "" || $(".orgaName").val() == null){
        $.messager.alert('提示', '组织机构名称必填。');
        return;
    }

    if($(".orgaName").val().length>=10){
        $.messager.alert('提示', '组织机构名称长度过长,最长不超过10个字符。');
        return;
    }



    if($(".orgaState").combobox('getValue') == "" ||  $(".orgaState").combobox('getValue') == null){
        $.messager.alert('提示', '组织机构类型。');
        return;
    }

    if($(".orgaTypeId").combobox('getValue') == "" ||  $(".orgaTypeId").combobox('getValue') == null){
        $.messager.alert('提示', '组织机构状态。');
        return;
    }
    var tenantId = $update.find("#tenantId").combobox('getValue');
    //var tenantId1 =$update.find("#tenantId").combobox('getText');
    if(tenantId == "" || tenantId == null){
        $.messager.alert('提示', '请选择组所属租户。');
        return;
    }
    ajaxLoading();
    //选中那个节点就绑定在哪个节点下，没有选就默认是最顶级。
    var data = {opStaffId:AjaxUtilsTemp.getOpStaffId(),TENANTID:tenantId,SUPERORGACODE:$(".superOrgaCode").val(),ORGACODE:$(".orgaCode").val(),ORGAID:$(".orgaId").val(),ORGANAME:$(".orgaName").val(), ORGASTATE:$(".orgaState").combobox('getValue'),ORGATYPEID:$(".orgaTypeId").combobox('getValue'),orgaDesc:$(".orgaDesc").val()};


    AjaxUtilsTemp.commonAjax('POST','/sam/tsamorgainfo/updateTSamOrgaInfo',JSON.stringify(data),true,'application/json',function (data) {
        ajaxLoadEnd();
        //关闭弹出层
        if(data.resultVal == 1){
            $.messager.alert('提示',data.resultMsg);
            $('#dg').datagrid('reload'); //刷新grid
            //新增成功 往tree节点上append数据。
            var treeObj = $.fn.zTree.getZTreeObj("menuTree");
            var node=treeObj.getNodeByParam('id',$(".orgaId").val());
            node.name=$(".orgaName").val();
            treeObj.updateNode(node);
        }else{
            $.messager.alert('提示', data.resultMsg);
        }

    },function () {
        $.messager.alert("提示","修改数据失败!");
    },'json');


    // $.ajax({
    //     type: "POST",
    //     url: "/tsamorgainfo/updateTSamOrgaInfo",
    //     data: JSON.stringify(data),
    //     dataType: "json",
    //     contentType:"application/json",
    //     success: function(data){
    //         ajaxLoadEnd();
    //         //关闭弹出层
    //         if(data == 1){
    //             $('#pop-window').window('close');
    //             $('#dg').datagrid('reload'); //刷新grid
    //             //新增成功 往tree节点上append数据。
    //             var treeObj = $.fn.zTree.getZTreeObj("menuTree");
    //             var node=treeObj.getNodeByParam('id',$(".orgaId").val());
    //             node.name=$(".orgaName").val();
    //             treeObj.updateNode(node);
    //         }else{
    //             $.messager.alert('提示', '修改失败!');
    //         }
    //
    //     }
    // });

}


/**
 * 删除
 */
function deleteOrga(){
    var zTree = $.fn.zTree.getZTreeObj("menuTree");
    var row = $('#dg').datagrid('getSelections');
    if(row == null || row.length == 0){
        $.messager.alert('提示', '请选择一条数据删除。');
        return;
    }
    var nodes = zTree.getSelectedNodes();
    var authtypeid = nodes[0].id;
    var commonids=new Array();
    var nodes=new Array();
    for(var i = 0;i<row.length;i++){

        commonids.push(row[i].commonid);
    }

    $.messager.confirm("提示","确认删除？",function(sure) {
        if(sure) {
            AjaxUtilsTemp.commonAjax('GET', '/sam/dataAuth/deleteCommonAuthData', {"authconfigid":authtypeid,"commonid": commonids.join(",")}, true, 'application/json', function (data) {
                if (data.resultVal == "1") {
                    if (data.resultMsg == "") {
                        $.messager.alert('提示', '组织删除成功。');
                    } else {
                        $.messager.alert('提示', data.resultMsg);
                    }
                    if (nodes.length > 0) {
                        for (var i = 0; i < nodes.length; i++) {
                            zTree.removeNode(nodes[i]);
                        }
                    }
                    $('#dg').datagrid('reload'); //刷新grid
                    return;
                } else {
                    $.messager.alert('提示', '组织删除失败。'+data.resultMsg);
                    return;

                }
            },function () {
                $.messager.alert("提示","删除组织失败!");
            }, 'json');
        }else{
            return false;
        }



        //     var rowDatga = $('#dg').datagrid('getChecked');
        //     $.ajax({
        //         type: "GET",
        //         url: "/tsamorgainfo/deleteOrga",
        //         data:{"orgaIds":orgaIds.join(",")},
        //         dataType: "json",
        //         success: function(data){
        //             if(data.resultVal == "1"){
        //                 if(data.resultMsg == ""){
        //                     $.messager.alert('提示', '组织删除成功。');
        //                 }else{
        //                     $.messager.alert('提示', data.resultMsg);
        //                 }
        //                 if(nodes.length>0){
        //                     for(var i = 0;i<nodes.length;i++){
        //                         zTree.removeNode(nodes[i]);
        //                     }
        //                 }
        //                 $('#dg').datagrid('reload'); //刷新grid
        //                 return;
        //             }else{
        //                 $.messager.alert('提示', '组织删除失败。');
        //                 return;
        //
        //             }
        //         }
        //     });
        // }else{
        //     return false;
        // }
    });

}
function deleteOrga1(){
    var zTree = $.fn.zTree.getZTreeObj("menuTree");
    var nodes = zTree.getSelectedNodes();
    if(nodes.length == 0){
        $.messager.alert('提示', '请选择树某个节点进行新增。');
        return;
    }

    $.messager.confirm("提示","确认删除？",function(sure) {
        if(sure) {
            AjaxUtilsTemp.commonAjax('GET', '/sam/dataAuth/deleteDataAuthTree', {"authtypeid": nodes[0].id,"opStaffId":AjaxUtilsTemp.getOpStaffId()}, true, 'application/json', function (data) {
                if (data.resultVal == "1") {
                    if (data.resultMsg == "") {
                        $.messager.alert('提示', '组织删除成功。');
                    } else {
                        $.messager.alert('提示', data.resultMsg);
                    }
                    if (nodes.length > 0) {
                        for (var i = 0; i < nodes.length; i++) {
                            zTree.removeNode(nodes[i]);
                        }
                    }
                    $('#dg').datagrid('reload'); //刷新grid
                    return;
                } else {
                    $.messager.alert('提示', '组织删除失败。'+data.resultMsg);
                    return;

                }
            },function () {
                $.messager.alert("提示","删除组织失败!");
            }, 'json');
        }else{
            return false;
        }



        //     var rowDatga = $('#dg').datagrid('getChecked');
        //     $.ajax({
        //         type: "GET",
        //         url: "/tsamorgainfo/deleteOrga",
        //         data:{"orgaIds":orgaIds.join(",")},
        //         dataType: "json",
        //         success: function(data){
        //             if(data.resultVal == "1"){
        //                 if(data.resultMsg == ""){
        //                     $.messager.alert('提示', '组织删除成功。');
        //                 }else{
        //                     $.messager.alert('提示', data.resultMsg);
        //                 }
        //                 if(nodes.length>0){
        //                     for(var i = 0;i<nodes.length;i++){
        //                         zTree.removeNode(nodes[i]);
        //                     }
        //                 }
        //                 $('#dg').datagrid('reload'); //刷新grid
        //                 return;
        //             }else{
        //                 $.messager.alert('提示', '组织删除失败。');
        //                 return;
        //
        //             }
        //         }
        //     });
        // }else{
        //     return false;
        // }
    });

}



//查询是否有子节点
function selectSamOrgaCountBySuperCode(orgaId){
    var count="";
    // $.ajax({
    //     type: "GET",
    //     url: "/tsamorgainfo/selectSamOrgaCountBySuperCode/"+orgaId,
    //     dataType: "json",
    //     async:false,
    //     success: function(data){
    //         count = data.resultMsg;
    //     }
    // });
    AjaxUtilsTemp.commonAjax('GET','/sam/tsamorgainfo/selectSamOrgaCountBySuperCode/'+orgaId,null,false,'text/plain',function (data) {
        count = data.resultMsg;
    },function () {
        $.messager.alert("提示","获取子节点数据失败!");
    },'json');
    return count;
}



/**
 * 刷新当前选择节点的父节点
 */
function refreshParentNode(parentTId) {
    var zTree = $.fn.zTree.getZTreeObj("menuTree"),
        type = "refresh",
        silent = false,
        nodes = zTree.getSelectedNodes();
    var parentNode = zTree.getNodeByTId(nodes.tId);
    /*选中指定节点*/
    zTree.selectNode(parentNode);
    zTree.reAsyncChildNodes(parentNode, type, silent);
    zTree.expandNode(parentNode,true,true,false,false);
}




