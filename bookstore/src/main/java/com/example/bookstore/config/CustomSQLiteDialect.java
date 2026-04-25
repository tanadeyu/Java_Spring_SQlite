package com.example.bookstore.config;

import org.hibernate.boot.model.FunctionContributions;
import org.hibernate.community.dialect.SQLiteDialect;

/**
 * SQLite用のカスタムダイアレクト（方言）クラス
 *
 * HibernateのSQLiteDialectを継承し、必要に応じてカスタム関数などを追加できます。
 * Spring BootのJPA設定でこのクラスを指定することで、SQLiteデータベースと連携します。
 *
 * @see org.hibernate.community.dialect.SQLiteDialect
 */
public class CustomSQLiteDialect extends SQLiteDialect {

	/**
	 * 関数レジストリの初期化
	 *
	 * 必要に応じてSQLiteの標準関数以外のカスタム関数を登録できます。
	 * 現在は親クラスの機能をそのまま使用しています。
	 *
	 * @param functionContributions 関数貢献度オブジェクト
	 */
	@Override
	public void initializeFunctionRegistry(FunctionContributions functionContributions) {
		super.initializeFunctionRegistry(functionContributions);
		// 必要に応じて関数を追加
		// 例: functionContributions.getFunctionRegistry().registerPattern("hoge", "hoge_func(?1)");
	}
}