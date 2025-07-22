# Spring Boot High-Concurrency Scaling Experiment

This project is in progress. It aims to build a minimal, clean Spring Boot TODO REST API storing data in a file to benchmark and explore **scaling performance from 1,000 to 100,000 requests per second (RPS).**

## 🚀 **Project Goals**

✅ Build a simple TODO REST API using Spring Boot  
✅ Follow best practices: controller, service, repository layers  
🔜 Benchmark and analyze performance under increasing load  
🔜 Explore optimizations:
- Thread pool and JVM tuning
- In-memory caching strategies
- Reactive programming with WebFlux
- Horizontal scaling with load balancers

## 🛠 **Current Tech Stack**

- Java 21 (or 17)
- Spring Boot 3.5.3
- File-based storage with Jackson
- Lombok

## 📅 **Project Status**

✅ **Setup basic project structure**  
🔄 **Implementing core CRUD APIs**

## 📝 **Future Plans**
- Load testing with k6 and wrk
- Optimizing and documenting results
- Replace synchronous file I/O with caching or database for higher RPS
