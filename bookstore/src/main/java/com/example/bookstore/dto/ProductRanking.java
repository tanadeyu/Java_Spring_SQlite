package com.example.bookstore.dto;

/**
 * 商品ランキングDTO
 *
 * 売上上位商品の集計結果を保持するデータ転送オブジェクト。
 *
 * @author BookStore Project
 * @version 1.0
 */
public class ProductRanking {

	private String name;
	private Long totalSales;
	private Long saleQuantity;

	/**
	 * コンストラクタ
	 *
	 * @param name 商品名
	 * @param totalSales 総売上金額
	 * @param saleQuantity 販売数量
	 */
	public ProductRanking(String name, Long totalSales, Long saleQuantity) {
		this.name = name;
		this.totalSales = totalSales;
		this.saleQuantity = saleQuantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(Long totalSales) {
		this.totalSales = totalSales;
	}

	public Long getSaleQuantity() {
		return saleQuantity;
	}

	public void setSaleQuantity(Long saleQuantity) {
		this.saleQuantity = saleQuantity;
	}
}
