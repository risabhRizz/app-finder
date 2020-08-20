package com.rizz.appfinder;

import com.rizz.appfinder.redis.DataFetcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppResultsController {
    private static final DataFetcher dataFetcher = new DataFetcher();

    @GetMapping("/search")
    public AppResults getSearchResults(@RequestParam(value = "name") String name) {
        AppResults results = new AppResults();
        dataFetcher.populateResults(results, name);

        return results;
    }
}
