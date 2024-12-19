//package servlet;
//
//import java.io.IOException;
//import java.util.concurrent.TimeUnit;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import okhttp3.MediaType;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//@WebServlet("/HuggingFaceServlet")
package servlet;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@WebServlet("/HuggingFaceServlet")
public class HuggingFaceServlet extends HttpServlet {

	  private static final String API_URL = "https://api-inference.huggingface.co/models/rinna/japanese-gpt-neox-3.6b-instruction-sft";
    private static final String API_TOKEN = "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"; // Hugging Face APIトークン
//
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ユーザー入力を取得
        String question = request.getParameter("question");
        if (question == null || question.trim().isEmpty()) {
            response.getWriter().println("Error: 質問が入力されていません。");
            return;
        }

        // OkHttpクライアントの初期化
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        // リクエストボディの作成
        String prompt = "以下の質問に対して、30文字以内で簡潔かつ自然な日本語で答えてください。\n質問: " + question + "\n回答:";
        String jsonRequest = new Gson().toJson(new Input(prompt, 5)); // 生成トークン数を最大50に設定
        RequestBody body = RequestBody.create(jsonRequest, MediaType.get("application/json; charset=utf-8"));

        // HTTPリクエストの作成
        Request httpRequest = new Request.Builder()
                .url(API_URL)
                .addHeader("Authorization", "Bearer " + API_TOKEN)
                .post(body)
                .build();

        // APIレスポンスの処理
        try (Response httpResponse = client.newCall(httpRequest).execute()) {
            if (httpResponse.isSuccessful() && httpResponse.body() != null) {
                String jsonResponse = httpResponse.body().string();

                // APIレスポンスを解析
                String generatedText = extractGeneratedText(jsonResponse);

                // JSPに応答を渡す
                request.setAttribute("response", generatedText);
                request.getRequestDispatcher("/WEB-INF/jsp/jeminiAPI.jsp").forward(request, response);
            } else {
                // エラーレスポンス処理
                int errorCode = httpResponse.code();
                String errorMessage = "API Error: " + errorCode + " - " + (httpResponse.body() != null ? httpResponse.body().string() : "No additional details.");
                response.getWriter().println(errorMessage);
            }
        } catch (Exception e) {
            response.getWriter().println("Error: APIリクエストの処理中に問題が発生しました。詳細: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // レスポンスから生成テキストを抽出するメソッド
    private String extractGeneratedText(String jsonResponse) {
        try {
            JsonArray jsonArray = JsonParser.parseString(jsonResponse).getAsJsonArray();
            if (jsonArray.size() > 0) {
                JsonObject responseObject = jsonArray.get(0).getAsJsonObject();
                return responseObject.get("generated_text").getAsString();
            }
        } catch (Exception e) {
            System.err.println("Error parsing JSON response: " + e.getMessage());
        }
        return "Error: 生成されたテキストを解析できませんでした。";
    }

    // リクエストボディ用の内部クラス
    private static class Input {
        String inputs;
        int max_tokens;

        public Input(String inputs, int max_tokens) {
            this.inputs = inputs;
            this.max_tokens = max_tokens; // 生成トークン数を指定
        }
    }
}


//public class HuggingFaceServlet extends HttpServlet {
//
//    // Hugging Face APIのエンドポイントとトークン
//    private static final String API_URL = "https://api-inference.huggingface.co/models/rinna/japanese-gpt2-medium";
//    private static final String API_TOKEN = "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"; // Hugging Face APIトークン
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // ユーザーからの質問を取得
//        String question = request.getParameter("question");
//
//        // OkHttpクライアントを初期化
//        OkHttpClient client = new OkHttpClient.Builder()
//        		.connectTimeout(60, TimeUnit.SECONDS) // 接続タイムアウトを60秒に設定
//        	    .readTimeout(60, TimeUnit.SECONDS)    // 読み取りタイムアウトを60秒に設定
//        	    .build();
//
//        // リクエストのボディを作成
//        String jsonRequest = new Gson().toJson(new Input(question));
//        RequestBody body = RequestBody.create(jsonRequest, MediaType.get("application/json; charset=utf-8"));
//
//        // HTTPリクエストの作成
//        Request httpRequest = new Request.Builder()
//                .url(API_URL)
//                .addHeader("Authorization", "Bearer " + API_TOKEN)
//                .post(body)
//                .build();
//
//        // APIレスポンスを取得
//        try (Response httpResponse = client.newCall(httpRequest).execute()) {
//            if (httpResponse.isSuccessful()) {
//                String jsonResponse = httpResponse.body().string();
//                
//             // APIレスポンスを取得した後
//
//                // Gsonを使ってレスポンスをパース
//                JsonArray jsonArray = new Gson().fromJson(jsonResponse, JsonArray.class);
//
//                // レスポンスから「generated_text」を取得
//                String generatedText = "";
//                if (jsonArray.size() > 0) {
//                    JsonObject responseObject = jsonArray.get(0).getAsJsonObject();
//                    generatedText = responseObject.get("generated_text").getAsString();
//                }
//
//                // 「generated_text」をリクエスト属性にセット
//                request.setAttribute("response", generatedText);
//                
//                
//                
//                
//                request.setAttribute("response", jsonResponse);
//                request.getRequestDispatcher("/WEB-INF/jsp/jeminiAPI.jsp").forward(request, response);
//            } else {
//                response.getWriter().println("API Error: " + httpResponse.code());
//            }
//        }
//    }
//
//    // 内部クラス（入力用データ構造）
//    private static class Input {
//        String inputs;
//
//        public Input(String inputs) {
//            this.inputs = inputs;
//        }
//    }
//}


