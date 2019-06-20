package com.example.demo2;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;
import java.io.InputStream;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {springbootwithActivityDemo.class})
@WebAppConfiguration
@IntegrationTest
public class Demo2ApplicationTests {

    @Autowired
    private ProcessEngine processEngine;

    @Test
    public void contextLoads() {
        //获取仓库服务 ：管理流程定义
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()//创建一个部署的构建器
                .addClasspathResource("processes/expression-service-process.bpmn")//从类路径中添加资源,一次只能添加一个资源
                .name("请求单流程")//设置部署的名称
                .category("办公类别")//设置部署的类别
                .deploy();

        System.out.println("部署的id"+deploy.getId());
        System.out.println("部署的名称"+deploy.getName());


    }


    //查看bpmn 资源图片
    @Test
    public void viewImage() throws Exception{
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()//创建一个部署的构建器
                .addClasspathResource("processes/expression-service-process.bpmn")//从类路径中添加资源,一次只能添加一个资源
                .name("请求单流程")//设置部署的名称
                .category("办公类别")//设置部署的类别
                .deploy();
        String deploymentId="5";
        String imageName=null;
        //取得某个部署的资源的名称  deploymentId
        List<String> resourceNames = processEngine.getRepositoryService().getDeploymentResourceNames(deploymentId);
        // buybill.bpmn  buybill.png
        if(resourceNames!=null&&resourceNames.size()>0){
            for(String temp :resourceNames){
                if(temp.indexOf(".png")>0){
                    imageName=temp;
                }
            }
        }

        /**
         * 读取资源
         * deploymentId:部署的id
         * resourceName：资源的文件名
         */
        InputStream resourceAsStream = processEngine.getRepositoryService()
                .getResourceAsStream(deploymentId, imageName);

        //把文件输入流写入到文件中
        File file=new File("/Users/liuyf/Desktop/"+imageName);
        FileUtils.copyInputStreamToFile(resourceAsStream, file);
    }


}
