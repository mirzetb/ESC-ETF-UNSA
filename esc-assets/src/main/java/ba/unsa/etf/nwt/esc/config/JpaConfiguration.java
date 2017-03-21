package ba.unsa.etf.nwt.esc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages={"ba.unsa.etf.nwt.esc.repository"})
public class JpaConfiguration {

}
