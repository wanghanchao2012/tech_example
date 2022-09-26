1.测试需要启动redis，

2.直接get访问localhost:8080/public/1  localhost:8080/private/1

3.使用http工具post访问password模式http://localhost:8080/oauth/token?username=user_1&password=123456&grant_type=password&scope=select&client_id=client_2&client_secret=123456

使用http工具post访问client模式http://localhost:8080/oauth/token?grant_type=client_credentials&scope=select&client_id=client_1&client_secret=123456

4.再次带上获取的tokenget访问http://localhost:8080/private/1?access_token=bd77315b-5f83-433f-a4aa-b9f20b89ff34

http://localhost:8080/private/1?access_token=d6912dd2-347d-4e64-98a3-922380dab7a0
