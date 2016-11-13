package sitemonitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sitemonitor.model.Website;

/**
 * Handles HTTP requests for websites to be tracked
 */

@Controller
public class SiteMonitorController {

  @GetMapping("/addSite")
  public String getSiteForm() {

  }

  //TODO try catch for illegal url
  /**
   * Adds the site specified by the HTTP request to the model.
   *
   * @param url the url of the site to be checked for uptime
   * @param model the model holding info about site uptimes
   * @return a string representing the view to be shown to the user.
   *          - In the case where the website is well-formed, simply repeat the website
   *            and notify that the website is being checked
   *          - In the case of an ill-formed request, notify the user that the website was
   *            not added
   */

  @PostMapping("/addsite")
  public String submitSite(@RequestParam(value = "url") String url, Model model) {
      model.addAttribute("url", new Website(url));
      return "siteAdd";
  }

}
