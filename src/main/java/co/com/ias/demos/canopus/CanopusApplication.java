package co.com.ias.demos.canopus;

import co.com.ias.demos.canopus.adapters.ObjectIdAdapter;
import co.com.ias.demos.canopus.adapters.StoreAdapter;
import co.com.ias.demos.canopus.domain.Store;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bson.types.ObjectId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { JacksonAutoConfiguration.class })
public class CanopusApplication {
	public static void main(String[] args) {
		SpringApplication.run(CanopusApplication.class, args);
	}


    public @Bean Gson gsonTemplate(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Store.class, new StoreAdapter());
        gsonBuilder.registerTypeAdapter(ObjectId.class, new ObjectIdAdapter());
        return gsonBuilder.create();
    }

	@Configuration
	@ConditionalOnClass(Gson.class)
	@ConditionalOnMissingClass(value = "com.fasterxml.jackson.core.JsonGenerator")
	@ConditionalOnBean(Gson.class)
	protected static class GsonHttpMessageConverterConfiguration {

		@Bean
		@ConditionalOnMissingBean
		public GsonHttpMessageConverter gsonHttpMessageConverter(Gson gson) {
			GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
			converter.setGson(gson);
			return converter;
		}

	}



}



