package com.onetuks.csphinxserver.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode;

@Configuration
@EnableJpaAuditing
@EnableSpringDataWebSupport(pageSerializationMode = PageSerializationMode.VIA_DTO)
public class SpringDataConfig {

  //  @Bean
  //  public MappingMongoConverter mappingMongoConverter(
  //      MongoDatabaseFactory mongoDatabaseFactory, MongoMappingContext mongoMappingContext) {
  //    DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDatabaseFactory);
  //    return new MappingMongoConverter(dbRefResolver, mongoMappingContext);
  //    //    converter.setTypeMapper(new DefaultMongoTypeMapper(null));
  //  }
}
