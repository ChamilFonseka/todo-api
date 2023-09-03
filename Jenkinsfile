pipeline {
    agent any

    tools {
        gradle "Gradle8.3"
    }

    stages {
        stage('Cleanup Workspace') {
            steps {
                cleanWs()
            }
        }

        stage('Build') {
            steps {
                bat "gradle clean build"
            }
        }
    }
}
