package ba.unsa.etf.nwt.esc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ba.unsa.etf.nwt.esc.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
