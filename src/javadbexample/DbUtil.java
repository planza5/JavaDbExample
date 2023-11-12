/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javadbexample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pablo
 */
public class DbUtil {
    private static Logger logger = Logger.getLogger(DbUtil.class.toString());
    private Connection conn;
    
    private Connection connect() throws Exception{
        String database = "jdbc:odbc:exceldb";
        return DriverManager.getConnection(database, "", ""); 
    }
    
    public List<Person> getPersons(String field,String filter) throws Exception{
        System.out.println("filter="+filter);
        List<Person> persons=new ArrayList();
        
        if(conn==null){
            conn = connect();
        }
        
        Statement st = conn.createStatement();
        ResultSet rs = null;
        
        if(field==null || field.length()==0 || filter==null || filter.length()==0){
            rs=st.executeQuery("SELECT * FROM [Hoja1$]");
        }else{
            String query = "SELECT * FROM [Hoja1$] WHERE "+field+" LIKE '%"+filter+"%'";
            rs=st.executeQuery(query);            
        }
        
        
        while(rs.next()){
            Person p=new Person();
            String nombre=rs.getString("nombre");
            String apellido=rs.getString("apellido");
            int edad=rs.getInt("edad");
            Date fecha_nacimiento = rs.getDate("fecha_nacimiento");
            String direccion=rs.getString("direccion");
            String ciudad=rs.getString("ciudad");
            String provincia=rs.getString("provincia");
            int codigo_postal=rs.getInt("codigo_postal");
            String telefono=rs.getString("telefono");
            String email=rs.getString("email");
            String profesion=rs.getString("profesion");
            String empresa=rs.getString("empresa");
       
            p.setNombre(nombre);
            p.setApellido(apellido);
            p.setEdad(edad);
            p.setFecha_nacimiento(fecha_nacimiento);
            p.setDireccion(direccion);
            p.setCiudad(ciudad);
            p.setProvincia(provincia);
            p.setCodigo_postal(codigo_postal);
            p.setTelefono(telefono);
            p.setEmail(email);
            p.setProfesion(profesion);
            p.setEmpresa(empresa);
            
            persons.add(p);
        }
        
        rs.close();
        st.close();
        
        return persons;
    }
    
    
    static{
         try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
