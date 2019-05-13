
$(function () {

    $("#gameList").bootstrapTable({

        url: '/game/findAllGames',
        method:'GET',
        toolbar: '#toolbar',
        striped:'true', //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        uniqueId: 'ID',

        //排序
        sortable: true,
        sortOrder: "asc", //排序方式 asc升序  desc降序

        //开启分页
        pagination: true, //分页
        sidePagination : 'server', //服务器端分页

        pageNumber:1,
        pageSize:5,
        pageList:[5, 10, 20, 25],

        //开启查询
        search: true,     //是否显示表格搜索
        showRefresh: true, //是否显示刷新按钮
        searchAlign:"right", //搜索框靠左对齐
        strictSearch: false, //关闭严格查询
        searchOnEnterKey:false, //回车开始查询

        showColumns: false,                  //是否显示所有的列（选择显示的列）
        minimumCountColumns: 2,             //最少允许的列数

        //关闭单击选中
        singleSelect:false,
        clickToSelect: false,                //是否启用点击选中行
        maintainSelected:false, //分页仍然选中

        showToggle: true,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表

        //得到的参数:
        queryParams:function(params){

            console.log("params = " + JSON.stringify(params));

            //这里的键的名字和控制器的变量名必须一致，这边改动，控制器也需要改成一样的
            var temp = {

                rows:params.limit, //页面大小
                page:(params.offset/params.limit) + 1,//页码
                sort:params.sort, //排序列名
                sortOrder:params.order //排位命令（desc，asc）
            };

            console.log("temp = " + JSON.stringify(temp));

            return temp;
        },
        columns:[
            {
                checkbox:true,
                align:'center'
            },
            {
                field:'id',
                title:'编号',
                sortable:true
            },
            {
                field:'cnName',
                title:'中文名',
                sortable:true
            },
            {
                field:'jpName',
                title:'日文名',
                sortable:true
            },
            {
                field:'enName',
                title:'英文名',
                sortable:true
            },
            {
                field:'nature',
                title:'属性',
                sortable:true
            },
            {
                field:'generation',
                title:'世代',
                sortable:true
            },
            {
                field:'power',
                title:'威力',
                sortable:true
            },
            {
                field:'hirate',
                title:'命中',
                sortable:true
            },
            {
                field:'type',
                title:'攻击类型',
                sortable:true
            },
            {
                field:'pp',
                title:'pp点数',
                sortable:true
            },
            {
                field:'pp',
                title:'操作',
                width:120,
                align:'center',
                valign:'middle',
                formatter:actionFormatter,
            }
        ],
        onLoadSuccess:function(data){

            console.log("success:data = " + JSON.stringify(data));
        },
        onLoadError:function(error){

            console.log("error: error = " + error);
        },
        onDblClickRow:function(row, value, index){

            console.log("onDbClickRow: row =" + JSON.stringify(row) + " value = " + JSON.stringify(value) + " index = " + index);
        }
    });

    //操作栏的格式化
    function actionFormatter(value, row, index) {

        var id = value;
        var result = "";

        result += "<a href='javascript:;' class='btn btn-xs green' onclick='seeDetail()' title='查看'>" +
            " <span class='glyphicon glyphicon-search'></span></a>";
        result += "<a href='javascript:;' class='btn btn-xs blue' onclick='edit()' title='编辑'>" +
            "<span class='glyphicon glyphicon-pencil'></span></a>";
        result += "<a href='javascript:;' class='btn btn-xs red' onclick='deleteAction()' title='删除'>" +
            "<span class='glyphicon glyphicon-remove'></span></a>"

        return result;
    };

    //查看
    function seeDetail() {

        console.log("查看");
    }

    //编辑
    function edit() {

        console.log("编辑");
    }

    //删除
    function deleteAction() {

        console.log("删除");
    }
});

$(document).ready(function () {

    console.log('========');


});
