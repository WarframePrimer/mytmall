# Apache shiro
- 鉴别用户身份
- 管理用户权限，例如：判断用户是否有某个角色或用户是否被允许做某一操作
- 即使没有web或EJB容器，也可以使用session API
- 在鉴别用户身份时，权限管理时或session生命周期内进行一些操作
- 可以聚合一个或多个用户权限数据源并且以用户视图的形式统一表现出来
- 实现了单点登录功能(SSO)  
...

Shiro的目标是做好Authentication(身份识别)、Authorization(权限管理)、Session Management(会话管理)、Cryptography(加密)四个方面(软件安全的四大基石)。
- Authentication(身份识别):鉴别用户的身份
- Authorization(权限管理):决定用户是否有权访问某物
- Session Management(会话管理):即使不是web应用也不是EJB应用，仍然可以管理具有明确用户的session
- Cryptography(加密):利用加密算法保证数据安全

- Shiro

