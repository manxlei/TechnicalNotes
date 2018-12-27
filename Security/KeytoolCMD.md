Create 
<JAVA_HOME>\jre\bin>
keytool -genkey -alias serverkey -keyalg RSA -dname "CN=tibco.com, OU=XXX CA, O=Tibco Inc, L=Xian, S=Shaanxi, C=CN" -keypass keypass -storepass storepass -keystore c:\keystore.jks 

keytool -genkey -alias alias.192.168.82.82 -keyalg RSA -dname "CN=192.168.82.82, OU=XXX CA, O=Tibco Inc, L=Xian, S=Shaanxi, C=CN" -keypass keypass -storepass storepass -keystore c:\192.168.82.82.jks 
keytool -slefcert -alias alias.192.168.82.82  -keystore c:\192.168.82.82.jks 

keytool -genkey -keyalg RSA -alias selfsigned -keystore c:\keystore1.jks -storepass storepass -validity 360 -keysize 2048

keytool -genkey -v -alias robin_test_ssl -keyalg RSA -keystore c:\keystore2.jks -dname "CN=localhost,OU=cn,O=cn,L=cn,ST=cn,C=cn" -storepass storepass -keypass keypass  

keytool -certreq -alias serverkey -sigalg "MD5withRSA" -file c:\cert.pem -keypass keypass -keystore c:\keystore.jks  -storepass storepass

Check 
keytool -list -v -keystore c:\keystore.jks -storepass storepass 

Export
keytool -export -alias serverkey -storepass storepass -file c:\server.cer -keystore c:\keystore.jks 
keytool -export -alias alias.192.168.82.82 -storepass storepass -file c:\192.168.82.82.cer -keystore c:\192.168.82.82.jks
keytool -export -alias key -storepass 111111 -file c:\key.cer -keystore c:\key.jks
 
Import
keytool -import -alias serverkey -file server.cer -keystore truststore -storepass storepass

Convert from .cer(or .der) file
keytool -import -v -alias alias -file cert_file -keystore keystore -storepass changeit
Import above .cer(or .der) file into cacerts(JVM Security CA keystore) file
keytool -import -v -alias CAcert -file cert_file -keystore ..\lib\security\cacerts -storepass changeit   //最后的changeit不要改

Delete
keytool -delete -alias "oidserverkey" -keystore ..\lib\security\cacerts -storepass changeit //最后的changeit不要改
