import dev.omico.fonts.FontsProvider

plugins {
    id("fonts.bundle")
}

fonts {
    family = "Noto Sans SC"
    provider = FontsProvider.GOOGLE
}
