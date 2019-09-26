$(function () {
    initialPage();
    getGrid();
});

function initialPage() {
    $(window).resize(function () {
        TreeGrid.table.resetHeight({height: $(window).height() - 100});
    });
}

function getGrid() {
    var colunms = TreeGrid.initColumn();
    var table = new TreeTable(TreeGrid.id, '../sys/store/list', colunms);
    table.setExpandColumn(2);
    table.setIdField("storeId");
    table.setCodeField("storeId");
    table.setExpandAll(true);
    table.setHeight($(window).height() - 100);
    table.init();
    TreeGrid.table = table;
}

var TreeGrid = {
    id: "storeTable",
    table: null,
    layerIndex: -1
};


/**
 * 初始化表格的列
 */
TreeGrid.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: '门店ID', field: 'storeId', visible: false, align: 'center', valign: 'middle', width: '80px'},
        {title: '门店名称', field: 'name', align: 'center', valign: 'middle', sortable: true, width: '180px'},
        {title: '门店地址', field: 'address', align: 'center', valign: 'middle', sortable: true, width: '100px'},
        {title: '联系方式', field: 'contact', align: 'center', valign: 'middle', sortable: true, width: '100px'},
        {title: '服务距离', field: 'distance', align: 'center', valign: 'middle', sortable: true, width: '100px'},
        {title: '店长', field: 'manager', align: 'center', valign: 'middle', sortable: true, width: '100px'},
        {title: '信息员', field: 'messenger', align: 'center', valign: 'middle', sortable: true, width: '100px'},
        {title: '排序号', field: 'orderNum', align: 'center', valign: 'middle', sortable: true, width: '100px'}]
    return columns;
};

var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "storeId",
            //pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url: "nourl"
        }
    }
};
var ztree;

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        store: {
            parentName: null,
            //parentId: 0,
            orderNum: 0
        },
        ruleValidate: {
            name: [
                {required: true, message: '门店名称不能为空', trigger: 'blur'}
            ]
        }
    },
    methods: {
        getstore: function () {
            //加载门店树
            Ajax.request({
                url: "../sys/store/select",
                async: true,
                successCallback: function (r) {
                    ztree = $.fn.zTree.init($("#storeTree"), setting, r.storeList);
                    // var node = ztree.getNodeByParam("storeId", vm.store.parentId);
                    // if (node) {
                    //     ztree.selectNode(node);
                    //     vm.store.parentName = node.name;
                    // } else {
                    //     node = ztree.getNodeByParam("storeId", 0);
                    //     ztree.selectNode(node);
                    //     vm.store.parentName = node.name;
                    // }
                }
            });
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            var storeId = TreeGrid.table.getSelectedRow();
            var parentId = 0;
            if (storeId.length != 0) {
                parentId = storeId[0].id;
            }
            vm.store = {parentName: null, orderNum: 0};
            vm.getstore();
        },
        update: function () {
            var storeId = getstoreId();
            if (!storeId) {
                return;
            }
            Ajax.request({
                url: "../sys/store/info/" + storeId,
                async: true,
                successCallback: function (r) {
                    vm.showList = false;
                    vm.title = "修改";
                    vm.store = r.store;

                    vm.getstore();
                }
            });
        },
        del: function () {
            var storeId = getstoreId();
            if (!storeId) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                Ajax.request({
                    type: "POST",
                    url: "../sys/store/delete",
                    params: {"storeId": storeId},
                    successCallback: function () {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    }
                });
            });
        },
        saveOrUpdate: function (event) {
            var url = vm.store.storeId == null ? "../sys/store/save" : "../sys/store/update";
            Ajax.request({
                url: url,
                contentType: "application/json",
                params: JSON.stringify(vm.store),
                type: 'POST',
                successCallback: function () {
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
                }
            });
        },
        storeTree: function () {
            openWindow({
                title: "选择门店",
                area: ['300px', '450px'],
                content: jQuery("#storeLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    //选择上级门店
                    // vm.store.parentId = node[0].storeId;
                    // vm.store.parentName = node[0].name;

                    layer.close(index);
                }
            });
        },
        reload: function () {
            vm.showList = true;
            TreeGrid.table.refresh();
        },
        handleSubmit: function (name) {
            handleSubmitValidate(this, name, function () {
                vm.saveOrUpdate()
            });
        },
        handleReset: function (name) {
            handleResetForm(this, name);
        }
    }
});

function getstoreId() {
    var selected = $('#storeTable').bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        alert("请选择一条记录");
        return false;
    } else {
        return selected[0].id;
    }
}