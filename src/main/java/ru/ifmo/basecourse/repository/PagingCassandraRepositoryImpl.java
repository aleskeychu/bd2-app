package ru.ifmo.basecourse.repository;

import com.datastax.driver.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.core.convert.CassandraConverter;
import org.springframework.data.domain.Page;
import ru.ifmo.basecourse.dto.cassandra.RuleLog;

import java.util.ArrayList;
import java.util.List;

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
        System.out.println("1");
        list.clear();
        Session session = cluster.connect();
        session.execute(new SimpleStatement("use rules;"));
        System.out.println("2");
        String query = "select * from rulelog";
        if (ruleId != null) {
            query += " where ruleid=" + ruleId;
        }
        Statement st = new SimpleStatement(query);
        st.setFetchSize(10);
        System.out.println("3");
        if (page != null && !page.isEmpty()) {
            st.setPagingState(PagingState.fromString(page));
        }
        System.out.println("4");
        ResultSet resultSet = session.execute(st);
        PagingState pagingState = resultSet.getExecutionInfo().getPagingState();
        System.out.println("5");

        for (Row row : resultSet) {
            System.out.println("-");
            RuleLog log = converter.read(RuleLog.class, row);
            list.add(log);
        }
        System.out.println("6");

        return pagingState != null ? pagingState.toString() : null;
    }

}
