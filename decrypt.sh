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
rm -f "$dbName_plain"

echo "Decrypting $dbName using key $key"
echo "PRAGMA cipher_default_kdf_iter = 4000;PRAGMA key='$key';select count(*) from sqlite_master;ATTACH DATABASE '${dbName_plain}' AS plaintext KEY '';SELECT sqlcipher_export('plaintext');DETACH DATABASE plaintext;" | sqlcipher ${dbName_enc}
echo "Done."
