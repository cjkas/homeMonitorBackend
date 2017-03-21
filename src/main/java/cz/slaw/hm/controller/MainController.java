package cz.slaw.hm.controller;

import cz.slaw.hm.UserSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Sławek on 2017-03-21.
 */
@RestController
public class MainController {

    // Inject the UserSearch object
    @Autowired
    private UserSearch userSearch;

    /**
     * Show search results for the given query.
     *
     * @param q The search query.
     */
    @RequestMapping("/search/{name}")
    public List search(@PathVariable String name) {
        List searchResults = null;
        try {
            searchResults = userSearch.search(name);
        }
        catch (Exception ex) {
             throw ex;
        }
        return searchResults;
    }

} // class MainController
