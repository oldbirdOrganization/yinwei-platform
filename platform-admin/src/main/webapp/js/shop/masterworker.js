$(function () {
    // let orderId = getQueryString("orderId");
    let url = '../sys/user/queryMasterWorkerList';

    $("#jqGrid").Grid({
        url: url,
        colModel: [
            // {label: 'orderId', name: 'orderId', index: 'orderId', key: true,hidden:true},
            {label: '师傅编号', name: 'userId', index: 'userId', key: true, width: 60},
            {label: '姓名', name: 'username', index: 'username', width: 90},
            {label: '手机号码', name: 'mobile', index: 'mobile', width: 80},
            {label: '邮箱地址', name: 'email', index: 'email', width: 90},
            {label: '状态', name: 'status', index: 'status', width: 80,
                formatter: function (value) {
                    if (value == 1) {
                        return '<span class="label label-success">正常</span>';
                    }
                    return '<span class="label label-danger">禁用</span>';
                }
            },
            {label: '所属门店', name: 'deptName', index: 'deptName', width: 90}
            ]
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        q: {
            username: '',
            mobile: '',
            status: ''
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'username': vm.q.username,
                    'mobile': vm.q.mobile,
                    'status': vm.q.status
                },
                page: page
            }).trigger("reloadGrid");
        },
        dispatchMaster: function (event) {
            var masterWorkerId = getSelectedRow("#jqGrid");
            if (masterWorkerId == null) {
                return;
            }
            let orderId = getQueryString("orderId");
            Ajax.request({
                url: "../order/info/" + orderId,
                async: true,
                successCallback: function (r) {
                    vm.order = r.order;
                    vm.order.masterWorkerId=masterWorkerId;
                }
            });

            confirm('确定指派给此工人师傅吗？', function () {
                var url = "../order/update";
                Ajax.request({
                    url: url,
                    params: JSON.stringify(vm.order),
                    contentType: "application/json",
                    type: 'POST',
                    successCallback: function () {
                        alert('操作成功', function (index) {
                            window.close();
                            parent.location.reload();
                        });
                    }
                });
            });
        }
    }
});