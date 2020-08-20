/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ghost
 */
@WebServlet(urlPatterns = {"/Guardad_Cliente"})
public class Guardad_Cliente extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            int id;
            String nombre, correo, telefono, direccion, contraseña;
            
            //obtener los parametros a traves de la peticion
            
            nombre = request.getParameter("Nombre");
            correo = request.getParameter("Correo");
            telefono = request.getParameter("Telefono");
            direccion = request.getParameter("Direccion");
            contraseña = request.getParameter("Contraseña");
            //necesito un obj alumno
            DatosCliente a = new DatosCliente();
            
            //enviar los parametros a la clase alumno
            
            a.setNombre(nombre);
            a.setCorreo(correo);
            a.setTelefono(telefono);
            a.setDireccion(direccion);
            a.setContraseña(contraseña);
            //ejecutar la sentencia o querry
            
            int estatus = AccionesCliente.Guardar(a);
            
            //comprobar si guardo al alumno
            
            
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Registro Cliente</title>");            
            out.println("</head>");
            out.println("<body>");
            
            if(estatus > 0){
            out.println("<h1>Registro Exitoso del Cliente</h1>"
                    + "<br>"
                    + "<a href='index.html' >Regresar al Menu</a>");
            }else{
            
            
            out.println("<h1>No se pudo realizar el registro</h1>"
                    + "<br>"
                    + "<a href='index.html' >Regresar al Menu</a>");
            }
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
