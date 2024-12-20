package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GeminiAPI")
public class GeminiAPI extends HttpServlet {

    // Pythonスクリプトの相対パス
//    private static final String PYTHON_SCRIPT_PATH = "../gemini_api.py";  // サーブレットから見た相対パスで指定
//    // Python実行ファイルが配置されている場所の相対パス
//    private static final String PYTHON_EXECUTABLE_PATH = "../Python/Python313/python.exe";  // Python実行ファイルへの相対パス

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ユーザー入力を取得
        String question = request.getParameter("question");
        if (question == null || question.trim().isEmpty()) {
            response.getWriter().println("Error: 質問が入力されていません。");
            return;
        }

        // サーブレットの作業ディレクトリを基準に相対パスで設定
//        String currentDirectory = getServletContext().getRealPath("/");  // サーブレットの実行ディレクトリ（VisitLoggerフォルダ）
//        String pythonScriptPath = currentDirectory + PYTHON_SCRIPT_PATH;
//        String pythonExecutablePath = currentDirectory + PYTHON_EXECUTABLE_PATH;
        String pythonScriptPath = "C:\\Users\\8Java13\\Desktop\\サーブレット_JSP\\VisitLogger\\gemini_api.py";
        String pythonExecutablePath = "C:\\Users\\8Java13\\AppData\\Local\\Programs\\Python\\Python313\\python.exe";
        // Pythonスクリプトの実行
        ProcessBuilder processBuilder = new ProcessBuilder(pythonExecutablePath, pythonScriptPath, question);
       // processBuilder.directory(new java.io.File(currentDirectory));  // サーブレットの作業ディレクトリを指定
        processBuilder.redirectErrorStream(true);  // エラーストリームも標準出力にリダイレクト

        try {
            // Pythonスクリプトを実行
            Process process = processBuilder.start();
            process.waitFor(60, TimeUnit.SECONDS);  // タイムアウトの設定（60秒）

            // Pythonスクリプトの標準出力を取得
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // レスポンスを取得
            String generatedText = output.toString().trim();

            // 生成されたテキストをJSPに渡す
            request.setAttribute("response", generatedText);
            request.getRequestDispatcher("/WEB-INF/jsp/jeminiAPI.jsp").forward(request, response);

        } catch (Exception e) {
            response.getWriter().println("Error: Pythonスクリプトの実行中に問題が発生しました。詳細: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
