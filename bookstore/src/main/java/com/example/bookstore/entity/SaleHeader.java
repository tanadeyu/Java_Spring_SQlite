package com.example.bookstore.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 売上ヘッダーエンティティ
 *
 * 販売伝票のヘッダー情報を管理するエンティティ。
 *
 * @author BookStore Project
 * @version 1.0
 */
@Entity
@Table(name = "sale_headers")
public class SaleHeader {

	/** 主キー */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** 購入顧客（多対一） */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	/** 販売日 */
	@Column(nullable = false, name = "sale_date")
	private LocalDate saleDate;

	/** 合計金額 */
	@Column(nullable = false)
	private Integer totalAmount;

	/** 明細リスト（1対多） */
	@OneToMany(mappedBy = "header", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Sale> details = new ArrayList<>();

	/** デフォルトコンストラクタ */
	public SaleHeader() {}

	/**
	 * 売上ヘッダーを指定してインスタンスを生成
	 *
	 * @param customer 購入顧客
	 * @param saleDate 販売日
	 */
	public SaleHeader(Customer customer, LocalDate saleDate) {
		this.customer = customer;
		this.saleDate = saleDate;
		this.totalAmount = 0;
	}

	// Getter/Setter

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public LocalDate getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(LocalDate saleDate) {
		this.saleDate = saleDate;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<Sale> getDetails() {
		return details;
	}

	public void setDetails(List<Sale> details) {
		this.details = details;
	}

	/**
	 * 明細を追加する
	 *
	 * @param detail 明細
	 */
	public void addDetail(Sale detail) {
		details.add(detail);
		detail.setHeader(this);
		totalAmount += detail.getTotalAmount();
	}

	/**
	 * 合計金額を再計算する
	 */
	public void recalculateTotal() {
		totalAmount = details.stream()
			.mapToInt(Sale::getTotalAmount)
			.sum();
	}
}
