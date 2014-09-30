package yohahn.dailyworks.jdbi;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import yohahn.dailyworks.core.Work;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author yohahn.kim
 * @since 9/12/14 10:36 PM
 */
public class WorkMapper implements ResultSetMapper<Work> {
    @Override
    public Work map(int i, ResultSet rs, StatementContext ctx) throws SQLException {
        return new Work(rs.getLong("id"), rs.getString("title"), rs.getBoolean("completed"));
    }
}
