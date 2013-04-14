/*
 * Copyright 2013 Ben Manes. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.benmanes.gradle.jsonschema2pojo

import com.googlecode.jsonschema2pojo.cli.Jsonschema2Pojo
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * A task that performs code generation.
 *
 * @author Ben Manes (ben.manes@gmail.com)
 */
class GenerateJsonSchemaTask extends DefaultTask {
  def configuration

  GenerateJsonSchemaTask() {
    description = 'Generates Java classes from a json schema.'
    group = 'Build'

    project.afterEvaluate {
      configure()
      outputs.dir configuration.targetDirectory
      project.sourceSets.main.java.srcDirs += [ configuration.targetDirectory ]
    }
    dependsOn(project.tasks.processResources)
    project.tasks.compileJava.dependsOn(this)
  }

  @TaskAction
  def generate() {
    Jsonschema2Pojo.generate(configuration)
  }

  def configure() {
    configuration = project.jsonSchema2Pojo
    if (!configuration.source.hasNext()) {
      configuration.source = project.files("${project.sourceSets.main.output.resourcesDir}")
    }
    configuration.targetDirectory = configuration.targetDirectory ?:
      new File("${project.buildDir}/generated-sources/js2p")
  }
}
