# springbootwithActivitiDemo

##springboot跑起来的Activiti例子

1. h2数据库
		
		http://localhost:8987/h2-console/
		
		JDBC URL : jdbc:h2:mem:activiti
		

 ![MacDown Screenshot](https://raw.githubusercontent.com/liuyf90/doc/master/pic/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-06-12%20%E4%B8%8B%E5%8D%889.42.29.png) 

2. http://localhost:8987/activityService/startActivityDemo
   
     	观察数据库中activiti表的变化
     	
     	
3. 服务启动后执行
		
		curl localhost:8987/activityService/start-my-process
		
		观察控制台输出