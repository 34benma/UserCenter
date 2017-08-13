package cn.wantedonline.usercenter.controller;

import cn.wantedonline.usercenter.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by wangcheng on 12/08/2017.
 */
public class UserCenterControllerTest extends BaseTest {
    @Autowired
    private UserCenterController userCenterController;
    private MockMvc mockMvc;


    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userCenterController)
                .build();

    }
    @Test
    public void testRegister() throws Exception{
        MultiValueMap params = new LinkedMultiValueMap();
        params.add("userName","tester");
        params.add("gender", "1");
        params.add("tel","123456789");
        params.add("email","test@123.com");
        params.add("password","abcdefg");
        mockMvc.perform(post("/usercenter/register")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .params(params))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }
}
