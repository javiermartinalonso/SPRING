package es.jmartin.ejemplos.spring.controlador;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.jmartin.ejemplos.spring.model.dto.Municipio;
import es.jmartin.ejemplos.spring.model.dto.Provincia;
import es.jmartin.ejemplos.spring.model.repository.MunicipioRepository;
import es.jmartin.ejemplos.spring.model.repository.PaisRepository;
import es.jmartin.ejemplos.spring.model.repository.ProvinciaRepository;

public class Ventana extends JFrame implements ActionListener, WindowListener {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4524751289151496355L;
	
	private JComboBox cb_Provincia;
	private JComboBox cb_municipios;
	
	private JPanel panel_Provincia;
		
	@Autowired
	private MunicipioRepository municipioRepository;
	@Autowired
	private PaisRepository paisRepository;
	@Autowired
	private ProvinciaRepository provinciaRepository;
	
	public MunicipioRepository getMunicipioRepository() {
		return municipioRepository;
	}

	public void setMunicipioRepository(MunicipioRepository municipioRepository) {
		this.municipioRepository = municipioRepository;
	}

	public PaisRepository getPaisRepository() {
		return paisRepository;
	}

	public void setPaisRepository(PaisRepository paisRepository) {
		this.paisRepository = paisRepository;
	}

	public ProvinciaRepository getProvinciaRepository() {
		return provinciaRepository;
	}

	public void setProvinciaRepository(ProvinciaRepository provinciaRepository) {
		this.provinciaRepository = provinciaRepository;
	}

	public Ventana() 
	{
		ApplicationContext contexto = new ClassPathXmlApplicationContext("/spring/app-context.xml");
		
		paisRepository = (PaisRepository) contexto.getBean("paisRepository");
		
		provinciaRepository = (ProvinciaRepository) contexto.getBean("provinciaRepository");
		
		municipioRepository = (MunicipioRepository) contexto.getBean("municipioRepository");
		
		setTitle("Combos Provincia-Municipios");
		setResizable(false);
		setBounds(0, 0, 534, 217);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
		
		crear_Interface();

		setVisible(true);
	}

	private void crear_Interface() 
	{
		panel_Provincia = new JPanel();
		panel_Provincia.setBounds(10, 11, 510, 170);
		getContentPane().add(panel_Provincia);
		panel_Provincia.setLayout(null);
				
		String tabla_Provincia[] = cargar_ProvinciaHibernate();
		String nombre_provincia = tabla_Provincia[0];
				
		String tabla_municipios[] = cargar_MunicipiosPorProvinciaHibernate(nombre_provincia);
		
		cb_Provincia = new JComboBox(tabla_Provincia);
		cb_Provincia.setName("cb_Provincia");
		cb_Provincia.setBounds(95, 32, 152, 25);
		cb_Provincia.addActionListener(this);
		
		cb_municipios = new JComboBox(tabla_municipios);
		cb_municipios.setSelectedIndex(-1);
		cb_municipios.setName("cb_municipios");
		cb_municipios.setBounds(329, 32, 171, 25);
		cb_municipios.addActionListener(this);
		
		cb_Provincia.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		panel_Provincia.add(cb_Provincia);
		panel_Provincia.add(cb_municipios);
		
		JLabel lbl_Provincia = new JLabel("Provincia");
		lbl_Provincia.setName("lbl_Provincia");
		lbl_Provincia.setBounds(23, 32, 91, 14);
		panel_Provincia.add(lbl_Provincia);
		
		JLabel lblMunicipios = new JLabel("Municipios");
		lblMunicipios.setName("lblMunicipios");
		lblMunicipios.setBounds(270, 37, 75, 14);
		panel_Provincia.add(lblMunicipios);
		
		
		validate();
		repaint();
		
		// A�ADIMOS A LA VENTANA EL ESCUCHADOR DE EVENTOS DE VENTANA
		this.addWindowListener(this);
	}
	
	
//	private String []  cargar_Provincia() {
//		//I_Provincia gestion_Provincia = new F_Provincia();
//		
//		I_Provincia gestion_Provincia = (I_Provincia) Inicio.contexto.getBean("F_Provincia");
//
//		List<Provincia> lista_Provincia = gestion_Provincia.consultar_Provincia();
//		String tabla_Provincia[] = new String[lista_Provincia.size()];
//		int posiciones = 0;
//		for (Provincia provincia : lista_Provincia) {
//			tabla_Provincia[posiciones] = String.valueOf(provincia.getCodigo_provincia())
//					+ " - " + provincia.getProvincia();
//			posiciones++;
//		}
//		
//		return tabla_Provincia;
//	}
	
	
	private String[] cargar_ProvinciaHibernate() 
	{				
		List<Provincia> lista_Provincia = getProvinciaRepository().findAll();
				
		String tabla_Provincia[] = new String[lista_Provincia.size()];
		int posiciones = 0;
		
		for (Provincia provincia : lista_Provincia) 
		{
			tabla_Provincia[posiciones] = String.valueOf(provincia.getIdprovincia())
					+ " - " + provincia.getNombre();
			posiciones++;
		}
		
		return tabla_Provincia;
	}
	
	
	
	
	
//	private String [] cargar_MunicipiosPorProvincia(String provincia) {
//		
//		Municipios_DAO muniDao = new Municipios_DAO();
//		
//		
//		
//		
//		Byte codigo_provincia = null;
//		
//		if (provincia.indexOf(" ")>-1)
//		{
//			codigo_provincia = new Byte(provincia.substring(0, provincia.indexOf(" ")));
//		}
//		else
//		{
//			codigo_provincia = new Byte(provincia);
//		}
//		
//		List<Municipios> listaMunicipios = muniDao.consultar_PorProvincia(codigo_provincia);
//		
//				
//		String tabla_municipios[] = new String[listaMunicipios.size()];
//		int posiciones = 0;
//		for (Municipios municipio : listaMunicipios) {
//			tabla_municipios[posiciones] = municipio.getMunicipio();
//			posiciones++;
//		}
//		
//
//		return tabla_municipios;
//	}
//	
	
	
	
