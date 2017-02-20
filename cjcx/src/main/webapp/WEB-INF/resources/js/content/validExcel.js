/**
 * Created by Administrator on 2017/2/2.
 */

$(document).ready(function () {
    $.ajax({
        type:"GET",
        url:"/cjcx/cj/ajaxExcelExam",
        success:    function (exam) {
            var columns = [];
            columns.push({field:'ck',checkbox:'true'},
                {field:'idNumber',title:'学生身份证号',width:200,editor:'text'},
                {field:'studentName',title:'学生姓名',width:200,editor:'text'},
                {field:'company',title:'工作单位',width:200,editor:'text'},
                {field:'batchNumber',title:'考试批次',width:200,editor:'text'},
                {field:'examDate',title:'考试时间',width:200}
            );
            for(var i=0;i<exam.tests.length;i++){
                columns.push({field:'grade'+i,width:100,editor:'numberbox',title:exam.tests[i].name,formatter:function (value,row,index) {
                    var name = this.title;
                    for(var i = 0;i< row.grades.length;i++){
                        if(row.grades[i].test.name == name){
                            return row.grades[i].socre;
                        }
                    }
                }})
            }

            $('#dg').datagrid({
                iconCls:'icon-edit',
                singleSelect:true,
                method:'get',
                url:'ajaxExcelContent',
                rownumbers:true,
                fit:true,
                toolbar:'#tb',
                onClickRow: onClickRow,
                columns:[columns]
            });
        }
    })
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
        var cjs = $('#dg').datagrid('getData');
        $.ajax({
            type:"POST",
            url:"/cjcx/cj/newFromExcel",
            async:false,
            data:{cjs:JSON.stringify(cjs.rows)},
            success:    function(){},
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
