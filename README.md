# Mambo King 67 HP

Sistema de escritorio para la **gestión de consultas médicas**: registro de pacientes, agendamiento de horas y revisión del historial de citas.

Desarrollado por **MamboTech Soluciones Informáticas** en el Instituto Comercial Alejandro Rivera Díaz (Copiapó), octubre 2026.

## ¿Qué problema resuelve?

Agendar una consulta suele depender de llamadas o atención presencial, lo que genera esperas, errores al digitar datos y falta de control de horarios ocupados. Mambo King 67 HP digitaliza el proceso: el paciente se registra una sola vez y agenda sus consultas con validación automática de fechas y horarios.

**Usuarios:** pacientes (autoconsulta) y personal administrativo del centro de salud.

## Funcionalidades

- Registro e inicio de sesión de pacientes por **RUT** (valida campos vacíos, formato y RUT duplicado).
- Agendamiento de consultas con selector de fecha y hora.
- Validaciones: no permite fechas pasadas, horas ya transcurridas ni bloques no disponibles (00:00–08:15 y 12:00–13:00).
- Cálculo automático del costo según tipo de consulta y médico.
- Historial de consultas del paciente en tabla, consultado en tiempo real.
- Verificación de conexión a la base de datos al iniciar.

## Tecnologías

- **Java 25** con interfaz **Swing** (JFrame / JDialog)
- **Microsoft SQL Server** (base de datos `Consulta`)
- **JDBC** (`mssql-jdbc 13.4.0`)
- IDE: Apache NetBeans

## Estructura

HospitalMambo/

├── src/hospitalmambo/

│   ├── HospitalMambo.java      # Clase principal

│   ├── Conexion/               # Acceso a datos (JDBC)

│   └── Interfaz/               # Ventanas (Swing)

├── src/img/                    # Imágenes

├── SQLQuery1.sql               # Script de la base de datos

└── docs/MamboKing67.docx       # Documentación del proyecto

​

## Modelo de datos

Relación uno a muchos: un **Cliente** tiene muchas **Consultas**.

| Cliente | Consulta |
|---|---|
| IdCliente (PK) | IdConsulta (PK) |
| Nombre | Fecha |
| Edad | Hora |
| Correo | Tipo |
| Rut (único) | Medico |
| | Costo |
| | IdCliente (FK) |

## Instalación

### Requisitos
- JDK 25
- SQL Server (por ejemplo, instancia Express)
- Driver [mssql-jdbc 13.4.0](https://learn.microsoft.com/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server) agregado a las librerías del proyecto
- Windows (la conexión usa autenticación integrada y requiere `mssql-jdbc_auth-13.4.0.x64.dll`)

### Pasos
1. Clona el repositorio:
   ```bash
   git clone https://github.com/<tu-usuario>/<tu-repo>.git
   ```
2. Ejecuta `SQLQuery1.sql` en SQL Server para crear la base de datos y las tablas.
3. Edita la cadena de conexión en `Conexion.java` y `ConexionCliente.java` con el nombre de **tu** servidor/instancia:
   ```java
   jdbc:sqlserver://TU-PC\\TU-INSTANCIA;database=Consulta;encrypt=false;integratedSecurity=true
   ```
4. Abre `HospitalMambo` en NetBeans, agrega el JAR del driver y ejecuta la clase `HospitalMambo`.

## Proyecto académico.
