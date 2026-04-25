# Claude Code Feedback Log

## JPA Entity Save Pattern

**Why:** JPAでエンティティのフィールドを変更しただけではDBに反映されない

**How to apply:** JPAエンティティ（Product、Customerなど）のフィールドを変更した後は、必ずsave()またはrepository.save()を呼び出す必要がある

### 間違った実装
```java
Product product = productService.findById(id);
product.setStock(newStock);  // メモリ上の値は変わるがDBには反映されない
// ここでsave()を忘れている
```

### 正しい実装
```java
Product product = productService.findById(id);
product.setStock(newStock);
productService.update(product);  // ← 重要：必ず保存処理を呼ぶ
```

### よくあるミスのパターン
1. セッター呼び出し後にsave()を忘れる
2. トランザクション内で変更しても保存しないと反映されない
3. 複数のエンティティを変更する場合、それぞれsave()が必要
