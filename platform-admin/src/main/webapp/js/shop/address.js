$(function () {
    let userId = getQueryString("userId");
    let url = '../address/list';
    if (userId) {
        url += '?userId=' + userId;
    }
    $("#jqGrid").Grid({
        url: url,
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '会员', name: 'shopUserName', index: 'user_id', width: 90},
            {label: '收货人姓名', name: 'contactName', index: 'contact_name', width: 80},
            {label: '手机', name: 'contactMobile', index: 'contact_mobile', width: 80},
            {label: '详细收货地址信息', name: 'address', index: 'address', width: 150},
            {label: '性别', name: 'sex', index: 'sex', width: 80,
                formatter: function (value) {
                    if (value == 1) {
                        return '<span class="label label-success">男</span>';
                    }
                    return '<span class="label label-danger">女</span>';
                }
            },
            {label: '标签', name: 'addressIndex', index: 'address_index', width: 80,
                formatter: function (value) {
                    if (value == 1) {
                        return '<span class="label label-success">家</span>';
                    }
                    if (value == 2) {
                        return '<span class="label label-success">公司</span>';
                    }
                    return '<span class="label label-success">学校</span>';
                }
            }
            ]
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        q: {
            userName: '',
            telNumber: ''
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
                    'contactName': vm.q.contactName,
                    'contactMobile': vm.q.contactMobile
                },
                page: page
            }).trigger("reloadGrid");
        },
        del: function (event) {
            var ids = getSelectedRows("#jqGrid");
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                Ajax.request({
                    type: "POST",
                    url: "../address/delete",
                    contentType: "application/json",
                    params: JSON.stringify(ids),
                    successCallback: function () {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    }
                });
            });
        }
    }
});