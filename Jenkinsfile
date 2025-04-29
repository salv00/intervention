pipeline {
    agent any

    tools {
        maven 'maven'
        jdk 'jdk-17'
    }

    stages {
        stage('Build & Test') {
            steps {
                echo '🧱 Compilation et tests unitaires...'
                withEnv(["JAVA_HOME=${tool 'jdk-17'}", "PATH+JAVA=${tool 'jdk-17'}/bin"]) {
                    sh 'mvn clean verify'
                }
            }
        }

        stage('Analyse SonarQube') {
            steps {
                echo '🔍 Analyse du code avec SonarQube...'
                withSonarQubeEnv('SonarQube_Server') {
                    withEnv(["JAVA_HOME=${tool 'jdk-17'}", "PATH+JAVA=${tool 'jdk-17'}/bin"]) {
                        sh 'mvn sonar:sonar'
                    }
                }
            }
        }

        stage('Quality Gate') {
            steps {
                echo '🧪 Vérification du Quality Gate...'
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
