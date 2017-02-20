/**
 * Created by Administrator on 2017/2/19.
 */
$('#diplomaTable').datagrid({
    url:'/cjcx/diploma/list',
    columns:[[
        {field:'ck',checkbox:'true'},
        {field:'name',title:'名称',width:100},
        {field:'idNumber',title:'身份证',width:100},
        {field:'company',title:'公司',width:100,align:'right'},
        {field:'job',title:'职位',width:100,align:'right'},
        {field:'diplomaNumber',title:'证书编号',width:100,align:'right'},
        {field:'issueTime',title:'发证日期',width:100,align:'right'},
        {field:'endTime',title:'有效期至',width:100,align:'right'},
        {field:'commissionUnit',title:'发证单位',width:100,align:'right'},
        {field:'state',title:'状态',width:100,align:'right'},
        {field:'detail',title:'备注',width:100,align:'right'}
    ]]
});
$('#diplomaType1').combobox({
    url:'/cjcx/diplomaType/list',
    method:'get',
    valueField:'number',
    textField:'name',
    onLoadSuccess:function(){
        $('#diplomaJob').combobox({
            url:'/cjcx/diploma/job',
            method:'get',
            queryParams:{
                number:$('#diplomaType1').combobox('getValue')
            },
            valueField:'name',
            textField:'job',
        })
    }
});
$('#diplomaType2').combobox({
    url:'/cjcx/diplomaType/list',
    method:'get',
    valueField:'number',
    textField:'name'
});
function newDiploma(){
    $('#diplomaPanel').panel('open');
    document.getElementById('zhezhao').style.display='block';
    document.getElementById('diplomaPanel').style.display='block';
    $('#newDiploma').form('clear');
    $('#newDiploma').form({
        url:'/cjcx/diploma/new',
        async:false,
        success:function(data){
            var result=eval("("+data+")");
            if(result.success){
                var array = [];
                array.push(result.rows);
                $('#diplomaTable').datagrid('loadData',array);
                $('#diplomaPanel').panel('close');
                document.getElementById('zhezhao').style.display='none';
                document.getElementById('diplomaPanel').style.display='none';
            }else{
                $.messager.alert('错误',result.message,'error');
            }
        }
    })
}
function loadExcel() {
    $('#excel').panel('open');
    document.getElementById('zhezhao').style.display='block';
    document.getElementById('excel').style.display='block'
    $('#excelFile').form({
        url:"/cjcx/diploma/excel",
        async:false,
        dataType:"json",
        beforeSend:function () {
            $('#dg').datagrid('loading');
        },
        success:function(data){
            $('#dg').datagrid('loaded');
            var result=eval("("+data+")");
            if(result.success){
                window.location.href = "/cjcx/diploma/validExcel"
            }else{
                $.messager.alert('错误',result.message,'error');
            }
        }
    })
}