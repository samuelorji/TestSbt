pipeline {
    agent {
        docker {
            image 'openjdk:11-jre-slim'
            args '-v $HOME/.sbt:/root/.sbt'
            args '-v $HOME/.ivy2:/root/.ivy2'
        }
    
    }
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
