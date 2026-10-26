/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hospitalmambo;

import hospitalmambo.Interfaz.VentanaInicial;
import hospitalmambo.Conexion.Conexion;
import javax.swing.JOptionPane;
/**
 *
 * @author Vis
 */
public class HospitalMambo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        if (!Conexion.Comp_conexion()){
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos. Verifica la conexion e intentalo nuevamente","Error de conexion", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        
        VentanaInicial VI = new VentanaInicial();
        VI.setVisible(true);
    }
    
}
