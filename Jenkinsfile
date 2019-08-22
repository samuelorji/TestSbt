pipeline {
    agent none 
    stages {
        stage('clean') {
            steps {
                echo 'Cleaning Project'
                sh '/usr/local/bin/sbt clean'
            }
        }
        stage('Compiling Project') {
            steps {
                echo 'Compiling project'
                sh '/usr/local/bin/sbt compile'
            }
        }
         stage('Running Tests') {
            steps {
                echo 'Running Tests'
                sh '/usr/local/bin/sbt test'
            }
        }
    }
}
