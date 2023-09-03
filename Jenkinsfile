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

        stage('Checkout from SCM') {
            steps {

                git 'https://github.com/ChamilFonseka/todo-api.git'


                bat "gradle clean build"
            }
        }
    }
}
