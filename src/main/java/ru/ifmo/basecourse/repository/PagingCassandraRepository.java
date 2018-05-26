package ru.ifmo.basecourse.repository;

import org.springframework.data.domain.Page;
import ru.ifmo.basecourse.dto.cassandra.RuleLog;

import java.util.List;

public interface PagingCassandraRepository {

    String findAll(List<RuleLog> log, String page);

    String findAllByRuleId(List<RuleLog> list, Long ruleId, String page);
}
