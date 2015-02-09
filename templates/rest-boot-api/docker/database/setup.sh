#!/bin/bash
echo "******CREATING DOCKER DATABASE******"

gosu postgres postgres --single <<- EOSQL
   CREATE DATABASE ${jdbcDb};
   CREATE ROLE ${jdbcUsername} WITH LOGIN PASSWORD '${jdbcPassword}';
   GRANT ALL PRIVILEGES ON DATABASE ${jdbcDb} TO ${jdbcUsername};
EOSQL

echo ""

echo "******DOCKER DATABASE CREATED******"
