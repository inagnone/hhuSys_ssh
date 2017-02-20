/**
 * Created by Administrator on 2017/2/2.
 */

$(document).ready(function () {
        $('#dg').datagrid({
            iconCls:'icon-edit',
            singleSelect:true,
            method:'get',
            url:'/cjcx/diploma/ajaxExcelContent',
            rownumbers:true,
            fit:true,
            toolbar:'#tb',
            onClickRow: onClickRow,
            columns:[[
                {field:'ck',checkbox:'true'},
                {field:'name',title:'名称',width:100,editor:'text'},
                {field:'idNumber',title:'身份证',width:100,editor:'text'},
                {field:'company',title:'公司',width:100,align:'right',editor:'text'},
                {field:'job',title:'职位',width:100,align:'right',editor:'text'},
                {field:'diplomaNumber',title:'证书编号',width:100,align:'right',editor:'text'},
                {field:'issuetime',title:'发证日期',width:100,align:'right',editor:{
                    type:'datebox',
                    options:{formatter:dateformatter,parser:dateparser}}
                },
                {field:'endtime',title:'有效期至',width:100,align:'right',editor:{
                    type:'datebox',
                    options:{formatter:dateformatter,parser:dateparser}}
                },
                {field:'issuecompany',title:'发证单位',width:100,align:'right',editor:'text'},
                {field:'state',title:'状态',width:100,align:'right'},
                {field:'detail',title:'备注',width:100,align:'right',editor:'text'}
            ]]
        });
})
(function($){
    function getCacheContainer(t){
        var view = $(t).closest('div.datagrid-view');
        var c = view.children('div.datagrid-editor-cache');
        if (!c.length){
            c = $('<div class="datagrid-editor-cache" style="position:absolute;display:none"></div>').appendTo(view);
        }
        return c;
    }
    function getCacheEditor(t, field){
        var c = getCacheContainer(t);
        return c.children('div.datagrid-editor-cache-' + field);
    }
    function setCacheEditor(t, field, editor){
        var c = getCacheContainer(t);
        c.children('div.datagrid-editor-cache-' + field).remove();
        var e = $('<div class="datagrid-editor-cache-' + field + '"></div>').appendTo(c);
        e.append(editor);
    }

    var editors = $.fn.datagrid.defaults.editors;
    for(var editor in editors){
        var opts = editors[editor];
        (function(){
            var init = opts.init;
            opts.init = function(container, options){
                var field = $(container).closest('td[field]').attr('field');
                var ed = getCacheEditor(container, field);
                if (ed.length){
                    ed.appendTo(container);
                    return ed.find('.datagrid-editable-input');
                } else {
                    return init(container, options);
                }
            }
        })();
        (function(){
            var destroy = opts.destroy;
            opts.destroy = function(target){
                if ($(target).hasClass('datagrid-editable-input')){
                    var field = $(target).closest('td[field]').attr('field');
                    setCacheEditor(target, field, $(target).parent().children());
                } else if (destroy){
                    destroy(target);
                }
            }
        })();
    }
})(jQuery);
var editIndex = undefined;
function endEditing(){
    if (editIndex == undefined){return true}
    if ($('#dg').datagrid('validateRow', editIndex)){
        var ed = $('#dg').datagrid('getEditor', {index:editIndex,field:'idNumber'});
        // var productname = $(ed.target).combobox('getText');
        // $('#dg').datagrid('getRows')[editIndex]['productname'] = productname;
        $('#dg').datagrid('endEdit', editIndex);
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
}
function onClickRow(index){
    if (editIndex != index){
        if (endEditing()){
            $('#dg').datagrid('selectRow', index)
                .datagrid('beginEdit', index);
            editIndex = index;
        } else {
            $('#dg').datagrid('selectRow', editIndex);
        }
    }
}
function accept(){
    if (endEditing()){
        var rows = $('#dg').datagrid('getChanges');
        $('#dg').datagrid('acceptChanges');
        var diplomas = $('#dg').datagrid('getData');
        $.ajax({
            type:"POST",
            url:"/cjcx/diploma/newFromExcel",
            async:false,
            data:{diplomas:JSON.stringify(diplomas.rows)},
            beforeSend:function () {
                $('#dg').datagrid('loading');
            },
            success:    function(result){
                $('#dg').datagrid('loaded')
                if(result.success){
                    window.location.href = "/cjcx/diploma"
                }else{
                    $.messager.alert('错误',result.message,'error');
                }
            },
        })
    }
}
function reject(){
    $('#dg').datagrid('rejectChanges');
    editIndex = undefined;
}
function getChanges(){
    var rows = $('#dg').datagrid('getChanges');
    alert(rows.length+' rows are changed!');
}
function dateformatter(date) {
    if(date == null) return null;
    var y = date.getFullYear();
    var m = date.getMonth()+1;
    var d = date.getDate();
    return y+'-'+m+'-'+d;
}
function dateparser(s){
    if (!s) return null;
    var ss = (s.split('-'));
    var y = parseInt(ss[0],10);
    var m = parseInt(ss[1],10);
    var d = parseInt(ss[2],10);
    if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
        return new Date(y,m-1,d);
    } else {
        return null;
    }
}