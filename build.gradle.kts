plugins {
    java
    id("org.springframework.boot") version "2.7.14"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

group = "com.andlopper"
version = "1"

java {
    sourceCompatibility = JavaVersion.VERSION_11
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("javax.validation:validation-api")
    compileOnly("org.projectlombok:lombok:1.18.28")
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("org.postgresql:postgresql")
    implementation("org.springdoc:springdoc-openapi-data-rest:1.7.0")
    implementation("org.springdoc:springdoc-openapi-ui:1.7.0")
    implementation("org.apache.poi:poi:5.2.3")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
