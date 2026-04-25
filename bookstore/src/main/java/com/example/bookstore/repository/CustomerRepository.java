package com.example.bookstore.repository;

import com.example.bookstore.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 顧客リポジトリ
 *
 * Customerエンティティへのデータアクセスを提供するリポジトリインターフェース。
 *
 * @author BookStore Project
 * @version 1.0
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	/**
	 * メールアドレスで検索する
	 *
	 * @param email メールアドレス
	 * @return 検索結果（存在しない場合は空）
	 */
	Optional<Customer> findByEmail(String email);

	/**
	 * 会員番号で検索する
	 *
	 * @param memberNumber 会員番号
	 * @return 検索結果（存在しない場合は空）
	 */
	Optional<Customer> findByMemberNumber(String memberNumber);
}
