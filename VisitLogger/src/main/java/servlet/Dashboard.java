package servlet;

import java.io.IOException;
import java.util.List;

import dao.DashboardDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import model.VisitRecord;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dashboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//ログインしているか確認するため、セッションスコープからユーザー情報を取得
				HttpSession session = request.getSession();
				User loginUser = (User)session.getAttribute("loginUser");
				
				if(loginUser == null) {//ログインしていない場合
					//リダイレクト
					response.sendRedirect("index.jsp");
				}else {//ログイン済みの場合
					//フォワード
					// DAOを使ってデータを取得
			        DashboardDAO dao = new DashboardDAO();
			        List<VisitRecord> nextVisits = dao.getNextVisitRecords(loginUser.getName());

			        // データをリクエストスコープにセット
			        request.setAttribute("nextVisits", nextVisits);

			        // JSPにフォワード
			        request.getRequestDispatcher("/WEB-INF/jsp/dashboard.jsp").forward(request, response);
					
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
					dispatcher.forward(request, response);
				}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
