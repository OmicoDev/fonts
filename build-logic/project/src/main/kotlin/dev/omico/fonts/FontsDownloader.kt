/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.fonts

import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.internal.file.FileOperations
import org.gradle.api.provider.Property
import org.gradle.api.resources.ResourceHandler
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import javax.inject.Inject

abstract class FontsDownloader : DefaultTask() {
    @get:Input
    abstract val familyProperty: Property<String>

    @get:OutputDirectory
    abstract val outputDirectoryProperty: DirectoryProperty

    @get:Inject
    protected abstract val fileOperations: FileOperations

    @get:Internal
    protected inline val resources: ResourceHandler
        get() = fileOperations.resources

    init {
        group = "fonts"
    }

    @TaskAction
    abstract fun download()
}
