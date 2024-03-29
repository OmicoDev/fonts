/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.fonts.internal

import okhttp3.OkHttpClient
import okhttp3.Request
import org.gradle.api.GradleException
import java.io.File

internal fun downloadFile(url: String, outputFile: File) {
    val request = Request.Builder().url(url).build()
    client.newCall(request).execute().use { response ->
        if (!response.isSuccessful) {
            throw GradleException("Cannot download $url. Response: ${response.code} ${response.message}")
        }
        outputFile.writeBytes(response.body.bytes())
    }
}

private val client: OkHttpClient = OkHttpClient()
