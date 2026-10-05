buildscript {
    dependencies {
        // Align AGP 9's built-in Kotlin with the Kotlin 2.4 stdlib that our
        // libraries pull in transitively (AGP's compiler otherwise reads ≤2.3 metadata).
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.4.20")
    }
}

plugins {
    id("com.android.application") version "9.4.1" apply false
}
