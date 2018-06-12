$(document).ready(function(){
   window.CHAT = {
       socket:null,
       init : function () {
           if(!window.WebSocket){
               alert("浏览器不支持WebSocket");
               return;
           }
           CHAT.socket = new WebSocket("ws://"+$("#host").val()+":8099/im");
           CHAT.socket.onopen = function (ev) {
               bindKey();//绑定键盘事件
               var in_height = $(".wechat_in").height();
               var in_text_h = in_height - 32;
               $(".wechat_in_text").css("height",in_text_h);
               var nick_name = $("#nick_name").val();
               CHAT.socket.send("[LOGIN]["+nick_name+"]["+new Date().getTime()+"]");
               $("#inorout").text("你加入聊天室").fadeIn(1000);
               fadeOut();
           }
           CHAT.socket.onmessage = function (ev) {
               setWechatContent(ev.data);
           }

           CHAT.socket.onclose = function (ev) {
           }
           CHAT.socket.onerror = function (ev) {
               alert("连接错误!");
           }
       }
   }
   $(".chat_in").click(function(){
       var nick_name = $("#nick_name").val();
       var name_l = jmz.GetLength(nick_name);
        if(name_l == 0 || name_l > 12 ){
            alert("请输入6个字符以内的名称!");
            return;
        }
       CHAT.init();
       $(".chat_out").show();
       $(".chat_in").hide();
       $("#nick_name").hide();
       $(".wechat_container").show();
       initContentWidth();

   });
   $(".chat_out").click(function(){
       var nick_name = $("#nick_name").val();
       CHAT.socket.send("[LOGOUT]["+nick_name+"]["+new Date().getTime()+"]");
        CHAT.socket.close();
        $(".chat_in").show();
        $(".chat_out").hide();
       $("#nick_name").val("").show();
       $(".wechat_container").hide();
   });

   $(".wechat_in_send").click(function () {
      var message = $(".wechat_in_text").text();
      if(message == ""){
          return;
      }
      var sender = $("#nick_name").val();
      var date = new Date().getTime();
      var receive = addItemContent("你",message,date,true);
      message = "[CHAT][" + sender + "][" + message +"][" + date + "]";
      CHAT.socket.send(message);
       $(".wechat_in_text").html("");
       $(".wechat_content").append(receive);
       setIScroll();
   });
});

function setWechatContent(message){
    var arrs = message.split("][");
    var protocol = arrs[0].replace("[","");
    var sender = arrs[1];
    var content = null;
    var date = null;
    if(protocol == "LOGIN"){
        $("#inorout").text(sender + "加入聊天室").fadeIn(1000);
        fadeOut();
    }else if(protocol == "CHAT"){
        content = arrs[2];
        date = arrs[3].replace("]","");
        var receive = addItemContent(sender,content,date,false);
        $(".wechat_in_text").html("");
        $(".wechat_content").append(receive);
        setIScroll();
    }else if(protocol == "LOGOUT"){
        $("#inorout").text(sender + "离开聊天室").fadeIn(1000);
        fadeOut();
    }
}

function addItemContent(sender,content,date,flag){
    date = formatDateTime(date);
    var html = '<div class="item_content">' +
        '<div class="send_date">'+date+'</div>';
    if(flag){
        html += '<div class="send_container_right">' +
            '<div class="send_content_text">'+content+'</div>' +
            '<div class="send_name">'+sender+'</div></div></div>';
    }else{
        html += '<div class="send_container_left">' +
            '<div class="send_name">'+sender+'</div>' +
            '<div class="send_content_text text_left">'+content+'</div></div></div>';
    }
    return html;

}

function fadeOut(){
    setTimeout(function(){
        $("#inorout").fadeOut(1000);
    },3000);
}
function formatDateTime(inputTime) {
    var date = new Date(parseInt(inputTime));
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();
    var second = date.getSeconds();
    minute = minute < 10 ? ('0' + minute) : minute;
    second = second < 10 ? ('0' + second) : second;
    return h+':'+minute+':'+second;
}
function bindKey(){
    document.onkeyup = function (event) {
        var e = event || window.event;
        var keyCode = e.keyCode || e.which;
        switch (keyCode) {
            case 13:
                $(".wechat_in_send").click();
                break;
            default:
                break;
        }
    }
}

var jmz = {};
jmz.GetLength = function(str) {
    return str.replace(/[\u0391-\uFFE5]/g,"aa").length;  //先把中文替换成两个字节的英文，在计算长度
};

function setIScroll(){
    var height = 0;
    $(".item_content").each(function(){
        height += $(this).height();
    });
    $(".wechat_content_scroll").animate({scrollTop: height}, 500);
}
function initContentWidth(){
    var width = $(".wechat_content_scroll").width() - 6;
    $(".wechat_content").css("width",width);
}
