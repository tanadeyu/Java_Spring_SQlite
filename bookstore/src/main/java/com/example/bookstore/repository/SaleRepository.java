package com.example.bookstore.repository;

import com.example.bookstore.dto.CategorySales;
import com.example.bookstore.dto.CustomerSales;
import com.example.bookstore.dto.ProductRanking;
import com.example.bookstore.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 売上リポジトリ
 *
 * Saleエンティティ（明細行）へのデータアクセスを提供するリポジトリインターフェース。
 * データ分析用の集計クエリも含む。
 *
 * @author BookStore Project
 * @version 1.0
 */
@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

	// ========== 分析クエリ ==========

	/**
	 * 売上上位の商品を取得する（GROUP BY、ORDER BY）
	 *
	 * @return 商品ランキング（売上金額順）
	 */
	@Query("SELECT NEW com.example.bookstore.dto.ProductRanking(" +
		   "s.product.name, " +
		   "SUM(s.quantity * s.salePrice), " +
		   "SUM(s.quantity)) " +
		   "FROM Sale s " +
		   "GROUP BY s.product " +
		   "ORDER BY SUM(s.quantity * s.salePrice) DESC")
	List<ProductRanking> findTopSellingProducts();

	/**
	 * カテゴリ別売上集計を取得する（JOIN、GROUP BY）
	 *
	 * @return カテゴリ別売上リスト
	 */
	@Query("SELECT NEW com.example.bookstore.dto.CategorySales(" +
		   "c.name, " +
		   "SUM(s.quantity * s.salePrice), " +
		   "SUM(s.quantity)) " +
		   "FROM Sale s " +
		   "JOIN s.product p " +
		   "JOIN p.categories c " +
		   "GROUP BY c " +
		   "ORDER BY SUM(s.quantity * s.salePrice) DESC")
	List<CategorySales> findSalesByCategory();

	/**
	 * 顧客別購入金額を取得する
	 *
	 * @return 顧客別売上リスト
	 */
	@Query("SELECT NEW com.example.bookstore.dto.CustomerSales(" +
		   "h.customer.name, " +
		   "SUM(s.quantity * s.salePrice), " +
		   "COUNT(s)) " +
		   "FROM Sale s " +
		   "JOIN s.header h " +
		   "GROUP BY h.customer " +
		   "ORDER BY SUM(s.quantity * s.salePrice) DESC")
	List<CustomerSales> findSalesByCustomer();

	/**
	 * 総売上金額を取得する
	 *
	 * @return 総売上金額（データがない場合はnull）
	 */
	@Query("SELECT SUM(s.quantity * s.salePrice) FROM Sale s")
	Long findTotalSales();

	/**
	 * 総販売数を取得する
	 *
	 * @return 総販売数（データがない場合はnull）
	 */
	@Query("SELECT SUM(s.quantity) FROM Sale s")
	Long findTotalQuantity();
}
