#!/bin/sh
set -e

echo "Starting MongoDB..."

mongod \
  --bind_ip_all \
  --replSet rs0 &

MONGOD_PID=$!

echo "Waiting for MongoDB to start..."

until mongosh --quiet --eval "db.adminCommand({ ping: 1 })" >/dev/null 2>&1; do
    sleep 2
done

echo "MongoDB is ready"

echo "Initializing replica set..."

mongosh --quiet <<EOF
try {
    rs.status()
} catch (e) {
    rs.initiate({
        _id: "rs0",
        members: [
            {
                _id: 0,
                host: "${HOSTNAME}:27017"
            }
        ]
    })
}
EOF

echo "Waiting for replica set..."

until mongosh --quiet --eval "rs.status().ok" | grep 1 >/dev/null; do
    sleep 2
done

if ! mongosh --quiet --eval 'const exists = db.getMongo().getDBNames().includes("sample_mflix");if (!exists) quit(1);'; then
    echo "Importing sample data"
    mongorestore --archive=/tmp/sampledata.archive
else
    echo "sample_mflix already exists"
fi

echo "MongoDB is running"


echo "Create debezium user"
mongosh --quiet <<EOF
use admin
db.runCommand({
      createRole: 'listDatabases',
      privileges: [
          { resource: { cluster : true }, actions: ['listDatabases']}
      ],
      roles: []
});

db.runCommand({
    createRole: 'readChangeStream',
    privileges: [
        { resource: { db: 'sample_mflix', collection: 'outboxevent'}, actions: [ 'find', 'changeStream' ] }
    ],
    roles: []
});

db.createUser({
    user: 'debezium',
    pwd: 'dbz',
    roles: [
        { role: 'readWrite', db: 'sample_mflix' },
        { role: 'read', db: 'local' },
        { role: 'listDatabases', db: 'admin' },
        { role: 'readChangeStream', db: 'admin' },
        { role: 'read', db: 'config' },
        { role: 'read', db: 'admin' }
    ]
});
EOF

wait $MONGOD_PID