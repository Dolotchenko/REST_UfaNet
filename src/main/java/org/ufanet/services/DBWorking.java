package org.ufanet.services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBWorking {

    List<Note> oneNoteArrayList = new ArrayList<>();

    protected Connection connects() throws ClassNotFoundException, SQLException {
        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        Connection connectToDB = DriverManager
                .getConnection("jdbc:hsqldb:file:C:/Users/Maxim/IdeaProjects/simple_webapp/DataBase/myDB", "test", "test");
        System.out.println("we're connected");
        return connectToDB;
    }


    protected List<Note> doGet() {
        Connection conn = null;

        try {
            conn = connects();
            //doWork(conn);
            viewAllFromDB(conn);
            //System.out.println(conn.isClosed());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return oneNoteArrayList;

    }

    protected boolean doPostOrDelete(int flag, String text) {// 2добавить новую, 3удалить
        Connection conn = null;
        boolean resultMethod;
        try {
            conn = connects();
            if (flag == 2) {
                resultMethod = insertNoteToDB(text, conn);
            } else if (flag == 3) {
                resultMethod = deleteNoteToDB(text, conn);
            } else resultMethod = false;

            return resultMethod;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    protected List<Note> viewAllFromDB(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM NOTES");
        while (rs.next()) {
            addNote(rs.getInt("ID"), rs.getString("TEXTNOTE"));
            System.out.printf("%3s|%10s|\n", rs.getString("ID"), rs.getString("TEXTNOTE"));
        }
        return oneNoteArrayList;
    }

    protected void addNote(int id, String note) {
        oneNoteArrayList.add(new Note(id, note));
    }

    protected boolean insertNoteToDB(String text, Connection connection) {

        try {
            PreparedStatement statement = connection
                    .prepareStatement("insert into NOTES(TEXTNOTE) values(?)");
            statement.setString(1, text);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            // e.printStackTrace();
            System.out.println("NONONONONOONONONO");
            return false;
        }

    }

    protected boolean deleteNoteToDB(String id, Connection connection) {

        try {
            PreparedStatement statement = connection
                    .prepareStatement("delete from NOTES where ID=?");
            statement.setInt(1, Integer.parseInt(id));
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("!!!!!!!!!!NONONONONOONONONO");
            return false;
        }
    }


}
