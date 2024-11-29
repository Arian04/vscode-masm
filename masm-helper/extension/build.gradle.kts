import org.jetbrains.kotlin.gradle.targets.js.npm.npmProject
import java.nio.file.Files

plugins {
    // Apply the shared build logic from a convention plugin.
    alias(libs.plugins.buildLogic.js)

    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
}

typealias NpmDependencyPair = Pair<String, Provider<String>>

object MyConstants {
    const val EXTENSION_NAME = "masm-helper"
    const val MASM_LANGUAGE_ID = "masm"

    const val VSCODE_PACKAGING_FILES_DIR = "vscode-packaging"
    const val RESOURCES_DIR = "kotlin"
    const val PACKAGE_EXTENSION_WORKDIR = "package-extension"
    const val EXTENSION_ENTRYPOINT = "dist/extension.js"

    const val SNIPPETS_DIR = "${RESOURCES_DIR}/snippets"
    val snippetFiles = arrayOf("masm.json", "masm-irvine.json")
}

kotlin {
    js {
        compilations.all {
            with(MyConstants) {
                packageJson {
                    // name is val
                    main = EXTENSION_ENTRYPOINT
                    customField("displayName", "MASM Helper")
                    customField("publisher", "arianb")
                    customField(
                        "repository", mapOf(
                            "type" to "git",
                            "url" to "https://github.com/Arian04/vscode-masm.git",
                            "directory" to "masm-helper",
                        )
                    )
                    customField("version", "1.0.0")
                    customField(
                        "categories", arrayOf(
                            "Programming Languages",
                            "Snippets"
                        )
                    )

                    customField("extensionDependencies", arrayOf("blindtiger.masm"))
                    val vscodeVersion = libs.versions.types.vscode.get()
                    customField(
                        "engines",
                        mapOf(
                            "vscode" to vscodeVersion,
                        ),
                    )
                    customField(
                        "activationEvents", arrayOf(
                            "onCommand:workbench.action.tasks.runTask"
                        )
                    )

                    customField(
                        "contributes", mapOf(
                            "taskDefinitions" to arrayOf(
                                mapOf("type" to MASM_LANGUAGE_ID)
                            ),
                            "snippets" to buildList<Any> {
                                snippetFiles.forEach {
                                    add(
                                        mapOf(
                                            "language" to MASM_LANGUAGE_ID,
                                            "path" to "${SNIPPETS_DIR}/$it"
                                        )
                                    )
                                }
                            }.toTypedArray(),
                            makeConfiguration()
                        )
                    )
                    customField(
                        "scripts" to mapOf(
                            "vscode:prepublish" to "pnpm run package",
                            "compile" to "node esbuild.js",
                            "package" to "node esbuild.js --production",
                        )
                    )
                }
            }
        }
    }

    sourceSets {
        all {
            languageSettings {
                optIn("kotlin.js.ExperimentalJsExport")
            }
        }

        commonMain.dependencies {
            implementation(libs.bundles.kotlinxEcosystem)
        }

        jsMain.dependencies {
            implementation(projects.vscode)

            val npmDevDependencies = arrayOf<NpmDependencyPair>(
                "@types/vscode" to libs.versions.types.vscode,
                "@types/node" to libs.versions.types.node,
                "@typescript-eslint/eslint-plugin" to libs.versions.typescriptEslint,
                "@typescript-eslint/parser" to libs.versions.typescriptEslint,
                "@vscode/vsce" to libs.versions.vscode.vsce,
                "esbuild" to libs.versions.esbuild,
                "eslint" to libs.versions.eslint,
                "npm-run-all" to libs.versions.npmRunAll,
                "typescript" to libs.versions.typescript
            )
            for ((name, version) in npmDevDependencies) {
                implementation(devNpm(name, version.get()))
            }
        }
    }
}

fun makeConfiguration(): Pair<String, Any> {
    // So I can declare my configuration properties in the order it'll be shown
    var order = 0

    return "configuration" to mapOf(
        "title" to "MASM",
        "properties" to mapOf(
            arrayOfStringsConfiguration(
                "assembler.includePaths",
                ++order,
                "Specifies paths to search for library include files"
            ),
            arrayOfStringsConfiguration(
                "linker.libraryPaths",
                ++order,
                "Specifies paths to search for library files"
            ),
            arrayOfStringsConfiguration(
                "linker.libraries",
                ++order,
                "Specifies names of library files to link with"
            ),
            "${MyConstants.EXTENSION_NAME}.experimental.automaticallySetupIrvine" to mapOf(
                "order" to ++order,
                "type" to "boolean",
                "default" to false,
                "description" to "Automatically download and configure the Irvine library"
            )
        )
    )
}

