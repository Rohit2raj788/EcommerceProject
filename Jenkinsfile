pipeline {
    agent any
    environment {
        JAVA_HOME = "C:\\Program Files\\Eclipse Adoptium\\jdk-17.0.14.7-hotspot"
        PATH = "${JAVA_HOME}\\bin;${env.PATH}"
    }
    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'develop', url: 'https://github.com/Rohit2raj788/EcommerceProject.git'
            }
        }

        stage('Verify Java') {
            steps {
                bat 'java -version'
                bat 'echo %JAVA_HOME%'
            }
        }

        stage('Build Project') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Run Selenium Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Publish Extent Reports') {
            steps {
                // Add code here to publish Extent Reports if required
                echo "Publishing Extent Reports..."
            }
        }
    }
}
