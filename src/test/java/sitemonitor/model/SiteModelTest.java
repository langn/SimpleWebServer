package sitemonitor.model;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;


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
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadURLNotAllowed() {
    this.model.addSite(new Website("I am not a URL"));
  }

  @Test
  public void testAddSite() {
    Website github = new Website("https://github.io");
    assertEquals(this.model.getSites().size(), 2);
    this.model.addSite(github);
    assertEquals(this.model.getSites().size(), 3);
  }

  @Test
  public void testGetAndCheckSitesTrue() {
    this.model.checkSites();
    try {
      Thread.sleep(2000);
    }
    catch (InterruptedException e) {}
    this.model.checkSites();
    HashSet<Website> sites = this.model.getSites();
    Website[] sitesAsArray = sites.toArray(new Website[sites.size()]);
    assertEquals(sitesAsArray[0].getUpTimes().containsValue(true), true);
    assertEquals(sitesAsArray[0].getUpTimes().containsValue(false), false);
    assertEquals(sitesAsArray[1].getUpTimes().containsValue(true), true);
    assertEquals(sitesAsArray[1].getUpTimes().containsValue(false), false);
  }

  @Test
  public void testGetAndCheckSitesFalse() {
    SiteModel falseSites = new SiteModel();
    falseSites.addSite(this.notReal);
    falseSites.checkSites();
    HashSet<Website> sites = falseSites.getSites();
    for (Website s : sites) {
      assertEquals(s.getUpTimes().containsValue(false), true);
      assertEquals(s.getUpTimes().containsValue(true), false);
    }

  }

}