plugins{
    `java-library`
    id("com.github.johnrengelman.shadow") version("8.1.1")
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT")
    api(project(":api"))
}

tasks {
    shadowJar {
        archiveBaseName.set(rootProject.name)
        archiveVersion.set("${rootProject.version}")

        relocate("io.github.francobm12.api", "io.github.francobm12.customevents.api")
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}