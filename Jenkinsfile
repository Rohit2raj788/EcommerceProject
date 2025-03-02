pipeline {
    agent any  // Use any available Jenkins agent

    environment {
        MAVEN_HOME = "/usr/share/maven"  // Set Maven path if needed
        PATH = "$MAVEN_HOME/bin:$PATH"
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'develop', url: 'https://github.com/Rohit2raj788/EcommerceProject.git'
            }
        }

        stage('Verify Test Cases') {
            steps {
                script {
                    def missingTests = sh(script: "git diff main develop --name-only -- src/test/java/testCases/", returnStdout: true).trim()
                    if (missingTests) {
                        error "❌ ERROR: Missing Test Cases in develop: \n$missingTests"
                    } else {
                        echo "✅ All Test Cases Exist"
                    }
                }
            }
        }

        stage('Run Selenium Tests') {
            steps {
                bat 'mvn clean test'
            }
        }

        stage('Publish Test Reports') {
            steps {
                publishHTML([reportName: 'Extent Reports', reportDir: 'reports', reportFiles: 'ExtentReport*.html'])
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/reports/*.html', fingerprint: true
        }
        success {
            echo "✅ Build Successful"
        }
        failure {
            echo "❌ Build Failed! Check Reports"
        }
    }
}
