<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="styles/example4.css">
</head>
<body>
<h1><img src="images/example4.jpg" style ="width: 1492px; height: 300px;"></h1>
<h2 >GIẢNG VIÊN KHOA TIN HỌC - ĐẠI HỌC SƯ PHẠM - ĐHĐN </h2>
    <div class="container" id="container">
        
        <div class="form-container sign-in-container">
            <form action="/Hello/view-schedule" method="get">
                <h1>ĐĂNG NHẬP</h1>

                <input type="text" name="maGV" placeholder="Tên đăng nhập" />
                <input type="password" placeholder= "Mật khẩu" />
                
                <a href="#">Quên mật khẩu?</a>
                <button>ĐĂNG NHẬP</button>
            </form>
        </div>
</body>
</html>