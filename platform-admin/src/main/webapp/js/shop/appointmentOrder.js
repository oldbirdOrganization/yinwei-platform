$(function () {
    let orderNo = getQueryString("orderNo");
    let channelId = getQueryString("channelId");
    let orderStatus = getQueryString("orderStatus");
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
    url += '?orderType=' + "1";
    $("#jqGrid").Grid({
        url: url,
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '预约订单号', name: 'orderNo', index: 'order_no', width: 130},
            {label: '渠道名称',name: 'channelName',index: 'channel_name',width: 80},
            {label: '下单用户', name: 'contactName', index: 'contact_name', width: 80},
            {label: '联系电话', name: 'contactMobile', index: 'contact_mobile', width: 100},
            {label: '地址', name: 'address', index: 'address', width: 120},
            {label: '问题描述', name: 'problemDescription', index: 'problem_description', width: 120},
            {
                label: '是否外部订单', name: 'isOuterOrder', index: 'is_outer_order', width: 100,
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
                label: '订单状态', name: 'orderStatus', index: 'order_status', width: 100,
                formatter: function (value) {
                    if (value == '1') {
                        return '待指派';
                    } else if (value == '2') {
                        return '已指派';
                    }else if(value == '4'){
                        return '作废';
                    }
                    return value;
                }
            },
            {
                label: '下单时间', name: 'createTime', index: 'create_time', width: 130,
                formatter: function (value) {
                    return transDate(value);
                }
            },
            {
                label: '操作', width: 90, align: 'center', sortable: false,
                formatter: function (value, col, row) {
                    return '<button class="btn btn-outline btn-info" onclick="vm.update(' + row.id +","+ row.payType+')">' +
                        '<i class="fa fa-info-circle"></i>&nbsp;详情</button>';
                }
            }
        ]
    });
    vm.countOrderStatus();
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
            orderStatus: ''
        },
        orderStatusCount: {}
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
        countOrderStatus: function () {
            Ajax.request({
                url: "../order/count?orderType=1",
                async: true,
                successCallback: function (r) {
                    vm.count = r.count;
                    console.log(vm.count);
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
    },
    mounted:function(){
        Ajax.request({
            url: "../order/count?orderType=1",
            async: true,
            successCallback: function (r) {
                vm.orderStatusCount = r.count;

            }
        });
    }
});