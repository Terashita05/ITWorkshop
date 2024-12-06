package servlet;

import java.io.IOException;

import dao.MuttersDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Mutter;

/**
 * Servlet implementation class Edit
 */

@WebServlet("/Edit")
public class Edit extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        // H2データベースから該当の訪問記録を取得
        MuttersDAO dao = new MuttersDAO();
        Mutter mutter = dao.findById(id);

        // 編集ページへフォワード
        request.setAttribute("mutter", mutter);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/edit.jsp");
        dispatcher.forward(request, response);
    }
}





