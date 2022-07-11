function $(id) {
    return document.getElementById(id);
}

function checkRegister() {
    //用户名
    var unameText = $("unameText");
    var unameReg = /[a-zA-Z\d]{6,16}/;
    var uname = unameText.value;
    var unameErr = $("unameErr");
    if (!unameReg.test(uname)) {
        unameErr.style.visibility = "visible";
        return false;
    } else {
        unameErr.style.visibility = "hidden";
    }

    //密码
    var pwdText = $("pwdText");
    var pwd = pwdText.value;
    var pwdReg = /[\w]{8,}/;
    var pwdErr = $("pwdErr");
    if (!pwdReg.test(pwd)) {
        pwdErr.style.visibility = "visible";
        return false;
    } else {
        pwdErr.style.visibility = "hidden";
    }

    //确认密码
    var pwd2 = $("pwdText2").value;
    var pwdErr2 = $("pwdErr2");
    if (pwd !== pwd2) {
        pwdErr2.style.visibility = "visible";
        return false;
    } else {
        pwdErr2.style.visibility = "hidden";
    }

    //邮箱
    var emailText = $("emailText");
    var email = emailText.value;
    var emailReg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
    var emailErr = $("emailErr");
    if (!emailReg.test(email)) {
        emailErr.style.visibility = "visible";
        return false;
    } else {
        emailErr.style.visibility = "hidden";
    }


    return true;
}

//发送异步请求需要的对象
var xmlHttpRequest;

function createXMLHttpRequest() {
    if (window.XMLHttpRequest) {
        //符合DOM2标准的浏览器
        xmlHttpRequest = new XMLHttpRequest();
    }
}

function checkUname(uname) {
    createXMLHttpRequest();
    var url = "user.do?operate=checkUname&uname=" + uname;
    xmlHttpRequest.open("GET", url, true);
    //设置回调函数
    xmlHttpRequest.onreadystatechange = checkUnameCB;
    //发送请求
    xmlHttpRequest.send();
}

function checkUnameCB() {
    if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
        var responseText = xmlHttpRequest.responseText;
        console.log(responseText);
        var j = JSON.parse(responseText);
        if (j.uname != 0) {
            alert("用户名已存在");
        }
    }
}