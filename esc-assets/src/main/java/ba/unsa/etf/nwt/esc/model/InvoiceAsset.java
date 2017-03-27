package ba.unsa.etf.nwt.esc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class InvoiceAsset {
	@Id
	@GeneratedValue
	Long id;
	Integer quantity;
	Double price;
	@ManyToOne
	Asset asset;
	@ManyToOne
	Invoice invoice;
	
	protected InvoiceAsset() {
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Asset getAsset() {
		return asset;
	}
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	public Long getId() {
		return id;
	}
	
}
