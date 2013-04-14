# Gradle Json Schema to Java Plugin

[Json Schema to Java](http://www.jsonschema2pojo.org) generates a Java representation of your
json schema. The [schema reference](https://code.google.com/p/jsonschema2pojo/wiki/Reference)
describes the rules and their effect on generated Java types.

## Usage

This plugin is hosted on the Maven Central Repository.

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

jsonSchema2Pojo {
  targetPackage = 'com.example'
}
```

## Tasks

### `generateJsonSchema2Pojo`

Executes the code generator.
