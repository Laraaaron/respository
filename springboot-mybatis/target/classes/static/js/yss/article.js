
window.onload = function () {
    NProgress.done();
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        async:false, //不是异步处理
        url: "/blog/togetarticle" ,
        data: {},
        success: function (result) {
            var text = "";
            var hot_text = "";
            console.log(result.lenth)
            var result_type=""
            for (var i =0;i<result.lenth;i++){
                if (result.article[i].article_type == 1){
                     result_type = "Java";
                }
                if (result.article[i].article_type == 2){
                     result_type = "前端";
                }
                if (result.article[i].article_type ==3){
                     result_type = "Python";
                }
                if (result.article[i].article_type == 4){
                     result_type = "PHP";
                }
                if (result.article[i].article_type == 5){
                     result_type = "其他";
                }
                // console.log(result);//打印服务端返回的数据(调试用)
                var data = "<section class=\"article-item zoomIn article\">\n" +
                    "\t\t\t\t\t\t\t<div class=\"fc-flag\">置顶</div>\n" +
                    "\t\t\t\t\t\t\t<h5 class=\"title\">\n" +
                    "\t\t\t\t\t\t\t\t<span class=\"fc-blue\">【原创】</span>\n" +
                    "\t\t\t\t\t\t\t\t<a href=\"/blog/article_read\">" +
                    result.article[i].article_title +
                    "</a>\n" +
                    "\t\t\t\t\t\t\t</h5>\n" +
                    "\t\t\t\t\t\t\t<div class=\"time\">\n" +
                    "\t\t\t\t\t\t\t\t<span class=\"day\">"+
                    result.article[i].article_time.split("-")[2].split(" ")[0]
                    +"</span>\n" +
                    "\t\t\t\t\t\t\t\t<span class=\"month fs-18\">"+
                    result.article[i].article_time.split("-")[1]
                    +"<span class=\"fs-14\">月</span></span>\n" +
                    "\t\t\t\t\t\t\t\t<span class=\"year fs-18 ml10\">"+
                    result.article[i].article_time.split("-")[0]
                    +"</span>\n" +
                    "\t\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t\t\t<div class=\"content\">\n" +
                    "\t\t\t\t\t\t\t\t<a href=\"/blog/article_read\" class=\"cover img-light\">\n" +
                    "\t\t\t\t\t\t\t\t\t<img src=\"../image/java.jpg\">\n" +
                    "\t\t\t\t\t\t\t\t</a>\n" +
                    result.article[i].article_text+
                    "\t\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t\t\t<div class=\"read-more\">\n" +
                    "\t\t\t\t\t\t\t\t<a href=\"/blog/article_read\" class=\"fc-black f-fwb\">继续阅读</a>\n" +
                    "\t\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t\t\t<aside class=\"f-oh footer\">\n" +
                    "\t\t\t\t\t\t\t\t<div class=\"f-fl tags\">\n" +
                    "\t\t\t\t\t\t\t\t\t<span class=\"fa fa-tags fs-16\"></span>\n" +
                    "\t\t\t\t\t\t\t\t\t<a class=\"tag\">"+
                    result_type
                    +"</a>\n" +
                    "\t\t\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t\t\t\t<div class=\"f-fr\">\n" +
                    "\t\t\t\t\t\t\t\t\t<span class=\"read\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t<i class=\"fa fa-eye fs-16\"></i>\n" +
                    "\t\t\t\t\t\t\t\t\t\t<i class=\"num\">"+
                    result.article[i].article_comments_like
                    +"</i>\n" +
                    "\t\t\t\t\t\t\t\t\t</span>\n" +
                    "\t\t\t\t\t\t\t\t\t<span class=\"ml20\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t<i class=\"fa fa-comments fs-16\"></i>\n" +
                    "\t\t\t\t\t\t\t\t\t\t<a href=\"javascript:void(0)\" class=\"num fc-grey\">"+
                    result.article[i].article_comments_nums
                    +"</a>\n" +
                    "\t\t\t\t\t\t\t\t\t</span>\n" +
                    "\t\t\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t\t\t</aside>\n" +
                    "\t\t\t\t\t\t</section>";
                text += data;
            }
            for (var i=0;i<result.hot_lenth;i++){

                var hot_data = "<li> <a href=\"/Blog/article_read/?article_id="+
                    result.hotarticle[i].article_id
                    +"\">"+
                    result.hotarticle[i].article_title
                    +"</a></li>"
                hot_text += hot_data;

            }
            // console.log(hot_text)
            $("#LAY_bloglist").html(text)
            $("#hot_article").html(hot_text)
            if (result !=null){
                $("#logo").html(result.username)
            }
        },
        error : function() {
            alert("异常！请重试");
        }
    });
};

layui.use(['jquery'], function () {
    var $ = layui.jquery;
    article.Init($);//初始化共用js
   
});
var article = {};
article.Init = function ($) {
    //var $ = layui.jquery,
    var slider = 0;
    blogtype();
    //类别导航开关点击事件
    $('.category-toggle').click(function (e) {
        e.stopPropagation();    //阻止事件冒泡
        categroyIn();
    });
    //类别导航点击事件，用来关闭类别导航
    $('.article-category').click(function () {
        categoryOut();
    });
    //遮罩点击事件
    $('.blog-mask').click(function () {
        categoryOut();
    });
    $('.f-qq').on('click', function () {
        window.open('http://connect.qq.com/widget/shareqq/index.html?url=' + $(this).attr("href") + '&sharesource=qzone&title=' + $(this).attr("title") + '&pics=' + $(this).attr("cover") + '&summary=' + $(this).attr("desc") + '&desc=你的分享简述' + $(this).attr("desc"));
    });
    $("body").delegate(".fa-times", "click", function () {
        $(".search-result").hide().empty(); $("#searchtxt").val("");
        $(".search-icon i").removeClass("fa-times").addClass("fa-search");
    });
    //显示类别导航
    function categroyIn() {
        $('.category-toggle').addClass('layui-hide');
        $('.article-category').unbind('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend');
        $('.blog-mask').unbind('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend');
        $('.blog-mask').removeClass('maskOut').addClass('maskIn');
        $('.blog-mask').removeClass('layui-hide').addClass('layui-show');
        $('.article-category').removeClass('categoryOut').addClass('categoryIn');
        $('.article-category').addClass('layui-show');
    }
    //隐藏类别导航
    function categoryOut() {
        $('.blog-mask').on('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
            $('.blog-mask').addClass('layui-hide');
        });
        $('.article-category').on('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
            $('.article-category').removeClass('layui-show');
            $('.category-toggle').removeClass('layui-hide');
        });
        $('.blog-mask').removeClass('maskIn').addClass('maskOut').removeClass('layui-show');
        $('.article-category').removeClass('categoryIn').addClass('categoryOut');
    }
    function blogtype() {
        $('#category li').hover(function () {
            $(this).addClass('current');
            var num = $(this).attr('data-index');
            $('.slider').css({ 'top': ((parseInt(num) - 1) * 40) + 'px' });
        }, function () {
            $(this).removeClass('current');
            $('.slider').css({ 'top': slider });
        });
        $(window).scroll(function (event) {
            var winPos = $(window).scrollTop();
            if (winPos > 750)
                $('#categoryandsearch').addClass('fixed');
            else
                $('#categoryandsearch').removeClass('fixed');
        });
    };
};

