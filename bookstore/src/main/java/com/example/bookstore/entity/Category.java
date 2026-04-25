package com.example.bookstore.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * カテゴリエンティティ
 *
 * 商品のカテゴリ（書籍、家電、衣料品など）を管理するエンティティ。
 * Productエンティティと多対多（N:N）の関係を持つ。
 *
 * @author BookStore Project
 * @version 1.0
 */
@Entity
@Table(name = "categories")
public class Category {

	/** 主キー */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** カテゴリ名（一意制約） */
	@Column(unique = true, nullable = false)
	private String name;

	/** N:N関係：このカテゴリに属する商品セット */
	@ManyToMany(mappedBy = "categories")
	private Set<Product> products = new HashSet<>();

	/** デフォルトコンストラクタ */
	public Category() {}

	/**
	 * カテゴリ名を指定してインスタンスを生成
	 *
	 * @param name カテゴリ名
	 */
	public Category(String name) {
		this.name = name;
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

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
}
