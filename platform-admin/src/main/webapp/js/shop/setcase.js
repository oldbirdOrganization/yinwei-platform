$(function () {
    $("#jqGrid").Grid({
        url: '../setcase/list',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '方案主题', name: 'title', index: 'title', width: 120},
            {
                label: '缩略图', name: 'casePicUrl', index: 'case_pic_url', width: 90, formatter: function (value) {
                    return transImg(value);
                }
            },
            {
                label: '创建时间', name: 'createTime', index: 'create_time', width: 120,
                formatter: function (value) {
                    return transDate(value);
                }
            },
            {label: '创建人', name: 'createBy', index: 'create_by', width: 80},
            {
                label: '操作', width: 80, align: 'center', sortable: false,
                formatter: function (value, col, row) {
                    return '<button class="btn btn-outline btn-info" onclick="vm.getInfo(' + row.id + ')">' +
                        '<i class="fa fa-info-circle"></i>&nbsp;详情</button>';
                }
            }
        ]
    });
    $('#content').editable({
        inlineMode: false,
        alwaysBlank: true,
        height: 'auto', //高度
        language: "zh_cn",
        minHeight: '200px',
        spellcheck: false,
        plainPaste: true,
        enableScript: false,
        imageButtons: ["floatImageLeft", "floatImageNone", "floatImageRight", "linkImage", "replaceImage", "removeImage"],
        allowedImageTypes: ["jpeg", "jpg", "png", "gif"],
        imageUploadURL: '../sys/oss/upload',//上传到本地服务器
        imageUploadParams: {id: "edit"},
        // imageManagerDeleteURL: '../sys/oss/delete',//删除图片(有问题)
        imagesLoadURL: '../sys/oss/queryAll'//管理图片
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        detail: false,
        updateDetail:false,
        title: null,
        setcase: {casePicUrl: ''},
        ruleValidate: {
            title: [
                {required: true, message: '方案主题不能为空', trigger: 'blur'}
            ]
        },
        q: {
            title: ''
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.detail = false;
            vm.title = "新增";
            vm. setcase = {casePicUrl: ''},
            $('#content').editable('setHTML', '');
        },
        update: function (event) {
            var id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.updateDetail = true;
            vm.title = "修改";
            vm.getInfo(id)
        },
        saveOrUpdate: function (event) {
            var url = vm.setcase.id == null ? "../setcase/save" : "../setcase/update";

            //编辑器内容
            vm.setcase.content = $('#content').editable('getHTML');
            Ajax.request({
                type: "POST",
                url: url,
                contentType: "application/json",
                params: JSON.stringify(vm.setcase),
                successCallback: function (r) {
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
                }
            });
        },
        del: function (event) {
            var ids = getSelectedRows("#jqGrid");
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                Ajax.request({
                    type: "POST",
                    url: "../setcase/delete",
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
            vm.showList = false;
            vm.updateDetail = false;
            vm.detail = true;
            vm.title = "详情";
            Ajax.request({
                url: "../setcase/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.setcase = r.setcase;
                    $('#content').editable('setHTML', vm.setcase.content);
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'title': vm.q.title},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
        },

        handleSuccessCasePicUrl: function (res, file) {
            vm.setcase.casePicUrl = file.response.url;
        },

        handleFormatError: function (file) {
            this.$Notice.warning({
                title: '文件格式不正确',
                desc: '文件 ' + file.name + ' 格式不正确，请上传 jpg 或 png 格式的图片。'
            });
        },
        handleMaxSize: function (file) {
            this.$Notice.warning({
                title: '超出文件大小限制',
                desc: '文件 ' + file.name + ' 太大，不能超过 2M。'
            });
        },
        eyeImageCasePicUrl: function () {
            var url = vm.setcase.casePicUrl;
            eyeImage(url);
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