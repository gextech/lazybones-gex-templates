var gulp = require('gulp');

var raml2code = require("raml2code/index.js");
var genPojos = require("raml2code/lib/generators/groovy/pojo.js");
var genJaxRs = require("raml2code/lib/generators/groovy/jaxrsInterface.js");
var genRetrofit = require("raml2code/lib/generators/java/retrofitClient.js");
var genJsClient = require("raml2code/lib/generators/javascript/jsClient");


var raml = require('gulp-raml');
var packagePojo = "${group}.dto";
var packageJaxRs = "${group}.jaxrs";
var packageClient = "${group}.client";

gulp.task('raml', function() {
    gulp.src('./src/index.raml')
        .pipe(raml())
        .pipe(raml.reporter('default'));
});


gulp.task("genPojos", ['raml'], function(){
    gulp.src('./src/index.raml')
        .pipe(raml2code({generator: genPojos, extra: {package: packagePojo}}))
        .pipe(gulp.dest('../commons/src/generated/groovy/${groupFolder}/dto'));
});

gulp.task("genJaxRs" , ['raml'], function(){
    gulp.src('./src/index.raml')
        .pipe(raml2code({generator: genJaxRs, extra: {package: packageJaxRs, importPojos: packagePojo}}))
        .pipe(gulp.dest('../api/src/generated/groovy/${groupFolder}/jaxrs'));
});


gulp.task("genClient" , ['raml'], function(){
    gulp.src('./src/index.raml')
        .pipe(raml2code({generator: genRetrofit, extra: {package: packageClient, importPojos: packagePojo}}))
        .pipe(gulp.dest('../client/src/generated/groovy/${groupFolder}/client'));
});

gulp.task("genJsClient" , ['raml'], function(){
  gulp.src('./src/index.raml')
    .pipe(raml2code({generator: genJsClient, extra: {package: packageClient, importPojos: packagePojo}}))
    .pipe(gulp.dest('../js-client'));
});

gulp.task('apidoc', function() {
    return gulp.src('./src/*')
        .pipe(raml2html())
        .pipe(gulp.dest('build'));
});

gulp.task('build', ['raml', 'genPojos', 'genJaxRs', 'genClient', 'genJsClient' ]);

