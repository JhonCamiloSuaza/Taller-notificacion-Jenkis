# 📱 Integración de Jenkins con Telegram (Notificaciones CI/CD)

> **Objetivo:** Documentar la configuración del bot de Telegram, las credenciales en Jenkins y las notificaciones automáticas de éxito o fallo en el pipeline.
> **Rama de entrega:** `feat-notificacion-telegram` (MD completo + evidencias). Las ramas `feature/auth-error-telegram` y `feature/login-ui-telegram` se usan solo para disparar builds de prueba.

---

## 🛠️ Paso 1: Configuración del Bot y Credenciales

### Paso 1.1: Crear el bot y obtener el token

1. En Telegram, abre **@BotFather** y crea un bot (comando `/newbot` o similar).
2. Copia el **token** que te entrega BotFather; lo usarás en Jenkins como `telegram-token`.

---
📸 **FOTO 1 A TOMAR:** Captura de la conversación con **@BotFather** donde se ve el **token** del bot (por ejemplo `@CamiloJenkis_bot`).
Guarda la imagen como `foto1_bot_telegram.png` en la carpeta `MD/Telegram/`:

![FOTO 1: Token del bot Telegram](foto1_bot_telegram.png)
---

### Paso 1.2: Obtener el Chat ID

1. Abre **@userinfobot** (u otro bot de ID) y envía `/start` o tu contacto.
2. Anota el **Chat ID** numérico; lo usarás en Jenkins como `telegram-chatid`.
3. Envía `/start` a tu bot para que pueda escribirte después.

---
📸 **FOTO 2 A TOMAR:** Captura donde se ve tu **Chat ID** (respuesta de @userinfobot o pantalla equivalente).
Guarda la imagen como `foto2_chatid_telegram.png` en la carpeta `MD/Telegram/`:

![FOTO 2: Chat ID Telegram](foto2_chatid_telegram.png)
---

### Paso 1.3: Guardar credenciales en Jenkins

1. En Jenkins: **Manage Jenkins** → **Credentials** → **System** → **Global credentials**.
2. Crea dos credenciales tipo **Secret text**:
   - ID: `telegram-token` → valor: token del bot.
   - ID: `telegram-chatid` → valor: Chat ID.
3. **Scope:** Global (para que el `Jenkinsfile` las use con `credentials('...')`).

---
📸 **FOTO 3 A TOMAR:** Captura del **Credentials Store** mostrando las credenciales `telegram-token` y `telegram-chatid` (sin exponer los valores completos si puedes).
Guarda la imagen como `foto3_credenciales_telegram.png` en la carpeta `MD/Telegram/`:

![FOTO 3: Configuración de Credenciales en Jenkins](foto3_credenciales_telegram.png)
---

## 🛠️ Paso 2: Configuración en Jenkinsfile

Se modificó el archivo `Jenkinsfile` del proyecto para incluir el bloque `environment` que inyecta las credenciales, y los comandos `curl` en el bloque `post` (éxito y fallo) apuntando a la API de Telegram.

**Bloque de entorno:**

```groovy
environment {
    TELEGRAM_TOKEN   = credentials('telegram-token')
    TELEGRAM_CHAT_ID = credentials('telegram-chatid')
}
```

**Notificación de éxito (bloque `success`):**

```groovy
sh """curl -s -X POST https://api.telegram.org/bot${env.TELEGRAM_TOKEN}/sendMessage \\
    -d chat_id=${env.TELEGRAM_CHAT_ID} \\
    -d parse_mode=Markdown \\
    -d text="✅ *Build #${env.BUILD_NUMBER} EN JENKINS*%0A*PROYECTO:* ${env.JOB_NAME}%0A*COMMIT:* ${env.GIT_COMMIT}%0A*URL:* ${env.BUILD_URL}" """
```

**Notificación de fallo (bloque `failure`):**

