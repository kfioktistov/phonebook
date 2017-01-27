FROM java:8
EXPOSE 8080
COPY phonebook-0.1.0.jar /root/phonebook.jar
ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar /root/phonebook.jar" ]