$(function () {
    let paymentStatus = getQueryString("paymentStatus");
    let orderStatus = getQueryString("orderStatus");
    let orderType = getQueryString("orderType");
    let url = '../order/list';
    if (paymentStatus) {
        url += '?paymentStatus=' + paymentStatus;
    }
    if (orderStatus) {
        url += '?orderStatus=' + orderStatus;
    }
    if (orderType) {
        url += '?orderType=' + orderType;
    }
    $("#jqGrid").Grid({
        url: url,
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '订单号', name: 'orderNo', index: 'order_no', width: 100},
            {label: '会员', name: 'userName', index: 'user_name', width: 80},
            {label: '渠道名称',name: 'channelName',index: 'channel_name',width: 60},
            {
                label: '订单类型', name: 'orderType', index: 'order_type', width: 80,
                formatter: function (value) {
                    if (value == '1') {
                        return '预约订单';
                    } else if (value == '2') {
                        return '定金订单';
                    }
                    return '-';
                }
            },
            {
                label: '订单状态', name: 'orderStatus', index: 'order_status', width: 80,
                formatter: function (value) {
                    if (value == '1') {
                        return '待指派';
                    } else if (value == '2') {
                        return '待服务';
                    } else if (value == '3') {
                        return '服务中';
                    } else if (value == '4') {
                        return '订单完成';
                    } else if (value == '5') {
                        return '已作废';
                    } else if (value == '6') {
                        return '待评价';
                    }
                    return value;
                }
            },
            {
                label: '付款状态', name: 'paymentStatus', index: 'payment_status', width: 80,
                formatter: function (value) {
                    if (value == '1') {
                        return '未付款';
                    } else if (value == '2') {
                        return '已付定金';
                    } 
                    return value;
                }
            },
            {label: '支付单号', name: 'paymentNo', index: 'payment_no', width: 80},

            {label: '实际支付金额', name: 'orderPrice', index: 'order_price', width: 80},
            {
                label: '支付时间', name: 'paymentTime', index: 'payment_time', width: 80,
                formatter: function (value) {
                    return transDate(value);
                }
            },
            {label: '优惠金额', name: 'couponPrice', index: 'coupon_price', width: 60},
            {
                label: '下单时间', name: 'createTime', index: 'create_time', width: 80,
                formatter: function (value) {
                    return transDate(value);
                }
            },
            {
                label: '操作', width: 160, align: 'center', sortable: false,
                formatter: function (value, col, row) {
                    return '<button class="btn btn-outline btn-info" onclick="vm.lookDetail(' + row.id + ')">' +
                        '<i class="fa fa-info-circle"></i>&nbsp;详情</button>';
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
        q: {
            orderSn: '',
            orderStatus: '',
            orderType: ''
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        dispatchOrder: function (event) {
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
        saveOrUpdate: function (event) {
            Ajax.request({
                type: "POST",
                url: "../order/dispatchOrder",
                contentType: "application/json",
                params: JSON.stringify(vm.order),
                successCallback: function (r) {
                    vm.reload();
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            vm.detail = false;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'orderSn': vm.q.orderSn,
                    'orderStatus': vm.q.orderStatus,
                    'orderType': vm.q.orderType
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
        printDetail: function (rowId) {
            openWindow({
                type: 2,
                title: '<i class="fa fa-print"></i>打印票据',
                content: '../shop/orderPrint.html?orderId=' + rowId
            })
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