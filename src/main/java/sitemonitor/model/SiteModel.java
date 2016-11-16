package sitemonitor.model;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * Holds the data related to the sites being monitored and their uptimes
 */
@Repository
public class SiteModel {

  //Instance variables
  ArrayList<Website> sites;

  /**
   * Constructor for the LookupService
   */
  public SiteModel() {
    this.sites = new ArrayList<>();
  }


  /**
   * Gets the uptimes of the sites in this model
   * @return an {@code ArrayList} with all of the Websites in this model
   */
  public ArrayList<Website> getSites() {
    return this.sites;
  }

  /**
   * Adds the given website into this model (if the site has not already been added)
   * @param website the website to be added into the model
   */
  public void addSite(Website website) {

    //Checks for duplicate URLs
    if (this.sites.stream().anyMatch(p -> p.getUrl().equals(website.getUrl()))) {
      return;
    }
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
