import java.util.*;
import java.sql.*;
public class AccionesCliente {
    
    /*
    Esta clase va a representar el manejo de todas las opciones con la
    cual opera el usuario
    */
    
    //crear una clase de conexion
    
    public static Connection getConnection(){
        String url, user, password;
        //donde esta mi bd
        url="jdbc:mysql:3306/localhost/cliente";
        user="root";
        password="system";
        
        //objeto de conexion
        Connection con = null;
        try{
            //cuando exista la conexion
            Class.forName("com.mysql.jdbc.Driver");
            //algunas veces no es necesario el puerto, pq ya esta por defecto en el driver
            url="jdbc:mysql://localhost/cliente";
            //enviamos los parametros de la conexion
            con = DriverManager.getConnection(url, user, password);
            
            System.out.println("Si se conecto");
            
        }catch(Exception e){
            System.out.println("No se conecto");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        
        return con;
    }
    
    
    //metodos correspondientes para
    //guardar
    
    public static int Guardar(DatosCliente a){
        int estatus = 0;
        
        try{
        
            //voy a mandar a llamar a mi metodo de conexion
            Connection con = AccionesCliente.getConnection();
            //ahora establezco mi querry
            String q = "insert into cliente (nom_cli, mail_cli, tel_cli, dir_cli, pass_cli)"
                    + " values (?,?,?,?)";
            //preparamos la sentencia de la querry
            PreparedStatement ps = con.prepareStatement(q);
            //tanto obtener como enviar los parametros gracias al encapsulamiento
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getCorreo());
            ps.setString(3, a.getTelefono());
            ps.setString(4, a.getDireccion());
            ps.setString(5, a.getContraseña());
            
            estatus = ps.executeUpdate();
            con.close();
            System.out.println("Registro exitoso");
            
        }catch(Exception e){
            System.out.println("No encontro la tabla");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return estatus;
    }
    
    //editar
    
    public static int Actualizar(DatosCliente a){
        int estatus = 0;
        
        try{
        
            //voy a mandar a llamar a mi metodo de conexion
            Connection con = AccionesCliente.getConnection();
            //ahora establezco mi querry
            String q = "update cliente set nom_cli = ?,"
                    + " mail_cli = ?,"
                    + " tel_cli = ?,"
                    + " dir_cli = ?"
                    + " pass_cli = ?"
                    + " where id_cli = ?";
            //preparamos la sentencia de la querry
            PreparedStatement ps = con.prepareStatement(q);
            //tanto obtener como enviar los parametros gracias al encapsulamiento
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getCorreo());
            ps.setString(3, a.getTelefono());
            ps.setString(4, a.getDireccion());
            ps.setString(5, a.getContraseña());
            ps.setInt(6, a.getId());
            estatus = ps.executeUpdate();
            con.close();
            System.out.println("Actualizacion exitosa");
            
        }catch(Exception e){
            System.out.println("No encontro la tabla");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return estatus;
    }
    
    //eliminar
    public static int Eliminar(int id){
        int estatus = 0;
        
        try{
        
            //voy a mandar a llamar a mi metodo de conexion
            Connection con = AccionesCliente.getConnection();
            //ahora establezco mi querry
            String q = "delete from cliente where id_cli = ?";
            //preparamos la sentencia de la querry
            PreparedStatement ps = con.prepareStatement(q);
            //tanto obtener como enviar los parametros gracias al encapsulamiento
            ps.setInt(1, id);
            
            estatus = ps.executeUpdate();
            con.close();
            System.out.println("Borrado exitoso");
            
        }catch(Exception e){
            System.out.println("No encontro la tabla");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return estatus;
    }
    //buscar por id
    public static DatosCliente getAlumnoById(int id){
        //objeto de alumno
        DatosCliente a = new DatosCliente();
        
        try{
        
            //voy a mandar a llamar a mi metodo de conexion
            Connection con = AccionesCliente.getConnection();
            //ahora establezco mi querry
            String q = "select * from cliente where id_cli = ?";
            //preparamos la sentencia de la querry
            PreparedStatement ps = con.prepareStatement(q);
            //tanto obtener como enviar los parametros gracias al encapsulamiento
            ps.setInt(1, id);
            //preparo la consulta
            ResultSet rs = ps.executeQuery();
            
            //buscamos dentro de la tabla
            if(rs.next()){
                a.setId(rs.getInt(1));
                a.setNombre(rs.getString(2));
                a.setCorreo(rs.getString(3));
                a.setTelefono(rs.getString(4));
                a.setDireccion(rs.getString(5));
                a.setContraseña(rs.getString(6));
            }
            
            con.close();
            System.out.println("Consulta exitoso");
            
        }catch(Exception e){
            System.out.println("No encontro la tabla");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return a;
    }
    
    
    //consulta general
    public static List<DatosCliente> getAllAlumnos(){
        //objeto de arraylist para almacenar los alumnos
        List<DatosCliente> lista = new ArrayList<DatosCliente>();
        
        try{
        
            //voy a mandar a llamar a mi metodo de conexion
            Connection con = AccionesCliente.getConnection();
            //ahora establezco mi querry
            String q = "select * from alumnos";
            //preparamos la sentencia de la querry
            PreparedStatement ps = con.prepareStatement(q);
            
            //preparo la consulta
            ResultSet rs = ps.executeQuery();
            
            //obtengo toda la tabla y como no se su dimension
            while(rs.next()){
                //objeto de alumno
                DatosCliente a = new DatosCliente();
                a.setId(rs.getInt(1));
                a.setNombre(rs.getString(2));
                a.setCorreo(rs.getString(3));
                a.setTelefono(rs.getString(4));
                a.setDireccion(rs.getString(5));
                a.setContraseña(rs.getString(6));
                lista.add(a);
            }
            
            con.close();
            System.out.println("Consulta exitoso");
            
        }catch(Exception e){
            System.out.println("No encontro la tabla");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return lista;
    } 
}