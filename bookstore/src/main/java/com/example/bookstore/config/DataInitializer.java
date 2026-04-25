package com.example.bookstore.config;

import com.example.bookstore.entity.Category;
import com.example.bookstore.entity.Customer;
import com.example.bookstore.entity.Product;
import com.example.bookstore.entity.Sale;
import com.example.bookstore.entity.SaleHeader;
import com.example.bookstore.repository.CategoryRepository;
import com.example.bookstore.repository.CustomerRepository;
import com.example.bookstore.repository.ProductRepository;
import com.example.bookstore.repository.SaleHeaderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * データ初期化クラス
 *
 * アプリケーション起動時にサンプルデータを自動生成します。
 *
 * @author BookStore Project
 * @version 1.0
 */
@Component
public class DataInitializer implements CommandLineRunner {

	private final CategoryRepository categoryRepository;
	private final ProductRepository productRepository;
	private final CustomerRepository customerRepository;
	private final SaleHeaderRepository saleHeaderRepository;

	/**
	 * コンストラクタインジェクション
	 *
	 * @param categoryRepository カテゴリリポジトリ
	 * @param productRepository 商品リポジトリ
	 * @param customerRepository 顧客リポジトリ
	 * @param saleHeaderRepository 売上ヘッダーリポジトリ
	 */
	public DataInitializer(
			CategoryRepository categoryRepository,
			ProductRepository productRepository,
			CustomerRepository customerRepository,
			SaleHeaderRepository saleHeaderRepository) {
		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
		this.customerRepository = customerRepository;
		this.saleHeaderRepository = saleHeaderRepository;
	}

	/**
	 * アプリケーション起動時に実行されるメソッド
	 *
	 * サンプルデータを生成してデータベースに保存します。
	 * データが既に存在する場合はスキップします。
	 *
	 * @param args コマンドライン引数
	 * @throws Exception 例外が発生した場合
	 */
	@Override
	public void run(String... args) throws Exception {
		// データが既に存在する場合はスキップ
		if (categoryRepository.count() > 0) {
			System.out.println("✅ サンプルデータは既に存在します。スキップします。");
			return;
		}

		// カテゴリの作成
		Category literature = categoryRepository.save(new Category("小説"));
		Category business = categoryRepository.save(new Category("ビジネス書"));
		Category comics = categoryRepository.save(new Category("コミック"));
		Category magazine = categoryRepository.save(new Category("雑誌"));
		Category education = categoryRepository.save(new Category("教育・参考書"));

		// 商品の作成
		Product product1 = createProduct("名作文学全集", "定評のある文学全集", 12000, 50, literature);
		Product product2 = createProduct("ライトノベル", "人気シリーズ", 600, 300, literature);
		Product product3 = createProduct("入門Java", "プログラミング入門", 2800, 100, business);
		Product product4 = createProduct("週刊誌定期購読", "最新号", 400, 50, magazine);
		Product product5 = createProduct("人気コミック1", "ベストセラー", 600, 80, comics);
		Product product6 = createProduct("MBAマーケティング", "経営戦略", 3200, 60, business);
		Product product7 = createProduct("コミックセット", "既刊1-10巻", 5500, 200, comics);
		Product product8 = createProduct("リーダーシップ論", "リーダー養成", 1800, 150, business);
		Product product9 = createProduct("月刊情報誌", "ITトレンド", 800, 100, magazine);
		Product product10 = createProduct("英語参考書", "TOEIC対策", 2500, 90, education);

		// 顧客の作成
		Customer customer1 = customerRepository.save(
			new Customer("佐藤太郎", "sato@example.com", null));
		customer1.setMemberNumber("MEM" + String.format("%08d", customer1.getId()));
		customerRepository.save(customer1);

		Customer customer2 = customerRepository.save(
			new Customer("鈴木花子", "suzuki@example.com", null));
		customer2.setMemberNumber("MEM" + String.format("%08d", customer2.getId()));
		customerRepository.save(customer2);

		Customer customer3 = customerRepository.save(
			new Customer("山田一郎", "yamada@example.com", null));
		customer3.setMemberNumber("MEM" + String.format("%08d", customer3.getId()));
		customerRepository.save(customer3);

		Customer customer4 = customerRepository.save(
			new Customer("田中美咲", "tanaka@example.com", "MEMBER0004"));
		customer4.setMemberNumber("MEM" + String.format("%08d", customer4.getId()));
		customerRepository.save(customer4);

		Customer customer5 = customerRepository.save(
			new Customer("伊藤健太", "ito@example.com", "MEMBER0005"));
		customer5.setMemberNumber("MEM" + String.format("%08d", customer5.getId()));
		customerRepository.save(customer5);

		// 販売データの作成（ヘッダーー明細構造）
		// IDと日付の順番を一致させる（ID=1が最も古い、ID=7が最も新しい）
		LocalDate today = LocalDate.now();

		// ID=1: 30日前
		createSaleHeader(customer1, today.minusDays(30),
			new Sale(product1, 1, 12000));

		// ID=2: 25日前
		createSaleHeader(customer2, today.minusDays(25),
			new Sale(product3, 2, 2800));

		// ID=3: 20日前
		createSaleHeader(customer3, today.minusDays(20),
			new Sale(product2, 3, 600),
			new Sale(product5, 2, 600));

		// ID=4: 15日前
		createSaleHeader(customer4, today.minusDays(15),
			new Sale(product6, 1, 3200));

		// ID=5: 10日前
		createSaleHeader(customer5, today.minusDays(10),
			new Sale(product4, 2, 400));

		// ID=6: 5日前
		createSaleHeader(customer1, today.minusDays(5),
			new Sale(product7, 3, 5500));

		// ID=7: 1日前
		createSaleHeader(customer2, today.minusDays(1),
			new Sale(product9, 5, 800));

		System.out.println("✅ サンプルデータの初期化が完了しました！");
		System.out.println("   カテゴリ: 5件");
		System.out.println("   商品: 10件");
		System.out.println("   顧客: 5件");
		System.out.println("   売上ヘッダー: 7件");
	}

	/**
	 * 商品を作成するヘルパーメソッド
	 *
	 * @param name 商品名
	 * @param description 説明
	 * @param price 価格
	 * @param stock 在庫数
	 * @param categories カテゴリ（可変長引数）
	 * @return 作成された商品
	 */
	private Product createProduct(String name, String description, int price, int stock, Category... categories) {
		Product product = new Product(name, description, price, stock);
		for (Category category : categories) {
			product.getCategories().add(category);
		}
		return productRepository.save(product);
	}

	/**
	 * 売上ヘッダーを作成するヘルパーメソッド
	 *
	 * @param customer 顧客
	 * @param saleDate 販売日
	 * @param details 明細（可変長引数）
	 * @return 作成された売上ヘッダー
	 */
	private SaleHeader createSaleHeader(Customer customer, LocalDate saleDate, Sale... details) {
		SaleHeader header = new SaleHeader(customer, saleDate);
		for (Sale detail : details) {
			header.addDetail(detail);
		}
		return saleHeaderRepository.save(header);
	}

	/**
	 * 売上明細を作成するヘルパーメソッド
	 *
	 * @param product 商品
	 * @param quantity 数量
	 * @param salePrice 販売単価
	 * @return 作成された売上明細
	 */
	private Sale createSale(Product product, int quantity, int salePrice) {
		return new Sale(product, quantity, salePrice);
	}
}
