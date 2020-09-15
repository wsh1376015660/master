package dao.impl;

import dao.dao.IotherDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.JdbcUtil;

public class OtherDaoImpl implements IotherDao {
    public OtherDaoImpl() {
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

    public double statistic(int sId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        double var8;
        try {
            conn = JdbcUtil.getConn();
            String sql = "select SUM(credit) FROM score WHERE stuID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, sId);
            rs = ps.executeQuery();
            if (!rs.next()) {
                return 0.0D;
            }

            double res = rs.getDouble("SUM(credit)");
            var8 = res;
        } catch (Exception var13) {
            var13.printStackTrace();
            return 0.0D;
        } finally {
            JdbcUtil.close(conn, ps, rs);
        }

        return var8;
    }

    public int analysis(int stuClass, int cId, int minScore, int maxScore) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int var10;
        try {
            conn = JdbcUtil.getConn();
            String sql = "SELECT COUNT(*) num FROM score\nWHERE stuID IN\n(SELECT stuID FROM student\nWHERE stuClass = ?)\nAND score >= ?\nAND score < ?  \nAnd cID=?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, stuClass);
            ps.setInt(2, minScore);
            ps.setInt(3, maxScore);
            ps.setInt(4, cId);
            rs = ps.executeQuery();
            if (!rs.next()) {
                return 0;
            }

            int res = rs.getInt("num");
            var10 = res;
        } catch (Exception var14) {
            var14.printStackTrace();
            return 0;
        } finally {
            JdbcUtil.close(conn, ps, rs);
        }

        return var10;
    }
}
