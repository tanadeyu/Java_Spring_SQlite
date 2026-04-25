package com.example.bookstore.dto;

/**
 * 顧客別売上DTO
 *
 * 顧客ごとの購入集計結果を保持するデータ転送オブジェクト。
 *
 * @author BookStore Project
 * @version 1.0
 */
public class CustomerSales {

	private String customerName;
	private Long totalSales;
	private Long purchaseCount;

	/**
	 * コンストラクタ
	 *
	 * @param customerName 顧客名
	 * @param totalSales 総購入金額
	 * @param purchaseCount 購入回数
	 */
	public CustomerSales(String customerName, Long totalSales, Long purchaseCount) {
		this.customerName = customerName;
		this.totalSales = totalSales;
		this.purchaseCount = purchaseCount;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Long getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(Long totalSales) {
		this.totalSales = totalSales;
	}

	public Long getPurchaseCount() {
		return purchaseCount;
	}

	public void setPurchaseCount(Long purchaseCount) {
		this.purchaseCount = purchaseCount;
	}
}
