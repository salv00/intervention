pipeline {
  agent any

  environment {
    MAVEN_OPTS = "-Dmaven.repo.local=${env.WORKSPACE}/.m2/repository"
  }

  stages {
    stage('Build & Test') {
      steps {
        echo '🧱 Compilation et tests unitaires…'
        script {
          docker.image('maven:3.9.9-openjdk-17').inside("-v ${env.WORKSPACE}/.m2:/root/.m2") {
            sh 'mvn clean verify'
          }
        }
      }
    }

    stage('Analyse SonarQube') {
      steps {
        echo '🔍 Analyse SonarQube…'
        script {
          docker.image('maven:3.9.9-openjdk-17').inside("-v ${env.WORKSPACE}/.m2:/root/.m2") {
            withSonarQubeEnv('SonarQube_Server') {
              sh 'mvn sonar:sonar'
            }
          }
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
