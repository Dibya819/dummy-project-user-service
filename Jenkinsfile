pipeline {
    agent any

    environment {
        SPRING_DATASOURCE_URL = 'jdbc:mysql://mysql:3306/userDB'
        SPRING_DATASOURCE_USERNAME = 'mysqluser'
        SPRING_DATASOURCE_PASSWORD = 'password'
        IMAGE_NAME = 'userservice'
        TAG = "${env.BRANCH_NAME}-${env.BUILD_NUMBER}"
    }
        stages {
            stage('Build & Package') {
                steps {
                    script {
                        retry(2) {
                             def mvnHome = tool name: 'maven'
                             bat "${mvnHome}\\bin\\mvn clean package -DskipTests"
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
                           bat "docker build -t ${env.IMAGE_NAME}:${env.TAG} ."
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
               echo 'Pipeline succeeded!'
           }
           failure {
               mail(
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
