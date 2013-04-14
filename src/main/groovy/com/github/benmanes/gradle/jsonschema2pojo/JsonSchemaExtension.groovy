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
import com.googlecode.jsonschema2pojo.DefaultGenerationConfig
import com.googlecode.jsonschema2pojo.SourceType

/**
 * The configuration properties.
 *
 * @author Ben Manes (ben.manes@gmail.com)
 * @see https://code.google.com/p/jsonschema2pojo/
 */
public class JsonSchemaExtension extends DefaultGenerationConfig {

  /**
   * Add the output directory to the project as a source root, so that the generated java types
   * are compiled and included in the project artifact.
   */
  boolean addCompileSourceRoot = true

  /**
   * The style of annotations to use in the generated Java types. Supported values:
   * <ul>
   *   <li>jackson2 (apply annotations from the Jackson 2.x library)
   *   <li>jackson1 (apply annotations from the Jackson 1.x library)
   *   <li>none (apply no annotations at all)
   * </ul>
   */
  String annotationStyle = 'jackson2'

  /**
   * A fully qualified class name, referring to a custom annotator class that implements
   * {@link com.googlecode.jsonschema2pojo.Annotator} and will be used in addition to the
   * one chosen by annotationStyle.
   */
  String customAnnotator = 'com.googlecode.jsonschema2pojo.NoopAnnotator'

  public Iterator<File> getSource() {
    [new File("src/test/resources/json/address.json")].iterator()
  }
}
