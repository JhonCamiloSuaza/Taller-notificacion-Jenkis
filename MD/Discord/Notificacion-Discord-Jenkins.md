# 🎮 Integración de Jenkins con Discord (Notificaciones CI/CD)

Este documento detalla el proceso paso a paso para configurar Jenkins de manera que envíe notificaciones automáticas (alertas de éxito o fallo) a un canal de Discord mediante el uso de Webhooks.

---

## 🛠️ Paso 1: Generación del Webhook en Discord

Para establecer la comunicación entre Jenkins y Discord, es necesario crear un Webhook que servirá como canal de entrada para los mensajes.

1. **Selección del canal:** Se seleccionó el canal de texto destinado para las alertas de Integración Continua dentro del servidor de Discord.
2. **Creación de la integración:** A través de los ajustes del canal (`Editar Canal` -> `Integraciones` -> `Webhooks`), se generó un nuevo Webhook llamado **Jenkins**.

*(La evidencia de la creación del Webhook se muestra a continuación).*

![FOTO 1: Webhook de Discord creado exitosamente](foto1_webhook_discord.png)

---

## 🚀 Paso 2: Integración en Jenkinsfile

Se modificó el archivo `Jenkinsfile` del proyecto para incluir la instrucción `sh` con el comando `curl`. Esto permite a Jenkins enviar notificaciones JSON al Webhook de Discord dependiendo de si el pipeline falla o tiene éxito.
