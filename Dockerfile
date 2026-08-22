# ============================
# Build
# ============================
FROM maven:3.9.11-eclipse-temurin-21 AS build

WORKDIR /app

# Primeiro copia apenas o pom para aproveitar o cache
COPY pom.xml .

# Baixa as dependências
RUN mvn dependency:go-offline -B

# Agora copia o código
COPY src ./src

# Build Quarkus
RUN mvn clean package -DskipTests


# ============================
# Runtime
# ============================
FROM eclipse-temurin:21-jre

WORKDIR /app

# Usuário não-root
RUN useradd --system --create-home quarkus

# O Quarkus gera:
# target/quarkus-app/
#
# Dentro dela:
#   app/
#   lib/
#   quarkus/
#   quarkus-run.jar

COPY --from=build /app/target/quarkus-app/ ./

RUN chown -R quarkus:quarkus /app

USER quarkus

EXPOSE 8080

ENV JAVA_OPTS="-Djava.net.preferIPv4Stack=true"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar quarkus-run.jar"]