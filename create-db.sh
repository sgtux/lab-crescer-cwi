#!/bin/bash
export PGPASSWORD=Postgres123
psql -U postgres -h 172.45.45.10 -d postgres -a -f ./dvsn-api/script.sql