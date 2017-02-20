/**
 * Created by Administrator on 2017/1/14.
 */

$('#examTable').datagrid({
    columns:[[
        {field:'ck',checkbox:true},
        {field:'number',title:'编号',width:80},
        {field:'name',title:'名称',width:100},
        {field:'listTests',title:'测试',width:200},
    ]]
})

/**
 * exam表单中添加test的输入文本框
 */
function addTest(){
    var trObj = document.createElement("tr");
    trObj.id = new Date().getTime();
    trObj.innerHTML = "" +
        "<td>" +
        "<input name='tests' class='easyui-validatebox' style='line-height:26px;border:1px solid #ccc' data-options='required:true'> " +
        "</td>" +
        "<td>" +
        " <buttton class='easyui-linkbutton'  value='Del' onclick='del(this)'>删除</buttton>" +
        "</td>";
    document.getElementById("tb").appendChild(trObj);
    $("#testAmount").text($("input[name='tests']").length);
    $.parser.parse($('#type').parent());
    $("input[name='tests']").validatebox({
        missingMessage:'测试名称不能为空'
    })
}

/**
 * 删除添加的test输入文本框
 * @param obj   添加的test输入文本框
 */
function del(obj) {
    var trId = obj.parentNode.parentNode.id;
    var trObj = document.getElementById(trId);
    document.getElementById("tb").removeChild(trObj);
    $("#testAmount").text($("input[name='tests']").length);
}

function deletes() {
    var ids = [];
    var rows = $('#examTable').datagrid('getSelections');
    var mess = "";
    for(var i=0; i<rows.length; i++){
        ids.push(rows[i].number);
        mess = mess+rows[i].name+",";
    }
    if (rows){
        $.messager.confirm('Confirm','您确认删除('+mess+')的考试吗?',function(r){
            if (r){
                $.post('/cjcx/exam/delete',{ids:ids.join(',')},function(result){
                    if (result.success){
                        $('#examTable').datagrid('reload');	// reload the user data
                    } else {
                        $.messager.show({	// show error message
                            title: '错误',
                            msg: result.message
                        });
                    }
                },'json');
            }
        });
    }
}

function doSearch(){
    $('#examTable').datagrid('options').url = "/cjcx/exam";
    $('#examTable').datagrid('load',{
        number:$('#typeid').val(),
        name:$('#typename').val(),
    })
}

$('#exam').form({
    success:function () {
        $('#newExam').panel('close');
        document.getElementById('zhezhao').style.display='none';
        document.getElementById('newExam').style.display='none';
        $('#examTable').datagrid('load',{});
    }
})
