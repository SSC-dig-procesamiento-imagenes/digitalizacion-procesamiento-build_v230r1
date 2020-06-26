package mx.com.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.border.LineBorder;

import mx.com.teclo.base.context.BeanLocator;
import mx.com.teclo.base.util.methods.Methods;
import mx.com.teclo.base.vo.bitacora.BitacoraVO;
import mx.com.teclo.base.vo.configuracion.ConfiguracionVO;
import mx.com.teclo.base.vo.images.ImagenMediasAritmeticas;
import mx.com.teclo.base.vo.images.ImagenVO;
import mx.com.teclo.base.vo.images.ImagenValidacionVO;
import mx.com.teclo.base.vo.lotes.LoteVO;
import mx.com.teclo.base.vo.usuario.UsuarioVO;
import mx.com.teclo.omr.Omr;

import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;

import mx.com.app.frames.reanudar.ReanudarLoteFrame;
import mx.com.app.services.BitacoraRestClientService;
import mx.com.app.services.LotesRestClientService;
//import mx.com.app.services.OmrService;
import mx.com.app.services.RestclientService;
import javax.swing.JScrollPane;
import javax.swing.JList;

import mx.com.teclo.base.util.enumerados.ConfigEnumStrings;
import mx.com.teclo.base.util.enumerados.ConfiguracionEnum;
import mx.com.teclo.base.util.enumerados.ParametrosBitacoraEnum;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileSystemView;

import org.apache.commons.io.FileUtils;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;

public class ValidacionFrame extends JFrame {

//	private Omr omr = (Omr) BeanLocator.getService("omr");
	private JPanel contentPane;
	JList<String> list = new JList<String>();
	DefaultListModel<String> dlm = new DefaultListModel<String>();
	boolean allright=false;
	
	JPanel panel_check_compresion = new JPanel();
	JPanel panel_check_asociacion = new JPanel();
	JPanel panel_check_posiblesinconsistencias = new JPanel();
	JPanel panel_check_nomenclatura = new JPanel();
	JPanel panel_check_archivodaniado = new JPanel();
	JPanel panel_listaimages = new JPanel();
	
	JLabel icon_archivodaniado = new JLabel("");
	JLabel icon_formatocompresion = new JLabel("");
	JLabel icon_nomenclatura = new JLabel("");
	JLabel icon_posiblesinconsistencias = new JLabel("");
	JLabel icon_asociacionlados = new JLabel("");
	JLabel icon_x = new JLabel("");
	JLabel label_12 = new JLabel("");
	
	JLabel label_17 = new JLabel("");
	JPanel btn_delete = new JPanel();
	JPanel btn_reemplazarimg = new JPanel();
	JLabel lbl_tipvalidacion = new JLabel("");
	JLabel lbl_currentimagen = new JLabel("");
	JPanel btn_procesarimg = new JPanel();
	
	JPanel btn_renombrarvalidar = new JPanel();
	JPanel btn_cambiarformato = new JPanel();
	
	JLabel txt_total = new JLabel("...");
	JLabel txt_titleimages = new JLabel("Imágenes");
	JLabel txt_title_img = new JLabel("...");
	
	JLabel txt_metodo_lote = new JLabel("");
	JLabel txt_fh_lote = new JLabel((String) null);
	JLabel lblLadosImagen = new JLabel("Lados de imagen:");
	JLabel txt_lados_lote = new JLabel((String) null);
	JLabel txt_totalimg_lote = new JLabel((String) null);
	
	JPanel btn_imginvalidas = new JPanel();
	JPanel btn_imgvalidas = new JPanel();
	
//	private String directoryPath=null;
	Methods met = new Methods();
	ConfiguracionVO conf;
	LoteVO lote;
	ImagenVO imagen_temp;
	ImagenValidacionVO imgvalidacion_temp;
	public int countimages=0;
	
	LotesRestClientService lotesRestclientservice;
	BitacoraRestClientService bitacoraRestClientService;

	List<String> imagenesTodas = new ArrayList<String>();
	List<String> images4delete = new ArrayList<String>();
	List<ImagenVO> imagenesValidas = new ArrayList<ImagenVO>();
	List<ImagenVO> imagenesInvalidas = new ArrayList<ImagenVO>();
	List<String> extensionesvalidas = new ArrayList<String>();
	
	List<String> nones;
    List<String> pares;
	
	public static String currentImage="";
	public static int currentImgPos;
	JLabel txt_imagen = new JLabel("");
	BufferedImage myPicture;
	public static String current_codigovalidacion;
	public static int currentImgStatus;
	
	int rbSelection = 0;
	
	JPanel btn_rotar = new JPanel();
	
	UsuarioVO usuario = new UsuarioVO();
	
//	public static int current_status_imagen;
	
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ValidacionFrame frame = new ValidacionFrame();
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

	public ValidacionFrame(final LoteVO lote, final ConfiguracionVO conf, final UsuarioVO usuario) {
		System.out.println("estas en validacion");
		this.lote = lote;
		this.conf = conf;
		this.usuario=usuario;
		
		extensionesvalidas.add(".TIF");
		extensionesvalidas.add(".TIFF");
		extensionesvalidas.add(".tif");
		extensionesvalidas.add(".tifF");
		extensionesvalidas.add(".JPG");
		extensionesvalidas.add(".JPEG");
		extensionesvalidas.add(".jpg");
		extensionesvalidas.add(".jpeg");
		extensionesvalidas.add(".png");
		extensionesvalidas.add(".PNG");
					
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(-6, 0, 1381, 734);
		this.setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0,0,0,0));
		setContentPane(contentPane);
		
		JPanel panel_contenedorMain = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_contenedorMain, GroupLayout.PREFERRED_SIZE, 1396, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_contenedorMain, GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
		);
		
		panel_contenedorMain.setBackground(new java.awt.Color(236, 240, 245));
		
		JPanel panel_header = new JPanel();
		panel_header.setBounds(0, 0, 1381, 54);
		panel_header.setLayout(null);
		panel_header.setBackground(Color.WHITE);
		panel_header.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		
		JLabel label = new JLabel("");
		label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logoApp.png")));
		label.setBounds(117, 0, 133, 44);
		panel_header.add(label);
		
		JPanel btn_closeWindow = new JPanel();
		btn_closeWindow.setToolTipText("Cerrar ventana");
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
		btn_closeWindow.setBounds(1302, 1, 51, 43);
		btn_closeWindow.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panel_header.add(btn_closeWindow);
		
		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "Estas seguro de que quieres salir de la aplicación");
				if (confirm == 0) {
		            System.exit(1);
		        }
			}
		});
		label_1.setToolTipText("Cerrar ventana");
		label_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Cancel_50px.png")));
		label_1.setBounds(0, 0, 51, 43);
		btn_closeWindow.add(label_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(30, 0, 57, 43);
		panel_header.add(panel_3);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Menu_35px.png")));
		label_2.setBounds(10, 0, 35, 44);
		panel_3.add(label_2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(360, 0, 703, 44);
		panel_header.add(panel_4);
		
		JLabel lblAplicacinDeDigitalizacin = new JLabel("Aplicación de Digitalización y Procesamiento de Imágenes");
		lblAplicacinDeDigitalizacin.setHorizontalAlignment(SwingConstants.CENTER);
		lblAplicacinDeDigitalizacin.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		lblAplicacinDeDigitalizacin.setBounds(10, 0, 683, 44);
		panel_4.add(lblAplicacinDeDigitalizacin);
		panel_contenedorMain.setLayout(null);
		
		JPanel panel_footer = new JPanel();
		panel_footer.setLayout(null);
		panel_footer.setBackground(new Color(211, 26, 43));
		panel_footer.setBounds(0, 681, 1381, 53);
		panel_contenedorMain.add(panel_footer);
		
		JLabel lblVersin = new JLabel("Versión 2.3.0");
		lblVersin.setForeground(Color.WHITE);
		lblVersin.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblVersin.setBounds(1230, 0, 151, 53);
		panel_footer.add(lblVersin);
		
		JLabel label_3 = new JLabel("Derechos de autor © 2018 Teclo Mexicana. Todos los derechos reservados.");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_3.setBounds(132, 0, 471, 53);
		panel_footer.add(label_3);
		
		JPanel panel_menu = new JPanel();
		panel_menu.setBounds(0, 53, 122, 631);
		panel_menu.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_menu.setBackground(Color.WHITE);
		panel_menu.setLayout(null);
		
		JPanel btn_menuConsultaLotes = new JPanel();
		btn_menuConsultaLotes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "Estas seguro de que quieres salir de la aplicación");
				if (confirm == JOptionPane.YES_OPTION) {
					changeToConsultaLotes();
		        }
				
			}
		});
		btn_menuConsultaLotes.setBounds(0, 0, 122, 75);
		btn_menuConsultaLotes.setLayout(null);
		btn_menuConsultaLotes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btn_menuConsultaLotes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btn_menuConsultaLotes.setBackground(SystemColor.menu);
		panel_menu.add(btn_menuConsultaLotes);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Search_36px.png")));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(37, 11, 46, 36);
		btn_menuConsultaLotes.add(label_4);
		
		JLabel label_5 = new JLabel("Consulta de Lotes");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_5.setBounds(0, 47, 122, 14);
		btn_menuConsultaLotes.add(label_5);
		
		JPanel btn_menuProcesamiento = new JPanel();
		btn_menuProcesamiento.setBounds(0, 74, 122, 75);
		btn_menuProcesamiento.setLayout(null);
		btn_menuProcesamiento.setBackground(new Color(211, 26, 43));
		btn_menuProcesamiento.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btn_menuProcesamiento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		panel_menu.add(btn_menuProcesamiento);
		
		JLabel label_6 = new JLabel("");
		label_6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Gears_36px.png")));
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(37, 11, 46, 36);
		btn_menuProcesamiento.add(label_6);
		
		JLabel lblWizard = new JLabel("Lote");
		lblWizard.setHorizontalAlignment(SwingConstants.CENTER);
		lblWizard.setForeground(Color.WHITE);
		lblWizard.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWizard.setBounds(0, 50, 122, 25);
		btn_menuProcesamiento.add(lblWizard);
		panel_contenedorMain.add(panel_menu);
		panel_contenedorMain.add(panel_header);
		
		JPanel btn_minimize = new JPanel();
		btn_minimize.setToolTipText("Minimizar ventana");
		btn_minimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btn_minimize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				minimizeWin();
			}
		});
		btn_minimize.setLayout(null);
		btn_minimize.setBackground(Color.WHITE);
		btn_minimize.setBounds(1254, 0, 51, 44);
		panel_header.add(btn_minimize);
		
		JLabel label_20 = new JLabel("");
		label_20.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				minimizeWin();
			}
		});
		label_20.setToolTipText("Minimizar ventana");
		label_20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Minus_50px.png")));
		label_20.setBounds(0, 0, 50, 44);
		btn_minimize.add(label_20);
		
		JPanel panel_contenedor = new JPanel();
		panel_contenedor.setBackground(Color.WHITE);
		panel_contenedor.setBounds(132, 127, 1218, 543);
		panel_contenedor.setBorder(new LineBorder(Color.GRAY));
		panel_contenedorMain.add(panel_contenedor);
		panel_contenedor.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.GRAY));
		panel.setBounds(26, 22, 286, 441);
		panel_contenedor.add(panel);
		panel.setLayout(null);
		txt_total.setBounds(52, 378, 60, 25);
		txt_total.setText("");
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.WHITE);
		panel_7.setBorder(new LineBorder(Color.GRAY));
		panel_7.setBounds(10, 214, 266, 216);
		panel.add(panel_7);
		panel_7.setLayout(null);
		panel_check_compresion.setBackground(new Color(230, 230, 250));
		panel_check_compresion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panel_check_compresion.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_check_compresion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cleanModel();	
				panel_listaimages.setVisible(false);
				current_codigovalidacion =ConfigEnumStrings.codigo_formatocompresion.getConstante();
				
