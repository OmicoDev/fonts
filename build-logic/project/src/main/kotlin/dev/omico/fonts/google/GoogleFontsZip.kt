/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.fonts.google

import kotlinx.serialization.Serializable

@Serializable
data class GoogleFontsZip(
    val manifest: Manifest,
) {
    @Serializable
    data class Manifest(
        val files: List<File>,
        val fileRefs: List<FileRef>,
    ) {
        @Serializable
        data class File(
            val filename: String,
            val contents: String,
        )

        @Serializable
        data class FileRef(
            val filename: String,
            val url: String,
        )
    }
}
