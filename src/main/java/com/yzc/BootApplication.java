package com.yzc;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yzc.repository.impl.CustomRepositoryFactoryBean;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = CustomRepositoryFactoryBean.class) // 配置自定义的Repository
@EnableCaching
public class BootApplication implements CommandLineRunner{
		
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(BootApplication.class);
		app.run(args);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
		template.setConnectionFactory(redisConnectionFactory);
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);

		template.setValueSerializer(jackson2JsonRedisSerializer);
		template.setKeySerializer(new StringRedisSerializer());

		template.afterPropertiesSet();

		return template;
	}

	@Bean
	public Queue yzcQueue(){
		
		return new Queue("my-queue");
	}
	@Override
	public void run(String... arg0) throws Exception {
		
		rabbitTemplate.convertAndSend("my-queue", "hello");

	}

//	// http自动转向https的配置
//	 @Bean
//	 public EmbeddedServletContainerFactory servletContainer(){
//
//	 TomcatEmbeddedServletContainerFactory tomcat = new
//	 TomcatEmbeddedServletContainerFactory(){
//
//	 @Override
//	 protected void postProcessContext(Context context){
//	 SecurityConstraint securityConstraint = new SecurityConstraint();
//	 securityConstraint.setUserConstraint("CONFIDENTIAL");
//	 SecurityCollection collection = new SecurityCollection();
//	 collection.addPattern("/*");
//	 securityConstraint.addCollection(collection);
//	 context.addConstraint(securityConstraint);
//
//	 }
//	 };
//	 tomcat.addAdditionalTomcatConnectors(httpConnector());
//	 return tomcat;
//
//	 }
//
//	 @Bean
//	 public Connector httpConnector(){
//
//	 Connector connector = new
//	 Connector("org.apache.coyote.http11.Http11NioProtocol");
//	 connector.setScheme("http");
//	 connector.setPort(8080);
//	 connector.setSecure(false);
//	 connector.setRedirectPort(8443);
//
//	 return connector;
//	 }

}
