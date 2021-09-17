mv application.properties library/target/application.properties
cd library/target
keytool -genkey -alias spring -storetype PKCS12 -keystore keystore.p12 -keyalg RSA -keysize 2048 \
        -validity 10000 -dname "cn=Unknown, ou=Unknown, o=Unknown, c=Unknown" \
        -storepass $KEY_PASSWORD -keypass $KEY_PASSWORD
java -jar library-0.0.1-SNAPSHOT.jar