sessionID在一个客户端请求过来时，判断用户cookie中是否携带了JSESSIONID
如果cookie中不存在该SessionID则由org.apache.catalina.valves.CrawlerSessionManagerValve.getClientIdentifier
该方法创建一个sessionID，在返回给用户时写到cookie中，后续用再次请求时用户的cookie便携带了该sessionID来建立后续的session会话，及过期时间等由tomcat容器来管理

<img width="850" alt="image" src="https://user-images.githubusercontent.com/35331347/163554134-5217fcc4-3a4c-417e-a8ca-2c6b9fc8e366.png">
