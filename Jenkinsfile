pipeline {
    agent any 
    stages {
        stage('clean') {
            steps {
                echo "Cleaning Project"
                sh "/usr/local/bin/sbt clean"
            }
        }
        stage('Compile') {
            steps {
                echo 'Compiling project'
                sh "/usr/local/bin/sbt compile"
            }
        }
         stage('Test') {
            steps {
                echo "Running Tests"
                sh "/usr/local/bin/sbt test"
            }
        }
    }
}
