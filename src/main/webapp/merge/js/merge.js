$(function() {
            ajaxTable("surviveTable", "#surviveTable_tb", {
                        "isSurvive" : true
                    });
            ajaxTable("retiredTable", "#retiredTable_tb", {
                        "isSurvive" : false
                    });
            $("#showSummary").hide();
        });
/**
 * 加载表格数据
 */
function ajaxTable(tableId, toobar, params) {
    var args = {};
    if (params != undefined && $.isPlainObject(params))
        args = params;

    // 加载表格
    $('#' + tableId).datagrid({
                toolbar : toobar,
                singleSelect : true,// 单选
                pagination : true,// 分页
                loadMsg : '数据加载中,请稍后...',
                onLoadError : function() {
                    alert('数据加载失败!');
                },
                queryParams : args,
                onClickRow : function(rowIndex, rowData) {
                    // 取消选择某行后高亮
                    $('#' + tableId).datagrid('unselectRow', rowIndex);
                },
                onLoadSuccess : function(data) {
                    var value = $('#' + tableId).datagrid('getData')['errorMsg'];
                    if (value != null) {
                        alert("错误消息:" + value);
                    }
                }
            }).datagrid('acceptChanges');
    var p = $('#' + tableId).datagrid('getPager');
    $(p).pagination({
                beforePageText : '第',
                afterPageText : '页    共 {pages} 页',
                displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
                pageSize : 10,
                pageList : [10, 20, 50, 100]
            });
}

/**
 * 选中 目标居民信息方法
 */
function selectSurvivePerson() {
    var row = $('#surviveTable').datagrid('getSelected');
    if (row == undefined || row == null) {
        alert("请选择居民信息!");
        return;
    }
    reSelectSurvivePerson();
    $("#survivePersonId").val(row.FIELD_PK);
    $("#surviveDomainId").val(row.DOMAIN_ID);
    addCellData("surviveSummary", 1, [row.NAME_CN, row.ID_NO,
                    row.DOMAIN_DESC]);
    $("#showSummary").show();
    searchRetiredTable();
    // 展开 被合并居民列表
    $('#table_accordion').accordion("select", "选择被合并居民");
}

/**
 * 选中 被合并居民信息
 */
function selectRetiredPerson() {
    var row = $('#retiredTable').datagrid('getSelected');
    if (row == undefined || row == null) {
        alert("请选择居民信息!");
        return;
    }

    if (row.PERSON_ID == $("#survivePersonId").val()) {
        alert("被合并居民不能与目标居民相同");
        return;
    }

    $("#retiredPersonId").val(row.FIELD_PK);
    addCellData("retiredSummary", 1, [row.NAME_CN, row.ID_NO,
                    row.DOMAIN_DESC]);
}

/**
 * 重选选定目标居民
 */
function reSelectSurvivePerson() {
    // 清除被合并居民信息
    reSelectRetiredPerson();
    // 清除目标居民信息
    $("#survivePersonId").val();
    $("#surviveDomainId").val();
    addCellData("surviveSummary", 1, ["", "", ""]);
    // 隐藏 顶部
    $("#showSummary").hide();
    // 展开 选择目标居民列表
    $('#table_accordion').accordion("select", "选择目标居民");
}

/**
 * 重选选定合并居民
 */
function reSelectRetiredPerson() {
    // 展开 被合并居民列表
    $('#table_accordion').accordion("select", "选择被合并居民");
    // 清除被合并居民信息
    $("#retiredPersonId").val();
    addCellData("retiredSummary", 1, ["", "", ""]);
}

/**
 * 添加表格数据
 * @param rowid
 *        tr的id
 * @param startCell
 *        其实列
 * @param datas
 *        数据
 */
function addCellData(rowid, startCell, datas) {
    var tr = $("#" + rowid);
    $.each(datas, function(index, val) {
                tr.children().get(startCell + index).innerHTML = val;
            });
}
// 查询匹配详情
function searchSurviveTable() {
    // 查询的时候重置回第一页
    $("#surviveTable").datagrid("options").pageNumber = 1;
    $("#surviveTable").datagrid({
                queryParams : {
                    "DOMAIN_ID" : $("#search_domain")
                            .combobox('getValue'),
                    "NAME_CN" : $("#search_personName").val(),
                    "ID_NO" : $("#search_personIdcard").val(),
                    "isSurvive" : true
                }
            });
}

// 查询匹配详情
function searchRetiredTable() {
    // 查询的时候重置回第一页
    $("#retiredTable").datagrid("options").pageNumber = 1;
    $("#retiredTable").datagrid({
                queryParams : {
                    "DOMAIN_ID" : $("#surviveDomainId").val(),
                    "NAME_CD" : $("#search_retired_personName").val(),
                    "ID_NO" : $("#search_retired_personIdcard").val(),
                    "isSurvive" : false
                }
            });
}

// 搜索条件重置
function resetSurviveTable() {
    $("#search_domain").combobox('setValue', '');
    $("#search_personName").val("");
    $("#search_personName").val("");
}

// 搜索条件重置
function resetRetiredTable() {
    $("#search_retired_personName").val("");
    $("#search_retired_personIdcard").val("");
}

// 刷新表格
function reloadTable(tableId) {
    $('#' + tableId).datagrid('reload');
}
/**
 * 前往 合并比较页面
 */
function toMergePage() {
    
    var survivePersonId = $("#survivePersonId").val();
    var retiredPersonId = $("#retiredPersonId").val();
    if(survivePersonId==undefined ||survivePersonId==null||survivePersonId==""||
        retiredPersonId==undefined ||retiredPersonId==null ||retiredPersonId==""){
        alert("请先选择居民信息");
        return;
    }
    
    var tabId = 'tabId_merge_compare_tab';
    var title = '合并居民详情比较';
    var url = root+'/merge/merge.ac?method=comp&survivePersonId='+survivePersonId+'&retiredPersonId='+retiredPersonId;
    var name = 'iframe_'+tabId; 
    
    //如果当前id的tab不存在则创建一个tab
    if(parent.$("#"+tabId).html()==null){       
        parent.$('#centerTab').tabs('add',{
            title: title,         
            closable:true,
            cache : false,
            //注：使用iframe即可防止同一个页面出现js和css冲突的问题
            content : '<iframe name="'+name+'" id="'+tabId+'" src="'+url+'" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>'
        });
    }else{
        var tabs = parent.$('#centerTab').tabs('tabs');
        for(var i = 0 ; i < tabs.length ; i++){
            var tab = tabs[i];
            if(tab.panel("options").title==title){
                parent.$('#centerTab').tabs('update',{
                    tab: tab,
                    options:{
                        content : '<iframe name="'+name+'" id="'+tabId+'" src="'+url+'" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>'
                    }
                });
                parent.$('#centerTab').tabs('select',title);
                break;
            }
        }   
    }
}

function buildPersonView(val , row){
    return '<a href="#" onclick="unify_viewPerson(\''+row.FIELD_PK+'\',\''+val+'\')">'+val+'</a>';
}
