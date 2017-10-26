// If you want, you can define your seed job in the DSL and create it via the REST API.
// See https://github.com/sheehan/job-dsl-gradle-example#rest-api-runner

job('seed') {
    scm {
        git {
            remote {
                github('pietern/caffe2-job-dsl-gradle')
            }
            branch('caffe2')
        }
    }
    triggers {
        githubPush()
    }
    steps {
        gradle 'clean test'
        dsl {
            external 'jobs/*Jobs.groovy'
            additionalClasspath 'src/main/groovy'
        }
    }
    publishers {
        archiveJunit 'build/test-results/**/*.xml'
    }
}
