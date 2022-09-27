INSERT INTO oauth.oauth_client_details (client_id,resource_ids,client_secret,`scope`,authorized_grant_types,web_server_redirect_uri,authorities,access_token_validity,refresh_token_validity,additional_information,autoapprove,create_time) VALUES
	 ('admin','example-product','$2a$10$zNDgAkXKu4xSeupcLlZsKuSpmU75MQDqODhieBZvTMdeNEKwaUoIi','read','authorization_code,password,refresh_token,client_credentials,implicit','http://www.baidu.com',NULL,NULL,NULL,NULL,NULL,'2022-09-27 03:08:30'),
	 ('client_1','server,example-product','$2a$10$zNDgAkXKu4xSeupcLlZsKuSpmU75MQDqODhieBZvTMdeNEKwaUoIi','read','password,refresh_token',NULL,NULL,NULL,NULL,NULL,NULL,'2022-09-27 03:08:30'),
	 ('client_2','xmall-auth,xmall-product','$2a$10$zNDgAkXKu4xSeupcLlZsKuSpmU75MQDqODhieBZvTMdeNEKwaUoIi','read','client_credentials,refresh_token',NULL,NULL,NULL,NULL,NULL,NULL,'2022-09-27 03:08:30');
	 
	 
/**default password : 123456**/
INSERT INTO oauth.users (username,password,enabled) VALUES
('admin','$2a$10$qHHF.ZZeLpa24B8XqN5CKeIjBErPzBUieWGr5fW86jqdCnd0jHIE2',1),
('user','$2a$10$qHHF.ZZeLpa24B8XqN5CKeIjBErPzBUieWGr5fW86jqdCnd0jHIE2',1),
('user_1','$2a$10$qHHF.ZZeLpa24B8XqN5CKeIjBErPzBUieWGr5fW86jqdCnd0jHIE2',1),
('user_2','$2a$10$qHHF.ZZeLpa24B8XqN5CKeIjBErPzBUieWGr5fW86jqdCnd0jHIE2',1);


INSERT INTO oauth.authorities (username,authority) VALUES
('admin','client'),
('user','client');