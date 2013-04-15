# Gradle Json Schema to Pojo Plugin

[Json Schema to Pojo](http://www.jsonschema2pojo.org) generates a Java representation of your
json schema. The [schema reference](https://code.google.com/p/jsonschema2pojo/wiki/Reference)
describes the rules and their effect on generated Java types.

## Usage

This plugin is hosted on the Maven Central Repository. All actions are logged at the `info` level.

```groovy
apply plugin: 'jsonschema2pojo'

buildscript {
  repositories {
    mavenCentral()
  }
  
  dependencies {
    classpath 'com.github.ben-manes:gradle-jsonschema2pojo-plugin:0.1'
  }
}

repositories {
  mavenCentral()
}

dependencies {
  // Required if generating equals, hashCode, or toString methods 
  compile 'commons-lang:commons-lang:2.6'
  // Required if generating JSR-303 annotations
  compile 'javax.validation:validation-api:1.1.0.CR2'
  // Required if generating Jackson 2 annotations
  compile 'com.fasterxml.jackson.core:jackson-databind:2.1.4'
}

// Each configuration is set to the default value
jsonSchema2Pojo {
  // Whether to generate builder-style methods of the form withXxx(value) (that return this),
  // alongside the standard, void-return setters.
  generateBuilders = false
  
  // Whether to use primitives (long, double, boolean) instead of wrapper types where possible
  // when generating bean properties (has the side-effect of making those properties non-null).
  usePrimitives = false

  // Location of the JSON Schema file(s). This may refer to a single file or a directory of files.
  source = files("${sourceSets.main.output.resourcesDir}/json")

  // Target directory for generated Java source files.
  targetDirectory = file("${project.buildDir}/generated-sources/js2p")

  // Package name used for generated Java classes (for types where a fully qualified name has not
  // been supplied in the schema using the 'javaType' property).
  targetPackage = ''

  // The characters that should be considered as word delimiters when creating Java Bean property
  // names from JSON property names. If blank or not set, JSON properties will be considered to
  // contain a single word when creating Java Bean property names.
  propertyWordDelimiters = [] as char[]

  // Whether to use the java type long (or Long) instead of int (or Integer) when representing the
  // JSON Schema type 'integer'.  
  useLongIntegers = false

  // Whether to include hashCode and equals methods in generated Java types.
  includeHashcodeAndEquals = true

  // Whether to include a toString method in generated Java types.
  includeToString = true
  
  // The style of annotations to use in the generated Java types. Supported values:
  //  - jackson (alias of jackson2)
  //  - jackson2 (apply annotations from the Jackson 2.x library)
  //  - jackson1 (apply annotations from the Jackson 1.x library)
  //  - none (apply no annotations at all)
  annotationStyle = 'jackson'

  // A fully qualified class name, referring to a custom annotator class that implements
  // com.googlecode.jsonschema2pojo.Annotator and will be used in addition to the one chosen
  // by annotationStyle. If you want to use the custom annotator alone, set annotationStyle to none.
  customAnnotator = 'com.googlecode.jsonschema2pojo.NoopAnnotator'
  
  // Whether to include JSR-303 annotations (for schema rules like minimum, maximum, etc) in
  // generated Java types. Schema rules and the annotation they produce:
  //  - maximum = @DecimalMax
  //  - minimum = @DecimalMin
  //  - minItems,maxItems = @Size
  //  - minLength,maxLength = @Size
  //  - pattern = @Pattern
  //  - required = @NotNull
  // Any Java fields which are an object or array of objects will be annotated with @Valid to 
  // support validation of an entire document tree.
  includeJsr303Annotations = false

  // The type of input documents that will be read. Supported values:
  //  - jsonschema (schema documents, containing formal rules that describe the structure of json data)
  //  - json (documents that represent an example of the kind of json data that the generated Java types
  //          will be mapped to)  
  sourceType = 'jsonschema'
}
```

## Tasks

### `generateJsonSchema2Pojo`

Executes the code generator.
