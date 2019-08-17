<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="panel-group col-sm-2" id="hrms_sidebar_left" role="tablist" aria-multiselectable="true">
    <ul class="nav nav-pills nav-stacked stu_sidebar">
        <li role="presentation" class="active">
            <a href="#" data-toggle="collapse" data-target="#collapse_stu">
                <span class="glyphicon glyphicon-user" aria-hidden="true">学生管理</span>
            </a>
            <ul class="nav nav-pills nav-stacked" id="collapse_stu">
                <li role="presentation"><a href="#" class="stu_info">学生信息</a></li>
                <li role="presentation"><a href="#" role="button" class="stu_add_btn" data-toggle="modal" data-target=".stu-add-modal">学生新增</a></li>
                <li role="presentation"><a href="#" class="stu_clearall_btn">学生清零</a></li>
            </ul>
        </li>
    </ul>
    <ul class="nav nav-pills nav-stacked teach_sidebar">
        <li role="presentation" class="active">
            <a href="#"  data-toggle="collapse" data-target="#collapse_teach">
                <span class="glyphicon glyphicon-cloud" aria-hidden="true">教师管理</span>
            </a>
            <ul class="nav nav-pills nav-stacked" id="collapse_teach">
                <li role="presentation"><a href="#" class="teach_info">教师信息</a></li>
                <li role="presentation"><a href="#" class="teach_add_btn" data-toggle="modal" data-target=".teach-add-modal">教师新增</a></li>
                <li role="presentation"><a href="#" class="teach_clearall_btn">教师清零</a></li>
            </ul>
        </li>
    </ul>

</div><!-- /.panel-group，#hrms_sidebar_left -->

<script type="text/javascript">
    //跳转到学生页面
    $(".stu_info").click(function () {
        $(this).attr("href", "/hrms/stu/getStuList");
    });
    //跳转到教师页面
    $(".teach_info").click(function () {
        $(this).attr("href", "/hrms/teach/getTeachList");
    });
    //学生清零这个功能暂未实现
    $(".stu_clearall_btn").click(function () {
        alert("对不起，您暂无权限进行操作！请先获取权限");
    });
    //教师清零这个功能暂未实现
    $(".teach_clearall_btn").click(function () {
        alert("对不起，您暂无权限进行操作！请先获取权限");
    });
</script>
</body>
</html>
