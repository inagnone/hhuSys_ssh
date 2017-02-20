/**
 * Created by Administrator on 2017/2/17.
 */
$('#roleTable').datagrid({
    columns:[[
        {field:'ck',checkbox:true},
        {field:'name',title:'名称',width:100},
    ]],
    url:'/cjcx/role/list',
    method:'GET'
});
var tree = [
    {
        id:'user',
        text:'用户管理',
        children:[
            {
                id:'newUser',
                text:'新建用户',
                attributes:'newUser',
                checked:false
            },{
                id:'updateUser',
                text:'编辑用户',
                attributes:'updateUser',
                checked:false,
            },{
                id:'deleteUser',
                text:'删除用户',
                attributes:'deleteUser',
                checked:false
            }
        ]
    },{
        id:'cj',
        text:'成绩管理',
        children:[
            {
                id:'newCj',
                text:'添加成绩',
                attributes:'newCj',
                checked:false
            },{
                id:'updateCj',
                text:'编辑成绩',
                attributes:'updateCj',
                checked:false
            },{
                id:'deleteCj',
                text:'删除成绩',
                attributes:'deleteCj',
                checked:false
            }
        ]
    },{
        id:'exam',
        text:'考试管理',
        children:[
            {
                id:'newExam',
                text:'添加考试',
                attributes:'newExam',
                checked:false
            },{
                id:'updateExam',
                text:'编辑考试',
                attributes:'updateExam',
                checked:false
            },{
                id:'deleteExam',
                text:'删除考试',
                attributes:'deleteExam',
                checked:false
            }
        ]
    },{
        id:'role',
        text:'权限管理',
        children:[
            {
                id:'newRole',
                text:'添加权限',
                attributes:'newRole',
                checked:false
            },{
                id:'updateRole',
                text:'编辑权限',
                attributes:'updateRole',
                checked:false
            },{
                id:'deleteRole',
                text:'删除权限',
                attributes:'deleteRole',
                checked:false
            }
        ]
    }
]
$('#tt').tree({
    data: tree
});

function doSearch() {
    $('#roleTable').datagrid('load',{
        name:$('#name').val(),
    })
}

function addRole(){
    $('#roleForm').panel('open');
    document.getElementById('zhezhao').style.display='block';
    document.getElementById('roleForm').style.display='block';
    $('#roleForm').form({
        url:'/cjcx/role/new'
    })
}
function formSubmit() {
    var nodes = $('#tt').tree('getChecked');
    var role = {}
    for(var i=0; i<nodes.length; i++){
        if(nodes[i].attributes == 'newUser'){
            role.newUser = 1;
        }else if(nodes[i].attributes == 'updateUser'){
            role.updateUser = 1;
        }else if(nodes[i].attributes == 'deleteUser'){
            role.deleteUser = 1;
        }else if(nodes[i].attributes == 'newCj'){
            role.newCj = 1;
        }else if(nodes[i].attributes == 'updateCj'){
            role.updateCj = 1;
        }else if(nodes[i].attributes == 'deleteCj'){
            role.deleteCj = 1;
        }else if(nodes[i].attributes == 'newExam'){
            role.newExam = 1;
        }else if(nodes[i].attributes == 'updateExam'){
            role.updateExam = 1;
        }else if(nodes[i].attributes == 'deleteExam'){
            role.deleteExam = 1;
        }else if(nodes[i].attributes == 'newRole'){
            role.newRole = 1;
        }else if(nodes[i].attributes == 'updateRole'){
            role.updateRole = 1;
        }else if(nodes[i].attributes == 'deleteRole'){
            role.deleteRole = 1
        }
    }
    $('#role').form({
        queryParams:role,
        success:function (data) {
            result=eval("("+data+")");
            if(result.success){
                $('#roleForm').panel('close');
                document.getElementById('zhezhao').style.display='none';
                document.getElementById('roleForm').style.display='none'
                $('#roleTable').datagrid('reload');
            }else{
                $.messager.show({	// show error message
                    title: '错误',
                    msg: result.message
                });
                $('#roleForm').panel('close');
                document.getElementById('zhezhao').style.display='none';
                document.getElementById('roleForm').style.display='none'
                $('#roleTable').datagrid('reload');
            }
        }
    })
    $('#role').submit();
};

function edit(){
    var row = $('#roleTable').datagrid('getSelected');
    $('#roleForm').panel('open');
    document.getElementById('zhezhao').style.display='block';
    document.getElementById('roleForm').style.display='block';
    $('#role').form({
        url:'/cjcx/role/edit'
    })
    $('#role').form('load',{
        name:row.name
    })
    for(var p in row) {
        if(p == "id")continue;
        if (row[p] == 1) {
            var node = $('#tt').tree('find',p);
            $('#tt').tree('check',node.target);
        }else if(row[p] == 0){
            var node = $('#tt').tree('find',p);
            $('#tt').tree('uncheck',node.target);
        }
    }
}
function deletes(){
    var ids = [];
    var rows = $('#roleTable').datagrid('getSelections');
    var mess = "";
    for(var i=0; i<rows.length; i++){
        ids.push(rows[i].id);
        mess = mess+rows[i].name+",";
    }
    if (rows){
        $.messager.confirm('Confirm','您确认删除('+mess+')的证书吗?',function(r){
            if (r){
                $.post('/cjcx/role/delete',{ids:ids.join(',')},function(result){
                    if (result.success){
                        $('#roleTable').datagrid('reload');	// reload the user data
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