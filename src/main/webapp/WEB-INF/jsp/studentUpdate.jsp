<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Update Page</title>
</head>
<body>
<div class="modal fade stu-update-modal" tabindex="-1" role="dialog" aria-labelledby="stu-update-modal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">学生更改</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal update_stu_form">
                    <div class="form-group">
                        <label  for="update_static_stuName" class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-8">
                            <p class="form-control-static" id="update_static_stuName"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="update_stuEmail" class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-8">
                            <input type="email" name="stuEmail" class="form-control" id="update_stuEmail">
                            <span id="helpBlock_update_inputEmail" class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">性别</label>
                        <div class="col-sm-8">
                            <label class="radio-inline">
                                <input type="radio" name="stuGender" id="update_stuGender1" value="M"> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="stuGender" id="update_stuGender2" value="F"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="update_teacher" class="col-sm-2 control-label">教师</label>
                        <div class="col-sm-8">
                            <div class="checkbox">
                                <select class="form-control" name="teacherId" id="update_teacher">
                                    <%-- <option value="1">CEO</option>--%>
                                </select>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary stu_update_btn">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->




<script type="text/javascript">
    <!-- ==========================学生修改操作=================================== -->
    $(".stu_edit_btn").click(function () {
        //1 获取点击修改学生的id与name;
        var updateStuId = $(this).parent().parent().find("td:eq(0)").text();

        //2 根据id或name查询出对应学生信息进行回显；
        $.ajax({
            url:"/hrms/stu/getStuById/"+updateStuId,
            type:"GET",
            success:function (result) {
                if (result.code == 100){
                    var stu = result.extendInfo.student;
                    $("#update_static_stuName").text(stu.stuName);
                    $("#update_stuEmail").val(stu.stuEmail);
                    $(".stu-update-modal input[name=gender]").val([stu.gender]);
                    $("#update_teacher").val(stu.teacherId);
                }
            }

        });

        //2 教师回显列表；
        $.ajax({
            url:"/hrms/teach/getTeachName",
            type:"GET",
            success:function (result) {
                if (result.code == 100){
                    $.each(result.extendInfo.teacherList, function () {
                        var optEle = $("<option></option>").append(this.teachName).attr("value", this.teachId);
                        optEle.appendTo("#update_teacher");
                    });
                }
            }

        });

        $(".stu_update_btn").attr("updateStuId", updateStuId);
    });


    $(".stu_update_btn").click(function () {
        var updateStuId = $(this).attr("updateStuId");
        //4 进行修改，对修改的邮箱格式进行判断；
        var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
        var updateStuEamil = $("#update_stuEmail").val();
        if (!regEmail.test(updateStuEamil)){
            $("#update_stuEmail").parent().parent().removeClass("has-sucess");
            $("#update_stuEmail").parent().parent().addClass("has-error");
            $("#helpBlock_update_inputEmail").text("邮箱格式不正确！");
            return false;
        }else {
            $("#update_stuEmail").parent().parent().removeClass("has-error");
            $("#update_stuEmail").parent().parent().addClass("has-success");
            $("#helpBlock_update_inputEmail").text("");
        }

        //5 点击更新按钮，发送AJAX请求到后台进行保存。
        $.ajax({
            url:"/hrms/stu/updateStu/"+updateStuId,
            type:"PUT",
            data:$(".update_stu_form").serialize(),
            success:function (result) {
                if (result.code==100){
                    alert("学生更改成功！");
                    $(".stu-update-modal").modal("hide");
                    //跳转到当前页
                    var curPage = ${curPage};
                    window.location.href="/hrms/stu/getStuList?pageNo="+curPage;
                }else if (result.code==200) {
                    alert(result.extendInfo.stu_update_error);
                }else {
                    alert(result.msg);
                }
            }
        });

    });
</script>
</body>
</html>
