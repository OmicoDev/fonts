import me.omico.gradle.initialization.includeAllSubprojectModules
import me.omico.gradm.addDeclaredRepositories

addDeclaredRepositories()

plugins {
    id("fonts.gradm")
    id("fonts.gradle-enterprise")
}

includeBuild("build-logic/project")

includeAllSubprojectModules("fonts")
