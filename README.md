# Taller: Notificaciones Jenkins (Gmail, Discord, Outlook y Telegram)

Proyecto de práctica **CI/CD** con Jenkins, Spring Boot y notificaciones automáticas en varios canales.  
Este repositorio documenta cada integración con capturas y un `Jenkinsfile` declarativo.

---

## Rama principal integrada: `dev`

La rama **`dev`** concentra **todo el taller** en un solo lugar:

| Elemento | Contenido en `dev` |
|----------|-------------------|
| **Código** | `Jenkinsfile` con **Gmail + Outlook + Discord + Telegram** |
| **Documentación** | Carpetas `MD/Gmail`, `MD/Discord`, `MD/Outlook`, `MD/Telegram` |
| **Evidencias** | Todas las fotos de cada canal |
| **Test** | `PracticaJenkinsApplicationTests` sin fallos intencionales |

**Jenkins (job unificado):** Branch Specifier → `*/dev`

---

## Ramas perfectas por canal (`feat-notificacion-*`)

Cada canal se desarrolló y documentó en su **rama de entrega**. Ahí está el MD completo, las fotos y el código listo para revisar **por separado**:

| Rama | Canal | Documentación | Fotos | Estado |
|------|-------|---------------|-------|--------|
| [`feat-notificacion-gmail`](https://github.com/JhonCamiloSuaza/Taller-notificacion-Jenkis/tree/feat-notificacion-gmail) | Gmail (`mail`) | [MD/Gmail/Notificacion-Gmail-Jenkins.md](MD/Gmail/Notificacion-Gmail-Jenkins.md) | 11 | PERFECTA |
| [`feat-notificacion-discord`](https://github.com/JhonCamiloSuaza/Taller-notificacion-Jenkis/tree/feat-notificacion-discord) | Discord (`curl` webhook) | [MD/Discord/Notificacion-Discord-Jenkins.md](MD/Discord/Notificacion-Discord-Jenkins.md) | 5 | PERFECTA |
| [`feat-notificacion-outlook`](https://github.com/JhonCamiloSuaza/Taller-notificacion-Jenkis/tree/feat-notificacion-outlook) | Outlook (`mail`) | [MD/Outlook/Notificacion-Outlook-Jenkins.md](MD/Outlook/Notificacion-Outlook-Jenkins.md) | 5 | PERFECTA |
| [`feat-notificacion-telegram`](https://github.com/JhonCamiloSuaza/Taller-notificacion-Jenkis/tree/feat-notificacion-telegram) | Telegram (`curl` API) | [MD/Telegram/Notificacion-Telegram-Jenkins.md](MD/Telegram/Notificacion-Telegram-Jenkins.md) | 9 (6 + 3 copias) | PERFECTA |

> Estas ramas **siguen en GitHub** después del merge a `dev`. Sirven para mostrar cada entrega por canal; **`dev`** es la versión **todo-en-uno**.

---

## Ramas de prueba (`feature/*`)

Para demostrar **fallo** y **corrección** en Jenkins (notificación ❌ y luego ✅), cada canal usa **dos ramas hijas**:

| Tipo | Patrón de nombre | Para qué sirve |
|------|------------------|----------------|
| Error intencional | `feature/auth-error-<canal>` | Test con `fail()` → build rojo → notificación de fallo |
| Corrección | `feature/login-ui-<canal>` | Se quita el `fail()` → build verde → notificación de éxito |

| Canal | Rama error | Rama corrección |
|-------|------------|-----------------|
| Gmail | `feature/auth-error-gmail` | `feature/login-ui-gmail` |
| Discord | `feature/auth-error-discord` | `feature/login-ui-discord` |
| Outlook | `feature/auth-error-outlook` | `feature/login-ui-outlook` |
| Telegram | `feature/auth-error-telegram` | `feature/login-ui-telegram` |

**No hace falta** mergear las ramas `feature/*` a `dev`; solo se usan durante las pruebas del pipeline.

---

## Estructura del proyecto

```
Taller-notificacion-Jenkis/
├── Jenkinsfile              # Pipeline con las 4 notificaciones (en dev)
├── README.md                # Este archivo
├── MD/
│   ├── Gmail/               # Doc + fotos Gmail
│   ├── Discord/             # Doc + fotos Discord
│   ├── Outlook/             # Doc + fotos Outlook
│   └── Telegram/            # Doc + fotos Telegram
├── src/
│   └── main/java/...        # API Spring Boot
│   └── test/java/...        # Tests
└── pom.xml
```

---

## Cómo leer la documentación

1. **Vista general (todo el taller):** quédate en **`dev`** y abre cada carpeta bajo `MD/`.
2. **Un solo canal:** cambia a la rama `feat-notificacion-<canal>` en GitHub o en local.
3. **Flujo fallo → arreglo:** revisa las ramas `feature/auth-error-*` y `feature/login-ui-*` de ese canal.

---

## Otras ramas

| Rama | Uso |
|------|-----|
| `main` | Estructura inicial del repositorio |
| `dev` | **Entrega integrada** del taller completo |

---

## Autor

**Jhon Camilo Suaza Sanchez** — Taller SENA / Jenkins + notificaciones multi-canal.
