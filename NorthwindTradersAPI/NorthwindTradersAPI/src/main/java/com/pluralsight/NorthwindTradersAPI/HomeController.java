package com.pluralsight.NorthwindTradersAPI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    // This method handles GET requests to "/"
    @GetMapping("/")
    public String home(@RequestParam(required = false) String country) {
        if (country != null && !country.isEmpty()) {
            return "Hello " + country;
        } else {
            return "Hello World";
        }
    }
}
