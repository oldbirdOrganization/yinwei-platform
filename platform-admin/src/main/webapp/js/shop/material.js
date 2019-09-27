$(function () {
    let id = getQueryString("id");
    let url = '../material/list';
    if (id) {
        url += '?id=' + id;
    }
    $("#jqGrid").Grid({
        url: url,
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '名称', name: 'name', index: 'name', width: 80},
            {label: '品牌', name: 'brand', index: 'brand', width: 80},
            {label: '规格型号', name: 'model', index: 'model', width: 80},
            {label: '单位', name: 'unit', index: 'unit', width: 80},
            {label: '单价', name: 'price', index: 'price', width: 80}
        ]
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        material: {},
        q: {
            name: ''
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        importFile: function (event) {
            var formData = new FormData();

            formData.append("uploadFile",$('#crowd_file')[0].files[0]);

            $.ajax({
                url:'../material/readExcel',
                dataType:'json',
                type:'POST',
                async: false,
                data: formData,
                processData : false, // 使数据不做处理
                contentType : false, // 不要设置Content-Type请求头
                success: function(data){
                    console.log(data);
                    if (data.code == '0') {
                        alert('上传成功！');
                    }

                },
                error:function(response){
                    console.log(response);
                    alert('上传失败');
                }
            });
        },
        exportFile: function(event) {
            var oReq = new XMLHttpRequest();
            url = "../material/exportExcel?order=1&sidx=1&limit=10&page=1";
            oReq.open("GET", url, true);
            oReq.responseType = "blob";
            oReq.onload = function (oEvent) {
                var content = oReq.response;

                var elink = document.createElement('a');
                elink.download = "材料列表.xlsx";
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
                order:1
            };
            oReq.send("order=1&sidx=1&limit=10&page=1");
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.material = {};
        },
        update: function (event) {
            var id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
        },
        saveOrUpdate: function (event) {
            var url = vm.material.id == null ? "../material/save" : "../material/update";
            Ajax.request({
                type: "POST",
                url: url,
                contentType: "application/json",
                params: JSON.stringify(vm.material),
                successCallback: function (r) {
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
                }
            });
        },
        del: function (event) {
            debugger;
            var ids = getSelectedRows("#jqGrid");
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                Ajax.request({
                    type: "POST",
                    url: "../material/delete",
                    contentType: "application/json",
                    params: JSON.stringify(ids),
                    successCallback: function (r) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    }
                });
            });
        },
        getInfo: function (id) {
            Ajax.request({
                url: "../material/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.material = r.material;
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.name},
                page: page
            }).trigger("reloadGrid");
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
