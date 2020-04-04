package com.example.demo;

import com.example.demo.entity.UserInfo;
import com.example.demo.mapper.UserInfoMapper;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AllArgsConstructor
public class DemoApplicationTests {

    private UserInfoMapper userInfoMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<UserInfo> userList = userInfoMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

}
