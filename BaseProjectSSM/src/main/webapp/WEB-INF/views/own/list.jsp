<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../common/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
    <!-- Begin of toolbar -->
    <div id="wu-toolbar">
        <div class="wu-toolbar-button">
            <%@include file="../common/menus.jsp"%>
        </div>
        <div style="color: red">每个订单可以免费预约一名教练</div>
        <div class="wu-toolbar-search">
            <label>日期:</label><div id="search-date" data-options="editable:false" style="width:100px"></div>
            <label>订单类型:</label>
            <select id="search-ordertype" class="easyui-combobox" panelHeight="auto" style="width:120px">
            	<option value="-1">全部</option>
            	<c:forEach items="${ordertypelist}" var="ordertypeId">
            		<option value="${ordertypeId.id }">${ordertypeId.ordertype }</option>
            	</c:forEach>
            </select>
            <label>时间:</label>
            <select id="search-time" class="easyui-combobox" panelHeight="auto" style="width:120px">
                <option value="-1">全部</option>
                <c:forEach items="${timelist}" var="timeId">
                    <option value="${timeId.id }">${timeId.time }</option>
                </c:forEach>
            </select>

            <!--需要修改内容-->
            <label>所属场地:</label>
            <select id="search-venues" class="easyui-combobox" panelHeight="auto" style="width:200px">
                <option value="-1">全部</option>
                <c:forEach items="${venuesList}" var="venues">
                    <option value="${venues.id }">球馆：${venues.stadiumname }  球场：${venues.venuesname }</option>
                </c:forEach>
            </select>
            <a href="#" id="search-btn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar"></table>
</div>
<div id="process-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-upload',title:'正在上传图片'" style="width:450px; padding:10px;">
<div id="p" class="easyui-progressbar" style="width:400px;" data-options="text:'正在上传中...'"></div>
</div>
<input type="file" id="photo-file" style="display:none;" onchange="upload()">
<%@include file="../common/footer.jsp"%>

<!-- End of easyui-dialog -->
<script type="text/javascript">
    $('#search-date').datebox().datebox('calendar').calendar();
    //搜索按钮监听
    $("#search-btn").click(function(){
        var ordertypeid = $("#search-ordertype").combobox('getValue');
        var timeid = $("#search-time").combobox('getValue');
        var venuesid = $("#search-venues").combobox('getValue');
        $.fn.datetimebox.defaults.formatter = function(date){
            var y = date.getFullYear();
            var m = date.getMonth()+1;
            var d = date.getDate();
            var h = date.getHours();
            var minu = date.getMinutes();
            var sce =date.getSeconds();
            return y+'-'+m+'-'+d;
        }
        var option = {datestr:$("#search-date").datebox('getValue')};
        if(ordertypeid != -1){
            option.ordertypeid = ordertypeid;
        }
        if(timeid != -1){
            option.timeid = timeid;
        }
        if(venuesid != -1){
            option.venuesid = venuesid;
        }
        $('#data-datagrid').datagrid('reload',option);
    });

    /**
     * 删除记录
     */
    function remove(){
        $.messager.confirm('信息提示','确定要取消该订单？', function(result){
            if(result){
                var item = $('#data-datagrid').datagrid('getSelections');
                this.obj=item[0];
                if(item == null || item.length == 0){
                    $.messager.alert('信息提示','请选择要取消的订单！','info');
                    return;
                }
                // item=JSON.parse(JSON.stringify(item))
                // console.log(item)
                $.ajax({
                    url:'delete',
                    dataType:'json',
                    type:'post',
                    data:{orderid:item[0].id,orderdate:item[0].datestr,price:item[0].price,memberid:item[0].memberid},
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
			{ field:'datestr',title:'日期',width:100,sortable:true},
			{ field:'ordertypeid',title:'订单类型',width:100,formatter:function(value,row,index){
				var ordertypeList = $("#search-ordertype").combobox('getData');
				for(var i=0;i<ordertypeList.length;i++){
					if(value == ordertypeList[i].value) return ordertypeList[i].text;
				}
				return value;
			}},
            { field:'timeid',title:'时间',width:100,formatter:function(value,row,index){
                    var timeList = $("#search-time").combobox('getData');
                    for(var i=0;i<timeList.length;i++){
                        if(value == timeList[i].value) return timeList[i].text;
                    }
                    return value;
                }},
            { field:'venuesid',title:'所属球场',width:100,formatter:function(value,row,index){
                    var venuesList = $("#search-venues").combobox('getData');
                    for(var i=0;i<venuesList.length;i++){
                        if(value == venuesList[i].value) return venuesList[i].text;
                    }
                    return value;
                }},
            { field:'number',title:'预约人数',width:100},
            { field:'price',title:'消费',width:100},
            { field:'icon',title:'查看教练',width:100,formatter:function(value,row,index){
                    console.log(row.datestr)
                    var test = '<a onclick="Open(\''+row.id+'\',\''+row.datestr+'\')" href="#">查看</a>'
                    console.log(test)
                    return test;
                }}
		]]
	});

    function Open(id,datestr){
        console.log(id)
        console.log(id+'ddddd'+datestr)
        var timestamp1 = new Date(datestr).getTime();//结束时间
        var timestamp2 = new Date().getTime();//现在时间
        var d = timestamp1 - timestamp2;//d>0 没到时间  d<0时间已经过了
        if(d<0){
            $.messager.confirm('信息提示','订单已过期，不可预约教练', function(result){
                if(result){
                    location.replace("/BaseProjectSSM/admin/instructororder/list?id=" + id + "&datestr=" + datestr)
                }
            });
        }else {
            location.replace("/BaseProjectSSM/admin/instructororder/list?id=" + id + "&datestr=" + datestr)
        }
    }
</script>