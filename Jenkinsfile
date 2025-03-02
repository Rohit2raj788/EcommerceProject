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
            // Fetch latest changes from remote
            bat 'git fetch --all'
            
            // Check if 'main' branch exists in remote
            def mainExists = bat(script: 'git branch -r | findstr "origin/main"', returnStdout: true).trim()
            
            if (mainExists) {
                // Compare changes between main and develop
                def missingTests = bat(script: 'git diff origin/main origin/develop --name-only -- src/test/java/testCases/', returnStdout: true).trim()
                
                if (missingTests) {
                    error "❌ ERROR: Missing Test Cases in develop:\n$missingTests"
                } else {
                    echo "✅ All Test Cases Exist"
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