```groovy
sh """curl -s -X POST https://api.telegram.org/bot${env.TELEGRAM_TOKEN}/sendMessage \\
    -d chat_id=${env.TELEGRAM_CHAT_ID} \\
    -d parse_mode=Markdown \\
    -d text="❌ *Build #${env.BUILD_NUMBER} EN JENKINS*%0A*PROYECTO:* ${env.JOB_NAME}%0A*COMMIT:* ${env.GIT_COMMIT}%0A*URL:* ${env.BUILD_URL}" """
```

---

## 📸 Paso 3: Iteración 1 – Primera ejecución exitosa (SUCCESS ✅)

1. Rama: `feat-notificacion-telegram`.
2. En Jenkins, **Branch Specifier:** `*/feat-notificacion-telegram`.
3. `git push` o **Build Now** → build en verde.

En Telegram debe llegar un mensaje que empieza con **✅** y el texto **Build #… EN JENKINS** (proyecto, commit y URL).

---
📸 **FOTO 4 A TOMAR:** Captura del chat con tu bot mostrando el mensaje de **éxito** (✅) del primer build correcto.
Guarda la imagen como `foto4_success_telegram.png` en la carpeta `MD/Telegram/`:

![FOTO 4: Mensaje de éxito en Telegram](foto4_success_telegram.png)
---

## 📸 Paso 4: Iteración 2 – Error intencional (FAILURE ❌)

1. Rama: `feature/auth-error-telegram` (test con `Assertions.fail()`).
2. En Jenkins, **Branch Specifier:** `*/feature/auth-error-telegram`.
3. `git push` → build en **rojo**.

En Telegram debe llegar un mensaje con **❌** y el mismo formato **Build #… EN JENKINS**.

---
📸 **FOTO 5 A TOMAR:** Captura del chat con el mensaje de **error** (❌) tras el build fallido.
Guarda la imagen como `foto5_failure_telegram.png` en la carpeta `MD/Telegram/`:

![FOTO 5: Mensaje de error en Telegram](foto5_failure_telegram.png)
---

## 📸 Paso 5: Iteración 3 – Corrección y éxito automático (SUCCESS ✅)

1. Rama: `feature/login-ui-telegram` (test sin `fail()`).
2. En Jenkins, **Branch Specifier:** `*/feature/login-ui-telegram`.
3. `git push` → build en verde (idealmente disparado por webhook).

En Telegram llega de nuevo un mensaje **✅** (número de build distinto al de la foto 5).

---
📸 **FOTO 6 A TOMAR:** Captura del chat con el mensaje **✅** después de corregir el test (build automático o manual en verde).
Guarda la imagen como `foto6_build_auto_telegram.png` en la carpeta `MD/Telegram/`:

![FOTO 6: Build automático (éxito) en Telegram](foto6_build_auto_telegram.png)
---

## 📂 Evidencias en esta carpeta

| Archivo | Contenido |
|---------|-----------|
| `foto1_bot_telegram.png` | Token en @BotFather |
| `foto2_chatid_telegram.png` | Chat ID |
| `foto3_credenciales_telegram.png` | Credenciales en Jenkins |
| `foto4_success_telegram.png` | Mensaje ✅ (primer éxito) |
| `foto5_failure_telegram.png` | Mensaje ❌ (fallo intencional) |
| `foto6_build_auto_telegram.png` | Mensaje ✅ (tras corrección) |

> El MD enlaza **una vez** cada número de foto (1–6) con el nombre canónico de arriba. Puedes conservar otras copias en la misma carpeta como respaldo local; no hace falta referenciarlas en este documento.

---

## ✅ Conclusiones

- **Telegram** se integra con una llamada HTTP (`curl`) en el bloque `post` del pipeline.
- Las credenciales viven en el *Credentials Store* de Jenkins (`telegram-token`, `telegram-chatid`).
- **Rama perfecta del taller:** `feat-notificacion-telegram` (código, MD y las 6 fotos canónicas).
- **Ramas de prueba:** `feature/auth-error-telegram` (fallo) y `feature/login-ui-telegram` (corrección).
