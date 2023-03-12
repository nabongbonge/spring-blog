package com.springblog.config;

import com.springblog.domain.UsernameAuditorAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@Configuration
public class JpaConfig {

  @Bean
  public AuditorAware<String> auditorAware() {
    return new UsernameAuditorAware();
  }
}
