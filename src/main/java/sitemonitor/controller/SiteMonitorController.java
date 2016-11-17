package sitemonitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;

import sitemonitor.model.SiteModel;
import sitemonitor.model.Website;

/**
 * Handles HTTP requests for websites to be tracked
 */

@Controller
public class SiteMonitorController {

  @Autowired
  SiteModel model;

  /**
   * Returns the Website list of the model in JSON format
   * @return the list of {@link Website} for this model in JSON format
   */
  @RequestMapping(value = "/getsites")
  public @ResponseBody
  HashSet<Website> getSitesData() {
    return this.model.getSites();
  }


  /**
   * Adds the site specified by the HTTP request to the model.
   *
   * @param url the url of the site to be checked for uptime
   * @param viewHelp the model used help with the view
   * @return a string representing the view to be shown to the user.
   *          - In the case where the website is well-formed, simply repeat the website
   *            and notify that the website is being checked
   *          - In the case of an ill-formed request, notify the user that the website was
   *            not added
   */
  @RequestMapping("/addsite")
  public String submitSite(@RequestParam(value = "url") String url, Model viewHelp) {
      try {
        this.model.addSite(new Website(url));
      }
      catch (IllegalArgumentException e) {
        viewHelp.addAttribute("url", url);
        return "badUrl";
      }
      viewHelp.addAttribute("url", url);
      return "addSite";
  }

}
