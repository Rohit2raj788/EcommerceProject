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

            // Ensure 'main' branch exists in remote
            def mainExists = bat(script: 'git branch -r | findstr "origin/main"', returnStdout: true).trim()

            if (mainExists) {
                // Check differences and prevent false errors
                def missingTests = bat(script: 'git diff --quiet origin/main origin/develop -- src/test/java/testCases/ || echo "CHANGED"', returnStdout: true).trim()
                
                if (missingTests.contains("CHANGED")) {
                    echo "❌ ERROR: Test cases changed/missing. Please check."
                    error "Build failed due to missing test cases."
                } else {
                    echo "✅ No missing test cases. Proceeding with the build."
                }
            } else {
                echo "⚠️ Warning: origin/main branch not found. Skipping test case verification."
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
