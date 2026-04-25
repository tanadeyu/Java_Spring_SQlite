✅ ① 途中の思考（Thinking…）をカットする方法
Ollama の CLI では 思考過程（Thinking）を非表示にする唯一の方法はこれ。

Thinking を隠す
コード
ollama run qwen3.5:9b --hidethinking
対話モード中に常に隠す
コード
/set nothink
※ /set default は存在しないが、/set nothink は有効。

✅ ② いままでの重要コマンドまとめ（最短版）
🔍 モデル一覧を確認
コード
ollama list
🗑️ モデル削除
コード
ollama rm qwen3.5:9b
🚀 モデルを指定して実行
コード
ollama run qwen3.5:9b
🚀 Thinking を隠して実行
コード
ollama run qwen3.5:9b --hidethinking
🔚 対話モードを終了
コード
/bye
または
Ctrl + D

⛔ ダウンロード中断
コード
Ctrl + C