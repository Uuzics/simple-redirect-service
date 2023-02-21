package org.uuzics.simpleredirectservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.uuzics.simpleredirectservice.entity.Redirection;
import org.uuzics.simpleredirectservice.service.RedirectionService;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class RedirectController {
    @Resource
    private RedirectionService redirectionService;

    @RequestMapping(value = "/{resourceId}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String redirect(@PathVariable("resourceId") String resourceId, Model model) {
        // Retrieve redirect url and redirect type
        List<Redirection> tempRedirectionList = this.redirectionService.queryByResourceId(resourceId);
        if (null == tempRedirectionList || 0 == tempRedirectionList.size()) {
            return "error";
        }
        String redirectUrl = tempRedirectionList.get(0).getRedirectUrl();
        String redirectType = tempRedirectionList.get(0).getRedirectType();

        // Validation
        if (null == redirectUrl || null == redirectType) {
            return "error";
        }

        // Execute redirection
        switch (redirectType) {
            case "http301":
                return "http301Redirect:" + redirectUrl;
            case "http302":
                return "redirect:" + redirectUrl;
            case "plainhttp":
                model.addAttribute("redirectUrl", redirectUrl);
                return "redirect/PlainHttpRedirect.html";
            case "javascript":
                model.addAttribute("redirectUrl", redirectUrl);
                return "redirect/JavaScriptRedirect.html";
            default:
                return "http301Redirect:" + redirectUrl;
        }
    }

}
