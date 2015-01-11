def props = [:]
props.group = ask("Define value for 'group' [gex.example]: ", "gex.example", "group")
props.projectName = ask("Define value for 'projectName' [sample-project]: ", "sample-project", "projectName")
props.version = ask("Define value for 'version' [0.1.0-SNAPSHOT]: ", "0.1.0-SNAPSHOT", "version")

props.groupFolder = props.group.replaceAll('\\.', '/')

processTemplates "gradle.properties", props
processTemplates "raml/package.json", props
processTemplates "raml/Gulpfile.js", props
processTemplates "raml/src/index.raml", props
