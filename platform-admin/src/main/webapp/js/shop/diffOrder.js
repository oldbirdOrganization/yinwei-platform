$(function () {
    let storeId = getQueryString("storeId");
    let payType = getQueryString("payType");
    let channelId = getQueryString("channelId");
    let url = '../diffOrder/list';
    if (storeId) {
        url += '?storeId=' + storeId;
    }
    if (payType) {
        url += '?payType=' + payType;
    }
    if (channelId) {
        url += '?channelId=' + channelId;
    }
    $("#jqGrid").Grid({
        url: url,
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {
                label: '订单分类', name: 'orderType', index: 'order_type', width: 80,
                formatter: function (value) {
                    if (value == '1') {
                        return '预约订单';
                    } else if (value == '2') {
                        return '定金订单';
                    }
                    return '-';
                }
            },
            {label: '订单号', name: 'orderNo', index: 'order_no', width: 80},
            {label: '订单金额',name: 'orderPrice',index: 'order_price',width: 80},
            {label: '实际支付金额（元）', name: 'paymentPrice', index: 'payment_Price', width: 80},
            {label: '优惠金额', name: 'couponPrice', index: 'coupon_price', width: 80},
            {label: '支付渠道', name: 'payChannel', index: 'pay_channel', width: 80},
            {label: '收款账户', name: 'shroffAccountNumber', index: 'shroff_account_number', width: 80},
            {label: '进账流水号', name: 'paymentNo', index: 'payment_no', width: 80},
            {label: '门店名称', name: 'storeName', index: 'store_Name', width: 80}
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
        startTimeOptions: {}, //开始日期设置
        endTimeOptions: {},
        q: {
            storeId: '',
            payType: '',
            startTime: '',
            endTime: '',
            channelId: ''
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        reload: function (event) {
            vm.showList = true;
            vm.detail = false;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'storeId': vm.q.storeId,
                    'payType': vm.q.payType,
                    'channelId': vm.q.channelId
                },
                page: page
            }).trigger("reloadGrid");
        },
        startTimeChange: function(e) { //设置开始时间
            vm.q.starttime = e;
            this.endTimeOptions = {
                disabledDate: date => {
                let startTime = vm.q.starttime ? new Date(vm.q.starttime).valueOf() : '';
                return date && (date.valueOf() < startTime);
                }
            }
        },
        endTimeChange: function(e) { //设置结束时间
            vm.q.endtime = e;
            let endTime = vm.q.endtime ? new Date(vm.q.endtime).valueOf() - 1 * 24 * 60 * 60 * 1000 : '';
            this.startTimeOptions = {
                disabledDate(date) {
                    return date && date.valueOf() > endTime;
                }
            }
        },
        exportFile: function(event) {
            let storeId = getQueryString("storeId");
            let payType = getQueryString("payType");
            let channelId = getQueryString("channelId");
            var oReq = new XMLHttpRequest();
            url = "../diffOrder/exportExcel?order=1&sidx=1&limit=10&page=1";
            if (storeId) {
                url += '&storeId=' + storeId;
            }
            if (payType) {
                url += '&payType=' + payType;
            }
            if (channelId) {
                url += '&channelId=' + channelId;
            }
            oReq.open("GET", url, true);
            oReq.responseType = "blob";
            oReq.onload = function (oEvent) {
                var content = oReq.response;

                var elink = document.createElement('a');
                elink.download = "对账订单.xlsx";
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
                payType:payType,
                storeId:storeId,
                channelId:channelId
            };
            oReq.send("payType=" + payType + "&storeId=" + storeId
                + "&channelId=" + channelId + "&order=1&sidx=1&limit=10&page=1");
        },
    },
});