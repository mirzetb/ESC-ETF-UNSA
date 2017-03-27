package ba.unsa.etf.nwt.esc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ba.unsa.etf.nwt.esc.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
