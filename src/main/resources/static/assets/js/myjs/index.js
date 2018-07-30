
// 跨域时将XHR对象的withCredentials设为true，请求时http头自动将cookie带上
var roleId = localStorage.getItem('roles');
// alert("roleId:"+roleId);
var type = roleId.split(",")

$('#navlist li').hide();
$('#navlist .navshowa').show();
for(var i=0;i<type.length;i++) {
    var mtype = parseInt(type[i]);
    // alert("mtype:"+mtype);
    if (mtype==1) {
        // alert("mtype1");
        $('#navlist .navshowa1').show();
    }
    if (mtype==2) {
        // alert("mtype2");
        $('#navlist .navshowa2').show();
        $('#navlist .navshowa21').show();
        $('#navlist .navshowa22').show();


    }
    if (mtype==3) {
        // alert("mtype3");
        $('#navlist .navshowa3').show();
        $('#navlist .navshowa31').show();
        $('#navlist .navshowa32').show();

    }
    if (mtype==4) {
        // alert("mtype4");
        $('#navlist .navshowa4').show();
    }
    if (mtype ==5) {
        // alert("mtype5");
        $('#navlist .navshowa5').show();
    }
}

var msid = localStorage.getItem("sid");
var mname =localStorage.getItem("username");
// alert("msid:"+msid);
// alert("mname:"+mname);

if(mname!=""&&mname!=null)
{
    $("#username").text(mname);
}
else
{
    $("#username").text("未登录");

}















$.ajaxSetup({
    xhrFields:{
        withCredentials:true
    },
    dataType:"json",
    dataFilter:function(data,type){
        if(type == "json" || type == "JSON" || type == "application/json;charset=UTF-8"){
            var result = JSON.parse(data);
            if(result.code == 709 || result.code == 701 || result.code == 731){
                // jAlert(result.exception);
                // window.appLogin && (window.appLogin.loginTips = result.exception);
                // window.appVerify && (window.appVerify.verifyTips = result.exception);
            }else if(result.code == 805){
                //短信验证码错误
                // appVerify.verifyTips = result.exception;
            }else if(result.code == 700 || result.code == 723){
                //未登录、被禁用
            }else if(result.code == 715){
                //无权限
            }else if(result.code == 1001){

            }else if(result.code == 6){
                //数据类型错误
            }else if(result.code != 0 && result.code != 6001){
                return data;
            }else{
                return data;
            }
        }else{
            return data;
        }
    },
    error:function(){
    }
});
