# **Distributed File System**

## **Summary**
- [Overview](#overview)
- [Architecture](#architecture)
- [Requirements](#requirements)
- [Configuration and Execution](#configuration-and-execution)
- [API Usage](#api-usage)
- [Testing](#testing)
- [Contributions](#contributions)
- [License](#license)

## **Overview**
This project implements a Distributed File System using **Spring Boot**. The system allows users to read and write text files through a distributed service, ensuring that files are stored redundantly across multiple nodes for high availability and reliability.

### **Main Features**
- **Distributed file reading and writing.**
- **Name resolution and service discovery through Eureka Server.**
- **Load balancing between storage nodes.**

## **Architecture**
The system is composed of multiple microservices that work together to provide file reading and writing functionality.

- **ISP Server:** Main interface for users. Forwards requests to the correct service.
- **DNS Server:** Resolves application IPs by querying Eureka Server.
- **Profile App:** Coordinates file reading and writing with storage nodes (DFS-A, DFS-B, DFS-C).
- **DFS-A (Master Node):** Manages file requests, forwarding them to storage nodes. - **DFS-B and DFS-C (Storage Nodes):** Physically store the files.

### **Architecture Diagram**
```plaintext
+--------------+ +-------------+ +-----------------+
| ISP Server |<--->| DNS Server |<--->| Eureka Server |
+--------------+ +-------------+ +-----------------+
| | |
v v v
+---------------+ +---------------+ +---------------+
| Profile App |<-->| DFS-A |<--->| DFS-B/C | +---------------+ +---------------+ +--------------+
```

## **Requirements**
- **Java 11+**
- **Maven or Gradle**
- **Spring Boot**
- **Spring Cloud Netflix Eureka**

## **Setup and Run**

### **1. Clone the Repository**
```bash
git clone https://github.com/your-username/your-repository.git
cd your-repository
```

### **2. Configure the Eureka Server**
Make sure the Eureka Server is configured and running at `http://localhost:8761`.

### **3. Configure each Microservice**
Each microservice has its own Spring Boot project. Make sure you configure the dependencies and `application.properties` correctly.

#### **Example `application.properties` (ISP Server)**
```properties
spring.application.name=isp-server
server.port=8080
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
```

### **4. Compile and Run the Services**
Compile and run each service in separate terminal windows or using your preferred IDE.

```bash
# Navigate to each service directory and run
cd path/to/service
mvn spring-boot:run
# or
./gradlew bootRun
```

## **API Usage**

### **Available Endpoints**
#### **Get File**
- **URL:** `http://localhost:8080/perfil/obterArquivo/{nome_arquivo}.txt`
- **Method:** GET
- **Description:** Gets the contents of the specified file.

#### **Save File**
- **URL:** `http://localhost:8080/perfil/salvarArquivo/{nome_arquivo}.txt`
- **Method:** POST
- **Request Body:** `{ "content": "File content" }`
- **Description:** Saves the specified file with the provided content.

### **GET Request Example**
```bash
curl -X GET http://localhost:8080/perfil/obterArquivo/perfis_v1.txt
```

### **POST Request Example**
```bash
curl -X POST -H "Content-Type: application/json" -d '{"content": "New content"}' http://localhost:8080/perfil/salvarArquivo/perfis_v3.txt
```

## **Tests**

### **Using Insomnia or Postman**
1. **To get a file:**
- **URL:** `http://localhost:8080/perfil/obterArquivo/perfis_v1.txt`
- **Method:** GET

2. **To save a file:**
- **URL:** `http://localhost:8080/perfil/salvarArquivo/perfis_v3.txt`
- **Method:** POST
- **Request Body:** `{ "content": "New content" }`

### **Automated Tests**
Implement unit and integration tests using frameworks such as JUnit and Mockito to ensure that services are working as expected.

## **Contributions**

### **How ​​to Contribute**
1. **Fork the repository**
2. **Create a new branch for your feature (git checkout -b feature/your-feature)**
3. **Commit your changes (git commit -am 'Add new feature')**
4. **Push to the branch (git push origin feature/your-feature)**
5. **Create a new Pull Request**

### **Contribution Guidelines**
- Keep the code clear and readable. - Add comments to explain complex areas of the code.
- Make sure all changes are covered by tests.

## **License**
This project is licensed under

--------------------

# **Sistema de Arquivos Distribuídos**

## **Sumário**
- [Visão Geral](#visão-geral)
- [Arquitetura](#arquitetura)
- [Requisitos](#requisitos)
- [Configuração e Execução](#configuração-e-execução)
- [Uso da API](#uso-da-api)
- [Testes](#testes)
- [Contribuições](#contribuições)
- [Licença](#licença)

## **Visão Geral**
Este projeto implementa um Sistema de Arquivos Distribuídos utilizando **Spring Boot**. O sistema permite que usuários leiam e escrevam arquivos de texto através de um serviço distribuído, garantindo que os arquivos sejam armazenados de forma redundante em múltiplos nós para alta disponibilidade e confiabilidade.

### **Características Principais**
- **Leitura e escrita de arquivos distribuída.**
- **Resolução de nomes e descoberta de serviços através do Eureka Server.**
- **Balanceamento de carga entre nós de armazenamento.**

## **Arquitetura**
O sistema é composto por múltiplos microserviços que trabalham em conjunto para fornecer as funcionalidades de leitura e escrita de arquivos.

- **ISP Server (Servidor de ISP):** Interface principal para os usuários. Encaminha as requisições para o serviço correto.
- **DNS Server (Servidor de DNS):** Resolve os IPs das aplicações consultando o Eureka Server.
- **Profile App:** Coordena a leitura e escrita de arquivos com os nós de armazenamento (DFS-A, DFS-B, DFS-C).
- **DFS-A (Master Node):** Gerencia as requisições de arquivos, encaminhando para os nós de armazenamento.
- **DFS-B e DFS-C (Storage Nodes):** Armazenam fisicamente os arquivos.

### **Diagrama da Arquitetura**
```plaintext
+--------------+     +-------------+     +-----------------+
|  ISP Server  |<--->|  DNS Server |<--->|  Eureka Server  |
+--------------+     +-------------+     +-----------------+
        |                  |                    |
        v                  v                    v
+---------------+    +---------------+     +--------------+
|  Profile App  |<-->|    DFS-A      |<--->|  DFS-B/C     |
+---------------+    +---------------+     +--------------+
```

## **Requisitos**
- **Java 11+**
- **Maven ou Gradle**
- **Spring Boot**
- **Spring Cloud Netflix Eureka**

## **Configuração e Execução**

### **1. Clonar o Repositório**
```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio
```

### **2. Configurar o Eureka Server**
Certifique-se de que o Eureka Server está configurado e rodando em `http://localhost:8761`.

### **3. Configurar cada Microserviço**
Cada microserviço tem seu próprio projeto Spring Boot. Certifique-se de configurar as dependências e `application.properties` corretamente.

#### **Exemplo de `application.properties` (ISP Server)**
```properties
spring.application.name=isp-server
server.port=8080
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
```

### **4. Compilar e Executar os Serviços**
Compile e execute cada serviço em janelas de terminal separadas ou utilizando seu IDE preferido.

```bash
# Navegar para o diretório de cada serviço e executar
cd path/to/service
mvn spring-boot:run
# ou
./gradlew bootRun
```

## **Uso da API**

### **Endpoints Disponíveis**
#### **Obter Arquivo**
- **URL:** `http://localhost:8080/perfil/obterArquivo/{nome_arquivo}.txt`
- **Método:** GET
- **Descrição:** Obtém o conteúdo do arquivo especificado.

#### **Salvar Arquivo**
- **URL:** `http://localhost:8080/perfil/salvarArquivo/{nome_arquivo}.txt`
- **Método:** POST
- **Corpo da Requisição:** `{ "content": "Conteúdo do arquivo" }`
- **Descrição:** Salva o arquivo especificado com o conteúdo fornecido.

### **Exemplo de Requisição GET**
```bash
curl -X GET http://localhost:8080/perfil/obterArquivo/perfis_v1.txt
```

### **Exemplo de Requisição POST**
```bash
curl -X POST -H "Content-Type: application/json" -d '{"content": "Novo conteúdo"}' http://localhost:8080/perfil/salvarArquivo/perfis_v3.txt
```

## **Testes**

### **Usando Insomnia ou Postman**
1. **Para obter um arquivo:**
    - **URL:** `http://localhost:8080/perfil/obterArquivo/perfis_v1.txt`
    - **Método:** GET

2. **Para salvar um arquivo:**
    - **URL:** `http://localhost:8080/perfil/salvarArquivo/perfis_v3.txt`
    - **Método:** POST
    - **Corpo da Requisição:** `{ "content": "Novo conteúdo" }`

### **Testes Automatizados**
Implemente testes unitários e de integração usando frameworks como JUnit e Mockito para garantir que os serviços estejam funcionando conforme esperado.

## **Contribuições**

### **Como Contribuir**
1. **Fork o repositório**
2. **Crie uma nova branch para a sua feature (git checkout -b feature/sua-feature)**
3. **Faça commit das suas mudanças (git commit -am 'Adiciona nova feature')**
4. **Push para a branch (git push origin feature/sua-feature)**
5. **Crie um novo Pull Request**

### **Diretrizes de Contribuição**
- Mantenha o código claro e legível.
- Adicione comentários para explicar áreas complexas do código.
- Certifique-se de que todas as mudanças estejam cobertas por testes.

## **Licença**
Este projeto é licenciado sob os termos da [MIT License](LICENSE).
