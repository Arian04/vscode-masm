plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

typealias NpmDependencyPair = Pair<String, Provider<String>>
kotlin {
    js {
        moduleName = "vscode"

        nodejs()

        binaries.library()
    }

    sourceSets {
        jsMain.dependencies {
            val npmDevTestDeps = arrayOf<NpmDependencyPair>(
                "@types/mocha" to libs.versions.types.mocha,
                "@vscode/test-cli" to libs.versions.vscode.testCli,
                "@vscode/test-electron" to libs.versions.vscode.testElectron,
            )

            val npmDevDependencies = arrayOf<NpmDependencyPair>(
                *npmDevTestDeps,
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