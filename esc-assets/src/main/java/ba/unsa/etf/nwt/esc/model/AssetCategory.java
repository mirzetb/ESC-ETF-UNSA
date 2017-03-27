package ba.unsa.etf.nwt.esc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class AssetCategory {
	@Id
	@GeneratedValue
	Long id;
	String code;
	String name;
	@OneToMany(mappedBy="assetCategory")
	List<Asset> assets = new ArrayList<Asset>();
	
	protected AssetCategory() {
	}
	public List<Asset> getAssets() {
		return assets;
	}
	public void setAssets(List<Asset> assets) {
		this.assets = assets;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	
}
