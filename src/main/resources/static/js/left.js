$(function() {
    /* 侧边栏切换形态 */
    $(".navbar-side a").click(function () {
        $("body").toggleClass("sidebar-collapse");
        if ($("body").hasClass("sidebar-collapse")) {
            $(".sidebar > h4").html("");

            var width = $(window).width() - 215 - 60 + 155;
            $(".table-responsive").width(width)
            var mr = $(".signout").css("margin-right");
            var num_mr = mr.substr(mr,mr.length - 2);
            $(".signout").css("margin-right",num_mr - 155);

        } else {
            $(".sidebar > h4").html("经销商管理系统");

            var width = $(window).width() - 215 - 60;
            $(".table-responsive").width(width);
            var mr = $(".signout").css("margin-right");
            var num_mr = mr.substr(mr,mr.length - 2);
            $(".signout").css("margin-right",num_mr + 155);
            $(".signout").css("margin-right",218);

        }
        return false;
    });

    $(".sidenav>li>a").click(function () {
        $(this).addClass("hover");
        $(this).next().slideToggle();
        $(this).parent().siblings().children("a").removeClass("hover").next().slideUp();

    });



});
