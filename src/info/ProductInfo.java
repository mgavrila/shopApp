package info;

public class ProductInfo {

	private String category_name;
	private String parent_category;
	private String product_category;
	private String product_price;
	private String quantity;
	private String guarantee;
	private String product_description;
	private String product_name;
	private String category;
	
	public ProductInfo() {}

	public ProductInfo(String category_name, String parent_category) {
		this.category_name = category_name;
		this.parent_category = parent_category;
	}

	
	public ProductInfo(String product_name) {
		this.product_name = product_name;
	}

	public ProductInfo(String product_name, String product_price, String product_category, String quantity, String guarantee,
			String product_description) {
		this.product_category = product_category;
		this.product_price = product_price;
		this.quantity = quantity;
		this.guarantee = guarantee;
		this.product_description = product_description;
		this.product_name = product_name;
	}


	public ProductInfo(String category_name, String category, String parent_category) {
		this.category_name = category_name;
		this.parent_category = parent_category;
		this.category = category;
	}


	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getParent_category() {
		return parent_category;
	}
	public void setParent_category(String parent_category) {
		this.parent_category = parent_category;
	}
	public String getProduct_category() {
		return product_category;
	}
	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}
	public String getProduct_price() {
		return product_price;
	}
	public void setProduct_price(String product_price) {
		this.product_price = product_price;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getGuarantee() {
		return guarantee;
	}
	public void setGuarantee(String guarantee) {
		this.guarantee = guarantee;
	}
	public String getProduct_description() {
		return product_description;
	}
	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	
	
	
	
}
