package ru.ifmo.basecourse.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import ru.ifmo.basecourse.dto.cassandra.RuleLog;
import ru.ifmo.basecourse.dto.cassandra.RuleLogKey;

import java.util.List;

public interface RuleLogRepository extends CassandraRepository<RuleLog, RuleLogKey>, PagingCassandraRepository {

}
