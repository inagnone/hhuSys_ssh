//排序
function sorter(a,b){
	return a.localeCompare(b);
}

//执行删除
function deletes(url){
   	var ids = [];
	var rows = $('#dg').datagrid('getSelections');
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].id);
	}
	if (rows){
		$.messager.confirm('Confirm','您确认删除证书吗?',function(r){
			if (r){
				$.post(url,{id:ids.join(',')},function(result){
					if (result.success){
						$('#dg').datagrid('reload');	// reload the user data
					} else {
						$.messager.show({	// show error message
							title: 'Error',
							msg: result.errorMsg
						});
					}
				},'json');
				$('#dg').datagrid('reload');
			}
		});
	}
}
//导出选中的行
function getselections(){
	return  rows = $('#dg').datagrid('getSelections');
}
//发送post请求
function post(URL, PARAMS) {        
    var temp = document.createElement("form");        
    temp.action = URL;        
    temp.method = "post";        
    temp.style.display = "none";        
    for (var x in PARAMS) {        
        var opt = document.createElement("textarea");        
        opt.name = x;        
        opt.value = PARAMS[x];        
        // alert(opt.name)        
        temp.appendChild(opt);        
    }        
    document.body.appendChild(temp);        
    temp.submit();        
    return temp;        
}  

function jsSelectItemByValue(objSelect,objItemText) {  
    for(var i=0;i<objSelect.options.length;i++) {  
        if(objSelect.options[i].value == objItemText) {  
            objSelect.options[i].selected = true;  
            break;  
        }  
    }  
}; 

function clearForm(formid){
	$(formid).form('clear');
};

function myformatter(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}
function myparser(s){
	if (!s) return new Date();
	var ss = (s.split('-'));
	var y = parseInt(ss[0],10);
	var m = parseInt(ss[1],10);
	var d = parseInt(ss[2],10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
		return new Date(y,m-1,d);
	} else {
		return new Date();
	}
}
function changeImg(img){
		img.src = img.src+"?time="+new Date().getTime();
}