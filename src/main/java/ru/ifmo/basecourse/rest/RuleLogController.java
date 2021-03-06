package ru.ifmo.basecourse.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.ifmo.basecourse.dto.cassandra.RuleLog;
import ru.ifmo.basecourse.dto.json.PagingCassandraDto;
import ru.ifmo.basecourse.repository.RuleLogRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/log")
public class RuleLogController {

    @Autowired
    private RuleLogRepository ruleLogRepository;

    private static final int size = 10;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public PagingCassandraDto getAll(@RequestParam(name = "page", /*defaultValue = "",*/ required = false) String page,
                                     @RequestParam(name = "ruleId", /*defaultValue = "",*/ required = false) Long ruleId) {
        List<RuleLog> logs = new ArrayList<>();
        String nextPage = ruleLogRepository.findAllByRuleId(logs, ruleId, page);
        return new PagingCassandraDto(nextPage, logs);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RuleLog addLog(@RequestBody RuleLog ruleLog) {
        return ruleLogRepository.insert(ruleLog);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteLog(@RequestBody RuleLog ruleLog) {
        ruleLogRepository.delete(ruleLog);
    }

}
