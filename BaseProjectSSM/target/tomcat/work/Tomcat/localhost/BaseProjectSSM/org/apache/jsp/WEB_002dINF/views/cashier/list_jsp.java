/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2022-08-24 10:32:26 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.cashier;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class list_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(3);
    _jspx_dependants.put("/WEB-INF/views/cashier/../common/footer.jsp", Long.valueOf(1657114502000L));
    _jspx_dependants.put("/WEB-INF/views/cashier/../common/header.jsp", Long.valueOf(1657115156000L));
    _jspx_dependants.put("/WEB-INF/views/cashier/../common/menus.jsp", Long.valueOf(1657114502000L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<meta name=\"copyright\" content=\"All Rights Reserved, Copyright (C) 2013, 包头师范学院, Ltd.\" />\r\n");
      out.write("<title></title>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"../../resources/admin/easyui/easyui/1.3.4/themes/default/easyui.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"../../resources/admin/easyui/css/wu.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"../../resources/admin/easyui/css/icon.css\" />\r\n");
      out.write("<script type=\"text/javascript\" src=\"../../resources/admin/easyui/js/jquery-1.8.0.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"../../resources/admin/easyui/easyui/1.3.4/jquery.easyui.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"../../resources/admin/easyui/easyui/1.3.4/locale/easyui-lang-zh_CN.js\"></script>\r\n");
      out.write("<body class=\"easyui-layout\">");
      out.write("\r\n");
      out.write("<div class=\"easyui-layout\" data-options=\"fit:true\">\r\n");
      out.write("    <!-- Begin of toolbar -->\r\n");
      out.write("    <div id=\"wu-toolbar\">\r\n");
      out.write("        <div class=\"wu-toolbar-button\">\r\n");
      out.write("            ");
      out.write("\r\n");
      out.write("\r\n");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"wu-toolbar-search\">\r\n");
      out.write("            <label>用户名:</label><input id=\"search-name\" class=\"wu-text\" style=\"width:100px\">\r\n");
      out.write("            <label>性别:</label>\r\n");
      out.write("            <select id=\"search-sex\" class=\"easyui-combobox\" panelHeight=\"auto\" style=\"width:120px\">\r\n");
      out.write("            \t<option value=\"-1\">全部</option>\r\n");
      out.write("            \t<option value=\"0\">未知</option>\r\n");
      out.write("            \t<option value=\"1\">男</option>\r\n");
      out.write("            \t<option value=\"2\">女</option>\r\n");
      out.write("            </select>\r\n");
      out.write("            <a href=\"#\" id=\"search-btn\" class=\"easyui-linkbutton\" iconCls=\"icon-search\">搜索</a>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <!-- End of toolbar -->\r\n");
      out.write("    <table id=\"data-datagrid\" class=\"easyui-datagrid\" toolbar=\"#wu-toolbar\"></table>\r\n");
      out.write("</div>\r\n");
      out.write("<!-- Begin of easyui-dialog -->\r\n");
      out.write("<div id=\"add-dialog\" class=\"easyui-dialog\" data-options=\"closed:true,iconCls:'icon-save'\" style=\"width:420px; padding:10px;\">\r\n");
      out.write("    <form id=\"add-form\" method=\"post\">\r\n");
      out.write("        <table>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td width=\"60\" align=\"right\">头像预览:</td>\r\n");
      out.write("                <td valign=\"middle\">\r\n");
      out.write("                    <img id=\"preview-photo\" style=\"float:left;\" src=\"/BaseProjectSSM/resources/admin/easyui/images/user_photo.jpg\" width=\"100px\">\r\n");
      out.write("                    <a style=\"float:left;margin-top:40px;\" href=\"javascript:void(0)\" class=\"easyui-linkbutton\" iconCls=\"icon-upload\" onclick=\"uploadPhoto()\" plain=\"true\">上传图片</a>\r\n");
      out.write("                </td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td width=\"60\" align=\"right\">头像:</td>\r\n");
      out.write("                <td><input type=\"text\" id=\"add-photo\" name=\"photo\" value=\"/BaseProjectSSM/resources/admin/easyui/images/user_photo.jpg\" readonly=\"readonly\" class=\"wu-text \" /></td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td width=\"60\" align=\"right\">用户名:</td>\r\n");
      out.write("                <td><input type=\"text\" id=\"add-username\" name=\"username\" class=\"wu-text easyui-validatebox\" data-options=\"required:true, missingMessage:'请填写用户名'\" /></td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td width=\"60\" align=\"right\">密码:</td>\r\n");
      out.write("                <td><input type=\"password\" id=\"add-password\" name=\"password\" class=\"wu-text easyui-validatebox\" data-options=\"required:true, missingMessage:'请填写密码'\" /></td>\r\n");
      out.write("            </tr>\r\n");
      out.write("\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td width=\"60\" align=\"right\">性别:</td>\r\n");
      out.write("                <td>\r\n");
      out.write("                    <select name=\"sex\" id=\"add-sex\" class=\"easyui-combobox\" panelHeight=\"auto\" style=\"width:268px\">\r\n");
      out.write("                        <option value=\"0\">未知</option>\r\n");
      out.write("                        <option value=\"1\">男</option>\r\n");
      out.write("                        <option value=\"2\">女</option>\r\n");
      out.write("                    </select>\r\n");
      out.write("                </td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td width=\"60\" align=\"right\">年龄:</td>\r\n");
      out.write("                <td><input type=\"text\" id=\"add-age\" name=\"age\" class=\"wu-text easyui-numberbox easyui-validatebox\" data-options=\"required:true,min:1,precision:0, missingMessage:'请填写年龄'\" /></td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td width=\"60\" align=\"right\">地址:</td>\r\n");
      out.write("                <td><input type=\"text\" id=\"add-address\" name=\"address\" class=\"wu-text easyui-validatebox\" /></td>\r\n");
      out.write("            </tr>\r\n");
      out.write("        </table>\r\n");
      out.write("    </form>\r\n");
      out.write("</div>\r\n");
      out.write("<!-- 修改窗口 -->\r\n");
      out.write("<div id=\"edit-dialog\" class=\"easyui-dialog\" data-options=\"closed:true,iconCls:'icon-save'\" style=\"width:450px; padding:10px;\">\r\n");
      out.write("\t<form id=\"edit-form\" method=\"post\">\r\n");
      out.write("        <input type=\"hidden\" name=\"id\" id=\"edit-id\">\r\n");
      out.write("        <table>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td width=\"60\" align=\"right\">头像预览:</td>\r\n");
      out.write("                <td valign=\"middle\">\r\n");
      out.write("                \t<img id=\"edit-preview-photo\" style=\"float:left;\" src=\"/BaseProjectSSM/resources/admin/easyui/images/user_photo.jpg\" width=\"100px\">\r\n");
      out.write("                \t<a style=\"float:left;margin-top:40px;\" href=\"javascript:void(0)\" class=\"easyui-linkbutton\" iconCls=\"icon-upload\" onclick=\"uploadPhoto()\" plain=\"true\">上传图片</a>\r\n");
      out.write("                </td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td width=\"60\" align=\"right\">头像:</td>\r\n");
      out.write("                <td><input type=\"text\" id=\"edit-photo\" name=\"photo\" value=\"/BaseProjectSSM/resources/admin/easyui/images/user_photo.jpg\" readonly=\"readonly\" class=\"wu-text \" /></td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td width=\"60\" align=\"right\">用户名:</td>\r\n");
      out.write("                <td><input type=\"text\" id=\"edit-username\" name=\"username\" class=\"wu-text easyui-validatebox\" data-options=\"required:true, missingMessage:'请填写用户名'\" /></td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td width=\"60\" align=\"right\">性别:</td>\r\n");
      out.write("                <td>\r\n");
      out.write("                \t<select id=\"edit-sex\" name=\"sex\" class=\"easyui-combobox\" panelHeight=\"auto\" style=\"width:268px\">\r\n");
      out.write("                        <option value=\"-1\">全部</option>\r\n");
      out.write("\t\t                <option value=\"0\">未知</option>\r\n");
      out.write("            \t\t\t<option value=\"1\">男</option>\r\n");
      out.write("            \t\t\t<option value=\"2\">女</option>\r\n");
      out.write("\t\t            </select>\r\n");
      out.write("                </td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td width=\"60\" align=\"right\">年龄:</td>\r\n");
      out.write("                <td><input type=\"text\" id=\"edit-age\" name=\"age\" value=\"1\" class=\"wu-text easyui-numberbox easyui-validatebox\" data-options=\"required:true,min:1,precision:0, missingMessage:'请填写年龄'\" /></td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td width=\"60\" align=\"right\">地址:</td>\r\n");
      out.write("                <td><input type=\"text\" id=\"edit-address\" name=\"address\" class=\"wu-text easyui-validatebox\" /></td>\r\n");
      out.write("            </tr>\r\n");
      out.write("        </table>\r\n");
      out.write("    </form>\r\n");
      out.write("</div>\r\n");
      out.write("<div id=\"process-dialog\" class=\"easyui-dialog\" data-options=\"closed:true,iconCls:'icon-upload',title:'正在上传图片'\" style=\"width:450px; padding:10px;\">\r\n");
      out.write("<div id=\"p\" class=\"easyui-progressbar\" style=\"width:400px;\" data-options=\"text:'正在上传中...'\"></div>\r\n");
      out.write("</div>\r\n");
      out.write("<input type=\"file\" id=\"photo-file\" style=\"display:none;\" onchange=\"upload()\">\r\n");
      out.write("\r\n");
      out.write("<div id=\"loading\" style=\"position:absolute;z-index:1000;top:0px;left:0px;width:100%;height:100%;background:#F9F9F9;text-align :center;padding-top:20%;\">\r\n");
      out.write("     <img src=\"../../resources/admin/easyui/images/load-page.gif\" width=\"50%\">\r\n");
      out.write("     <h1><font color=\"#15428B\">加载中....</font></h1>\r\n");
      out.write("</div>\r\n");
      out.write("</body>\r\n");
      out.write("<script>\r\n");
      out.write("    var pc; \r\n");
      out.write("    //不要放在$(function(){});中\r\n");
      out.write("    $.parser.onComplete = function () {\r\n");
      out.write("        if (pc) clearTimeout(pc);\r\n");
      out.write("        pc = setTimeout(closes, 1000);\r\n");
      out.write("    } \r\n");
      out.write("\r\n");
      out.write("    function closes() {\r\n");
      out.write("        $('#loading').fadeOut('normal', function () {\r\n");
      out.write("            $(this).remove();\r\n");
      out.write("        });\r\n");
      out.write("    }\r\n");
      out.write("</script>");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- End of easyui-dialog -->\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t//上传图片\r\n");
      out.write("\tfunction start(){\r\n");
      out.write("\t\t\tvar value = $('#p').progressbar('getValue');\r\n");
      out.write("\t\t\tif (value < 100){\r\n");
      out.write("\t\t\t\tvalue += Math.floor(Math.random() * 10);\r\n");
      out.write("\t\t\t\t$('#p').progressbar('setValue', value);\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\t$('#p').progressbar('setValue',0)\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t};\r\n");
      out.write("\tfunction upload(){\r\n");
      out.write("\t\tif($(\"#photo-file\").val() == '')return;\r\n");
      out.write("\t\tvar formData = new FormData();\r\n");
      out.write("\t\tformData.append('photo',document.getElementById('photo-file').files[0]);\r\n");
      out.write("\t\t$(\"#process-dialog\").dialog('open');\r\n");
      out.write("\t\tvar interval = setInterval(start,200);\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\turl:'upload_photo',\r\n");
      out.write("\t\t\ttype:'post',\r\n");
      out.write("\t\t\tdata:formData,\r\n");
      out.write("\t\t\tcontentType:false,\r\n");
      out.write("\t\t\tprocessData:false,\r\n");
      out.write("\t\t\tsuccess:function(data){\r\n");
      out.write("\t\t\t\tclearInterval(interval);\r\n");
      out.write("\t\t\t\t$(\"#process-dialog\").dialog('close');\r\n");
      out.write("\t\t\t\tif(data.type == 'success'){\r\n");
      out.write("\t\t\t\t\t$(\"#preview-photo\").attr('src',data.filepath);\r\n");
      out.write("\t\t\t\t\t$(\"#add-photo\").val(data.filepath);\r\n");
      out.write("\t\t\t\t\t$(\"#edit-preview-photo\").attr('src',data.filepath);\r\n");
      out.write("\t\t\t\t\t$(\"#edit-photo\").val(data.filepath);\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t$.messager.alert(\"消息提醒\",data.msg,\"warning\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\terror:function(data){\r\n");
      out.write("\t\t\t\tclearInterval(interval);\r\n");
      out.write("\t\t\t\t$(\"#process-dialog\").dialog('close');\r\n");
      out.write("\t\t\t\t$.messager.alert(\"消息提醒\",\"上传失败!\",\"warning\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction uploadPhoto(){\r\n");
      out.write("\t\t$(\"#photo-file\").click();\r\n");
      out.write("\t\t\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t/**\r\n");
      out.write("\t*  添加记录\r\n");
      out.write("\t*/\r\n");
      out.write("\tfunction add(){\r\n");
      out.write("\t\tvar validate = $(\"#add-form\").form(\"validate\");\r\n");
      out.write("\t\tif(!validate){\r\n");
      out.write("\t\t\t$.messager.alert(\"消息提醒\",\"请检查你输入的数据!\",\"warning\");\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tvar data = $(\"#add-form\").serialize();\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\turl:'add',\r\n");
      out.write("\t\t\tdataType:'json',\r\n");
      out.write("\t\t\ttype:'post',\r\n");
      out.write("\t\t\tdata:data,\r\n");
      out.write("\t\t\tsuccess:function(data){\r\n");
      out.write("\t\t\t\tif(data.type == 'success'){\r\n");
      out.write("\t\t\t\t\t$.messager.alert('信息提示','添加成功！','info');\r\n");
      out.write("                    $(\"#add-username\").val('');\r\n");
      out.write("                    $(\"#add-age\").val('');\r\n");
      out.write("                    $(\"#add-password\").val('');\r\n");
      out.write("                    $(\"#add-address\").val('');\r\n");
      out.write("                    $(\"#add-sex\").val('');\r\n");
      out.write("\t\t\t\t\t$('#add-dialog').dialog('close');\r\n");
      out.write("\t\t\t\t\t$('#data-datagrid').datagrid('reload');\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t$.messager.alert('信息提示',data.msg,'warning');\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t/**\r\n");
      out.write("\t* Name 修改记录\r\n");
      out.write("\t*/\r\n");
      out.write("\tfunction edit(){\r\n");
      out.write("\t\tvar validate = $(\"#edit-form\").form(\"validate\");\r\n");
      out.write("\t\tif(!validate){\r\n");
      out.write("\t\t\t$.messager.alert(\"消息提醒\",\"请检查你输入的数据!\",\"warning\");\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tvar data = $(\"#edit-form\").serialize();\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\turl:'edit',\r\n");
      out.write("\t\t\tdataType:'json',\r\n");
      out.write("\t\t\ttype:'post',\r\n");
      out.write("\t\t\tdata:data,\r\n");
      out.write("\t\t\tsuccess:function(data){\r\n");
      out.write("\t\t\t\tif(data.type == 'success'){\r\n");
      out.write("\t\t\t\t\t$.messager.alert('信息提示','修改成功！','info');\r\n");
      out.write("                    $\r\n");
      out.write("\t\t\t\t\t$('#edit-dialog').dialog('close');\r\n");
      out.write("\t\t\t\t\t$('#data-datagrid').datagrid('reload');\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t$.messager.alert('信息提示',data.msg,'warning');\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t/**\r\n");
      out.write("\t* 删除记录\r\n");
      out.write("\t*/\r\n");
      out.write("\tfunction remove(){\r\n");
      out.write("\t\t$.messager.confirm('信息提示','确定要删除该记录？', function(result){\r\n");
      out.write("\t\t\tif(result){\r\n");
      out.write("\t\t\t\tvar item = $('#data-datagrid').datagrid('getSelections');\r\n");
      out.write("\t\t\t\tif(item == null || item.length == 0){\r\n");
      out.write("\t\t\t\t\t$.messager.alert('信息提示','请选择要删除的数据！','info');\r\n");
      out.write("\t\t\t\t\treturn;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tvar ids = '';\r\n");
      out.write("\t\t\t\tfor(var i=0;i<item.length;i++){\r\n");
      out.write("\t\t\t\t\tids += item[i].id + ',';\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\t\turl:'delete',\r\n");
      out.write("\t\t\t\t\tdataType:'json',\r\n");
      out.write("\t\t\t\t\ttype:'post',\r\n");
      out.write("\t\t\t\t\tdata:{ids:ids},\r\n");
      out.write("\t\t\t\t\tsuccess:function(data){\r\n");
      out.write("\t\t\t\t\t\tif(data.type == 'success'){\r\n");
      out.write("\t\t\t\t\t\t\t$.messager.alert('信息提示','删除成功！','info');\r\n");
      out.write("\t\t\t\t\t\t\t$('#data-datagrid').datagrid('reload');\r\n");
      out.write("\t\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\t\t$.messager.alert('信息提示',data.msg,'warning');\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\t\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t/**\r\n");
      out.write("\t* Name 打开添加窗口\r\n");
      out.write("\t*/\r\n");
      out.write("\tfunction openAdd(){\r\n");
      out.write("\t\t//$('#add-form').form('clear');\r\n");
      out.write("        $(\"#add-sex\").combobox('setValue',-1);\r\n");
      out.write("\t\t$('#add-dialog').dialog({\r\n");
      out.write("\t\t\tclosed: false,\r\n");
      out.write("\t\t\tmodal:true,\r\n");
      out.write("            title: \"添加用户信息\",\r\n");
      out.write("            buttons: [{\r\n");
      out.write("                text: '确定',\r\n");
      out.write("                iconCls: 'icon-ok',\r\n");
      out.write("                handler: add\r\n");
      out.write("            }, {\r\n");
      out.write("                text: '取消',\r\n");
      out.write("                iconCls: 'icon-cancel',\r\n");
      out.write("                handler: function () {\r\n");
      out.write("                    $('#add-dialog').dialog('close');                    \r\n");
      out.write("                }\r\n");
      out.write("            }],\r\n");
      out.write("            onBeforeOpen:function(){\r\n");
      out.write("            \t//$(\"#add-form input\").val('');\r\n");
      out.write("            }\r\n");
      out.write("        });\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t/**\r\n");
      out.write("\t* 打开修改窗口\r\n");
      out.write("\t*/\r\n");
      out.write("\tfunction openEdit(){\r\n");
      out.write("\t\t//$('#edit-form').form('clear');\r\n");
      out.write("\t\tvar item = $('#data-datagrid').datagrid('getSelections');\r\n");
      out.write("\t\tif(item == null || item.length == 0){\r\n");
      out.write("\t\t\t$.messager.alert('信息提示','请选择要修改的数据！','info');\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(item.length > 1){\r\n");
      out.write("\t\t\t$.messager.alert('信息提示','请选择一条数据进行修改！','info');\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\titem = item[0];\r\n");
      out.write("\t\t$('#edit-dialog').dialog({\r\n");
      out.write("\t\t\tclosed: false,\r\n");
      out.write("\t\t\tmodal:true,\r\n");
      out.write("            title: \"修改用户信息\",\r\n");
      out.write("            buttons: [{\r\n");
      out.write("                text: '确定',\r\n");
      out.write("                iconCls: 'icon-ok',\r\n");
      out.write("                handler: edit\r\n");
      out.write("            }, {\r\n");
      out.write("                text: '取消',\r\n");
      out.write("                iconCls: 'icon-cancel',\r\n");
      out.write("                handler: function () {\r\n");
      out.write("                    $('#edit-dialog').dialog('close');                    \r\n");
      out.write("                }\r\n");
      out.write("            }],\r\n");
      out.write("            onBeforeOpen:function(){\r\n");
      out.write("            \t$(\"#edit-id\").val(item.id);\r\n");
      out.write("            \t$(\"#edit-preview-photo\").attr('src',item.photo);\r\n");
      out.write("\t\t\t\t$(\"#edit-photo\").val(item.photo);\r\n");
      out.write("            \t$(\"#edit-username\").val(item.username);\r\n");
      out.write("            \t$(\"#edit-roleId\").combobox('setValue',item.roleId);\r\n");
      out.write("            \t$(\"#edit-sex\").combobox('setValue',item.sex);\r\n");
      out.write("            \t$(\"#edit-age\").val(item.age);\r\n");
      out.write("            \t$(\"#edit-address\").val(item.address);\r\n");
      out.write("            }\r\n");
      out.write("        });\r\n");
      out.write("\t}\t\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t//搜索按钮监听\r\n");
      out.write("\t$(\"#search-btn\").click(function(){\r\n");
      out.write("\t\tvar roleId = $(\"#search-role\").combobox('getValue');\r\n");
      out.write("\t\tvar sex = $(\"#search-sex\").combobox('getValue')\r\n");
      out.write("\t\tvar option = {username:$(\"#search-name\").val()};\r\n");
      out.write("\t\tif(roleId != -1){\r\n");
      out.write("\t\t\toption.roleId = roleId;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(sex != -1){\r\n");
      out.write("\t\t\toption.sex = sex;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t$('#data-datagrid').datagrid('reload',option);\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\t/** \r\n");
      out.write("\t* 载入数据\r\n");
      out.write("\t*/\r\n");
      out.write("\t$('#data-datagrid').datagrid({\r\n");
      out.write("\t\turl:'list',\r\n");
      out.write("\t\trownumbers:true,\r\n");
      out.write("\t\tsingleSelect:false,\r\n");
      out.write("\t\tpageSize:20,           \r\n");
      out.write("\t\tpagination:true,\r\n");
      out.write("\t\tmultiSort:true,\r\n");
      out.write("\t\tfitColumns:true,\r\n");
      out.write("\t\tidField:'id',\r\n");
      out.write("\t    treeField:'name',\r\n");
      out.write("\t\tfit:true,\r\n");
      out.write("\t\tcolumns:[[\r\n");
      out.write("\t\t\t{ field:'chk',checkbox:true},\r\n");
      out.write("\t\t\t{ field:'photo',title:'用户头像',width:100,align:'center',formatter:function(value,row,index){\r\n");
      out.write("\t\t\t\tvar img = '<img src=\"'+value+'\" width=\"50px\" />';\r\n");
      out.write("\t\t\t\treturn img;\r\n");
      out.write("\t\t\t}},\r\n");
      out.write("\t\t\t{ field:'username',title:'用户名',width:100,sortable:true},\r\n");
      out.write("\t\t\t{ field:'sex',title:'性别',width:100,formatter:function(value,row,index){\r\n");
      out.write("\t\t\t\tswitch(value){\r\n");
      out.write("\t\t\t\t\tcase 0:{\r\n");
      out.write("\t\t\t\t\t\treturn '未知';\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\tcase 1:{\r\n");
      out.write("\t\t\t\t\t\treturn '男';\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\tcase 2:{\r\n");
      out.write("\t\t\t\t\t\treturn '女';\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\treturn value;\r\n");
      out.write("\t\t\t}},\r\n");
      out.write("\t\t\t{ field:'age',title:'年龄',width:100},\r\n");
      out.write("\t\t\t{ field:'address',title:'地址',width:200}\r\n");
      out.write("\t\t]],\r\n");
      out.write("\t\tonLoadSuccess:function(data){  \r\n");
      out.write("\t\t\t$('.authority-edit').linkbutton({text:'编辑权限',plain:true,iconCls:'icon-edit'});  \r\n");
      out.write("\t\t }  \r\n");
      out.write("\t});\r\n");
      out.write("</script>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fforEach_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent(null);
    // /WEB-INF/views/cashier/../common/menus.jsp(3,0) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/views/cashier/../common/menus.jsp(3,0) '${thirdMenuList }'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${thirdMenuList }",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    // /WEB-INF/views/cashier/../common/menus.jsp(3,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("thirdMenu");
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("   <a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${thirdMenu.icon }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("\" onclick=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${thirdMenu.url}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("\" plain=\"true\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${thirdMenu.name }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</a>\r\n");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f0.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }
}
