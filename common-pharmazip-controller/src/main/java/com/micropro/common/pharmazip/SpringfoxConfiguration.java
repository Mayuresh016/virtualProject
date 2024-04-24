package com.micropro.common.pharmazip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringfoxConfiguration {

	private static final String DOCKET_GROUP_NAME = "Micropro Software Solutions Pvt. Ltd.";

	@Bean
	Docket generatedDocket() {
		return docket().groupName(DOCKET_GROUP_NAME);
	}

	public Docket docket() {
		List<Parameter> parameters = new ArrayList<Parameter>();
		Parameter authHeader = new ParameterBuilder()
				.parameterType("header")
				.name("Authorization")
				.defaultValue("HMIS1.0 YjXzGtMJSegkSNeHFPrDo5Z4nag")
				.modelRef(new ModelRef("string"))
				.build();
		Parameter rightId = new ParameterBuilder()
				.parameterType("header")
				.name("rightId")
				.defaultValue("0")
				.modelRef(new ModelRef("string"))
				.build();
		parameters.add(authHeader);
		parameters.add(rightId);
		
		final List<ResponseMessage> globalResponses = Arrays.asList(
				new ResponseMessageBuilder()
				.code(200)
				.message("OK")
				.build(),
				new ResponseMessageBuilder()
				.code(400)
				.message("Bad Request")
				.build(),
				new ResponseMessageBuilder()
				.code(401)
				.message("Unauthorized")
				.build(),
				new ResponseMessageBuilder()
				.code(403)
				.message("Forbidden")
				.build(),
				new ResponseMessageBuilder()
				.code(404)
				.message("Not Found")
				.build(),
				new ResponseMessageBuilder()
				.code(500)
				.message("Server Side Error")
				.build());
		
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.micropro.common.pharmazip")).build()
				.apiInfo(apiInfo())
				.globalOperationParameters(parameters)
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, globalResponses)
				.globalResponseMessage(RequestMethod.POST, globalResponses)
				.globalResponseMessage(RequestMethod.PUT, globalResponses)
				.globalResponseMessage(RequestMethod.PATCH, globalResponses)
				.globalResponseMessage(RequestMethod.DELETE, globalResponses);
	}

	public ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Micropro PHARMAZIP REST api")
				.description("Generated documentation for Micropro PHARMAZIP").termsOfServiceUrl("").version("1.0.0")
				.contact(new Contact("", "", "")).build();
	}

}