package ba.unsa.etf.nwt.esc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ba.unsa.etf.nwt.esc.model.Asset;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

}
