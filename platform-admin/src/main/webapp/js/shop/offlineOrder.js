$(function () {
    let orderNo = getQueryString("orderNo");
    let paymentStatus = getQueryString("paymentStatus");
    let channelId = getQueryString("channelId")
    let isOuterOrder = getQueryString("isOuterOrder")
    let url = '../offlineOrder/list';
    if (paymentStatus) {
        url += '?paymentStatus=' + paymentStatus;
    }
    if (orderNo) {
        url += '?orderNo=' + orderNo;
    }
    if (channelId) {
        url += '?channelId=' + channelId;
    }
    if (isOuterOrder) {
        url += '?isOuterOrder=' + isOuterOrder;
    }
    $("#jqGrid").Grid({
        url: url,
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '支付订单号', name: 'orderNo', index: 'order_no', width: 150},
            {label: '预约订单号', name: 'parentOrderNo', index: 'parent_order_no', width: 150},
            {label: '下单用户', name: 'userName', index: 'user_name', width: 80},
            {label: '渠道名称',name: 'channelName',index: 'channel_name',width: 60},
            {
                label: '付款状态', name: 'paymentStatus', index: 'payment_status', width: 60,
                formatter: function (value) {
                    if (value == '1') {
                        return '未付款';
                    } else if (value == '2') {
                        return '已付款';
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
            {
                label: '进账科目', name: 'item', index: 'item', width: 60,
                formatter: function (value) {
                    if (value == '1') {
                        return '定金';
                    } else if (value == '2') {
                        return '进度款';
                    }else if (value == '3') {
                        return '尾款';
                    }
                    return value;
                }
            },
            {label: '总金额(元)', name: 'totalAmount', index: 'total_amount', width: 110},
            {label: '收款账号', name: 'shroffAccountNumber', index: 'shroff_account_number', width: 110},
            {label: '进账流水号', name: 'paymentNo', index: 'paymentNo', width: 110},
            {label: '实际支付金额(元)', name: 'orderPrice', index: 'order_price', width: 80},
            {
                label: '下单时间', name: 'createTime', index: 'create_time', width: 130,
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
            orderNo: '',
            paymentStatus: '',
            channelId:'',
            isOuterOrder:''
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

            let orderNo = getQueryString("orderNo");
            let paymentStatus = getQueryString("paymentStatus");
            let channelId = getQueryString("channelId")
            let isOuterOrder = getQueryString("isOuterOrder")

            var oReq = new XMLHttpRequest();
            let url = "../offlineOrder/exportExcel?order=1&sidx=1&limit=10&page=1";

            if (paymentStatus) {
                url += '?paymentStatus=' + paymentStatus;
            }
            if (orderNo) {
                url += '?orderNo=' + orderNo;
            }
            if (channelId) {
                url += '?channelId=' + channelId;
            }
            if (isOuterOrder) {
                url += '?isOuterOrder=' + isOuterOrder;
            }

            if (paymentStatus) {
                url += '&paymentStatus=' + paymentStatus;
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
                orderNo: orderNo,
                paymentStatus: paymentStatus,
                channelId:channelId,
                isOuterOrder:isOuterOrder
            };
            oReq.send("orderNo=" + orderNo + "&paymentStatus=" + paymentStatus
            + "&channelId=" + channelId + "&isOuterOrder=" + isOuterOrder +"&order=1&sidx=1&limit=10&page=1");
        },
        exportFile2: function (event) {
            let orderNo = getQueryString("orderNo");
            let paymentStatus = getQueryString("paymentStatus");
            let channelId = getQueryString("channelId")
            let isOuterOrder = getQueryString("isOuterOrder")
            let url = '../offlineOrder/exportExcel?1=1';
            $.ajax({
                url: url,
                type: 'GET',
                data: {
                    page:1,
                    limit:10,
                    sidx:1,
                    order:1,
                    orderNo: orderNo,
                    paymentStatus: paymentStatus,
                    channelId:channelId,
                    isOuterOrder:isOuterOrder
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
                    'orderNo': vm.q.orderNo,
                    'paymentStatus': vm.q.paymentStatus,
                    'channelId': vm.q.channelId,
                    'isOuterOrder':vm.q.isOuterOrder
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