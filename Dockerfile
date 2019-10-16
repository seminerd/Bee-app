FROM openjdk:11.0.2-jdk-slim
RUN apt-get update && apt-get install -y wget
ENV TZ=Asia/Ho_Chi_Minh
WORKDIR /
ADD docker/run.sh run.sh
ADD target/lib lib
ADD target/beemart-service-minimal.jar beemart-service.jar
RUN sh -c 'touch /beemart-service.jar'
RUN sh -c 'chmod +x /run.sh'
CMD ["/run.sh"]
