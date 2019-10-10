$(function () {
    let orderStatus = getQueryString("orderStatus");
    let paymentStatus = getQueryString("paymentStatus");
    let orderType = getQueryString("orderType");
    let url = '../offlineOrder/list';
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
            {label: '订单号', name: 'orderNo', index: 'order_no', width: 120},
            {label: '会员', name: 'userName', index: 'user_name', width: 70},
            {label: '渠道名称',name: 'channelName',index: 'channel_name',width: 60},
            {
                label: '订单类型', name: 'orderType', index: 'order_type', width: 60,
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
                label: '订单状态', name: 'orderStatus', index: 'order_status', width: 60,
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
                label: '付款状态', name: 'paymentStatus', index: 'payment_status', width: 60,
                formatter: function (value) {
                    if (value == '1') {
                        return '未付款';
                    } else if (value == '2') {
                        return '已付定金';
                    } 
                    return value;
                }
            },
            {
                label: '是否第三方', name: 'isOuterOrder', index: 'is_outer_order', width: 60,
                formatter: function (value) {
                    if (value == '0') {
                        return '否';
                    } else if (value == '1') {
                        return '是';
                    }
                    return value;
                }
            },
            {label: '支付单号', name: 'paymentNo', index: 'payment_no', width: 110},

            {label: '实际支付金额（元）', name: 'orderPrice', index: 'order_price', width: 80},
            {
                label: '支付时间', name: 'paymentTime', index: 'payment_time', width: 110,
                formatter: function (value) {
                    return transDate(value);
                }
            },
            {label: '优惠金额', name: 'couponPrice', index: 'coupon_price', width: 60},
            {
                label: '下单时间', name: 'createTime', index: 'create_time', width: 110,
                formatter: function (value) {
                    return transDate(value);
                }
            },
            {
                label: '操作', width: 90, align: 'center', sortable: false,
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

        importFile: function (event) {
            var formData = new FormData();

            formData.append("uploadFile",$('#crowd_file')[0].files[0]);

            $.ajax({
                url:'../offlineOrder/readExcel',
                dataType:'json',
                type:'POST',
                async: false,
                data: formData,
                processData : false, // 使数据不做处理
                contentType : false, // 不要设置Content-Type请求头
                success: function(data){
                    console.log(data);
                    if (data.code == '0') {
                        alert('上传成功!', function (index) {
                            vm.reload();
                        });
                    }
                    if (data.code == '1001') {
                        alert(data.msg);
                    }
                    if (data.code == '1002') {
                        alert(data.msg);
                    }

                },
                error:function(response){
                    console.log(response);
                    alert('上传失败');
                }
            });
        },
        exportFile: function(event) {
            let orderStatus = getQueryString("orderStatus");
            let paymentStatus = getQueryString("paymentStatus");
            let orderType = getQueryString("orderType");
            var oReq = new XMLHttpRequest();
            url = "../offlineOrder/exportExcel?order=1&sidx=1&limit=10&page=1";
            if (paymentStatus) {
                url += '&paymentStatus=' + paymentStatus;
            }
            if (orderStatus) {
                url += '&orderStatus=' + orderStatus;
            }
            if (orderType) {
                url += '&orderType=' + orderType;
            }
            oReq.open("GET", url, true);
            oReq.responseType = "blob";
            oReq.onload = function (oEvent) {
                var content = oReq.response;

                var elink = document.createElement('a');
                elink.download = "线下订单.xlsx";
                elink.style.display = 'none';

                var blob = new Blob([content]);
                elink.href = URL.createObjectURL(blob);

                document.body.appendChild(elink);
                elink.click();

                document.body.removeChild(elink);
            };
            oReq.data = {
                page:1,
                limit:10,
                sidx:1,
                order:1,
                orderStatus:orderStatus,
                paymentStatus:paymentStatus,
                orderType:orderType
            };
            oReq.send("orderStatus=" + orderStatus + "&paymentStatus=" + paymentStatus
            + "&orderType=" + orderType + "&order=1&sidx=1&limit=10&page=1");
        },
        exportFile2: function (event) {
            let orderStatus = getQueryString("orderStatus");
            let paymentStatus = getQueryString("paymentStatus");
            let orderType = getQueryString("orderType");
            let url = '../offlineOrder/exportExcel?1=1';
            $.ajax({
                url: url,
                type: 'GET',
                data: {
                    page:1,
                    limit:10,
                    sidx:1,
                    order:1,
                    orderStatus:orderStatus,
                    paymentStatus:paymentStatus,
                    orderType:orderType
                },
                cache: false,
                dataType: "json",
                success: function (data) {
                    if (data["state"] == "0") {
                        location = host + "/" + data["path"];
                    } else {
                        ;
                    }
                },
                error:function(response){
                    console.log(response);
                    alert('导出失败');
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
                url: "../offlineOrder/info/" + rowId,
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