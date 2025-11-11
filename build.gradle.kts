plugins {
    alias(libs.plugins.dependencyManagement)
    alias(libs.plugins.springBoot)
    id("java")
}

group = "org.ruyibang"
version = (findProperty("projectVersion") as? String ?: "0.0-SNAPSHOT")



dependencies {
    implementation(platform("org.springframework.ai:spring-ai-bom:1.0.3"))
    implementation("org.ruyibang:ottplatform.core:[0.0,)")
    implementation("org.ruyibang:ottplatform.entity:[0.0,)")

    implementation("org.springframework.boot:spring-boot-starter-actuator")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.apache.commons:commons-lang3")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation("org.springframework.ai:spring-ai-starter-model-openai")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.maxmind.geoip2:geoip2:4.1.0")
    runtimeOnly("org.postgresql:postgresql")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}