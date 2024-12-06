package servlet;
import java.io.IOException;

import dao.MuttersDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Mutter;

@WebServlet("/Update")
public class Update extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String clientName = request.getParameter("clientName");
        String visitDate = request.getParameter("visitDate");
        String address = request.getParameter("address");
        String visitNotes = request.getParameter("visitNotes");

        // データベースを更新
        MuttersDAO dao = new MuttersDAO();
        dao.updateMutter(new Mutter(id, clientName, visitDate, address, visitNotes));

        // 更新後にメインページへリダイレクト
        response.sendRedirect("Main");
    }
}
