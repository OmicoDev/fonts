/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.fonts.google

import dev.omico.fonts.FontsDownloader
import dev.omico.fonts.internal.json

abstract class GoogleFontsDownloader : FontsDownloader() {
    override fun download() {
        val family = familyProperty.get()
        val outputDirectory = outputDirectoryProperty.get().asFile
        val url = "https://fonts.google.com/download/list?family=${family.replace(" ", "%20")}"
        val jsonContent = resources.text.fromUri(url).asString().replace(")]}'\n", "")
        val googleFontsZip = json.decodeFromString<GoogleFontsZip>(jsonContent)
        outputDirectory.deleteRecursively()
        outputDirectory.mkdirs()
        googleFontsZip.manifest.files.forEach { file ->
            outputDirectory.resolve(file.filename).writeText(file.contents)
        }
        val fontPrefix = "static/${family.replace(" ", "")}-"
        googleFontsZip.manifest.fileRefs
            .filter { fileRef -> fileRef.filename.startsWith(fontPrefix) }
            .forEach { fileRef ->
                val downloadFile = resources.text.fromUri(fileRef.url).asFile()
                val outputFile = outputDirectory.resolve(fileRef.filename.removePrefix(fontPrefix))
                downloadFile.copyTo(outputFile, overwrite = true)
            }
    }
}
