FROM openjdk:11
VOLUME /tmp
EXPOSE 8111
ADD ./target/ms-transaction-current-account-0.0.1-SNAPSHOT.jar ms-transaction-current-account.jar
ENTRYPOINT ["java","-jar","ms-transaction-current-account.jar"]