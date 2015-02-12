import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils

def props = [:]
props.group = ask("Define value for 'group' [gex.example]: ", "gex.example", "group")
props.projectName = ask("Define value for 'projectName' [sample-project]: ", "sample-project", "projectName")
props.version = ask("Define value for 'version' [0.1.0-SNAPSHOT]: ", "0.1.0-SNAPSHOT", "version")
props.dockerPrefix = props.projectName.replaceAll('-', '').replaceAll('_', '')
props.jdbcHost = ask("Define value for 'jdbcHost' [localhost]: ", "localhost", "jdbcHost")
props.jdbcPort = ask("Define value for 'jdbcPort' [5432]: ", "5432", "jdbcPort")
props.jdbcDb = ask("Define value for 'jdbcDb' [rest_api]: ", "rest_api", "jdbcDb")
props.jdbcUsername = ask("Define value for 'jdbcUsername' [dbuser]: ", "dbuser", "jdbcUsername")
props.jdbcPassword = ask("Define value for 'jdbcPassword' [dbpass]: ", "dbpass", "jdbcPassword")

props.groupFolder = props.group.replace('.' as char, '/' as char)

processTemplates "docker/fig.yml", props
processTemplates "docker/database/setup.sh", props
processTemplates "**/application.yaml", props
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
  'client/src/test/groovy/groupFolder/': FilenameUtils.concat('client/src/test/groovy/', props.groupFolder)
]

conversion.each { k, v ->
  File originApiFolder = new File(projectDir, k)
  File destFolder = new File(projectDir, v)
  destFolder.parentFile.mkdirs()

  FileUtils.moveDirectory(originApiFolder, destFolder)
}

