Jenkinsfile (Declarative Pipeline)
pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                echo 'Building...'
                sh 'mvn --version'
                sh 'mvn clean compile'
                sh 'mvn test'
                sh 'mvn package'
                echo 'Build'
            }
        }
    }
}