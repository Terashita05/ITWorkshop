package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.GetMutterListLogic;
import model.Mutter;
import model.PostMutterLogic;
import model.User;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//つぶやきリストを取得して、リクエストスコープに保存
		GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
		List<Mutter>mutterList = getMutterListLogic.execute();
		request.setAttribute("mutterList",  mutterList);
		
		
		//ログインしているか確認するため、セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		
		if(loginUser == null) {//ログインしていない場合
			//リダイレクト
			response.sendRedirect("index.jsp");
		}else {//ログイン済みの場合
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String visit_date = request.getParameter("visit_date");
		String clientName = request.getParameter("clientName");
		String text = request.getParameter("text");
		String address = request.getParameter("address");
		String search = request.getParameter("search");
		//入力チェック
		if(text != null && text.length() != 0){
			//アプリケーションスコープに保存されたつぶやきリストを取得
//			ServletContext application = this.getServletContext();
//			List<Mutter> mutterList = (List<Mutter>)application.getAttribute("mutterList");
			
			//セッションスコープに保存されたユーザー情報を取得
			HttpSession session = request.getSession();
			User loginUser = (User)session.getAttribute("loginUser");
			
			//つぶやきを作成してつぶやきリストに追加
			Mutter mutter = new Mutter(visit_date, clientName, loginUser.getName(), text, address);
			
			PostMutterLogic postMutterLogic = new PostMutterLogic();
			postMutterLogic.execute(mutter);
		}else {
			if(search == null)
			{
				//エラーメッセージをリクエストスコープに保存
				request.setAttribute("errorMsg", "つぶやきが入力されていません");
			}
		}
		
		//つぶやきリストを取得して、リクエストスコープに保存
		GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
		List<Mutter>mutterList;
		if(search != null)
		{
			mutterList = getMutterListLogic.execute(search);
			request.setAttribute("search", search);
		}else {
			mutterList = getMutterListLogic.execute();
		}
		request.setAttribute("mutterList", mutterList);
		
		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
				
	}

}
