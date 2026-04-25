package com.example.bookstore.repository;

import com.example.bookstore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * カテゴリリポジトリ
 *
 * Categoryエンティティへのデータアクセスを提供するリポジトリインターフェース。
 *
 * @author BookStore Project
 * @version 1.0
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	/**
	 * カテゴリ名で検索する
	 *
	 * @param name カテゴリ名
	 * @return 検索結果（存在しない場合は空）
	 */
	Optional<Category> findByName(String name);
}
