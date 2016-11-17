package sitemonitor.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.HashSet;

import sitemonitor.model.SiteModel;
import sitemonitor.model.Website;

import static org.junit.Assert.*;

/**
 * Tests the lookup service
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LookupServiceTest {

  private SiteModel model;
  private LookupService service;

  @Autowired
  WebApplicationContext context;

  /**
   * Inits data for testing
   */
  @Before
  public void init() {
    this.model = context.getBean(SiteModel.class);
    this.service = context.getBean(LookupService.class);
  }

  @Test
  public void testLookupServiceUpdatesTimes() {
    HashSet<Website> sites = this.model.getSites();

    for (Website site : sites) {
      assertEquals(site.getUpTimes().isEmpty(), true);
    }

    this.service.checkUpTime();

    //Ensure that the sites have data for their uptimes
    for (Website site : sites) {
      assertEquals(site.getUpTimes().isEmpty(), false);
    }

  }

}