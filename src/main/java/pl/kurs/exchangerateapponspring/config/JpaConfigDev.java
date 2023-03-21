package pl.kurs.exchangerateapponspring.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Profile("dev")
@Configuration
public class JpaConfigDev {

    @Bean
    public LocalContainerEntityManagerFactoryBean createEMF(JpaVendorAdapter adapter, DataSource ds){ //ten EMF nie potrzebuje persistence.xml i NIE korzysta z puli połączeń
        System.out.println("Utowrzenie EMFa");

        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setPersistenceUnitName("springPersistenceUnit");
        emf.setJpaVendorAdapter(adapter);
        emf.setPackagesToScan("pl.kurs.exchangerateapponspring.model"); //to wskazuje lokazlizację encji
        emf.setDataSource(ds);
        return emf;

    }

    @Bean
    public JpaVendorAdapter createVendorAdapter() {
        System.out.println("Utowrzenie JpaVendorAdaptera");
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.H2);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        return adapter;
    }

    @Bean
    public DataSource createDS() {
        System.out.println("Utworzenie DataSourca");
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:h2:mem:testdb");
        ds.setUsername("SA");
        ds.setPassword("");
        ds.setDriverClassName("org.h2.Driver");
        ds.setInitialSize(5);
        return ds;
    }
}
