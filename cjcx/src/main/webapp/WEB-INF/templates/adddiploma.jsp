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
    
    <title>证书录入</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/app.min.css">
	<link rel="stylesheet" type="text/css" href="easyui/css/demo.css">
	<link rel="stylesheet" type="text/css" href="easyui/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/css/icon.css">
	
  	<script type="text/javascript" src="easyui/js/jquery.min.js"></script>
  	<script type="text/javascript" src="easyui/js/jquery.easyui.min.js"></script>

  </head>
  
 <body >
		
	<div class="content" style="background-color: #F0F0F0;" >
 		<div class="content-color-top">
		    <div class="brand"><img src="img/add.png" width="135" height="50">
		    		<c:if test="${sessionScope.user != null }">
						<h2 class="time" id="jnkc" style="position: absolute;top: 15px;margin-left: 300px;color: white;"></h2>
				 	</c:if>
		    </div>
		</div>
 			
	 	<div class="content-body" style="height: 600px">
		     <div class="block" style="width: 1162px">
	            <div class="form" style="margin-left: 100px">	
		    		<form id="ff"  method="post" enctype="multipart/form-data"  action="new/AddDiplomaServlet" onsubmit="return checkData()" >
				    	<table cellpadding="5" align="center">
				    		<tr>
				    			<td>培训证书类别编号:</td>
				    			<td><input class="easyui-textbox" type="text" name="typeId" data-options="required:true"></input></td>
				    			<td><font color="red">*</font>证书编号:</td>
				    			<td><input class="easyui-textbox" type="text" name="diplomaId" data-options="required:true,validType:'diplomaid'"></input></td>
				    		</tr>
				    		<tr>
				    			<td>审批单位区域</td>
				    			<td>
					    			<select id="area" name="area"   panelHeight="auto" style="width:200px" data-options="required:true">
					    			<option value=""></option>
									<option value="01">水利部长江水利委员会</option>
									<option value="02">水利部黄河水利委员会</option>	
									<option value="03">水利部淮河水利委员会</option>
									<option value="04">水利部海河水利委员会</option>
									<option value="05">水利部珠江水利委员会</option>
									<option value="06">水利部松辽水利委员会</option>
									<option value="07">水利部太湖流域管理局</option>
									<option value="08">其他部直属及非水利系统单位</option>
									<option value="11">北京市</option>
									<option value="12">天津市</option>
									<option value="13">河北省</option>
									<option value="14">山西省</option>
									<option value="15">内蒙省</option>
									<option value="16">辽宁省</option>
									<option value="22">吉林省</option>
									<option value="23">黑龙江省</option>
									<option value="31">上海市</option>
									<option value="32">江苏省</option>
									<option value="33">浙江省</option>
									<option value="34">安徽省</option>
									<option value="35">福建省</option>
									<option value="36">江西省</option>
									<option value="37">山东省</option>
									<option value="41">河南省</option>
									<option value="42">湖北省</option>
									<option value="43">湖南省</option>
									<option value="44">广东省</option>
									<option value="45">广西壮族自治区</option>
									<option value="46">海南省</option>
									<option value="50">重庆市</option>
									<option value="51">四川省</option>
									<option value="52">贵州省</option>
									<option value="53">云南省</option>
									<option value="54">西藏自治区</option>
									<option value="61">陕西省</option>
									<option value="62">甘肃省</option>
									<option value="63">青海省</option>
									<option value="64">宁夏回族自治区</option>
									<option value="65">新疆维吾尔自治区</option>
									<option value="09">新疆兵团</option>
								</select>
								</td>
				    		</tr>
				    		<tr>
				    			<td><font color="red">*</font>姓名:</td>
				    			<td><input class="easyui-textbox" type="text" name="name" data-options="required:true"></input></td>
				    			<td><font color="red">*</font>身份证号码:</td>
				    			<td><input class="easyui-validatebox" type="text" name="personId" data-options="validType:'personId'"></input></td>
				    		</tr>
				    		<tr>
				    			<td><font color="red">*</font>工作单位:</td>
				    			<td><input class="easyui-textbox" type="text" name="company" data-options="required:true"></input></td>
				    			<td><font color="red">*</font>岗位名称:</td>
				    			<td><input class="easyui-textbox" type="text" name="job" data-options="required:true"></input></td>
				    		</tr>
				    		<tr>
				    			<td>学时数:</td>
				    			<td><input class="easyui-textbox" type="text" name="studyHour"></input></td>
				    		</tr>
				    		<tr>
				    			<td>发证单位:</td>
				    			<td><input class="easyui-textbox" type="text" name="issueCompany"></input></td>
				    		</tr>
				    		<tr>
				    			<td>委托单位:</td>
				    			<td><input class="easyui-textbox" type="text" name="commissionUnit"></input></td>
				    			<td>承办单位:</td>
				    			<td><input class="easyui-textbox" type="text" name="underTaker"></input></td>
				    		</tr>
				    		<tr>
				    			<td>培训方式:</td>
				    			<td><input class="easyui-textbox" type="text" name="trainWay"></input></td>
				    			<td>培训机构:</td>
				    			<td><input class="easyui-textbox" type="text" name="trainer"></input></td>
				    		</tr>
				    		
				    		<tr>	
				    			<td>培训开始日期:</td>
				    			<td><input class="easyui-datebox" type="text" name="startTime"></input></td>
				    			<td>培训结束日期:</td>
				    			<td><input class="easyui-datebox" type="text" name="finishTime"></input></td>
				    		</tr>
				    		<tr>
				    			<td><font color="red">*</font>批准日期:</td>
				    			<td><input class="easyui-datebox" type="text" name="approveTime" data-options="required:true"></input></td>
				    		</tr>	    		
				    		<tr>
				    			<td><font color="red">*</font>发证日期:</td>
				    			<td><input class="easyui-datebox" type="text" name="issueTime" data-options="required:true"></input></td>
				    			<td><font color="red">*</font>有效期至:</td>
				    			<td><input class="easyui-datebox" type="text" name="endTime" data-options="required:true"></input></td>
				    		</tr>
				    		
				    		<tr>
				    			<td>第一次延期:</td>
				    			<td><input class="easyui-datebox" type="text" name="firDelay" ></input></td>
				    			<td>第二次延期:</td>
				    			<td><input class="easyui-datebox" type="text" name="secDelay" ></input></td>
				    		</tr>
				    		<tr>
								<td>证书图片:</td>
								<td><input class="easyui-filebox" name="imgPath" data-options="prompt:'Choose a file...'" style="width:100%"></td>
							</tr>
				    	
				    		<tr>
				    			<td>备注:</td>
				    			<td colspan="3"><input class="easyui-textbox" name="desc" data-options="multiline:true" style="height:60px;width: 500px"></input></td>
				    		</tr>
				    		<tr>
				    			<td align="center" colspan="8">
					    			<input type="submit" value="提交" class="easyui-linkbutton">
					    			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
					    			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#dlg').dialog('open')">批量导入</a>
				    			</td>
				    		</tr>
				    	</table>
		    		</form>
			</div> 
			<div class="news-slider">
						  <p>(1)批量导入功能请先使用提供的模板填充数据后再上传excel文件，并确保文件类型维持.xls不变</p>
			
						  <p>(2)批量上传图片功能是对已经存在于数据库的证书添加图片资料，所以请确保添加的图片对应的证书已经正确存进了数据库</p>
						
						  <p>(3)批量图片上传的压缩包请确保是.zip格式,压缩包中图片文件命名方式为身份证号-证书编号</p>
						
						  <p>(4)单条证书录入表单中，带*号输入框为必填输入框</p>
						
						  <p>(5)当批量导入数据的数据有误时，会返回一个result.txt文件,里面记录了错误数据的位置,请根据文件描述修改错误数据后重新导入数据</p>
			</div> 
		</div>
	</div>
