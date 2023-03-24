
# Informe del Primer Parcial Backend 2

Este proyecto consiste en una api que contiene información sobre películas, además de almacenar los datos de sus usuarios y su facturación. La api solo puede ser consultada por usuarios autorizados ya que cuenta con un sistema de seguridad por tokens.


## Autor

Elena Fischietto
## 🔗 Links y documentación utilizada

 - [Playground Digital House](https://playground.digitalhouse.com/course/8e011171-687f-4369-b905-6ea233420179/units)
 - [Documentación Keycloak](https://www.keycloak.org/docs/latest/securing_apps/)
## Pasos para su ejecución

1. Abrir terminal en la carpeta contenedora del docker-compose y levantarlo
```bash
docker-compose up
```

2. Acceder a http://localhost:8082/, loguearse como admin e importar archivo realm-export.json con las configuraciones del reino.

3. Abrir el proyecto con el editor de código y levantar los microservicios en el siguiente orden:

    * eureka-server.
    * api-gateway.
    * movies, bills y users.
## Consumo y obtención de tokens con Postman

Si llegase a fallar el consumo del token, este puede validarse a través de Postman.

1. Abrir una nueva pestaña en Postman.
2. Ir a **Authorization** y en la sección **Type** seleccionar **OAuth 2.0**
3. Marcar el check de **Authorization using browser**.
4. En **Auth URL** agregar http://localhost:8082/realms/digital-media/protocol/openid-connect/auth
5. En **Access Token URL** agregar http://localhost:8082/realms/digital-media/protocol/openid-connect/token
6. En Client ID y Client Secret completar con los datos del cliente del que queremos obtener el token.
  
  
![postman](https://media.discordapp.net/attachments/1044391703399899146/1085752972937531412/image.png?width=956&height=649)
