#!/bin/bash
dbName=$1
key=$2

if [ -z "$key" ] ; then 
	echo "Please provide a database and key"
	exit 1
fi

# load key from file
key=$(cat $key);
dbName_plain="${dbName}_plain.db"
dbName_enc="${dbName}.db"

# remove existing plain database 
rm -f "$dbName_enc"

echo "Encrypting ${dbName_plain} using key $key"

echo "PRAGMA cipher_default_kdf_iter = 4000; ATTACH DATABASE '${dbName_enc}' AS encrypted KEY '$key'; SELECT sqlcipher_export('encrypted'); DETACH DATABASE encrypted;" | sqlcipher ${dbName_plain}
echo "Done."
