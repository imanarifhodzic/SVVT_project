import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CookieSecurityTests extends SetupAndTearDown {

    @Test
    void cookiesAreHTTPOnly() { // 1 cookie is httponly, rest are probably non-sensitive

        driver.get(baseUrl);
        driver.navigate().to(baseUrl + "knjizara/beletristika");
        // get all cookies after the action
        Set<Cookie> cookies = driver.manage().getCookies();

        boolean httpOnlyFlag = false;

        for (Cookie cookie : cookies) {
            // Check if the cookie is HttpOnly
            if (cookie.isHttpOnly()) {
                httpOnlyFlag = true;
            }

            // printing the cookies
            System.out.println("Cookie: " + cookie.getName() + " | HttpOnly: " + cookie.isHttpOnly());
        }

        // verifying the cookies are HttpOnly
        assertTrue(httpOnlyFlag, "The cookies should be flagged as HttpOnly.");

    }

    @Test
    void cookiesAreSecure(){ // only 1 cookie is secure, rest are probably not sensitive
                              // similar with httpOnly

        driver.get(baseUrl);
        driver.navigate().to(baseUrl + "knjizara/beletristika");

        // get all cookies after the action
        Set<Cookie> cookies = driver.manage().getCookies();

        boolean secureFlag = false;

        for (Cookie cookie : cookies) {
            // Check if the cookie is Secure
            if (cookie.isSecure()) {
                secureFlag = true;
            }

            // printing out cookies
            System.out.println("Cookie: " + cookie.getName() + " | Secure: " + cookie.isSecure());
        }

        // verifying the cookies are Secure
        assertTrue(secureFlag, "The cookies should be flagged as Secure.");
    }
}

