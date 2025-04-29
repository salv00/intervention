pipeline {
    /*  
     * On exécute chaque étape dans un conteneur Docker
     * qui contient déjà Maven et Java 17 configurés.
     */
    agent {
        docker {
            image 'maven:3.9.9-openjdk-17'   // conteneur Maven + JDK17
            args  '-v $HOME/.m2:/root/.m2'    // monte ton repo Maven local pour le cache
        }
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Clonage du dépôt…'
                checkout scm
            }
        }

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
        success { echo '✅ Pipeline terminé avec succès !' }
        failure { echo '❌ Pipeline échoué. Vérifie les logs ou le Quality Gate.' }
    }
}
