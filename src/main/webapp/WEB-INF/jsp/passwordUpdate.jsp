<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>update password</title>
</head>
<body>
<div class="modal fade password-update-modal" tabindex="-1" role="dialog" aria-labelledby="password-update-modal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">密码修改</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal update_password_form">
                    <div class="form-group">
                        <label for="newPassword" class="col-sm-2 control-label">用户姓名</label>
                        <div class="col-sm-8">
                            <input type="text" name="username" class="form-control" id="username">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="newPassword" class="col-sm-2 control-label">新密码</label>
                        <div class="col-sm-8">
                            <input type="text" name="password" class="form-control" id="newPassword" >
                            <span id="helpBlock_update_inputPassword" class="help-block"></span>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary password_save_btn">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script type="text/javascript">
    <!-- ==========================密码修改操作=================================== -->
   

    $(".hrms_update_password").click(function () {
        $.ajax({
                url: "/hrms/getUserMsg",
                type: "GET",
                success: function (result) {
                    $("#username").attr("value",result.extendInfo.username)
                }
             });
        $('.password-update-modal').modal({
            backdrop: static,
            keyboard: true
        });
    });


   //对数字密码的验证
   $(".password_save_btn").click(function () {
       var inputPassword = $("#newPassword").val();
       //验证格式：6位数字
       var regPassword = /(^[0-9]{6}$)/;
       if (!regPassword.test(inputPassword)) {
           //输入框变红
           $("#newPassword").parent().parent().addClass("has-error");
           //输入框下面显示红色提示信息
           $("#helpBlock_update_inputPassword").text("输入6位的数字组合");
       } else {
           $.ajax({
               url: "/hrms/main/updatePassword",
               type: "PUT",
               data: $(".update_password_form").serialize(),
               success: function (result) {
                   if (result.code == 100) {
                       alert("密码更改成功！");
                       $(".password-update-modal").modal("hide");
                       window.location.href="/hrms/login";
                   } else {
                       alert(result.extendInfo.password_update_error);
                   }
               }
           });

       }
   });


</script>
</body>
</html>
