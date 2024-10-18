FROM bellsoft/liberica-openjdk-debian:17

WORKDIR /tracking-mail

COPY out/artifacts/tracking_mail_jar/tracking-mail.jar .

ENTRYPOINT ["java", "-jar", "tracking-mail.jar"]