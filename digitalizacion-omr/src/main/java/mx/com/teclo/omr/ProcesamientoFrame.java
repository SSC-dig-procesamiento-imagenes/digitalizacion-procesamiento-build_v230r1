package mx.com.teclo.omr;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

import mx.com.teclo.base.util.methods.Methods;
import mx.com.teclo.base.vo.configuracion.ConfiguracionVO;
import mx.com.teclo.base.vo.lotes.LoteVO;
import mx.com.teclo.base.vo.plantilla.PlantillaVO;

import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class ProcesamientoFrame extends JFrame {

	private JPanel contentPane;
	JLabel txt_nb_lote = new JLabel((String) null);
	JLabel txt_met_lote = new JLabel("");
	JLabel txt_fh_lote = new JLabel((String) null);
	JPanel panel_10 = new JPanel();
	JLabel txt_modelo = new JLabel((String) null);
	JLabel txt_nombreconfig = new JLabel((String) null);
	JLabel txt_marca = new JLabel((String) null);
	JLabel txt_formato = new JLabel((String) null);
	JLabel txt_resolucion = new JLabel((String) null);
	JLabel txt_tipopapel = new JLabel("<dynamic>");
	JLabel txt_ladospapel = new JLabel((String) null);
	JList list = new JList();
	
	JLabel txt_totalavance = new JLabel("0");
	JLabel txt_totalimg = new JLabel("0");
	DefaultListModel dlm = new DefaultListModel();
	CustomPanel jp_progress = new CustomPanel();
	
	ConfiguracionVO conf;
	LoteVO lote;
	Methods met = new Methods();
	
	
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ProcesamientoFrame frame = new ProcesamientoFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public ProcesamientoFrame(final LoteVO lote, final ConfiguracionVO conf) {
		
		this.lote = lote;
		this.conf = conf;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(-6, 0, 1381, 734);
//		this.setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_contenedorMain = new JPanel();
		panel_contenedorMain.setBounds(0, 0, 1381, 734);
		contentPane.add(panel_contenedorMain);
		panel_contenedorMain.setBackground(new java.awt.Color(236, 240, 245));
		panel_contenedorMain.setLayout(null);
		
		JPanel panel_footer = new JPanel();
		panel_footer.setLayout(null);
		panel_footer.setBackground(new Color(211, 26, 43));
		panel_footer.setBounds(0, 681, 1381, 53);
		panel_contenedorMain.add(panel_footer);
		
		JLabel label_8 = new JLabel("Derechos de autor © Teclo Mexicana ");
		label_8.setForeground(Color.WHITE);
		label_8.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_8.setBounds(1031, 0, 239, 42);
		panel_footer.add(label_8);
		
		JPanel panel_menu = new JPanel();
		panel_menu.setLayout(null);
		panel_menu.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_menu.setBackground(Color.WHITE);
		panel_menu.setBounds(0, 53, 122, 631);
		panel_contenedorMain.add(panel_menu);
		
		JPanel btn_menuConsultaLotes = new JPanel();
		btn_menuConsultaLotes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeToConsultaLotes();
			}
		});
		btn_menuConsultaLotes.setLayout(null);
		btn_menuConsultaLotes.setBackground(SystemColor.menu);
		btn_menuConsultaLotes.setBounds(0, 0, 122, 75);
		btn_menuConsultaLotes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btn_menuConsultaLotes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panel_menu.add(btn_menuConsultaLotes);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon("C:\\WorkSpace_ybrran\\digitalizacion-procesamiento-build\\digitalizacion-app\\src\\main\\images\\icons8_Search_36px.png"));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(37, 11, 46, 36);
		btn_menuConsultaLotes.add(label_4);
		
		JLabel label_5 = new JLabel("Consulta de Lotes");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_5.setBounds(0, 47, 122, 14);
		btn_menuConsultaLotes.add(label_5);
		
		JPanel btn_menuProcesamiento = new JPanel();
		btn_menuProcesamiento.setLayout(null);
		btn_menuProcesamiento.setBackground(new Color(211, 26, 43));
		btn_menuProcesamiento.setBounds(0, 74, 122, 75);
		btn_menuProcesamiento.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btn_menuProcesamiento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panel_menu.add(btn_menuProcesamiento);
		
		JLabel label_6 = new JLabel("");
		label_6.setIcon(new ImageIcon("C:\\WorkSpace_ybrran\\digitalizacion-procesamiento-build\\digitalizacion-app\\src\\main\\images\\icons8_Gears_36px.png"));
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(37, 11, 46, 36);
		btn_menuProcesamiento.add(label_6);
		
		JLabel label_7 = new JLabel("Procesamiento");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setForeground(Color.WHITE);
		label_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_7.setBounds(0, 50, 122, 25);
		btn_menuProcesamiento.add(label_7);
		
		JPanel panel_header = new JPanel();
		panel_header.setLayout(null);
		panel_header.setBackground(Color.WHITE);
		panel_header.setBounds(0, 0, 1381, 54);
		panel_contenedorMain.add(panel_header);
		panel_header.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\WorkSpace_ybrran\\digitalizacion-procesamiento-build\\digitalizacion-app\\src\\main\\images\\LogotipoTeclo5.png"));
		label.setBounds(117, 0, 133, 44);
		panel_header.add(label);
		
		JPanel btn_closeWindow = new JPanel();
		btn_closeWindow.setVisible(false);
		btn_closeWindow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "Estas seguro de que quieres salir de la aplicación");
				if (confirm == 0) {
		            System.exit(1);
		        }
			}
		});
		btn_closeWindow.setLayout(null);
		btn_closeWindow.setBorder(null);
		btn_closeWindow.setBackground(Color.WHITE);
		btn_closeWindow.setBounds(1260, 1, 93, 43);
		btn_closeWindow.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panel_header.add(btn_closeWindow);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("C:\\WorkSpace_ybrran\\digitalizacion-procesamiento-build\\digitalizacion-app\\src\\main\\images\\icons8_Cancel_50px.png"));
		label_1.setBounds(22, 0, 50, 43);
		btn_closeWindow.add(label_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(30, 0, 57, 43);
		panel_header.add(panel_2);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon("C:\\WorkSpace_ybrran\\digitalizacion-procesamiento-build\\digitalizacion-app\\src\\main\\images\\icons8_Menu_35px.png"));
		label_2.setBounds(10, 0, 35, 44);
		panel_2.add(label_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(360, 0, 703, 44);
		panel_header.add(panel_3);
		
		JLabel label_3 = new JLabel("Sistema de Digitalización de Boletas");
		label_3.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		label_3.setBounds(130, 0, 409, 44);
		panel_3.add(label_3);
		
		JPanel panel_contenedor = new JPanel();
		panel_contenedor.setBackground(Color.WHITE);
		panel_contenedor.setBounds(132, 127, 1218, 543);
		panel_contenedor.setBorder(new LineBorder(Color.GRAY));
		panel_contenedorMain.add(panel_contenedor);
		panel_contenedor.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(458, 30, 372, 441);
		panel_contenedor.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(SystemColor.inactiveCaptionBorder);
		panel_1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		panel_1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panel_1.setBounds(56, 288, 272, 36);
		panel.add(panel_1);
		
		JLabel lblTotalDeImgenes = new JLabel("Total de imágenes");
		lblTotalDeImgenes.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalDeImgenes.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotalDeImgenes.setBounds(10, 0, 155, 36);
		panel_1.add(lblTotalDeImgenes);
		
		JLabel label_12 = new JLabel("");
		label_12.setIcon(new ImageIcon("C:\\WorkSpace_ybrran\\digitalizacion-procesamiento-build\\digitalizacion-app\\src\\main\\images\\icons8_Checked_25px.png"));
		label_12.setBounds(235, 0, 30, 36);
		panel_1.add(label_12);
		
		
		txt_totalimg.setForeground(new Color(47, 79, 79));
		txt_totalimg.setHorizontalAlignment(SwingConstants.CENTER);
		txt_totalimg.setFont(new Font("Tahoma", Font.BOLD, 15));
		txt_totalimg.setBounds(161, 0, 64, 36);
		panel_1.add(txt_totalimg);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(SystemColor.inactiveCaptionBorder);
		panel_4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		panel_4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panel_4.setBounds(56, 335, 272, 36);
		panel.add(panel_4);
		
		JLabel lblProcesadas = new JLabel("Procesadas");
		lblProcesadas.setHorizontalAlignment(SwingConstants.LEFT);
		lblProcesadas.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblProcesadas.setBounds(10, 0, 155, 36);
		panel_4.add(lblProcesadas);
		
		JLabel label_14 = new JLabel("");
		label_14.setIcon(new ImageIcon("C:\\WorkSpace_ybrran\\digitalizacion-procesamiento-build\\digitalizacion-app\\src\\main\\images\\icons8_Checked_25px.png"));
		label_14.setBounds(235, 0, 30, 36);
		panel_4.add(label_14);
		
		
		txt_totalavance.setHorizontalAlignment(SwingConstants.CENTER);
		txt_totalavance.setForeground(new Color(47, 79, 79));
		txt_totalavance.setFont(new Font("Tahoma", Font.BOLD, 15));
		txt_totalavance.setBounds(161, 0, 64, 36);
		panel_4.add(txt_totalavance);
		
		JPanel btn_iniciarprocessing = new JPanel();
		btn_iniciarprocessing.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		btn_iniciarprocessing.setLayout(null);
		btn_iniciarprocessing.setBackground(new Color(211, 26, 43));
		btn_iniciarprocessing.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btn_iniciarprocessing.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btn_iniciarprocessing.setBounds(135, 382, 139, 36);
		panel.add(btn_iniciarprocessing);
		
		JLabel lblIniciar = new JLabel("Iniciar");
		lblIniciar.setForeground(Color.WHITE);
		lblIniciar.setHorizontalAlignment(SwingConstants.CENTER);
		lblIniciar.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIniciar.setBounds(35, 0, 105, 36);
		btn_iniciarprocessing.add(lblIniciar);
		
		JLabel label_16 = new JLabel("");
		label_16.setIcon(new ImageIcon("C:\\WorkSpace_ybrran\\digitalizacion-procesamiento-build\\digitalizacion-app\\src\\main\\images\\icons8_Play_25px.png"));
		label_16.setBounds(21, 0, 30, 36);
		btn_iniciarprocessing.add(label_16);
		jp_progress.setBackground(Color.WHITE);
		
		
		jp_progress.setBounds(10, 11, 352, 252);
		panel.add(jp_progress);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		panel_6.setBounds(868, 30, 316, 441);
		panel_contenedor.add(panel_6);
		panel_6.setLayout(null);
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		panel_7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panel_7.setBackground(new Color(230, 230, 250));
		panel_7.setBounds(21, 11, 46, 43);
		panel_6.add(panel_7);
		
		JLabel label_17 = new JLabel("");
		label_17.setIcon(new ImageIcon("C:\\WorkSpace_ybrran\\digitalizacion-procesamiento-build\\digitalizacion-app\\src\\main\\images\\icons8_Microsoft_Excel_45px.png"));
		label_17.setBounds(0, 0, 45, 45);
		panel_7.add(label_17);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 65, 296, 352);
		panel_6.add(scrollPane);
		
		
		scrollPane.setViewportView(list);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_8.setBackground(Color.WHITE);
		panel_8.setBounds(26, 43, 351, 121);
		panel_contenedor.add(panel_8);
		panel_8.setLayout(null);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(211, 26, 43));
		panel_9.setBounds(0, 0, 351, 25);
		panel_8.add(panel_9);
		
		JLabel label_13 = new JLabel("Configuración del Lote");
		label_13.setForeground(Color.WHITE);
		label_13.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_9.add(label_13);
		
		JLabel label_18 = new JLabel("Nombre del Lote:");
		label_18.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_18.setBounds(20, 40, 127, 15);
		panel_8.add(label_18);
		
		JLabel label_34 = new JLabel("Método:");
		label_34.setHorizontalAlignment(SwingConstants.RIGHT);
		label_34.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_34.setBounds(19, 59, 103, 25);
		panel_8.add(label_34);
		
		JLabel label_35 = new JLabel("Fecha del Lote:");
		label_35.setHorizontalAlignment(SwingConstants.RIGHT);
		label_35.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_35.setBounds(10, 82, 112, 28);
		panel_8.add(label_35);
		
		
		txt_met_lote.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_met_lote.setBounds(143, 59, 152, 25);
		panel_8.add(txt_met_lote);
		
		
		txt_nb_lote.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_nb_lote.setBounds(149, 40, 136, 14);
		panel_8.add(txt_nb_lote);
		
		
		txt_fh_lote.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_fh_lote.setBounds(148, 82, 136, 25);
		panel_8.add(txt_fh_lote);
		
		
		panel_10.setLayout(null);
		panel_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_10.setBackground(Color.WHITE);
		panel_10.setBounds(26, 175, 351, 296);
		panel_contenedor.add(panel_10);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBackground(new Color(211, 26, 43));
		panel_12.setBounds(0, 0, 351, 24);
		panel_10.add(panel_12);
		
		JLabel label_19 = new JLabel("Configuración del Escaner");
		label_19.setForeground(Color.WHITE);
		label_19.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_12.add(label_19);
		
		JPanel panel_13 = new JPanel();
		panel_13.setLayout(null);
		panel_13.setBackground(Color.WHITE);
		panel_13.setBounds(10, 147, 103, 50);
		panel_10.add(panel_13);
		
		JLabel label_20 = new JLabel("Formato:");
		label_20.setHorizontalAlignment(SwingConstants.RIGHT);
		label_20.setBounds(0, 25, 103, 18);
		panel_13.add(label_20);
		
		JLabel label_21 = new JLabel("Dpi:");
		label_21.setHorizontalAlignment(SwingConstants.RIGHT);
		label_21.setBounds(0, 0, 103, 18);
		panel_13.add(label_21);
		
		JPanel panel_14 = new JPanel();
		panel_14.setLayout(null);
		panel_14.setBackground(Color.WHITE);
		panel_14.setBounds(127, 147, 214, 50);
		panel_10.add(panel_14);
		
		
		txt_formato.setHorizontalAlignment(SwingConstants.LEFT);
		txt_formato.setForeground(new Color(0, 51, 0));
		txt_formato.setBounds(0, 25, 103, 18);
		panel_14.add(txt_formato);
		
		
		txt_resolucion.setHorizontalAlignment(SwingConstants.LEFT);
		txt_resolucion.setForeground(new Color(0, 51, 0));
		txt_resolucion.setBounds(0, 0, 103, 18);
		panel_14.add(txt_resolucion);
		
		JPanel panel_15 = new JPanel();
		panel_15.setLayout(null);
		panel_15.setBackground(Color.WHITE);
		panel_15.setBounds(10, 35, 103, 69);
		panel_10.add(panel_15);
		
		JLabel label_24 = new JLabel("Marca:");
		label_24.setHorizontalAlignment(SwingConstants.RIGHT);
		label_24.setBackground(Color.WHITE);
		label_24.setBounds(0, 28, 103, 18);
		panel_15.add(label_24);
		
		JLabel label_25 = new JLabel("Modelo:");
		label_25.setHorizontalAlignment(SwingConstants.RIGHT);
		label_25.setBounds(0, 51, 103, 18);
		panel_15.add(label_25);
		
		JLabel label_26 = new JLabel("Configuración:");
		label_26.setHorizontalAlignment(SwingConstants.RIGHT);
		label_26.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_26.setBackground(Color.WHITE);
		label_26.setBounds(0, 0, 103, 18);
		panel_15.add(label_26);
		
		JPanel panel_16 = new JPanel();
		panel_16.setLayout(null);
		panel_16.setBackground(Color.WHITE);
		panel_16.setBounds(127, 35, 214, 69);
		panel_10.add(panel_16);
		
		
		txt_marca.setHorizontalAlignment(SwingConstants.LEFT);
		txt_marca.setForeground(new Color(0, 51, 0));
		txt_marca.setBounds(0, 26, 94, 20);
		panel_16.add(txt_marca);
		
		
		txt_modelo.setHorizontalAlignment(SwingConstants.LEFT);
		txt_modelo.setForeground(new Color(0, 51, 0));
		txt_modelo.setBounds(0, 47, 153, 22);
		panel_16.add(txt_modelo);
		
		
		txt_nombreconfig.setForeground(Color.BLACK);
		txt_nombreconfig.setFont(new Font("Tahoma", Font.BOLD, 12));
		txt_nombreconfig.setBounds(0, 0, 214, 20);
		panel_16.add(txt_nombreconfig);
		
		JPanel panel_17 = new JPanel();
		panel_17.setLayout(null);
		panel_17.setBackground(Color.WHITE);
		panel_17.setBounds(10, 104, 103, 43);
		panel_10.add(panel_17);
		
		JLabel label_30 = new JLabel("Lados de imágen:");
		label_30.setHorizontalAlignment(SwingConstants.RIGHT);
		label_30.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_30.setBounds(0, 25, 103, 18);
		panel_17.add(label_30);
		
		JLabel label_31 = new JLabel("Papel:");
		label_31.setHorizontalAlignment(SwingConstants.RIGHT);
		label_31.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_31.setBounds(0, 2, 103, 18);
		panel_17.add(label_31);
		
		JPanel panel_18 = new JPanel();
		panel_18.setLayout(null);
		panel_18.setBackground(Color.WHITE);
		panel_18.setBounds(127, 104, 214, 43);
		panel_10.add(panel_18);
		
		
		txt_ladospapel.setHorizontalAlignment(SwingConstants.LEFT);
		txt_ladospapel.setForeground(new Color(0, 51, 0));
		txt_ladospapel.setBounds(0, 24, 214, 19);
		panel_18.add(txt_ladospapel);
		
		
		txt_tipopapel.setHorizontalAlignment(SwingConstants.LEFT);
		txt_tipopapel.setForeground(new Color(0, 51, 0));
		txt_tipopapel.setBounds(0, 0, 214, 19);
		panel_18.add(txt_tipopapel);
		
		JPanel panel_wizard = new JPanel();
		panel_wizard.setLayout(null);
		panel_wizard.setBounds(395, 53, 606, 74);
		panel_wizard.setBackground(new java.awt.Color(236, 240, 245));
		panel_contenedorMain.add(panel_wizard);
		
		JLabel label_9 = new JLabel("");
		label_9.setIcon(new ImageIcon("C:\\WorkSpace_ybrran\\digitalizacion-procesamiento-build\\digitalizacion-app\\src\\main\\images\\wizard3.png"));
		label_9.setBounds(43, 0, 524, 74);
		panel_wizard.add(label_9);
		
		
		//Metodos al iniciar la aplicacion
		setConfigurationData();
		setLoteData();
		
	}
	
	public List<String> getAllImages(){
		List<String> imagesall = new ArrayList<String>();
		return imagesall=met.getImages(lote.getRutaAlmacenamiento(), null, false);
	}
	
	public void changeToConsultaLotes() {
	 }
	
	public void setConfigurationData() {
		 txt_nombreconfig.setText(lote.getConfiguracionEscaner().getNombreConfiguracionEscaner());
		 txt_marca.setText(lote.getConfiguracionEscaner().getEscaner().getMarca());
		 txt_modelo.setText(lote.getConfiguracionEscaner().getEscaner().getModelo());
		 txt_resolucion.setText(lote.getConfiguracionEscaner().getResolucion().getNombre());
		 txt_formato.setText(lote.getConfiguracionEscaner().getTipoArchivos().getNombre());
		 txt_tipopapel.setText(lote.getConfiguracionEscaner().getTamanioPapael().getNombre()+"");
		 txt_ladospapel.setText(lote.getConfiguracionEscaner().getSuministroPapel().getNombre());
	 }
	 
	 public void setLoteData() {
		 txt_nb_lote.setText(lote.getNombreLote());
		 String metodo="";
		 if(lote.getMetDigitalizacion()==1) {
			 metodo="Digitalizacion Directa";
		 }else {
			 metodo="Digitalizacion Externa";
		 }
		 txt_met_lote.setText(metodo);
		 
//		Date d = new Date(lote.getFechaCracionLote());
//		ConfiguracionVO conf = new ConfiguracionVO();
//    	String fecha= met.convertDateToString(d, conf.getFormatDateDecode());
//    	System.out.println("esta fecha:"+fecha);
		txt_fh_lote.setText(lote.getFechaCracionLote());
	 }
	 
	 public void closeFrame() {
		 this.dispose();
	 }
	 
	 public void setTextToLogResultados(String texto) {
		 dlm.addElement(texto);            
		 list.setModel(dlm);
		 setListToBottom();
		 
	 }
	 
	 public void setListToBottom() {
		 int lastIndex = list.getModel().getSize() - 1;
         if (lastIndex >= 0) {
        	 list.ensureIndexIsVisible(lastIndex);
         }
	 }
	 
	 public void setTotal(String text) {
		 txt_totalimg.setText(text);
	 }
	 
	 public void setTotalAvance(String text) {
		 txt_totalavance.setText(text);
	 }
	 
	 public void updateGraphics(int num) {
		 jp_progress.UpdateProgress(num);
         jp_progress.repaint();
		 
	 }
}
