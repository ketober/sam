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
    $('#tenantId1').combobox({
        // url: '../../data/skill-priority.json',
        valueField: 'codeValue',
        textField: 'codeName',
        panelHeight: 'auto',
        disabled:false,
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
                            codeValue: item["tenantId1"],
                            codeName: item["tenantName1"]
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
            url: "/sam/tsamorgainfo/selectSamOrgaTree?access_token="+token+"&opStaffId="+AjaxUtilsTemp.getOpStaffId(),
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
            {field :'ck',checkbox : true,width : '10%' },
            {field:'orgaId',title:'组织机构编号',width:'23%'},
            {field:'orgaName',title:'组织机构名称',width:'25%'},
            {field:'orgaTypeName',title:'组织机构类型',width:'10%'},
            {field:'orgaStateName',title:'组织机构状态',width:'10%'},
            {field:'tenantName',title:'所属租户',width:'20%'},
            {field: 'opera', title: '操作', width: '10%',formatter: rowformater}
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
                "orgaName":$("#searchOrgaName").val(),
                "tenantName":$("#searchTenantName").val(),
                "page":pageNum,
                "start":start};
            // $.ajax({
            //     url: "/tsamorgainfo/selectTSamOrgaGrid?nodeId="+id, //获取数据后台接口
            //     method: "GET",
            //     data: params,
            //     dataType: "json",
            //     success: function (data) {
            //         success(data);
            //     }
            // });

            AjaxUtilsTemp.commonAjax('GET','/sam/tsamorgainfo/selectTSamOrgaGrid?nodeId='+id,params,true,'text/plain',function (data) {
                success(jQuery.parseJSON(data));
            },function () {
                $.messager.alert("提示","获取数据异常!");
            },'json');


        },


    });
}


function rowformater(value, row, index) {
    var orgaId = row.orgaId;
    return  "<a onclick='initWinContent(),initUpdateWindow(\""+orgaId+"\");'>编辑</a>";
}

//修改弹出窗口
window.initWinContent =function initWinContent(){
    $("#win_content").show().window({
        width:950,
        height:550,
        modal:true,
        title:"修改组织结构信息"
    });
};

//修改页面初始化
window.initUpdateWindow = function initUpdateWindow(orgaId) {
    $("#win_content").empty();
    // $popWindow = $("<div id='tt' class='easyui-tabs' style='fit:true;overflow:auto;'>").appendTo($("#win_content"));
    $popWindow = $("<div id='tt' class='easyui-tabs'>").appendTo($("#win_content"));
    var $winContent =
        $([
            "<div title=\"基本信息\" style=\"padding:20px;\">\n" +
            "        <div data-options=\"region:'center'\" style=\"overflow: auto;\">\n" +
            "            <div id=\"baseInfo_page_content\" data-options=\"region:'center'\">\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "<div title=\"人员分配\"  style=\"overflow:auto;padding:20px;\">\n" +
            "        <div data-options=\"region:'center'\" style=\"overflow: auto;\">\n" +
            "            <div id=\"plat_page_content\" data-options=\"region:'center'\">\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "    </div>"
        ].join("")).appendTo($popWindow);
    initConfig(orgaId);
};

