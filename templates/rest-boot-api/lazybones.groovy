@GrabResolver(name='jcenter', root='http://jcenter.bintray.com/')
@Grab(group="uk.co.cacoethes", module="groovy-handlebars-engine", version="0.2")
import uk.co.cacoethes.handlebars.HandlebarsTemplateEngine
import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils

registerEngine "hbs", new HandlebarsTemplateEngine()

def props = [:]
props.group = ask("Define value for 'group' [gex.example]: ", "gex.example", "group")
props.projectName = ask("Define value for 'projectName' [sample-project]: ", "sample-project", "projectName")
props.version = ask("Define value for 'version' [0.1.0-SNAPSHOT]: ", "0.1.0-SNAPSHOT", "version")
props.jdbcHost = ask("Define value for 'jdbcHost' [localhost]: ", "localhost", "jdbcHost")
props.jdbcPort = ask("Define value for 'jdbcPort' [5432]: ", "5432", "jdbcPort")
props.jdbcDb = ask("Define value for 'jdbcDb' [rest_api]: ", "rest_api", "jdbcDb")
props.jdbcUsername = ask("Define value for 'jdbcUsername' [dbuser]: ", "dbuser", "jdbcUsername")
props.jdbcPassword = ask("Define value for 'jdbcPassword' [dbpass]: ", "dbpass", "jdbcPassword")
props.includeClient = ask("Do you want to include the java client?: [Y] ", "Y", "includeClient")?.toLowerCase() == 'y'
props.includeJsClient = ask("Do you want to include the js client?: [Y] ", "Y", "includeJsClient")?.toLowerCase() == 'y'
props.includeHystrix = ask("Do you want to include hystrix?: [Y] ", "Y", "includeHystrix")?.toLowerCase() == 'y'
if(props.includeHystrix) {
  props.includeHystrixMonitoring = ask("Do you want to include hystrix monitoring?: [Y] ", "Y", "includeHystrixMonitoring")?.toLowerCase() == 'y'
} else {
  props.includeHystrixMonitoring = false
}
props.includeDocker = ask("Do you want to include docker and docker-compose templates?: [Y] ", "Y", "includeDocker")?.toLowerCase() == 'y'

props.dockerPrefix = props.projectName.replaceAll('-', '').replaceAll('_', '')
props.groupFolder = props.group.replace('.' as char, '/' as char)

processTemplates "settings.gradle", props
processTemplates "api/build.gradle", props
processTemplates "core/build.gradle", props
processTemplates "docker/docker-compose.yml", props
processTemplates "docker/database/setup.sh", props
processTemplates "**/application.yaml", props
processTemplates "**/messages_en.properties", props
processTemplates "gradle.properties", props
processTemplates "js-client/package.json", props
processTemplates "raml/package.json", props
processTemplates "raml/Gulpfile.js", props
processTemplates "raml/src/index.raml", props
processTemplates "*/src/*/groovy/**/*.groovy", props
processTemplates "*/src/*/java/**/*.java", props

def conversion = [
  'api/src/main/groovy/groupFolder/': FilenameUtils.concat('api/src/main/groovy/', props.groupFolder),
  'api/src/test/groovy/groupFolder/': FilenameUtils.concat('api/src/test/groovy/', props.groupFolder),
  'core/src/main/groovy/groupFolder/': FilenameUtils.concat('core/src/main/groovy/', props.groupFolder),
  'core/src/test/groovy/groupFolder/': FilenameUtils.concat('core/src/test/groovy/', props.groupFolder),
  'domain/src/main/groovy/groupFolder/': FilenameUtils.concat('domain/src/main/groovy/', props.groupFolder),
  'client/src/main/java/groupFolder/': FilenameUtils.concat('client/src/main/java/', props.groupFolder),
  'client/src/test/groovy/groupFolder/': FilenameUtils.concat('client/src/test/groovy/', props.groupFolder),
  'web/src/main/groovy/groupFolder/': FilenameUtils.concat('web/src/main/groovy/', props.groupFolder),
  'turbineApp/src/main/groovy/groupFolder/': FilenameUtils.concat('turbineApp/src/main/groovy/', props.groupFolder)
]

conversion.each { k, v ->
  File originApiFolder = new File(projectDir, k)
  File destFolder = new File(projectDir, v)
  destFolder.parentFile.mkdirs()

  FileUtils.moveDirectory(originApiFolder, destFolder)
}

if(!props.includeClient) {
  FileUtils.forceDelete(new File(projectDir, "client"))
}

if(!props.includeJsClient) {
  FileUtils.forceDelete(new File(projectDir, "js-client"))
}

if(!props.includeHystrix) {
  def apiRoot = new File(projectDir, conversion['api/src/main/groovy/groupFolder/'])
  def coreRoot = new File(projectDir, conversion['core/src/main/groovy/groupFolder/'])
  def clientTestRoot = new File(projectDir, conversion['client/src/test/groovy/groupFolder/'])
  def files = [
    new File(apiRoot, 'restv1/XkcdResourceV1.groovy'),
    new File(clientTestRoot, 'XkcdResourceSpec.groovy'),
    new File(coreRoot, 'service/impl/XkcdServiceImpl.groovy'),
    new File(coreRoot, 'service/XkcdService.groovy')
  ]
  files.each {
    if(it.exists()) {
      FileUtils.forceDelete(it)
    }
  }
}

if(!props.includeHystrixMonitoring) {
  def hystrixDirectories = ['web', 'turbineApp']
  hystrixDirectories.each {
    FileUtils.forceDelete(new File(projectDir, it))
  }
}

if(!props.includeDocker) {
  FileUtils.forceDelete(new File(projectDir, "docker"))
}

if (hasFeature("scmExclusions")) {
    scmExclusions ".gradle",
        "build",
        "out",
        ".gradletasknamecache",
        "*.iml",
        "*.ipr",
        "*.iws",
        "*.idea",
        "README.html",
        "*.log",
        "node_modules/",
        "generated/",
        "2.2/",
        "2.3/",
        "out.txt",
        "*.lock",
        "*.bin",
        "*.jar",
        "*.class"
}