</div>  
	<div id="dlg" class="easyui-dialog" closed="true" title="Basic Dialog" data-options="iconCls:'icon-save'" style="width:400px;height:200px;padding:10px;z-index:10">
		模板选择：
		<form action="DownServlet">
			<select id="cc" class="easyui-combobox" name="templetfile" style="width:200px;">
				<option value="WEB-INF/templet/templet1.xls">模板1</option>
			</select>
			<input type="submit" value="下载">
		</form>
		<form action="new/LoadExcelServlet" enctype="multipart/form-data" method="post">
			请上传excel文件<input class="easyui-filebox" name="excel" data-options="prompt:'选择excel文件'" style="width:100%">
			请上传证书图片压缩文件<input class="easyui-filebox" name="zipfile" data-options="prompt:'选择压缩好的图片文件'" style="width:100%">
			<input type="submit" value="提交" onClick="wait()">
		</form>
	</div>
</div>

<script language=javascript>
		function wait(){
			$.messager.alert('数据提交','数据正在提交中，请稍等','info');
		}
</script>
<script>

		//欢迎词
	if('${sessionScope.user.role }' == "admin"){
		jnkc.innerHTML='${sessionScope.user.name }'+',欢迎您!'+' '+new Date().toLocaleDateString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());
	}else{
		jnkc.innerHTML='${sessionScope.user.areaname }'+',欢迎您!'+' '+new Date().toLocaleDateString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());
	}
	
	$("div.slider").slider({
		picture:".slider-img",
		thumbPicture:".slider-nav",
		title:".slider-title",
		//link:".art-img a",
		current:0,
		effect:{
			type:"fade",
			speed:300
		},
		thumbEffect:{
			style:"icon",
			type:null,
			selectedClass:"active"
		},
		delay:3000
	});

	$(document).ready(function(e) {
		time = window.setInterval(function(){
			$('.og_next').click();	
		},5000);
		var linum = $('.mainlist li').length,
			licount = 4,
			total_width = linum * 220,
			width = 880;
		$('.piclist').css('width', total_width + 'px');
		$('.og_next').click(function(){
			if($('.mainlist').is(':animated')){
				$('.mainlist').stop(true,true);
			}
			if(linum > 4){
				ml = parseInt($('.mainlist').css('left'));
				if(licount < linum) {
					licount += 4;
					$('.mainlist').animate({left: ml - width + 'px'},'slow');
				}else{
					$('.mainlist').animate({left: 0 + 'px'});
					licount = 4;
				}
			}
		});
		$('.og_prev').click(function(){
			
			if($('.mainlist').is(':animated')){
				$('.mainlist').stop(true,true);
			}
			if(linum > 4){
				ml = parseInt($('.mainlist').css('left'));
				console.log(licount);
				if(licount > 4) {
					licount -= 4;
					$('.mainlist').animate({left: ml + width + 'px'},'slow');
				}
			}
		});    
	});

