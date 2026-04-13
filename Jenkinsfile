pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build + Tests + Coverage') {
            steps {
                sh 'mvn clean verify -B'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                    junit '**/target/failsafe-reports/*.xml'
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            }
        }

        stage('Qualite statique') {
            steps {
                sh 'mvn checkstyle:checkstyle pmd:pmd pmd:cpd spotbugs:spotbugs -B'
            }
            post {
                always {
                    archiveArtifacts artifacts: 'target/checkstyle-result.xml, target/pmd.xml, target/cpd.xml, target/spotbugsXml.xml', allowEmptyArchive: true
                }
            }
        }
    }

    post {
        failure {
            emailext(
                subject: "Build FAILED - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """Le build a échoué.

Projet : ${env.JOB_NAME}
Build : #${env.BUILD_NUMBER}
URL : ${env.BUILD_URL}
""",
                to: "dydoudubg@gmail.com"
            )
        }
    }
}