$(function() {
    console.log("$(function())");
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

    // $(".sidenav>li>a").click(function () { //左侧菜单栏点击列表
    //     console.log("$(fun).sidenav>li>a  : " +this);
    //     $(this).addClass("hover");
    //     $(this).next().slideToggle();
    //     $(this).parent().siblings().children("a").removeClass("hover").next().slideUp();
    //
    // });

        $(window).resize(function () {
            //监听 浏览器width ， height 的变化
            var width = $(window).width() - 215 - 60;
            $(".table-responsive").width(width)
        });

    //init table
    -function (){
        var cc = document.cookie;
        var strcookie = document.cookie;//获取cookie字符串
        var width = $(window).width() - 215 - 60;
        $(".table-responsive").width(width);
        $(".table tbody tr:even").css("background-color","rgb(230,230,230)")
        // $("#addUser").hide();
        $("#userList2").hide();

    }();

});// $(function)
console.log("....");
// --- $(function() --- end
    var token;


    -function(){
        var url = window.location.href;
        if(url.indexOf("=") == -1){
            findState(100);
        }else if(url.indexOf("token") > 0 ){//首页跳转过来
            token = url.substr(url.indexOf("=")+1);
            findState(100);
           // 获取 token
        }else if(url.indexOf("state") > 0 ){// add 页面跳转过来
            var parameter = url.substr(url.indexOf("=")+1);
            findState(parameter);
        }

        getUserMenu(29); // 获取用户对应权限菜单
    }();


    function findState(state){
        $(".table").children("tbody").children("tr").remove();
        var url = "/user/findByState/"+state;
        $.ajax({
            url:url,
            type:"get",
            processData:false,
            contentType:false,
            success:function(data){
                creattable(data);
                $(".table tbody tr:even").css("background-color","rgb(230,230,230)")
            },
            error:function (result) {
            }
        });
    };

    // 获取用户对应权限菜单
    function getUserMenu(id){
        $(".table").children("tbody").children("tr").remove();
        var url = "/user/userMenu/"+id;
        $.ajax({
            url:url,
            type:"get",
            processData:false,
            contentType:false,
            success:function(data){
                creatLeftMenu(data);
                $('.sidenav>li>a').bind("click", function(){//左侧菜单栏点击列表
                    console.log("  .sidenav>li>a  : " +this);
                    $(this).addClass("hover");
                    $(this).next().slideToggle();
                    $(this).parent().siblings().children("a").removeClass("hover").next().slideUp();
                });
            },
            error:function (result) {
            }
        });
    };

    // 输出显示内容
    function creattable(data){
        var map = {
            100:"新用户",
            200:"设计中",
            300:"施工中",
            400:"验收",
            500:"施工完成"
        };

        for(var i = 0;i<data.length;i++){
            var tr = $("<tr></tr>");
            var td_name = $("<td id="+data[i].id+">"+data[i].name+"</td>");
            var td_name1 = $("<td>"+data[i].name+"</td>");
            var td_name3 = $("<td>"+data[i].name+"</td>");
            var td_username = $("<td>"+data[i].username+"</td>");
            var td_email = $("<td>"+data[i].email+"</td>");
            if(data[i].state == 500){
                  var a = $("<a></a>");
            }else if(data[i].state == 400){
                var xx =  map.hasOwnProperty(data[i].state);
            }else if(data[i].state == 300){
                var xx =  map.hasOwnProperty(data[i].state);
            }else if(data[i].state == 200){
                var xx =  map.hasOwnProperty(data[i].state);
            }else if(data[i].state == 100){
                var xx =  map.hasOwnProperty(data[i].state);
            }
            var td_handel = $("<td>"+"</td>");
            tr.append(td_name);
            tr.append(td_email);
            tr.append(td_name1);
            tr.append(td_username);
            tr.append(td_name3);
            tr.append(td_handel);
            $("#userListTable").append(tr);
            td_name.bind("click", function(){
                console.log("  bind   :" +this.id);
                $.ajax({
                    url:"getuser",type:"get",processData:false,contentType:false,
                    success:function(data){

                    },
                    error:function (result) {
                    }
                });
            });
            // $(".table.table-striped.table-sm").append(tr);
        }
    }

    // 创建左侧菜单栏
    function creatLeftMenu(menu){
        for(var i=0;i < menu.length;i++){
            // console.log("menu["+i+"]:  "+menu[i]);
            var id = menu[i].id;
            var name =  menu[i].name;
            var parentid =  menu[i].parentid;

            if(!parentid){ // parentid == null 既为父菜单
                var $li = $("<li></li>");
                var $a = $("<a class='withripple' href='javascript:;'>" +
                    "<i class='"+menu[i].icon+"'></i>" +
                    "<span class='sidespan'>"+name+"</span>"+
                    "<i class=\"iright pull-right\">&gt;</i>"+
                    "</a>");
                $li.append($a);
                $(".sidenav").append($li);

                var $ul = $("<ul class='sidebar-dropdown'></ul>");
                for(var j=0;j < menu.length;j++){
                    if(menu[j].parentid == id){
                        var child_li = $("<li><a href='#' class='withripple' target='myframe'>"+menu[j].name+"</a></li>");
                        if(menu[j].jsAction){
                            child_li = $("<li><a href='#' class='withripple' target='myframe'  onclick="+menu[j].jsAction+">"+menu[j].name+"</a></li>");
                        }
                        $ul.append(child_li);
                    }
                }
                $li.append($ul);
                //$(".sidenav").append($ul);
            }
        }
    }


    function process(){
        console.log();
    }

