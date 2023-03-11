package uz.java.Lotinkrill.config;

import com.ibm.icu.text.Transliterator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
@Getter
@Setter
@Configuration
@PropertySource("application.properties")
public class Botconfig {

    @Value("${bot.username}")
    String username;
    @Value("${bot.token}")
    String token;


    }
