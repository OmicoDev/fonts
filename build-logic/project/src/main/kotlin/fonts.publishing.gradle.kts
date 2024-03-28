import me.omico.consensus.dsl.by
import java.time.LocalDate

plugins {
    id("me.omico.consensus.publishing")
}

consensus {
    publishing {
        when {
            environmentVariables.getOrDefault("CI", false) -> publishToNexusRepository()
            else -> publishToLocalRepository("MAVEN_OMICO_LOCAL_URI")
        }
        signing {
            if (isSnapshot) return@signing
            useGpgCmd()
            sign(publications)
        }
        publications.all {
            if (this !is MavenPublication) return@all
            pom {
                name by gradleProperty("POM_NAME")
                description by gradleProperty("POM_DESCRIPTION", "")
                url by "https://github.com/OmicoDev/fonts"
                licenses {
                    license {
                        name by "Copyright ${yearRange(2024)} Omico. All Rights Reserved."
                    }
                }
                developers {
                    developer {
                        id by "Omico"
                        name by "Omico"
                    }
                }
                scm {
                    url by "https://github.com/OmicoDev/fonts"
                    connection by "scm:git:https://github.com/OmicoDev/fonts.git"
                    developerConnection by "scm:git:https://github.com/OmicoDev/fonts.git"
                }
            }
        }
    }
}

fun yearRange(initialYear: Int, currentYear: Int = LocalDate.now().year): String =
    when (initialYear) {
        currentYear -> initialYear.toString()
        else -> "$initialYear-$currentYear"
    }
