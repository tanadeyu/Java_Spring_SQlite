package com.example.bookstore.dto;

/**
 * カテゴリ別売上DTO
 *
 * カテゴリごとの売上集計結果を保持するデータ転送オブジェクト。
 *
 * @author BookStore Project
 * @version 1.0
 */
public class CategorySales {

	private String categoryName;
	private Long totalSales;
	private Long productCount;

	/**
	 * コンストラクタ
	 *
	 * @param categoryName カテゴリ名
	 * @param totalSales 総売上金額
	 * @param productCount 販売冊数
	 */
	public CategorySales(String categoryName, Long totalSales, Long productCount) {
		this.categoryName = categoryName;
		this.totalSales = totalSales;
		this.productCount = productCount;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Long getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(Long totalSales) {
		this.totalSales = totalSales;
	}

	public Long getProductCount() {
		return productCount;
	}

	public void setProductCount(Long productCount) {
		this.productCount = productCount;
	}
}
