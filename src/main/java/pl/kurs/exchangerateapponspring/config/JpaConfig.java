package pl.kurs.exchangerateapponspring.config;


import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Profile({"prod", "!prod & !dev"}) // drugi argument oznacza,że ten profil zostanie wybrany domyślnie
@Configuration
@PropertySource("classpath:application.properties")
public class JpaConfig {

    @Autowired
    private Environment env;


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
        adapter.setDatabase(Database.valueOf(env.getProperty("database.type")));
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        return adapter;
    }

    @Bean
    public DataSource createDS() {
        System.out.println("Utworzenie DataSourca");
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(env.getProperty("database.url"));
        ds.setUsername(env.getProperty("database.user"));
        ds.setPassword(env.getProperty("database.pass"));
        ds.setDriverClassName(env.getProperty("database.driver"));
        ds.setInitialSize(5);
        return ds;
    }

}
