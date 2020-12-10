FROM openjdk:10-jre
LABEL MAINTAINER MSB-Arkeup

ARG APP_MCS_NAME=gestion-profil-mcs
ARG APP_GROUP_ID=com.arkeup.link_innov
ARG APP_MCS_SOURCE=target

ARG APP_GLOBAL_PROPERTIES_SRC=external_properties/dev
ARG APP_GLOBAL_PROPERTIES_NAME=application.properties

#ARG APP_LOGBACK_PROPERTIES_SRC=external_properties/dev
#ARG APP_LOGBACK_PROPERTIES_NAME=logback-spring.xml

ARG CERTIFICATE_STOREPASS=pocjava-mcs
ARG CERTIFICATE_KEYPASS=pocjava-mcs

ENV APP_PATH="/opt/$APP_MCS_NAME/bin/$APP_MCS_NAME.jar" \
    # LOG_CONFIG_PATH="/etc/opt/$APP_MCS_NAME/$APP_LOGBACK_PROPERTIES_NAME" \
    SPRING_CONFIG_PATH="/opt/$APP_MCS_NAME/etc/"

RUN mkdir -p /var/log/$APP_MCS_NAME

VOLUME /opt/$APP_MCS_NAME/var/log/
VOLUME /opt/$APP_MCS_NAME/etc/

# récupérer le fichier jar
COPY ${APP_MCS_SOURCE}/${APP_MCS_NAME}*.jar "/opt/$APP_MCS_NAME/bin/$APP_MCS_NAME.jar"

# récupérer les fichiers properties
COPY $APP_GLOBAL_PROPERTIES_SRC/$APP_GLOBAL_PROPERTIES_NAME /opt/$APP_MCS_NAME/etc/$APP_GLOBAL_PROPERTIES_NAME

# récupérer la config logback
# COPY $APP_LOGBACK_PROPERTIES_SRC/$APP_LOGBACK_PROPERTIES_NAME /etc/opt/$APP_MCS_NAME/$APP_LOGBACK_PROPERTIES_NAME

RUN keytool -genkey -noprompt -alias $APP_MCS_NAME \
     -storetype PKCS12 -keyalg RSA -keysize 2048 \
     -keystore keystore.p12 -validity 3650\
     -dname "CN=$APP_MCS_NAME, OU=$APP_GROUP_ID, O=ARKEUP, L=PARIS, S=IDF, C=FR" \
     -storepass $CERTIFICATE_STOREPASS \
     -keypass $CERTIFICATE_KEYPASS

CMD ["/bin/sh","-c","/usr/bin/java -jar ${APP_PATH} -Dio.netty.leakDetectionLevel=advanced -Dio.netty.noPreferDirect=true -Dio.netty.allocator.type=unpooled -Dio.netty.maxDirectMemory=0 --spring.config.location=classpath:/application.properties,${SPRING_CONFIG_PATH}"]
#CMD ["/bin/sh","-c","/usr/bin/java -jar ${APP_PATH}"]
#ENTRYPOINT "/bin/sh"

EXPOSE 8080 8443
