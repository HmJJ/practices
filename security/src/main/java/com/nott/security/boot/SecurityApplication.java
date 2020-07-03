package com.nott.security.boot;

import com.nott.security.common.config.DefaultProfileUtil;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

@ComponentScan({"com.nott.*.*"})
@MapperScan(basePackages = {"com.nott"})
@EnableJpaRepositories(basePackages = {"com.nott"})
@EnableConfigurationProperties({LiquibaseProperties.class})
@PropertySource(value = {"classpath:config/properties/redis.properties"}, encoding = "utf-8")
@SpringBootApplication
public class SecurityApplication implements InitializingBean {
	private static final Logger log = LoggerFactory.getLogger(SecurityApplication.class);
	private final Environment env;

	public SecurityApplication(Environment env) {
		this.env = env;
	}

	public void afterPropertiesSet() throws Exception {
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(new Class[]{SecurityApplication.class});
		/*BootappApplicationListener bootappApplicationListener = new BootappApplicationListener();
		bootappApplicationListener.loadSystemProperties();
		app.addListeners(new ApplicationListener[]{bootappApplicationListener});
		DefaultProfileUtil.addDefaultProfile(app);*/
		Environment env = app.run(args).getEnvironment();
		logApplicationStartup(env);
	}

	private static void logApplicationStartup(Environment env) {
		String protocol = "http";
		if (env.getProperty("server.ssl.key-store") != null) {
			protocol = "https";
		}

		String serverPort = env.getProperty("server.port");
		String contextPath = env.getProperty("server.servlet.context-path");
		if (StringUtils.isBlank(contextPath)) {
			contextPath = "/";
		}

		String hostAddress = "localhost";

		try {
			hostAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException var6) {
			log.warn("The host name could not be determined, using `localhost` as fallback");
		}

		log.info("\n----------------------------------------------------------\n\tApplication '{}' is running! Access URLs:\n\tLocal: \t\t{}://localhost:{}{}\n\tExternal: \t{}://{}:{}{}\n\tProfile(s): \t{}\n----------------------------------------------------------", new Object[]{env.getProperty("spring.application.name"), protocol, serverPort, contextPath, protocol, hostAddress, serverPort, contextPath, env.getActiveProfiles()});
		String configServerStatus = env.getProperty("configserver.status");
		if (configServerStatus == null) {
			configServerStatus = "Not found or not setup for this application";
		}

		log.info("\n----------------------------------------------------------\n\tConfig Server: \t{}\n----------------------------------------------------------", configServerStatus);
	}
}
