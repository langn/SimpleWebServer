package sitemonitor.model;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.TreeMap;

/**
 * Represents a website and its up-times
 */
public class Website {

  //Instance variables
  private String url;

  private TreeMap<Date, Boolean> upTimes; //Map date -> is the site up
  /**
   * Creates an object of type website
   * @param url the url of the website
   * @throws IllegalArgumentException if the url is malformed
   */
  public Website(String url) throws IllegalArgumentException {
    if (!this.wellFormedURL(url)) {
      throw new IllegalArgumentException("The URL: " + url + " is malformed.");
    }
    this.url = url;
    this.upTimes = new TreeMap<>();
  }

  /**
   * Returns true if this URL is well-formed
   * @param url the url to be checked for well-formedness
   * @return true if url is well formed
   */
  private boolean wellFormedURL(String url) {
    try {
      URL test = new URL(url);
    }
    catch (MalformedURLException e) {
      return false;
    }
    return true;
  }

  /**
   * Gets the map representing when a website is up
   * @return the TreeMap with the data of when the site is up
   */
  public TreeMap<Date, Boolean> getUpTimes() {
    return upTimes;
  }

  /**
   * Gets the url of this website
   * @return the url of this website
   */
  public String getUrl() {
    return url;
  }

  /**
   * Checks to see if this website is up and adds the result to the tree-map using the current
   * date
   */
  public void checkUpTime() {

    //The current system time
    Date date = new Date();

    try {
      URL siteURL = new URL(url);
      HttpURLConnection testConnection = (HttpURLConnection) siteURL.openConnection();
      testConnection.setRequestMethod("GET");
      testConnection.connect();

      if (testConnection.getResponseCode() == 200) {
        this.upTimes.put(date, true);
      }
      else {
        this.upTimes.put(date, false);
      }
    }
    catch (Exception e) {
      this.upTimes.put(date, false);
    }
  }

}
