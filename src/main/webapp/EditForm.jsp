<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="murach.model.Country" %>
<%@ page import="murach.model.User" %>
<!DOCTYPE html>
<html>
<head>
<jsp:useBean id="a" class="mysql.DAO.PobDAO" scope="request"></jsp:useBean>
<meta charset="UTF-8">
<title>Edit Form</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
	table{
		width: 100%;
	}
	.onLeft{
		text-align: left;
	}
	.onRight{
		text-align: right;
	}
</style>
</head>
<body>
	<% User user = (User) request.getAttribute("user"); 
		String sex = "Male,Female,Other";
	
	%>
	<c:set var="sex" value="Male,Female,Other" ></c:set>
	<div class="container mb-3 mt-3">
	<h2>Information of User</h2>
	<form name="inforForm" method= "post" action="/Hello/userConfirm">	
		<input type="hidden" name="action" value="update">
		<div class="mb-3 mt-3">
			<label for="email">Email:</label>
			<input type="email" name="email" class="form-control" id="email" placeholder="Enter email" value="<c:out value="${user.email}"/>" required>
		</div>
		
		<div class="mb-3 mt-3">
			<label for="fName">First name:</label>
			<input type="text" name="firstName" class="form-control" id="fName" placeholder="Enter your first name" value="<c:out value="${user.firstName}"/>" required>
		</div>
		
		<div class="mb-3 mt-3">
			<label for="lName">Last name:</label>
			<input type="text" name="lastName" class="form-control" id="lName" placeholder="Enter your last name" value="${user.lastName}" required>
		</div>
		
		<div class="mb-3 mt-3">
			<label for="DOB">Day of birth:</label>
			<input type="date" name="DOB" class="input-group" id="DOB" value="${user.dob}" required>
		</div>
		
		<div class="mb-3">
			<label>Gender:</label>
			<c:forTokens items="${sex}" delims="," var="tmp">
				<div class="form-check">
				<c:if test="${user.gender.equals(tmp)}">
					<label class="form-check-label">
		        		<input class="form-check-input" type="radio" name="gender" value="${tmp}" checked>${tmp}
		      		</label>
				</c:if>
					<label class="form-check-label">
		        		<input class="form-check-input" type="radio" name="gender" value="${tmp}">${tmp}
		      		</label>
		      	</div>
			</c:forTokens>
			
 		</div>
 		
 		<div class="mb-3">
			<label>Available day:</label>
			<% ArrayList<String> days = new ArrayList<String>();
				  days.add("Thứ hai");
				  days.add("Thứ ba");
				  days.add("Thứ tư");
				  days.add("Thứ năm");
				  days.add("Thứ sáu");
				  days.add("Thứ bảy");
				  days.add("Chủ nhật");
				   
				  pageContext.setAttribute("myAvailDay",days);
			
			%>
			
			<c:forEach var="temAvailDay" items="${myAvailDay}">
				<div class="form-check">
		      		<label class="form-check-label">
		       			<input class="form-check-input" type="checkbox" name="availDay" value="${temAvailDay}"> ${temAvailDay}
		      		</label>
    			</div>
			</c:forEach>
 		</div>

		<div class="mb-3">
			<label>Place of birth:</label>
			<select class="form-select" name="pob">
			    <c:forEach var="temp" items="${a.listCountry}">
			    	<c:if test="${user.pob.equals(temp.name_country)}">
			    	<option value="${temp.name_country}" selected>${temp.name_country}</option>
			    	</c:if>
			    	<option value="${temp.name_country}">${temp.name_country}</option>
			    </c:forEach>
	   		</select> 
	   		
		</div>
		<table>
			<tr>
				<input type="hidden" name="action" value="update">
				<td class="onLeft"><a href="" class="btn btn-primary"><i style="font-size:24px" class="fa">&#xf177;</i></a></td>
				<td class="onRight"><button type="submit" name="buttonSubmit" class="btn btn-primary">Update</button></td>
			</tr>
		</table>
	</form>
	</div>

</body>
</html>