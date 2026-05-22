pipeline {
    agent any

    environment {
        TELEGRAM_TOKEN = credentials('telegram-token')
        TELEGRAM_CHAT_ID = credentials('telegram-chatid')
    }

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

        // ---- NOTIFICACIÓN POR GMAIL Y TELEGRAM (ÉXITO) ----
        success {
            // Gmail
            mail to: 'jhon04suazasanchez@gmail.com',
                 subject: "✅ Build #${env.BUILD_NUMBER} EN JENKINS - EXITOSO",
                 body: """\
                     ¡El build ha finalizado correctamente!
                     Proyecto: ${env.JOB_NAME}
                     Commit: ${env.GIT_COMMIT}
                     URL: ${env.BUILD_URL}
                 """

            // Telegram
            sh """curl -s -X POST https://api.telegram.org/bot${env.TELEGRAM_TOKEN}/sendMessage \\
                -d chat_id=${env.TELEGRAM_CHAT_ID} \\
                -d parse_mode=Markdown \\
                -d text="✅ *Build #${env.BUILD_NUMBER} EN JENKINS*%0A*PROYECTO:* ${env.JOB_NAME}%0A*COMMIT:* ${env.GIT_COMMIT}%0A*URL:* ${env.BUILD_URL}" """
        }

        // ---- NOTIFICACIÓN POR GMAIL Y TELEGRAM (FALLA) ----
        failure {
            // Gmail
            mail to: 'jhon04suazasanchez@gmail.com',
                 subject: "❌ Build #${env.BUILD_NUMBER} EN JENKINS - FALLÓ",
                 body: """\
                     El build ha fallado.
                     Revisa la consola: ${env.BUILD_URL}console
                     Proyecto: ${env.JOB_NAME}
                     Commit: ${env.GIT_COMMIT}
                 """

            // Telegram
            sh """curl -s -X POST https://api.telegram.org/bot${env.TELEGRAM_TOKEN}/sendMessage \\
                -d chat_id=${env.TELEGRAM_CHAT_ID} \\
                -d parse_mode=Markdown \\
                -d text="❌ *Build #${env.BUILD_NUMBER} EN JENKINS*%0A*PROYECTO:* ${env.JOB_NAME}%0A*COMMIT:* ${env.GIT_COMMIT}%0A*URL:* ${env.BUILD_URL}" """
        }
    }
}
