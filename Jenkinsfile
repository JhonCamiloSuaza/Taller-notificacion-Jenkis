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

        // ---- NOTIFICACIÓN (ÉXITO) ----
        success {
            // Notificación Gmail
            mail to: 'jhon04suazasanchez@gmail.com',
                 subject: "✅ Build #${env.BUILD_NUMBER} EN JENKINS - EXITOSO",
                 body: """\
                     ¡El build ha finalizado correctamente!
                     Proyecto: ${env.JOB_NAME}
                     Commit: ${env.GIT_COMMIT}
                     URL: ${env.BUILD_URL}
                 """
            
            // Notificación Discord
            sh """
                curl -H "Content-Type: application/json" \\
                -d '{"content": "✅ **ÉXITO EN JENKINS** | **Proyecto:** ${env.JOB_NAME} | **Build:** #${env.BUILD_NUMBER} | **Revisa aquí:** ${env.BUILD_URL}"}' \\
                https://discordapp.com/api/webhooks/1507119197937733735/HCfw4Ch9k8aeUn2hvCE-uKMKUjuJLwv2Nqkm5ZGkq0hqHXbWxdK7tiVi8Ge83ZsscFpX
            """
        }

        // ---- NOTIFICACIÓN (FALLA) ----
        failure {
            // Notificación Gmail
            mail to: 'jhon04suazasanchez@gmail.com',
                 subject: "❌ Build #${env.BUILD_NUMBER} EN JENKINS - FALLÓ",
                 body: """\
                     El build ha fallado.
                     Revisa la consola: ${env.BUILD_URL}console
                     Proyecto: ${env.JOB_NAME}
                     Commit: ${env.GIT_COMMIT}
                 """

            // Notificación Discord
            sh """
                curl -H "Content-Type: application/json" \\
                -d '{"content": "❌ **FALLO EN JENKINS** | **Proyecto:** ${env.JOB_NAME} | **Build:** #${env.BUILD_NUMBER} | **Revisa la consola:** ${env.BUILD_URL}console"}' \\
                https://discordapp.com/api/webhooks/1507119197937733735/HCfw4Ch9k8aeUn2hvCE-uKMKUjuJLwv2Nqkm5ZGkq0hqHXbWxdK7tiVi8Ge83ZsscFpX
            """
        }
    }
}
