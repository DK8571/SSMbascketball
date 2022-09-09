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
            <label>日期:</label><div id="search-date" data-options="editable:false" style="width:100px"></div>
            <label>预约球场:</label>
            <select id="search-venues" class="easyui-combobox" data-options="editable:false" panelHeight="auto" style="width:400px">
                <option value="-1">全部</option>
                <c:forEach items="${venuesList}" var="venuesId">
                    <option value="${venuesId.id }">${venuesId.venuesname }   单价：${venuesId.price }元/人   全场价：${venuesId.allprice }元   最大人数：${venuesId.max }</option>
                </c:forEach>
            </select>
            <a href="#" id="search-btn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
            <a href="#" id="edit-btn" onclick="openEdit()" class="easyui-linkbutton" iconCls="icon-ok">预约</a>
            <a href="/BaseProjectSSM/admin/booking/list">返回</a>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar"></table>
</div>
<!-- Begin of easyui-dialog -->
<!-- 修改窗口 -->
<div id="edit-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:450px; padding:10px;">
	<form id="edit-form" method="post">
        <input type="hidden" name="timeid" id="edit-id">
        <input type="hidden" name="sum" id="edit-sum">
        <input type="hidden" name="quote" id="edit-quote">

        <table>
            <tr>
                <td width="60" align="right">人数:</td>
                <td><input type="text" id="edit-number" name="number" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写预约人数'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">预约活动:</td>
                <td>
                	<select id="edit-ordertypeid" name="ordertypeid" class="easyui-combobox" panelHeight="auto" style=" width:268px" >
                        <c:forEach items="${ordertypelist}" var="ordertype">
                            <option value="${ordertype.id }">${ordertype.ordertype }</option>
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
					$.messager.alert('信息提示','预约成功！','info');
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
            title: "预约",
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
            	$("#edit-id").val(item.timeid);
                $("#edit-sum").val(item.sum);
                $("#edit-quote").val(item.quote);
            }
        });
	}
    var curr_time = new Date();
    console.log(curr_time);
    $('#search-date').datebox().datebox('calendar').calendar({
        validator: function(date){
            var d1 = new Date(curr_time.getFullYear(), curr_time.getMonth(), curr_time.getDate());
            return d1<date;
        }
    });

	
	//搜索按钮监听
    $("#search-btn").click(function(){
        var venuesid = $("#search-venues").combobox('getValue');
        $.fn.datetimebox.defaults.formatter = function(date){
            var y = date.getFullYear();
            var m = date.getMonth()+1;
            var d = date.getDate();
            return y+'-'+m+'-'+d;
        }
        var option = {datestr:$("#search-date").datebox('getValue')};
        if(venuesid !== -1){
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
            { field:'time',title:'时间',width:100},
            { field:'sum',title:'预约人数',width:100},
            { field:'quotestr',title:'状态',width:100,formatter:function(value,row,index){
                if (value === "无人预定") {
                    var test = '<div style="color: green" >' + value + '</div>'
                }
                if (value === "有比赛不可预约"){
                    var test = '<div style="color: red" >' + value + '</div>'
                }
                if (value === "不可预约比赛"){
                    var test = '<div style="color: orange" >' + value + '</div>'
                }
                return test;
    }}
        ]]
	});
</script>