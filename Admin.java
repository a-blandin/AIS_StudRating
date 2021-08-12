package Students;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import Students.Selector;

public class Admin {
    private static String user = "root";
    private static String pass = "root123";
    private static int lastUsedID;

    public String LoginPasswordCheck(String entered_login, String entered_password){
        if (user.equals(entered_login)&&pass.equals(entered_password))
        {
            return "ok";
        }
        else
        {
            return "Неверный логин или пароль";
        }
    }

    public static int Adder(int student_id,String Name,String SerName,String StudentGroup,String Faculty,int baseMark,int progMark,int webMark,double rating) throws SQLException, ClassNotFoundException {
        String userName = user;
        String password = pass;
        String connectionUrl = "jdbc:mysql://localhost:3306/RatingSystem";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
        Statement statement = connection.createStatement();
        AddScript addScript = new AddScript();
        Rating r = new Rating();
        if(Selector.IdCheck(student_id)==true)
        {
            return 0;
        }
        statement.executeUpdate(addScript.addToStudents(student_id, Name, SerName, StudentGroup, Faculty, baseMark, progMark, webMark, r.Count(baseMark,progMark,webMark,rating)));
        return 1;
    }

    public static int Deleter(int student_id) throws SQLException, ClassNotFoundException {
        String userName = user;
        String password = pass;
        String connectionUrl = "jdbc:mysql://localhost:3306/RatingSystem";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
        Statement statement = connection.createStatement();
        DeleteScript deleteScript = new DeleteScript();
        if (Selector.IdCheck(student_id)==true) {
            statement.executeUpdate(deleteScript.deleteFromStudents(student_id));
            return 1;
        }
        return 0;
    }

    public static int Updater(int student_id,String Name,String SerName,String StudentGroup,String Faculty,int baseMark,int progMark,int webMark,double rating) throws SQLException, ClassNotFoundException {
        String userName = user;
        String password = pass;
        String connectionUrl = "jdbc:mysql://localhost:3306/RatingSystem";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
        Statement statement = connection.createStatement();
        UpdateScript updateScript = new UpdateScript();
        Rating r = new Rating();
        statement.executeUpdate(updateScript.updateStudents(student_id, Name, SerName, StudentGroup, Faculty, baseMark, progMark, webMark, r.Count(baseMark,progMark,webMark,rating)));
        return 1;
    }

    public static int idCheck(int student_id) throws SQLException, ClassNotFoundException {
        Selector selector = new Selector();
        if(selector.IdCheck(student_id)==true)
        {
            Admin.LastUsedID(student_id);
            return 1;
        }
        else
        {
            return 0;
        }
    }

    public static void LastUsedID(int student_id)  {
        Admin.lastUsedID=student_id;
    }

    public static int getLastUsedID() {
        return Admin.lastUsedID;
    }

    private static class AddScript {
        private String addToStudents(int student_id, String Name, String SerName, String StudentGroup, String Faculty, int baseMark, int progMark, int webMark, double rating) {
            String s = "INSERT into Students VALUES (" + student_id + ", '" + Name + "', '" + SerName + "', '" + StudentGroup + "', '" + Faculty + "', " + baseMark + ", " + progMark + ", " + webMark + ", " + rating + ")";
            return s;
        }
    }

    private static class DeleteScript {
        private String deleteFromStudents(int student_id) {
            String s = "DELETE FROM Students WHERE student_id =" + student_id + ";";
            return s;
        }
    }

    private static class UpdateScript {
        private String updateStudents(int student_id, String Name, String SerName, String StudentGroup, String Faculty, int baseMark, int progMark, int webMark, double rating) {
            String s = "UPDATE Students SET Name = '"+Name+"', SerName = '"+SerName+"', StudentGroup = '"+StudentGroup+"', Faculty = '"+Faculty+"', baseMark = "+baseMark+", progMark = "+progMark+", webMark = "+webMark+", rating = "+rating+" WHERE student_id ="+student_id+";";
            return s;
        }
    }

    private static class Rating{
        private double Count(double baseMark, double progMark, double webMark, double rating) {
            rating = (baseMark + progMark + webMark) / 3;
            return rating;
        }
    }
}
