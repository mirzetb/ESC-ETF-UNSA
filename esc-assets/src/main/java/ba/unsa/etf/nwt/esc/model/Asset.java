package ba.unsa.etf.nwt.esc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Asset {
	@Id
	@GeneratedValue
	Long id;
	String name;
	String description;
	Integer inStock;
	Integer inventoryId;
	Double price;
	@ManyToOne
	AssetType assetType;
	@ManyToOne
	AssetCategory assetCategory;
	@ManyToOne
	AssetManufacturer assetManufacturer;
	@OneToMany(mappedBy="asset")
	List<InvoiceAsset> invoices = new ArrayList<InvoiceAsset>();
	
	protected Asset() {
	}
	public List<InvoiceAsset> getInvoices() {
		return invoices;
	}
	public void setInvoices(List<InvoiceAsset> invoices) {
		this.invoices = invoices;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getInStock() {
		return inStock;
	}
	public void setInStock(Integer inStock) {
		this.inStock = inStock;
	}
	public Integer getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public AssetType getAssetType() {
		return assetType;
	}
	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}
	public AssetCategory getAssetCaegory() {
		return assetCategory;
	}
	public void setAssetCaegory(AssetCategory assetCaegory) {
		this.assetCategory = assetCaegory;
	}
	public AssetManufacturer getAssetManufacturer() {
		return assetManufacturer;
	}
	public void setAssetManufacturer(AssetManufacturer assetManufacturer) {
		this.assetManufacturer = assetManufacturer;
	}
	public Long getId() {
		return id;
	}
	
}
