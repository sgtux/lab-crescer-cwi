echo "DROP TABLE IF EXISTS rainbowtable;" > script-rainbow-table.sql
echo "" >> script-rainbow-table.sql
echo "CREATE TABLE rainbowtable (" >> script-rainbow-table.sql
echo "    id SERIAL PRIMARY KEY," >> script-rainbow-table.sql
echo "    texto VARCHAR(200) NOT NULL," >> script-rainbow-table.sql
echo "    hash_md5 VARCHAR(200) NOT NULL," >> script-rainbow-table.sql
echo "    hash_sha1 VARCHAR(200) NOT NULL" >> script-rainbow-table.sql
echo ");" >> script-rainbow-table.sql
echo "" >> script-rainbow-table.sql

for pass in $(cat 10000-top-passwords.txt); do
    hash_md5=`echo -n $pass | md5sum | awk '{print $1}'`
    hash_sha1=`echo -n $pass | sha1sum | awk '{print $1}'`
    echo "INSERT INTO rainbowtable (texto, hash_md5, hash_sha1) VALUES ('$pass', '$hash_md5', '$hash_sha1');" >> script-rainbow-table.sql
done