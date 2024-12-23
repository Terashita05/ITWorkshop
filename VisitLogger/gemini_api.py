import google.generativeai as genai
import sys
import logging
import os
sys.stdout.reconfigure(encoding='utf-8')

# 取得したAPIキーを設定
API_KEY = "AIzaSyDuZcz-k-hWvKRaYy4pPe12IDSJA9pCpgQ"  # ここに取得したAPIキーを入力

# モデルの指定
MODEL_NAME = "gemini-1.5-flash"  # 使用するGeminiモデル
logging.basicConfig(level=logging.WARNING)
def call_gemini_api(prompt):
    genai.configure(api_key=API_KEY)
    model = genai.GenerativeModel(MODEL_NAME)
    # response = model.generate_content(pronpt)
    response = model.generate_content(prompt)

    return response.text
    # try:
    #     # APIリクエストを送信
    #     response = genai.generate_text(
    #         model=MODEL_NAME,
    #         prompt=prompt,
    #         max_output_tokens=max_output_tokens
    #     )
    #     return response
    # except Exception as e:
    #     print(f"Error occurred: {e}")
    #     return None

# テストクエリ
if __name__ == "__main__":

    if len(sys.argv) < 2:
        print("Error: プロンプトが指定されていません。")
        sys.exit(1)

    prompt = sys.argv[1]
    result = call_gemini_api(prompt)
    print(result)