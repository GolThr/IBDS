function init() {
    $("#manager_upload").css({'border-bottom':'rgba(255, 255, 255, 1) solid 5px'});
}

//UploadFile
var totalSize = 0;
var canUpload = false;
function onUploadFile() {
    $(".dialog_btn_upload_file").hide();
    $(".dialog_btn_upload_complete").show();
    //创建FormData对象，初始化为form表单中的数据。需要添加其他数据可使用formData.append("property", "value");
    var formData = new FormData();
    formData.append("file", $('#file_input')[0].files[0]);
    console.log($('#file_input')[0].files[0]);
    console.log(formData);
    //ajax_1_POST
    //发出(FormData)：formDta:File(文件对象)
    //接收(json)：ifsuccess:0(失败),1(上传成功)
    $.ajax({
        url: "http://localhost:63342/upload/",
        type: "POST",
        data: formData,
        xhr: function () { //获取ajaxSettings中的xhr对象，为它的upload属性绑定progress事件的处理函数
            myXhr = $.ajaxSettings.xhr();
            if (myXhr.upload) { //检查upload属性是否存在
                //绑定progress事件的回调函数
                myXhr.upload.addEventListener('progress', progressHandlingFunction, false);
            }
            return myXhr; //xhr对象返回给jQuery使用
        },
        success: function (result) {
            if(!result.ifsuccess){
                alert('内部错误，请联系管理员！');
            }
        },
        error: function(result){
            console.log("error!");
            alert("请求失败，请重试");
        },
        contentType: false, //必须false才会自动加上正确的Content-Type
        processData: false  //必须false才会避开jQuery对 formdata 的默认处理
    });
}

function onUploadComplete() {
    dialogBack();
}

function onUploadBusData(){
    $(".dialog_content").fadeIn("fast");
    $(".dialog_pop_upload_file").fadeIn("fast");
    $(".dialog_btn_upload_file").show();
    $(".dialog_btn_upload_complete").hide();
    $(".dialog_btn_upload_file").remove("dialog_btn_upload_file_clickable");
    $('#progress').css('width', "0");
    canUpload = false;
}

//绑定所有type=file的元素的onchange事件的处理函数
$(':file').change(function () {
    var file = this.files[0]; //假设file标签没打开multiple属性，那么只取第一个文件就行了
    fileName = file.name;
    size = file.size;
    type = file.type;
    url = window.URL.createObjectURL(file); //获取本地文件的url，如果是图片文件，可用于预览图片

    totalSize += size;
    $("#file_name").text(fileName);
    if(type === "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" || type === "application/vnd.ms-excel"){
        $(".dialog_btn_upload_file").text("确认上传");
        $(".dialog_btn_upload_file").addClass("dialog_btn_upload_file_clickable");
        canUpload = true;
    }else {
        $(".dialog_btn_upload_file").text("需要Excel文件");
        $(".dialog_btn_upload_file").remove("dialog_btn_upload_file_clickable");
        canUpload = false;
    }
});

//上传进度回调函数：
function progressHandlingFunction(e) {
    if (e.lengthComputable) {
        $('#progress').attr({ value: e.loaded, max: e.total }); //更新数据到进度条
        var percent = e.loaded / e.total * 100;
        $('.dialog_btn_upload_complete').text(percent.toFixed(2) + "%");
        $('#progress').css('width', percent.toFixed(2) + "%");
        if(e.loaded === e.total){
            $(".dialog_btn_upload_complete").text("上传完成");
        }
    }
}

//截止事件
$(".dialog_pop").click(function (event) {
    event.stopPropagation();
});