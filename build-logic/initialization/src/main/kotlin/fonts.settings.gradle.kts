import me.omico.gradle.initialization.includeAllSubprojectModules
import me.omico.gradm.addDeclaredRepositories

addDeclaredRepositories()

plugins {
    id("fonts.develocity")
    id("fonts.gradm")
}

includeBuild("build-logic/project")

includeAllSubprojectModules("fonts")
