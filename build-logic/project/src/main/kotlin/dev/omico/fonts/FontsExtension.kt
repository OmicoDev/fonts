/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.fonts

import org.gradle.api.provider.Property

interface FontsExtension {
    val family: Property<String>
    val provider: Property<FontsProvider>
}
