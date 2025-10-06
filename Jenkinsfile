pipeline {
    agent any
tools {
        jdk 'JDK21'
        maven 'maven'
    }
    environment {
        SPRING_DATASOURCE_URL = 'jdbc:mysql://mysql:3306/userDB'
        SPRING_DATASOURCE_USERNAME = 'mysqluser'
        SPRING_DATASOURCE_PASSWORD = 'password'
        IMAGE_NAME = 'userservice'
        TAG = "${env.BRANCH_NAME}-${env.BUILD_NUMBER}"
        EMAIL_RECIPIENTS = 'dibyabikashpradhan@gmail.com'
    }

    stages {
        stage('Build & Package') {
            steps {
                script {
                    retry(2) {
                        dir('UserService'){
                        bat 'mvn clean package -DskipTests'
                        }
                    }
                }
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: 'UserService/target/*.jar', fingerprint: true
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    bat 'docker build -t userservice:latest .'
                }
            }
        }

        stage('Docker Compose Deploy') {
            steps {
                script {
                    bat "docker-compose down"
                    bat "docker-compose up -d"
                }
            }
        }
    }

    post {
        success {
        mail(
        to: "${EMAIL_RECIPIENTS}",
        subject: "Build Succeeded: ${currentBuild.fullDisplayName}",
        body: "The Jenkins build was successful."
        )
            echo 'Pipeline succeeded!'
        }
        failure {
            mail(
            to: "${EMAIL_RECIPIENTS}",
                subject: "Build Failed: ${currentBuild.fullDisplayName}",
                body: "Check Jenkins build logs for details."
            )
        }
        always {
            bat 'docker system prune -f'
            echo 'Pipeline finished.'
        }
    }
}
