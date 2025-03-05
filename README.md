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
git clone https://github.com/azevedo1x/Files-Manager-Microservice.git
cd Files-Manager-Microservice
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
This project is licensed under the terms of the [MIT License](LICENSE).
