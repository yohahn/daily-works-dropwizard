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

    @SqlQuery("select id, title from work")
    List<Work> findAll();

    @SqlQuery("select id, title from work where id = :id")
    Work findById(@Bind("id") int id);

    @GetGeneratedKeys
    @SqlUpdate("insert into work (id, title) values (NULL, :title)")
    int create(@Bind("title") String title);

    @SqlUpdate("update work set title = :title where id = :id")
    void update(@Bind("id") int id, @Bind("title") String title);

    @SqlUpdate("delete from work where id = :id")
    void delete(@Bind("id") int id);
}
