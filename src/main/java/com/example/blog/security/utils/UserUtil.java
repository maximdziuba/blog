package com.example.blog.security.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

public class UserUtil {

    public static String getUsernameFromAuthentication(Authentication authentication) {
        String username = "";
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof DefaultOidcUser) {
                DefaultOidcUser oauthUser = (DefaultOidcUser) authentication.getPrincipal();
                username = oauthUser.getEmail();
            } else {
                UserDetails user = (UserDetails) authentication.getPrincipal();
                username = user.getUsername();
            }
        }
        return username;
    }

    public static boolean isUserLoggedIn(Authentication authentication) {
        return authentication != null;
    }
}
