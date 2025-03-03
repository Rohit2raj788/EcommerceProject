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
                bat 'mvn test -DsuiteXmlFile=master.xml -Dbrowsers=chrome -DoperatingSystem=Windows'
            }
        }

        stage('Publish Extent Reports') {
    steps {
        script {
            def localReportPath = "reports"
            def jenkinsReportPath = "target\\extent-reports"  

            // Ensure target directory exists
            bat "mkdir ${jenkinsReportPath}"

            // Copy reports from local to Jenkins workspace
            bat "xcopy /E /Y ${localReportPath} ${jenkinsReportPath}"

            // Verify if Extent Report exists before publishing
            def reportFiles = findFiles(glob: 'target/extent-reports/*.html')
            if (reportFiles.length > 0) {
                echo "✅ Extent Report found, publishing..."
                publishHTML([
                    reportDir: 'target/extent-reports',
                    reportFiles: 'ExtentReport_*.html',
                    reportName: 'Extent Reports',
                    keepAll: true
                ])
            } else {
                echo "❌ Extent Report NOT found. Check test execution."
                error("Extent Report not generated.")
            }
        }
    }
}


    }
}
