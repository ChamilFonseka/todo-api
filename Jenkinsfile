pipeline {
    agent any

    tools {
        gradle "Gradle8.3"
        jdk "JDK17"
    }

    stages {
        stage('Build') {
            steps {
                bat "java -version"
                bat "gradle clean build"
            }
        }
    }
}
