<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../common/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
    <!-- Begin of toolbar -->
    <div id="wu-toolbar">
        <div class="wu-toolbar-button">
            <%@include file="../common/menus.jsp"%>
        </div>
        <div class="wu-toolbar-search">
            <label>球馆名称:</label><input id="search-name" class="wu-text" style="width:100px">
            <a href="#" id="search-btn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
        </div>

    </div>
    <!-- End of toolbar -->
    <table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar"></table>
</div>
<div id="add-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:420px; padding:10px;">
    <form id="add-form" method="post">
        <table>
            <tr>
                <td width="60" align="right">球场编号:</td>
                <td><input type="text" name="venuesname" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写用户名'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">所属球馆:</td>
                <td>
                    <select name="stadiumid" class="easyui-combobox" panelHeight="auto" style="width:268px" data-options="required:true, missingMessage:'请选择角色'">
                        <c:forEach items="${stadiumList }" var="stadiumId">
                            <option value="${stadiumId.id }">${stadiumId.stadiumname }</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td align="right">价格:</td>
                <td><textarea id="add-price" name="price" class="wu-textarea" style="width:260px"></textarea></td>
            </tr>
        </table>
    </form>
</div>
<!-- 修改窗口 -->
<div id="edit-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:450px; padding:10px;">
    <form id="edit-form" method="post">
        <input type="hidden" name="id" id="edit-id">
        <table>
            <tr>
                <td width="60" align="right">场地编号:</td>
                <td><input type="text" id="edit-username" name="venuesname" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写用户名'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">所属角色:</td>
                <td>
                    <select id="edit-roleId" name="stadiumid" class="easyui-combobox" panelHeight="auto" style="width:268px" data-options="required:true, missingMessage:'请选择角色'">
                        <c:forEach items="${stadiumList}" var="stadiumId">
                            <option value="${stadiumId.id }">${stadiumId.stadiumname }</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td align="right">价格:</td>
                <td><textarea id="edit-price" name="price" class="wu-textarea" style="width:260px"></textarea></td>
            </tr>
        </table>
    </form>
</div>
<%@include file="../common/footer.jsp"%>

<!-- End of easyui-dialog -->
<script type="text/javascript">

    /**
     *  添加记录
     */
    function add(){
        var validate = $("#add-form").form("validate");
        if(!validate){
            $.messager.alert("消息提醒","请检查你输入的数据!","warning");
            return;
        }
        var data = $("#add-form").serialize();
        $.ajax({
            url:'add',
            dataType:'json',
            type:'post',
            data:data,
            success:function(data){
                if(data.type == 'success'){
                    $.messager.alert('信息提示','添加成功！','info');
                    $('#add-dialog').dialog('close');
                    $('#data-datagrid').datagrid('reload');
                }else{
                    $.messager.alert('信息提示',data.msg,'warning');
                }
            }
        });
    }
    /*
    *  修改
    * */
    function edit(){
        var validate = $("#edit-form").form("validate");
        if(!validate){
            $.messager.alert("消息提醒","请检查你输入的数据!","warning");
            return;
        }
        var data = $("#edit-form").serialize();
        $.ajax({
            url:'edit',
            dataType:'json',
            type:'post',
            data:data,
            success:function(data){
                if(data.type == 'success'){
                    $.messager.alert('信息提示','修改成功！','info');
                    $("#edit-name").val('');
                    $("#edit-remark").val('');
                    $('#edit-dialog').dialog('close');
                    $('#data-datagrid').datagrid('reload');
                }else{
                    $.messager.alert('信息提示',data.msg,'warning');
                }
            }
        });
    }

    /**
     * 删除记录
     */
    function remove(){
        $.messager.confirm('信息提示','确定要删除该记录？', function(result){
            if(result){
                var item = $('#data-datagrid').datagrid('getSelections');
                if(item == null || item.length == 0){
                    $.messager.alert('信息提示','请选择要删除的数据！','info');
                    return;
                }
                var ids = '';
                for(var i=0;i<item.length;i++){
                    ids += item[i].id + ',';
                }
                $.ajax({
                    url:'delete',
                    dataType:'json',
                    type:'post',
                    data:{ids:ids},
                    success:function(data){
                        if(data.type == 'success'){
                            $.messager.alert('信息提示','删除成功！','info');
                            $('#data-datagrid').datagrid('reload');
                        }else{
                            $.messager.alert('信息提示',data.msg,'warning');
                        }
                    }
                });
            }
        });
    }

    /**
     * Name 打开添加窗口
     */
    function openAdd(){
        $('#add-form').form('clear');
        $('#add-dialog').dialog({
            closed: false,
            modal:true,
            title: "添加学科信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: add
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#add-dialog').dialog('close');
                }
            }],
            onBeforeOpen:function(){
                //$("#add-form input").val('');
            }
        });
    }
    //修改
    function openEdit(){

        var item = $('#data-datagrid').datagrid('getSelected');
        if(item == null || item.length == 0){
            $.messager.alert('信息提示','请选择要编辑的数据！','info');
            return;
        }

        //$('#add-form').form('clear');
        $('#edit-dialog').dialog({
            closed: false,
            modal:true,
            title: "编辑球馆信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: edit
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#edit-dialog').dialog('close');
                }
            }],
            onBeforeOpen:function(){
                //$("#add-form input").val('');
                $("#edit-id").val(item.id);
                $("#edit-name").val(item.stadiumname);
                $("#edit-remark").val(item.address);
            }
        });
    }

    //搜索按钮监听
    $("#search-btn").click(function(){
        var option = {name:$("#search-name").val()};
        $('#data-datagrid').datagrid('reload',option);
    });

    /**
     * 载入数据
     */
    $('#data-datagrid').datagrid({
        url:'list',
        rownumbers:true,
        singleSelect:false,
        pageSize:20,
        pagination:true,
        multiSort:true,
        fitColumns:true,
        idField:'id',
        treeField:'name',
        fit:true,
        columns:[[
            { field:'chk',checkbox:true},
            { field:'stadiumname',title:'球馆名称',width:100,sortable:true},
            { field:'address',title:'球馆地址',width:200},
            { field:'icon',title:'预约',width:100,formatter:function(value,row,index){
                    var test = '<a href="/BaseProjectSSM/admin/booking1/list?_mid=101&id='+row.id+'">预约</a>'
                    return test;
                }}
        ]],
    });
</script>