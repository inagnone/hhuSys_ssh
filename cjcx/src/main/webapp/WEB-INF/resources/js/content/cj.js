/**
 * Created by Administrator on 2017/1/18.
 */

/**
 * 获取工具栏中考试类型的值，动态改变datagrid组件的样式
 */
function changeExam() {
    $.ajax({
        type:"GET",
        url:"/cjcx/exam/"+$("#examtype").val(),
        success:    getExam
    })
}
/**
 * 实例化datagrid组件
 * @param exam
 */
function getExam(exam){
    var columns = [];
    columns.push({field:'ck',checkbox:'true'},
        {field:'idNumber',title:'学生身份证号',width:200},
        {field:'studentName',title:'学生姓名',width:100},
        {field:'company',title:'工作单位',width:200},
        {field:'batchNumber',title:'考试批次',width:50},
        {field:'examDate',title:'考试时间',width:100}
    );
    //根据exam改变datagrid关于成绩的列
    for(var i=0;i<exam.tests.length;i++){
        columns.push({field:'grade'+i,width:100,title:exam.tests[i].name,formatter:function (value,row,index) {
            var name = this.title;
            for(var index = 0;index< row.grades.length;index++){
                if(row.grades[index].test.name == name){
                    return row.grades[index].socre;
                }
            }
        }})
    }
    $("#exam").datagrid({
        columns:[columns],
        url:""
    });
}
$(document).ready(changeExam());

/**
 * 执行查询
 */
function doSearch(){
    $('#exam').datagrid('options').url = "/cjcx/cj/cjs";
    $('#exam').datagrid('load',{
            stuname:$('#stuname').val(),
            idNumber:$('#idNumber').val(),
            examNumber:$('#examtype').val()
    })
}
/**
 * 修改编辑表单
 * 根据编辑表单中考试类型多选框的选择结果，动态改变表单中测试成绩栏的种类
 */
function getExam2(){
    $.ajax({
        type:"GET",
        url:"/cjcx/exam/"+$("#examType2").val(),
        async:false,
        success:    function(exam){
            $('#testCj').empty();
            var t = exam.tests;
            $('#testCj').append("<tr><td>考试成绩:</td></tr>")
            for(var i=0;i<Number(exam.tests.length);i++){
                $('#testCj').append("<tr>" +
                    "<td>"+t[i].name+":</td>" +
                    "<td><input style='width: 150px' id="+t[i].name+" name="+t[i].name+" type='text' class='easyui-numberbox' data-options='min:0,precision:1'></td>" +
                    "</tr>")
            }
            $.parser.parse($('#testCj').parent());
        }
    })
}

/**
 * 新增成绩
 */
function newCj() {
    $('#p').panel('open');
    document.getElementById('zhezhao').style.display='block';
    document.getElementById('p').style.display='block';
    $('#newExam').form('clear');
    $('#newExam').form({
        url:'/cjcx/cj/new',
        async:false,
        onSubmit:function () {

        },
        success:function(data){
            result=eval("("+data+")");
            if(result.success){
                var array = [];
                array.push(result.rows);
                $('#exam').datagrid('loadData',array);
                $('#p').panel('close');
                document.getElementById('zhezhao').style.display='none';
                document.getElementById('p').style.display='none';
            }else{
                $.messager.alert('错误',result.message,'error');
            }
        }
    })
}

/**
 * 加载Excel文件
 * 上传Excel文件内容，服务器解析后返回文件中所有成绩对象信息，跳转到信息核实页面
 */
function loadExcel() {
    $('#excel').panel('open');
    document.getElementById('zhezhao').style.display='block';
    document.getElementById('excel').style.display='block'
    $('#excelFile').form({
        url:"/cjcx/cj/excel",
        async:false,
        dataType:"json",
        success:function(data){
            result=eval("("+data+")");
            if(result.success){
                window.location.href = "/cjcx/cj/validExcel"
            }else{
                $.messager.alert('错误',result.message,'error');
            }
        }
    })
}

$('#p').panel('onOpen',getExam2());

/**
 * 编辑
 * 获取算中的列，打开编辑表单，修改对应类id的成绩的属性
 */
function edit(){
    var row = $('#exam').datagrid('getSelected');
    var examNmber = $('#examtype').val();
    document.getElementById("examType2").value = examNmber;
    getExam2();
    $('#p').panel('open');
    document.getElementById('zhezhao').style.display='block';
    document.getElementById('p').style.display='block';
    // $('#examType2').value = examNmber;
    $('#newExam').form('load',{
        examNumber:$('#examtype').val(),
        studentName:row.studentName,
        idNumber:row.idNumber,
        examDate:row.examDate,
        batchNumber:row.batchNumber,
        company:row.company
    });
    $('#newExam').form({
        url:'/cjcx/cj/edit',
        queryParams:{id:row.id},
        success:function (data) {
            result=eval("("+data+")");
            if(result.success){
                var cj = result.rows;
                var array = [];
                array.push(cj)
                $('#exam').datagrid('loadData',array);
                $('#p').panel('close');
                document.getElementById('zhezhao').style.display='none';
                document.getElementById('p').style.display='none'
            }else{
                $.messager.alert('错误',result.message,'error');
            }
        }
    })
}

/**
 * 删除
 * 获取选中行，将id属性作为参数发送post请求删除对应成绩
 */
function deletes(){
    var ids = [];
    var rows = $('#exam').datagrid('getSelections');
    var mess = "";
    for(var i=0; i<rows.length; i++){
        ids.push(rows[i].id);
        mess = mess+rows[i].studentName+",";
    }
    if (rows){
        $.messager.confirm('Confirm','您确认删除('+mess+')的证书吗?',function(r){
            if (r){
                $.post('/cjcx/cj/delete',{id:ids.join(',')},function(result){
                    if (result.success){
                        $('#exam').datagrid('reload');	// reload the user data
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

function allToExcel() {
    var stuname = $('#stuname').val();
    var idNumber = $('#idNumber').val();
    var examNumber = $('#examtype').val();
    var addr = '/cjcx/cj/allCjToExcel?stuname='+stuname+'&idNumber='+idNumber+'&examNumber='+examNumber;
    $('#allToExcel').attr('href',addr);
}