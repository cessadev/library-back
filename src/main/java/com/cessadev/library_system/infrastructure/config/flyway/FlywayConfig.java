package com.cessadev.library_system.infrastructure.config.flyway;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class FlywayConfig {

  @Bean
  @Profile("dev")
  public FlywayMigrationStrategy cleanMigrateStrategy() {
    return flyway -> {
      // Solo para desarrollo - limpia y recrea
      flyway.clean();
      flyway.migrate();
    };
  }

  @Bean
  @Profile("!dev")
  public FlywayMigrationStrategy productionMigrationStrategy() {
    // Para producción - solo migraciones normales
    return Flyway::migrate;
  }
}
