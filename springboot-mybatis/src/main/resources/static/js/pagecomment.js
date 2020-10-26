layui.use(['element', 'jquery', 'form', 'layedit', 'flow'], function () {
    var element = layui.element;
    var form = layui.form;
    var $ = layui.jquery;
    var layedit = layui.layedit;
    var flow = layui.flow;
    //评论和留言的编辑器的验证
    form.verify({
        content: function (value) {
            value = $.trim(layedit.getContent(editIndex));
            if (value == "") return "请输入内容";
            layedit.sync(editIndex);
        },
        replyContent: function (value) {
            if (value == "") return "请输入内容";
        }
    });

    //回复按钮点击事件
    $('#blog-comment').on('click', '.btn-reply', function () {
        var targetId = $(this).data('targetid')
            , targetName = $(this).data('targetname')
            , $container = $(this).parent('p').parent().siblings('.replycontainer');
        if ($(this).text() == '回复') {
            $container.find('textarea').attr('placeholder', '回复【' + targetName + '】');
            $container.removeClass('layui-hide');
            $container.find('input[name="targetUserId"]').val(targetId);
            $(this).parents('.blog-comment li').find('.btn-reply').text('回复');
            $(this).text('收起');
        } else {
            $container.addClass('layui-hide');
            $container.find('input[name="targetUserId"]').val(0);
            $(this).text('回复');
        }
    });


    // 点击提交按钮提交评论并获取评论做展示
    $('#commit-btn').click(function () {
        var comment = $('#remarkEditor').val();

        var test = window.location.href;
        var article_id = test.split("?article_id=")[1];

        if (!comment) {
            alert("输入内容不能为空！");
        } else {
            $.ajax({
                type: "POST",
                dataType: "json",
                async: false,
                url: "/blog/comment",
                data: {article_review: comment, article_id: article_id},
                success: function (result) {
                    console.log(result.review[1].article_review);
                    var s = "";
                    for (var i = 0; i <= result.length; i++) {
                        var t =
                            "<li class=\"list-group-item\">\n" +
                            "<div>\n" +
                            "<span>" + result.review[1].user_name + "</span>\n" +
                            "</div>\n" +
                            "<div class=\"comment_con\">\n" +
                            "<span>" + result.review[1].article_review + "</span>\n" +
                            "</div>\n" +
                            "</li>"
                        s += t;
                    }
                    console.log(s);
                    $('#reviews').html(s);
                },
                error: function () {
                    alert("异常，请重试！");
                }
            });


            // $.post(
            //     'blog/comment',
            //     {article_review: comment},
            //     function (result) {
            //         $.each(function () {
            //             var username = result.get("username")
            //             var comment = result.get("article_review");
            //
            //             var s = '<li class="list-group-item">\n' +
            //                 '<div>\n' +
            //                 '<span>' + username + '</span>\n' +
            //                 '</div>\n' +
            //                 '<div class="comment_con">\n' +
            //                 '<p>' + comment + '</p>\n' +
            //                 '</div>\n' +
            //                 '</li>'
            //             $('#reviews').append(s);
            //         });
            //     },
            //     "json"
            // );


        }

    });

});
 