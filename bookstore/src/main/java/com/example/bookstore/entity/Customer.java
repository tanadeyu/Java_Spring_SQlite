package com.example.bookstore.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * 顧客エンティティ
 *
 * 顧客情報を管理するエンティティ。
 *
 * @author BookStore Project
 * @version 1.0
 */
@Entity
@Table(name = "customers")
public class Customer {

	/** 主キー */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** 氏名 */
	@Column(nullable = false)
	private String name;

	/** メールアドレス（一意制約） */
	@Column(unique = true, nullable = false)
	private String email;

	/** 会員番号（一意制約） */
	@Column(unique = true, name = "member_number")
	private String memberNumber;

	/** 会員登録日 */
	private LocalDate memberSince;

	/** デフォルトコンストラクタ */
	public Customer() {}

	/**
	 * 顧客情報を指定してインスタンスを生成
	 *
	 * @param name 氏名
	 * @param email メールアドレス
	 * @param memberNumber 会員番号
	 */
	public Customer(String name, String email, String memberNumber) {
		this.name = name;
		this.email = email;
		this.memberNumber = memberNumber;
		this.memberSince = LocalDate.now();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}

	public LocalDate getMemberSince() {
		return memberSince;
	}

	public void setMemberSince(LocalDate memberSince) {
		this.memberSince = memberSince;
	}
}
