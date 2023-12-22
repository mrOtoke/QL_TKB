<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="murach.model.Instructor" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký nghỉ</title>
	<style>
		body {
            font-family: Arial, sans-serif;
            margin: 50px 20px;
            padding: 0;
        }
        

        nav {
            background-color: #333;
            overflow: hidden;
        }

        nav a {
            float: left;
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        nav a:hover {
            background-color: #ddd;
            color: black;
        }
        
        .infoInstructor {
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0;
        }
        
        ul{
        	list-style: none;
        	text-align: left;
        	padding: 0;
        }
        
        li{
        	margin-bottom: 10px;
        }
        
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

	</style>
	

</head>
<body>
		<form method="get" action="/Hello/view-dayOff">
			<input type="hidden" name="maGV" value="<%=(request.getParameter("maGV")!=null)?request.getParameter("maGV"):"" %>">
			<div>
				Ngày xin nghỉ: <input type="date" name="dayOff" required>
				Lý do nghỉ: <input type="text" placeholder="Nhập lý do xin nghỉ">
				<button type="submit">Xem</button>
			</div>
			<h2>Thông tin các lớp học phần ngày ${dayOff}</h2>
			
			<table>
		        <thead>
		            <tr>
		                <th>Thứ</th>
		                <th>Mã học phần</th>
		                <th>Nhóm học phần</th>
		                <th>Sĩ số</th>
		                <th>Tên học phần</th>
		                <th>Giờ vào</th>
		                <th>Giờ ra</th>
		                <th>Phòng</th>
		            </tr>
		        </thead>
		        <tbody>
		            <!-- Sử dụng vòng lặp trong JSP để hiển thị thông tin từ cơ sở dữ liệu -->
		            <c:forEach var="item" items="${listOff}">
		            <tr>
		                <td>${item.thu}</td>
		                <td>${item.maHP}</td>
		                <td>${item.maNHP}</td>
		                <td>${item.soLuong}</td>
		                <td>${item.tenHP}</td>
		                <td>${item.gioVao}</td>
		                <td>${item.gioRa}</td>
		                <td>${item.phong}</td>
		            </tr>
		            </c:forEach>
		            <!-- ... Các dòng tiếp theo -->
		        </tbody>
		    </table>
	</form>
	
	<form method="post" action="/Hello/add-schedule">
		<input type="hidden" name="maGV" value="${maGV}">
		<input type="hidden" name="dayOff" value="${dayOff}">
		<h2>${message}</h2>
		<button type="submit">Đăng ký nghỉ</button>
	</form>
	
	<form action="Schedule.jsp" method="post">
			<button type="submit">Quay lại</button>
	</form>
	

	
	

</body>
</html>