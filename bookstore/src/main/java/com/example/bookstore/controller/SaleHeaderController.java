package com.example.bookstore.controller;

import com.example.bookstore.entity.Customer;
import com.example.bookstore.entity.Product;
import com.example.bookstore.entity.Sale;
import com.example.bookstore.entity.SaleHeader;
import com.example.bookstore.service.CustomerService;
import com.example.bookstore.service.ProductService;
import com.example.bookstore.service.SaleHeaderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

/**
 * 売上ヘッダーコントローラー
 *
 * 売上管理画面を担当するコントローラークラス。
 *
 * @author BookStore Project
 * @version 1.0
 */
@Controller
@RequestMapping("/sales")
public class SaleHeaderController {

	private final SaleHeaderService saleHeaderService;
	private final CustomerService customerService;
	private final ProductService productService;

	/**
	 * コンストラクタインジェクション
	 *
	 * @param saleHeaderService 売上ヘッダーサービス
	 * @param customerService 顧客サービス
	 * @param productService 商品サービス
	 */
	public SaleHeaderController(SaleHeaderService saleHeaderService,
								CustomerService customerService,
								ProductService productService) {
		this.saleHeaderService = saleHeaderService;
		this.customerService = customerService;
		this.productService = productService;
	}

	/**
	 * 売上一覧画面を表示する
	 *
	 * @param model モデル
	 * @return ビュー名
	 */
	@GetMapping
	public String list(Model model) {
		model.addAttribute("headers", saleHeaderService.findAll());
		return "sales";
	}

	/**
	 * 新規登録画面を表示する
	 *
	 * @param model モデル
	 * @return ビュー名
	 */
	@GetMapping("/new")
	public String newForm(Model model) {
		model.addAttribute("customers", customerService.findAll());
		model.addAttribute("products", productService.findAll());
		return "sales-create";
	}

	/**
	 * 売上を作成する
	 *
	 * @param customerId 顧客ID
	 * @param saleDate 販売日
	 * @param productIds 商品ID配列
	 * @param quantities 数量配列
	 * @param model モデル
	 * @return リダイレクトURL
	 */
	@PostMapping("/create")
	public String create(@RequestParam Long customerId,
						  @RequestParam String saleDate,
						  @RequestParam(required = false) Long[] productIds,
						  @RequestParam(required = false) Integer[] quantities,
						  Model model) {
		if (productIds == null || quantities == null) {
			model.addAttribute("error", "商品を少なくとも1つ選択してください");
			model.addAttribute("customers", customerService.findAll());
			model.addAttribute("products", productService.findAll());
			return "sales-create";
		}

		Customer customer = customerService.findById(customerId);
		SaleHeader header = new SaleHeader(customer, LocalDate.parse(saleDate));

		for (int i = 0; i < productIds.length; i++) {
			if (productIds[i] != null && quantities[i] != null && quantities[i] > 0) {
				Product product = productService.findById(productIds[i]);
				Sale detail = new Sale(product, quantities[i], product.getPrice());
				header.addDetail(detail);
			}
		}

		if (header.getDetails().isEmpty()) {
			model.addAttribute("error", "商品を少なくとも1つ選択してください");
			model.addAttribute("customers", customerService.findAll());
			model.addAttribute("products", productService.findAll());
			return "sales-create";
		}

		saleHeaderService.create(header);
		return "redirect:/sales";
	}

	/**
	 * 売上を削除する
	 *
	 * @param id 売上ヘッダーID
	 * @return リダイレクトURL
	 */
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Long id) {
		saleHeaderService.delete(id);
		return "redirect:/sales";
	}

	/**
	 * 売上詳細を取得する（Ajax用）
	 *
	 * @param id 売上ヘッダーID
	 * @param model モデル
	 * @return 詳細テンプレートのフラグメント
	 */
	@GetMapping("/{id}/detail")
	public String getDetail(@PathVariable Long id, Model model) {
		model.addAttribute("header", saleHeaderService.findById(id));
		return "sales-detail :: detail";
	}

}
