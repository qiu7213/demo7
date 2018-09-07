var formFlag = false;
$(function(){



    $("input").blur(function () {
        var msg="";
        if(this.attributes[2].value == "username"){
            if( this.value.length < 4){
               msg = "用户名不能为空，或者少于4位";
                $("#msg").html(msg);
                $("#msg").attr("class","msg");
                formFlag = false;
                return ;

            }
        }else if(this.attributes[2].value == "password"){
            if( this.value.length < 6){
                msg = "密码不能为空，或者少于6位";
                $("#msg").html(msg);
                formFlag = false;
                return ;
            }
        }else if(this.attributes[2].value == "address"){
            if( this.value.length < 4){
                msg = " 地址不能为空";
                $("#msg").html(msg);
                formFlag = false; return ;
            }
        }else if(this.attributes[2].value == "phone1"){
            if( this.value.length < 11){
                msg = "电话不能为空";
                $("#msg").html(msg);
                formFlag = false; return ;
            }
        }
        msg = "";
        formFlag = true;

    });

    var addFlag = function vald(){
        return "889";
    };

    console.log(addFlag());

    function subdata(){
        console.log("tijiao ...   " +formFlag);
        //有效性验证
        if(!formFlag){
            return ;
        }
    }


});


function tofindState(state){

    console.log("tofindState ...   " +state);
    window.location.href="main.html?state="+state;
}

function subdata(){
    console.log("提交 表单...   " +formFlag);
    //有效性验证
    if(!formFlag){
        return ;
    }
    else{
        var da = $("#addform").serialize();
        console.log(da);
        $.ajax({
            url:"/user/addUser/",
            type:"post",
            data:da,

            contentType:"application/x-www-form-urlencoded; charset=utf-8",
            success:function(data){
                if(data){
                    $("#msg").html("创建用户成功");
                }
                $("#addform")[0].reset();
            },
            error:function (result) {
                $("#msg").html("创建用户失败");
                $("#addform")[0].reset();
            }
        });
    }
}