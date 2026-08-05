# Stage 1: Download sample data and MongoDB Database Tools
FROM registry.access.redhat.com/ubi8/ubi-minimal AS tools

ARG TOOLS_VERSION=100.17.0

RUN microdnf install -y \
        curl \
        tar \
        gzip \
    && microdnf clean all

# Download sample data
RUN curl -L \
    -o /tmp/sampledata.archive \
    https://atlas-education.s3.amazonaws.com/sampledata.archive

# Download MongoDB Database Tools
RUN curl -L \
    -o /tmp/tools.tgz \
    https://fastdl.mongodb.org/tools/db/mongodb-database-tools-rhel88-aarch64-100.17.0.tgz\
 && mkdir -p /opt/tools \
 && tar -xzf /tmp/tools.tgz -C /opt/tools --strip-components=1

# Stage 2: Final MongoDB image
FROM mongodb/mongodb-community-server:8.0-ubi8

COPY --from=tools /tmp/sampledata.archive /tmp/sampledata.archive

COPY --from=tools /opt/tools/bin/mongorestore /usr/local/bin/mongorestore

COPY --chmod=777 entrypoint.sh /usr/local/bin/entrypoint.sh

ENTRYPOINT ["/usr/local/bin/entrypoint.sh"]