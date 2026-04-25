package com.example.bookstore.service;

import com.example.bookstore.entity.Product;
import com.example.bookstore.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品サービス
 *
 * 商品データの管理を行うサービスクラス。
 *
 * @author BookStore Project
 * @version 1.0
 */
@Service
@Transactional
public class ProductService {

	private final ProductRepository productRepository;

	/**
	 * コンストラクタインジェクション
	 *
	 * @param productRepository 商品リポジトリ
	 */
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	/**
	 * 全商品を取得する
	 *
	 * @return 商品リスト
	 */
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	/**
	 * IDで商品を取得する
	 *
	 * @param id 商品ID
	 * @return 商品
	 */
	public Product findById(Long id) {
		return productRepository.findById(id).orElseThrow();
	}

	/**
	 * 商品を新規登録する
	 *
	 * @param product 登録する商品
	 * @return 登録された商品
	 */
	public Product create(Product product) {
		return productRepository.save(product);
	}

	/**
	 * 商品を更新する（在庫数変更用）
	 *
	 * @param product 更新する商品
	 * @return 更新された商品
	 */
	public Product update(Product product) {
		return productRepository.save(product);
	}
}
