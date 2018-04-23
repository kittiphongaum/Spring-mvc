<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.hillert.model.LoginBean"%>
<%@ page import="java.util.List"%>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel='stylesheet'
	href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
<%
	LoginBean bean = null;
	String result = "";
%>
<%
	bean = (LoginBean) request.getSession().getAttribute("LoginUser");
	result = (String) request.getAttribute("messes");
%>
</head>
<body style="margin-top: 7%">
	<form name="myform" action="insert	" method="post">
		<div class="container">
			 <!--   <div class="alert alert-success" align="right">
				<strong>Welcome </strong>
				<%//=bean.getLogUsername()%>
				<a href="javascript: document.logoutForm.submit()"
					class="btn btn-danger">Logout</a>
			</div>-->
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
			<div class="form-group">
				<label for="exampleInputEmail1">ID Card</label> <input type="text"
					class="form-control" name="stIdcard">
			</div>
			<div class="form-group">
			<input type="hidden" name="length" value="8">
				<label for="exampleInputEmail1">Password</label> <input name="password" type="text"
					class="form-control" >&nbsp;
					<td><input type="button" class="btn btn-success" value="Generate" onClick="generate();" tabindex="2">
			    </td>
			</div>
			
			<div class="form-group">
				<label for="exampleInputEmail1">FristName</label> <input type="text"
					class="form-control" name="stFname">
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">LastName</label> <input type="text"
					class="form-control" name="stLname">
			</div>
			
			<div class="form-group">
				<label for="exampleInputEmail1">Phone</label> <input type="text"
					class="form-control" name="stPhone">
			</div>
			<div class="form-group">
				<select name="roleId" class="form-control">
					<option value="1">ADMIN</option>
					<option value="2">USER</option>
				</select>
			</div>
			<button type="submit" class="btn btn-success" onclick="myFunction()">Submit</button>
			<button type="reset" class="btn btn-danger">Reset</button>
			<a type="button" class="btn btn-danger"
				href="javascript: document.backForm.submit()"> Back</a>

		</div>
		
		
	</form>
	
	<script>function randomPassword(length) {
	    var chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOP1234567890";
	    var pass = "";
	    for (var x = 0; x < length; x++) {
	        var i = Math.floor(Math.random() * chars.length);
	        pass += chars.charAt(i);
	    }
	    return pass;
	}

	function generate() {
	    myform.password.value = randomPassword(myform.length.value);
	}
	function myFunction() {
        alert("Welcome To Web");
    }

	
	</script>
	<form name="backForm" action="/" method="post"
		th:hidden="true"></form>
	<form name="logoutForm" action="logout" method="post" th:hidden="true"></form>


	<script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
	<script type="text/javascript"
		src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</body>
</html>