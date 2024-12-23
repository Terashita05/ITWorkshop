import google.generativeai as genai

genai.configure(api_key="AIzaSyDuZcz-k-hWvKRaYy4pPe12IDSJA9pCpgQ")
model = genai.GenerativeModel("gemini-1.5-flash")
response = model.generate_content("今日の天気は？")
print(response.text)