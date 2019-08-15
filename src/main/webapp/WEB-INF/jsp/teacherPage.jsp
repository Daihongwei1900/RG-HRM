<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>教师管理页面</title>
</head>
<body>
<div class="hrms_teach_container">
    <!-- 导航栏-->
    <%@ include file="./commom/head.jsp"%>


    <!-- 中间部分（左侧栏+表格内容） -->
    <div class="hrms_teach_body">
        <!-- 左侧栏 -->
        <%@ include file="./commom/leftsidebar.jsp"%>

        <!-- 教师表格内容 -->
        <div class="teach_info col-sm-10">
            <div class="panel panel-success">
                <!-- 路径导航 -->
                <div class="panel-heading">
                    <ol class="breadcrumb">
                        <li><a href="#">教师管理</a></li>
                        <li class="active">教师信息</li>
                    </ol>
                </div>
                <!-- Table -->
                <table class="table table-bordered table-hover" id="teach_table">
                    <thead>
                        <th>教师编号</th>
                        <th>教师名称</th>
                        <th>教师职称</th>
                        <th>操作</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${teachers}" var="teach">
                            <tr>
                                <td>${teach.teachId}</td>
                                <td>${teach.teachName}</td>
                                <td>${teach.teachTitle}</td>
                                <td>
                                    <a href="#" role="button" class="btn btn-primary teach_edit_btn" data-toggle="modal" data-target=".teach-update-modal">编辑</a>
                                    <a href="#" role="button" class="btn btn-danger teach_delete_btn">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <div class="panel-body">
                    <div class="table_items">
                        当前第<span class="badge">${curPageNo}</span>页，共有<span class="badge">${totalPages}</span>页，总记录数<span class="badge">${totalItems}</span>条。
                    </div>
                    <nav aria-label="Page navigation" class="pull-right">
                        <ul class="pagination">
                            <li><a href="/hrms/teach/getTeachList?pageNo=1">首页</a></li>
                            <c:if test="${curPageNo==1}">
                                <li class="disabled">
                                    <a href="#" aria-label="Previous" class="prePage">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>
                            <c:if test="${curPageNo!=1}">
                                <li>
                                    <a href="#" aria-label="Previous" class="prePage">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>

                            <c:forEach begin="${curPage-2<1?1:(curPage-2)}" end="${(curPage+2)>totalPages?totalPages:(curPage+2)}" step="1" var="itemPage">
                                <c:if test="${curPageNo == itemPage}">
                                    <li class="active"><a href="/hrms/teach/getTeachList?pageNo=${itemPage}">${itemPage}</a></li>
                                </c:if>
                                <c:if test="${curPageNo != itemPage}">
                                    <li><a href="/hrms/teach/getTeachList?pageNo=${itemPage}">${itemPage}</a></li>
                                </c:if>
                            </c:forEach>

                            <c:if test="${curPageNo==totalPages}">
                                <li class="disabled" class="nextPage">
                                    <a href="#" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:if>
                            <c:if test="${curPageNo!=totalPages}">
                                <li>
                                    <a href="#" aria-label="Next" class="nextPage">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:if>
                            <li><a href="/hrms/teach/getTeachList?pageNo=${totalPages}">尾页</a></li>
                        </ul>
                    </nav>
                </div>
            </div><!-- /.panel panel-success -->
        </div><!-- /.teach_info -->
    </div><!-- /.hrms_teach_body -->

    <%@ include file="teacherAdd.jsp"%>
    <%@ include file="teacherUpdate.jsp"%>
    <%@ include file="passwordUpdate.jsp"%>
    <!-- 尾部-->
    <%@ include file="./commom/foot.jsp"%>

</div><!-- /.hrms_teach_container -->

<script type="text/javascript">
    var curPageNo = ${curPageNo};
    var totalPages = ${totalPages};
    //上一页
    $(".prePage").click(function () {
         if (curPageNo > 1){
             var pageNo = curPageNo - 1;
             $(this).attr("href", "/hrms/teach/getTeachList?pageNo="+pageNo);
         }
    });
    //下一页
    $(".nextPage").click(function () {
        if (curPageNo < totalPages){
            var pageNo = curPageNo + 1;
            $(this).attr("href", "/hrms/teach/getTeachList?pageNo="+pageNo);
        }
    });


    <!-- ==========================教师删除操作=================================== -->
    $(".teach_delete_btn").click(function () {
        var delTeachId = $(this).parent().parent().find("td:eq(0)").text();//表示取父节点中的改行中的索引等于0
        var delTeachName = $(this).parent().parent().find("td:eq(1)").text();
        var curPageNo = ${curPageNo};
        if (confirm("确认删除【"+ delTeachName +"】的信息吗？")){
            $.ajax({
                url:"/hrms/teach/delTeach/"+delTeachId,
                type:"DELETE",
                success:function (result) {
                    if (result.code == 100){
                        alert("删除成功！");
                        window.location.href = "/hrms/teach/getTeachList?pageNo="+curPageNo;
                    }else {
                        alert(result.extendInfo.del_teach_error);
                    }
                }
            });
        }
    });
</script>
</body>
</html>
