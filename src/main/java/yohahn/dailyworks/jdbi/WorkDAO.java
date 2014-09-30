package yohahn.dailyworks.jdbi;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import yohahn.dailyworks.core.Work;

import java.util.List;

/**
 * @author yohahn.kim
 * @since 9/12/14 10:24 PM
 */
@RegisterMapper(WorkMapper.class)
public interface WorkDAO {

    @SqlQuery("select id, title, completed from work")
    List<Work> findAll();

    @SqlQuery("select id, title, completed from work where id = :id")
    Work findById(@Bind("id") int id);

    @GetGeneratedKeys
    @SqlUpdate("insert into work (id, title, completed) values (NULL, :title, :completed)")
    int create(@Bind("title") String title, @Bind("completed") boolean completed);

    @SqlUpdate("update work set title = :title, compelted = :completed where id = :id")
    void update(@Bind("id") int id, @Bind("title") String title, @Bind("completed") boolean completed);

    @SqlUpdate("delete from work where id = :id")
    void delete(@Bind("id") int id);
}
