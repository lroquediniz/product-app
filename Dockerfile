FROM java:8
MAINTAINER Luan Roque <lroquediniz@gmail.com>

WORKDIR /app

ADD pom.xml /app/pom.xml
ADD src /app/src

RUN apt-get update && apt-get install -y maven \
    && mvn dependency:resolve verify package

EXPOSE 4567
CMD ["/usr/lib/jvm/java-8-openjdk-amd64/bin/java", "-jar", "target/product-app-jar-with-dependencies.jar"]