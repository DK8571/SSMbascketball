<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../common/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
    <!-- Begin of toolbar -->
    <div id="wu-toolbar">
        <div class="wu-toolbar-button">
            <%@include file="../common/menus.jsp"%>
        </div>
        <div style="color: red">如果为空则表明没有空闲教练，非常抱歉，您可以到前台领取小礼物一份</div>
        <div class="wu-toolbar-search">
            <label>教练名:</label><input id="search-name" class="wu-text" style="width:100px">
            <label>性别:</label>
            <select id="search-sex" class="easyui-combobox" panelHeight="auto" style="width:120px">
            	<option value="-1">全部</option>
            	<option value="0">未知</option>
            	<option value="1">男</option>
            	<option value="2">女</option>
            </select>
            <a href="#" id="search-btn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
            <a href="#" id="edit-btn" onclick="openEdit()" class="easyui-linkbutton" iconCls="icon-ok">预约</a>
            <a href="#" id="edit-" onclick="remove()" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
            <a href="/BaseProjectSSM/admin/own/list?_mid=105">返回</a>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar"></table>
</div>
</div>
<%@include file="../common/footer.jsp"%>

<!-- End of easyui-dialog -->
<script type="text/javascript">
	/**
	* 删除记录
	*/
	function openEdit(){
		$.messager.confirm('信息提示','确定要预约该教练？', function(result){
			if(result){
				var item = $('#data-datagrid').datagrid('getSelections');
				if(item == null || item.length == 0){
					$.messager.alert('信息提示','请选择要预约的教练！','info');
					return;
				}
                if(item[0].t == 1){
                    $.messager.alert('信息提示','该教练您已预约！','warning');
                    return;
                }
				$.ajax({
					url:'add',
					dataType:'json',
					type:'post',
					data:{instructorid:item[0].id},
					success:function(data){
						if(data.type == 'success'){
							$.messager.alert('信息提示','预约成功！','info');
							$('#data-datagrid').datagrid('reload');
						}else{
							$.messager.alert('信息提示',data.msg,'warning');
						}
					}
				});
			}	
		});
	}

    function remove(){
        $.messager.confirm('信息提示','确定要取消预约该教练？', function(result){
            if(result){
                var item = $('#data-datagrid').datagrid('getSelections');
                this.obj=item[0];
                if(item == null || item.length == 0){
                    $.messager.alert('信息提示','请选择要取消预约的教练！','info');
                    return;
                }
                $.ajax({
                    url:'delete',
                    dataType:'json',
                    type:'post',
                    data:{instructorid:item[0].id},
                    success:function(data){
                        if(data.type == 'success'){
                            $.messager.alert('信息提示','取消成功！','info');
                            $('#data-datagrid').datagrid('reload');
                        }else{
                            $.messager.alert('信息提示',data.msg,'warning');
                        }
                    }
                });
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
			{ field:'name',title:'教练名',width:100,sortable:true},
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
            { field:'t',title:'状态',width:100,formatter:function(value,row,index){
                    switch(value){
                        case 0:{
                            return '未预约';
                        }
                        case 1:{
                            return '已预约';
                        }
                    }
                    return value;
                }},
		]]
	});
</script>