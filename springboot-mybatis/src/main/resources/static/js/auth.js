//校验用户名
function checkUsername() {
    //1.获取用户名值
    var username = $("#signupname").val();
    // alert("进入效益")
    //2.定义正则
    var reg_username = /^\w{8,20}$/;

    //3.判断，给出提示信息
    var flag = reg_username.test(username);
    if (flag) {
        //用户名合法
        $("#signupname").css("border", "");
    } else {
        //用户名非法,加一个红色边框
        $("#signupname").css("border", "1px solid red");
    }

    return flag;
}

//校验邮箱
function checkEmail() {
    //1.获取邮箱
    var email = $("#signupemail").val();
    //2.定义正则		itcast@163.com
    var reg_email = /^\w+@\w+\.\w+$/;

    //3.判断
    var flag = reg_email.test(email);
    if (flag) {
        $("#signupemail").css("border", "");
    } else {
        $("#signupemail").css("border", "1px solid red");
    }

    return flag;
}


//校验密码
function checkPassword() {
    //1.获取密码值
    var password = $("#signuppassword").val();
    //2.定义正则
    var reg_password = /^\w{8,20}$/;

    //3.判断，给出提示信息
    var flag = reg_password.test(password);
    if (flag) {
        //密码合法
        $("#signuppassword").css("border", "");
    } else {
        //密码非法,加一个红色边框
        $("#signuppassword").css("border", "1px solid red");
    }

    return flag;
}

function tosignup() {
    //     alert(11111)
    var signupname = $('#signupname').val();
    var signupemail = $('#signupemail').val();
    var signuppassword = $('#signuppassword').val();


    //当某一个组件失去焦点是，调用对应的校验方法
    // $("#signupname").blur(checkUsername);
    // $("#signupemail").blur(checkEmail);
    // $("#signuppassword").blur(checkPassword);
    // alert(signupname)

    if (checkUsername() && checkPassword() && checkEmail()) {
        $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            async: false, //不是异步处理
            url: "/blog/signup",
            data: {"user_password": signuppassword, "user_email": signupemail, "user_name": signupname},
            success: function (result) {
                console.log(result);//打印服务端返回的数据(调试用)
                if (result.status == 1) {
                    alert('success','注册成功')
                    window.location.href = "/blog/signin";
                }
                if (result.status == 2) {
                    alert('warning',"该邮箱已被注册")
                }
                if (result.status == 3) {
                    alert('error',"注册失败")
                }

            },
            error: function () {
                alert('error',"异常！请重试");
            }
        });

    } else {
        alert('warning',"输入不合法，请重新输入！")
    }


}

function tosignin() {
    var signinemail = $('#signinemail').val();
    var signinpassword = $('#signinpassword').val();
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        async: false, //不是异步处理
        dataType: "json",//预期服务器返回的数据类型
        url: "/blog/checkmsg",
        data: {"user_password": signinpassword, "user_email": signinemail},
        success: function (result) {
            console.log(result);//打印服务端返回的数据(调试用)
            if (result.status != false) {
                url = window.location.pathname;
                url = url.substring(url.lastIndexOf('/') + 1, url.length);
                window.location.href = "/blog/article_page";

            } else {
                alert('error','账号或密码错误')
            }
        },
        error: function () {
            alert('error',"异常！");
        }
    });

}

function checkTitle() {
    //1.获取标题
    var title = $("#title").val();
    //2.定义标题
    if(title.length ==0 || title.length>22){
     $("#title").css("border", "1px solid #c05353");
    return false
    }
    else{
     $("#title").css("border", "");
    return true
    }

    //3.判断，给出提示信息
    console.log(title)
}

function checkType(){
    //1.获取类型
    var titletype = $("#type").val();
    //2.核对类型
    if(titletype == 0){
    $("#type").css("border", "1px solid #c05353");
    return false
    }
    else{
    $("#type").css("border", "");
    return true
    }
    //3.判断，给出提示信息
    console.log(titletype)
}

function sendarticle() {
    if(checkTitle() && checkType()){
    var text = editor.txt.html()
    var type = $("#type").val()
    var title =$("#title").val()
    // console.log(text)
    // console.log(type)
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        async: false, //不是异步处理
        url: "/blog/addarticle",
        data: {"text": text, "type": type, "title": title},
        success: function (result) {
            console.log(result);//打印服务端返回的数据(调试用)
            if (result.status == true) {
                alert("success","添加成功");
                window.location.href = "/blog/article_page";
            }
            if (result.status == false) {
                alert("warning","添加失败，请重试")
            }

        },
        error: function () {
            alert("error","异常！请重试");
        }
    });
   }
    else{
    alert("warning","请输入文章标题或选择文章类型")
    }
}
function alert(e,text){
    $modal({
        type: 'alert', //弹框类型  'alert' or  'confirm' or 'message'   message提示(开启之前如果之前含有弹框则清除)
        icon: e, // 提示图标显示 'info' or 'success' or 'warning' or 'error'  or 'question'
        timeout: 2000, // 单位 ms  显示多少毫秒后关闭弹框 （ confirm 下无效 | 不传默认为 2000ms | 最短显示时间为500ms）
        title:text, // 提示文字
        content: '', // 提示文字
        top:20, //距离顶部距离 单位px
        center: false,// 是否绝对居中 默认为false  设置true后   top无效
        transition: 300, //过渡动画 默认 200   单位ms
        closable: true, // 是否显示可关闭按钮  默认为 false
        mask:true, // 是否显示遮罩层   默认为 false
        pageScroll: true, // 是否禁止页面滚动
        width:300, // 单位 px 默认显示宽度 最下默认为300
        maskClose:true, // 是否点击遮罩层可以关闭提示框 默认为false
    })
}


