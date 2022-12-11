## Camel有五个组件，允许您以各种方式访问数据库：

* JDBC-Component 允许您从Camel路由访问JDBC API
* SQL-Component 允许您将SQL语句直接写入组件的URI中，以便使用简单查询。此组件还可用于调用存储过程。
* JPA-Component 使用Java Per-sistence体系结构将Java对象持久化到关系数据库。
* Hibernate-Component 使用Hibernate框架持久化Java对象。由于许可不兼容，该组件未随Apache
  Camel一起分发。你可以在骆驼额外项目中找到它  (https://github.com/camel-extra/camel-extra)
* Mybatis-Component 允许您将Java对象映射到关系数据库
