package murach.com;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import murach.model.DetailSchedule;
import mysql.DAO.DetailScheduleDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class ViewDayOffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dayOff = request.getParameter("dayOff");
		String maGV =  request.getParameter("maGV");
		DetailScheduleDAO dtSchedule = new DetailScheduleDAO();
		HttpSession session = request.getSession();
		

		
		session.setAttribute("maGV", maGV);
		session.setAttribute("dayOff",dayOff);
		try {
			//Lich giang day cua GV
			List<DetailSchedule> listOff = dtSchedule.showDayOff(maGV,dayOff);
			session.setAttribute("listOff", listOff);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("Off.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
