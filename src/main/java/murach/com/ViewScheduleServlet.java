package murach.com;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mysql.DAO.DetailScheduleDAO;
import murach.model.DetailSchedule;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class ViewScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String instructor_id = request.getParameter("maGV");
		String action = request.getParameter("action");
		String url = "/Schedule.jsp";
		DetailScheduleDAO dtSchedule = new DetailScheduleDAO();
		HttpSession session = request.getSession();
		session.setAttribute("maGV", instructor_id);
		
		if(action == null) {
			action = "view";
		}
		
		if(action.equals("view")) {
			
		
		try {
			//Thong tin cua GV
			session.setAttribute("instructor", dtSchedule.showInfoInstructor(instructor_id));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		try {
			//Lich giang day cua GV
			List<DetailSchedule> listSchedule = dtSchedule.showSchedule(instructor_id);
			session.setAttribute("listSchedule", listSchedule);
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		url = "/Schedule.jsp";
		}
		else if (action.equals("edit")) {
			int maTKB = Integer.parseInt(request.getParameter("maTKB"));
			session.setAttribute("maTKB", maTKB);
			try {
				request.setAttribute("dtS", dtSchedule.getScheduleByID(maTKB));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			url = "/changeRoom.jsp";
		}
		
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		 // TODO Auto-generatedmethod stub 
		 	doPost(request, response); 
		}
	
}
