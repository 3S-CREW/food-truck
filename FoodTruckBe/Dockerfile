# 1. Base Image: Java 17 (Eclipse Temurin - 성능과 안정성이 입증된 JDK)
FROM eclipse-temurin:17-jdk

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. 빌드된 JAR 파일 복사
# (주의: 로컬에서 먼저 ./gradlew build를 실행해야 파일이 존재함)
COPY build/libs/*.jar app.jar

# 4. 포트 노출 (문서화 용도, 실제 포트 오픈은 docker run -p 옵션)
EXPOSE 8080

# 5. 실행 명령어
ENTRYPOINT ["java", "-jar", "app.jar"]