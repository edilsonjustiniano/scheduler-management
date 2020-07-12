FROM openjdk:8
MAINTAINER edilsonjustiniano
COPY scheduler-management-1.0.0.jar /home/
EXPOSE 8080
CMD ["java", "-jar", "/home/scheduler-management-1.0.0.jar"]