package sitemonitor.model;

import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.TreeSet;


/**
 * Holds the data related to the sites being monitored and their uptimes
 */
@Repository
public class SiteModel {

  //Instance variables
  HashSet<Website> sites;

  /**
   * Constructor for the LookupService
   */
  public SiteModel() {
    this.sites = new HashSet<>();
  }


  /**
   * Gets the uptimes of the sites in this model
   * @return an {@code ArrayList} with all of the Websites in this model
   */
  public HashSet<Website> getSites() {
    return this.sites;
  }

  /**
   * Adds the given website into this model
   * @param website the website to be added into the model
   */
  public void addSite(Website website) {
    //If not a duplicate URL, add this website
    this.sites.add(website);
  }

  /**
   * Checks to see if the {@link Website}s in this model are up, and update their maps
   * accordingly
   */
  public void checkSites() {
    this.sites.forEach(Website::checkUpTime);
  }




}
