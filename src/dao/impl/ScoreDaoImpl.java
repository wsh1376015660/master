package dao.impl;

import dao.dao.IScoreDao;
import domain.Score;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.JdbcUtil;

public class ScoreDaoImpl implements IScoreDao {
    public ScoreDaoImpl() {
    }

    public int executeUpdate(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = JdbcUtil.getConn();
            ps = conn.prepareStatement(sql);

            int i;
            for(i = 0; i < params.length; ++i) {
                ps.setObject(i + 1, params[i]);
            }

            i = ps.executeUpdate();
            return i;
        } catch (Exception var9) {
            var9.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, (ResultSet)null);
        }

        return 0;
    }

    public void save(int sId, int cId, Score score) {
        String sql = "insert into score(stuID, cID, score, credit) values (?,?,?,?)";
        this.executeUpdate(sql, score.getStuID(), score.getcID(), score.getScore(), score.getCredit());
    }

    public void delete(int sId, int cId) {
        String sql = "delete from score where stuID = ? and cID = ?";
        this.executeUpdate(sql, sId, cId);
    }

    public void update(int sId, Score score) {
        String sql = "update score set score=?, credit=? where stuId =? and cID=?";
        this.executeUpdate(sql, score.getScore(), score.getCredit(), sId, score.getcID());
    }

    public Score get(int sId, int cId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Score var8;
        try {
            conn = JdbcUtil.getConn();
            String sql = "select * from score where stuID = ? and cID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, sId);
            ps.setInt(2, cId);
            rs = ps.executeQuery();
            if (!rs.next()) {
                return null;
            }

            Score score = new Score(rs.getString("stuID"), rs.getString("cID"), rs.getString("score"), rs.getString("credit"));
            var8 = score;
        } catch (Exception var12) {
            var12.printStackTrace();
            return null;
        } finally {
            JdbcUtil.close(conn, ps, rs);
        }

        return var8;
    }

    public List<Score> getAll() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtil.getConn();
            st = conn.createStatement();
            String sql = "select * from score ";
//            System.out.println(sql);
            rs = st.executeQuery(sql);
            ArrayList list = new ArrayList();

            while(rs.next()) {
                Score score = new Score(rs.getString("stuID"), rs.getString("cID"), rs.getString("score"), rs.getString("credit"));
                list.add(score);
            }

            ArrayList var12 = list;
            return var12;
        } catch (Exception var10) {
            var10.printStackTrace();
        } finally {
            JdbcUtil.close(conn, st, rs);
        }

        return null;
    }
}
