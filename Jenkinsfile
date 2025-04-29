pipeline {
    agent any

    tools {
        maven 'maven'
        jdk 'jdk-17'
    }

    environment {
        SONARQUBE_ENV = 'SonarQube_Server' // Nom configuré dans Jenkins
    }

    stages {
        stage('Build & Test') {
            steps {
                echo '🧱 Compilation et tests unitaires...'
                sh 'mvn clean verify'
            }
        }

        stage('Analyse SonarQube') {
            steps {
                echo '🔍 Analyse du code avec SonarQube...'
                withSonarQubeEnv("${env.SONARQUBE_ENV}") {
                    sh 'mvn sonar:sonar'
                }
            }
        }

        stage('Quality Gate') {
            steps {
                echo '🧪 Vérification du Quality Gate...'
                // Attendre la réponse du serveur Sonar
                timeout(time: 2, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline terminé avec succès !'
        }
        failure {
            echo '❌ Pipeline échoué. Vérifie les logs ou le Quality Gate.'
        }
    }
}
