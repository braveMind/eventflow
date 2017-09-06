# eventflow
基于xml的事件编排框架，简易的轻量级事件流引擎

# EventFlow设计文档

# 一、项目概述
 
Event-Flow（事件流引擎）是一个基于流程图可配置的事件驱动引擎。可以根据流程图的配置流决定事件的流向，解决项目中对流程处理的硬编码问题，在不修改代码的情况下做到流程可配置。
 
#二、 设计思想
把业务处理逻辑和事件分开，业务逻辑描述采用xml元数据的方式进行描述，定义流程跳转条件的布尔条件，事件根据这些条件进行触发。参考（决策树算法），如图：xml流程文件只要描述跳转，在文档中定义每一步跳转需要触发的函数，这些函数提交数据到"流程引擎"流程引擎就可以进行决策下次跳转调用那个事件。

![决策树](http://7xstto.com1.z0.glb.clouddn.com/tree.png)

# 三、模块架构图
如图：

![模块架构图](http://7xstto.com1.z0.glb.clouddn.com/EventFlowEngine.png)


## 模块描述
XML流程文件：用来定义用户流程信息，包括 开始节点，跳转条件，下个节点信息，以及一些触发函数等。  
解析模块：负责解析XML文档信息，生成JVM中的与流程对应的Java对象。  
上下文容器：负责存放流程引擎启动时的一些Bean对象，以及存放流程运行过程中的环境变量等。  
流程模型模块：定义流程中的一些活动对象，连线，决策节点等。  
依赖注入模块：负责流程启动时候，注入流程引擎用到的Java类，以及用户需要的扩展的Java对象等，  
决策判断模块：根据流程定义的EL表达式负责调整流程运行的下个节点。  
缓存模块：流程引擎启动时缓存XML文件，Java对象等。  
用户配置模块：每个节点对象提供用户一些可配置的Handler接口，用户只要实现这些接口，就可以处理自己的业务逻辑，同时根据业务处理结果，可以影响流程引擎下步骤的跳转。  
 
# 四、流程处理时序图
![流程时序图](http://7xstto.com1.z0.glb.clouddn.com/process.png)
 
# 五、项目依赖
cglib-nodep，jackson-mapper-asl，jackson-mapper-asl，commons-beanutils，juel，org.slf4j
 
# 六、引入方式
EventFlow打的是jar包，接入方只要引入jar包后自定义事件流了。

# 七、事件流XML文件描述
 
start：开始节点
transition：节点之间的连线
decision：决策节点，决策事件流向。
task：用户事件
end:结束节点。
# 八、决策支持情况
1，目前决策支持 “排它节点“满足条件只会有一个事件被触发。 
2，支持并接“并行行节点“”，任务会被依次调度。
3，支持事件回调。
 
# 九、与Activiti 对比
 
Activiti是基于BPMN2.0规范的一个开源的工作流引擎，功能比较强大，但是需要与业务紧耦合在一起，比较重量级，需要数据库的支持，用来做审批，流程查询等比较适合企业OA系统。
Event-Flow的关注点是：事件编排。所以不会依赖数据库，对流程信息进行保存，核心代码只有2000多行，适合基于事件驱动的业务逻辑处理。
 
#十、未来展望
 
提供画图工具自动生成XML，以及图片。
根据业务需求，可进行再扩展，例如事件:fork/join 事件之间等待聚合，事件异步执行，事件执行前后可拦截等。
 
#十一、设计参考：
Activiti源码：https://github.com/Activiti/Activiti
Activiti工作流https://www.activiti.org/
BPMN规范：https://camunda.org/bpmn/reference/


# 运行式例

com.event.flow.core.Test 包下。

逻辑执行流程图：

![](http://7xstto.com1.z0.glb.clouddn.com/logic.png)

执行结果：  
![](http://7xstto.com1.z0.glb.clouddn.com/result.png)
