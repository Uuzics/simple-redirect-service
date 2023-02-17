package org.uuzics.simpleredirectservice.view;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Locale;

@Component
public class Http301RedirectViewResolver implements ViewResolver {
    public static final String HTTP_301_REDIRECT_PREFIX = "http301Redirect:";

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