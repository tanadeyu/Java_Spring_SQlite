# BookStore - 商品販売データ分析システム

書籍店舗の商品販売データを収集・分析し、売上状況を可視化するWebアプリケーション。

## 概要

小売業の店舗管理者や販売データの分析担当者を対象とした、商品販売データの管理・分析システム。ダッシュボードでの売上可視化、商品・顧客管理、販売データの登録・閲覧機能を提供します。

## 主な機能

| 機能 | 説明 |
|------|------|
| 📊 **ダッシュボード** | 売上ランキング、カテゴリ別集計、顧客別分析を一括表示 |
| 📚 **商品管理** | 商品マスタの閲覧（在庫管理対応） |
| 👥 **顧客管理** | 顧客マスタの閲覧 |
| 💰 **販売管理** | 販売データの登録・閲覧（ヘッダーー明細構造） |

## 技術スタック

| 層 | 技術 | バージョン |
|----|------|-----------|
| 言語 | Java | 21 |
| フレームワーク | Spring Boot | 3.3.4 |
| ORM | Spring Data JPA | - |
| データベース | SQLite | 3.46.0.0 |
| テンプレートエンジン | Thymeleaf | - |
| ビルドツール | Maven | - |

## データベース設計

### ER図

```
customers (顧客) ──1:N── sale_headers (販売ヘッダー) ──1:N── sales (販売明細) ──N:1── products (商品)
                                                                       │
                                                                       N:N
                                                                       │
                                                                  categories (カテゴリ)
```

### 主要テーブル

- **customers**: 顧客マスタ（氏名、メールアドレス、会員番号）
- **products**: 商品マスタ（商品名、説明、価格、在庫）
- **categories**: カテゴリマスタ
- **sale_headers**: 販売ヘッダー（顧客、販売日、合計金額）
- **sales**: 販売明細（商品、数量、販売単価）

## 導入方法

### 前提条件

- Java 21+
- Maven 3.6+

### 手順

#### コマンドラインから実行

```bash
# リポジトリのクローン
git clone <repository-url>
cd Java_Spring_SQlite_proj/bookstore

# アプリケーションの実行
./mvnw spring-boot:run

# またはWindowsの場合
mvnw.cmd spring-boot:run
```

#### Eclipseで開発する場合

1. **リポジトリのクローン**
   ```bash
   git clone <repository-url>
   ```

2. **Eclipseにインポート**
   - File → Import → Maven → Existing Maven Projects
   - `Java_Spring_SQlite_proj/bookstore` ディレクトリを選択
   - Finish

3. **アプリケーションの実行**
   - Package Explorer で `BookstoreApplication.java` を右クリック
   - Run As → Spring Boot App

起動後、ブラウザで `http://localhost:8080` にアクセスしてください。

## データ初期化

アプリケーション起動時に、以下のサンプルデータが自動生成されます：

- カテゴリ：5種類
- 商品：10件
- 顧客：5件
- 販売データ：15件

## URL設計

| パス | 説明 |
|------|------|
| `/` | ダッシュボード |
| `/products` | 商品一覧 |
| `/customers` | 顧客一覧 |
| `/sales` | 販売一覧 |
| `/sales/new` | 販売登録 |

## デザイン

白黒モノクロームのミニマルデザインを採用。洗練されたプレミアムな雰囲気で、データが見やすく整理されています。

## 画面キャプチャ

### ダッシュボード
![ダッシュボード](docs/images/dashboard.png)

売上ランキング、カテゴリ別集計、顧客別分析を一覧表示

### 商品管理
![商品管理](docs/images/products.png)

商品マスタの一覧表示

### 顧客管理
![顧客管理](docs/images/customers.png)

顧客マスタの一覧表示

### 販売管理
![販売管理](docs/images/sales.png)

販売データの登録・閲覧（ヘッダーー明細構造）

#### 販売一覧
販売ヘッダーの一覧表示。詳細ボタンで明細をモーダル表示

#### 販売詳細
![販売詳細](docs/images/sales-detail.png)

明細行のモーダル表示（商品名、数量、単価、金額）

#### 販売登録
![販売登録](docs/images/sales-create.png)

最大5商品までの販売データ登録

## 開発者向け情報

### スクリーンショットの撮り方（Chrome DevTools）

ページ全体のスクリーンショットを撮る場合：

1. `F12` キーで開発者ツールを開く
2. `Ctrl + Shift + P` を押す
3. 「screenshot」と入力
4. 「Capture full size screenshot」を選択

### ビルド

```bash
./mvnw clean install
```

### テスト

```bash
./mvnw test
```

## ライセンス

MIT License

## 作者

BookStore Project
