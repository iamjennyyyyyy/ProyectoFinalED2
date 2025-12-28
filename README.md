# 🏥 Sistema de Detección de Epidemias - Proyecto Final ED

## 📋 Descripción
Sistema desarrollado en Java para la detección temprana de epidemias basado en la estructura del sistema de salud cubano. El proyecto utiliza estructuras de datos avanzadas (árboles y grafos) para modelar la jerarquía médica y las relaciones geográficas entre consejos populares.

## 🎯 Objetivo
Identificar posibles brotes epidémicos mediante el análisis de casos médicos reportados en la estructura territorial de La Habana, permitiendo alertas tempranas y visualización de zonas afectadas.

## 🏗️ Arquitectura del Sistema

### 📊 Estructuras de Datos Implementadas

#### 1. **Árbol Jerárquico del Sistema de Salud**
```
Nivel 1: MINSAP
└── Nivel 2: Dirección Provincial
    └── Nivel 3: Dirección Municipal
        └── Nivel 4: Policlínico (Área de Salud)
            └── Nivel 5: Consultorio
```

#### 2. **Grafo de Consejos Populares**
- Nodos: Consejos Populares de la provincia
- Aristas: Relaciones de colindancia entre consejos
- Atributos: Estadísticas de enfermedades por período

### 👥 Roles del Sistema
- **Médico**: Registro de pacientes y casos médicos
- **Administrador**: Gestión del sistema y generación de reportes

## 📁 Entidades Principales

### Core del Sistema
- `MINSAP`, `DireccionProvincial`, `DireccionMunicipal`
- `Policlinico`, `Consultorio`
- `Medico`, `Administrador`
- `Paciente`, `Enfermedad`
- `ConsejoPopular`

### Modelo de Datos
- **Paciente**: Información personal + historial de enfermedades
- **Enfermedad**: Tipos de patologías con categorización
- **Registro Epidemiológico**: Casos por consejo popular, fecha y enfermedad

## 🔍 Detección de Epidemias

### Algoritmo de Detección
1. **Cálculo de Línea Base**: Promedio histórico de casos por enfermedad
2. **Monitoreo Continuo**: Registro diario/semanal de nuevos casos
3. **Análisis Comparativo**:
   - Comparación con promedios históricos
   - Evaluación de tendencia de crecimiento
   - Consideración de población del consejo popular
4. **Detección de Patrones**:
   - Aumento significativo respecto al promedio
   - Crecimiento acelerado en períodos cortos
   - Propagación a consejos colindantes

### Niveles de Alerta
1. **Informativa**: Situación normal
2. **Alerta**: Aumento moderado de casos
3. **Alarma**: Posible epidemia detectada

## 🛠️ Implementación Técnica

### Requisitos
- Java JDK 17 (compatible con bibliotecas de la escuela)
- NetBeans IDE
- Bibliotecas de ED proporcionadas

### Consideraciones de Implementación
- Uso de árbol general para jerarquía de salud
- Grafo no dirigido para relaciones de colindancia
- Persistencia inicial en memoria (posterior migración a BD)
- Interfaz gráfica tipo escritorio simulando patrones web

## 📈 Funcionalidades por Rol

### 👨‍⚕️ Médico
- Registrar nuevos pacientes
- Asociar enfermedades a pacientes existentes
- Consultar historial médico
- Reportar casos por consultorio

### 👨‍💼 Administrador
- Visualizar mapa epidemiológico
- Generar reportes por:
  - Municipio/Consejo Popular
  - Enfermedad específica
  - Período temporal
- Configurar umbrales de alerta
- Monitoreo en tiempo real

## 🎨 Interfaz de Usuario
- Mapa interactivo de La Habana por consejos populares
- Visualización por colores según nivel de alerta
- Paneles de reporte y estadísticas
- Formularios CRUD para entidades principales
- Dashboard con métricas clave

## 📊 Almacenamiento de Datos

### Temporal (Fase Actual)
- Estructuras en memoria durante ejecución
- Datos de prueba generados programáticamente
- Posibilidad de carga/descarga de archivos

### Futura Evolución
- Integración con base de datos relacional
- Persistencia de historial completo
- Soporte para múltiples años de datos

## 🚀 Próximos Pasos de Implementación

1. **Fase 1**: Modelo de dominio completo
2. **Fase 2**: Estructuras de datos básicas funcionando
3. **Fase 3**: Algoritmos de detección epidemiológica
4. **Fase 4**: Interfaz de usuario básica
5. **Fase 5**: Reportes y funcionalidades avanzadas
6. **Fase 6**: Optimización y pruebas

## ⏰ Cronograma de Entrega
- **Semana del 5 al 9 de enero**: Presentación final
- **28-31 de diciembre**: Desarrollo intensivo
- **Reuniones diarias**: Seguimiento de avances

## 👥 Equipo de Desarrollo
- **Nickiiiiii** - Líder técnico / Modelado
- **Jenny** - Interfaz / Frontend
- **Dariel** - Backend / Lógica
- **Omar Ayudante IP** - Asesoría / Arquitectura
- **Adrián IP** - Consultoría técnica

---

## ⚠️ CONSIDERACIONES VITALES - ULTIMAS ACTUALIZACIONES

### 1. **Definición de Epidemia**
- **Criterio principal**: Aumento significativo respecto al promedio histórico
- **Factores a considerar**:
  - Porcentaje de población afectada
  - Tendencia de crecimiento (aceleración)
  - Propagación a zonas colindantes
  - Comparación con años anteriores

### 2. **Modelado de Datos Epidemiológicos**
- **NO incluir años de epidemia en cálculo de promedios** (distorsiona línea base)
- **Mantener registro histórico completo** para análisis posterior
- **Considerar densidad poblacional** por consejo popular

### 3. **Prioridades de Implementación**
1. **Funcionalidad core primero**: Estructuras de datos + algoritmos básicos
2. **CRUD después**: Interfaz de administración como secundaria
3. **Enfoque en**: Árbol de salud + Grafo de consejos + Detección epidemiológica

### 4. **Decisiones Técnicas Críticas**
- **JDK 17** obligatorio (compatibilidad con bibliotecas)
- **NetBeans** como IDE principal
- **Datos en memoria** para fase inicial (sin BD aún)
- **Paciente como única entidad** (sin separar Persona)

### 5. **Requisitos de Presentación**
- **Demostración con datos de prueba** suficientes
- **Visualización de alertas** en mapa/interfaz
- **Explicación clara** de algoritmos de detección
- **Uso correcto** de árboles y grafos demostrable

### 6. **Gestión de Tiempo - URGENTE**
- **Foco inmediato**: Algoritmo de detección epidemiológica
- **División de tareas** clara entre equipo
- **Reunión diaria** de coordinación
- **MVP para el 30/12**: Sistema básico funcionando

### 7. **Puntos de Atención Especial**
- **Relación Consejo Popular ↔ Área de Salud**: Un policlínico puede abarcar múltiples consejos
- **Grafo sin distancias**: Solo colindancia (no geolocalización exacta)
- **Alertas por propagación**: Consejos afectados + colindantes en riesgo

### 8. **Expectativas del Cliente (Martha)**
- Interfaz tipo web (simulada en desktop)
- Mapas visuales con niveles de alerta
- Reportes analíticos por zona/enfermedad
- Sistema usable por personal médico real

---

**⚠️ ESTADO ACTUAL**: Fase de implementación intensiva - Plazo de entrega: 5-9 de enero  
**🚨 PRIORIDAD ABSOLUTA**: Algoritmo de detección + Estructuras funcionando
