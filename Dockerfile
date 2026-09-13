# syntax=docker/dockerfile:1

# ----- build stage -----
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

ARG GPR_USER
ARG GPR_TOKEN
ENV GPR_USER=${GPR_USER}
ENV GPR_TOKEN=${GPR_TOKEN}
# 1) Copy wrapper & gradle metadata first (better caching)
COPY gradlew gradlew
COPY gradle gradle
COPY settings.gradle* build.gradle* ./

# 2) Make wrapper executable and warm up (downloads Gradle)
RUN chmod +x ./gradlew && ./gradlew --version

# 3) Copy the rest of the sources
COPY src src

# 4) Build the jar (skip tests if you like: add -x test)
RUN ./gradlew --no-daemon clean bootJar

# ----- run stage -----
FROM eclipse-temurin:21-jre
RUN useradd -m -s /bin/bash appuser
WORKDIR /app
VOLUME ["/data"]

# copy the built jar (adjust the glob if needed)
COPY --from=build /app/build/libs/*-SNAPSHOT.jar /app/app.jar

ENV SPRING_PROFILES_ACTIVE=render
ENV JAVA_OPTS="-Xms256m -Xmx512m"
USER appuser
CMD ["sh","-c","java $JAVA_OPTS -Dserver.port=${PORT} -jar /app/app.jar"]