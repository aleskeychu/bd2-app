package ru.ifmo.basecourse.dto.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import ru.ifmo.basecourse.dto.cassandra.RuleLog;

import java.util.List;

public class PagingCassandraDto {

    private String page;
    private List<RuleLog> ruleLogs;

    @JsonCreator
    public PagingCassandraDto(String page, List<RuleLog> ruleLogs) {
        this.page = page;
        this.ruleLogs = ruleLogs;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<RuleLog> getRuleLogs() {
        return ruleLogs;
    }

    public void setRuleLog(List<RuleLog> ruleLogs) {
        this.ruleLogs = ruleLogs;
    }
}
