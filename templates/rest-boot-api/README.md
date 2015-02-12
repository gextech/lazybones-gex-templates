# Simple Spring Boot Rest Template

This is a simple project using Spring Boot REST API
it is highly opinionated, we chose to use:

* Spring Boot
* Jersey
* OAuth 2
* Postgresql
* GORM
* RAML contracts with code generation
* Java 8

It should be pretty easy to change the database, or OAuth security.
We would recommend against it.

## Start writing your code

Always define your contract first, you can do it by editing the `raml/src/index.raml`
The body of the requests and the responses is defined by json schemas, they are also
in the raml/src folder.

## Generating code

Code is generated using node, but we download that for you if you don't have it, just run:

```
./gradlew :raml:npmInstall :raml:installGulp :raml:build
```

### Helper scripts

There are a couple of scripts that you can use for help:

* `./fullBuild` It generates everything

