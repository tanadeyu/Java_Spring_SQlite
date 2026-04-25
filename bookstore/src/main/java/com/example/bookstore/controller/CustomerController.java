package com.example.bookstore.controller;

import com.example.bookstore.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 顧客コントローラー
 *
 * 顧客一覧画面を担当するコントローラークラス（閲覧のみ）。
 *
 * @author BookStore Project
 * @version 1.0
 */
@Controller
@RequestMapping("/customers")
public class CustomerController {

	private final CustomerService customerService;

	/**
	 * コンストラクタインジェクション
	 *
	 * @param customerService 顧客サービス
	 */
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	/**
	 * 顧客一覧画面を表示する
	 *
	 * @param model モデル
	 * @return ビュー名
	 */
	@GetMapping
	public String list(Model model) {
		model.addAttribute("customers", customerService.findAll());
		return "customers";
	}
}
