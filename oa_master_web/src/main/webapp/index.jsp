<%--
  Created by IntelliJ IDEA.
  User: Echo
  Date: 2020/10/3
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <!-- 也就是取出部署的应用程序名或者是当前的项目名称

    比如我的项目名称是demo1在浏览器中输入为http://localhost:8080/demo1
    /a.jsp $//{pageContext.request.contextPath}或<//%=request.getContextPath()%>取出来的就是/demo1,
    而"/"代表的含义就是http://localhost:8080-->
    <a href="${pageContext.request.contextPath}/product/findAll.do">查询所有商品信息</a>
</body>
</html>
