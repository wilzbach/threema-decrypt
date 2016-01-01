
1) Create plain-text key
------------------------

```
javac ThreemaDecrypt.java && java ThreemaDecrypt key.dat > key.plain
```

2) Decrypt database
-------------------

```
./decrypt.sh threema key.plain
```

3) Encrypt database
-------------------

```
./encrypt.sh threema key.plain
```

Decrypt images/resources
------------------------

```
javac ThreemaDecrypt.java && java ThreemaDecrypt 7bc0df74ca2e40af897152bcf7836624    
```
