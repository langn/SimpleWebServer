package sitemonitor.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;


import static org.junit.Assert.*;

/**
 * Test methods of the {@link SiteModel} class
 */
public class SiteModelTest {

  //Instance variables
  Website google;
  Website youtube;
  Website notReal;

  SiteModel model;

  @Before
  public void init() {
    this.google = new Website("https://google.com");
    this.youtube = new Website("https://youtube.com");
    this.notReal = new Website("http://nationaltreasure3isgonnabeagoodfilm.com");

    this.model = new SiteModel();
    this.model.addSite(this.google);
    this.model.addSite(this.youtube);
    this.model.addSite(this.notReal);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadURLNotAllowed() {
    this.model.addSite(new Website("I am not a URL"));
  }

  @Test
  public void testAddSite() {
    Website github = new Website("https://github.io");
    assertEquals(this.model.getSites().size(), 3);
    this.model.addSite(github);
    assertEquals(this.model.getSites().size(), 4);
  }

  @Test
  public void testGetAndCheckSites() {
    this.model.checkSites();
    try {
      Thread.sleep(2000);
    }
    catch (InterruptedException e) {}
    this.model.checkSites();
    ArrayList<Website> sites = this.model.getSites();
    assertEquals(sites.get(0).getUpTimes().containsValue(true), true);
    assertEquals(sites.get(0).getUpTimes().containsValue(false), false);
    assertEquals(sites.get(1).getUpTimes().containsValue(true), true);
    assertEquals(sites.get(1).getUpTimes().containsValue(false), false);
    assertEquals(sites.get(2).getUpTimes().containsValue(true), false);
    assertEquals(sites.get(2).getUpTimes().containsValue(false), true);
  }

}