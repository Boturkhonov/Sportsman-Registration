package database;

import model.Sport;
import model.SportTitle;
import model.Sportsman;
import org.h2.tools.RunScript;

import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;

public class DataBase {
    private String DB_URL = "jdbc:h2:/" + System.getProperty("user.dir");
    private String DB_DRIVER = "org.h2.Driver";

    private static Connection connection;

    public DataBase() {
        try {
            Class.forName(this.DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL + File.separator + "resources" + File.separator +"DataBase");
            RunScript.execute(connection, new FileReader("src/database/script.sql"));
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("БД Подключена");
            System.err.println(ex.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Не удалось подключиться к базе данных");
        }
    }
    //---------------------------------------------Sportsman------------------------------------------------------------
    public static ArrayList<Sportsman> getAllSportsman() {
        ArrayList<Sportsman> sportsman = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Sportsman")) {
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                sportsman.add(new Sportsman(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6)
                ));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return sportsman;
    }

    public static int addSportsman(String fullName, int sportId, String phoneNumber, int sportTitleId, String coachName) {
        int id = -1;
        String[] generatedColumns = {"Id"};
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO Sportsman(FullName,SportId,PhoneNumber, SportTitleId, CoachName) VALUES(?,?,?,?,?)", generatedColumns)) {
            statement.setString(1, fullName);
            statement.setInt(2, sportId);
            statement.setString(3, phoneNumber);
            statement.setInt(4, sportTitleId);
            statement.setString(5, coachName);
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public static void deleteSportsman(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Sportsman where id = ?")) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void updateSportsman(int id, String fullName, int sportId, String phoneNumber, int sportTitleId, String coachName) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE Sportsman " +
                        "SET FullName=?, SportId=?, PhoneNumber=?, SportTitleId=?, CoachName=? " +
                        "WHERE Id = ?"
        )) {
            statement.setString(1, fullName);
            statement.setInt(2, sportId);
            statement.setString(3, phoneNumber);
            statement.setInt(4, sportTitleId);
            statement.setString(5, coachName);
            statement.setInt(6, id);

            statement.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //---------------------------------------------Sport----------------------------------------------------------------

    public static ArrayList<Sport> getAllSport() {
        ArrayList<Sport> sports = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Sport")) {
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                sports.add(new Sport(rs.getInt(1),rs.getString(2)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return sports;
    }

    public static int addSport(String name) {
        int id = -1;
        String[] generatedColumns = {"Id"};

        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO Sport(Name) VALUES(?)" , generatedColumns)) {
            statement.setString(1, name);
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public static void deleteSport(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Sport where id = ?")) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void updateSport(int id, String name) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE Sport SET Name=? where id = ?")) {
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static String getSportName(int id) {
        String name = "";
        try (PreparedStatement statement = connection.prepareStatement("SELECT Name FROM Sport WHERE Id=?")) {
            statement.setInt(1, id);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            if (rs.next()) {
                name = rs.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return name;
    }

    //-------------------------------------------SportTitle-------------------------------------------------------------
    /**
     * Returns all elements from SportTitle table
     * @return List of SportTitle
     */
    public static ArrayList<SportTitle> getAllSportTitle() {
        ArrayList<SportTitle> sportTitles = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM SportTitle")) {
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                sportTitles.add(new SportTitle(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return sportTitles;
    }

    public static int addSportTitle(String name) {
        int id = -1;
        String[] generatedColumns = {"Id"};

        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO SportTitle(Name) VALUES(?)" , generatedColumns)) {
            statement.setString(1, name);
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public static void deleteSportTitle(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM SportTitle where id = ?")) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void updateSportTitle(int id, String name) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE SportTitle SET Name=? WHERE id = ?")) {
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static String getSportTitleName(int id) {
        String name = "";
        try (PreparedStatement statement = connection.prepareStatement("SELECT Name FROM SportTitle WHERE Id=?")) {
            statement.setInt(1, id);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            if (rs.next()) {
                name = rs.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return name;
    }
}


