$(function () {
    let orderNo = getQueryString("orderNo");
    let channelId = getQueryString("channelId");
    let orderStatus = getQueryString("orderStatus");
    let paymentStatus = getQueryString("paymentStatus");
    let payType = getQueryString("payType");

    let url = '../order/list';

    if (orderNo) {
        url += '?orderNo=' + orderNo;
    }
    if (channelId) {
        url += '?channelId=' + channelId;
    }
    if (orderStatus) {
        url += '?orderStatus=' + orderStatus;
    }
    if (payType) {
        url += '?payType=' + payType;
    }
    if (orderStatus) {
        url += '?orderStatus=' + orderStatus;
    }
    if (paymentStatus) {
        url += '?paymentStatus=' + paymentStatus;
    }
    url += '?orderType=' + "2";
    $("#jqGrid").Grid({
        url: url,
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '支付订单号', name: 'orderNo', index: 'order_no', width: 190},
            {label: '预约订单号', name: 'parentOrderNo', index: 'parent_order_no', width: 190},
            {label: '下单用户', name: 'contactName', index: 'contact_name', width: 100},
            {label: '联系电话', name: 'contactMobile', index: 'contact_mobile', width: 140},
            {label: '地址', name: 'address', index: 'address', width: 120},
            {label: '问题描述', name: 'problemDescription', index: 'problem_description', width: 120},
            {label: '渠道名称',name: 'channelName',index: 'channel_name',width: 80},
            {
                label: '是否外部订单', name: 'isOuterOrder', index: 'is_outer_order', width: 80,
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
                label: '下单时间', name: 'createTime', index: 'create_time', width: 140,
                formatter: function (value) {
                    return transDate(value);
                }
            },
            {
                label: '订单状态', name: 'orderStatus', index: 'order_status', width: 90,
                formatter: function (value) {
                    if (value == '2') {
                        return '服务中';
                    }else if (value == '3') {
                        return '已完成';
                    }else if (value == '4') {
                        return '作废';
                    }
                    return value;
                }
            },
            {
                label: '付款状态', name: 'paymentStatus', index: 'payment_status', width: 100,
                formatter: function (value) {
                    if (value == '1') {
                        return '待付款';
                    } else if (value == '2') {
                        return '已付款';
                    }
                    return value;
                }
            },
            {label: '订单总金额(元)', name: 'totalAmount', index: 'total_amount', width: 100},
            {label: '支付金额(元)', name: 'orderPrice', index: 'order_price', width: 100},
            {label: '优惠金额(元)', name: 'couponPrice', index: 'coupon_price', width: 100},
            {
                label: '支付时间', name: 'paymentTime', index: 'payment_time', width: 120,
                formatter: function (value) {
                    return transDate(value);
                }
            },
            {
                label: '操作', width: 140, align: 'center', sortable: false,
                formatter: function (value, col, row) {
                    if(row.paymentStatus == 1 && row.payType ==1 && row.orderStatus != 4){
                        return '<button class="btn btn-outline btn-info" onclick="vm.update(' + row.id +","+ row.payType+')">' +
                            '<i class="fa fa-info-circle"></i>&nbsp;编辑</button>' +
                            '<button class="btn btn-outline btn-info" onclick="vm.cancelOrder(' + row.id +","+ row.payType+')">' +
                            '<i class="fa fa-times-rectangle"></i>&nbsp;作废</button>';
                    }else{
                        return '<button class="btn btn-outline btn-info" onclick="vm.update(' + row.id +","+ row.payType+')">' +
                            '<i class="fa fa-info-circle"></i>&nbsp;编辑</button>';
                    }

                }
            }
        ]
    });
});

let vm = new Vue({
    el: '#rrapp',
    data: {
        isAble:false,
        showList: true,
        detail: false,
        addList: false,
        title: null,
        order: {},
        orderInfos: [],
        orderInfo1:'',
        shippings: [],
        info: {
            id: '',
            payType: ''
        },
        q: {
            orderNo: '',
            channelId: '',
            orderStatus: '',
            payType: '',
            paymentStatus: ''
        },
        isFirstPayOrder:'',
        totalAmount:'',

},
    methods: {
        query: function () {
            vm.reload();
        },
        handleChangeOrder: function(value) {
            console.log(value)
            vm.orderInfo1 = value;
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

        cancelOrder: function (rowId) {
            // let id = getSelectedRow("#jqGrid");
            if (rowId == null) {
                return;
            }
            Ajax.request({
                type: "POST",
                url: "../order/cancelOrder",
                contentType: "application/json",
                params: JSON.stringify(rowId),
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
            vm.addList = false;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'orderNo': vm.q.orderNo,
                    'channelId': vm.q.channelId,
                    'payType': vm.q.payType,
                    'orderStatus': vm.q.orderStatus,
                    'paymentStatus': vm.q.paymentStatus
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

        onlineAdd: function () {
            vm.addList = true;
            vm.title = "新增";
            vm.order = {};
            // vm.order.payType = "1";
            vm.order.orderType = "2";
            vm.getOrderInfo();
        },

        offlineAdd: function () {
            vm.addList = true;
            vm.title = "新增";
            vm.order = {};
            // vm.order.payType = "2";
            vm.order.orderType = "2";
            vm.getOrderInfo();
        },

        checkPreorder:function () {
            var url ="../order/isFirstPayOrder";
            Ajax.request({
                type: "POST",
                url: url,
                contentType: "application/json",
                params: JSON.stringify(vm.order),
                successCallback: function (r) {
                   if(!r.isFirstPayOrder){//不为首次添加支付单
                        //总金额输入框不可用
                        vm.order.totalAmount=r.totalAmount;
                        vm.isAble = true;
                    }
                }
            });

        },

        update: function (rowId,rowPayType) { //第三步：定义编辑操作
            vm.detail = true;
            vm.title = "编辑";

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
        getOrderInfo: function () {
            Ajax.request({
                url: "../order/list?orderType=2&_search=false&limit=10&page=1&sidx=&order=asc",
                async: true,
                successCallback: function (r) {
                    vm.orderInfos = r.page.list;
                    console.log(vm.orderInfos);
                }
            });
        }
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