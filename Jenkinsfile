pipeline {
    agent any

    tools {
        gradle "Gradle8.3"
        java "JDK17"
    }

    stages {
        stage('Cleanup Workspace') {
            steps {
                cleanWs()
            }
        }
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/ChamilFonseka/todo-api.git'
            }
        }
        stage('Build') {
            steps {
                bat "java -version"
                bat "gradle clean build"
            }
        }
    }
}
