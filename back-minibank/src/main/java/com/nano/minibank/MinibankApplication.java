package com.nano.minibank;

import com.nano.minibank.beans.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan({"com.nano.minibank.entities"})
@EnableJpaRepositories({"com.nano.minibank.repositories"})
@Import({CustomerBean.class, AccountBean.class, TrxBean.class, WalletBean.class, SwaggerBean.class})
public class MinibankApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinibankApplication.class, args);
	}

}
