pipeline {
    agent any

    stages {
        stage('Build and Test') {
            steps {
                // Dar permisos de ejecución al Maven Wrapper en Linux
                sh 'chmod +x mvnw'
                // Usa el wrapper Maven incluido en tu proyecto
                sh './mvnw clean verify'
            }
        }
    }

    // ---------- NOTIFICACIONES ----------
    post {
        always {
            junit testResults: 'target/surefire-reports/*.xml',
                  allowEmptyResults: true
        }

        // ---- NOTIFICACIÓN POR GMAIL Y OUTLOOK (ÉXITO) ----
        success {
            mail to: 'jhon04suazasanchez@gmail.com, jhonsuazasanchez@outlook.com',
                 subject: "✅ Build #${env.BUILD_NUMBER} EN JENKINS - EXITOSO",
                 body: """\
                     ¡El build ha finalizado correctamente!
                     Proyecto: ${env.JOB_NAME}
                     Commit: ${env.GIT_COMMIT}
                     URL: ${env.BUILD_URL}
                 """
        }

        // ---- NOTIFICACIÓN POR GMAIL Y OUTLOOK (FALLA) ----
        failure {
            mail to: 'jhon04suazasanchez@gmail.com, jhonsuazasanchez@outlook.com',
                 subject: "❌ Build #${env.BUILD_NUMBER} EN JENKINS - FALLÓ",
                 body: """\
                     El build ha fallado.
                     Revisa la consola: ${env.BUILD_URL}console
                     Proyecto: ${env.JOB_NAME}
                     Commit: ${env.GIT_COMMIT}
                 """
        }
    }
}
