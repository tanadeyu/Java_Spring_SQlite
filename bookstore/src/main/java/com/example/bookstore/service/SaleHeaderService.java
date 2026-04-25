package com.example.bookstore.service;

import com.example.bookstore.entity.Customer;
import com.example.bookstore.entity.Product;
import com.example.bookstore.entity.Sale;
import com.example.bookstore.entity.SaleHeader;
import com.example.bookstore.repository.SaleHeaderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * 売上ヘッダーサービス
 *
 * 売上ヘッダーのビジネスロジックを提供するサービスクラス。
 *
 * @author BookStore Project
 * @version 1.0
 */
@Service
@Transactional
public class SaleHeaderService {

	private final SaleHeaderRepository saleHeaderRepository;
	private final CustomerService customerService;
	private final ProductService productService;

	/**
	 * コンストラクタインジェクション
	 *
	 * @param saleHeaderRepository 売上ヘッダーリポジトリ
	 * @param customerService 顧客サービス
	 * @param productService 商品サービス
	 */
	public SaleHeaderService(SaleHeaderRepository saleHeaderRepository,
							  CustomerService customerService,
							  ProductService productService) {
		this.saleHeaderRepository = saleHeaderRepository;
		this.customerService = customerService;
		this.productService = productService;
	}

	/**
	 * 全ての売上ヘッダーを取得する
	 *
	 * @return 売上ヘッダーリスト
	 */
	public List<SaleHeader> findAll() {
		return saleHeaderRepository.findAll();
	}

	/**
	 * IDで売上ヘッダーを取得する
	 *
	 * @param id 売上ヘッダーID
	 * @return 売上ヘッダー
	 */
	public SaleHeader findById(Long id) {
		return saleHeaderRepository.findById(id).orElseThrow();
	}

	/**
	 * 売上ヘッダーを作成する
	 *
	 * @param header 売上ヘッダー
	 * @return 作成された売上ヘッダー
	 */
	public SaleHeader create(SaleHeader header) {
		// 在庫数を減らす
		for (Sale detail : header.getDetails()) {
			Product product = productService.findById(detail.getProduct().getId());
			int newStock = product.getStock() - detail.getQuantity();
			if (newStock < 0) {
				throw new IllegalArgumentException(product.getName() + "の在庫が不足しています（残: " + product.getStock() + "、要求: " + detail.getQuantity() + "）");
			}
			product.setStock(newStock);
			productService.update(product);
		}
		return saleHeaderRepository.save(header);
	}

	/**
	 * 売上ヘッダーを削除する
	 *
	 * @param id 売上ヘッダーID
	 */
	public void delete(Long id) {
		saleHeaderRepository.deleteById(id);
	}
}
