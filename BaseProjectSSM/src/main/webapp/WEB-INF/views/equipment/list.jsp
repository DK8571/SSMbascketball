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
            <label>设备名称:</label><input id="search-name" class="wu-text" style="width:100px">
            <label>所属场地:</label>
            <select id="search-venues" class="easyui-combobox" panelHeight="auto" style="width:120px">
            	<option value="-1">全部</option>
            	<c:forEach items="${venuesList}" var="venues">
            		<option value="${venues.id }">${venues.venuesname }</option>
            	</c:forEach>
            </select>
            <label>操作人:</label>
            <select id="search-user" class="easyui-combobox" panelHeight="auto" style="width:120px">
                <option value="-1">全部</option>
                <c:forEach items="${userList}" var="user">
                    <option value="${user.id }">${user.username }</option>
                </c:forEach>
            </select>
            <a href="#" id="search-btn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar"></table>
</div>
<!-- Begin of easyui-dialog -->
<div id="add-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:420px; padding:10px;">
	<form id="add-form" method="post">
        <input type="hidden" name="userid" class="wu-text easyui-validatebox" value="${admin.id }">
        <table>
            <tr>
                <td width="60" align="right">设备名称:</td>
                <td><input type="text" name="equipmentname" id="add-equipmentname"  class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写用户名'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">备注:</td>
                <td><input type="text" name="remark" id="add-remark" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写用户名'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">所属场地:</td>
                <td>
                	<select name="venuesid" id="add-venuesid" class="easyui-combobox" panelHeight="auto" style="width:268px" data-options="required:true, missingMessage:'请选择角色'">
                        <c:forEach items="${venuesList}" var="venues">
                            <option value="${venues.id }">${venues.venuesname }</option>
                        </c:forEach>
		            </select>
                </td>
            </tr>
        </table>
    </form>
</div>
<!-- 修改窗口 -->
<div id="edit-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:450px; padding:10px;">
	<form id="edit-form" method="post">
        <input type="hidden" name="id" id="edit-id">
        <input type="text" name="id" value="${venuesid }">
        <table>
            <input  type="hidden" name="userid" class="wu-text easyui-validatebox" value="${admin.id}">
            <tr>
                <td width="60" align="right">设备名称:</td>
                <td><input type="text" id="edit-equipmentname" name="equipmentname" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写用户名'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">备注:</td>
                <td><input type="text" id="edit-remark" name="remark" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写用户名'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">所属场地:</td>
                <td>
                	<select id="edit-venuesid" name="venuesid" class="easyui-combobox" panelHeight="auto" style="width:268px" data-options="required:true, missingMessage:'请选择角色'">
                        <c:forEach items="${venuesList}" var="venues">
                            <option value="${venues.id }">${venues.venuesname }</option>
                        </c:forEach>
		            </select>
                </td>
            </tr>
        </table>
    </form>
</div>

<div id="process-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-upload',title:'正在上传图片'" style="width:450px; padding:10px;">
<div id="p" class="easyui-progressbar" style="width:400px;" data-options="text:'正在上传中...'"></div>
</div>
<input type="file" id="photo-file" style="display:none;" onchange="upload()">
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
					$.messager.alert('信息提示','修改成功！','info');
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
                            $("#add-equipmentname").val('')
                            $("#add-remark").val('')
                            $('#data-datagrid').datagrid('clearSelections');
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
        $('#add-venuesid').combobox('setValue','1');
		$('#add-dialog').dialog({
			closed: false,
			modal:true,
            title: "添加用户信息",
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
	
	/**
	* 打开修改窗口
	*/
	function openEdit(){
		//$('#edit-form').form('clear');
		var item = $('#data-datagrid').datagrid('getSelections');
		if(item == null || item.length == 0){
			$.messager.alert('信息提示','请选择要修改的数据！','info',item);
			return;
		}
		if(item.length > 1){

			$.messager.alert('信息提示','请选择一条数据进行修改！','info');
			return;
		}
		item = item[0];
		$('#edit-dialog').dialog({
			closed: false,
			modal:true,
            title: "修改用户信息",
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
            	$("#edit-equipmentname").val(item.equipmentname);
                $("#edit-remark").val(item.remark)
            	$("#edit-venuesid").combobox('setValue',item.venuesid);
            }
        });
	}	
	
	
	//搜索按钮监听
	$("#search-btn").click(function(){
		var userid = $("#search-user").combobox('getValue');
        var venuesid = $("#search-venues").combobox('getValue');
		var option = {equipmentname:$("#search-name").val()};
		if(userid != -1){
			option.userid = userid;
		}
        if(venuesid != -1){
            option.venuesid = venuesid;
        }
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
            { field:'id',title:'时间id',width:100},
			{ field:'equipmentname',title:'设备名称',width:100,sortable:true},
            { field:'remark',title:'备注',width:100,sortable:true},
            // { field:'id',title:'所属球馆',width:200,sortable:true},
			{ field:'userid',title:'操作人',width:100,formatter:function(value,row,index){
				var userList = $("#search-user").combobox('getData');
				for(var i=0;i<userList.length;i++){
					if(value == userList[i].value) return userList[i].text;
				}
				return value;
			}},
            { field:'venuesid',title:'所属场地',width:100,formatter:function(value,row,index){
                    var venuesList = $("#search-venues").combobox('getData');
                    for(var i=0;i<venuesList.length;i++){
                        if(value == venuesList[i].value) return venuesList[i].text;
                    }
                    return value;
                }}
		]]
	});
</script>