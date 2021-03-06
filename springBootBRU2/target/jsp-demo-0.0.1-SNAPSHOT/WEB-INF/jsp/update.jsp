<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.hillert.model.*" %>
<%@ page import="java.util.List" %>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel='stylesheet'
          href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
    <%
        LoginBean bean = null;
        StudentBean stBean = null;
        String result = "";
    %>
    <%
        bean = (LoginBean) request.getSession().getAttribute("LoginUser");
        stBean = (StudentBean) request.getAttribute("resultBean");
        result = (String) request.getAttribute("messesUpdate");
    %>
</head>
<body style="margin-top: 5%">

<form name="updateForm" action="update-data" method="post">

    <div class="container">
        <div class="alert alert-success" align="right">
            <strong>Welcome </strong>
            <%=bean.getLogUsername()%>
            <a href="javascript: document.logoutForm.submit()"
               class="btn btn-danger">Logout</a>
        </div>
            <%
				if (result.equals("S")) {
			%>
        <div class="alert alert-success">
            <strong>Success!</strong> Insert Success..
        </div>
            <%
				} else if (result.equals("F")) {
			%>
        <div class="alert alert-danger">
            <strong>Danger!</strong> Insert Fail !..
        </div>
            <%
				}
			%>
<%--        <div id="id01" class="modal">--%>
            <div class="imgcontainer">
                <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
<%--                <img src="img_avatar2.png" alt="Avatar" class="avatar">--%>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">ID Card</label> <input type="text"
                                                                       class="form-control" name="stIdcard" readonly=""
                                                                       value="<%=stBean.getStIdcard()%>">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">FristName</label> <input type="text"
                                                                         class="form-control" name="stFname"
                                                                         value="<%=stBean.getStFname()%>">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">LastName</label> <input type="text"
                                                                        class="form-control" name="stLname"
                                                                        value="<%=stBean.getStLname()%>">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">Phone</label> <input type="text"
                                                                     class="form-control" name="stPhone"
                                                                     value="<%=stBean.getStPhone()%>">
            </div>
            <button type="submit" class="btn btn-success">Submit</button>
            <a type="button" class="btn btn-danger"
               href="javascript: document.backForm.submit()"> Back</a>

<%--        </div>--%>
</form>
<form name="backForm" action="gotoWelcome" method="post"
      th:hidden="true"></form>
<form name="logoutForm" action="logout" method="post" th:hidden="true"></form>



<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.0.min.js"></script>
<script type="text/javascript"
        src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</body>
</html>