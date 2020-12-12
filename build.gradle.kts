import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version "1.4.10"
}

application {
    mainClass.set("util.Runner")
}

version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.reflections", "reflections", "0.9.12")
    testImplementation("junit", "junit", "4.13.1")
    testImplementation("org.hamcrest", "hamcrest", "2.2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

open class StartDay : DefaultTask() {

    private var day: Int = -1

    @Option(option = "day", description = "Number of the day to prepare")
    fun setDay(day: String) {
        try {
            this.day = day.toInt()
        }
        catch (e: NumberFormatException) {
            logger.error("NumberFormatException parsing day '$day'")
            throw RuntimeException("Day must be a valid integer - received '$day'")
        }
    }

    @TaskAction
    fun startDay() {
        if (day == -1) {
            logger.error("Day is -1 (uninitialized) - stopping")
            throw RuntimeException("Syntax: gradle startDay --day [integer]")
        }

        logger.info("Creating files for Day $day")
        val src = File("./src")
        if (!src.isDirectory) {
            logger.error("Cannot find src directory")
            throw RuntimeException("Cannot find src directory - please ensure you are executing this task from the project root")
        }

        getFilePaths().forEach { path ->
            val file = File(path)
            if (file.exists()) {
                logger.warn("$path already exists - skipping creation")
                return@forEach
            }
            file.createNewFile()
            logger.lifecycle("Created $path")

            // if file is a kotlin class, give it minimal skeleton
            if (!path.contains("input")) {
                if (path.contains("Test")) {
                    file.appendText("package days\n\nclass Day${day}Test {\n\n}\n")
                }
                else {
                    file.appendText("package days\n\nclass Day$day : Day($day) {\n\n}\n")
                }
            }
        }
    }

    private fun getFilePaths(): List<String> {
        val mainClass = "./src/main/kotlin/days/Day$day.kt"
        val mainInput = "./src/main/resources/input_day_$day.txt"
        val testClass = "./src/test/kotlin/days/Day${day}Test.kt"
        val testInput = "./src/test/resources/input_day_$day.txt"
        return listOf(mainClass, mainInput, testClass, testInput)
    }
}

tasks.register<StartDay>("startDay")
