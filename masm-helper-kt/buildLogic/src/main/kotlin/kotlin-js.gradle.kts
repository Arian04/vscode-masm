// The code in this file is a convention plugin - a Gradle mechanism for sharing reusable build logic.
package buildlogic.convention

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JsMainFunctionExecutionMode
import org.jetbrains.kotlin.gradle.dsl.JsModuleKind

plugins {
    // Currently can't use the version catalog from convention plugins: https://github.com/gradle/gradle/issues/15383
    kotlin("multiplatform")
}

kotlin {
    js {
        nodejs()

        binaries.executable()

        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            target = "es2015"

            // vscode is freaking out when I try to run as ES modules
            moduleKind = JsModuleKind.MODULE_COMMONJS

            // This is a vscode extension, so the runtime will call the exported "activate" function, so
            // calling "main()" is unnecessary
            main = JsMainFunctionExecutionMode.NO_CALL
        }
    }
}