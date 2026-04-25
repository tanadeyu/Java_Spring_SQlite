package com.example.bookstore.repository;

import com.example.bookstore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品リポジトリ
 *
 * Productエンティティへのデータアクセスを提供するリポジトリインターフェース。
 *
 * @author BookStore Project
 * @version 1.0
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	/**
	 * 商品名で検索する（部分一致）
	 *
	 * @param name 検索キーワードを含む商品名
	 * @return 検索結果リスト
	 */
	List<Product> findByNameContaining(String name);

	/**
	 * 説明文で検索する
	 *
	 * @param description 説明文
	 * @return 検索結果リスト
	 */
	List<Product> findByDescription(String description);

	/**
	 * 在庫数が指定数以下の商品を取得する
	 *
	 * @param stock 在庫数の閾値
	 * @return 在庫切れに近い商品リスト
	 */
	List<Product> findByStockLessThan(Integer stock);
}
