<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学生管理页面</title>
</head>
<body>
<div class="hrms_container">
    <!-- 导航条 -->
    <%@ include file="./commom/head.jsp"%>

    <!-- 中间部分（包括左边栏和学生/教师表单显示部分） -->
    <div class="hrms_body" style="position:relative; top:-15px;">

        <!-- 左侧栏 -->
        <%@ include file="./commom/leftsidebar.jsp"%>

        <!-- 中间学生表格信息展示内容 -->
        <div class="stu_info col-sm-10">

            <div class="panel panel-success">
                <!-- 路径导航 -->
                <div class="panel-heading">
                    <ol class="breadcrumb">
                        <li><a href="#">学生管理</a></li>
                        <li class="active">学生信息</li>
                    </ol>
                </div>
                <!-- Table -->
                <table class="table table-bordered table-hover" id="stu_table">
                    <thead>
                    <th>学生编号</th>
                    <th>学生姓名</th>
                    <th>邮箱</th>
                    <th>性别</th>
                    <th>教师</th>
                    <th>操作</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${students}" var="stu">
                            <tr>
                                <td>${stu.stuId}</td>
                                <td>${stu.stuName}</td>
                                <td>${stu.stuEmail}</td>
                                <td>${stu.stuGender == "F"? "女": "男"}</td>
                                <td>${stu.teacher.teachName}</td>
                                <td>
                                    <a href="#" role="button" class="btn btn-primary stu_edit_btn" data-toggle="modal" data-target=".stu-update-modal">编辑</a>
                                    <a href="#" role="button" class="btn btn-danger stu_delete_btn">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <div class="panel-body">
                    <div class="table_items">
                        当前第<span class="badge">${curPage}</span>页，共有<span class="badge">${totalPages}</span>页，总记录数<span class="badge">${totalItems}</span>条。
                    </div>
                    <nav aria-label="Page navigation" class="pull-right">
                        <ul class="pagination">
                            <li><a href="/hrms/stu/getStuList?pageNo=1">首页</a></li>
                            <c:if test="${curPage==1}">
                                <li class="disabled">
                                    <a href="#" aria-label="Previous" class="prePage">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>
                            <c:if test="${curPage!=1}">
                                <li>
                                    <a href="#" aria-label="Previous" class="prePage">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>

                            <c:forEach begin="${curPage-2<1?1:(curPage-2)}" end="${(curPage+2)>totalPages?totalPages:(curPage+2)}" step="1" var="itemPage">
                                <c:if test="${curPage == itemPage}">
                                    <li class="active"><a href="/hrms/stu/getStuList?pageNo=${itemPage}">${itemPage}</a></li>
                                </c:if>
                                <c:if test="${curPage != itemPage}">
                                    <li><a href="/hrms/stu/getStuList?pageNo=${itemPage}">${itemPage}</a></li>
                                </c:if>
                            </c:forEach>

                            <c:if test="${curPage==totalPages}">
                                <li class="disabled" class="nextPage">
                                    <a href="#" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:if>
                            <c:if test="${curPage!=totalPages}">
                                <li>
                                    <a href="#" aria-label="Next" class="nextPage">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:if>
                            <li><a href="/hrms/stu/getStuList?pageNo=${totalPages}">尾页</a></li>
                        </ul>
                    </nav>
                </div>
            </div><!-- /.panel panel-success -->
        </div><!-- /.stu_info -->
        <!-- 尾部 -->
    </div><!-- /.hrms_body -->
    <%@ include file="./commom/foot.jsp"%>
</div><!-- /.container -->

<%@ include file="studentAdd.jsp"%>
<%@ include file="studentUpdate.jsp"%>


<script>
    $(function () {
        //上一页
        var curPage = ${curPage};
        var totalPages = ${totalPages};
        $(".prePage").click(function () {
            if (curPage > 1){
                var pageNo = curPage-1;
                $(this).attr("href", "/hrms/stu/getStuList?pageNo="+pageNo);
            }
        });
        //下一页
        $(".nextPage").click(function () {
            if (curPage < totalPages){
                var pageNo = curPage+1;
                $(this).attr("href", "/hrms/stu/getStuList?pageNo="+pageNo);
            }
        });
    })

    <!-- ==========================学生删除操作=================================== -->
    $(".stu_delete_btn").click(function () {
        var curPage = ${curPage};
        var delStuId = $(this).parent().parent().find("td:eq(0)").text();
        var delStuName = $(this).parent().parent().find("td:eq(1)").text();
        if (confirm("确认删除【" + delStuName+ "】的信息吗？")){
            $.ajax({
                url:"/hrms/stu/deleteStu/"+delStuId,
                type:"DELETE",
                success:function (result) {
                    if (result.code == 100){
                        alert("删除成功！");
                        window.location.href="/hrms/stu/getStuList?pageNo="+curPage;
                    }else {
                        alert(result.extendInfo.stu_del_error);
                    }
                }
            });
        }
    });


</script>
</body>
</html>
