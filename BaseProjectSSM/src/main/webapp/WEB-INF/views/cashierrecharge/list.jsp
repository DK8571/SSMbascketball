<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../common/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
    <!-- Begin of toolbar -->
    <div id="wu-toolbar">
        <div class="wu-toolbar-button">
            <%@include file="../common/menus.jsp"%>
        </div>
        <div class="wu-toolbar-search">
            <label>用户名:</label><input id="search-name" class="wu-text" style="width:100px">
            <label>性别:</label>
            <select id="search-sex" class="easyui-combobox" panelHeight="auto" style="width:120px">
            	<option value="-1">全部</option>
            	<option value="0">未知</option>
            	<option value="1">男</option>
            	<option value="2">女</option>
            </select>
            <a href="#" id="search-btn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar"></table>
</div>
<!-- Begin of easyui-dialog -->
<!-- 修改窗口 -->
<div id="edit-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:450px; padding:10px;">
	<form id="edit-form" method="post">
        <input type="hidden" name="id" id="edit-id">
        <table>
            <tr>
                <td width="60" align="right">充值金额:</td>
                <td><input type="text" id="edit-balance" name="balance" class="wu-text easyui-validatebox" /></td>
            </tr>
        </table>
    </form>
</div>
<%@include file="../common/footer.jsp"%>

<!-- End of easyui-dialog -->
<script type="text/javascript">
	/**
	* Name 修改记录
	*/
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
					$.messager.alert('信息提示',data.msg,'info');
					$('#edit-dialog').dialog('close');
					$('#data-datagrid').datagrid('reload');
				}else{
					$.messager.alert('信息提示',data.msg,'warning');
				}
			}
		});
	}
	/**
	* 打开修改窗口
	*/
	function openAdd(){
		//$('#edit-form').form('clear');
		var item = $('#data-datagrid').datagrid('getSelections');
		if(item == null || item.length == 0){
			$.messager.alert('信息提示','请选择要充值用户！','info');
			return;
		}
		if(item.length > 1){
			$.messager.alert('信息提示','请选择一名会员进行充值！','info');
            // window.location.reload(item);
			return;
		}
		item = item[0];
		$('#edit-dialog').dialog({
			closed: false,
			modal:true,
            title: "会员充值",
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
                $("#edit-id").val(item.id);
            }
        });
	}
	//搜索按钮监听
	$("#search-btn").click(function(){
		var sex = $("#search-sex").combobox('getValue')
		var option = {username:$("#search-name").val()};
		if(sex != -1){
			option.sex = sex;
		}
		$('#data-datagrid').datagrid('reload',option);
	});
	/** 
	* 载入数据
	*/
	$('#data-datagrid').datagrid({
		url:'list',
		rownumbers:true,
		singleSelect:true,
		pageSize:20,           
		pagination:true,
		multiSort:true,
		fitColumns:true,
		idField:'id',
	    treeField:'name',
		fit:true,
		columns:[[
			{ field:'chk',checkbox:true},
			{ field:'photo',title:'用户头像',width:100,align:'center',formatter:function(value,row,index){
				var img = '<img src="'+value+'" width="50px" />';
				return img;
			}},
			{ field:'username',title:'用户名',width:100,sortable:true},
            { field:'name',title:'姓名',width:100},
			{ field:'sex',title:'性别',width:100,formatter:function(value,row,index){
				switch(value){
					case 0:{
						return '未知';
					}
					case 1:{
						return '男';
					}
					case 2:{
						return '女';
					}
				}
				return value;
			}},
			{ field:'age',title:'年龄',width:100},
			{ field:'address',title:'地址',width:200},
            { field:'balance',title:'余额',width:200}
		]]
	});
</script>