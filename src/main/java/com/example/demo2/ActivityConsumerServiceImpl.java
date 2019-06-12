package com.example.demo2;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 刘宇飞 on 2019-06-12.
 */
@Service("activityService")
public class ActivityConsumerServiceImpl implements ActivityConsumerService {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @Override
    public boolean startActivityDemo() {
        System.out.println("method startActivityDemo begin....");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("apply","zhangsan");
        map.put("approve","lisi");
//流程启动
        ExecutionEntity pi1 = (ExecutionEntity) runtimeService.startProcessInstanceByKey("leave",map);
        String processId = pi1.getId();
        String taskId = pi1.getTasks().get(0).getId();
        System.out.println("流程实例id:"+pi1.getId());//流程实例id
        System.out.println("流程定义id:"+pi1.getProcessDefinitionId());//输出流程定义的i
        taskService.complete(taskId, map);//完成第一步申请

        Task task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
        String taskId2 = task.getId();
        map.put("pass", true);
        System.out.println("任务的办理人："+task.getAssignee());
        System.out.println("任务的id："+task.getId());
        System.out.println("任务的名称："+task.getName());
        taskService.complete(taskId2, map);//驳回申请
        System.out.println("任务的办理人："+task.getAssignee());
        System.out.println("任务的id："+task.getId());
        System.out.println("任务的名称："+task.getName());
        System.out.println("method startActivityDemo end....");
        return false;
    }
}
