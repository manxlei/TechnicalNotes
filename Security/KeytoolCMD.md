## 生成自签名证书：
* https://www.sslshopper.com/article-how-to-create-a-self-signed-certificate-using-java-keytool.html
* http://blog.csdn.net/lapiggy/article/details/2808124
* http://blog.csdn.net/tony1130/article/details/5134318

## 数字证书
* 数字证书: http://www.cnblogs.com/hyddd/archive/2009/01/07/1371292.html
* 数字证书和SSL: http://www.2cto.com/Article/201203/121534.html 
* 数字签名: http://www.ruanyifeng.com/blog/2011/08/what_is_a_digital_signature.html

## Java数字证书体系
* http://snowolf.iteye.com/blog/391931
* http://snowolf.iteye.com/blog/383412

## 常见KeyStore文件格式
格式    :  JKS 
扩展名  : .jks/.ks
描述    : 【Java Keystore】密钥库的Java实现版本，provider为SUN
特点    :  密钥库和私钥用不同的密码进行保护
 
格式    :  JCEKS 
扩展名  :  .jce
描述    : 【JCE Keystore】密钥库的JCE实现版本，provider为SUN JCE
特点    :  相对于JKS安全级别更高，保护Keystore私钥时采用TripleDES
 
格式    :  PKCS12
扩展名  :  .p12/.pfx
描述    : 【PKCS #12】个人信息交换语法标准
特点    :  1、包含私钥、公钥及其证书
           2、密钥库和私钥用相同密码进行保护
 
格式    :  BKS 
扩展名  : .bks
描述    :  Bouncycastle Keystore】密钥库的BC实现版本，provider为BC
特点    :  基于JCE实现
 
 格式   : UBER
 扩展名 : .ubr
 描述   : 【Bouncycastle UBER Keystore】密钥库的BC更安全实现版本，provider为BC

## 证书文件格式【Certificate】 
格式    :  DER 
扩展名  :  .cer/.crt/.rsa
描述    : 【ASN .1 DER】用于存放证书 
特点    :  不含私钥、二进制
 
格式    :  PKCS7 
扩展名  : .p7b/.p7r 
描述    : 【PKCS #7】加密信息语法标准
特点    : 1、p7b以树状展示证书链，不含私钥
          2、p7r为CA对证书请求签名的回复，只能用于导入
 
格式    :  CMS 
扩展名  :  .p7c/.p7m/.p7s 
描述    : 【Cryptographic Message Syntax】 
特点    : 1、p7c只保存证书
          2、p7m：signature with enveloped data
          3、p7s：时间戳签名文件
 
格式    :  PEM 
扩展名  : .pem 
描述    : 【Printable Encoded Message】 
特点    : 1、该编码格式在RFC1421中定义，其实PEM是【Privacy-Enhanced Mail】的简写，但他也同样广泛运用于密钥管理
          2、ASCII文件
          3、一般基于base 64编码
 
格式    :  PKCS10 
扩展名  : .p10/.csr 
描述    : 【PKCS #10】公钥加密标准【Certificate Signing Request】
特点    :  1、证书签名请求文件
           2、ASCII文件
           3、CA签名后以p7r文件回复
 
格式    :  SPC 
扩展名  : .pvk/.spc 
描述    : 【Software Publishing Certificate】 
特点    :  微软公司特有的双证书文件格式，经常用于代码签名，其中
           1、pvk用于保存私钥
           2、spc用于保存公钥


## 常用命令
* Create 
<JAVA_HOME>\jre\bin>
keytool -genkey -alias serverkey -keyalg RSA -dname "CN=tibco.com, OU=XXX CA, O=Tibco Inc, L=Xian, S=Shaanxi, C=CN" -keypass keypass -storepass storepass -keystore c:\keystore.jks 

keytool -genkey -alias alias.192.168.82.82 -keyalg RSA -dname "CN=192.168.82.82, OU=XXX CA, O=Tibco Inc, L=Xian, S=Shaanxi, C=CN" -keypass keypass -storepass storepass -keystore c:\192.168.82.82.jks 
keytool -slefcert -alias alias.192.168.82.82  -keystore c:\192.168.82.82.jks 

keytool -genkey -keyalg RSA -alias selfsigned -keystore c:\keystore1.jks -storepass storepass -validity 360 -keysize 2048

keytool -genkey -v -alias robin_test_ssl -keyalg RSA -keystore c:\keystore2.jks -dname "CN=localhost,OU=cn,O=cn,L=cn,ST=cn,C=cn" -storepass storepass -keypass keypass  

keytool -certreq -alias serverkey -sigalg "MD5withRSA" -file c:\cert.pem -keypass keypass -keystore c:\keystore.jks  -storepass storepass

* Check 
keytool -list -v -keystore c:\keystore.jks -storepass storepass 

* Export
keytool -export -alias serverkey -storepass storepass -file c:\server.cer -keystore c:\keystore.jks 
keytool -export -alias alias.192.168.82.82 -storepass storepass -file c:\192.168.82.82.cer -keystore c:\192.168.82.82.jks
keytool -export -alias key -storepass 111111 -file c:\key.cer -keystore c:\key.jks
 
* Import
keytool -import -alias serverkey -file server.cer -keystore truststore -storepass storepass

* Convert from .cer(or .der) file
keytool -import -v -alias alias -file cert_file -keystore keystore -storepass changeit
Import above .cer(or .der) file into cacerts(JVM Security CA keystore) file
keytool -import -v -alias CAcert -file cert_file -keystore ..\lib\security\cacerts -storepass changeit   //最后的changeit不要改

* Delete
keytool -delete -alias "oidserverkey" -keystore ..\lib\security\cacerts -storepass changeit //最后的changeit不要改
