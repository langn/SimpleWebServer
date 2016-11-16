package sitemonitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import sitemonitor.model.SiteModel;

/**
 * Implements the {@link ILookupService} interface in order to check if the sites in this model
 * are up on a scheduled timer
 */
@Service ("lookupService")
public class LookupService implements ILookupService {

  @Autowired
  SiteModel model;

  /**
   * Checks to see if the websites in this model are up every 5 seconds
   */
  @Scheduled(fixedRate = 5000)
  public void checkUpTime() {
    model.checkSites();
  }
}
