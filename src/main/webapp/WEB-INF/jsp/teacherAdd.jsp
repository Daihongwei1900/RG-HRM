<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>教师新增页面</title>
</head>
<body>
<div class="modal fade teach-add-modal" tabindex="-1" role="dialog" aria-labelledby="teach-add-modal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">教师新增</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal add_teach_form">
                    <div class="form-group">
                        <label for="add_teachId" class="col-sm-2 control-label">教师编号</label>
                        <div class="col-sm-8">
                            <input type="text" name="teachId" class="form-control" id="add_teachId" placeholder="1">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_teachName" class="col-sm-2 control-label">教师名称</label>
                        <div class="col-sm-8">
                            <input type="text" name="teachName" class="form-control" id="add_teachName" placeholder="人事部">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add_teachTitle" class="col-sm-2 control-label">教师职称</label>
                        <div class="col-sm-8">
                            <input type="text" name="teachTitle" class="form-control" id="add_teachTitle" placeholder="XXX">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary teach_save_btn">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script type="text/javascript">
    <!-- ==========================教师新增操作=================================== -->
    // 为简单操作，省去了输入名称的验证、错误信息提示等操作
    //1 点击教师新增按钮，弹出模态框；
    //2 输入新增教师信息，点击保存按钮，发送AJAX请求到后台进行保存；
    //3 保存成功跳转最后一页
    $(".teach_add_btn").click(function () {
        $('.teach-add-modal').modal({
            backdrop:static,
            keyboard:true
        })

    });

    $(".teach_save_btn").click(function () {
        var teachId = $("#add_teachId").val();
        var teachName = $("#add_teachName").val();
        var teachTitle = $("#add_teachTitle").val();
        //验证省略...
        $.ajax({
            url:"/hrms/teach/addTeach",
            type:"PUT",
            data:$(".add_teach_form").serialize(),
            success:function (result) {
                if(result.code == 100){
                    alert("新增成功");
                    $('.teach-add-modal').modal("hide");
                    $.ajax({
                        url:"/hrms/teach/getTotalPages",
                        type:"GET",
                        success:function (result) {
                            if (result.code == 100){
                                var totalPage = result.extendInfo.totalPages;
                                window.location.href="/hrms/teach/getTeachList?pageNo="+totalPage;
                            }
                        }
                    });
                }else {
                    alert(result.extendInfo.add_teach_error);
                }
            }
        });



    });



</script>
</body>
</html>
