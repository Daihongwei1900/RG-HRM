<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教师更改页面</title>
</head>
<body>
<div class="modal fade teach-update-modal" tabindex="-1" role="dialog" aria-labelledby="teach-update-modal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">教师更改</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal update_teach_form">
                    <div class="form-group">
                        <label for="update_teachName" class="col-sm-2 control-label">教师名称</label>
                        <div class="col-sm-8">
                            <input type="text" name="teachName" class="form-control" id="update_teachName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="update_teachTitle" class="col-sm-2 control-label">教师职称</label>
                        <div class="col-sm-8">
                            <input type="text" name="teachTitle" class="form-control" id="update_teachTitle">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary teach_update_btn">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<script type="text/javascript">
    <!-- ==========================教师新增操作=================================== -->
    //1 点击编辑按钮，发送AJAX请求查询对应id的教师信息，进行回显；
    //2 进行修改，点击更新按钮发送AJAX请求，将更改后的信息保存到数据库中；
    //3 跳转到当前更改页；
    var edit_teachId =0;
    var edit_teachName = 0;
    var curPageNo = ${curPageNo};

    $(".teach_edit_btn").click(function () {
        edit_teachId = $(this).parent().parent().find("td:eq(0)").text();
        edit_teachName = $(this).parent().parent().find("td:eq(1)").text();
        alert("确认修改"+edit_teachName+"信息吗？");
        //查询对应teachId的教师信息
        $.ajax({
            url:"/hrms/teach/getTeachById/"+edit_teachId,
            type:"GET",
            success:function (result) {
                if (result.code == 100){
                    var teachData = result.extendInfo.teacher;
                    //回显
                    $("#update_teachName").val(teachData.teachName);
                    $("#update_teachTitle").val(teachData.teachTitle);
                }else {
                    alert(result.extendInfo.get_teach_error);
                }
            }
        });
    });

    $(".teach_update_btn").click(function () {
        $.ajax({
            url:"/hrms/teach/updateTeach/"+edit_teachId,
            type:"PUT",
            data:$(".update_teach_form").serialize(),
            success:function (result) {
                if(result.code == 100){
                    alert("更新成功！");
                    window.location.href = "/hrms/teach/getTeachList?pageNo="+curPageNo;
                } else if(result.code==200){
                    alert(result.extendInfo.update_teach_error);
                }else {
                    alert(result.msg);
                }
            }

        });
    });


</script>
</body>
</html>
