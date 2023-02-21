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
package org.uuzics.simpleredirectservice.view;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Locale;

/**
 * Customized view resolver for HTTP301 redirection
 */
@Component
public class Http301RedirectViewResolver implements ViewResolver {
    public static final String HTTP_301_REDIRECT_PREFIX = "http301Redirect:";

    /**
     * Redirect by HTTP301 response
     *
     * @param viewName view name
     * @param locale locale
     * @return redirection view
     * @throws Exception exception
     */
    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        if (!viewName.startsWith(HTTP_301_REDIRECT_PREFIX)) {
            return null;
        }
        String redirectUrl = viewName.substring(HTTP_301_REDIRECT_PREFIX.length());
        RedirectView view = new RedirectView(redirectUrl);
        view.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return view;
    }
}