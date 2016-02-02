__WARNING__: The following procedures are unofficial and dangerous.
Be aware that you might break your Threema installation. 

__NEVER__ leave your private key on a public computer. Keep it __private__!

_Update_: I wrote a short wrap [blog post](https://seb.wilzba.ch/b/2016/02/decrypting-threema/) 
about my insights.

0) Requirements
---------------

* Java
* sqlite
* [sqlcipher](https://www.zetetic.net/sqlcipher/)
* *nix system

On a Debian-like system it should be as easy as:

```
sudo apt-get install sqlcipher sqlite
```

0) Clone this repository
------------------------

Download this code to your computer

```
git clone https://github.com/greenify/threema-decrypt
cd threema-decrypt
```

1) Obtaining your key
---------------------

Copy `key.dat` and `threema.db` to your computer (root is needed) in this folder.
Exact paths:

```
/data/data/ch.threema.app/files/key.dat
/data/data/ch.threema.app/databases/threema.db
```

2) Create plain-text key
------------------------

We first need to convert the binary key to its string version.

```
javac ThreemaDecrypt.java && java ThreemaDecrypt key.dat > key.plain
```

`key.plain` should look roughly like this. It's a 64 character hex string which
SQLCipher will automatically convert to its 32bytes (256 bits) representation.

```
x"a50b..."
```

3) Decrypt database
-------------------

Now using our fresh plain-text key, we can decrypt the database.

```
./decrypt.sh threema key.plain
```

You now can use any tool like `sqlite3` (CLI) or [SQLiteBrowser](http://sqlitebrowser.org/) (GUI)
to browse through the encrypted database.

__Warning:__ Newer versions of Threema might change the database layout.

Decrypting on-the-fly
---------------------

If you need to make changes, I recommend to work on the encrypted database directly
by opening it with sqlcipher.

```
sqlcipher threema.db
```

To encrypt it enter your full hexadecimal key (with 'x').

```
PRAGMA cipher_default_kdf_iter = 4000;PRAGMA key='x"your-key"';
```


4) Encrypt database
-------------------

You can also encrypt the database. However if you plan to push it make to your
device, I recommend you to use the previous approach and open it directly in
sqlcipher as you might loose your indexes by this procedure.

```
./encrypt.sh threema key.plain
```

Decrypt images/resources
------------------------

You can find the referenced media files in `sdcard/Android/data/ch.threema.app/files/data`.
They are _hidden_ files and the suffix `_T` is obviously for thumbnails.
Once you have copied your desired file to your computer, you can decrypt it with:

```
javac ThreemaDecrypt.java && java ThreemaDecrypt 7bc0df74ca2e40af897152bcf7836624    
```

(where `7bc...` is the to encrypted filename)

Most image viewer should recognize the file format automatically, otherwise
append `.jpg`.

Disclaimer
----------

This procedure and part of its source code resulted from decompiling Threema 
android source. I am not the owner nor legal representative of their intellectual property.
Happy hacking!
