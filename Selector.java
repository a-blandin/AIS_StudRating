package Students;

import java.sql.*;

public class Selector
{

    public static String SelectedOut( int selectedPlace, int selectedElement) throws SQLException, ClassNotFoundException
    {
        String userName = "root";
        String password = "root123";
        String connectionUrl = "jdbc:mysql://localhost:3306/RatingSystem";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
        Statement statement = connection.createStatement();
        Out out = new Out();
        int c = 1;
        ResultSet result = statement.executeQuery(out.OutSorted());
        while(result.next())
        {
            int student_id = result.getInt("student_id");
            String Name = result.getString("Name");
            String SerName = result.getString("SerName");
            String StudentGroup = result.getString("StudentGroup");
            String Faculty = result.getString("Faculty");
            int baseMark = result.getInt("baseMark");
            int progMark = result.getInt("progMark");
            int webMark = result.getInt("webMark");
            double rating = result.getDouble("rating");

            if (c==selectedPlace)
            {
                if (selectedElement==1)
                {
                    return String.valueOf(student_id);
                }

                if (selectedElement==2)
                {
                    return Name;
                }

                if (selectedElement==3)
                {
                    return SerName;
                }

                if (selectedElement==4)
                {
                    return StudentGroup;
                }

                if (selectedElement==5)
                {
                    return Faculty;
                }

                if (selectedElement==6)
                {
                    return String.valueOf(baseMark);
                }

                if (selectedElement==7)
                {
                    return String.valueOf(progMark);
                }

                if (selectedElement==8)
                {
                    return String.valueOf(webMark);
                }

                if (selectedElement==9)
                {
                    return String.valueOf(rating);
                }
            }
            c++;
        }
       return null;
    }

    public static String SelectedForUpdate(int selectedElement) throws SQLException, ClassNotFoundException
    {
        String userName = "root";
        String password = "root123";
        String connectionUrl = "jdbc:mysql://localhost:3306/RatingSystem";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
        Statement statement = connection.createStatement();
        OutForUpdate out = new OutForUpdate();
        ResultSet result = statement.executeQuery(out.OutSorted(Admin.getLastUsedID()));
        while(result.next())
        {
            String Name = result.getString("Name");
            String SerName = result.getString("SerName");
            String StudentGroup = result.getString("StudentGroup");
            String Faculty = result.getString("Faculty");
            int baseMark = result.getInt("baseMark");
            int progMark = result.getInt("progMark");
            int webMark = result.getInt("webMark");
            double rating = result.getDouble("rating");

            if (selectedElement==2)
            {
                return Name;
            }

            if (selectedElement==3)
            {
                return SerName;
            }

            if (selectedElement==4)
            {
                return StudentGroup;
            }

            if (selectedElement==5)
            {
                return Faculty;
            }

            if (selectedElement==6)
            {
                return String.valueOf(baseMark);
            }

            if (selectedElement==7)
            {
                return String.valueOf(progMark);
            }

            if (selectedElement==8)
            {
                return String.valueOf(webMark);
            }

            if (selectedElement==9)
            {
                return String.valueOf(rating);
            }

        }
        return null;
    }

    public static boolean IdCheck(int student_id) throws SQLException, ClassNotFoundException {
        String userName = "root";
        String password = "root123";
        String connectionUrl = "jdbc:mysql://localhost:3306/RatingSystem";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
        Statement statement = connection.createStatement();
        IdExists exists = new IdExists();
        ResultSet result = statement.executeQuery(exists.Exists(student_id));
        while(result.next())
        {
            return result.getBoolean(1);

        }
        return false;
    }
        public static int Quantity() throws SQLException, ClassNotFoundException
    {
        String userName = "root";
        String password = "root123";
        String connectionUrl = "jdbc:mysql://localhost:3306/RatingSystem";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
        Statement statement = connection.createStatement();
        Count count = new Count();

        ResultSet result = statement.executeQuery(count.Counter());
        while(result.next())
        {
            return result.getInt(1);

        }
        return 0;
    }


    public static class Out
    {
        public static String OutSorted()
        {
            String s = "SELECT student_id, Name, SerName, StudentGroup, Faculty, baseMark, progMark, webMark, rating FROM students ORDER BY rating DESC ;";
            return s;
        }
    }

    public static class OutForUpdate
    {
        public static String OutSorted(int student_id)
        {
            String s = "SELECT Name, SerName, StudentGroup, Faculty, baseMark, progMark, webMark, rating FROM students WHERE student_id="+student_id+";";
            return s;
        }
    }

    public static class Count
    {
        public static String Counter()
        {
            String s = "SELECT COUNT(*) FROM students;";
            return s;
        }
    }

    public static class IdExists
    {
        public static String Exists(int student_id)
        {
            String s = "SELECT EXISTS(SELECT student_id FROM students WHERE student_id = "+student_id+");";
            return s;
        }
    }
}
