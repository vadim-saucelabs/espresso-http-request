#!groovy

// For more information about TestRunner please visit https://github.com/saucelabs/pipeline-test-runner

def test = {
    sh "./gradlew clean"
    sh "./gradlew assemble"
    sh "./gradlew assembleAndroidTest"
    sh "java -jar espressorunner-0.2.jar"
}

TestRunner {
    useDockerFile = true
    junitReportPath = "**/testobject/*.xml"
    steps = test
}
