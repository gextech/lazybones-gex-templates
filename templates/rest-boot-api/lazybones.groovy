import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils

def props = [:]
props.group = ask("Define value for 'group' [gex.example]: ", "gex.example", "group")
props.projectName = ask("Define value for 'projectName' [sample-project]: ", "sample-project", "projectName")
props.version = ask("Define value for 'version' [0.1.0-SNAPSHOT]: ", "0.1.0-SNAPSHOT", "version")

props.groupFolder = props.group.replace('.' as char, '/' as char)


processTemplates "gradle.properties", props
processTemplates "raml/package.json", props
processTemplates "raml/Gulpfile.js", props
processTemplates "raml/src/index.raml", props
processTemplates "api/src/main/groovy/**/*.groovy", props

File originApiFolder = new File(projectDir, "api/src/main/groovy/groupFolder/")
File destFolder = new File(projectDir, FilenameUtils.concat("api/src/main/groovy/", props.groupFolder))
destFolder.parentFile.mkdirs()

FileUtils.moveDirectory(originApiFolder, destFolder)
