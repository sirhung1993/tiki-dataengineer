# Tiki Data Engineer

Model e-commerce products and test cases.

## System Requirements

- Java 8
- Maven 3.6.0
- OS Ubuntu 18.04 (tested) or Window
___
## Build processes

1. Must build **model/** **first** by commands: 
2. cd **$PROJECT_HOME/model** && mvn clean install
3. Then build **product/**
4. cd **$PROJECT_HOME/product** && mvn clean install
___
## Project structure

- **src/main/proto**: contain product model definitions.  
- **sample_product/src/main**: containt sample product for test cases.  
- **sample_product/src/test**: containt test cases written by JUnit5 to check sample product and configurable product.  
---
## Notes

- Project use [Protocol Buffer](https://developers.google.com/protocol-buffers/?hl=vi) as a model builder. It requires the executable files proto (on Linux) or proto.exe (on Window).  
