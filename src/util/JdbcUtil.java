package util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.sql.DataSource;

public class JdbcUtil {
    public static DataSource ds = null;

    public JdbcUtil() {
    }

    public static Connection getConn() {
        try {
            return ds.getConnection();
        } catch (Exception var1) {
            var1.printStackTrace();
            return null;
        }
    }

    public static void close(Connection conn, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException var6) {
                var6.printStackTrace();
            }
        }

        if (st != null) {
            try {
                st.close();
            } catch (SQLException var5) {
                var5.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException var4) {
                var4.printStackTrace();
            }
        }

    }

    static {
        try {
            Properties p = new Properties();
            FileInputStream in = new FileInputStream("resource/db.properties");
            p.load(in);
            ds = DruidDataSourceFactory.createDataSource(p);
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }
}