	private String [] cargar_MunicipiosPorProvinciaHibernate(String nombre_provincia) 
	{		
//		String nombreProvinciaReal = nombre_provincia.split(" - ")[1]; 
//		List<Municipio> listaMunicipios = getMunicipioRepository().findAllByProvinciaNombre(nombreProvinciaReal);
		
		Integer idProvincia = Integer.valueOf(nombre_provincia.split(" - ")[0]); 
		List<Municipio> listaMunicipios = getMunicipioRepository().findAllByProvinciaIdProvincia(idProvincia);
		
		String tabla_municipios[] = new String[listaMunicipios.size()];
		
		//CUIDADO SI EL PRIMERO VIENE NULL
		int posiciones = 0;
		
		for (Iterator<Municipio> iterator = listaMunicipios.iterator(); iterator.hasNext();) 
		{
			Municipio municipioAux = (Municipio) iterator.next();
			tabla_municipios[posiciones] = municipioAux.getNombre();
			posiciones++;
		}
		
		return tabla_municipios;
	}
	
	
	
	/**
	 * Proceso unico de cierre de ventana y fin de programa.
	 */
	private void cierre_Ventana() 
	{
		// OPCION COMPLETA DE CONFIRMDIALOG PARA COLOCARLE EL ICONO QUE NOS GUSTE
		int opcion = JOptionPane.showConfirmDialog(this,
				"�Desea salir del programa?", "CIERRE DE PROGRAMA",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if (opcion == JOptionPane.YES_OPTION) 
		{
			// LUGAR DONDE REALIZAR CUALQUIER PROCESO DE CIERRE DE LA
			// APLICACION.
			System.exit(0);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent evento) 
	{
		String opcion_elegida = ((JComponent) evento.getSource()).getName();
		if (opcion_elegida.equals("cb_Provincia")){
			
			String provi_elegida = (String) ((JComboBox) evento.getSource()).getSelectedItem();
			provi_elegida=provi_elegida.substring(0, provi_elegida.indexOf(" "));
			
			
			String tabla_municipios[] = cargar_MunicipiosPorProvinciaHibernate(provi_elegida);
			
			//Actualizamos el modelo de datos del combo
			DefaultComboBoxModel  listmodel = new DefaultComboBoxModel<>(tabla_municipios); 
			cb_municipios.setModel(listmodel);
			
			cb_municipios.setSelectedIndex(-1);
			
			//OTRA OPCION ELIMINANDO Y CREANDO UN NUEVO COMBO
//			panel_Provincia.remove(cb_municipios);
//			cb_municipios = new JComboBox(tabla_municipios);
//			cb_municipios.setBounds(368, 32, 125, 14);
//			panel_Provincia.add(cb_municipios);
			
			
			//BASTA CON REPINTAR EL PANEL
			panel_Provincia.validate();
			panel_Provincia.repaint();
				
////	O BIEN PODEMOS REPINTAR TODO, MALA OPCION SI HAY QUE REPINTAR MUCHOS MAS OBJETOS INNECESRIAMENTE
//			validate();
//			repaint();
		}	
	}


	// ***** EVENTOS DE VENTANA *****
	/**
	 * Evento de cierre de ventana. Solo llama a otro proceso interno de la
	 * ventana.
	 */
	@Override
	public void windowClosing(WindowEvent evento) 
	{
		this.cierre_Ventana();
	}

	@Override
	public void windowActivated(WindowEvent arg0) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void windowClosed(WindowEvent arg0) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void windowIconified(WindowEvent arg0) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void windowOpened(WindowEvent arg0) 
	{
		// TODO Auto-generated method stub
	}
}
