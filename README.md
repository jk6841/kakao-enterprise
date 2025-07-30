# README

## 시스템 구조
```mermaid
graph TD
    A[api application] --> C[(Database)]
    B[data load application] --> C
```
- `api`
    - API 제공하는 서버 application

- `data load`
  - 기초 데이터를 읽어서 RDB에 저장
  - 작업을 마치고 종료되는 short-lived application

## 기술 스택
- Java 21
- Spring Boot(3.5.4)
- Spring Webflux
- Spring Data R2dbc
- `/build.gradle.kts`

## git repository 구조
### `/src`
- source code (gradle 기본 convention)
- package 구조
  - `io.github.jk6841.kakaoenterprise`
    - `api`
      - `api` 애플리케이션에 필요한 클래스들이 모여 있는 package
    - `dataload`
      - `data load` 애플리케이션에 필요한 클래스들이 모여 있는 package
    - `common`
      - `api` 애플리케이션과 `dataload` application이 공유하는 package
  - ```
    이런 경우 api, dataload, common을 gradle module로 분리하는 multi module project로 분리하는 것이 보편적인 방식이지만, 
    전체 기능이 많지 않고 초기 개발 상황이어서 패키지를 분리하고 component scan으로 처리했습니다.
    ```

### `/api.yaml`
- API 스펙 파일

### `/compose.yaml`
- 로컬 실행을 위해 MySQL을 docker compose로 실행하는 파일

### `/ddl.sql`
- DB DDL 파일

## 실행 방법
### MySQL
- ```shell
  docker compose up -d
  ```
  
### DataLoad Application
- ```shell
  gradle dataload-app
  ```
  

### Api Application
- ```shell
  gradle api-app
  ```