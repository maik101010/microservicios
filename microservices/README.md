# microservicios

## Configuración microservicios

- Crear repositorio para el control de los microservicios

- Carpeta root: Hacer commit de dicha carpeta una vez existen cambios, para no tener conflictos con el path
#### Configución eclipse
1. Click derecho en eclipse, link source, seleccionamos carpeta donde estara el repo root "git-localconfig-repo"
2. Acceso al recurso del repo: http://localhost:8888/limits-service/default (una vez configurado, podemos llamar qa, dev, entre otras properties) ver en repositorio las properties. 
3. Conexión LimitsService con SpringCloudConfigServer, cambiamos el nombre del properties por "bootstrap.properties", agregamos la ubicación del service

## Client sin RestTemplate (Feign Netflix)
1. Dependencia spring-cloud-starter-openfeign

    1.1 Crear interfaz con el/los metodos que se desea retornar
2. Configurar el nombre del servicio al que debe acceder con el url
3. Habilitar feign client en la clase principal de spring boot

## Balanceador de carga de servicios
1. Dependencia spring-cloud-starter-netflix-ribbon

    1.1 En el properties, poner el listado de servicios: current-exchange-service.ribbon-listOfServers=http://localhost:8000,http://localhost:8001 
2. En la interfaz para la configuración de Feign, anotar con RibbonClient el nombre del proyecto

## Configuración ApiGateway
1. Dependencia netfliz zuul, EnableZuulProxy en main class
2. Configuration class util
4. Registrar applicacion en servidor de netflix
3. call service: host:port/{name-application}/{url-service} (see Currency conversion service app)

## Intercep request for debugging (sleuth dependency)
1. Configurate Bean:
@Bean
 	public Sampler defaultSampler(){
   		return Sampler.ALWAYS_SAMPLE;
   	}
	
## Comunicación entre microservicios
- Usar colas, usar fetch, restTemplate? 
