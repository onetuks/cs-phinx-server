package com.onetuks.csphinxserver;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

class ArchitectureTest {

  JavaClasses javaClasses;

  @BeforeEach
  void setUp() {
    javaClasses =
        new ClassFileImporter()
            .withImportOption(new ImportOption.DoNotIncludeTests()) // 테스트 클래스는 이 검증에서 제외
            .importPackages(getClass().getPackageName());
  }

  @Nested
  class ClassNameTest {

    @Test
    @DisplayName("controller 패키지 안에 있는 클래스는 RestController 로 끝난다.")
    void controller_ClassNamePostfix_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAnyPackage("..controller")
              .should()
              .haveSimpleNameEndingWith("RestController")
              .andShould()
              .beAnnotatedWith(RestController.class)
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("converter 패키지 안에 있는 클래스는 Converter 로 끝난다.")
    void converter_ClassNamePostfix_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAnyPackage("..converter")
              .and()
              .areNotAnonymousClasses()
              .should()
              .haveSimpleNameEndingWith("Converter")
              .andShould()
              .beAnnotatedWith(Component.class)
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("entity 패키지 안에 있는 클래스는 Entity, Node, Relation 으로 끝난다.")
    void entity_ClassNamePostfix_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAnyPackage("..entity")
              .should()
              .haveNameMatching("^.*(Entity|Node|Relation)$")
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("repository 패키지 안에 있는 클래스는 Repository 로 끝난다.")
    void repository_ClassNamePostfix_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAnyPackage("..repository")
              .should()
              .haveSimpleNameEndingWith("Repository")
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("persistence 패키지의 클래스는 Adapter 로 끝난다.")
    void persistence_ClassNamePostfix_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAnyPackage("..persistence")
              .should()
              .haveSimpleNameEndingWith("Adapter")
              .andShould()
              .beAnnotatedWith(Repository.class)
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("command 패키지 안에 있는 클래스는 Command 로 끝난다.")
    void command_ClassNamePostfix_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAnyPackage("..command")
              .should()
              .haveSimpleNameEndingWith("Command")
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("port.in 패키지 안에 있는 클래스는 UseCases 로 끝난다.")
    void useCases_ClassNamePostfix_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAnyPackage("..port.in")
              .should()
              .haveSimpleNameEndingWith("UseCases")
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("port.out 패키지 안에 있는 클래스는 Port 로 끝난다.")
    void port_ClassNamePostfix_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAnyPackage("..port.out")
              .should()
              .haveSimpleNameEndingWith("Port")
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("application 패키지 안에 있는 클래스는 Service 로 끝난다.")
    void service_ClassNamePostfix_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAnyPackage("..application")
              .should()
              .haveSimpleNameEndingWith("Service")
              .andShould()
              .beAnnotatedWith(Service.class)
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("config 패키지 안에 있는 클래스는 Config 로 끝난다.")
    void config_ClassNamePostfix_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAnyPackage("..config")
              .should()
              .haveSimpleNameEndingWith("Config")
              .andShould()
              .beAnnotatedWith(Configuration.class)
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }
  }

  @Nested
  class MethodNameTest {

    @Test
    @DisplayName("Controller 에서는 get, post, patch, put, delete 로 시작하는 메서드 이름을 사용한다.")
    void controller_MethodNamePrefix_Test() {
      ArchRule rule =
          ArchRuleDefinition.methods()
              .that()
              .arePublic()
              .and()
              .areDeclaredInClassesThat()
              .haveSimpleNameEndingWith("Controller")
              .should()
              .haveNameMatching("^(get|post|patch|put|delete).*")
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("converter 에서는 toDomain, toEntity, toNode, toRelation 으로 시작하는 메소드 이름을 사용한다.")
    void converter_MethodNamePrefix_Test() {
      ArchRule rule =
          ArchRuleDefinition.methods()
              .that()
              .arePublic()
              .and()
              .areDeclaredInClassesThat()
              .resideInAPackage("..converter")
              .should()
              .haveNameMatching("^to(Domain|Entity|Node|Relation).*")
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("port 에서는 create, read, update, delete 로 시작하는 메서드 이름을 사용한다.")
    void repository_MethodNamePrefix_Test() {
      ArchRule rule =
          ArchRuleDefinition.methods()
              .that()
              .arePublic()
              .and()
              .areDeclaredInClassesThat()
              .resideInAPackage("..port.out")
              .should()
              .haveNameMatching("^(create|read|update|delete).*")
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }
  }

  @Nested
  class DependencyTest {

    @Test
    @DisplayName("domain 패키지는 어떤 패키지도 의존하지 않는다.")
    void domain_DependOn_Test() {
      ArchRule rule =
          ArchRuleDefinition.noClasses()
              .that()
              .resideInAPackage("..domain..")
              .should()
              .onlyDependOnClassesThat()
              .resideInAnyPackage("..lombok..")
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("service 는 application, domain 만 의존한다")
    void service_DependOn_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAPackage("..application..")
              .should()
              .onlyDependOnClassesThat()
              .resideInAnyPackage(
                  "..domain..", "..application..", "..springframework..", "jakarta..", "java..")
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("in 패키지는 out 패키지는 의존하지 않는다")
    void in_DependOn_Test() {
      ArchRule rule =
          ArchRuleDefinition.noClasses()
              .that()
              .resideInAPackage("..in..")
              .should()
              .dependOnClassesThat()
              .resideInAPackage("..out..")
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("out 패키지는 in 패키지는 의존하지 않는다")
    void out_DependOn_Test() {
      ArchRule rule =
          ArchRuleDefinition.noClasses()
              .that()
              .resideInAPackage("..out..")
              .should()
              .dependOnClassesThat()
              .resideInAPackage("..in..")
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("Entity 는 persistence 패키지에서만 의존한다")
    void entity_HaveDependency_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAnyPackage("..entity..")
              .should()
              .onlyHaveDependentClassesThat()
              .resideInAnyPackage("..persistence..")
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("dto 는 오직 in 패키지에서만 의존한다")
    void dto_HaveDependency_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAnyPackage("..dto..")
              .should()
              .onlyHaveDependentClassesThat()
              .resideInAnyPackage("..in..")
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }
  }
}
