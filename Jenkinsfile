pipeline {
    agent any

    tools {
        maven 'maven'
        jdk 'jdk-17'
    }

    environment {
        // On demande à Jenkins où il a installé le JDK
        JAVA_HOME = tool(name: 'jdk-17', type: 'jdk')
        // On s’assure que mvn utilise ce JAVA_HOME
        PATH       = "${JAVA_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Build & Test') {
            steps {
                echo '🧱 Compilation et tests unitaires…'
                sh 'mvn clean verify'
            }
        }

        stage('Analyse SonarQube') {
            steps {
                echo '🔍 Analyse SonarQube…'
                withSonarQubeEnv('SonarQube_Server') {
                    sh 'mvn sonar:sonar'
                }
            }
        }

        stage('Quality Gate') {
            steps {
                echo '🧪 Vérification du Quality Gate…'
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
