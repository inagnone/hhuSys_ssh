<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>全国水利安全远程教育培训平台证书查询系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="easyui/css/demo.css">
	<link rel="stylesheet" type="text/css" href="easyui/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/css/icon.css">
	<link rel="stylesheet" type="text/css" href="css/app.min.css">
	<link rel="stylesheet" href="css/normalize.css">

    <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />

  	<script type="text/javascript" src="easyui/js/jquery.min.js"></script>
  	<script type="text/javascript" src="easyui/js/jquery.easyui.min.js"></script>
  	<script type="text/javascript" src="easyui/js/datagrid-scrollview.js"></script>
  </head>
  
  <body>
	  <div class="header">
		<div class="header-body">
		    <div class="header-top">
		       <a href="http://slaqpx.hhu.edu.cn" target="_self"><img src="img/return.png"></img></a>&nbsp;&nbsp;
			   <a href="${basepath }/Certificate/login.jsp"><img src="img/login1.png"></a>	    
			 </div>
			<div class="brand"><img src="img/logo.png" width="700" height="65">
`		  </div>
		</div>
		<div class="header-color-bottom"></div>
	</div>
  <div class="content" style="height: 72.5%;">
	    
	    <div class="content-body " oncontextmenu="return false" ondragstart="return false" onselectstart ="return false" onselect="document.selection.empty()"
 oncopy="document.selection.empty()" onbeforecopy="return false" onmouseup="document.selection.empty()" style="height: 100%">
	
      		<div class="search-block" style="width: 100%;margin-left: 0px;height: 100%">
        		<iframe src="SearchStu.jsp" style="width: 100%;height: 100%" name="date" ></iframe>
			</div>
		</div>
		
  	<div id="win" class="easyui-window" title="Modal Window" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:500px;height:200px;padding:10px;">
  		<img id="showImg" src="" height="100%" >
  	</div>  
</div>
<div class="footer mt-10">
	<div class="footer-top"></div>
	<div class="footer-body pt-10">
		<div class="p-10">
			<p>
				<span class="ml-30">主管单位：<a href=" http://aqjd.mwr.gov.cn/" target="_blank">水利部安全监督司</a></span>
				<span class="ml-30">主办单位：<a href="http://www.cwec.org.cn/" target="_blank">中国水利企业协会</a></span>
				<span class="ml-30">承办单位：<a href="http://www.hhu.edu.cn" target="_blank">河海大学</a></span>
				<span class="ml-30">友情链接：
					<span class="footer-select">
					  <select id="FriendLink" class="footer_sel" onchange="window.location=this.value;">
					    <option selected="" value="">友情链接</option>
					    <option value="http://www.mwr.gov.cn/">中华人民共和国水利部</option>
					    <option value="http://aqjd.mwr.gov.cn/">水利部安全监督司</option>
					    <option value="http://www.cwec.org.cn/">中国水利企业协会</option>
					    <option value="http://www.cahee.org.cn/">中国水利教育协会</option>
					    <option value="http://slpx.hhu.edu.cn">基层水利队伍培训工程</option>
					    <option value="http://www.hhu.edu.cn">河海大学</option>
					    <option value="http://jjy.hhu.edu.cn">河海大学继续教育学院</option>
					  </select>
					</span>
				</span>
			</p>

		</div>		
		<div class="pb-10" style="height: 5%">
			<p class="p-5">地址：江苏省南京市西康路1号 邮编：210098 办公室电话：025-83786362 技术咨询电话：025-83787318</p>
			<p class="p-5">版权： copyright 2014 河海大学远程与继续教育学院 All rights reserved</p>
		</div>	
	</div>
</div>


<c:if test="${requestScope.msg != null }">
	<script>	
		$.messager.alert('My Title','${requestScope.msg}','info');
	</script>
</c:if>
<c:if test="${param.msg != null }">
	<script>	
		$.messager.alert('My Title','${param.msg}','info');
	</script>
</c:if>
  </body>

</html>
