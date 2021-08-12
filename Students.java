package Students;

import java.sql.*;

public class Students {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String userName = "root";
        String password = "root123";
        String connectionUrl = "jdbc:mysql://localhost:3306/RatingSystem";
        Class.forName("com.mysql.jdbc.Driver");
        try(Connection connection = DriverManager.getConnection(connectionUrl, userName, password); Statement statement = connection.createStatement())
        {
            //System.out.println("Connected");
            CreateScript createScript = new CreateScript();
            AddScript addScript = new AddScript();
            Rating r = new Rating();
            String Table;
            Table = createScript.getTable();
            //System.out.println(Table);
            statement.executeUpdate(Table);
            statement.executeUpdate(addScript.addToStudents(1, "Иван", "Иванов", "ИКПИ-85", "ИКСС", 4, 5, 3, r.Count(4,5,3,0)));
            statement.executeUpdate(addScript.addToStudents(2, "Пётр", "Петров", "ИКПИ-83", "ИКСС", 3, 5, 3, r.Count(3,5,3,0)));
            statement.executeUpdate(addScript.addToStudents(3, "Дмитрий", "Дмитриев", "ИКПИ-84", "ИКСС", 5, 5, 3, r.Count(5,5,3,0)));
            statement.executeUpdate(addScript.addToStudents(4, "Александр", "Александров", "ИКПИ-84", "ИКСС", 4, 4, 4, r.Count(4,4,4,0)));
            statement.executeUpdate(addScript.addToStudents(5, "Андрей", "Андреев", "ИКПИ-85", "ИКСС", 4, 3, 4, r.Count(4,3,4,0)));
            statement.executeUpdate(addScript.addToStudents(6, "Алексей", "Алексеев", "ИКПИ-81", "ИКСС", 3, 3, 4, r.Count(3,3,4,0)));
            statement.executeUpdate(addScript.addToStudents(7, "Максим", "Максимов", "ИКПИ-82", "ИКСС", 5, 5, 4, r.Count(5,5,4,0)));
            statement.executeUpdate(addScript.addToStudents(8, "Сергей", "Сергеев", "ИКПИ-83", "ИКСС", 4, 5, 4, r.Count(4,5,4,0)));
            statement.executeUpdate(addScript.addToStudents(9, "Михаил", "Михайлов", "ИКПИ-85", "ИКСС", 3, 3, 3, r.Count(3,3,3,0)));
            statement.executeUpdate(addScript.addToStudents(10, "Никита", "Никитин", "ИКПИ-81", "ИКСС", 3, 5, 3, r.Count(3,5,3,0)));
            //statement.executeUpdate(out.OutSorted());
        }
    }

    private static class AddScript {
        private String addToStudents(int student_id, String Name, String SerName, String StudentGroup, String Faculty, int baseMark, int progMark, int webMark, double rating){
            String s = "INSERT into Students VALUES (" + student_id + ", '" + Name + "', '" + SerName + "', '" + StudentGroup + "', '" + Faculty + "', " + baseMark + ", " + progMark + ", " + webMark + ", " + rating + ")";
            return s;
        }

    }
    private static class CreateScript {

        private String getTable(){
            String Table ;
            Table = createStudents();
            return Table;
        }
        private String createStudents() {
            String s = "CREATE TABLE IF NOT EXISTS Students(student_id int primary key, Name varchar(64), SerName varchar(64), StudentGroup varchar(64), Faculty varchar(64), baseMark int, progMark int, webMark int, rating double(4,2));";
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
