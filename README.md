# Lazybones Gex Templates

[![Apache License 2](https://img.shields.io/badge/license-ASF2-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0.txt)

This repository hosts some lazybone templates we use at Grupo Expansión. 
Right now it has a single template for creating Spring Boot APIs that use RAML.

## Requirements

[!["JDK"](https://img.shields.io/badge/JDK-8.0+-F30000.svg?style=flat)](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

## Usage

1. Add following snippet to `~/.lazybones/config.groovy` (If this dir/file doesn't exist, create it)

    ```groovy
    bintrayRepositories = [
        "gextech/oss"
    ]
    ```

2. Do a `lazybones list` to see available templates

3. Create an app from template (use `--with-git`)
    `lazybones create <template-name> <version> <dir-name> --with-git`

> Note: If you don't have [lazybones] installed, install that first :
>
> 1. Install gvm `curl -s get.gvmtool.net | bash`
>
> 2. Install lazybones via gvm `gvm install lazybones`

## Creating a Rest Boot API template

* Enter the following command:

    $ lazybones create rest-boot-api 0.1.0 my-boot-api --with-git


