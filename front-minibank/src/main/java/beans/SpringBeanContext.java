package beans;

import dao.*;
import dao.impl.*;
import entity.Account;
import entity.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

public class SpringBeanContext {

    @Bean
    public ViewResolver viewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public CustomerWsDao customerWsDao(){
        return new CustomerWsDaoImpl();
    }

    @Bean
    public AuthWsDao authService(){
        return new AuthWsDaoImpl();
    }

    @Bean
    public AccountWsDao accountWsDao(){
        return new AccountWaDaoImpl();
    }

    @Bean
    public Account account(){
        return new Account();
    }

    @Bean
    public Customer customer(){
        return new Customer();
    }

    @Bean
    public TrxWsDao trxWsDao(){
        return new TrxWsDaoImpl();
    }

    @Bean
    public WalletWsDao walletWsDao(){
        return new WalletWsDaoImpl();
    }

}
