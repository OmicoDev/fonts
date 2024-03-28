import dev.omico.fonts.FontsExtension
import dev.omico.fonts.FontsProvider
import dev.omico.fonts.google.GoogleFontsDownloader
import org.gradle.kotlin.dsl.invoke

plugins {
    id("fonts.kotlin.jvm")
    id("fonts.publishing")
}

val fontsExtension = extensions.create(
    name = "fonts",
    type = FontsExtension::class,
)

afterEvaluate {
    if (fontsExtension.family.orNull.isNullOrEmpty()) return@afterEvaluate
    val provider = fontsExtension.provider.orNull ?: return@afterEvaluate
    val outputDirectory = layout.projectDirectory.dir("src/main/resources/fonts/${project.name.removePrefix("fonts-")}")
    val downloadFonts = when (provider) {
        FontsProvider.UNKNOWN -> return@afterEvaluate
        FontsProvider.GOOGLE ->
            tasks.register<GoogleFontsDownloader>("downloadFonts") {
                familyProperty.set(fontsExtension.family)
                outputDirectoryProperty.set(outputDirectory)
            }
    }
    val validateFonts = tasks.register("validateFonts") {
        dependsOn(downloadFonts)
        doLast {
            if (outputDirectory.asFile.listFiles().isNullOrEmpty().not()) return@doLast
            throw GradleException("No fonts downloaded")
        }
    }
    tasks.processResources {
        dependsOn(downloadFonts, validateFonts)
    }
}

consensus {
    publishing {
        createMavenPublication {
            from(components["kotlin"])
        }
    }
}
