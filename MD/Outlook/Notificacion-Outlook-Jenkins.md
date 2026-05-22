# 📧 Integración de Jenkins con Outlook (Notificaciones CI/CD)

Este documento detalla el proceso paso a paso para configurar Jenkins de manera que envíe notificaciones automáticas (alertas de éxito o fallo) a la cuenta de correo de Outlook.

---

## 🛠️ Paso 1: Configuración en Jenkinsfile

Se modificó el archivo `Jenkinsfile` del proyecto para incluir el comando `mail`, apuntando a la dirección de correo de Outlook (`jhonsuazasanchez@outlook.com`). Jenkins utilizará su servidor de correo configurado para hacer la entrega en la bandeja de entrada de Outlook.

**Bloque de código agregado (éxito):**

```groovy
mail to: 'jhonsuazasanchez@outlook.com',
     subject: "✅ Build #${env.BUILD_NUMBER} EN JENKINS - EXITOSO",
     body: """\\
         ¡El build ha finalizado correctamente!
         Proyecto: ${env.JOB_NAME}
         Commit: ${env.GIT_COMMIT}
         URL: ${env.BUILD_URL}
     """
```

**Bloque de código agregado (fallo):**

```groovy
mail to: 'jhonsuazasanchez@outlook.com',
     subject: "❌ Build #${env.BUILD_NUMBER} EN JENKINS - FALLÓ",
     body: """\\
         El build ha fallado en la etapa de tests.
         Revisa la consola: ${env.BUILD_URL}console
         Proyecto: ${env.JOB_NAME}
         Commit: ${env.GIT_COMMIT}
     """
```

---

## 📸 Paso 2: Iteración 1 – Primera prueba de éxito (SUCCESS ✅)

Se realizó el primer `git push` con la configuración de correo integrada al `Jenkinsfile`. Jenkins ejecutó el pipeline correctamente y envió la notificación de éxito a la bandeja de entrada de Outlook.

*(Falta capturar la FOTO 1 cuando llegue el primer correo de éxito)*
![FOTO 1: Mensaje de éxito en Outlook](foto1_mensaje_exito_outlook.png)

---

## 📸 Paso 3: Iteración 2 – Ejecución automática y Error intencional (FAILURE ❌)

Se introdujo un fallo intencional en el archivo de pruebas `PracticaJenkinsApplicationTests.java` (`Assertions.fail()`). El Webhook de GitHub detectó el `push` automáticamente y Jenkins ejecutó el pipeline. Al fallar la etapa de tests, se envió la notificación de error a Outlook.

*(Falta capturar la FOTO 2 cuando llegue el correo de fallo)*
![FOTO 2: Mensaje de error en Outlook](foto2_error_outlook.png)

---

## 📸 Paso 4: Iteración 3 – Corrección del error (SUCCESS ✅)

Se eliminó el `Assertions.fail()` del archivo de pruebas, restaurando el test. Al hacer el nuevo `push`, Jenkins se disparó automáticamente, el pipeline pasó en verde y Outlook recibió el correo de éxito confirmando la reparación.

*(Falta capturar la FOTO 3 cuando llegue el correo de reparación)*
![FOTO 3: Mensaje de éxito tras corrección en Outlook](foto3_fix_success_outlook.png)

---

## ✅ Conclusiones

- **Notificaciones por Correo:** La integración con Outlook se logró utilizando la función nativa `mail` del pipeline declarativo de Jenkins.
- **Automatización total:** El webhook de GitHub y el túnel SSH (`localhost.run`) permitieron la ejecución automática del pipeline tras cada `push`, notificando el resultado en tiempo real a la bandeja de entrada del SENA.
- **Feedback visual:** El uso de emojis (✅ y ❌) en el Asunto del correo facilita la rápida identificación del estado del pipeline sin necesidad de abrir el mensaje.