//				lbl_txt_btn_invalidas.setText("Inválidas");
//				lbl_txt_btn_validas.setText("Válidas");
				icon_x.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Delete_22px.png")));
				label_12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Checkmark_22px.png")));
				
//				pnl_btns_valid_invalid.setVisible(true);
				txt_title_img.setText("Formato de compresión");
				txt_total.setText("");
				lbl_tipvalidacion.setText("Validación de formato de compresión:");
				txt_imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LogotipoTeclo.png")));
				lbl_currentimagen.setText("");
				setearColorNormal();
				setDataInvalidas();
				btn_cambiarformato.setVisible(true);
				panel_check_compresion.setBackground(new Color(255, 102, 102));
				
				btn_imginvalidas.setVisible(true);
				btn_imgvalidas.setVisible(true);
			}
		});
		
		
		panel_check_compresion.setBounds(10, 133, 246, 34);
		panel_7.add(panel_check_compresion);
		panel_check_compresion.setLayout(null);
		
		JLabel lblFormatoDeCompresin = new JLabel("4. Formato de compresión");
		lblFormatoDeCompresin.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFormatoDeCompresin.setBounds(10, 0, 177, 39);
		panel_check_compresion.add(lblFormatoDeCompresin);
		
		
		icon_formatocompresion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Checked_Checkbox_35px_1.png")));
		icon_formatocompresion.setBounds(201, 0, 35, 34);
		panel_check_compresion.add(icon_formatocompresion);
		
		panel_check_compresion.setVisible(false);
		panel_check_nomenclatura.setBackground(new Color(230, 230, 250));
		panel_check_nomenclatura.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cleanModel();	
				panel_listaimages.setVisible(false);
				current_codigovalidacion =ConfigEnumStrings.codigo_nomenclatura.getConstante();
				
//				lbl_txt_btn_invalidas.setText("Inválidas");
//				lbl_txt_btn_validas.setText("Válidas");
				icon_x.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Delete_22px.png")));
				label_12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Checkmark_22px.png")));
				
//				pnl_btns_valid_invalid.setVisible(true);
				txt_title_img.setText("Nomenclatura");
				txt_total.setText("");
				lbl_tipvalidacion.setText("Validación de nomenclatura:");
				setearColorNormal();
				setDataInvalidas();
				btn_renombrarvalidar.setVisible(true);
				panel_check_nomenclatura.setBackground(new Color(255, 102, 102));
				
				btn_imginvalidas.setVisible(true);
				btn_imgvalidas.setVisible(true);
			}
		});
		panel_check_nomenclatura.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		changeIcon();
		panel_check_nomenclatura.setBounds(10, 91, 246, 34);
		panel_7.add(panel_check_nomenclatura);
		panel_check_nomenclatura.setLayout(null);
		
		JLabel lblNomenclaturaDeImgen = new JLabel("3. Nomenclatura de imágen");
		lblNomenclaturaDeImgen.setBounds(10, 0, 181, 39);
		panel_check_nomenclatura.add(lblNomenclaturaDeImgen);
		lblNomenclaturaDeImgen.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		
		icon_nomenclatura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Checked_Checkbox_35px_1.png")));
		icon_nomenclatura.setBounds(201, 0, 35, 34);
		panel_check_nomenclatura.add(icon_nomenclatura);
		panel_check_nomenclatura.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panel_check_posiblesinconsistencias.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panel_check_posiblesinconsistencias.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cleanModel();	
				panel_listaimages.setVisible(false);
				current_codigovalidacion =ConfigEnumStrings.codigo_inconsistencia.getConstante();
				
//				lbl_txt_btn_invalidas.setText("Inválidas");
//				lbl_txt_btn_validas.setText("Válidas");
				icon_x.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Delete_22px.png")));
				label_12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Checkmark_22px.png")));
				
//				pnl_btns_valid_invalid.setVisible(true);
				txt_title_img.setText("Características descriptivas");
				txt_total.setText("");
				lbl_tipvalidacion.setText("Validación de características:");
				txt_imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LogotipoTeclo.png")));
				lbl_currentimagen.setText("");
				setearColorNormal();
				panel_check_posiblesinconsistencias.setBackground(new Color(255, 102, 102));
				
				btn_imginvalidas.setVisible(true);
				btn_imgvalidas.setVisible(true);
				
				setDataInvalidas();
			}
		});
		panel_check_posiblesinconsistencias.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		
		
		panel_check_posiblesinconsistencias.setBounds(10, 11, 246, 34);
		panel_7.add(panel_check_posiblesinconsistencias);
		panel_check_posiblesinconsistencias.setLayout(null);
		
		JLabel lblDimensionesDeImgen = new JLabel("1. Características descriptivas");
		lblDimensionesDeImgen.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDimensionesDeImgen.setBounds(10, 0, 195, 39);
		panel_check_posiblesinconsistencias.add(lblDimensionesDeImgen);
		
		
		icon_posiblesinconsistencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Checked_Checkbox_35px_1.png")));
		icon_posiblesinconsistencias.setBounds(201, 0, 35, 34);
		panel_check_posiblesinconsistencias.add(icon_posiblesinconsistencias);
		panel_check_asociacion.setBackground(new Color(230, 230, 250));
		panel_check_asociacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panel_check_asociacion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cleanModel();	
				panel_listaimages.setVisible(false);
				current_codigovalidacion =ConfigEnumStrings.codigo_asociacionlados.getConstante();
//				lbl_txt_btn_invalidas.setText("Anverso");
//				lbl_txt_btn_validas.setText("Reverso");
				
				icon_x.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Checkmark_22px.png")));
				label_12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Checkmark_22px.png")));
				
//				pnl_btns_valid_invalid.setVisible(true);
				txt_title_img.setText("Asociacion de lados");
				txt_total.setText("");
				lbl_tipvalidacion.setText("Validación de asociación de lados:");
				
				txt_imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LogotipoTeclo.png")));
				lbl_currentimagen.setText("");
				setearColorNormal();
				panel_check_asociacion.setBackground(new Color(255, 102, 102));
				
				btn_imginvalidas.setVisible(true);
				btn_imgvalidas.setVisible(true);
				
			}
		});
		panel_check_asociacion.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		
		
		panel_check_asociacion.setBounds(10, 173, 246, 34);
		panel_7.add(panel_check_asociacion);
		panel_check_asociacion.setLayout(null);
		
		JLabel lblAsociacinDeLatos = new JLabel("5. Asociación de lados");
		lblAsociacinDeLatos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAsociacinDeLatos.setBounds(10, 0, 181, 34);
		panel_check_asociacion.add(lblAsociacinDeLatos);
		
		
		icon_asociacionlados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Checked_Checkbox_35px_1.png")));
		icon_asociacionlados.setBounds(201, 0, 35, 34);
		panel_check_asociacion.add(icon_asociacionlados);
		panel_check_archivodaniado.setBackground(new Color(230, 230, 250));
		panel_check_archivodaniado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panel_check_archivodaniado.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_check_archivodaniado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cleanModel();	
				panel_listaimages.setVisible(false);
				current_codigovalidacion =ConfigEnumStrings.codigo_archivodaniado.getConstante();
				
