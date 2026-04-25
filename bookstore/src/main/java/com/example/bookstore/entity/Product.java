package com.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 商品エンティティ
 *
 * 商品情報を管理するエンティティ。
 * Categoryエンティティと多対多（N:N）、Saleエンティティと一対多（1:N）の関係を持つ。
 *
 * @author BookStore Project
 * @version 1.0
 */
@Entity
@Table(name = "products")
public class Product {

	/** 主キー */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** 商品名 */
	@Column(nullable = false)
	private String name;

	/** 説明 */
	private String description;

	/** 価格 */
	private Integer price;

	/** 在庫数（デフォルト値: 0） */
	private Integer stock = 0;

	/** 発行日（書籍の場合など） */
	private java.time.LocalDate publishedDate;

	/** N:N関係：この商品が属するカテゴリセット */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "product_categories",
		joinColumns = @JoinColumn(name = "product_id"),
		inverseJoinColumns = @JoinColumn(name = "category_id")
	)
	private Set<Category> categories = new HashSet<>();


	/** デフォルトコンストラクタ */
	public Product() {}

	/**
	 * 商品情報を指定してインスタンスを生成
	 *
	 * @param name 商品名
	 * @param description 説明
	 * @param price 価格
	 * @param stock 在庫数
	 */
	public Product(String name, String description, Integer price, Integer stock) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
	}

	// Getter/Setter

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public java.time.LocalDate getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(java.time.LocalDate publishedDate) {
		this.publishedDate = publishedDate;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	/**
	 * カテゴリを追加する便利メソッド
	 *
	 * 双方向の関連を維持するため、Product側とCategory側の両方に追加します。
	 *
	 * @param category 追加するカテゴリ
	 */
	public void addCategory(Category category) {
		categories.add(category);
		category.getProducts().add(this);
	}
}
