package servlet;

import java.io.IOException;

import dao.VisitRecordDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        // データベースから該当の訪問記録を削除
        VisitRecordDAO dao = new VisitRecordDAO();
        dao.deleteMutter(id);

        // メインページへリダイレクト
        response.sendRedirect("Main");
    }

}
