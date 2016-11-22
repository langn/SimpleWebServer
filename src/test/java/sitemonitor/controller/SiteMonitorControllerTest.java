package sitemonitor.controller;

import com.jayway.jsonpath.JsonPath;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Tests the site monitor controller
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SiteMonitorControllerTest {


  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext context;



  /**
   * Sets up data for the tests
   */
  @Before
  public void init() throws Exception {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    mockMvc.perform(get("/addsite?url=https://google.com"));
  }

  @Test
  public void badURLReturnsCorrectPage() throws Exception {
    mockMvc.perform(get("/addsite?url=ht:/badurl.com"))
            .andExpect(status().isOk())
            .andExpect(view().name("badUrl"));
  }

  @Test
  public void testAddSitePageCorrect() throws Exception {
    mockMvc.perform(get("/addsite?url=https://google.com"))
            .andExpect(status().isOk())
            .andExpect(view().name("addSite"));
    mockMvc.perform(get("/addsite?url=https://youtube.com"))
            .andExpect(status().isOk())
            .andExpect(view().name("addSite"));
  }


  @Test
  public void testReturnData() throws Exception {
    MvcResult result = mockMvc.perform(get("/getsites"))
            .andExpect(status().isOk())
            .andReturn();
    String resultString = result.getResponse().getContentAsString();
    assertTrue(resultString.contains("https://google.com"));
    assertTrue(resultString.contains("upTimes"));
  }
}