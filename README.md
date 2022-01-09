##ECOMMERCE PROJECT NOTE

##How to build microservice project:
## step1 - Maven clean install

mvn -s C:\spring-cloud\apache-maven-3.8.1\conf\settings.xml clean install -Dmaven.test.skip=true

##Build docker compose in root project

docker-compose up

##How to mapping service uri to Gateway-server
http://localhost:8765/{application-name}/uri

Ex: product-service:http://localhost:8080/product/limit
-> gateway-server: http://localhost:8765/product-service/product/limit

## Development CLI helpers
#### Redis clean up: redis-cli flushall
#### Elasticsearch clean up: curl -X DELETE 'http://localhost:9200/_all'
