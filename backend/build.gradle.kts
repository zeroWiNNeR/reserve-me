plugins {
    java
    id("org.springframework.boot") version "3.1.4"
}

group = "com.reserveme"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

val springBootVersion = "3.1.4"
val snakeyamlVersion = "2.0"
val postgresJdbcVersion = "42.5.4"
val jacksonVersion = "2.14.2"
val mapStructVersion = "1.5.3.Final"
val springSecurityVersion = "6.1.1"
val hibernateVersion = "6.2.7.Final"
val logstashVersion = "7.2"
val logbackVersion = "1.4.5"
val janinoVersion = "3.1.9"

tasks.withType<JavaCompile> {
    val compilerArgs = options.compilerArgs
    compilerArgs.add("-Amapstruct.unmappedTargetPolicy=IGNORE")
    compilerArgs.add("-Amapstruct.defaultComponentModel=spring")
}

dependencies {
    annotationProcessor("org.hibernate:hibernate-core:$hibernateVersion")
    annotationProcessor("org.hibernate:hibernate-jpamodelgen:$hibernateVersion")
    annotationProcessor("org.mapstruct:mapstruct-processor:$mapStructVersion")
    implementation("org.springframework.boot:spring-boot-starter:$springBootVersion") {
        exclude("ch.qos.logback:logback-classic")
    }
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion") {
        exclude("javax.persistence:persistence-api")
    }
    implementation("org.springframework.boot:spring-boot-starter-security:$springBootVersion")
    implementation("org.springframework.security:spring-security-oauth2-jose:$springSecurityVersion")
    implementation("org.springframework.security:spring-security-oauth2-resource-server:$springSecurityVersion")

    implementation("net.logstash.logback:logstash-logback-encoder:$logstashVersion")
    implementation("ch.qos.logback:logback-core:$logbackVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("org.codehaus.janino:janino:$janinoVersion")
    runtimeOnly("org.yaml:snakeyaml:$snakeyamlVersion")

    runtimeOnly("org.postgresql:postgresql:$postgresJdbcVersion")
    implementation("com.fasterxml.jackson.core:jackson-core:$jacksonVersion")
    implementation("org.mapstruct:mapstruct:$mapStructVersion")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
//    testImplementation("org.springframework.security:spring-security-test:$springBootVersion")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
