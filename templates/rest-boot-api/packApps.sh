#!/usr/bin/env bash

./gradlew clean raml:npmInstall raml:installGulp raml:gulp_build raml:build  api:bootRepackage web:bootRepackage turbineApp:bootRepackage -x test
