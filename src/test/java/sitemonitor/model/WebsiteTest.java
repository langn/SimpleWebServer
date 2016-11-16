package sitemonitor.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.TreeMap;

import static org.junit.Assert.*;

/**
 * Tests methods on the {@link Website} class
 */
public class WebsiteTest {

  //Test variables
  Website google;
  Website youtube;
  Website doesntExist;

  @Before
  public void init() {
    google = new Website("https://www.google.com");
    youtube = new Website("https://youtube.com");
    doesntExist = new Website("http://www.bobrosswasgoodatpainingtrees.com");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoHTTP() {
    Website noHTTP = new Website("wwww.google.com");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadHTTP() {
    Website noHTTP = new Website("htp://google.com");
  }

  @Test
  public void testCheckUpTime() {
    google.checkUpTime();
    youtube.checkUpTime();
    doesntExist.checkUpTime();
    //Sleep the thread so that the next check occurs at another date
    try {
      Thread.sleep(2000);
    }
    catch (InterruptedException e) {}
    google.checkUpTime();
    youtube.checkUpTime();
    doesntExist.checkUpTime();

    TreeMap<Date, Boolean> googleMap = google.getUpTimes();
    TreeMap<Date, Boolean> youtubeMap = youtube.getUpTimes();
    TreeMap<Date, Boolean> doesntExistMap = doesntExist.getUpTimes();

    //Test to see that the real website are full of true values
    //and no false values
    assertEquals(googleMap.containsValue(true), true);
    assertEquals(googleMap.containsValue(true), true);
    assertEquals(youtubeMap.containsValue(true), true);
    assertEquals(youtubeMap.containsValue(true), true);
    assertEquals(doesntExistMap.containsValue(false), true);
    assertEquals(doesntExistMap.containsValue(false), true);
    //Ensure that the maps have two entries (one for both times)
    assertEquals(googleMap.size(), 2);
    assertEquals(youtubeMap.size(), 2);
    assertEquals(doesntExistMap.size(), 2);
  }

}