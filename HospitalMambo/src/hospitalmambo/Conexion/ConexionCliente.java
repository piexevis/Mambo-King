package hospitalmambo.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
/**
 *
 * @author Vis
 */
public class ConexionCliente {

    // Cadena de conexión: servidor, instancia, base de datos y tipo de autenticación
    private String stringconexion ="jdbc:sqlserver://DESKTOP-2I11P2E\\JLMAMBO_4F;database=Consulta;encrypt=false;trutServerCertificate=false;integratedSecurity=true";
    
    // Abre y devuelve una conexión nueva a la base de datos.
    // Centralizado aquí para no repetir DriverManager.getConnection(...) en cada método.
    private Connection conexion() throws SQLException{
       
        Connection con = DriverManager.getConnection(stringconexion);
        return con;
       
    }

    // Revisa si ya existe un cliente con el Rut indicado.
    // Se usa antes de insertar, para evitar registros duplicados.
    boolean existeCliente(String rut) throws SQLException {
        try(
            Connection con = conexion();
             // "?" es un parámetro seguro que se completa más abajo (evita inyección SQL)
            PreparedStatement ps = con.prepareStatement("SELECT 1 FROM Cliente WHERE Rut = ?");
        ){
            ps.setString(1, rut); // reemplaza el "?" por el valor real del rut
            ResultSet rs = ps.executeQuery(); // ejecuta el SELECT y trae el resultado
            return rs.next(); // true = encontró al menos una fila -> el rut ya existe
        }
    }

    // Busca un cliente por su Rut y devuelve su IdCliente.
    // Devuelve -1 si no existe ningún cliente con ese Rut.
    public int buscarClientePorRut(String rut) throws SQLException {
        try (
                Connection con = conexion();
                PreparedStatement ps = con.prepareStatement("SELECT IdCliente FROM Cliente WHERE Rut = ?");
        ) {
            ps.setString(1, rut);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("IdCliente");
            }
            return -1; // no encontrado
        }
    }

    /*  
        Inserta un cliente nuevo en la base de datos.
        Devuelve:
        -1  -> hubo un error de base de datos
        -2  -> el cliente ya existía (rut duplicado)
        >=1 -> el IdCliente generado para el nuevo registro
    */
       public int inserar_datos(String Nombre, String Correo, String Rut, int Edad){ //Constructors Cliente
    
        int idGenerado = -1; // valor por defecto: se asume error hasta que se demuestre lo contrario
        try{
            // Corta la ejecución si el rut ya está registrado, antes de intentar el INSERT
            if (existeCliente(Rut)) {
                return -2;
            }
        // try-with-resources: cierra automáticamente la conexión y el statement al terminar
        try(
            Connection con = conexion();
            PreparedStatement preins = con.prepareStatement(
                "INSERT INTO Cliente (Nombre, Edad, Correo, Rut) VALUES (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS // pide que devuelva el ID autogenerado (IdCliente)
            );
        ){
            // Rellena cada "?" del INSERT en el mismo orden en que aparecen
            preins.setString(1, Nombre);
            preins.setInt(2, Edad);
            preins.setString(3, Correo);
            preins.setString(4, Rut);
            preins.execute(); // ejecuta el INSERT

            // Recupera el IdCliente generado automáticamente por la base de datos
            ResultSet rs = preins.getGeneratedKeys();
            if (rs.next()) {
                idGenerado = rs.getInt(1); // primera (y única) columna devuelta: el ID nuevo
            }
        }
    }catch(SQLException e){
        // Si algo falla (conexión, sintaxis SQL, etc.), se imprime el detalle en consola
        // para poder depurar, sin detener el programa.
        e.printStackTrace();
    }
    return idGenerado; // entrega el resultado final a quien llamó al método
       }}