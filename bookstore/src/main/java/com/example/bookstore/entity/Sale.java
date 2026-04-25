package com.example.bookstore.entity;

import jakarta.persistence.*;

/**
 * 売上明細エンティティ
 *
 * 販売明細を管理するエンティティ。
 *
 * @author BookStore Project
 * @version 1.0
 */
@Entity
@Table(name = "sales")
public class Sale {

	/** 主キー */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** 売上ヘッダー（多対一） */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "header_id", nullable = false)
	private SaleHeader header;

	/** 購入商品（多対一） */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	/** 販売数量 */
	@Column(nullable = false)
	private Integer quantity;

	/** 販売単価 */
	@Column(nullable = false, name = "sale_price")
	private Integer salePrice;

	/** デフォルトコンストラクタ */
	public Sale() {}

	/**
	 * 売上明細を指定してインスタンスを生成
	 *
	 * @param product 購入商品
	 * @param quantity 販売数量
	 * @param salePrice 販売単価
	 */
	public Sale(Product product, Integer quantity, Integer salePrice) {
		this.product = product;
		this.quantity = quantity;
		this.salePrice = salePrice;
	}

	// Getter/Setter

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SaleHeader getHeader() {
		return header;
	}

	public void setHeader(SaleHeader header) {
		this.header = header;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Integer salePrice) {
		this.salePrice = salePrice;
	}

	/**
	 * 明細金額を計算する
	 *
	 * @return 販売数量 × 販売単価
	 */
	public Integer getTotalAmount() {
		return quantity * salePrice;
	}
}
