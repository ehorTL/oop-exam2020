package dao;

import db.DBConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Level is simplified so as to demonstrate the execution of queries.
 * */
public class CustomQueries {

    private static Connection connection = new DBConnectionProvider().getConnection();

    private static final String q1 = "SELECT * from satellite inner join planet on satellite.planet_id=planet.id " +
            "where planet.is_inhabited=TRUE AND planet.galaxy_id=?";

    private static final String q2 = "SELECT *, COUNT(satellite.id) AS satelites_count " +
            "FROM planet INNER JOIN satellite ON planet.id = satellite.planet_id " +
            "GROUP BY planet.id, satellite.id " +
            "ORDER BY planet.radius, satelites_count DESC;";

    /**
     * Find all inhabited planets and their satellites by galaxy id
     * */
    public static void query1(int galaxyId) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(q1);
        ps.setInt(1, galaxyId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            System.out.println(
                    "satellite_id: " + rs.getInt(1) + " satellite_name: " + rs.getString(2) +
                    " radius: " + rs.getDouble(3) +  " dist: " + rs.getDouble(4) +
                    " planet_id: " + rs.getInt(5) + " planet_radius: " + rs.getDouble(7) +
                    " kernel_temp: " + rs.getDouble(8)
            );
        }
    }

    public static void query2(int galaxyId) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(q2);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            int colsNumber = rs.getMetaData().getColumnCount();
            for (int i = 1; i < colsNumber; i++) {
                int type = rs.getMetaData().getColumnType(i);
                if (type == 4){
                    System.out.print(rs.getInt(i) + " ");
                } else if (type == 8){
                    System.out.print(rs.getDouble(i) + " ");
                } else if (type == 12){
                    System.out.print(rs.getString(i) + " ");
                } else if (type == 16){
                    System.out.print(rs.getBoolean(i) + " ");
                }
                System.out.println("\n\n");
            }
        }
    }

}
