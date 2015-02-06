#!/bin/bash
echo "******CREATING DOCKER DATABASE******"

gosu postgres postgres --single <<- EOSQL
   CREATE DATABASE pub_integration;
   CREATE ROLE userpub WITH LOGIN PASSWORD 'dbuser';
   GRANT ALL PRIVILEGES ON DATABASE pub_integration TO userpub;
EOSQL

echo ""

echo "******DOCKER DATABASE CREATED******"
