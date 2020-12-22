<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.hillert.model.LoginBean" %>
<%@ page import="java.util.List" %>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel='stylesheet'
          href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
    <%
        LoginBean bean = null;
        List<LoginBean> list = null;
    %>
    <%
        bean = (LoginBean) request.getSession().getAttribute("LoginUser");
        list = (List<LoginBean>) request.getSession().getAttribute("listUser");
    %>
</head>
<body style="margin-top: 6%">
<form name="welcome" action="#" method="post">
    <input type="hidden" name="stIdcard" id="stIdcard">
    <div class="container">
        <div class="alert alert-success" align="right">
            <strong>Welcome </strong>
            <%=bean.getLogUsername()%>
            <a
<%--                    href="javascript: document.gotoProfile.submit()"  --%>
               onclick="gotoProfile('<%=bean.getLogUsername()%>')"><span
                    class="glyphicon glyphicon-cloud-upload "> </span></a>
            <a href="javascript: document.logoutForm.submit()"
               class="btn btn-danger">Logout</a>
        </div>

        <table class="table table-bordered">
            <tr>
                <th class="text-center">ลำดับ</th>
                <th class="text-center">username</th>
                <th class="text-center">password</th>
                <th class="text-center">สถานะ</th>
                <th class="text-center">รายละเอียด</th>
            </tr>
            <%
                for (int i = 0; i < list.size(); i++) {
            %>
            <tr class="text-center">
                <td><%=i + 1%>
                </td>
                <td><%=list.get(i).getLogUsername()%>
                </td>
                <td><%=list.get(i).getLogPassword()%>
                </td>
                <td><%=list.get(i).getLogStatus()%>
                </td>
                <td>
                    <a href="javascript: document.insertForm.submit()"><span
                            class="glyphicon glyphicon-plus"> </span></a>

                    <span onclick="gotoUpdate('<%=list.get(i).getLogUsername()%>'); document.getElementById('id01')"
                          title="Update" class="glyphicon glyphicon-edit">&times;
						 </span>

                    <span
                    <%--							class="glyphicon glyphicon-search"> </span></a> --%>
                    <a
                            onclick="gotoDetele('<%=list.get(i).getLogUsername()%>')" title="Detele"><span
                            class="glyphicon glyphicon-trash "> </span></a></td>

            </tr>
            <%
                }
            %>
        </table>

    </div>
    <script type="text/javascript">
        function gotoUpdate(filter) {
            document.getElementById("stIdcard").value = filter;
            document.welcome.action = "gotoUpdate";
            document.welcome.submit();
        }

        function gotoDetele(filter) {
            document.getElementById("stIdcard").value = filter;
            document.welcome.action = "delete";
            document.welcome.submit();
        }
        function gotoProfile(filter) {
            document.getElementById("stIdcard").value = filter;
            document.welcome.action = "gotoProfile";
            document.welcome.submit();
        }

        // Get the modal
        var modal = document.getElementById('id01');

        // When the user clicks anywhere outside of the modal, close it
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    </script>
</form>
<form name="logoutForm" action="logout" method="post" th:hidden="true"></form>
<form name="insertForm" action="gotoInsert" method="post" th:hidden="true"></form>
<%--<form name="gotoProfile" action="gotoProfile" method="post" th:hidden="true">--%>
<%--    <input type="hidden" name="stIdcard" value="<%=bean.getLogId()%>">--%>
<%--</form>--%>

<%--	<script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>--%>
<script type="text/javascript"
        src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</body>
</html>