import google.generativeai as genai

# APIキーを直接設定
GOOGLE_API_KEY = ""

# APIキーを設定
genai.configure(api_key=GOOGLE_API_KEY)

def call_gemini_api(question):
    try:
        # "gemini-pro" モデルを使用してコンテンツを生成
        gemini_pro = genai.GenerativeModel("gemini-pro")
        response = gemini_pro.generate_content(question, timeout=120)
        return response.text  # 生成されたテキストを返す
    except Exception as e:
        return f"Error: {str(e)}"

if __name__ == "__main__":
    import sys
    if len(sys.argv) > 1:
        question = sys.argv[1]
        result = call_gemini_api(question)
        print(result)  # 結果を標準出力に出力
    else:
        print("Error: No question provided.")
