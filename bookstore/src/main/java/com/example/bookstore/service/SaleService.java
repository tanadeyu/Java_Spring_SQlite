package com.example.bookstore.service;

import com.example.bookstore.dto.CategorySales;
import com.example.bookstore.dto.CustomerSales;
import com.example.bookstore.dto.ProductRanking;
import com.example.bookstore.repository.SaleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 売上サービス
 *
 * 売上データの分析機能を提供するサービスクラス。
 *
 * @author BookStore Project
 * @version 1.0
 */
@Service
@Transactional
public class SaleService {

	private final SaleRepository saleRepository;

	/**
	 * コンストラクタインジェクション
	 *
	 * @param saleRepository 売上リポジトリ
	 */
	public SaleService(SaleRepository saleRepository) {
		this.saleRepository = saleRepository;
	}

	/**
	 * 売上ランキングを取得する
	 *
	 * @return 商品ランキングリスト
	 */
	public List<ProductRanking> getSalesRanking() {
		return saleRepository.findTopSellingProducts();
	}

	/**
	 * カテゴリ別売上を取得する
	 *
	 * @return カテゴリ別売上リスト
	 */
	public List<CategorySales> getCategorySales() {
		return saleRepository.findSalesByCategory();
	}

	/**
	 * 顧客別売上を取得する
	 *
	 * @return 顧客別売上リスト
	 */
	public List<CustomerSales> getCustomerSales() {
		return saleRepository.findSalesByCustomer();
	}

	/**
	 * 総売上金額を取得する
	 *
	 * @return 総売上金額（データがない場合は0）
	 */
	public Long getTotalSales() {
		return saleRepository.findTotalSales() != null ? saleRepository.findTotalSales() : 0L;
	}

	/**
	 * 総販売数を取得する
	 *
	 * @return 総販売数（データがない場合は0）
	 */
	public Long getTotalQuantity() {
		return saleRepository.findTotalQuantity() != null ? saleRepository.findTotalQuantity() : 0L;
	}
}
