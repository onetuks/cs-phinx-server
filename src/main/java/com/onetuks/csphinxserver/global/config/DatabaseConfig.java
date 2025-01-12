package com.onetuks.csphinxserver.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class DatabaseConfig {

  //  @Bean
  //  public MappingMongoConverter mappingMongoConverter(
  //      MongoDatabaseFactory mongoDatabaseFactory, MongoMappingContext mongoMappingContext) {
  //    DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDatabaseFactory);
  //    return new MappingMongoConverter(dbRefResolver, mongoMappingContext);
  //    //    converter.setTypeMapper(new DefaultMongoTypeMapper(null));
  //  }
}
