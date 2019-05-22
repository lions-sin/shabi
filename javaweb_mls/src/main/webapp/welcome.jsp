<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="html/index.html">麦乐送.html</a><br>
    <a href="index.jsp">麦乐送.jsp</a><br>

    项目路径：<%=basePath%>
</body>
</html>
