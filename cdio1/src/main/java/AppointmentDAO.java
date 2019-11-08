import entitites.Appointment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    private Connection conn;
    private Statement statement;

    public AppointmentDAO() {
        conn = ConnectionProvider.getConnection();
    }

    public List<Appointment> getAllAppointmentsForId(int personId) throws SQLException {
        statement = conn.createStatement();
        //String query = "select * from appointment where personid="+personId+";";
        String query = "select * from Person p join Appointment a on p.ID=a.PersonId join Sygehus s on a.SygehusID=s.ID where CPR = '"+personId+"';";
        ResultSet set = statement.executeQuery(query);
        List<Appointment> allAppointments = new ArrayList<>();
        while(set.next()){
            int id = Integer.parseInt(set.getString("id"));
            Date dato = Date.valueOf(set.getString("dato"));
            int varighed = Integer.parseInt(set.getString("Varighed"));
            String type = set.getString("UndersoegelseType");//study type
            int sygehusID = Integer.parseInt(set.getString("SygehusId"));
            Appointment appointment = new Appointment(id, dato, varighed, type, sygehusID, personId);
            allAppointments.add(appointment);
        }
        return allAppointments;
    }

    public void removeAppointment(int appointmentID) throws SQLException {
        statement = conn.createStatement();
        String query = "delete from appointment where id="+appointmentID+";";
        statement.executeUpdate(query);
    }
}
