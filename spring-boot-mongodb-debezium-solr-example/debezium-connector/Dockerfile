FROM quay.io/debezium/connect:3.6

ARG DEBEZIUM_CONNECTOR_VERSION=3.6.0.Final
ENV KAFKA_CONNECT_PLUGIN_PATH=/kafka/connect

WORKDIR /usr/local/bin

RUN mkdir -p /tmp/plugins \
 && cd /tmp/plugins \
 && curl -fsSL https://repo1.maven.org/maven2/io/debezium/debezium-connector-mongodb/${DEBEZIUM_CONNECTOR_VERSION}/debezium-connector-mongodb-${DEBEZIUM_CONNECTOR_VERSION}-plugin.tar.gz \
    | tar xz