fun arrayOfStringsConfiguration(settingsName: String, order: Int, description: String): Pair<String, Map<String, Any>> {
    return "${MyConstants.EXTENSION_NAME}.$settingsName" to mapOf(
        "order" to order,
        "type" to "array",
        "items" to mapOf(
            "type" to "string",
        ),
        "description" to description
    )
}

val buildOutputsPath = kotlin.js().compilations["main"].npmProject.dir
val packageExtensionDir = buildOutputsPath.map { it.dir(MyConstants.PACKAGE_EXTENSION_WORKDIR) }

val taskPackageExtensionPrepareResourcesDev = tasks.register<Copy>("packageExtensionPrepareResourcesDev") {
    dependsOn(tasks.jsDevelopmentExecutableCompileSync)

    val kotlinJsSrcDir = buildOutputsPath.map { it.dir("kotlin") }
    val kotlinJsDestDir = packageExtensionDir.map { it.dir(MyConstants.RESOURCES_DIR) }
    Files.createDirectories(file(packageExtensionDir).toPath())

    // Copy resources
    from(kotlinJsSrcDir) {
        include("**/*")
        exclude("*.js", "*.js.map")
        destinationDir = file(kotlinJsDestDir)
    }
}

val taskPackageExtensionPrepareResourcesProd = tasks.register<Copy>("packageExtensionPrepareResourcesProd") {
    dependsOn(tasks.jsProductionExecutableCompileSync)

    val kotlinJsSrcDir = buildOutputsPath.map { it.dir("kotlin") }
    val kotlinJsDestDir = packageExtensionDir.map { it.dir(MyConstants.RESOURCES_DIR) }
    Files.createDirectories(file(packageExtensionDir).toPath())

    // Copy resources
    from(kotlinJsSrcDir) {
        include("**/*")
        exclude("*.js", "*.js.map")
        destinationDir = file(kotlinJsDestDir)
    }
}

val taskPackageExtensionPrepareSources = tasks.register<Copy>("packageExtensionPrepareSources") {
    dependsOn(tasks.jsPackageJson)

    Files.createDirectories(file(packageExtensionDir).toPath())

    // Copy other important files
    into(packageExtensionDir)
    from(project.rootDir.resolve(MyConstants.VSCODE_PACKAGING_FILES_DIR)) {
        include("README.md", "LICENSE", ".vscodeignore", "esbuild.js")
    }
    from(project.rootDir.resolve("kotlin-js-store")) {
        include("package-lock.json")
    }
    from(buildOutputsPath) {
        include("package.json")
    }
}

val taskPackageExtensionNpmImport by tasks.register<Exec>("packageExtensionNpmImport") {
    dependsOn(taskPackageExtensionPrepareResourcesProd)
    dependsOn(taskPackageExtensionPrepareSources)

    workingDir = file(packageExtensionDir)
    commandLine("pnpm", "import")
}

val taskPackageExtensionNpmInstall by tasks.register<Exec>("packageExtensionNpmInstall") {
    dependsOn(taskPackageExtensionNpmImport)

    workingDir = file(packageExtensionDir)
    commandLine("pnpm", "install", "-D", "--frozen-lockfile")
}

tasks.register<Exec>("packageExtensionProduction") {
    dependsOn(taskPackageExtensionNpmInstall)

    workingDir = file(packageExtensionDir)
    commandLine("pnpm", "vsce", "package", "--no-dependencies")

    doLast {
        copy {
            from(packageExtensionDir)
            include("*.vsix")
            into(packageExtensionDir.map { it.dir("../dist-vsix") })
        }
    }
}

val taskPackageExtensionEsbuildDev by tasks.register<Exec>("packageExtensionEsbuildDev") {
    dependsOn(taskPackageExtensionNpmInstall)

    workingDir = file(packageExtensionDir)
    commandLine("pnpm", "run", "compile")
}

tasks.register<Exec>("debugExtension") {
    dependsOn(taskPackageExtensionEsbuildDev)

    commandLine("code", "--inspect-extensions=9229", "--extensionDevelopmentPath=${packageExtensionDir.get()}")
}

