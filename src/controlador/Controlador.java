/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Persona;
import modelo.PersonaDAO;
import vista.Principal;

/**
 *
 * @author ismael engrique
 */
public class Controlador implements ActionListener {
    
     PersonaDAO dao = new PersonaDAO();
    Persona p = new Persona();

    Principal prin = new Principal();
    DefaultTableModel modelo = new DefaultTableModel();

    public Controlador(Principal prin) {
        this.prin = prin;
        this.prin.btnListar.addActionListener(this);
        this.prin.btnGuardar1.addActionListener(this);
        this.prin.btnModifixar.addActionListener(this);
        this.prin.btnEliminar.addActionListener(this);
        this.prin.btnBuscar.addActionListener(this);
    }

    public void Listar(JTable tabla){
        modelo = (DefaultTableModel)tabla.getModel();

        // Limpiar el modelo de la tabla
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        List<Persona> lista = (List<Persona>) dao.listar();
        Object[] object = new Object[4];

        for(int i = 0; i < lista.size(); i++){
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombre();
            object[2] = lista.get(i).getCorreo();
            object[3] = lista.get(i).getTelefono();

            //agregamos al table los datos
            modelo.addRow(object);
        }
    }
    
	public void agregar() {
		String nombre = prin.jTextField2.getText();
		String correo = prin.tfCorreo.getText();
		String telefono = prin.tfTelefono.getText();

		p.setNombre(nombre);
		p.setCorreo(correo);
		p.setTelefono(telefono);

		int r = dao.agregar(p);

		if (r == 1) {
			JOptionPane.showMessageDialog(null, "Persona agregada");    
			prin.jTextField2.setText("");
	        prin.tfCorreo.setText("");
	        prin.tfTelefono.setText("");
		} else {
			JOptionPane.showMessageDialog(null, "Error al agregar");
		}
	}
	
	public void modificar() {
		int id = Integer.parseInt(prin.tfID.getText());
		String nombre = prin.jTextField2.getText();
		String correo = prin.tfCorreo.getText();
		String telefono = prin.tfTelefono.getText();

		p.setId(id);
		p.setNombre(nombre);
		p.setCorreo(correo);
		p.setTelefono(telefono);

		int r = dao.actualizar(p);

		if (r == 1) {
			JOptionPane.showMessageDialog(null, "Persona modificada");
			prin.jTextField2.setText("");
			prin.tfCorreo.setText("");
			prin.tfTelefono.setText("");
		} else {
			JOptionPane.showMessageDialog(null, "Error al modificar");
		}
	}
	
	public void eliminar() {
		int id = Integer.parseInt(prin.tfID.getText());
		dao.eliminar(id);
		JOptionPane.showMessageDialog(null, "Persona eliminada");
		prin.jTextField2.setText("");
		prin.tfCorreo.setText("");
		prin.tfTelefono.setText("");
	}
	

	public void filtrarPorNombre(JTable tabla) {
	    String nombre = prin.jTextField2.getText();
	    filtrar(tabla, nombre, "nombre");
	}
	
	public void filtrarPorTelefono(JTable tabla) {
	    String telefono = prin.tfTelefono.getText();
	    filtrar(tabla, telefono, "telefono");
	}
	
	public void filtrarPorCorreo(JTable tabla) {
	    String correo = prin.tfCorreo.getText();
	    filtrar(tabla, correo, "correo");
	}
	
	public void filtrar(JTable tabla, String valor, String campo) {
	    modelo = (DefaultTableModel) tabla.getModel();
	
	    while (modelo.getRowCount() > 0) {
	        modelo.removeRow(0);
	    }
	
	    List<Persona> lista = (List<Persona>) dao.filtrar(valor, campo);
	    Object[] object = new Object[4];
	
	    for (int i = 0; i < lista.size(); i++) {
	        object[0] = lista.get(i).getId();
	        object[1] = lista.get(i).getNombre();
	        object[2] = lista.get(i).getCorreo();
	        object[3] = lista.get(i).getTelefono();
	
	        modelo.addRow(object);
	    }
	
	    if (lista.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "No se encontraron resultados");
	    }
	}

	
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == prin.btnListar) {
            Listar(prin.jTabla);
        }
        
		if (e.getSource() == prin.btnGuardar1) {
			agregar();
		}
		
		if (e.getSource() == prin.btnModifixar) {
			modificar();
		}
		
		if (e.getSource() == prin.btnEliminar) {
			eliminar();
        }
		

	    if (e.getSource() == prin.btnBuscar) {
	        String filterOption = (String) prin.CbFiltar.getSelectedItem();
	        switch (filterOption) {
	            case "Nombre":
	                filtrarPorNombre(prin.jTabla);
	                break;
	            case "Teléfono":
	                filtrarPorTelefono(prin.jTabla);
	                break;
	            case "Correo":
	                filtrarPorCorreo(prin.jTabla);
	                break;
	        }
	    }
    }
    
}
