<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="murach.model.Instructor" %>
<%@ page import="murach.model.DetailSchedule" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thời khóa biểu</title>
<style>

		body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 0;
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

	<% String maGV = request.getParameter("maGV"); %>
				
			<div class="infoInstructor">
				<ul>
					<li>Mã GV: ${instructor.getMaGV()}</li>
					<li>Tên GV: ${instructor.getTenGV()}</li>
					<li>Email: ${instructor.getEmail()}</li>
				</ul>
			</div>
		<h2>Thời Khóa Biểu</h2>

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
	                <th>Action</th>
	            </tr>
	        </thead>
	        
	        
	        <tbody>
	            <!-- Sử dụng vòng lặp trong JSP để hiển thị thông tin từ cơ sở dữ liệu -->
	            <c:forEach var="schedule" items="${listSchedule}">
	            
	            <tr>
	                <td>${schedule.thu}</td>
	                <td>${schedule.maHP}</td>
	                <td>${schedule.maNHP}</td>
	                <td>${schedule.soLuong}</td>
	                <td>${schedule.tenHP}</td>
	                <td>${schedule.gioVao}</td>
	                <td>${schedule.gioRa}</td>
	                <td>${schedule.phong}</td>
	            	<td>
	            	<form method="get" action="changeRoom.jsp">
	            	<input type="hidden" name="action" value="edit">
	            	<input type="hidden" name="maTKB" value="${schedule.maTKB}">
	            	<input type="submit" value="Sửa">
	            	</form>
	            	</td>
				</tr>
	            </c:forEach>
	            <!-- ... Các dòng tiếp theo -->
	        </tbody>
	    </table>
		<form action="Off.jsp" method="post">
			<input type="hidden" name="maGV" value="${instructor.getMaGV()}" >
			<button type="submit">Đăng ký nghỉ</button>
		</form>
		
		


</body>
</html>