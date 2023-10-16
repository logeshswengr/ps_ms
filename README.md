### Build and Deploy the Application

```
1. Run "mvn clean install"
2. Run "mvn compile jib:build"
```

### Start applications using docker 🐋

```
docker compose up -d
```

### Connect to PostgreSQL databases via IntelliJ

1. Properties
    - `Host`: **localhost**
    - `Port`: **5431** and **5432**
    - `Password`: **1234**

### Keycloak setup

1. Open `localhost:8181`
    - username: `admin`
    - password: `admin`

2. Generate secret
    - Open `Clients` tab from left side
    - Open `##Client_ID##`
    - Go to `Credentials` tab and click Regenerate Secret


### Execute HTTP requests with Postman

1. Add the OAuth config
    - Open `Authorization`
    - Select Type `OAuth 2.0`
    - Configure New Token
        - Token Name: `token`
        - Grant Type: `Client Credentials`
        - Access Token URL:  `http://keycloak:8080/realms/##context##/protocol/openid-connect/token`  replace the context with realm
        - Client ID: `##CLIENT_ID##`
        - Client Secret: Get from 3rd of Keycloak setup.
        - Click `Get New Access Token` and `Use Token`
        - Send the Request

### Grafana dashboard setup

1. Open http://localhost:3000
2. On the left side hover on `+` icon and click Import
3. Paste the content of `Grafana_Dashboard.json` located in the root of the project and click `Load`
4. Select Prometheus as a datasource and click `Import`

## Available endpoints

|     Name      |        Address        |
|:-------------:|:---------------------:|
| API Endpoint  | http://localhost:8585 |
| Eureka Server | http://localhost:8761 |
|   Keycloak    | http://localhost:8080 |
|    Zipkin     | http://localhost:9411 |
|  Prometheus   | http://localhost:9090 |
|    Grafana    | http://localhost:3000 |
