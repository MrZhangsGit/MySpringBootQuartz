package my.springboot.quartz.controller;

import my.springboot.quartz.service.IQuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangs
 * @Description
 * @createDate 2019/1/26
 */
@RestController
public class QuartzTaskController {
    @Autowired
    private IQuartzService iQuartzService;

    @RequestMapping(value = "/task/start", method = RequestMethod.GET)
    public String startTask(@RequestParam(value = "taskNo") String taskNo) {
        boolean result = iQuartzService.startTask(taskNo);
        if (result) {
            return "SUCCESS!";
        } else {
            return "FAILED!";
        }
    }

    @RequestMapping(value = "/task/stop", method = RequestMethod.GET)
    public String stopTask(@RequestParam(value = "taskNo") String taskNo) {
        boolean result = iQuartzService.stopTask(taskNo);
        if (result) {
            return "SUCCESS!";
        } else {
            return "FAILED!";
        }
    }
}
