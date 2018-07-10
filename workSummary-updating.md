# 1.MyBaits && MyBatisPlus

## 1.1 mybatis中大于等于小于等于的写法

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

