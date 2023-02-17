package org.uuzics.simpleredirectservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RedirectController {
    @RequestMapping(value = "/{resource}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String redirect(@PathVariable("resource") String resource, Model model) {
        // Retrieve redirect url and redirect type
        String redirectUrl = "https://example.org";
        String redirectType = "http301";

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
