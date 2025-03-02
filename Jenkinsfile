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
            // Fetch latest changes from remote
            bat 'git fetch --all'

            // Check if 'main' branch exists remotely
            def mainExists = bat(script: 'git branch -r | findstr "origin/main"', returnStdout: true).trim()

            if (mainExists) {
                // Debugging: List all test files
                bat 'dir /s /b src\\test\\java\\testCases\\ > test_files_list.txt'
                bat 'git ls-tree -r origin/develop --name-only > develop_files.txt'
                bat 'git ls-tree -r origin/main --name-only > main_files.txt'

                // Check for missing test cases
                def missingTests = bat(script: 'git diff --name-only origin/main origin/develop -- src/test/java/testCases/', returnStdout: true).trim()

                if (missingTests) {
                    echo "❌ ERROR: Test cases changed/missing:\n$missingTests"
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
