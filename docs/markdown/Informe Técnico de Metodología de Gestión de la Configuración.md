# Informe técnico de la metodología y la gestión de la configuración
**[1. Estándares de Código](#_hohjtsklci24)**

**[2. Política de mensajes de commit](#_gmoye6w9s4sc)**

**[3. Estructura de los repositorios y ramas por defecto](#_ln7utkmzdkiq)** 

**[4. Branching strategy, basadas en Git Flow e incluyendo peer reviews](#_nqewzr49jya0)**

**[5. Desarrollo en ramas feature](#_k2x7cl80z42m)**

**[6. Preparación del despliegue](#_edywamrxtwai)**

**[7. Cómo solucionar bugs en producción](#_xz46azgjxca7)**

**[8. Política de versiones](#_66v958dp6ifs)**

## 1. Estándares de Código
Estándar de código establecido por convención en el lenguaje java:
- CamelCase
- Variables, funciones y Clases escritas en inglés
- Funciones comienzan en minuscula
- Clases y paquetes comienzan con mayúscula
- ¿Documentación de las funciones con javadoc?
### Política de mensajes de commit
La estructura de mensajes de commit será la siguiente:

**Tipo[!]:Título**

**[Cuerpo]**

**[Pie]**

Los tipos de commit que utilizamos son los siguientes:
- **feat** : Desarrollo de una nueva funcionalidad
- **fix** : Corrección de alguna funcionalidad 
- **docs** :Creación de nuevo documento 
- **release** : Despliegue de nueva versión 
- **style** : Modificación de hoja de estilos 
- **test** : Tarea de testing 
En caso de que se hayan añadido cambios importantes en el commit, se añadirá el símbolo "!" tras el tipo. El título describirá levemente el contenido del commit. Se escribirá en mayúscula el primer carácter y estará en español. El cuerpo será opcional y describirá lo desarrollado y el por qué del commit. Estará en español y el contenido se truncará cada 72 caracteres para facilitar su lectura. El pie será opcional, aunque recomendable, y hará referencia al issue tratado, así como al estado de éste de la siguiente forma: **Tipo#Nº issue** Siendo el tipo: 
- Refs: Para referenciar una issue que no está terminada. 
- Fixes: Para cerrar una issue mediante el commit. 
## 3. Estructura de los repositorios y ramas por defecto
Nuestro repositorio del proyecto seguirá la siguiente estructura: 
- "src". Carpeta contenedora de los archivos java del proyecto.   
- "main".     
- "Appengine". Aquí encontraremos archivos correspondientes al despliegue del proyecto.     
- ".../petclinic". El proyecto de ejemplo de spring que usaremos como base.
- "less". Archivos de estilo de la página web.     
- "resources". Archivos estáticos que serán enviados por el servidor web.     
- ".../WEB-INF". Archivos de la arquitectura JSP.   
- "test". Aquí se encontrarán los tests automatizados del proyecto. 
- "docs". Carpeta conteniendo todos los documentos de relevancia para el entregable. 
- Encontraremos además en la carpeta base otros archivos de texto importantes, tales como el pom.xml o readme.md. 

En cuanto a las ramas principales por defecto, tendremos una **rama principal "master"**. A partir de esta rama obtenemos la **segunda rama principal, "develop"**. La rama master contendrá todas las releases terminadas y revisadas. A su vez, la rama "develop" contendrá el código referente al desarrollo que no esté listo para release, ya sea por falta de funcionalidades, un testing lo suficientemente extensivo o cualquier motivo que el equipo estime oportuno. 
## 4. Branching strategy, basadas en Git Flow e incluyendo peer reviews
Como se ha comentado en el punto anterior, se usará **Git Flow** basado en dos ramas principales: **main** y **develop**. Para el desarrollo de funcionalidades, se crearán. 
## 5. Desarrollo en ramas feature
Por cada nueva feature, se generará una nueva rama a partir de **develop** , llamada **"feature-Número de issue"**. Al terminar de desarrollarse, se generará una **PR a Develop** , otro usuario hará la revisión y cuando esté correcto, se hará un merge a Develop. **Las ramas no se borrarán** en ningún momento 
## 6. Preparación del despliegue 
Para el despliegue se abrirá una nueva rama a partir de **develop** y nombrándola como **release-version**. En esta rama se podrán **solventar bugs menores** , además de añadir **cambios de última hora** que se consideren necesarios. Una vez terminado el trabajo con la rama se realizará un **merge** con las ramas **main** y **develop** para continuar con el desarrollo de la siguiente versión. 
## 7. Cómo solucionar bugs en producción 
El procedimiento que se ha considerado para tratar bugs que surjan en producción consiste en crear una rama nueva denotada como **bugfix** (bugfix-tagIssue), incluyendo el bug a solucionar. 
## 8. Política de versiones 
En cuanto a las versiones de la aplicación seguiremos la siguiente semántica: 

**X.Y.Z**

Donde X es para versiones con cambios mayores, que rompan la compatibilidad con versiones o APIs anteriores. El segundo número, Y, indica versiones con cambios menores que no rompan la compatibilidad y que incluyan funcionalidad o incrementos sustanciales. Por último la Z sería para parches que arreglan errores, para arreglar comportamientos incorrectos de la aplicación.
