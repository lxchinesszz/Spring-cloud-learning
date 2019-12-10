# Spring-cloud-learning

SpringCloud集群负载均衡及全链路监控


![](http://wx2.sinaimg.cn/mw690/0060lm7Tly1fsy09ih3p6j311905bt9c.jpg)


## 前言

### 我们要从那些解读去考虑技术选型?

- 服务器(云服务提供商是否稳定)
- 代码层面(技术选型+代码质检Sonarqube) https://www.toutiao.com/i6501805549015794189/
- 数据库(是否面临高并发或大数据问题)

---

### 解决的问题是什么?

**微服务错误及耗时定位分析困难**

- 具体解决方案: 引入ZipKin进行抽样监控

**微服务错误引发雪崩现象**

- 具体解决方案: hystrix及时熔断
- 具体解决方案 : hystrix流量洪峰监控

**微服务日志拆分导致查看异常困难**

- 具体解决方案: ELK日志分析
- 具体解决方案: Sentry 日志异常通知

**微服务配置变更**

- 具体解决方案: eureka注册中心/ consul

**紧急备用方案**

- 快速部署 docker技术

## 目录

1. 以集群的方式启动Eureka

2. 注册服务

3. Ribbon目录做负载均衡,及熔断错误

4. 提供测试DTO

5. Hystrix演示

5.1 熔断聚合及监控

6. 全链路追踪

6.1 如何在保证链路监控有效的情况下,对其进行监控!

7. 注册中心比较及Consul使用

7.1 mac安装Consul

8. 消息总线 Spring Cloud Bus

9. 高可用分布式配置中心Spring Cloud Config

10. 负载均衡策略

10.1 阿里云基础架构

### 框架目录

![](https://p3.pstatp.com/large/pgc-image/1524386268001fa3d7ed585)

![](https://p3.pstatp.com/large/pgc-image/15243862797652a230b534a)

#### 1.以集群的方式启动Eureka

登录账号: blm 密码: blm123

Spring-learning-eureka-01 端口: 10001 注册实例名: eureka-service-01 应用名:eureka-service

Spring-learning-eureka-01 端口: 10002 注册实例名: eureka-service-02 应用名:eureka-service

![](https://p1.pstatp.com/large/pgc-image/1524386394795aad7dc74df)

#### 2.注册服务

Spring-learning-service-01 端口: 20001 注册实例名: eureka-provider-service-01 应用名:eureka-provider-service

Spring-learning-service-02 端口: 20002 注册实例名: eureka-provider-service-02 应用名:eureka-provider-service

![](https://p3.pstatp.com/large/pgc-image/15243864272609cd459f95f)

注意:

当注册实例名都为eureka-provider-service时候就启动了负载均衡,从注册中查询服务

![](https://p1.pstatp.com/large/pgc-image/15243864628828a76f9a333)

#### 3.Ribbon目录做负载均衡,及熔断错误

Spring-learning-ribbon-consume 端口: 30000 注册实例: eureka-ribbon 应用名: eureka-ribbon

建议注册名和应用名统一命名,ribbon会根据spring.application.name知道服务地址,也就是应用名

![](https://p3.pstatp.com/large/pgc-image/152438651819504b350bdf0)

#### 提供测试DTO

Spring-learning-model

如果用idea导入,service-01和02依赖改模块,需要添加该模块

![](https://p3.pstatp.com/large/pgc-image/1524386542273ac5ca9705a)

#### 5.Hystrix演示

![](https://p1.pstatp.com/large/pgc-image/15243865663296e1931549b)

- 负载均衡操作
均匀分散在 eureka-provider-service 暴路的地址

![](https://p3.pstatp.com/large/pgc-image/1524386605198e4354cd70d)
![](https://p9.pstatp.com/large/pgc-image/15243866192259109714f84)

#### 5.1 熔断聚合及监控
Spring-learning-ribbon-consume加入熔断依赖,

![](https://p9.pstatp.com/large/pgc-image/1524386700526ee3e2f2091)
启动类添加@EnableCircuitBreaker就可访问
![](https://p3.pstatp.com/large/pgc-image/1524386724034e018479979)

如果要访问页面,要新添加依赖,并在启动类,添加@EnableHystrixDashboard

![](https://p1.pstatp.com/large/pgc-image/15243867454942f435a14b9)

![](https://p3.pstatp.com/large/pgc-image/1524386772171a667d3460c)

即可访问

![](https://p3.pstatp.com/large/pgc-image/1524386789721531186f07e)

关闭Spring-learning-service-01和-02然后调动https://blm:blm123@lxchinesszz:30000/ribbon/user,进行熔断


![](https://p3.pstatp.com/large/pgc-image/152438681222500eb1c9fb0)

#### 6. 全链路追踪
Zipkin为分布式链路调用监控系统，聚合各业务系统调用延迟数据，达到链路调用监控跟踪。

追踪一次请求

基于微服务的链路追踪,当遇到请求第三方服务时候就无法,实现,

所以还有一种方法,就是给http请求做代理,拦截请求加上span,然后http发送给Zipkin服务器进行统计

spring-cloud-sleuth收集信息是有一定的比率的，默认的采样率是0.1，配置此值的方式在配置文件中增加spring.sleuth.sampler.percentage参数配置（如果不配置默认0.1），如果我们调大此值为1


![](https://p3.pstatp.com/large/pgc-image/15243869161368d51493079)

![](https://p3.pstatp.com/large/pgc-image/152438694650901cc38c368)
![](https://p1.pstatp.com/large/pgc-image/15243869557101a0d067a24)
![](https://p3.pstatp.com/large/pgc-image/15243869562045a0098c3a2)


**参考文档:** https://yq.aliyun.com/articles/78128?spm=5176.100239.blogcont78144.18.a7IIwu

其实，我们仔细想想也可以总结出这种方式的几种缺陷

**缺陷1：**zipkin客户端向zipkin-server程序发送数据使用的是http的方式通信，每次发送的时候涉及到连接和发送过程。

**缺陷2：**当我们的zipkin-server程序关闭或者重启过程中，因为客户端收集信息的发送采用http的方式会被丢失。

##### 针对以上两个明显的缺陷，改进的办法是

1、通信采用socket或者其他效率更高的通信方式。

2、客户端数据的发送尽量减少业务线程的时间消耗，采用异步等方式发送收集信息。

3、客户端与zipkin-server之间增加缓存类的中间件，例如redis、MQ等，在zipkin-server程序挂掉或重启过程中，客户端依旧可以正常的发送自己收集的信息。

相信采用以上三种方式会很大的提高我们的效率和可靠性。其实spring-cloud以及为我们提供采用MQ或redis等其他的采用socket方式通信，利用消息中间件或数据库缓存的实现方式。下一次我们再来测试spring-cloud-sleuth-zipkin-stream方式的实现。

#### 6.1 如何在保证链路监控有效的情况下,对其进行监控!
1. 引入spring-cloud-sleuth-zipkin-stream & spring-cloud-starter-stream-rabbit

![](https://p1.pstatp.com/large/pgc-image/1524387040312fbee86f0fc)

![](https://p1.pstatp.com/large/pgc-image/1524387048811d35b7465f8)

#### 7. 网关转发模块Zuul
正在完善，敬请期待，会有专篇，讲述！

8. 注册中心比较及Consul使用
参考文档： https://www.consul.io/intro/vs/eureka.html

![](https://note.youdao.com/yws/api/personal/file/WEB4a51d027813a11cf02afd3a2f3d97b3a?method=download&shareKey=19007eee98bdcb7da062f21bab44b57b)

#### 8.1 mac安装Consul
brew install consul
![](https://p3.pstatp.com/large/pgc-image/15243871323925c79bdd844)

https://127.0.0.1:8500/ui/#/dc1/services

![](https://p1.pstatp.com/large/pgc-image/152438714379582d43eb212)

#### 9. 消息总线 Spring Cloud Bus
在前面我们学习的文章中,我们学习了EventBus,从设计模式上来讲,是事件模式,原理即: 当服务引入bus组件,会将自动订阅,config-server-bus在rabbit或者kafka的队列,当向config-server-bus发起/bus/refresh更新事件的时候,会想所有订阅的服务,发起更新命令,即从新从config-server-bus中拉去配置信息。 这点可以参考小编之前的文章

#### 10. 高可用分布式配置中心Spring Cloud Config

参考文档：https://yq.aliyun.com/articles/169879?spm=5176.100239.blogcont173263.25.MgI7lF

![](https://p9.pstatp.com/large/pgc-image/15243872721003f346346ce)

创建config-server,和config-client并引入响应依赖,注意config-client一定要引入web模块,否则启动时候会自动注销

**流程:**

config-server注册到eureka-1/2, config-client注册到eureka-1/2,并获取到config-server地址,从中读取配置信息,其中配置信息从git上面读取,可以配置用户信息

![](https://p9.pstatp.com/large/pgc-image/15243873034076f254a50f7)

![](https://p1.pstatp.com/large/pgc-image/152438732584122df15b8c7)

验证配置

启动config-server(端口号10003,启动顺序eureka-1 eureka-2 zipkin config-server config-client) 并

![](https://p3.pstatp.com/large/pgc-image/1524387364893bd7d1201a5)

配置文件命名规则:
![](https://p3.pstatp.com/large/pgc-image/15243873790049a9c79ed91)

**如何从config-server服务器,验证配置文件**

https://localhost:10003/{applicationName}/{profile环境}/{lable分支}

11. 负载均衡策略
负载均衡（Server Load Balancer）是对多台云服务器进行流量分发的负载均衡服务。负载均衡可以通过流量分发扩展应用系统对外的服务能力，通过消除单点故障提升应用系统的可用性。

SLB通过购买云服务提供商的负载均衡服务(服务器根据供应商购买),然后配置

![](https://p3.pstatp.com/large/pgc-image/1524387429420b24038fc10)

性能保障型实例的三个关键指标如下：

最大连接数-Max Connection

最大连接数定义了一个负载均衡实例能够承载的最大连接数量。当实例上的连接超过规格定义的最大连接数时，新建连接请求将被丢弃。

每秒新建连接数-Connection Per Second (CPS)

每秒新建连接数定义了新建连接的速率。当新建连接的速率超过规格定义的每秒新建连接数时，新建连接请求将被丢弃。

每秒查询数-Query Per Second (QPS)

每秒请求数是七层监听特有的概念，指的是每秒可以完成的HTTP/HTTPS的查询（请求）的数量。当请求速率超过规格所定义的每秒查询数时，新建连接请求将被丢弃。

#### 11.1 阿里云基础架构
负载均衡采用集群部署，可实现会话同步，以消除服务器单点故障，提升冗余，保证服务的稳定性。阿里云当前提供四层（TCP协议和UDP协议）和七层（HTTP和HTTPS协议）的负载均衡服务。

四层采用开源软件LVS（Linux Virtual Server）+ keepalived的方式实现负载均衡，并根据云计算需求对其进行了个性化定制。四层负载均衡实际上是由多台LVS机器部署成一个LVS集群来运行的。采用集群部署模式极大地保证了异常情况下负载均衡服务的可用性、稳定性与可扩展性。工作在TCP/IP协议的四层，其转发是依赖于四层协议的特征进行转发的，由于其转发要依赖于协议的特征进行转发，因此需要在内核的TCP/IP协议栈进行过滤筛选。

如果是四层监听，关注的重点是长连接的并发连接数，那么最大（并发）连接数应当作为一个关键指标来参考。根据不同的业务场景，您需要预估一个负载均衡实例需要承载的最大并发连接数，并选择相应的规格。

![](https://p1.pstatp.com/large/pgc-image/15243874903253d58a5b826)

七层采用Tengine实现负载均衡。Tengine是由淘宝网发起的Web服务器项目，它在Nginx的基础上，针对有大访问量的网站需求，添加了很多高级功能和特性。七层负载均衡工作在OSI模型的应用层，因为它需要解析应用层流量，所以七层负载均衡在接到客户端的流量以后，还需要一个完整的TCP/IP协议栈。七层负载均衡会与客户端建立一条完整的连接并将应用层的请求流量解析出来，再按照调度算法选择一个应用服务器，并与应用服务器建立另外一条连接将请求发送过去，因此七层负载均衡的主要工作就是代理。

针对7层（HTTP协议和HTTPS协议）服务，负载均衡系统是基于Cookie的会话保持。负载均衡系统提供了两种Cookie处理方式:

植入Cookie: 此种方法下，您只需要指定cookie的过期时间。客户端第一次访问时，负载均衡服务在返回请求中植入cookie（即在HTTP/HTTPS响应报文中插入SERVERID字串），下次客户端携带此cookie访问，负载均衡服务会将请求定向转发给之前记录到的ECS实例上。

重写Cookie: 此种方式下，您可以根据需要指定HTTPS/HTTP响应中插入的cookie。您需要在后端ECS上维护该cookie的过期时间和生存时间。负载均衡服务发现用户自定义了cookie，将会对原来的cookie进行重写，下次客户端携带新的cookie访问，负载均衡服务会将请求定向转发给之前记录到的ECS实例上。

如果是七层监听，关注的重点是QPS的性能，QPS决定了一个七层应用系统的吞吐量。同样，您也需要根据经验对QPS进行预估。在初步选定一个规格后，在业务压测和实测过程中对规格进行微调。

####  12 .架构设计之高可用

- 服务层

nginx活性keepalive检测,域名连接代替ip连接

- UI层到-服务层

静态资源cdn加速

- 服务层到-数据层

读写分离,连接关闭,数据库集群部署

- 多机房冗余

单机房无论多么冗余，多么牛逼，当施工队靠近，自然灾害发生，还是面临不可用，这时我们需要部署多机房高可用，服务多机房同活很容易，但是对于需要持久化的存储层则需要采用光纤直连，设置保证分布式多机房的情况，至少保证其中两个机房事务已经落盘，才返回给用户成功。当然会牺牲部分速度。

高可用是架构设计上必须考虑的问题，主要是指减少系统因为某些不可抗拒原因带来的不可用时间。方法论：冗余部署+自动故障转移+自动服务降级+自动服务隔离等



