package com.example.bookstore.repository;

import com.example.bookstore.entity.SaleHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 売上ヘッダーリポジトリ
 *
 * 売上ヘッダーのデータアクセスを行うリポジトリインターフェース。
 *
 * @author BookStore Project
 * @version 1.0
 */
@Repository
public interface SaleHeaderRepository extends JpaRepository<SaleHeader, Long> {
}
