/*
 * Copyright 2023 Uuzics
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

/**
 * Controller for URL redirection
 */
@Controller
public class RedirectController {
    @Resource
    private RedirectionService redirectionService;

    /**
     * Redirect to destination URL given a resource ID
     *
     * @param resourceId resource ID
     * @param model      Spring Boot model
     * @return name of Spring Boot view
     */
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
        // TODO serious validations on URL
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
                // TODO maybe another error page?
                return "http301Redirect:" + redirectUrl;
        }
    }

}
