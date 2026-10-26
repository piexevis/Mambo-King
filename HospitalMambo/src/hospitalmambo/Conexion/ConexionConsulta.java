/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalmambo.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Vis
 */
public class ConexionConsulta {
    private String stringconexion ="jdbc:sqlserver://DESKTOP-2I11P2E\\JLMAMBO_4F;database=Consulta;encrypt=false;trutServerCertificate=false;integratedSecurity=true";
    private Connection conexion() throws SQLException{
       
        Connection con = DriverManager.getConnection(stringconexion);
        return con;
       
    }
    public void inserar_datos(String hora, String fecha, String medico, String tipo, int Costo, int idCliente){//Constructor Consulta
        try(
            Connection con = conexion();
            PreparedStatement preins = con.prepareStatement(
                "INSERT INTO Consulta (Fecha, Hora, Tipo, Medico, Costo, IdCliente) VALUES (?, ?, ?, ?, ?, ?)"
            );
        ){
            preins.setString(1, fecha);
            preins.setString(2, hora);
            preins.setString(3, tipo);
            preins.setString(4, medico);
            preins.setInt(5, Costo);
            preins.setInt(6, idCliente);
            preins.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    
    /*
        Trae todas las consultas registradas para un cliente específico.
        Cada fila del resultado es un Object[] con el orden:
        { Fecha, Hora, Tipo, Medico, Costo }
        Se ordena de la más reciente a la más antigua.
        Si ocurre un error de conexión, devuelve una lista vacía (no null),
        para que la pantalla que la use no tenga que preocuparse por null.
    */
    public List<Object[]> obtenerConsultasPorCliente(int idCliente) {
        List<Object[]> consultas = new ArrayList<>();
        try (
            Connection con = conexion();
            PreparedStatement ps = con.prepareStatement(
                "SELECT Fecha, Hora, Tipo, Medico, Costo FROM Consulta WHERE IdCliente = ? ORDER BY Fecha DESC, Hora DESC"
            );
        ) {
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    consultas.add(new Object[] {
                        rs.getString("Fecha"),
                        rs.getString("Hora"),
                        rs.getString("Tipo"),
                        rs.getString("Medico"),
                        rs.getInt("Costo")
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultas;
    }
}