package com.example.bookstore.service;

import com.example.bookstore.entity.Customer;
import com.example.bookstore.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 顧客サービス
 *
 * 顧客データの管理を行うサービスクラス（閲覧・登録のみ）。
 *
 * @author BookStore Project
 * @version 1.0
 */
@Service
@Transactional
public class CustomerService {

	private final CustomerRepository customerRepository;

	/**
	 * コンストラクタインジェクション
	 *
	 * @param customerRepository 顧客リポジトリ
	 */
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	/**
	 * 全顧客を取得する
	 *
	 * @return 顧客リスト
	 */
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	/**
	 * IDで顧客を取得する
	 *
	 * @param id 顧客ID
	 * @return 顧客
	 */
	public Customer findById(Long id) {
		return customerRepository.findById(id).orElseThrow();
	}

	/**
	 * 顧客を新規登録する
	 *
	 * @param customer 登録する顧客
	 * @return 登録された顧客
	 */
	public Customer create(Customer customer) {
		return customerRepository.save(customer);
	}
}
