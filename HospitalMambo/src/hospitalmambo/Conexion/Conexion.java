/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalmambo.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Vis
 */
public class Conexion {
    private static final String stringconexion ="jdbc:sqlserver://DESKTOP-2I11P2E\\JLMAMBO_4F;database=Consulta;encrypt=false;trutServerCertificate=false;integratedSecurity=true";
    public static boolean Comp_conexion(){
        try (Connection con = DriverManager.getConnection(stringconexion)){
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
              
        
 
