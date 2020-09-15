package dao.dao;

import domain.Score;
import java.util.List;

public interface IScoreDao {
    void save(int var1, int var2, Score var3);

    void delete(int var1, int var2);

    void update(int var1, Score var2);

    Score get(int var1, int var2);

    List<Score> getAll();
}
