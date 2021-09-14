package com.dolby.pastebin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Spring boot main application.
 *
 * @author ravi shanker katta
 * @since 2021 Sep
 * @version 1.0
 */
@SpringBootApplication
@PropertySources({@PropertySource("classpath:pastebin.properties")})
public class DolbyPastebinApplication {

  public static void main(String[] args) {
    SpringApplication.run(DolbyPastebinApplication.class, args);
  }
}
