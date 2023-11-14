package com.learn.stockwatch.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

	@Value("${swagger.env.url}")
	private String url;

	@Value("${swagger.contract.email}")
	private String contactEmail;

	@Value("${swagger.contract.name}")
	private String contactName;

	@Value("${swagger.info.desc}")
	private String description;

	@Value("${swagger.info.title}")
	private String title;

	@Value("${swagger.licence.name}")
	private String licenceName;

	@Value("${swagger.licence.url}")
	private String licenceURL;

	@Bean
	public OpenAPI swaager() {

		Server server = new Server();
		server.setUrl(url);
		server.setDescription("Server URL in Developemnt");

		Contact contact = new Contact();
		contact.setEmail(contactEmail);
		contact.setName(contactName);

		License licence = new License().name(licenceName).url(licenceURL);

		Info info = new Info().title(title).version("1.0").contact(contact).description(description).license(licence);

		return new OpenAPI().info(info).servers(List.of(server));
	}

}
