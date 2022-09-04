<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../common/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<body style="height: 100%;width: 100%;" onload="setsex()">
<div style=" margin: auto auto;height: 900px;width: 900px">
    <div style="margin: 100px auto;;height: 800px;width: 800px">
        <form id="edit-form" style=" margin:  auto;height: 400px;width: 500px;text-align:center;" method="post">
            <input type="hidden" name="id" id="edit-id" value="${Member[0].id }">
            <table style="margin: auto auto;height: 300px;width: 400px;text-align:center;">
                <tr style="text-align:center;">
<%--                    <td width="60" align="right">头像预览:</td>--%>
                    <td>
                        <td style="margin-left: 100px"></td>
                        <img id="preview-photo" style="Border-radius:60%" src="${Member[0].photo }"onclick="uploadPhoto()" height="100px" width="100px">
<%--                        <a style="float:left;margin-top:40px;" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-upload" onclick="uploadPhoto()" plain="true">上传图片</a>--%>
                    </td>
                </tr>
                <tr>
                    <td width="60" align="right">头像:</td>
                    <td><input type="text" id="add-photo" name="photo" value="${Member[0].photo }" readonly="readonly" class="wu-text " /></td>
                </tr>
                <tr>
                    <td width="60" align="right">用户名:</td>
                    <td><input type="text" id="add-username" name="username" class="wu-text easyui-validatebox" readonly="readonly" value="${Member[0].username }"/></td>
                </tr>
                <tr>
                    <td width="60" align="right">姓名:</td>
                    <td><input type="text" id="add-name" name="name" class="wu-text easyui-validatebox"  value="asd"/></td>
                </tr>
                <tr>
                    <td width="60" align="right">性别:</td>
                    <td>
                        <select name="sex" id="add-sex" class="easyui-combobox" panelHeight="auto" style="width:268px">
                            <option value="0">未知</option>
                            <option value="1">男</option>
                            <option value="2">女</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="60" align="right">年龄:</td>
                    <td><input type="text" id="add-age" value="${Member[0].age }" name="age" class="wu-text easyui-numberbox easyui-validatebox"/></td>
                </tr>
                <tr>
                    <td width="60" align="right">地址:</td>
                    <td><input type="text" id="add-address" value="${Member[0].address }" name="address" class="wu-text easyui-validatebox" /></td>
                </tr>
                <tr>
                    <td width="60" align="right">余额:</td>
                    <td><input type="text" id="add-balance" value="${Member[0].balance }" name="balance" readonly="readonly" class="wu-text easyui-validatebox" /></td>
                </tr>

            </table>
            <input type="button" id="edit-btn"  class="edit-btn" value="保存">
        </form>
    </div>
<div id="process-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-upload',title:'正在上传图片'" style="width:450px; padding:10px;">
<div id="p" class="easyui-progressbar" style="width:400px;" data-options="text:'正在上传中...'"></div>
</div>
<input type="file" id="photo-file" style="display:none;" onchange="upload()">
</div>
<script type="text/javascript">
    //上传图片
    function start(){
        var value = $('#p').progressbar('getValue');
        if (value < 100){
            value += Math.floor(Math.random() * 10);
            $('#p').progressbar('setValue', value);
        }else{
            $('#p').progressbar('setValue',0)
        }
    };
    function upload(){
        if($("#photo-file").val() == '')return;
        var formData = new FormData();
        formData.append('photo',document.getElementById('photo-file').files[0]);
        $("#process-dialog").dialog('open');
        var interval = setInterval(start,200);
        $.ajax({
            url:'upload_photo',
            type:'post',
            data:formData,
            contentType:false,
            processData:false,
            success:function(data){
                clearInterval(interval);
                $("#process-dialog").dialog('close');
                if(data.type == 'success'){
                    $("#preview-photo").attr('src',data.filepath);
                    $("#add-photo").val(data.filepath);
                }else{
                    $.messager.alert("消息提醒",data.msg,"warning");
                }
            },
            error:function(data){
                clearInterval(interval);
                $("#process-dialog").dialog('close');
                $.messager.alert("消息提醒","上传失败!","warning");
            }
        });
    }

    function uploadPhoto(){
        $("#photo-file").click();
    }

    $("#edit-btn").click(function(){
        var validate = $("#edit-form").form("validate");
        if(!validate){
            $.messager.alert("消息提醒","请检查你输入的数据!","warning");
            return;
        }
        var data = $("#edit-form").serialize();
        console.log(data)
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

    });

    function setsex(){
        $("#add-sex").combobox('setValue',${Member[0].sex })
    }
</script>
</body>
</html>
