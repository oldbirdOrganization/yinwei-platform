$(function () {
    let orderNo = getQueryString("orderNo");
    let channelId = getQueryString("channelId");
    let storeId = getQueryString("storeId");
    let payType = getQueryString("payType");
    let masterWorker = getQueryString("masterWorker");
    let url = '../order/list';
    if (orderNo) {
        url += '?orderNo=' + orderNo;
    }
    if (channelId) {
        url += '?channelId=' + channelId;
    }
    if (storeId) {
        url += '?storeId=' + storeId;
    }
    if (payType) {
        url += '?payType=' + payType;
    }
    if (masterWorker) {
        url += '?masterWorker=' + masterWorker;
    }
    url += '?orderType=' + "1";
    $("#jqGrid").Grid({
        url: url,
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '订单号', name: 'orderNo', index: 'order_no', width: 100},
            {label: '用户姓名', name: 'userName', index: 'user_name', width: 100},
            {label: '联系方式', name: 'userMobile', index: 'user_mobile', width: 100},
            {label: '订单类型',name: 'channelName',index: 'channel_name',width: 100},
            {
                label: '是否第三方', name: 'isOuterOrder', index: 'is_outer_order', width: 100,
                formatter: function (value) {
                    if (value == '0') {
                        return '否';
                    } else if (value == '1') {
                        return '是';
                    }
                    return value;
                }
            },
            {
                label: '下单时间', name: 'createTime', index: 'create_time', width: 100,
                formatter: function (value) {
                    return transDate(value);
                }
            },
            {
                label: '订单状态', name: 'paymentStatus', index: 'payment_status', width: 100,
                formatter: function (value) {
                    if (value == '1') {
                        return '未支付';
                    } else if (value == '2') {
                        return '已支付';
                    }
                    return value;
                }
            },
            {
                label: '门店名称', name: 'storeName', index: 'store_name', width: 100
            },
            {
                label: '门店地址', name: 'storeAddress', index: 'store_address', width: 100
            },
            {
                label: '门店预约电话', name: 'storeContact', index: 'store_contact', width: 100
            },
            {
                label: '师傅名称', name: 'masterWorker', index: 'master_worker', width: 100
            },
            {
                label: '师傅联系方式', name: 'masterContact', index: 'master_contact', width: 100
            },
            {label: '订单金额', name: 'orderPrice', index: 'order_price', width: 100},
            {label: '支付分类', name: 'payType', index: 'pay_type', width: 100},
            {
                label: '操作', width: 90, align: 'center', sortable: false,
                formatter: function (value, col, row) {
                    return '<button class="btn btn-outline btn-info" onclick="vm.update(' + row.id +","+ row.payType+')">' +
                        '<i class="fa fa-info-circle"></i>&nbsp;编辑</button>';
                }
            }
        ]
    });
});

let vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        detail: false,
        title: null,
        order: {},
        shippings: [],
        info: {
            id: '',
            payType: ''
        },
        q: {
            orderNo: '',
            channelId: '',
            storeId: '',
            payType: '',
            masterWorker: ''
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        /*dispatchOrder: function (event) {
            let id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            alert(id);
            vm.showList = false;
            vm.title = "订单指派";
            Ajax.request({
                url: "../sys/user/queryMasterWorkerList",
                async: true,
                successCallback: function (r) {
                    vm.workerList = r.workerList;
                }
            });
        },*/

        dispatchOrder : function() {
            var id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            openWindow({
                title : '工人师傅列表',
                type : 2,
                content : '../shop/masterworker.html?orderId=' + id
            })
        },

        cancelOrder: function (event) {
            let id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            Ajax.request({
                type: "POST",
                url: "../order/cancelOrder",
                contentType: "application/json",
                params: JSON.stringify(id),
                successCallback: function (r) {
                    if (r.code == 0) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        // saveOrUpdate: function (event) {
        //     Ajax.request({
        //         type: "POST",
        //         url: "../order/dispatchOrder",
        //         contentType: "application/json",
        //         params: JSON.stringify(vm.order),
        //         successCallback: function (r) {
        //             vm.reload();
        //         }
        //     });
        // },
        reload: function (event) {
            vm.showList = true;
            vm.detail = false;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'orderNo': vm.q.orderNo,
                    'channelId': vm.q.channelId,
                    'payType': vm.q.payType,
                    'masterWorker': vm.q.masterWorker,
                    'storeId': vm.q.storeId
                },
                page: page
            }).trigger("reloadGrid");
        },
        lookDetail: function (rowId) { //第三步：定义编辑操作
            vm.detail = true;
            vm.title = "详情";
            Ajax.request({
                url: "../order/info/" + rowId,
                async: true,
                successCallback: function (r) {
                    vm.order = r.order;
                }
            });
        },

        update: function (rowId,rowPayType) { //第三步：定义编辑操作
            vm.detail = true;
            vm.title = "编辑";
            debugger;
            vm.info.id = rowId;
            vm.info.payType = rowPayType;
            Ajax.request({
                type: "POST",
                url: "../order/info",
                contentType: "application/json",
                params: JSON.stringify(vm.info),
                successCallback: function (r) {
                    vm.order = r.order;
                }
            });
        },

        saveOrUpdate: function (event) {
            var url = vm.order.id == null ? "../order/save" : "../order/update";
            Ajax.request({
                type: "POST",
                url: url,
                contentType: "application/json",
                params: JSON.stringify(vm.order),
                successCallback: function (r) {
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
                }
            });
        },
        handleSubmit: function (name) {

            handleSubmitValidate(this, name, function () {
                vm.saveOrUpdate()
            });
        },
    },
    created: function () {
        let vue = this;
        Ajax.request({
            url: "../shipping/queryAll",
            async: true,
            successCallback: function (r) {
                vue.shippings = r.list;
            }
        });
    }
});