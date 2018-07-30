function logout() {
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        // contentType: "application/json",
        dataType: "json",//预期服务器返回的数据类型
        url: "/user/logout",//url
        async: false,
        success: function (result) {
            // console.log(result);//打印服务端返回的数据(调试用)
            if (result.httpStatus==200) {
                alert("成功退出！");
                window.location.href = "/static/login.html";//你可以跟换里面的网址，以便成功后跳转
            }
            ;
        },
        error: function () {
            alert("异常！");
        }
    });
}

//时间截格式化
function formatDate(time) {
    if(time==null){ return ""};
    if(typeof time==='string'){ time=time.replace(/-/g,'/');};
    time = new Date(time);
    var year = time.getFullYear(),
        month = time.getMonth() + 1,
        date = time.getDate(),
        hour = time.getHours(),
        minute = time.getMinutes(),
        second = time.getSeconds();
    month = (month<10?"0":'')+month;
    date = (date<10?"0":'')+date;
    hour = (hour<10?"0":'')+hour;
    minute = (minute<10?"0":'')+minute;
    second = (second<10?"0":'')+second;
    return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
}
