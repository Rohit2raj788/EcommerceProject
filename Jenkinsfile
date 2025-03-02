pipeline {
    agent any  // Use any available Jenkins agent

    environment {
        MAVEN_HOME = "C:\\Program Files\\Apache\\maven"  // Adjust Maven path for Windows
        PATH = "${MAVEN_HOME}\\bin;${env.PATH}"
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
            bat 'git fetch --all'

            // Run git diff to compare test cases between main and develop
            def missingTests = bat(script: 'git diff origin/main origin/develop --name-only -- src/test/java/testCases/', returnStdout: true).trim()

            if (missingTests) {
                echo "❌ ERROR: Missing Test Cases in develop:\n$missingTests"
                error "Build failed due to missing test cases."
            } else {
                echo "✅ All Test Cases Exist"
            }
        }
    }
}



        stage('Run Selenium Tests') {
            steps {
                bat '"C:\\Program Files\\Apache\\maven\\bin\\mvn.cmd" clean test'
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
