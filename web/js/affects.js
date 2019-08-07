$.fn.shake = function (intShakes /*Amount of shakes*/, intDistance /*Shake distance*/, intDuration /*Time duration*/) {
    this.each(function () {
        var jqNode = $(this);
        jqNode.css({ position: 'relative' });
        for (var x = 1; x <= intShakes; x++) {
            jqNode.animate({ left: (intDistance * -1) }, (((intDuration / intShakes) / 4)))
                .animate({ left: intDistance }, ((intDuration / intShakes) / 2))
                .animate({ left: 0 }, (((intDuration / intShakes) / 4)));
        }
    });
    return this;
}

function fadeAndZommIn(jqelet1, jqselet2) {
    jqelet1.animate({width: 150, height: 120, opacity: 1}, 1000,function () {
        jqselet2.fadeIn(500);
    });
}

function fadeAndZommOut(jqelet1, jqselet2) {
    jqselet2.hide();
    jqelet1.animate({width: 5, height: 4, opacity: 0}, 1000);
}

function changeBorderColor(jqelet, color) {
    jqelet.css({border: color+' solid 2px'});
}

var tororo = 'tororo/assets/tororo.model.json';

function show(path) {
    config.model.jsonPath = path;
    L2Dwidget.init(config)
}

var config = {
    model: {
        jsonPath: '', // xxx.model.json 的路径
    },
    display: {
        superSample: 1, // 超采样等级
        width: 200, // canvas的宽度
        height: 229, // canvas的高度
        position: 'right', // 显示位置：左或右
        hOffset: 0, // canvas水平偏移
        vOffset: 0, // canvas垂直偏移
    },
    mobile: {
        show: true, // 是否在移动设备上显示
        scale: 1, // 移动设备上的缩放
        motion: true, // 移动设备是否开启重力感应
    },
    react: {
        opacityDefault: 1, // 默认透明度
        opacityOnHover: 1, // 鼠标移上透明度
    },
}

function waitToLogin(time){
    if(time > 0){
        $(".time_left").text(time);
        setTimeout(function () {
            waitToLogin(time);
        }, 1000);
        time--;
    }else if (time == 0){
        location.href = "login.html";
    }
}

function waitBtn(time){
    if(time > 0){
        $(".time_left_btn").text(time);
        setTimeout(function () {
            waitBtn(time);
        }, 1000);
        time--;
    }else if (time == 0){
        $("#btn_wait").hide();
        $("#btn_get_code").show();
        $("#btn_wait_1").hide();
        $("#btn_get_code_1").show();
    }
}

function dialogBack(){
    $(".dialog_pop_add_driver").hide();
    $(".dialog_pop_delete_driver").hide();
    $(".dialog_content_process").hide();
    $(".dialog_ensure_delete").hide();
}