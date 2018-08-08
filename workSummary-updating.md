# 1.MyBaits && MyBatisPlus

## 1.1 mybatis 比较符的写法

```mysql
第一种写法（1）：
原符号       <        <=      >       >=       &        '        "
替换符号    &lt;    &lt;=   &gt;    &gt;=   &amp;   &apos;  &quot;
例如：sql如下：
create_date_time &gt;= #{startTime} and  create_date_time &lt;= #{endTime}

第二种写法（2）：
大于等于
<![CDATA[ >= ]]>
小于等于
<![CDATA[ <= ]]>
例如：sql如下：
create_date_time <![CDATA[ >= ]]> #{startTime} and  create_date_time <![CDATA[ <= ]]> #{endTime}
```



# 2.Java 基础

# 3.RabbitMQ

# 4.Redis

# 5.Mysql

# 6.其他

## 6.1 excel批量生成sql语句

  	需求背景：用户直接给我们发过来一些数据，要求我们把这些数据导入到数据库中，对于少量的数据来说，用最原始的方法就可以解决，直接在SQL里面用语句来实现，但是如果有成千上万条的数据呢？如果你还继续单独写SQL语句，估计写个几十条你就会有跳楼的冲动，其实有两种简单的方法：

   1、将Excel的数据整理好，通过SQL的导入功能直接导入到数据库中，但是要保证数据库的字段和Excel的字段一致。

   2（**推荐**）、通过Excel生成相应的SQL语句，然后，放到SQL的新建查询中，执行。

​	增加一列（H列）

​	在第一行的D列，就是D1中输入公式：="insert into tms_fee_type (val,name) value('"&A2&"','"&B2&"');"

```mysql
insert into tms_fee_type (val,name) value('"&A2&"','"&B2&"');
```

​	此时H1已经生成了如下的sql语句：

```mysql
 insert into tms_fee_type (val,name) values ('1','提货费');    
```

  然后直接从H列头拉到尾，你会发现所有的数据都有对应的脚本，然后直接复制相关的SQL语句，到分析器中，F5,OK,任务完成！

![img](.\image\work\excel2sql.jpg)

 