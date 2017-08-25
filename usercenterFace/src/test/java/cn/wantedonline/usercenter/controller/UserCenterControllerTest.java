package cn.wantedonline.usercenter.controller;

import cn.wantedonline.common.base.SystemConstant;
import cn.wantedonline.usercenter.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by wangcheng on 12/08/2017.
 */
public class UserCenterControllerTest extends BaseTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private ObjectMapper mapper;


    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.mapper = new ObjectMapper();
    }

    /**
     * 正常情况
     * @throws Exception
     */
    @Test
    public void testRegister1() throws Exception{
        MultiValueMap params = new LinkedMultiValueMap();
        params.add("userName","abcaa");
        params.add("gender", "1");
        params.add("tel","123456789");
        params.add("email","test@123.com");
        params.add("password","abcdefg");

        String value = mockMvc.perform(post("/usercenter/register")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .params(params))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Map<String, Object> valueMap = mapper.readValue(value, Map.class);

        Assert.assertEquals(valueMap.get("message"), "OK");
        Assert.assertEquals(valueMap.get("code"), SystemConstant.HTTP_OK);
    }

    /**
     * 异常情况 用户名字段为空 报 4000
     * @throws Exception
     */
    @Test
    public void testRegister2() throws Exception {
        MultiValueMap params = new LinkedMultiValueMap();
        params.add("userName","");
        params.add("gender", "1");
        params.add("tel","123456789");
        params.add("email","test@123.com");
        params.add("password","abcdefg");

        String value = mockMvc.perform(post("/usercenter/register")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .params(params))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Map<String, Object> valueMap = mapper.readValue(value, Map.class);

        Assert.assertEquals(valueMap.get("message"), "UserName不能为空");
        Assert.assertEquals(valueMap.get("code"), SystemConstant.HTTP_INVALID_PARAMETER);
    }

    /**
     * 异常情况 Service出错 返回 -1
     * @throws Exception
     */
    @Test
    public void testRegister3() throws Exception {
        MultiValueMap params = new LinkedMultiValueMap();
        params.add("userName","abc");
        params.add("gender", "1");
        params.add("tel","123456789");
        params.add("email","test@123.com");
        params.add("password","abcdefg");

        String value = mockMvc.perform(post("/usercenter/register")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .params(params))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Map<String, Object> valueMap = mapper.readValue(value, Map.class);

        Assert.assertEquals(valueMap.get("message"), "服务器异常，请稍后再试");
        Assert.assertEquals(valueMap.get("code"), SystemConstant.HTTP_SERVER_EXCEPTION);
    }


}
