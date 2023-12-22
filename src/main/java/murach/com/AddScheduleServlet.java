package murach.com;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mysql.DAO.DetailScheduleDAO;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class AddScheduleServlet
 */
public class AddScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		
		doPost(request,response);
	  }
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String dayOff = request.getParameter("dayOff");
		String maGV = request.getParameter("maGV");
		DetailScheduleDAO dtSchedule = new DetailScheduleDAO();
		HttpSession session = request.getSession();
		session.setAttribute("maGV", maGV);
		int rowUpdate;
		String message = "";
				
		try {
			rowUpdate = dtSchedule.update_StatusSchedule(maGV, dayOff);
			if(rowUpdate>0) {
				message = "Đăng ký nghỉ thành công, bạn nhớ đăng ký lịch dạy bù nhé";
			}
			else {
				message = "Đăng ký nghỉ thành công";
			}
			session.setAttribute("message", message);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("Off.jsp").forward(request, response);
		
	}

}
