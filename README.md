## Login with Spring Security

Tech Stack
- Java 21
- Spring Boot 3.2.3
- Spring Security
- Spring Data JPA + MariaDB
- Spring Redis + IO.JsonWebToken
---
```
  dependencies {
  	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
  	implementation 'org.springframework.boot:spring-boot-starter-data-redis'
  	implementation 'org.springframework.boot:spring-boot-starter-oauth2-client'
  	implementation 'org.springframework.boot:spring-boot-starter-security'
  	implementation 'org.springframework.boot:spring-boot-starter-web'
  	implementation group: 'io.jsonwebtoken', name: 'jjwt-api', version: '0.12.5'
  
  	compileOnly 'org.projectlombok:lombok'
  
  	developmentOnly 'org.springframework.boot:spring-boot-devtools'
  
  	runtimeOnly 'org.mariadb.jdbc:mariadb-java-client'
  	runtimeOnly group: 'io.jsonwebtoken', name: 'jjwt-impl', version: '0.12.5'
  	runtimeOnly group: 'io.jsonwebtoken', name: 'jjwt-jackson', version: '0.12.5'
  
  	annotationProcessor 'org.projectlombok:lombok'
  
  	testImplementation 'org.springframework.boot:spring-boot-starter-test'
  	testImplementation 'org.springframework.security:spring-security-test'
  }
```
