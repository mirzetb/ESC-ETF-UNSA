package ba.unsa.etf.nwt.esc.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Invoice {
	@Id
	@GeneratedValue
	Long id;
	Date date;
	@ManyToOne
	User user;
	@ManyToOne
	InvoiceType invoiceType;
	@OneToMany(mappedBy="invoice")
	List<InvoiceAsset> invoiceAssets = new ArrayList<InvoiceAsset>();
	
	protected Invoice() {
	}
	public List<InvoiceAsset> getInvoiceAssets() {
		return invoiceAssets;
	}
	public void setInvoiceAssets(List<InvoiceAsset> invoiceAssets) {
		this.invoiceAssets = invoiceAssets;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public InvoiceType getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(InvoiceType invoiceType) {
		this.invoiceType = invoiceType;
	}
	public Long getId() {
		return id;
	}
	
}
