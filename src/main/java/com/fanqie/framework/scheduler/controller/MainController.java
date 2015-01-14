package com.fanqie.framework.scheduler.controller;

import com.fanqie.framework.scheduler.model.Farmer;
import com.fanqie.framework.scheduler.repo.FarmerRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.String.format;

@Controller
@RequestMapping("/farmer")
public class MainController {
    private static final Log log = LogFactory.getLog(MainController.class);
    private final ObjectMapper mapper = new ObjectMapper();

    @Value("${name}")
    private String name;

    @Autowired
    private FarmerRepository farmerRepository;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void create() {
        log.debug("create farmer start");
        farmerRepository.save(new Farmer());
        farmerRepository.save(new Farmer());

        log.debug("create farmer finish");
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public
    @ResponseBody
    String search(@RequestParam("keyword") String keyword) throws Exception {
        log.debug("search farmer start");
        log.debug(format("keyword:%s", keyword));

        List<Farmer> farmers = farmerRepository.findAll();
        String resultArrayJson = mapper.writeValueAsString(farmers);
        log.debug(format("resultArrayJson: %s", resultArrayJson));
        log.debug("search farmer finish");
        return resultArrayJson;
    }
}