$(document).ready(function(){
	$('.og_prev,.og_next').hover(function(){
			$(this).fadeTo('fast',1);
		},function(){
			$(this).fadeTo('fast',0.7);
	});
});
</script>
	
</body>
<c:if test="${requestScope.msg != null }">
	<script>	
		$.messager.alert('My Title','${requestScope.msg}','warning');
	</script>
</c:if>
<script>

		function checkData(){
			return $('#ff').form('enableValidation').form('validate');
  		}
  		
		function clearForm(){
			$('#ff').form('clear');
		}
		
		//格式化日期
		$.fn.datebox.defaults.formatter = function(date){
	        var y = date.getFullYear();
	        var m = date.getMonth()+1;
	        var d = date.getDate();
	        return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
   	 	};
    	$.fn.datebox.defaults.parser = function(s){
	        if (!s) return new Date();
	        var ss = s.split('-');
	        var y = parseInt(ss[0],10);
	        var m = parseInt(ss[1],10);
	        var d = parseInt(ss[2],10);
	        if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
	            return new Date(y,m-1,d);
	        } else {
	            return new Date();
	        }
    	};
    	
    	$.extend($.fn.validatebox.defaults.rules, {
	        personId: {
	    		validator: function(value, param){
	    			var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
	    			return (reg.test(value) === true);
	    			//return value.length >= param[0];
	    		},
	    		message: '请输入正确的身份证号！'
	        },
	        diplomaid:{
	        	validator: function(value, param){
	    			var reg = /(SGL|SQX)\d{11}/;
	    			return (reg.test(value) === true);
	    			//return value.length >= param[0];
	    		},
	    		message: '请输入正确的证书编号！'
	        }
    	});
    
	</script>
</html>