//				lbl_txt_btn_invalidas.setText("Inválidas");
//				lbl_txt_btn_validas.setText("Válidas");
				icon_x.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Delete_22px.png")));
				label_12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Checkmark_22px.png")));
				
//				pnl_btns_valid_invalid.setVisible(true);
				txt_title_img.setText("Archivo dañado");
				txt_total.setText("");
				lbl_tipvalidacion.setText("Validación de archivo dañado:");
				txt_imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LogotipoTeclo.png")));
				lbl_currentimagen.setText("");
				setearColorNormal();
				setDataInvalidas();
				panel_check_archivodaniado.setBackground(new Color(255, 102, 102));
				
				btn_imginvalidas.setVisible(true);
				btn_imgvalidas.setVisible(true);
			}
		});
		
		
		panel_check_archivodaniado.setBounds(10, 51, 246, 34);
		panel_7.add(panel_check_archivodaniado);
		panel_check_archivodaniado.setLayout(null);
		
		JLabel lblArchivosDaados = new JLabel("2. Archivos dañados");
		lblArchivosDaados.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblArchivosDaados.setBounds(10, 0, 181, 34);
		panel_check_archivodaniado.add(lblArchivosDaados);
		
		
		icon_archivodaniado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Checked_Checkbox_35px_1.png")));
		icon_archivodaniado.setBounds(201, 0, 35, 34);
		panel_check_archivodaniado.add(icon_archivodaniado);
		panel_check_asociacion.setVisible(false);
		panel_check_posiblesinconsistencias.setVisible(false);
		panel_check_nomenclatura.setVisible(false);
		panel_check_archivodaniado.setVisible(false);
		
		JLabel lblV = new JLabel("VALIDACIONES REALIZADAS:");
		lblV.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblV.setHorizontalAlignment(SwingConstants.CENTER);
		lblV.setBounds(63, 186, 165, 28);
		panel.add(lblV);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		panel_13.setBounds(10, 11, 266, 123);
		panel.add(panel_13);
		panel_13.setLayout(null);
		
		JLabel txt_nblote = new JLabel("...");
		
		txt_nblote.setBounds(133, -7, 71, 30);
		panel_13.add(txt_nblote);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.GRAY));
		panel_1.setBounds(545, 22, 663, 29);
		panel_contenedor.add(panel_1);
		panel_1.setLayout(null);
		
		
		lbl_tipvalidacion.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_tipvalidacion.setForeground(new Color(47, 79, 79));
		lbl_tipvalidacion.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_tipvalidacion.setBounds(10, 0, 314, 29);
		panel_1.add(lbl_tipvalidacion);
		
		
		lbl_currentimagen.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_currentimagen.setForeground(new Color(178, 34, 34));
		lbl_currentimagen.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_currentimagen.setBounds(344, 0, 309, 29);
		panel_1.add(lbl_currentimagen);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(322, 60, 213, 403);
		panel_contenedor.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(10, 378, 46, 25);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_2.add(lblTotal);
		
		
		txt_total.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_2.add(txt_total);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(Color.GRAY));
		panel_5.setBounds(26, 474, 509, 58);
		panel_contenedor.add(panel_5);
		panel_5.setLayout(null);
		
		
		btn_renombrarvalidar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				iniValidaciones(true);
