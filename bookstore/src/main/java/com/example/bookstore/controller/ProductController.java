package com.example.bookstore.controller;

import com.example.bookstore.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商品コントローラー
 *
 * 商品一覧画面を担当するコントローラークラス（閲覧のみ）。
 *
 * @author BookStore Project
 * @version 1.0
 */
@Controller
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;

	/**
	 * コンストラクタインジェクション
	 *
	 * @param productService 商品サービス
	 */
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * 商品一覧画面を表示する
	 *
	 * @param model モデル
	 * @return ビュー名
	 */
	@GetMapping
	public String list(Model model) {
		model.addAttribute("products", productService.findAll());
		return "products";
	}
}
