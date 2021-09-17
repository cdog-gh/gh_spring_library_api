FROM maven:3.8.2-openjdk-8
COPY . .
WORKDIR /library
RUN mvn clean
RUN mvn -DskipTests package
WORKDIR /