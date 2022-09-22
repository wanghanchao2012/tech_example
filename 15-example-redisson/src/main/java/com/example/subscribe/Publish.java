package com.example.subscribe;

import com.example.dto.Consts;
import com.example.dto.MyObjectDTO;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Publish {
    @Autowired
    private RedissonClient redissonClient;

    //发布
    public long publish(MyObjectDTO myObjectDTO){
        RTopic rTopic = redissonClient.getTopic(Consts.TopicName);
        return  rTopic.publish(myObjectDTO);
    }

}
