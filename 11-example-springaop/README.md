reference:https://gitee.com/bryan31/aspect-log

shiro启动流程:

1.初始化加载 ini文件
2.根据初始化工厂得到serurityManager,并又SecurityUtils保存
3.通过SecurityUtils.getSubject得到当前登陆用户信息,且可操作用户的session(当前session不需要Tomcat或EJB容器)
4.判断是否认证,没有认证进入认证流程,认证过了直接进入授权流程
5.权限验证,有正常走,没有则抛出异常