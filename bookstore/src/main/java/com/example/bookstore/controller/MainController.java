package com.example.bookstore.controller;

import com.example.bookstore.dto.CategorySales;
import com.example.bookstore.dto.CustomerSales;
import com.example.bookstore.dto.ProductRanking;
import com.example.bookstore.service.SaleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * メインコントローラー
 *
 * ダッシュボード画面（トップページ）を担当するコントローラークラス。
 *
 * @author BookStore Project
 * @version 1.0
 */
@Controller
public class MainController {

	private final SaleService saleService;

	/**
	 * コンストラクタインジェクション
	 *
	 * @param saleService 売上サービス
	 */
	public MainController(SaleService saleService) {
		this.saleService = saleService;
	}

	/**
	 * トップページ（ダッシュボード）を表示する
	 *
	 * データ分析結果をモデルに追加してビューに渡します。
	 *
	 * @param model モデル
	 * @return ビュー名
	 */
	@GetMapping("/")
	public String index(Model model) {
		// 統計サマリー
		model.addAttribute("totalSales", saleService.getTotalSales());
		model.addAttribute("totalQuantity", saleService.getTotalQuantity());

		// データ分析結果
		model.addAttribute("ranking", saleService.getSalesRanking());
		model.addAttribute("categorySales", saleService.getCategorySales());
		model.addAttribute("customerSales", saleService.getCustomerSales());

		return "index";
	}

}
