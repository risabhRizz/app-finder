package com.rizz.appfinder;

import com.rizz.appfinder.redis.DataFetcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/search")
    public App applications(@RequestParam(value = "name") String name) {
        return new DataFetcher().findApp(name);
    }
}
