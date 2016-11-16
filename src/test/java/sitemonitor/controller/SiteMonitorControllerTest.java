package sitemonitor.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;

/**
 * Tests the site monitor controller
 */
@RunWith(SpringRunner.class)
@WebMvcTest(SiteMonitorController.class)
public class SiteMonitorControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void badURLReturnsCorrectPage() {

  }

  @Test
  public void testAddSitePageCorrect() {

  }

  @Test
  public void testReturnData() {

  }
}