//				iniValidaciones(false);
			}
		});
		btn_renombrarvalidar.setLayout(null);
		btn_renombrarvalidar.setBackground(new Color(211, 26, 43));
		btn_renombrarvalidar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btn_renombrarvalidar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btn_renombrarvalidar.setBounds(177, 11, 136, 36);
		panel_5.add(btn_renombrarvalidar);
		
		JLabel lblProcesar = new JLabel("Renombrar");
		lblProcesar.setForeground(Color.WHITE);
		lblProcesar.setHorizontalAlignment(SwingConstants.LEFT);
		lblProcesar.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblProcesar.setBounds(39, 0, 99, 36);
		btn_renombrarvalidar.add(lblProcesar);
		
		JLabel label_15 = new JLabel("");
		label_15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Process_25px_1.png")));
		label_15.setBounds(10, 0, 30, 36);
		btn_renombrarvalidar.add(label_15);
		btn_cambiarformato.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		btn_cambiarformato.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cambiarFormatoArchivoImagenes();
			}
		});
		btn_cambiarformato.setLayout(null);
		btn_cambiarformato.setBackground(new Color(211, 26, 43));
		btn_cambiarformato.setBounds(323, 11, 176, 36);
		panel_5.add(btn_cambiarformato);
		
		JLabel lblCambiarFormatoY = new JLabel("Cambiar formato");
		lblCambiarFormatoY.setHorizontalAlignment(SwingConstants.LEFT);
		lblCambiarFormatoY.setForeground(Color.WHITE);
		lblCambiarFormatoY.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCambiarFormatoY.setBounds(39, 0, 141, 36);
		btn_cambiarformato.add(lblCambiarFormatoY);
		
		JLabel label_10 = new JLabel("");
		label_10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Process_25px_1.png")));
		label_10.setBounds(10, 0, 30, 36);
		btn_cambiarformato.add(label_10);
		
		JPanel btn_validar = new JPanel();
		btn_validar.setBounds(10, 11, 136, 36);
		panel_5.add(btn_validar);
		btn_validar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				iniValidaciones(false);
			}
		});
		btn_validar.setBackground(SystemColor.inactiveCaptionBorder);
		btn_validar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btn_validar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btn_validar.setLayout(null);
		
		JLabel lblRevalidar = new JLabel("Revalidar");
		lblRevalidar.setHorizontalAlignment(SwingConstants.LEFT);
		lblRevalidar.setBounds(40, 0, 86, 36);
		lblRevalidar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn_validar.add(lblRevalidar);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(Color.GRAY));
		panel_6.setBounds(545, 60, 663, 472);
		panel_contenedor.add(panel_6);
		panel_6.setLayout(null);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_12.setBackground(Color.WHITE);
		panel_12.setBounds(10, 11, 643, 404);
		panel_6.add(panel_12);
		panel_12.setLayout(null);
		
		JLabel label_14 = new JLabel("");
		label_14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Reboot_25px.png")));
		label_14.setBounds(10, 0, 23, 36);
		btn_validar.add(label_14);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 0, 643, 404);
		panel_12.add(scrollPane_2);
		scrollPane_2.setViewportView(txt_imagen);
		
		
		txt_imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LogotipoTeclo.png")));
		btn_delete.setToolTipText("Borrar imagen");
		btn_delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "Estas seguro de que quieres eliminar la imágen");
				if (confirm == 0) {
					
					if(met.deleteFile(lote.getRutaAlmacenamiento(), currentImage)) {						
						txt_imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LogotipoTeclo.png")));
						for(int i=0;i<imagenesInvalidas.size();i++) {
							if(imagenesInvalidas.get(i).getNombre().equals(currentImage)) {
								imagenesInvalidas.remove(imagenesInvalidas.get(i));
							}
						}
						for(int i=0;i<imagenesValidas.size();i++) {
							if(imagenesValidas.get(i).getNombre().equals(currentImage)) {
								imagenesValidas.remove(imagenesValidas.get(i));
							}
						}
						setLista(currentImgStatus);
						showImageOptions(false);
						botonesVisibles(false);
						iniValidaciones(false);
					}else {
						JOptionPane.showMessageDialog(null, "Ocurrio un error al eliminar el archivo, intentelo más tarde");
					}
		        }
				lbl_tipvalidacion.setVisible(false);
				lbl_currentimagen.setVisible(false);
			}
		});
		
		
		btn_delete.setBounds(56, 426, 36, 38);
		panel_6.add(btn_delete);
		btn_delete.setLayout(null);
		
		
		label_17.setBounds(0, 0, 36, 36);
		btn_delete.add(label_17);
		btn_delete.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btn_delete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		label_17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Trash_Can_36px.png")));
		btn_reemplazarimg.setToolTipText("Reemplazar imagen");
		btn_reemplazarimg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 
				 ReemplazarImagenFrame replace = new ReemplazarImagenFrame(conf, lote, currentImage); 
				 replace.setVisible(true); txt_imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LogotipoTeclo.png")));
				 
			}
		});
		
		
		btn_reemplazarimg.setBounds(102, 426, 36, 38);
		panel_6.add(btn_reemplazarimg);
		btn_reemplazarimg.setLayout(null);
		
		JLabel label_18 = new JLabel("");
		label_18.setBounds(0, 0, 36, 38);
		
		btn_reemplazarimg.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btn_reemplazarimg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		//label_18.setIcon(new ImageIcon("C:\\Pablo\\pgm\\eclipse\\digitalizacion-procesamiento-build_v230r1\\digitalizacion-procesamiento-build_v230r1\\digitalizacion-procesamiento-build_v220r1\\digitalizacion-app\\src\\main\\resources\\images\\icons8_Add_Image_36px.png"));
		label_18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Add_Image_36px.png")));
		btn_reemplazarimg.add(label_18);
		
		
		btn_rotar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					BufferedImage immaa=met.rotate180(myPicture);
					myPicture=immaa;
					txt_imagen.setIcon(new javax.swing.ImageIcon(met.ScaleImage(immaa,txt_imagen.getWidth(),txt_imagen.getHeight())));
					File outputfile = new File(lote.getRutaAlmacenamiento()+currentImage);					
					ImageIO.write(immaa, "jpg", outputfile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_rotar.setBounds(10, 426, 36, 38);
		btn_rotar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btn_rotar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panel_6.add(btn_rotar);
		btn_rotar.setLayout(null);
		
		JLabel label_16 = new JLabel("");
		label_16.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					BufferedImage immaa=met.rotate180(myPicture);
					myPicture=immaa;
					txt_imagen.setIcon(new javax.swing.ImageIcon(met.ScaleImage(immaa,txt_imagen.getWidth(),txt_imagen.getHeight())));
					File outputfile = new File(lote.getRutaAlmacenamiento()+currentImage);					
					ImageIO.write(immaa, "jpg", outputfile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		label_16.setToolTipText("Rotar imagen 180°");
		label_16.setBounds(0, 0, 36, 38);
		btn_rotar.add(label_16);
		label_16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Synchronize_36px.png")));
		btn_procesarimg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("ambos lados lote, suministro: "+lote.getConfiguracionEscaner().getSuministroPapel().getValor());
				
				if(allright) {
					if(lote.getConfiguracionEscaner().getSuministroPapel().getValor()==ConfiguracionEnum.ladoambos.getConstante()) {
						
						List<String> images = getAllImages();
						System.out.println("total de imagenes:: "+images.size());
						if ( (images.size()%2)!=0) {
							JOptionPane.showMessageDialog(null,"Atención!. No existe paridad en el número de imágenes de anversos y reversos");
						}else {
							int confirm = JOptionPane.showConfirmDialog(null, "Estas seguro de que desea procesar las imágenes");
							if (confirm == 0) {
								
								BitacoraVO bitacoraVO = new BitacoraVO();
								
								bitacoraVO.setTabla(ParametrosBitacoraEnum.TDP_BITACORA_CAMBIOS.getParametro());
								bitacoraVO.setComponenteId(2L);
								bitacoraVO.setConceptoId(2L);
								
								
								String valorOriginal="LOTE EN VALIDACIÓN";
								
								bitacoraVO.setValorOriginal(valorOriginal);
								bitacoraVO.setValorFinal("LOTE EN PROCESAMIENTO");
								Long valorId = usuario.getUsuarioId() == null ? 2 : usuario.getUsuarioId();
								bitacoraVO.setModificadoPor(valorId);
								bitacoraVO.setIdRegistro(lote.getIdLote()+""); 
								bitacoraVO.setRegistroDescripcion(lote.getNombreLote());
								bitacoraVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
								
								bitacoraVO.setNuFolioInicial(lote.getNuFolioInicial());
								bitacoraVO.setNuFolioFinal(lote.getNuFolioFinal());
								bitacoraVO.setNombreLote(lote.getNombreLote());
								
								if(updateLote(3L,bitacoraVO)){
									
									changeToProcesarLote(lote);
								}
								
					        }
						}
				    }else {
				    	System.out.println("una sola cara lote");
				    	int confirm = JOptionPane.showConfirmDialog(null, "Estas seguro de que desea procesar las imágenes");
						if (confirm == 0) {
							
							BitacoraVO bitacoraVO = new BitacoraVO();
							
							bitacoraVO.setTabla(ParametrosBitacoraEnum.TDP_BITACORA_CAMBIOS.getParametro());
							bitacoraVO.setComponenteId(2L);
							bitacoraVO.setConceptoId(2L);
							
							
							String valorOriginal="LOTE EN VALIDACIÓN";
							
							bitacoraVO.setValorOriginal(valorOriginal);
							bitacoraVO.setValorFinal("LOTE EN PROCESAMIENTO");
							Long valorId = usuario.getUsuarioId() == null ? 2 : usuario.getUsuarioId();
							bitacoraVO.setModificadoPor(valorId);
							bitacoraVO.setIdRegistro(lote.getIdLote()+"");
							bitacoraVO.setRegistroDescripcion(lote.getNombreLote());
							bitacoraVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
							
							if(updateLote(3L, bitacoraVO)){
								
								changeToProcesarLote(lote);
//								bitacoraRestClientService = new BitacoraRestClientService();
//								boolean result=bitacoraRestClientService.guardarBitacora(bitacoraVO, conf);
							}
							
				        }
				    }
				}else {
					JOptionPane.showMessageDialog(null, "Debes completar todas las validaciones antes de pasar al paso de procesamiento");
				}
				
				
				
			}
		});
		
		
		btn_procesarimg.setLayout(null);
		btn_procesarimg.setBackground(new Color(211, 26, 43));
		btn_procesarimg.setBounds(458, 426, 195, 36);
		btn_procesarimg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panel_6.add(btn_procesarimg);
		
		JLabel lblProcesarImgenes = new JLabel("Procesar imágenes");
		lblProcesarImgenes.setHorizontalAlignment(SwingConstants.LEFT);
		lblProcesarImgenes.setForeground(Color.WHITE);
		lblProcesarImgenes.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblProcesarImgenes.setBounds(39, 0, 142, 36);
		btn_procesarimg.add(lblProcesarImgenes);
		
		JLabel label_11 = new JLabel("");
		label_11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Play_25px.png")));
		label_11.setBounds(10, 0, 30, 36);
		btn_procesarimg.add(label_11);
		
		JPanel btn_back = new JPanel();
		btn_back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeToReanudarLote(lote, usuario);
			}
		});
		btn_back.setBounds(412, 426, 36, 38);
		panel_6.add(btn_back);
		btn_back.setLayout(null);
		btn_back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btn_back.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btn_back.setToolTipText("Retornar a modificar lote actual");
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 36, 38);
		btn_back.add(lblNewLabel);
		lblNewLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Backicons8_Play_25px_2.png")));
		
		JPanel panel_wizard = new JPanel();
		panel_wizard.setLayout(null);
		panel_wizard.setBounds(395, 53, 606, 74);
		panel_wizard.setBackground(new java.awt.Color(236, 240, 245));
		panel_contenedorMain.add(panel_wizard);
		
		JLabel label_9 = new JLabel("");
		label_9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/wizard2.png")));
		label_9.setBounds(43, 0, 524, 74);
		panel_wizard.add(label_9);
		contentPane.setLayout(gl_contentPane);
		panel_listaimages.setBounds(0, 39, 213, 328);
		panel_listaimages.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.add(panel_listaimages);
		panel_listaimages.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 213, 328);
		panel_listaimages.add(scrollPane_1);
		list.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int index=0;
				if(e.getKeyCode()==KeyEvent.VK_DOWN) {
					index = list.getSelectedIndex()+1;
					
					if (index >= 0) {
						Object o = list.getModel().getElementAt(index);
						String[] parts = o.toString().split("--");
						currentImage=parts[0];
						currentImgPos=index;
						setImageFile(lote.getRutaAlmacenamiento(), currentImage);
						showImageOptions(true);
						lbl_currentimagen.setText(currentImage);
					}
				}
				if(e.getKeyCode()==KeyEvent.VK_UP) {
					index = list.getSelectedIndex()-1;
					
					if (index >= 0) {
						Object o = list.getModel().getElementAt(index);
						String[] parts = o.toString().split("--");
						currentImage=parts[0];
						currentImgPos=index;
						setImageFile(lote.getRutaAlmacenamiento(), currentImage);
						showImageOptions(true);
						lbl_currentimagen.setText(currentImage);
					}
				}				
					
				
				if(e.getKeyCode()==KeyEvent.VK_DELETE) {
					int confirm = JOptionPane.showConfirmDialog(null, "Estas seguro de que quieres eliminar la imágen");
					if (confirm == 0) {
						
						if(met.deleteFile(lote.getRutaAlmacenamiento(), currentImage)) {						
							txt_imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LogotipoTeclo.png")));
							for(int i=0;i<imagenesInvalidas.size();i++) {
								if(imagenesInvalidas.get(i).getNombre().equals(currentImage)) {
									imagenesInvalidas.remove(imagenesInvalidas.get(i));
								}
							}
							for(int i=0;i<imagenesValidas.size();i++) {
								if(imagenesValidas.get(i).getNombre().equals(currentImage)) {
									imagenesValidas.remove(imagenesValidas.get(i));
								}
							}
							setLista(currentImgStatus);
							showImageOptions(false);
						}else {
							JOptionPane.showMessageDialog(null, "Ocurrio un error al eliminar el archivo, intentelo más tarde");
						}
			        }
				}
			}
		});
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JList<?> theList = (JList<?>) e.getSource();
				if (e.getClickCount() == 1) {
					lbl_tipvalidacion.setVisible(true);
					lbl_currentimagen.setVisible(true);
					int index = theList.locationToIndex(e.getPoint());
					if (index >= 0) {
						botonesVisibles(true);
						Object o = theList.getModel().getElementAt(index);
						String[] parts = o.toString().split("--");
						currentImage=parts[0];
						currentImgPos=index;
						setImageFile(lote.getRutaAlmacenamiento(), currentImage);
						showImageOptions(true);
						lbl_currentimagen.setText(currentImage);
					}
				}
			}
		});
		
		
		scrollPane_1.setViewportView(list);
		
		list.setModel(dlm);
		btn_imginvalidas.setToolTipText("Imágenes inválidas");
		
		
		btn_imginvalidas.setBounds(139, 375, 27, 25);
		panel_2.add(btn_imginvalidas);
		btn_imginvalidas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setDataInvalidas();
				
				
				
			}
		});
		btn_imginvalidas.setBackground(SystemColor.inactiveCaptionBorder);
		btn_imginvalidas.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btn_imginvalidas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btn_imginvalidas.setLayout(null);
		icon_x.setBounds(0, 0, 27, 25);
		icon_x.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		icon_x.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Delete_22px.png")));
		btn_imginvalidas.add(icon_x);
		btn_imgvalidas.setToolTipText("Imágenes válidas");
		
		
		btn_imgvalidas.setBounds(176, 375, 27, 25);
		panel_2.add(btn_imgvalidas);
		btn_imgvalidas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(current_codigovalidacion.equals(ConfigEnumStrings.codigo_asociacionlados.getConstante())) {
					panel_listaimages.setVisible(true);
					currentImgStatus=ConfiguracionEnum.imagenPares.getConstante();
					setLista(ConfiguracionEnum.imagenPares.getConstante());
					txt_imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LogotipoTeclo.png")));
					lbl_currentimagen.setText("");
					
				}else {
					panel_listaimages.setVisible(true);
					currentImgStatus=ConfiguracionEnum.imagenValida.getConstante();
					setLista(ConfiguracionEnum.imagenValida.getConstante());
					txt_imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LogotipoTeclo.png")));
					lbl_currentimagen.setText("");
				}
				
				
				
			}
		});
		btn_imgvalidas.setBackground(SystemColor.inactiveCaptionBorder);
		btn_imgvalidas.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btn_imgvalidas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btn_imgvalidas.setLayout(null);
		
		
		label_12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Checkmark_22px.png")));
		label_12.setBounds(0, 0, 27, 25);
		btn_imgvalidas.add(label_12);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_10.setBounds(0, 0, 213, 40);
		panel_2.add(panel_10);
		panel_10.setBackground(new Color(211, 26, 43));
		panel_10.setLayout(null);
		
		
		txt_titleimages.setForeground(new Color(255, 255, 224));
		txt_titleimages.setFont(new Font("Tahoma", Font.BOLD, 15));
		txt_titleimages.setHorizontalAlignment(SwingConstants.CENTER);
		txt_titleimages.setBounds(0, 0, 213, 40);
		panel_10.add(txt_titleimages);
		
