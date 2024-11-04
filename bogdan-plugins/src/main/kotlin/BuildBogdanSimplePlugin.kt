

import org.gradle.api.Plugin
import org.gradle.api.Project


class BuildBogdanSimplePlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.tasks.create("bogdanSimpleTask"){
            println("Simple Task!")
        }
    }
}