//修改页面tab页初始化及事件
window.initConfig=function initConfig(orgaId){
    var $updatePage;
    var $platPage;
    $updatePage = $("<div></div>").appendTo($("#baseInfo_page_content"));
    $platPage = $("<div></div>").appendTo($("#plat_page_content"));
    $.parser.parse();
    var token = AjaxUtilsTemp.getToken();
    initSearchGrid();
    initPlatSearch();
    initPlatTab();
    initPlatEvent()
    function initSearchGrid() {
        $update = $([
            "<div class='panel-search'style='height: 300px;'>"+
            "                <form action='' method='post' class='form form-horizontal'>"+
            "                    <div class='row cl'>"+
            "                        <label class='form-label col-4'>组织机构名称：</label>"+
            "                        <div class='formControls col-6'>"+
            "                            <input type='hidden' class='orgaId' style='height:30px' />"+
            "                            <input type='hidden' class='orgaCode' style='height:30px' />"+
            "                            <input type='hidden' class='superOrgaCode' style='height:30px' />"+
            "                            <input type='text' class='easyui-textbox orgaName' style='height:30px' />"+
            "                        </div>"+
            "                        <div class='formControls col-2' style='padding-top: 10px;'>"+
            "                            <span style='color:red;padding-left:25px;text-align: center'>*</span>"+
            "                        </div>"+
            ""+
            "                    </div>"+
            ""+
            "                    <div class='row cl'>"+
            "                        <label class='form-label col-4'>组织机构类型：</label>"+
            "                        <div class='formControls col-6'>"+
            "                            <select  style='height:30px' class='easyui-combobox orgaTypeId' >"+
            "                                <option value='-1'>--请选择--</option>"+
            "                                <option value='1'>公司</option>"+
            "                                <option value='2'>省份</option>"+
            "                                <option value='3'>地市</option>"+
            "                                <option value='4'>组织</option>"+
            "                                <option value='5'>班组</option>"+
            "                            </select>"+
            "                        </div>"+
            "                        <div style='padding-top: 10px;position: initial;'>"+
            "                            <span style='color:red;padding-left:25px;text-align: center'>*</span>"+
            "                        </div>"+
            "                    </div>"+
            ""+
            "                    <div class='row cl'>"+
            "                        <label class='form-label col-4'>组织机构状态：</label>"+
            "                        <div class='formControls col-6'>"+
            "                            <select  style='height:30px' class='easyui-combobox orgaState' >"+
            "                                <option value='-1'>--请选择--</option>"+
            "                                <option value='0'>在用</option>"+
            "                                <option value='1'>废弃</option>"+
            "                                <option value='2'>暂停</option>"+
            "                            </select>"+
            "                        </div>"+
            "                        <div  style='padding-top: 10px;position: initial;'>"+
            "                            <span style='color:red;padding-left:25px;text-align: center'>*</span>"+
            "                        </div>"+
            "                    </div>"+
            "                    <div class='row cl'>"+
            "                    <label class='form-label col-4'>租户名称：</label>"+
            "                    <div class='formControls col-6' >"+
            "                    <input type='text' class='easyui-combobox' id= 'tenantId' name='tenantId'style='height:30px' >"+
            "                    </div>"+
            "                        <div  style='padding-top: 10px;position: initial;'>"+
            "                            <span style='color:red;padding-left:25px;text-align: center'>*</span>"+
            "                        </div>"+
            "                    </div>"+
            "                    <div class='row cl'>"+
            "                        <label class='form-label col-4'>组织机构描述：</label>"+
            "                        <div class='formControls col-6'>"+
            "                            <input class='easyui-textbox orgaDesc' data-options='multiline:true'  style='height:100px;'></input>"+
            "                        </div>"+
            "                    </div>"+
            "                </form>"+
            "            </div>"+
            "<div class='row cl'>",
            "<div class='mt-10 text-c '>" +
            "<a href='javascript:void(0)' id='global' onclick=\"dynameicOrgaOperation()\" class='btn btn-green radius mt-l-20' >" +
            "保存</a>",
            "<span>          </span>",
            "<a href='javascript:void(0)' id='cancel1' onclick=\"cancle1()\" class='btn btn-secondary radius mt-l-20' >" +
            "取消</a>",
            "</div>",
            "</div>",
            "</form>",
            "</div>"
        ].join("")).appendTo($updatePage);

        $update.find("input.easyui-textbox").textbox();
        $update.find("select.easyui-combobox").combobox();
        $update.find("input.easyui-datetimebox").datetimebox({
            editable: false
        });
        $update.find("a.easyui-linkbutton").linkbutton();
        $update.find("a.btn").linkbutton();
        $update.find('#tenantId').combobox({
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
        AjaxUtilsTemp.commonAjax('GET','/sam/tsamorgainfo/'+orgaId,null,true,'text/plain',function (data) {
            $(".orgaId").val(data.ORGAID);
            $(".orgaCode").val(data.ORGACODE);
            $(".superOrgaCode").val(data.SUPERORGACODE);
            $(".orgaName").textbox('setValue', data.ORGANAME);
            $(".orgaTypeId").combobox('setValue', data.ORGATYPEID);
            $(".orgaState").combobox('setValue', data.ORGASTATE);
            $(".orgaDesc").textbox('setValue', data.ORGANAME);
            $update.find("#tenantId").combobox('setText', data.tenantName);
            $update.find("#tenantId").combobox('setValue', data.TENANTID);
        },'json');

    }

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
    function initPlatTab() {
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
                /*if(data.staffId == "" && data.staffName=="" && data.staffIdMax=="" && data.staffIdMin=="")
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
                var params ={"orgaId":orgaId,
                    "opStaffId":AjaxUtilsTemp.getOpStaffId()
                };
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
                $.ajax({
                    headers: {
                        Authorization: "Bearer " + token,
                    },
                    type: "post",
                    url: '/sam/StaffInfo/qryStaffInfo',
                    data: params,
                    //async: async,
                    //contentType:postType,
                    error: function (request) {
                        $.messager.alert("提示","查询已分配平台工号信息失败!");
                    },
                    success: function (result) {
                        //console.log("result =====" + JSON.stringify(result));
                        var dd = {"total":result.total,"rows":result.list};
                        success(dd);
                    }
                });
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
                "orgaId":orgaId,
                "staffIds":ids
            };
/*            AjaxUtilsTemp.commonAjax('post','/sam/StaffInfo/updateStaffOrgaInfo',params,function (data) {
                if(data.resultVal == "1"){
                    $.messager.alert("提示","分配组织机构工号成功");
                }else{
                    $.messager.alert("提示","分配组织机构工号失败"+data.resultMsg);
                }
            },function () {
                $.messager.alert("提示","分配平台工号失败!");
            },'json');*/
            $.ajax({
                headers: {
                    Authorization: "Bearer " + token,
                },
                type: "post",
                url: '/sam/StaffInfo/updateStaffOrgaInfo',
                data: params,
                //async: async,
                //contentType:postType,
                error: function (request) {
                    $.messager.alert("提示","分配工号组织失败!");
                },
                success: function (result) {
                    if(result.resultVal == "1"){
                        $.messager.alert("提示","分配组织机构工号成功</br>"+result.resultMsg);
                    }else{
                        $.messager.alert("提示","分配组织机构工号失败"+result.resultMsg);
                    }

                }
            });
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
    $('#pop-window1').window('open');
    $(".form").form('clear');
    AjaxUtilsTemp.commonAjax('POST','/sam/StaffInfo/qryStaffInfo?staffId='+ AjaxUtilsTemp.getOpStaffId(),null,true,'text/plain',function (data) {
        $("#tenantId1").combobox('setValue',  data.list[0].tenantId);
        $("#tenantId1").combobox('setText',  data.list[0].tenantName);
    },'json');

    $(".orgaTypeId").combobox("setValue",-1);
    $(".orgaState").combobox("setValue",-1);
}



//新增修改入口,根据主键id 判断是新增还是修改
function dynameicOrgaOperation(){
    var orgaId = $(".orgaId").val();
    if(orgaId == "" || orgaId == null){
        saveOrgaInfo();
    }else{
        updateOrgaInfo();
    }
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
    var SUPERGROUPCODE;
    var zTree = $.fn.zTree.getZTreeObj("menuTree");
    var nodes = zTree.getSelectedNodes();

    var orgaName = $(".orgaName").textbox('getValue');
    if(orgaName == "" || orgaName == null){
        $.messager.alert('提示', '组织机构名称必填。');
        return;
    }

    if(orgaName.length>=10){
        $.messager.alert('提示', '组织机构名称长度过长,最长不超过10个字符。');
        return;
    }


    var result = selectRepeatOrgaName(orgaName, nodes[0].id);
    if(result !=0){
        $.messager.alert('提示', '组织机构名称重复,请重新填写。');
        return;
    }

    if($(".orgaTypeId").combobox('getValue') == "" || $(".orgaTypeId").combobox('getValue') == null || $(".orgaTypeId").combobox('getValue') == "-1"){
        $.messager.alert('提示', '请选择组织机构类型。');
        return;
    }
    if($(".orgaState").combobox('getValue') == "" || $(".orgaState").combobox('getValue') == null || $(".orgaState").combobox('getValue') == "-1"){
        $.messager.alert('提示', '请选择组织机构状态。');
        return;
    }
    var tenantId = $("#tenantId1").combobox('getValue');
    //var tenantId1 =$update.find("#tenantId").combobox('getText');
    if(tenantId == "" || tenantId == null){
        $.messager.alert('提示', '请选择组所属租户。');
        return;
    }
    ajaxLoading();
    if(nodes[0] != null){
        SUPERORGACODE = nodes[0].id;
    }else{
        SUPERORGACODE = '1';
    }
    var data = {SUPERORGACODE:""+SUPERORGACODE+"",TENANTID:tenantId,ORGANAME:$(".orgaName").val(), ORGASTATE:$(".orgaState").combobox('getValue'),ORGATYPEID:$('.orgaTypeId').combobox('getValue'),orgaDesc:$(".orgaDesc").val(),opStaffId:AjaxUtilsTemp.getOpStaffId()};
    AjaxUtilsTemp.commonAjax('POST','/sam/tsamorgainfo/insterTSamOrgaInfo',JSON.stringify(data),true,'application/json',function (data) {
        ajaxLoadEnd();
        if(data.flag == 1){
            $('#dg').datagrid('reload'); //刷新grid
            //刷新父节点
            var parentZNode = zTree.getNodeByParam("id", SUPERORGACODE, null); //获取父节点
            zTree.addNodes(parentZNode, new ZtreeNode(data.orgaId,SUPERORGACODE,$(".orgaName").val()), true);
            zTree.expandNode(parentZNode,true,true);
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

    var orgaIds=new Array();
    var nodes=new Array();
    for(var i = 0;i<row.length;i++){
        var node=zTree.getNodeByParam('id',row[i].orgaId);
        var count = selectSamOrgaCountBySuperCode(row[i].orgaId);
        if(count ==0 ){
            nodes.push(node);
        }
         orgaIds.push(row[i].orgaId);
    }

    $.messager.confirm("提示","确认删除？",function(sure) {
        if(sure) {
            AjaxUtilsTemp.commonAjax('GET', '/sam/tsamorgainfo/deleteOrga', {"orgaIds": orgaIds.join(","),"opStaffId":AjaxUtilsTemp.getOpStaffId()}, true, 'application/json', function (data) {
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




