package ru.ifmo.basecourse.repository;

import com.datastax.driver.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.core.convert.CassandraConverter;
import org.springframework.data.domain.Page;
import ru.ifmo.basecourse.dto.cassandra.RuleLog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;

public class PagingCassandraRepositoryImpl implements PagingCassandraRepository {

    @Autowired
    Cluster cluster;

    @Autowired
    CassandraConverter converter;

    @Override
    public String findAll(List<RuleLog> list, String page) {
        if (list == null) {
            return null;
        }
        list.clear();
        Session session = cluster.connect();
        Statement st = new SimpleStatement("select * from rulelog;");
        st.setFetchSize(10);
        if (page != null && !page.isEmpty()) {
            st.setPagingState(PagingState.fromString(page));
        }

        ResultSet resultSet = session.execute(st);
        PagingState pagingState = resultSet.getExecutionInfo().getPagingState();

        for (Row row : resultSet) {
            RuleLog log = converter.read(RuleLog.class, row);
            list.add(log);
        }

        return pagingState != null ? pagingState.toString() : null;
    }

    @Override
    public String findAllByRuleId(List<RuleLog> list, Long ruleId, String page) {
        if (list == null) {
            return null;
        }
        list.clear();
        Session session = cluster.connect();
        session.execute(new SimpleStatement("use rules;"));
        String query = "select * from rulelog";
        if (ruleId != null) {
            query += " where ruleid=" + ruleId;
        }
        Statement st = new SimpleStatement(query);
        st.setFetchSize(10);
        if (page != null && !page.isEmpty()) {
            st.setPagingState(PagingState.fromString(page));
        }
        ResultSet resultSet = session.execute(st);
        PagingState pagingState = resultSet.getExecutionInfo().getPagingState();

        Iterator<Row> it = resultSet.iterator();
        for (int i = 0; i < 10; i++)  {
            Row row = it.next();
            RuleLog log = converter.read(RuleLog.class, row);
            list.add(log);
        }

        return pagingState != null ? pagingState.toString() : null;
    }

}
