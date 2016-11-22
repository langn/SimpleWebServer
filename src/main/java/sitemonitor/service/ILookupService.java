package sitemonitor.service;

import org.springframework.stereotype.Service;

/**
 * Interface used for a service to check the uptimes of this model's sites
 */
public interface ILookupService {

  /**
   * Checks the uptimes for the sites and updates the data accordingly
   */
  void checkUpTime();

}