var Auth = {
    vars: {
        lowin: document.querySelector('.lowin'),
        lowin_brand: document.querySelector('.lowin-brand'),
        lowin_wrapper: document.querySelector('.lowin-wrapper'),
        lowin_login: document.querySelector('.lowin-login'),
        lowin_wrapper_height: 0,
        login_back_link: document.querySelector('.login-back-link'),
        forgot_link: document.querySelector('.forgot-link'),
        login_link: document.querySelector('.login-link'),
        login_btn: document.querySelector('.login-btn'),
        register_link: document.querySelector('.register-link'),
        password_group: document.querySelector('.password-group'),
        password_group_height: 0,
        lowin_register: document.querySelector('.lowin-register'),
        lowin_footer: document.querySelector('.lowin-footer'),
        box: document.getElementsByClassName('lowin-box'),
        option: {}
    },
    register(e) {
        Auth.vars.lowin_login.className += ' lowin-animated';
        setTimeout(() => {
            Auth.vars.lowin_login.style.display = 'none';
        }, 500);
        Auth.vars.lowin_register.style.display = 'block';
        Auth.vars.lowin_register.className += ' lowin-animated-flip';

        Auth.setHeight(Auth.vars.lowin_register.offsetHeight + Auth.vars.lowin_footer.offsetHeight);

        e.preventDefault();
    },
    login(e) {
        Auth.vars.lowin_register.classList.remove('lowin-animated-flip');
        Auth.vars.lowin_register.className += ' lowin-animated-flipback';
        Auth.vars.lowin_login.style.display = 'block';
        Auth.vars.lowin_login.classList.remove('lowin-animated');
        Auth.vars.lowin_login.className += ' lowin-animatedback';
        setTimeout(() => {
            Auth.vars.lowin_register.style.display = 'none';
        }, 500);

        setTimeout(() => {
            Auth.vars.lowin_register.classList.remove('lowin-animated-flipback');
            Auth.vars.lowin_login.classList.remove('lowin-animatedback');
        }, 1000);

        Auth.setHeight(Auth.vars.lowin_login.offsetHeight + Auth.vars.lowin_footer.offsetHeight);

        e.preventDefault();
    },
    forgot(e) {
        Auth.vars.password_group.classList += ' lowin-animated';
        Auth.vars.login_back_link.style.display = 'block';

        setTimeout(() => {
            Auth.vars.login_back_link.style.opacity = 1;
            Auth.vars.password_group.style.height = 0;
            Auth.vars.password_group.style.margin = 0;
        }, 100);

        Auth.vars.login_btn.innerText = 'Forgot Password';

        Auth.setHeight(Auth.vars.lowin_wrapper_height - Auth.vars.password_group_height);
        Auth.vars.lowin_login.querySelector('form').setAttribute('action', Auth.vars.option.forgot_url);

        e.preventDefault();
    },
    loginback(e) {
        Auth.vars.password_group.classList.remove('lowin-animated');
        Auth.vars.password_group.classList += ' lowin-animated-back';
        Auth.vars.password_group.style.display = 'block';

        setTimeout(() => {
            Auth.vars.login_back_link.style.opacity = 0;
            Auth.vars.password_group.style.height = Auth.vars.password_group_height + 'px';
            Auth.vars.password_group.style.marginBottom = 30 + 'px';
        }, 100);

        setTimeout(() => {
            Auth.vars.login_back_link.style.display = 'none';
            Auth.vars.password_group.classList.remove('lowin-animated-back');
        }, 1000);

        Auth.vars.login_btn.innerText = 'Sign In';
        Auth.vars.lowin_login.querySelector('form').setAttribute('action', Auth.vars.option.login_url);

        Auth.setHeight(Auth.vars.lowin_wrapper_height);

        e.preventDefault();
    },
    setHeight(height) {
        Auth.vars.lowin_wrapper.style.minHeight = height + 'px';
    },
    brand() {
        Auth.vars.lowin_brand.classList += ' lowin-animated';
        setTimeout(() => {
            Auth.vars.lowin_brand.classList.remove('lowin-animated');
        }, 1000);
    },
    init(option) {
        Auth.setHeight(Auth.vars.box[0].offsetHeight + Auth.vars.lowin_footer.offsetHeight);

        Auth.vars.password_group.style.height = Auth.vars.password_group.offsetHeight + 'px';
        Auth.vars.password_group_height = Auth.vars.password_group.offsetHeight;
        Auth.vars.lowin_wrapper_height = Auth.vars.lowin_wrapper.offsetHeight;

        Auth.vars.option = option;
        Auth.vars.lowin_login.querySelector('form').setAttribute('action', option.login_url);

        var len = Auth.vars.box.length - 1;

        for (var i = 0; i <= len; i++) {
            if (i !== 0) {
                Auth.vars.box[i].className += ' lowin-flip';
            }
        }

        Auth.vars.forgot_link.addEventListener("click", (e) => {
            Auth.forgot(e);
        });

        Auth.vars.register_link.addEventListener("click", (e) => {
            Auth.brand();
            Auth.register(e);
        });

        Auth.vars.login_link.addEventListener("click", (e) => {
            Auth.brand();
            Auth.login(e);
        });

        Auth.vars.login_back_link.addEventListener("click", (e) => {
            Auth.loginback(e);
        });
    }
}