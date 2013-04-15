/*
 * Copyright 2013 Ben Manes. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
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

import com.googlecode.jsonschema2pojo.AnnotationStyle
import com.googlecode.jsonschema2pojo.Annotator
import com.googlecode.jsonschema2pojo.GenerationConfig
import com.googlecode.jsonschema2pojo.NoopAnnotator
import com.googlecode.jsonschema2pojo.SourceType

/**
 * The configuration properties.
 *
 * @author Ben Manes (ben.manes@gmail.com)
 * @see https://code.google.com/p/jsonschema2pojo
 */
public class JsonSchemaExtension implements GenerationConfig {
  boolean generateBuilders
  boolean usePrimitives
  Iterable<File> sourceFiles
  File targetDirectory
  String targetPackage
  char[] propertyWordDelimiters
  boolean useLongIntegers
  boolean includeHashcodeAndEquals
  boolean includeToString
  AnnotationStyle annotationStyle
  Class<? extends Annotator> customAnnotator
  boolean includeJsr303Annotations
  SourceType sourceType

  public JsonSchemaExtension() {
    // See DefaultGenerationConfig
    generateBuilders = false
    usePrimitives = false
    sourceFiles = []
    targetPackage = ''
    propertyWordDelimiters = [] as char[]
    useLongIntegers = false
    includeHashcodeAndEquals = true
    includeToString = true
    annotationStyle = AnnotationStyle.JACKSON
    customAnnotator = NoopAnnotator.class
    includeJsr303Annotations = false
    sourceType = SourceType.JSONSCHEMA
  }

  @Override
  public Iterator<File> getSource() {
    sourceFiles.iterator()
  }

  public void setSource(Iterable<File> files) {
    def copy = [] as List
    files.each { copy.add(it) }
    sourceFiles = copy
  }

  public void setAnnotationStyle(String style) {
    annotationStyle = AnnotationStyle.valueOf(style.toUpperCase())
  }

  public void setCustomAnnotator(String clazz) {
    customAnnotator = Class.forName(clazz)
  }

  public void setSourceType(String sourceType) {
    SourceType.JSONSCHEMA.valueOf(sourceType.toUpperCase())
  }

  @Override
  public String toString() {
    """|generateBuilders = ${generateBuilders}
       |usePrimitives = ${usePrimitives}
       |source = ${sourceFiles}
       |targetDirectory = ${targetDirectory}
       |targetPackage = ${targetPackage}
       |propertyWordDelimiters = ${Arrays.toString(propertyWordDelimiters)}
       |useLongIntegers = ${useLongIntegers}
       |includeHashcodeAndEquals = ${includeHashcodeAndEquals}
       |includeToString = ${includeToString}
       |annotationStyle = ${annotationStyle.toString().toLowerCase()}
       |customAnnotator = ${customAnnotator.getName()}
       |includeJsr303Annotations = ${includeJsr303Annotations}
       |sourceType = ${sourceType.toString().toLowerCase()}
     """.stripMargin()
  }
}
