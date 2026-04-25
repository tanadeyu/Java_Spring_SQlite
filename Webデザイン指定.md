# 白黒シックなWEBデザイン指定

## 日本語プロンプト

```
モノクローム・ミニマルデザインのWEBサイトを作成してください。
色は白、黒、グレースケールのみ使用。シンプルで洗練された雰囲気で。
```

---

## 詳細プロンプト（日本語）

```
【デザインスタイル】
- カラースキーム: 白黒モノクローム（#FFFFFF, #000000, グレースケール）
- タイポグラフィ: サンセリフ系、見出しは太字、十分な余白
- レイアウト: グリッドベース、ミニマル、整然とした配置
- コントラスト: 高コントラストで明瞭な視覚階層
- アクセント: 黒のボーダー、影、ホバーエフェクトのみ

【雰囲気】
- プレミアム、高級感
- 現代的、洗練された
- クリーン、プロフェッショナル
```

---

## 英語プロンプト（画像生成AI向け）

```
Minimalist black and white web design, monochromatic color scheme, clean layout with generous white space, premium and sophisticated aesthetic, sharp typography, high contrast, modern luxury style, UI/UX interface
```

---

## CSS実装例

### CSS変数
```css
:root {
  --bg-primary: #FFFFFF;
  --bg-secondary: #F5F5F5;
  --text-primary: #000000;
  --text-secondary: #666666;
  --border: #E0E0E0;
  --accent: #000000;
}
```

### ベーススタイル
```css
body {
  font-family: 'Helvetica Neue', Arial, sans-serif;
  background: var(--bg-primary);
  color: var(--text-primary);
  line-height: 1.6;
}
```

### ボタン
```css
.btn-primary {
  background: var(--accent);
  color: var(--bg-primary);
  border: 1px solid var(--accent);
  padding: 12px 24px;
  transition: all 0.3s ease;
}

.btn-primary:hover {
  background: var(--bg-primary);
  color: var(--accent);
}
```

### カード
```css
.card {
  background: var(--bg-primary);
  border: 1px solid var(--border);
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.card:hover {
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.2);
}
```

---

## カラーパレット

| 用途 | カラーコード | HEX |
|------|-------------|-----|
| 背景（プライマリ） | 白 | #FFFFFF |
| 背景（セカンダリ） | ライトグレー | #F5F5F5 |
| テキスト（プライマリ） | 黒 | #000000 |
| テキスト（セカンダリ） | ダークグレー | #666666 |
| ボーダー | グレー | #E0E0E0 |
| アクセント | 黒 | #000000 |

---

## 適用例

- ポートフォリオサイト
- 高級ブランドサイト
- 企業コーポレートサイト
- ライフスタイルブログ
- プロダクトランディングページ
