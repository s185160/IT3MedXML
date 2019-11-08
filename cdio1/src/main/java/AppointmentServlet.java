import entitites.Appointment;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/appointment/*")
public class AppointmentServlet extends HttpServlet {

    AppointmentDAO appointmentDAO = new AppointmentDAO();

//    public void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, java.io.IOException {
//        HttpSession session = request.getSession(true);
//
//       int userID =  Integer.parseInt((String) session.getAttribute("currentUserId"));
//        try {
//            List<Appointment> curentAppointments = appointmentDAO.getAllAppointmentsForId(userID);
//            request.setAttribute("allAppointments",curentAppointments);
//
//            //Disptching request
//
//            RequestDispatcher dispatcher = request.getRequestDispatcher("appointment-page.jsp");
//
//            if (dispatcher != null){
//
//                dispatcher.forward(request, response);
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int userID = Integer.parseInt((String) request.getParameter("cpr"));
            List<Appointment> curentAppointments = appointmentDAO.getAllAppointmentsForId(userID);

            response.setContentType("text/xml;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            
            for (Appointment model : curentAppointments) {
                writer.append("<Appointments>");
                writer.append("<Id>").append(String.valueOf(model.getId())).append("</Id>");
                writer.append("<Dato>").append(String.valueOf(model.getDato())).append("</Dato>");
                writer.append("<Varighed>").append(String.valueOf(model.getVarighed())).append("</Varighed>");
                writer.append("<Type>").append(String.valueOf(model.getType())).append("</Type>");                
                writer.append("<PersonID>").append(String.valueOf(model.getPersonID())).append("</PersonID>");
                writer.append("<SygehusID>").append(String.valueOf(model.getSygehusID())).append("</SygehusID>");
                writer.append("</Appointments>");
            }

        } catch (SQLException ex) {
            Logger.getLogger(AppointmentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
