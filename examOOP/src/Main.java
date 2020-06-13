import db.DBConnectionProvider;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        DBConnectionProvider connectionProvider = new DBConnectionProvider();
        Connection connection = connectionProvider.getConnection();
    }
}