//		pnl_btns_valid_invalid.setVisible(false);
//		panel_listaimages.setVisible(false);
		
		//Metodos al inicar la aplicacion
		txt_nblote.setText(lote.getNombreLote());
		txt_nblote.setToolTipText(lote.getNombreLote());
		String metdig="";
		if(lote.getMetDigitalizacion()==ConfiguracionEnum.metDigitalizacionDirecta.getConstante()) { 
			metdig="Directa";
		}
		if(lote.getMetDigitalizacion()==ConfiguracionEnum.metDigitalizacionExterna.getConstante()) {
			metdig="Externa";
		}
		iniValidaciones(false);
		
		txt_metodo_lote.setText(metdig);
		txt_fh_lote.setText(lote.getFechaCracionLote());
		txt_lados_lote.setText(lote.getConfiguracionEscaner().getSuministroPapel().getNombre());
		
		btn_imginvalidas.setVisible(false);
		btn_imgvalidas.setVisible(false);
		
		JLabel label_7 = new JLabel("Nombre del Lote:");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_7.setBounds(10, 0, 127, 15);
		panel_13.add(label_7);
		
		JLabel label_13 = new JLabel("Método:");
		label_13.setHorizontalAlignment(SwingConstants.RIGHT);
		label_13.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_13.setBounds(9, 19, 103, 25);
		panel_13.add(label_13);
		
		JLabel label_19 = new JLabel("Fecha del Lote:");
		label_19.setHorizontalAlignment(SwingConstants.RIGHT);
		label_19.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_19.setBounds(0, 42, 112, 28);
		panel_13.add(label_19);
		
		
		txt_metodo_lote.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_metodo_lote.setBounds(129, 19, 127, 25);
		panel_13.add(txt_metodo_lote);
		
		txt_fh_lote.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_fh_lote.setBounds(127, 45, 142, 25);
		panel_13.add(txt_fh_lote);
		
		lblLadosImagen.setBounds(0, 70, 112, 28);
		panel_13.add(lblLadosImagen);
		lblLadosImagen.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLadosImagen.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		
		txt_lados_lote.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_lados_lote.setBounds(127, 70, 142, 25);
		panel_13.add(txt_lados_lote);
		
		JLabel lblTotalDeImgenes = new JLabel("Total de imágenes");
		lblTotalDeImgenes.setBounds(0, 95, 112, 28);
		panel_13.add(lblTotalDeImgenes);
		lblTotalDeImgenes.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalDeImgenes.setFont(new Font("Tahoma", Font.BOLD, 12));
		txt_totalimg_lote.setBounds(133, 97, 69, 25);
		panel_13.add(txt_totalimg_lote);
		txt_totalimg_lote.setText(countNumberOfImages()+"");
		
		
		txt_totalimg_lote.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JPanel panel_8 = new JPanel();
		panel_8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panel_8.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				setListaValidasTodas();
			}
		});
		panel_8.setBounds(44, 145, 204, 30);
		panel.add(panel_8);
		panel_8.setBackground(new Color(211, 26, 43));
		panel_8.setLayout(null);
		
		JLabel lblVerTodasLas = new JLabel("Ver todas las imágenes válidas");
		lblVerTodasLas.setForeground(new Color(250, 235, 215));
		lblVerTodasLas.setHorizontalAlignment(SwingConstants.CENTER);
		lblVerTodasLas.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblVerTodasLas.setBounds(0, 0, 204, 30);
		panel_8.add(lblVerTodasLas);
		
		JPanel btn_solovalidas = new JPanel();
		btn_solovalidas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		btn_solovalidas.setBorder(new LineBorder(new Color(0, 0, 0)));
		btn_solovalidas.setBounds(322, 22, 213, 29);
		panel_contenedor.add(btn_solovalidas);
		btn_solovalidas.setLayout(null);
		txt_title_img.setForeground(new Color(0, 0, 0));
		txt_title_img.setBounds(10, 0, 193, 29);
		btn_solovalidas.add(txt_title_img);
		
		
		txt_title_img.setHorizontalAlignment(SwingConstants.CENTER);
		txt_title_img.setFont(new Font("Tahoma", Font.BOLD, 14));
		
//		iniValidaciones();
		setearColorNormal();
		
		botonesVisibles(false);
		
		
//		btn_closeWindow.setVisible(false);
//		btn_minimize.setVisible(false);
//		
//		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//		this.addWindowListener(new WindowAdapter() {
//		    public void windowClosing(WindowEvent e) {
//		    	int confirm = JOptionPane.showConfirmDialog(null, "Estas seguro de que quieres salir de la aplicación");
//				if (confirm == 0) {
//		            System.exit(1);
//		        }
//				
//		    }
//		});
		
	}
	
