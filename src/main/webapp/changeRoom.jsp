<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="murach.model.DetailSchedule" %>
<%@ page import="mysql.DAO.DetailScheduleDAO" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
<style>
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
	<% int maTKB = Integer.parseInt(request.getParameter("maTKB"));
		DetailScheduleDAO dtSDAO = new DetailScheduleDAO();
		DetailSchedule dtS = dtSDAO.getScheduleByID(maTKB);
	%>
	
	
		
		
		

	    <table>
	        <thead>
	            <tr>
	                <th>Tên học phần</th>
	                <th>Nhóm học phần</th>
	                <th>Thứ</th>
	                <th>Giờ vào</th>
	                <th>Giờ ra</th>
	                <th>Phòng</th>
	            </tr>
	        </thead>
	        <tbody>
	            <!-- Sử dụng vòng lặp trong JSP để hiển thị thông tin từ cơ sở dữ liệu -->
	            <tr>
	            	<td>${dtS.tenHP}</td>
	            	<td>${dtS.maNHP}</td>
	                <td><input type="text" name="thu" value="${dtS.thu}"></td>
	                <td><input type="text" name="gioVao" value="${dtS.gioVao}"></td>
	                <td><input type="text" name="gioRa" value="${dtS.gioRa}"></td>
	                <td><input type="text" name="phong" value="${dtS.phong}"></td>
	                <td><input type="hidden" name="message1"></td>
	                <td>${message1}</td>
	            </tr>
	            <!-- ... Các dòng tiếp theo -->
	        </tbody>
	    </table>
	<form action="/Hello/change-room" method="post">
	    <button type="submit" value="Cập nhật"></button>
	</form>
	<form action="Schedule.jsp" method="post">
			<button type="submit">Quay lại</button>
	</form>

</body>
</html>