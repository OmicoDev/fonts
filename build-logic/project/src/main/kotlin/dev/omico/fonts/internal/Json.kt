/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.fonts.internal

import kotlinx.serialization.json.Json

internal val json: Json =
    Json {
        ignoreUnknownKeys = true
    }