//	public void changeToProcesamiento() {	 
//		ProcesamientoFrame procesamiento = new ProcesamientoFrame();
//		procesamiento.setVisible(true); 
//		this.dispose();
//	 }
	
	public void changeToConsultaLotes() {		 
		 ConsultaLotesFrame frame = new ConsultaLotesFrame();
		 frame.setVisible(true);	 
		 this.dispose();
	 }
	
	
	public void iniValidaciones(boolean renameImages) {
		
		if(renameImages) {
			renombrarImagenes();
		}
		
		imagenesTodas.clear();
		imagenesValidas.clear();
		imagenesInvalidas.clear();
		cleanModel();
//      Servicios que conectan con lo otros proyectos
//        RestclientService restclientservice = new RestclientService();
//        OmrService omrservice = new OmrService();
		List<String> images = getAllImages();
		imagenesTodas=getAllImages();
		System.out.println("acabas de obtener todas las imagenes: "+images.size()+"image:"+images.get(0).toString());
		
		/***********************VALIDACION: 1 *****************************************/
		boolean inconsistenciasValid=validaInconsistenciasImages(images);
		if(!inconsistenciasValid) {
			icon_posiblesinconsistencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Close_Window_35px.png")));
		}else {
			icon_posiblesinconsistencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Checked_Checkbox_35px_1.png")));
		}
		System.out.println("inconsistenciasValid: "+inconsistenciasValid);
		
		
		/***********************VALIDACION: 2 *****************************************/
		boolean archivosDaniadosValid = validaArchivosDaniados(images);
		if(!archivosDaniadosValid) {
			icon_archivodaniado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Close_Window_35px.png")));
		}else {
			icon_archivodaniado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Checked_Checkbox_35px_1.png")));
		}
		System.out.println("archivosDaniadosValid: "+archivosDaniadosValid);
		
//		
		/***********************VALIDACION: 3 *****************************************/
		boolean nombreValid=validaNombreImages(images);
		if(!nombreValid) {
			icon_nomenclatura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Close_Window_35px.png")));
		}else {
			icon_nomenclatura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Checked_Checkbox_35px_1.png")));
		}
		System.out.println("nombreValid: "+nombreValid);
		
//		
//		
		/***********************VALIDACION: 4 *****************************************/
		boolean formatoValid=validaFormatoCompresion(images);
		if(!formatoValid) {
			icon_formatocompresion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Close_Window_35px.png")));
		}else {
			icon_formatocompresion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Checked_Checkbox_35px_1.png")));
		}
//		System.out.println("formatoValid: "+formatoValid);
//		
//		boolean asociacionLadosValid=false;
//		/***********************VALIDACION: 5 *****************************************/
//		//Lo realizamos siempre que se halla escogido la opcion de escaneo de anverso y reverso
//		if(true&&nombreValid) {			
//			asociacionLadosValid=validaAsociacionLados(images);
//			if(!asociacionLadosValid) {
//				icon_asociacionlados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Error_35px.png")));
//			}else {
//				icon_asociacionlados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Checked_Checkbox_35px_1.png")));
//			}
//			System.out.println("formatoValid: "+asociacionLadosValid);
//		}
//		
//		if(inconsistenciasValid&&archivosDaniadosValid&&nombreValid&&formatoValid&&asociacionLadosValid) {
//			btn_procesarimg.setVisible(true);
//		}
		
		new ArrayList<String>(new HashSet<String>(images4delete));
		for(int i=0;i<images4delete.size();i++) {
			imagenesTodas.remove(images4delete.get(i));
		}
		
		
		
		setListaValidasTodas();
		lbl_tipvalidacion.setVisible(false);
		lbl_currentimagen.setVisible(false);
		
		
		if(archivosDaniadosValid&&inconsistenciasValid&&nombreValid&&formatoValid) {
			allright=true;
		}else {
			allright=false;
		}
		
		
		
        
	}
	
	public List<String> getAllImages(){
		List<String> imagesall = new ArrayList<String>();	
		imagesall=met.getImages(lote.getRutaAlmacenamiento(), extensionesvalidas, true);
		System.out.println("hay en total: "+imagesall.size());
		return imagesall;
	}
	
	public boolean validaAsociacionLados(List<String> images) {
		boolean isValid=true;
		
		nones = new ArrayList<String>();
        pares = new ArrayList<String>();
        char caracter = '#';
        
        if ( (images.size() & 1) == 0 ) {
			for(int i=1;i<=images.size();i++) {
				String[] parts =images.get(i-1).split("\\.");
				String nombreParseado=met.parsearNumeroANomenclatura(i, lote.getConfiguracionEscaner().getNombreNomenclatura(), caracter);
				String imagesAComparar=parts[0];
				if ( (i & 1) == 0 ) {
					if(nombreParseado.equals(imagesAComparar)) {
						pares.add(images.get(i-1));
					}
				}else{
					if(nombreParseado.equals(imagesAComparar)) {
						nones.add(images.get(i-1));
					}
				}
			}
			
			if (!(pares.size()==nones.size())) {
				isValid=false;
			}
			
		}else{
			System.out.println("No existe relacion de paridad en las imágenes");
			isValid=false;
		}
        panel_check_asociacion.setVisible(true);
		return isValid;
	}
	
	public boolean validaInconsistenciasImages(List<String> images) {
		boolean isValid=true;
		ImagenMediasAritmeticas medias = met.generaListaInfoPromedioImagenes(lote.getRutaAlmacenamiento(), images);
      
        
        for(int i=0; i<images.size();i++) {       	
        	System.out.println("entra la imagen: "+images.get(i));
        	boolean fileValid=met.validaImagenSospechosa(lote.getRutaAlmacenamiento(), images.get(i), medias);
        	System.out.println("la imagen: "+images.get(i)+", es Valida?= "+fileValid);
        	imagen_temp = new ImagenVO();
			imagen_temp.setNombre(images.get(i).toUpperCase());
			imgvalidacion_temp = new ImagenValidacionVO();
			imgvalidacion_temp.setCdvalidacion(ConfigEnumStrings.codigo_inconsistencia.getConstante());
			if(fileValid) {
				imgvalidacion_temp.setDescripcion("Correcto");				
				imagen_temp.setStatus(ConfiguracionEnum.imagenValida.getConstante());
				imagen_temp.setValidacion(imgvalidacion_temp);
				imagenesValidas.add(imagen_temp);
			}else {
				imgvalidacion_temp.setDescripcion("El archivo es probablemente inconsistente");
				imagen_temp.setStatus(ConfiguracionEnum.imagenInvalida.getConstante());
				imagen_temp.setValidacion(imgvalidacion_temp);
				imagenesInvalidas.add(imagen_temp);
				isValid=false;
				images4delete.add(images.get(i));
				
			}
        }
        panel_check_posiblesinconsistencias.setVisible(true);
		return isValid;
	}
	
	public boolean validaArchivosDaniados(List<String> images) {
		boolean isValid=true;
		for(int i=0;i<images.size();i++) {
			boolean fileValid = met.validaArchivoInaccesibleODaniado(lote.getRutaAlmacenamiento(), images.get(i));
			System.out.println("fomato dañado: "+lote.getRutaAlmacenamiento()+images.get(i)+" = "+fileValid);
			imagen_temp = new ImagenVO();
			imagen_temp.setNombre(images.get(i).toUpperCase());
			imgvalidacion_temp = new ImagenValidacionVO();
			imgvalidacion_temp.setCdvalidacion(ConfigEnumStrings.codigo_archivodaniado.getConstante());
			if(fileValid) {
				imgvalidacion_temp.setDescripcion("Correcto");				
				imagen_temp.setStatus(ConfiguracionEnum.imagenValida.getConstante());
				imagen_temp.setValidacion(imgvalidacion_temp);
				imagenesValidas.add(imagen_temp);
			}else {
				imgvalidacion_temp.setDescripcion("El archivo esta dañado o es inaccesible");
				imagen_temp.setStatus(ConfiguracionEnum.imagenInvalida.getConstante());
				imagen_temp.setValidacion(imgvalidacion_temp);
				imagenesInvalidas.add(imagen_temp);
				isValid=false;
				images4delete.add(images.get(i));
			}
		}
		panel_check_archivodaniado.setVisible(true);
		return isValid;
	}
	
	public boolean validaNombreImages(List<String> images) {
		boolean isValid=true;
		String nomenclatura=(lote.getConfiguracionEscaner().getNombreNomenclatura()).toLowerCase();
		char caracter = '#';

		for(int i=0;i<images.size();i++) {
			String nombreArchivo= images.get(i).toLowerCase();
			boolean fileValid = met.validaNombreImagenCorrespondaNomenclatura(nomenclatura, nombreArchivo, caracter);
			
			imagen_temp = new ImagenVO();
			imagen_temp.setNombre(nombreArchivo.toUpperCase());
			imgvalidacion_temp = new ImagenValidacionVO();
			imgvalidacion_temp.setCdvalidacion(ConfigEnumStrings.codigo_nomenclatura.getConstante());
			if(fileValid) {
				imgvalidacion_temp.setDescripcion("Correcto");				
				imagen_temp.setStatus(ConfiguracionEnum.imagenValida.getConstante());
				imagen_temp.setValidacion(imgvalidacion_temp);
				imagenesValidas.add(imagen_temp);
			}else {
				imgvalidacion_temp.setDescripcion("La nomenclatura es incorrecta");
				imagen_temp.setStatus(ConfiguracionEnum.imagenInvalida.getConstante());
				imagen_temp.setValidacion(imgvalidacion_temp);
				imagenesInvalidas.add(imagen_temp);
				isValid=false;
				images4delete.add(images.get(i));
			}
		}
		panel_check_nomenclatura.setVisible(true);
		return isValid;
	}
	
	public boolean validaFormatoCompresion(List<String> images) {
		boolean isValid=true;
		String formatoValido = (lote.getConfiguracionEscaner().getTipoArchivos().getExtencion()).toLowerCase();
//		
		for(int i=0;i<images.size();i++) {
			/*Realizamos la validacion*/
			String nombreArchivo = images.get(i).toLowerCase();
			boolean fileValid =  nombreArchivo.contains(formatoValido);
			/*Fin de realizamos la validacion*/
			
			imagen_temp = new ImagenVO();
			imgvalidacion_temp = new ImagenValidacionVO();
			
			imagen_temp.setNombre(nombreArchivo.toUpperCase());
			imgvalidacion_temp.setCdvalidacion(ConfigEnumStrings.codigo_formatocompresion.getConstante());
			
			if(fileValid) {
				imgvalidacion_temp.setDescripcion("Correcto");				
				imagen_temp.setStatus(ConfiguracionEnum.imagenValida.getConstante());
				imagen_temp.setValidacion(imgvalidacion_temp);
				imagenesValidas.add(imagen_temp);
			}else {
				imgvalidacion_temp.setDescripcion("El formato de archivo es incorrecto");
				imagen_temp.setStatus(ConfiguracionEnum.imagenInvalida.getConstante());
				imagen_temp.setValidacion(imgvalidacion_temp);
				imagenesInvalidas.add(imagen_temp);
				isValid=false;
				images4delete.add(images.get(i));
			}
		}
		panel_check_compresion.setVisible(true);
//		
		return isValid;
	}
	
	public void setListaValidasTodas(){	
		cleanModel();	
		int totalimages=0;
		for(int i=0; i<imagenesTodas.size(); i++){				 
			dlm.addElement(imagenesTodas.get(i));            
			list.setModel(dlm);
			totalimages++;
		 }
		System.out.println("en imagenes solo validas hay: "+imagenesTodas.size());
		txt_total.setText(totalimages+"");
		txt_totalimg_lote.setText(totalimages+"");
		txt_title_img.setText("Todas");
		txt_titleimages.setText("Imágenes Válidas");
		btn_imginvalidas.setVisible(false);
		btn_imgvalidas.setVisible(false);
		setearColorNormal();
	}
	
	public void setLista(int imagenEstatus){	        
		 cleanModel();	 
		 int totalimages=0;
		 /*****************************INVÁLIDAS*****************************************/
		 if(imagenEstatus==ConfiguracionEnum.imagenInvalida.getConstante()&&
				 current_codigovalidacion.equals(ConfigEnumStrings.codigo_formatocompresion.getConstante())){
			 for(int i=0; i<imagenesInvalidas.size(); i++){				 
				 if(imagenesInvalidas.get(i).getValidacion().getCdvalidacion().equals(ConfigEnumStrings.codigo_formatocompresion.getConstante())){
					 dlm.addElement(imagenesInvalidas.get(i).getNombre()+"--"+imagenesInvalidas.get(i).getValidacion().getDescripcion());            
					 list.setModel(dlm);
					 totalimages++;
				 }
				 //*Aqui agrega los demas tipos de error
			 }
			 txt_total.setText(totalimages+"");
			 txt_totalimg_lote.setText(totalimages+"");
			 txt_titleimages.setText("Imágenes Inválidas");
		 }
		 if(imagenEstatus==ConfiguracionEnum.imagenInvalida.getConstante()&&
				 current_codigovalidacion.equals(ConfigEnumStrings.codigo_nomenclatura.getConstante())){
			 for(int i=0; i<imagenesInvalidas.size(); i++){				 
				 if(imagenesInvalidas.get(i).getValidacion().getCdvalidacion().equals(ConfigEnumStrings.codigo_nomenclatura.getConstante())){
					 dlm.addElement(imagenesInvalidas.get(i).getNombre()+"--"+imagenesInvalidas.get(i).getValidacion().getDescripcion());            
					 list.setModel(dlm);
					 totalimages++;
				 }
				 //*Aqui agrega los demas tipos de error
			 }
			 txt_total.setText(totalimages+"");
			 txt_totalimg_lote.setText(totalimages+"");
			 txt_titleimages.setText("Imágenes Inválidas");
		 }
		 
		 if(imagenEstatus==ConfiguracionEnum.imagenInvalida.getConstante()&&
				 current_codigovalidacion.equals(ConfigEnumStrings.codigo_archivodaniado.getConstante())){
			 for(int i=0; i<imagenesInvalidas.size(); i++){				 
				 if(imagenesInvalidas.get(i).getValidacion().getCdvalidacion().equals(ConfigEnumStrings.codigo_archivodaniado.getConstante())){
					 dlm.addElement(imagenesInvalidas.get(i).getNombre()+"--"+imagenesInvalidas.get(i).getValidacion().getDescripcion());            
					 list.setModel(dlm);
					 totalimages++;
				 }
				 //*Aqui agrega los demas tipos de error
			 }
			 txt_total.setText(totalimages+"");
			 txt_totalimg_lote.setText(totalimages+"");
			 txt_titleimages.setText("Imágenes Inválidas");
		 }
		 
		 if(imagenEstatus==ConfiguracionEnum.imagenInvalida.getConstante()&&
				 current_codigovalidacion.equals(ConfigEnumStrings.codigo_inconsistencia.getConstante())){
			 for(int i=0; i<imagenesInvalidas.size(); i++){				 
				 if(imagenesInvalidas.get(i).getValidacion().getCdvalidacion().equals(ConfigEnumStrings.codigo_inconsistencia.getConstante())){
					 dlm.addElement(imagenesInvalidas.get(i).getNombre()+"--"+imagenesInvalidas.get(i).getValidacion().getDescripcion());            
					 list.setModel(dlm);
					 totalimages++;
				 }
				 //*Aqui agrega los demas tipos de error
			 }
			 txt_total.setText(totalimages+"");
			 txt_totalimg_lote.setText(totalimages+"");
			 txt_titleimages.setText("Imágenes Inválidas");
		 }
		 
		 
		 /*****************************VÁLIDAS*****************************************/
		 
		 if(imagenEstatus==ConfiguracionEnum.imagenValida.getConstante()&&
				 current_codigovalidacion.equals(ConfigEnumStrings.codigo_formatocompresion.getConstante())){
			 for(int i=0; i<imagenesValidas.size(); i++){
				 if(imagenesValidas.get(i).getValidacion().getCdvalidacion().equals(ConfigEnumStrings.codigo_formatocompresion.getConstante())){
					 dlm.addElement(imagenesValidas.get(i).getNombre());            
					 list.setModel(dlm);
					 totalimages++;
				 }
			 }
			 txt_total.setText(totalimages+"");
			 txt_totalimg_lote.setText(totalimages+"");
			 txt_titleimages.setText("Imágenes Válidas");
		 }
		 
		 if(imagenEstatus==ConfiguracionEnum.imagenValida.getConstante()&&
				 current_codigovalidacion.equals(ConfigEnumStrings.codigo_nomenclatura.getConstante())){
			 for(int i=0; i<imagenesValidas.size(); i++){
				 if(imagenesValidas.get(i).getValidacion().getCdvalidacion().equals(ConfigEnumStrings.codigo_nomenclatura.getConstante())){
					 dlm.addElement(imagenesValidas.get(i).getNombre());            
					 list.setModel(dlm);
					 totalimages++;
				 }
				//*Aqui agrega los demas tipos de error
			 }
			 txt_total.setText(totalimages+"");
			 txt_totalimg_lote.setText(totalimages+"");
			 txt_titleimages.setText("Imágenes Válidas");
		 }
		 
		 if(imagenEstatus==ConfiguracionEnum.imagenValida.getConstante()&&
				 current_codigovalidacion.equals(ConfigEnumStrings.codigo_archivodaniado.getConstante())){
			 for(int i=0; i<imagenesValidas.size(); i++){
				 if(imagenesValidas.get(i).getValidacion().getCdvalidacion().equals(ConfigEnumStrings.codigo_archivodaniado.getConstante())){
					 dlm.addElement(imagenesValidas.get(i).getNombre());            
					 list.setModel(dlm);
					 totalimages++;
				 }
				//*Aqui agrega los demas tipos de error
			 }
			 txt_total.setText(totalimages+"");
			 txt_totalimg_lote.setText(totalimages+"");
			 txt_titleimages.setText("Imágenes Válidas");
		 }
		 
		 if(imagenEstatus==ConfiguracionEnum.imagenValida.getConstante()&&
				 current_codigovalidacion.equals(ConfigEnumStrings.codigo_inconsistencia.getConstante())){
			 for(int i=0; i<imagenesValidas.size(); i++){
				 if(imagenesValidas.get(i).getValidacion().getCdvalidacion().equals(ConfigEnumStrings.codigo_inconsistencia.getConstante())){
					 dlm.addElement(imagenesValidas.get(i).getNombre());            
					 list.setModel(dlm);
					 totalimages++;
				 }
				//*Aqui agrega los demas tipos de error
			 }
			 txt_total.setText(totalimages+"");
			 txt_totalimg_lote.setText(totalimages+"");
			 txt_titleimages.setText("Imágenes Válidas");
		 }
		 //
		 
		 /*************************IMAGEN NONES*************************************/
		 if(imagenEstatus==ConfiguracionEnum.imagenNones.getConstante()&&
				 current_codigovalidacion.equals(ConfigEnumStrings.codigo_asociacionlados.getConstante())){
			 for(int i=0; i<nones.size(); i++){
				 dlm.addElement(nones.get(i));            
				 list.setModel(dlm);
				 totalimages++;
			 }
			 txt_total.setText(totalimages+"");
			 txt_totalimg_lote.setText(totalimages+"");
			 txt_titleimages.setText("Imágenes Anverso");
		 }
		 /*************************IMAGEN PARES*************************************/
		 if(imagenEstatus==ConfiguracionEnum.imagenPares.getConstante()&&
				 current_codigovalidacion.equals(ConfigEnumStrings.codigo_asociacionlados.getConstante())){
			 for(int i=0; i<pares.size(); i++){
				 dlm.addElement(pares.get(i));            
				 list.setModel(dlm);
				 totalimages++;
			 }
			 txt_total.setText(totalimages+"");
			 txt_totalimg_lote.setText(totalimages+"");
			 txt_titleimages.setText("Imágenes Reverso");
		 }
		 
		 //
	 }
	 
	 public void cleanModel(){
	        ((DefaultListModel<String>)list.getModel()).clear();
	 }
	 
	 public void setImageFile(String path, String fileName){
		 txt_imagen.setIcon(new javax.swing.ImageIcon(""));
	     try {
		 myPicture = ImageIO.read(new File(lote.getRutaAlmacenamiento()+fileName));
		 System.out.println(lote.getRutaAlmacenamiento()+fileName);
		 txt_imagen.setIcon(new javax.swing.ImageIcon(met.ScaleImage(myPicture,txt_imagen.getWidth(),1900)));
	     } catch (IOException e) {
	    	 // TODO Auto-generated catch block
	    	 e.printStackTrace();
	     }               
	 }
	 
	 public void showImageOptions(boolean valor) {
		 btn_rotar.setVisible(valor);
		 btn_delete.setVisible(valor);
		 btn_reemplazarimg.setVisible(valor);
	 }
	 
	 public void startRenombrado() {
		 final LoadingFrame loadi = new LoadingFrame(lote, conf);
		 loadi.setVisible(true);
			/********************/
			new Thread(new Runnable(){
				 @Override
				public void run() {
					 
					 int countimages=0;
					 List<String> images = getAllImages();
					 String[] folders= {"temporal"};
					 try {
						met.makeFolders(lote.getRutaAlmacenamiento(), folders);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 loadi.showMensaje("Espere mientras se realiza el nombrado de los archivos conforme a la nomenclatura indicada.");
					 
		             for(int i=0;i<images.size();i++) {
		            	 countimages++;
		            	 String nomim=met.parsearNumeroANomenclatura(countimages, lote.getConfiguracionEscaner().getNombreNomenclatura(), '#');
						 met.moveToPath(lote.getRutaAlmacenamiento(), lote.getRutaAlmacenamiento()+"\\temporal\\", 
								 images.get(i).toString(), images.get(i).toString());
						 
						 met.copyToPath(lote.getRutaAlmacenamiento()+"\\temporal\\", lote.getRutaAlmacenamiento(), 
								 images.get(i).toString(), nomim);
						 
						 float result=((i+1)*100)/images.size();
						 loadi.updateGraphics((int) result);
		            	 			            	 
		             }	 
		             try {
						FileUtils.deleteDirectory(new File(lote.getRutaAlmacenamiento()+"\\temporal\\"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		             loadi.btn_close.setVisible(true);
		             loadi.showMensaje("El proceso de renombrado de los archivos se completó satisfactoriamente.");
		             iniValidaciones(false);
				 }
			 }).start();
			/****************/
		}
	 
	 public boolean renombrarImagenes() {
		 txt_imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LogotipoTeclo.png")));
		 startRenombrado();
		 //		 LoadingFrame load = new LoadingFrame(lote, conf);
//		 load.setVisible(true);
//		 boolean value=load.startRenombrado();
//		 System.out.println("este value hilo: "+value);
//		 int countimages=0;
//		 List<String> images = getAllImages();
//		 String[] folders= {"temporal"};
//		 try {
//			met.makeFolders(lote.getRutaAlmacenamiento(), folders);
//			//Movemos a un directorio temporal
//			for(int i=0;i<images.size();i++) {
//				countimages++;
//				String nomim=met.parsearNumeroANomenclatura(countimages, lote.getConfiguracionEscaner().getNombreNomenclatura(), '#');
//				 met.moveToPath(lote.getRutaAlmacenamiento(), lote.getRutaAlmacenamiento()+"\\temporal\\", 
//						 images.get(i).toString(), images.get(i).toString());
//				 
//				 met.copyToPath(lote.getRutaAlmacenamiento()+"\\temporal\\", lote.getRutaAlmacenamiento(), 
//						 images.get(i).toString(), nomim);
//			 }
//			FileUtils.deleteDirectory(new File(lote.getRutaAlmacenamiento()+"\\temporal\\"));
//			//Reasignamos nombres
//			
//			
//			
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;
//		}
//		 
		 return true;
	 }
	 
	 
	 public void startFormato() {
		 final LoadingFrame load = new LoadingFrame(lote, conf);
		 load.setVisible(true);
			/********************/
			new Thread(new Runnable(){
				 @Override
				public void run() {
					 List<String> images = getAllImages();
					 String[] folders= {"temporal"};
					 try {
						met.makeFolders(lote.getRutaAlmacenamiento(), folders);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 load.showMensaje("Espere mientras se modifica en los archivos el formato de compresión.");
					 
		             for(int i=0;i<images.size();i++) {
		            	 met.moveToPath(lote.getRutaAlmacenamiento(), lote.getRutaAlmacenamiento()+"\\temporal\\", 
								 images.get(i).toString(), images.get(i).toString());
						 String[] parts = images.get(i).toString().split("\\.");
						 met.writeImagesFromPathToAnotherCustomFormat(lote.getRutaAlmacenamiento()+"\\temporal\\", lote.getRutaAlmacenamiento(), 
								 images.get(i).toString(), parts[0], lote.getConfiguracionEscaner().getTipoArchivos().getExtencion());
						 		 			            			 
						 float result=((i+1)*100)/images.size();
						 load.updateGraphics((int) result);
		            	 			            	 
		             }	 
		             try {
						FileUtils.deleteDirectory(new File(lote.getRutaAlmacenamiento()+"\\temporal\\"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		             load.btn_close.setVisible(true);
		             load.showMensaje("El proceso de cambio formato de compresión se completó satisfactoriamente.");
		             iniValidaciones(false);
				 }
			 }).start();
			/****************/
			
		}
	 
	 public boolean cambiarFormatoArchivoImagenes() {
		 
		 
		 txt_imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LogotipoTeclo.png")));
//		 LoadingFrame load = new LoadingFrame(lote, conf);
//		 load.setVisible(true);
//		 load.startFormato();
		 startFormato();
//		 load.dispose();
//		 int countimages=0;
//		 List<String> images = getAllImages();
//		 
//		 /*****************************************/
//		 
////		 
//		 String[] folders= {"temporal"};
//		 try {
//			met.makeFolders(lote.getRutaAlmacenamiento(), folders);
//			
//
//			//Movemos a un directorio temporal
//			for(int i=0;i<images.size();i++) {
//				countimages++;
//				
//				//String nomim=met.parsearNumeroANomenclatura(countimages, lote.getConfiguracionEscaner().getNombreNomenclatura(), '#');
//				 met.moveToPath(lote.getRutaAlmacenamiento(), lote.getRutaAlmacenamiento()+"\\temporal\\", 
//						 images.get(i).toString(), images.get(i).toString());
//				 String[] parts = images.get(i).toString().split("\\.");
//				 met.writeImagesFromPathToAnotherCustomFormat(lote.getRutaAlmacenamiento()+"\\temporal\\", lote.getRutaAlmacenamiento(), 
//						 images.get(i).toString(), parts[0], lote.getConfiguracionEscaner().getTipoArchivos().getExtencion());
//				 
//				 float result=((i+1)*100)/images.size();
//					load.updateGraphics((int) result);
//				
//				 
//				
//			 }
//			FileUtils.deleteDirectory(new File(lote.getRutaAlmacenamiento()+"\\temporal\\"));
//			//Reasignamos nombres
//			
//			
//			
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;
//		}
//		 /**********************************/
		 return true;
	 }
	 
	 public void changeToProcesarLote(LoteVO lote) {
		 ProcesamientoFrame validacionLote = new ProcesamientoFrame(lote, conf, usuario);
		 validacionLote.setVisible(true); 
		 this.dispose();
	 }
	 
	 public int countNumberOfImages(){
		 List<String> listImages = met.getImages(lote.getRutaAlmacenamiento(), null, false);
		 return listImages.size();
	 }
	 
	public void changeToReanudarLote(LoteVO lote, UsuarioVO usuario) {
		 ReanudarLoteFrame reanudarLote = new ReanudarLoteFrame(lote, conf, usuario);
		 reanudarLote.setVisible(true); 
		 this.dispose();
	}
	 
	 public boolean updateLote(Long idEstatus, BitacoraVO bitacoraVO) {
		 countimages=countNumberOfImages();
		 System.out.println("Se actualiza el lote");
		 lotesRestclientservice = new LotesRestClientService();
		 boolean isSaved = lotesRestclientservice.actualizarEstTolImgLote(lote.getIdLote(),countimages,idEstatus, conf, bitacoraVO);
		 if(!isSaved) {
			 JOptionPane.showMessageDialog(null, "¡No se pudo establecer conexion intentelo mas tarde");
			 return false;
		 }
		 return true;
	 }
	 
	 public void setDataInvalidas() {
		 if(current_codigovalidacion.equals(ConfigEnumStrings.codigo_asociacionlados.getConstante())) {
				panel_listaimages.setVisible(true);
				currentImgStatus=ConfiguracionEnum.imagenNones.getConstante();
				setLista(ConfiguracionEnum.imagenNones.getConstante());
				txt_imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LogotipoTeclo.png")));
				lbl_currentimagen.setText("");
				
			}else {
				panel_listaimages.setVisible(true);
				currentImgStatus=ConfiguracionEnum.imagenInvalida.getConstante();
				setLista(ConfiguracionEnum.imagenInvalida.getConstante());
				txt_imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LogotipoTeclo.png")));
				lbl_currentimagen.setText("");
			}
	 }
	 
	 public void setearColorNormal() {
		 panel_check_posiblesinconsistencias.setBackground(new Color(230, 230, 250));
		 panel_check_nomenclatura.setBackground(new Color(230, 230, 250));
		 panel_check_archivodaniado.setBackground(new Color(230, 230, 250));
		 panel_check_compresion.setBackground(new Color(230, 230, 250));
		 panel_check_asociacion.setBackground(new Color(230, 230, 250));
		 
		 btn_renombrarvalidar.setVisible(false);
		 btn_cambiarformato.setVisible(false);
	 }
	 
	 public void minimizeWin() {
			this.setState(ConsultaLotesFrame.ICONIFIED);
		}
	 
	 public void changeIcon(){
			this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/Isotipo_DPIv2r2.png")));
		}
	 
	 public void botonesVisibles(boolean val) {
		 btn_rotar.setVisible(val);
		 btn_delete.setVisible(val);
		 btn_reemplazarimg.setVisible(val);
	 }
}
