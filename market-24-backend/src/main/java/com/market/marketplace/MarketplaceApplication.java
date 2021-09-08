package com.market.marketplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@SpringBootApplication
//@SpringBootApplication
//		(exclude = {SecurityAutoConfiguration.class })
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class MarketplaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketplaceApplication.class, args);
	}

}
