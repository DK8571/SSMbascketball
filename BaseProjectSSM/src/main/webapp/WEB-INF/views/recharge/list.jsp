<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../common/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
    <!-- Begin of toolbar -->
    <div id="wu-toolbar">
        <div class="wu-toolbar-button">
            <%@include file="../common/menus.jsp"%>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar"></table>
</div>
<!-- Begin of easyui-dialog -->
<div id="add-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:420px; padding:10px;">
    <form id="add-form" action=/BaseProjectSSM/admin/recharge/showPay name=alipayment  method=post
          target="_blank">
        <div id="body1" class="show" name="divcontent">
            <dl class="content">
                <dd>充值订单号 ：</dd>
                <dd>
                    <input id="WIDout_trade_no" name="WIDout_trade_no"  readonly="readonly"/>
                </dd>
                <dd>订单名称 ：</dd>
                <dd>
                    <input id="WIDsubject" name="WIDsubject" readonly="readonly" />
                </dd>
                <dd>充值金额 ：</dd>
                <dd>
                    <input id="WIDtotal_amount" name="WIDtotal_amount" />
                </dd>
                <dd style="display: none">
                    <input id="WIDbody" name="WIDbody" />
                </dd>
                <dd></dd>
                <dd id="btn-dd">
						<span class="new-btn-login-sp">
							<button id="closs-btn" class="new-btn-login" type="submit"
                                    style="text-align: center;">付 款</button>
                            <button id="closs-btn1" class="new-btn-login" type="button"
                                    style="text-align: center;">取 消</button>
						</span>
                </dd>
            </dl>
        </div>
    </form>
</div>
<%@include file="../common/footer.jsp"%>

<!-- End of easyui-dialog -->
<script type="text/javascript">

    /**
     * Name 打开添加窗口
     */
    function openAdd(){
        GetDateNow();
        $('#add-dialog').dialog({
            closed: false,
            modal:true,
            title: "充值",
            onBeforeOpen:function(){
                //$("#add-form input").val('');
            }
        });
    }

    //搜索按钮监听
    $("#closs-btn").click(function(){
        $('#add-dialog').dialog('close');
        window.parent.close();
    });
    $("#closs-btn1").click(function(){
        $('#add-dialog').dialog('close');
        window.parent.close();
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
            { field:'balance',title:'余额',width:100,sortable:true},
            { field:'memberid',title:'会员',width:100,sortable:true},
        ]]
    });
    function GetDateNow() {
        var vNow = new Date();
        var sNow = "";
        sNow += String(vNow.getFullYear());
        sNow += String(vNow.getMonth() + 1);
        sNow += String(vNow.getDate());
        sNow += String(vNow.getHours());
        sNow += String(vNow.getMinutes());
        sNow += String(vNow.getSeconds());
        sNow += String(vNow.getMilliseconds());
        document.getElementById("WIDout_trade_no").value =  sNow;
        document.getElementById("WIDsubject").value = "充值";
        document.getElementById("WIDtotal_amount").value = "50";
    }
</script>