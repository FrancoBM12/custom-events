plugins{
    java
    kotlin("jvm")
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT")
    implementation(kotlin("stdlib-jdk8"))
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}