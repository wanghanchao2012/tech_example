```xml
<!-- jwt -->
<dependency>
    <groupId>com.auth0</groupId>
    <artifactId>java-jwt</artifactId>
    <version>3.8.1</version>
</dependency>
```



```java
eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9
.eyJ1aWQiOjEsInNjb3BlIjo4LCJleHAiOjE3MTU3NDAyMjIsImlhdCI6MTYyOTM0MDIyMn0
.wuRsF5wvLHbDF_21Pocas8SeXQ315rgBl6wm1LRL2bQ
```



1. Header（头部）// Header 部分是一个 JSON 对象，描述 JWT 的元数据，通常是下面的样子

2. Payload（负载）//Payload 部分是一个 JSON 对象，用来存放实际需要传递的数据

3. Signature（签名）//Signature 部分是对前两部分的签名，防止数据篡改

   

- 第一段字符串Header：`eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9`将其 Base64 解码后得到：

  

  ```json
  {
  	"typ": "JWT", // TOKEN TYPE ,token类型
  	"alg": "HS256"  //ALGORITHM，算法 哈希256
  }
  ```

- 第二段字符串(PAYLOAD是数据载体，可以有自定义数据)

  Payload：`eyJ1aWQiOjEsInNjb3BlIjo4LCJleHAiOjE3MTU3NDAyMjIsImlhdCI6MTYyOTM0MDIyMn0`

  ```json
  {
    "uid": "1234567890" // 自定义数据
  }
  ```

  

- 第三段字符串Signature：`wuRsF5wvLHbDF_21Pocas8SeXQ315rgBl6wm1LRL2bQ`

Signature 部分是对前两部分的签名，防止数据篡改。



生成token

```java
   //secret 加密串儿,自定义
	 Algorithm algorithm = Algorithm.HMAC256(secret);
   String token = JWT.create()
      .withClaim("name", "melody")
      .withClaim("age", 20)
      .sign(algorithm);
```

验证token

```java
 Algorithm algorithm = Algorithm.HMAC256(secret); //use more secure key
 JWTVerifier verifier = JWT.require(algorithm).build(); //Reusable verifier instance
 DecodedJWT jwt = verifier.verify(token);
 //token第二部分得到的参与生成token的键值对
 Map<String, Claim> claims = jwt.getClaims();
 //得到参与生成token的参数值
 System.out.println(claims.get("username").asString());
```

