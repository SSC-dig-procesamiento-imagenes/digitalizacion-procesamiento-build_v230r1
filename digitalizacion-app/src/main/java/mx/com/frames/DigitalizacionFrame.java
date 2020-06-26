package mx.com.frames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileSystemView;

import mx.com.app.services.BitacoraRestClientService;
import mx.com.app.services.ConfiguracionRestClientService;
import mx.com.app.services.LotesRestClientService;
import mx.com.app.services.ScannerService;
import mx.com.teclo.base.vo.bitacora.BitacoraVO;
import mx.com.teclo.base.vo.configuracion.ConfiguracionVO;
import mx.com.teclo.base.vo.configuracionescaner.ConfiguracionEscanerVO;
import mx.com.teclo.base.vo.estatusproceso.EstatusProcesoVO;
import mx.com.teclo.base.vo.lotes.LoteVO;
import mx.com.teclo.base.vo.lotes.PersistirLoteLoteVO;
import mx.com.teclo.base.util.methods.Methods;
import mx.com.teclo.base.vo.usuario.UsuarioVO;
import mx.com.teclo.base.util.enumerados.ConfiguracionEnum;
import mx.com.teclo.base.util.enumerados.ParametrosBitacoraEnum;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JScrollPane;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import javax.swing.UIManager;
import javax.swing.JSpinner;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Cursor;

public class DigitalizacionFrame extends JFrame {

	//Metodos bean locator
//	private Scanner scanner = (Scanner)BeanLocator.getService("scanner");

	ConfiguracionRestClientService configuracionRestclientservice;
	LotesRestClientService lotesRestclientservice;
	BitacoraRestClientService bitacoraRestClientService;
	ScannerService scannerService = new ScannerService();
	
//	ScannerService scannerservice = new ScannerService();
	Methods met = new Methods();
	ConfiguracionVO conf;
	List<ConfiguracionEscanerVO> listconfifEscaner;
	LoteVO lote = new LoteVO();
	List<LoteVO> lotesAll;
	
	//Variables Globales de componentes	
	private JPanel contentPane;
	private JTextField txt_nombreLote;
	JLabel txt_imagen = new JLabel("");
	JPanel btn_rotate = new JPanel();
	JPanel btn_delete = new JPanel();
	JPanel btn_addImage = new JPanel();
	JComboBox<String> combo_configuracion = new JComboBox<String>();
	final JComboBox<String> combo_metodo = new JComboBox<>();
	JLabel txt_marca = new JLabel("...");
	JLabel txt_modelo = new JLabel("...");
	JLabel lblFormato = new JLabel("Formato:");
	JLabel lblDpi = new JLabel("Dpi:"); 
	JLabel txt_resolucion = new JLabel("...");
	JLabel txt_formato = new JLabel("...");
	JLabel txt_tipopapel = new JLabel("...");
	JLabel txt_ladospapel = new JLabel("...");
	JLabel txt_nombreconfig = new JLabel("...");
	JMonthChooser monthChooser = new JMonthChooser();
	final JPanel btn_explorarFolder = new JPanel();
	final JPanel btn_cargarImage = new JPanel();
	JPanel btn_pausarlote = new JPanel();
	JPanel btn_guardarLote = new JPanel();
	JLabel lbl_nomimagen = new JLabel("");
    JSpinner spinner_1 = new JSpinner();
    
    JSpinner spinnerFolioInicial = new JSpinner();
    JSpinner spinnerFolioFinal = new JSpinner();
	private Long folioInicial = (long) 0;
	private Long folioFinal = (long) 0;
    
    
	JYearChooser yearChooser = new JYearChooser();
	JPanel panel_3 = new JPanel();
	
	//Variables globales
//	String[] extensionesvalidas={".TIF", ".tif", ".JPG", ".jpg"};
	List<String> extensionesvalidas = new ArrayList<String>();
	
//	int posX=0,posY=0;
	
	public static String currentImage="";
	public int currentMetDigitalizacion;
	BufferedImage myPicture;
	public int currentConfiguration;
	public int countimages=0;
	
	UsuarioVO usuario;
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					DigitalizacionFrame frame = new DigitalizacionFrame();
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
	JLabel text_totalimg = new JLabel("0");
	JList<String> list = new JList<String>();
	DefaultListModel<String> dlm = new DefaultListModel<String>();
	
	
	public DigitalizacionFrame(final ConfiguracionVO conf, final List<LoteVO> lotes,  final UsuarioVO usuario) {
		
		
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
		this.lotesAll=lotes;
		this.usuario=usuario;
		/*Inicio. quitar estas líneas*/
		if (this.usuario == null) {
			this.usuario = new UsuarioVO();
			this.usuario.setUsuarioId((long)2);
			this.usuario.setUsuarioNombre("dperez");
		}
		
		/*Final. Quitar estas líneas*/
		changeIcon();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(-6, 0, 1381, 734);
		this.setUndecorated(true);
//		this.setLocationByPlatform(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0,0,0,0));
		setContentPane(contentPane);
		
		JPanel panel_contenedorMain = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_contenedorMain, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1381, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_contenedorMain, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
		);
		panel_contenedorMain.setLayout(null);
		
//		this.addMouseListener(new MouseAdapter()
//        {
//           public void mousePressed(MouseEvent e)
//           {
//              posX=e.getX();
//              posY=e.getY();
//           }
//        });
//        
//        this.addMouseMotionListener(new MouseAdapter()
//        {
//             public void mouseDragged(MouseEvent evt)
//             {
//        		//sets frame position when mouse dragged			
//        		setLocation (evt.getXOnScreen()-posX,evt.getYOnScreen()-posY);
//        					
//             }
//        });
		
		JPanel panel_footer = new JPanel();
		panel_footer.setBounds(0, 681, 1381, 53);
		panel_footer.setBackground(new java.awt.Color(211, 26, 43));
		panel_footer.setLayout(null);
		panel_contenedorMain.add(panel_footer);
		panel_contenedorMain.setBackground(new java.awt.Color(236, 240, 245));
		
		JLabel lblVersin = new JLabel("Versión 2.3.0");
		lblVersin.setForeground(Color.WHITE);
		lblVersin.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblVersin.setBounds(1230, 0, 151, 53);
		panel_footer.add(lblVersin);
		
		JLabel label_18 = new JLabel("Derechos de autor © 2018 Teclo Mexicana. Todos los derechos reservados.");
		label_18.setForeground(Color.WHITE);
		label_18.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_18.setBounds(135, 0, 471, 53);
		panel_footer.add(label_18);
		
		JPanel panel_menu = new JPanel();
		panel_menu.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_menu.setBounds(0, 53, 122, 631);
		panel_menu.setBackground(Color.WHITE);
		panel_contenedorMain.add(panel_menu);
		panel_menu.setLayout(null);
		
		JPanel btn_menuConsultaLotes = new JPanel();
		btn_menuConsultaLotes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("esto trae lote: "+lote.getEstatusProceso().getIdEstatusProceso());
				if(lote.getEstatusProceso().getIdEstatusProceso()== (int) (long) ConfiguracionEnum.loteNoCreado.getConstante()) {
					changeToConsultaLotes();
				}else{
					 int confirm = JOptionPane.showConfirmDialog(null, "¿Deseas pausar el lote y continuar la digitalización más tarde?");
					 if (confirm == 0) {
						boolean isupdated = persistenciaLote();
						if(!isupdated) {
							JOptionPane.showMessageDialog(null, "¡No se pudo establecer conexion intentelo mas tarde");
						}
						changeToConsultaLotes();
				     }
				 }
				
			}
		});
		btn_menuConsultaLotes.setBackground(SystemColor.control);
//		panel_7.setBorder(new LineBorder(Color.GRAY));
		btn_menuConsultaLotes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btn_menuConsultaLotes.setBounds(0, 0, 122, 75);
		btn_menuConsultaLotes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panel_menu.add(btn_menuConsultaLotes);
		btn_menuConsultaLotes.setLayout(null);
		
		JLabel label_3 = new JLabel("");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(37, 11, 46, 36);
		label_3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Search_36px.png")));
		btn_menuConsultaLotes.add(label_3);
		
		JLabel lblConsultaDeLotes = new JLabel("Consulta de Lotes");
		lblConsultaDeLotes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConsultaDeLotes.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultaDeLotes.setBounds(0, 47, 122, 14);
		btn_menuConsultaLotes.add(lblConsultaDeLotes);
		
		JPanel btn_menuProcesamiento = new JPanel();
		btn_menuProcesamiento.setLayout(null);
		btn_menuProcesamiento.setBackground(new java.awt.Color(211, 26, 43));
		btn_menuProcesamiento.setBounds(0, 74, 122, 75);
		panel_menu.add(btn_menuProcesamiento);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Gears_36px.png")));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(37, 11, 46, 36);
		btn_menuProcesamiento.add(label_4);
		btn_menuProcesamiento.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btn_menuProcesamiento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		JLabel lblProcesamiento = new JLabel("Lote");
		lblProcesamiento.setForeground(Color.WHITE);
		lblProcesamiento.setHorizontalAlignment(SwingConstants.CENTER);
		lblProcesamiento.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProcesamiento.setBounds(0, 50, 122, 25);
		btn_menuProcesamiento.add(lblProcesamiento);
		
		JPanel panel_header = new JPanel();
		panel_header.setBounds(0, 0, 1381, 54);
		panel_contenedorMain.add(panel_header);
		panel_header.setBackground(Color.WHITE);
		panel_header.setLayout(null);
		panel_header.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		
		
		JLabel label = new JLabel("");
		label.setBounds(117, 0, 133, 44);
		label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logoApp.png")));
		panel_header.add(label);
		
		JPanel btn_closeWindow = new JPanel();
		btn_closeWindow.setToolTipText("Cerrar ventana");
		btn_closeWindow.setVisible(true);
		btn_closeWindow.setBorder(null);
		btn_closeWindow.setBounds(1296, 1, 57, 43);
		btn_closeWindow.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btn_closeWindow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "Estas seguro de que quieres salir de la aplicación");
				if (confirm == 0) {
		            System.exit(1);
		        }
			}
		});
		btn_closeWindow.setBackground(Color.WHITE);
		panel_header.add(btn_closeWindow);
		btn_closeWindow.setLayout(null);
		
		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int confirm = JOptionPane.showConfirmDialog(null, "Estas seguro de que quieres salir de la aplicación");
				if (confirm == 0) {
		            System.exit(1);
		        }
			}
		});
		label_1.setToolTipText("Cerrar ventana");
		label_1.setBounds(10, 0, 47, 43);
		label_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Cancel_50px.png")));
		btn_closeWindow.add(label_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(30, 0, 57, 43);
		panel_1.setBackground(Color.WHITE);
		panel_header.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(10, 0, 35, 44);
		label_2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Menu_35px.png")));
		panel_1.add(label_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(360, 0, 703, 44);
		panel_header.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Aplicación de Digitalización y Procesamiento de Imágenes");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 0, 683, 44);
		lblNewLabel.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		panel_2.add(lblNewLabel);
		
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
		btn_minimize.setBounds(1250, 0, 51, 44);
		panel_header.add(btn_minimize);
		
		JLabel label_16 = new JLabel("");
		label_16.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				minimizeWin();
			}
		});
		label_16.setToolTipText("Minimizar ventana");
		label_16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Minus_50px.png")));
		label_16.setBounds(0, 0, 50, 44);
		btn_minimize.add(label_16);
		
		JPanel panel_contenedor = new JPanel();
		panel_contenedor.setBorder(new LineBorder(Color.GRAY));
//		panel_9.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		panel_contenedor.setBackground(Color.WHITE);
		panel_contenedor.setBounds(132, 127, 1218, 543);
		
		panel_contenedorMain.add(panel_contenedor);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(Color.WHITE);
		panel_10.setBorder(new LineBorder(Color.GRAY));
		panel_10.setBounds(26, 22, 352, 225);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new LineBorder(Color.GRAY));
		panel_11.setBackground(Color.WHITE);
		panel_11.setBounds(388, 22, 130, 509);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new LineBorder(Color.GRAY));
		panel_12.setBounds(524, 22, 668, 509);
		panel_contenedor.setLayout(null);
		panel_contenedor.add(panel_10);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(1, 1, 351, 25);
		
		JLabel lblNombreDelLote = new JLabel("Nombre del Lote:");
		lblNombreDelLote.setBounds(11, 37, 127, 15);
		lblNombreDelLote.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		txt_nombreLote = new JTextField();
		txt_nombreLote.setBounds(119, 37, 222, 20);
		txt_nombreLote.setColumns(10);
		
		JLabel lblFechaDelLote = new JLabel("Fecha del Lote:");
		lblFechaDelLote.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaDelLote.setBounds(1, 130, 112, 28);
		lblFechaDelLote.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblMtodo = new JLabel("Método:");
		lblMtodo.setBounds(10, 72, 103, 15);
		lblMtodo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMtodo.setFont(new Font("Tahoma", Font.BOLD, 12));
		combo_metodo.setBounds(119, 68, 222, 25);
		combo_metodo.addItem("Digitalización Directa");
		combo_metodo.addItem("Digitalización Externa");
		btn_explorarFolder.setToolTipText("Selecciona carpeta de imágenes");
		
		
		btn_explorarFolder.setBounds(18, 195, 95, 24);
		btn_explorarFolder.setVisible(false);
		btn_explorarFolder.setBackground(new java.awt.Color(211, 26, 43));
		btn_explorarFolder.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btn_explorarFolder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btn_explorarFolder.setLayout(null);
		
		JLabel lblExplorar = new JLabel("Explorar");
		lblExplorar.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblExplorar.setForeground(Color.WHITE);
		lblExplorar.setBounds(39, 0, 56, 24);
		btn_explorarFolder.add(lblExplorar);
		
		JLabel label_10 = new JLabel("");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Folder_25px.png")));
		label_10.setBounds(0, 6, 35, 14);
		btn_explorarFolder.add(label_10);
		panel_10.setLayout(null);
		panel_6.setBackground(new java.awt.Color(211, 26, 43));
		
		JLabel lblConfiguracinDelLote = new JLabel("Configuración del Lote");
		lblConfiguracinDelLote.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblConfiguracinDelLote.setForeground(Color.WHITE);
		panel_6.add(lblConfiguracinDelLote);
		panel_10.add(panel_6);
		panel_10.add(lblNombreDelLote);
		panel_10.add(txt_nombreLote);
		panel_10.add(lblFechaDelLote);
		panel_10.add(lblMtodo);
		panel_10.add(btn_explorarFolder);
		panel_10.add(combo_metodo);
		
		final JLabel text_path = new JLabel("");
		text_path.setBounds(106, 195, 178, 24);
		
		text_path.setForeground(new Color(47, 79, 79));
		panel_10.add(text_path);
		text_path.setVisible(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(119, 133, 223, 25);
		panel_10.add(panel);
		panel.setLayout(null);
		yearChooser.setBounds(167, 0, 56, 25);
		panel.add(yearChooser);
		monthChooser.setBounds(0, 0, 95, 25);
		panel.add(monthChooser);

		spinner_1.setBounds(105, 0, 52, 25);
		panel.add(spinner_1);
		
		panel_3.setVisible(false);
		panel_3.setBounds(119, 195, 222, 25);
		panel_10.add(panel_3);
		panel_3.setLayout(null);
		
		
		btn_cargarImage.setToolTipText("Clic para cargar imágenes");
		btn_cargarImage.setVisible(false);
		
		btn_cargarImage.setBounds(187, 0, 35, 25);
		panel_3.add(btn_cargarImage);
		btn_cargarImage.setBackground(SystemColor.menu);
		btn_cargarImage.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btn_cargarImage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btn_cargarImage.setLayout(null);
		
		JLabel label_5 = new JLabel("");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Upload_25px.png")));
		label_5.setBounds(10, 0, 15, 14);
		btn_cargarImage.add(label_5);
		
		btn_cargarImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(validaCampos()) {
//					Date fech = met.convertLongToDate(lote.getFechaCracionLote(), conf.getFormatDateDecode());
					makeBaseFolders();
					String[] folders= {lote.getFechaCracionLote(),lote.getNombreLote()};
					try {
						met.makeFolders(conf.getPath(), folders);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					List<String> listImages = met.getImages(text_path.getText(), extensionesvalidas, true);
					countimages=countNumberOfImages();
					for(int i=0;i<listImages.size();i++) {
						countimages++;
					
					}
					LoadingFrame load = new LoadingFrame(lote, conf);
					 load.setVisible(true);
					 load.copyFilesFromDirectoryToAnother(listImages, text_path.getText()+"\\", lote.getRutaAlmacenamiento());
					 listenFiles();
//						setLista();
						
						boolean isSaved = persistenciaLote();//
						if(!isSaved) {
							JOptionPane.showMessageDialog(null, "¡No se puede establecer conexion intentelo mas tarde!");
						}else {
							disableFields();
							lote.setNumeroTotalImagenes(listImages.size());
//							JOptionPane.showMessageDialog(null, "¡Se han cargado las imágenes satisfactoriamente!");
							btn_cargarImage.setVisible(false);
							text_path.setVisible(false);
						}
					}	
			}
		});
		panel_contenedor.add(panel_11);
		panel_11.setLayout(null);
		
		JPanel panel_14 = new JPanel();
		panel_14.setBounds(0, 0, 130, 25);
		panel_14.setBackground(new Color(211, 26, 43));
		panel_11.add(panel_14);
		
		JLabel lblImgenes = new JLabel("Imágenes");
		lblImgenes.setForeground(Color.WHITE);
		lblImgenes.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_14.add(lblImgenes);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 23, 130, 416);
		panel_11.add(scrollPane);
		list.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int index=0;
				if(e.getKeyCode()==KeyEvent.VK_DOWN) {
					index = list.getSelectedIndex()+1;
					
					if (index >= 0) {
						Object o = list.getModel().getElementAt(index);
						currentImage=o.toString();
						setImageFile(lote.getRutaAlmacenamiento(), currentImage);
						showImageOptions(true);
						lbl_nomimagen.setText("Imagen: "+currentImage);
					}	
				}
				if(e.getKeyCode()==KeyEvent.VK_UP) {
					index = list.getSelectedIndex()-1;
					
					if (index >= 0) {
						Object o = list.getModel().getElementAt(index);
						currentImage=o.toString();
						setImageFile(lote.getRutaAlmacenamiento(), currentImage);
						showImageOptions(true);
						lbl_nomimagen.setText("Imagen: "+currentImage);
					}	
				}		
				
				if(e.getKeyCode()==KeyEvent.VK_DELETE) {
					int confirm = JOptionPane.showConfirmDialog(null, "Estas seguro de que quieres eliminar la imágen");
					if (confirm == 0) {
						
						if(met.deleteFile(lote.getRutaAlmacenamiento(), currentImage)) {						
							txt_imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LogotipoTeclo.png")));
//							setLista();
							showImageOptions(false);
							lbl_nomimagen.setText("");
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
					int index = theList.locationToIndex(e.getPoint());
					if (index >= 0) {
						Object o = theList.getModel().getElementAt(index);
//						JOptionPane.showMessageDialog(null, index);
						currentImage=o.toString();
						setImageFile(lote.getRutaAlmacenamiento(), o.toString());
						showImageOptions(true);
						lbl_nomimagen.setText("Imagen: "+currentImage);
					}
				}
				
			}
		});
		scrollPane.setViewportView(list);
		list.setModel(dlm);
		
		JPanel btn_refresh = new JPanel();
		btn_refresh.setToolTipText("Refresca la lista de imágenes");
		btn_refresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btn_refresh.setBounds(0, 476, 130, 33);
		panel_11.add(btn_refresh);
		btn_refresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		btn_refresh.setLayout(null);
		
		JLabel label_12 = new JLabel("");
		label_12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				JOptionPane.showConfirmDialog(null,"estas aqui");
				setLista();
			}
		});
		label_12.setToolTipText("Refresca la lista de imágenes");
		
		label_12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Refresh_27px.png")));
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setBounds(0, 0, 130, 33);
		label_12.setBorder(new LineBorder(Color.GRAY));
		label_12.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_refresh.add(label_12);
		
		JPanel panel_16 = new JPanel();
		panel_16.setBackground(Color.WHITE);
		panel_16.setBorder(new LineBorder(Color.GRAY));
		panel_16.setBounds(0, 439, 130, 39);
		panel_11.add(panel_16);
		panel_16.setLayout(null);
		text_totalimg.setBounds(50, 0, 70, 36);
		panel_16.add(text_totalimg);
		text_totalimg.setForeground(new Color(139, 0, 0));
		
		
		text_totalimg.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				
			}
		});
		text_totalimg.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblTotalDeImgenes = new JLabel("Total:");
		lblTotalDeImgenes.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalDeImgenes.setBounds(3, 1, 49, 36);
		panel_16.add(lblTotalDeImgenes);
		lblTotalDeImgenes.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_contenedor.add(panel_12);
		panel_12.setLayout(null);
		
		JPanel panel_contieneImagen = new JPanel();
		panel_contieneImagen.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_contieneImagen.setBackground(Color.WHITE);
		panel_contieneImagen.setBounds(10, 47, 648, 392);
		panel_12.add(panel_contieneImagen);
		panel_contieneImagen.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 648, 392);
		panel_contieneImagen.add(scrollPane_1);
		scrollPane_1.setViewportView(txt_imagen);
		txt_imagen.setHorizontalAlignment(SwingConstants.CENTER);
		txt_imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LogotipoTeclo.png")));
		btn_pausarlote.setToolTipText("Pausa el proceso de digitalización");
		
		btn_pausarlote.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btn_pausarlote.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				System.out.println("quieres pausar lote");
//				if(validaCampos()) {
					int confirm = JOptionPane.showConfirmDialog(null, "¿Deseas pausar el lote y continuar la digitalización más tarde?");
					if (confirm == 0) {
						countimages=countNumberOfImages();
						System.out.println("Se acaban de contar las imagenes con: "+countimages);
						boolean isUpdated = persistenciaLote();
						if(!isUpdated) {
							JOptionPane.showMessageDialog(null, "¡No se puede establecer conexion intentelo mas tarde!");
						}
						changeToConsultaLotes();
			        }
//					
//				}				
			}
		});
		btn_pausarlote.setBounds(383, 450, 119, 41);
		panel_12.add(btn_pausarlote);
		btn_pausarlote.setLayout(null);
		btn_pausarlote.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		
		JLabel lblPausarLote = new JLabel("Pausar Lote");
		lblPausarLote.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPausarLote.setBounds(29, 0, 76, 41);
		btn_pausarlote.add(lblPausarLote);
		
		JLabel label_9 = new JLabel("");
		label_9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Pause_25px.png")));
		label_9.setBounds(0, 0, 25, 41);
		btn_pausarlote.add(label_9);
		btn_guardarLote.setToolTipText("Guarda los cambios y continúa con el proceso de validación");
		
		
		btn_guardarLote.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "¿Deseas guardar el lote?");
				if (confirm == 0) {
					countimages=countNumberOfImages();
					
					BitacoraVO bitacoraVO = new BitacoraVO();
					bitacoraVO.setTabla(ParametrosBitacoraEnum.TDP_BITACORA_CAMBIOS.getParametro());
					bitacoraVO.setComponenteId(2L);
					bitacoraVO.setConceptoId(2L);
					
					String valorOriginal="LOTE CREADO";
					if(lote.getEstatusProceso().getIdEstatusProceso()== (int) (long) ConfiguracionEnum.actualizarLote.getConstante()) {
						valorOriginal="LOTE CREADO";
					}else if(lote.getEstatusProceso().getIdEstatusProceso()== (int) (long) ConfiguracionEnum.actualizarLote.getConstante()) {
						valorOriginal="LOTE EN PAUSA";
					}
					
					bitacoraVO.setValorOriginal(valorOriginal);
					bitacoraVO.setValorFinal("LOTE EN VALIDACIÓN");
					Long valorId = usuario.getUsuarioId() == null ? (long) 2 : usuario.getUsuarioId();
					bitacoraVO.setModificadoPor(valorId);
					bitacoraVO.setIdRegistro(lote.getIdLote()+"");
					bitacoraVO.setRegistroDescripcion(lote.getNombreLote());
					bitacoraVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
					
					bitacoraVO.setNuFolioInicial(lote.getNuFolioInicial());
					bitacoraVO.setNuFolioFinal(lote.getNuFolioFinal());
					bitacoraVO.setIdUsuario(valorId);
					bitacoraVO.setNombreLote(lote.getNombreLote());
					
					if(updateLote(2L,bitacoraVO)){

					}
					System.out.println("Se envia a validacion");
					changeToValidacion();
		        }
				
				
			}
		});
		btn_guardarLote.setLayout(null);
		btn_guardarLote.setBounds(512, 450, 131, 41);
		btn_guardarLote.setBackground(new java.awt.Color(211, 26, 43));
		btn_guardarLote.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btn_guardarLote.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panel_12.add(btn_guardarLote);
		
		JLabel lblGuardarLote = new JLabel("Guardar Lote");
		lblGuardarLote.setForeground(Color.WHITE);
		lblGuardarLote.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGuardarLote.setBounds(43, 0, 88, 41);
		btn_guardarLote.add(lblGuardarLote);
		
		JLabel label_11 = new JLabel("");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Save_25px_3.png")));
		label_11.setBounds(0, 0, 43, 41);
		btn_guardarLote.add(label_11);
		btn_rotate.setToolTipText("Rotar imagen 180°");
		btn_rotate.addMouseListener(new MouseAdapter() {
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
		
		
		btn_rotate.setBounds(10, 450, 41, 41);
		btn_rotate.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btn_rotate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panel_12.add(btn_rotate);
		btn_rotate.setLayout(null);
		
		JLabel label_6 = new JLabel("");
		label_6.setBounds(0, 0, 41, 41);
		btn_rotate.add(label_6);
		label_6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Synchronize_36px.png")));
		btn_delete.setToolTipText("Borrar imagen");
		btn_delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "Estas seguro de que quieres eliminar la imágen");
				if (confirm == 0) {
					
					if(met.deleteFile(lote.getRutaAlmacenamiento(), currentImage)) {						
						txt_imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LogotipoTeclo.png")));
//						setLista();
						showImageOptions(false);
					}else {
						JOptionPane.showMessageDialog(null, "Ocurrio un error al eliminar el archivo, intentelo más tarde");
					}
		        }
			}
		});
		
		btn_delete.setBounds(61, 450, 41, 41);
		btn_delete.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btn_delete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panel_12.add(btn_delete);
		btn_delete.setLayout(null);
		
		JLabel label_7 = new JLabel("");
		label_7.setBounds(0, 0, 41, 41);
		btn_delete.add(label_7);
		label_7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Trash_Can_36px.png")));
		btn_addImage.setToolTipText("Reemplazar imagen");
		btn_addImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ReemplazarImagenFrame replace = new ReemplazarImagenFrame(conf, lote, currentImage);
				replace.setVisible(true);
//				setLista();
				
				btn_rotate.setVisible(false);
				btn_delete.setVisible(false);
				btn_addImage.setVisible(false);
				
				txt_imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LogotipoTeclo.png")));
//				/**************************************/
//				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
//				jfc.setDialogTitle("Selecciona la carpeta de lotes a cargar: ");
//				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
//
//				int returnValue = jfc.showSaveDialog(null);
//				if (returnValue == JFileChooser.APPROVE_OPTION) {
//					if (jfc.getSelectedFile().isFile()) {
////						btn_cargarImage.setVisible(true);
//
//						String path=""+jfc.getSelectedFile().getAbsolutePath();
//						String name=""+jfc.getSelectedFile().getName();
//						path=path.replaceAll(name, "");
////						name=jfc.getSelectedFile().getName().split("\\.")[0];
//						
//						
//						System.out.println("You selected the jfc.getSelectedFile().getAbsolutePath(): " + jfc.getSelectedFile().getAbsolutePath());	
//						met.replaceToPath(jfc.getSelectedFile().getAbsolutePath(), lote.getRutaAlmacenamiento(), 
//								jfc.getSelectedFile().getName(), currentImage);
//						txt_imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LogotipoTeclo.png")));
////						text_path.setText(""+jfc.getSelectedFile());
////						text_path.setToolTipText(""+jfc.getSelectedFile());
//					}
//				}
//				
//				/**************************************/
			}
		});
		
		btn_addImage.setBounds(112, 450, 41, 41);
		btn_addImage.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btn_addImage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panel_12.add(btn_addImage);
		btn_addImage.setLayout(null);
		
		JLabel label_8 = new JLabel("");
		label_8.setBounds(0, 0, 41, 41);
		btn_addImage.add(label_8);
		label_8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Add_Image_36px.png")));
		
		
		lbl_nomimagen.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_nomimagen.setForeground(new Color(0, 0, 51));
		lbl_nomimagen.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_nomimagen.setBounds(10, 11, 648, 25);
		panel_12.add(lbl_nomimagen);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(26, 252, 351, 279);
		panel_contenedor.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblCOnfiguracin = new JLabel("Configuración:");
		lblCOnfiguracin.setBounds(10, 37, 103, 22);
		panel_4.add(lblCOnfiguracin);
		lblCOnfiguracin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCOnfiguracin.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JPanel panel_19 = new JPanel();
		panel_19.setBorder(null);
		panel_19.setBounds(128, 35, 213, 24);
		panel_4.add(panel_19);
		panel_19.setBackground(UIManager.getColor("CheckBox.background"));
		panel_19.setLayout(null);
		txt_nombreconfig.setBounds(10, 0, 193, 24);
		txt_nombreconfig.setHorizontalAlignment(SwingConstants.LEFT);
		
		
		txt_nombreconfig.setForeground(Color.BLACK);
		txt_nombreconfig.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_19.add(txt_nombreconfig);
		
		JPanel btn_digitalizar = new JPanel();
		btn_digitalizar.setToolTipText("Digitalizar con escáner");
		btn_digitalizar.setBounds(112, 217, 142, 51);
		panel_4.add(btn_digitalizar);
		btn_digitalizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(validaCampos()) {
					makeBaseFolders();
//					Date fech = met.convertLongToDate(lote.getFechaCracionLote(), conf.getFormatDateDecode());
					
					String[] folders= {lote.getFechaCracionLote(),lote.getNombreLote()};
					try {
						
						met.makeFolders(conf.getPath(), folders);
					} catch (IOException e1) {
						// TODO Auto-generated catch block 
						e1.printStackTrace();
					}
					
					lote.getConfiguracionEscaner().setIdConfiguracionEscaner(listconfifEscaner.get(currentConfiguration).getIdConfiguracionEscaner());
					lote.setConfiguracionEscaner(listconfifEscaner.get(currentConfiguration));
					
					/*********seccion para manejar el scanner individualmente***************/
					int consecutivo = getNextNumberOfImage();
					boolean continuar=true;
//					if(consecutivo==0&&countNumberOfImages()>0) {
//						int confirm = JOptionPane.showConfirmDialog(null,"La última imágen no cumple con la nomenclatura apropiada, ¿Desea continuar con la digitalización a partir de la numeracion: "+(countNumberOfImages()+1)+"?");
//						if(confirm==JOptionPane.YES_OPTION) {
//							consecutivo=countNumberOfImages();
//						}else {
//							continuar=false;
//						}
//					}
					
					lote.setNextNumber(consecutivo+1);
					
					ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
					String lote_json=null;
					try {
						lote_json = ow.writeValueAsString(lote);
					} catch (JsonProcessingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					met.saveDataFile("C:\\TECLO_PROCESSING\\", "lote.txt", lote_json);
//					/*********seccion para manejar el scanner individualmente***************/
					
					
					if(continuar) {
						scannerService.scan(lote, consecutivo+1, false, null);
						listenFiles();
						
//						setLista(); 
						boolean isSaved = persistenciaLote();
						if(!isSaved) {
//							JOptionPane.showMessageDialog(null, "¡No se puede establecer conexion intentelo mas tarde!");
						}else {
							disableFields();
							lote.setNumeroTotalImagenes(countNumberOfImages());
//							JOptionPane.showMessageDialog(null, "¡Se han cargado las imágenes satisfactoriamente!");
						}
					}else {
						JOptionPane.showMessageDialog(null, "no se hace nada");
					}
					
				}
				
			}
		});
		btn_digitalizar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btn_digitalizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btn_digitalizar.setLayout(null);
		
		JLabel label_13 = new JLabel("");
		label_13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(validaCampos()) {
					makeBaseFolders();
//					Date fech = met.convertLongToDate(lote.getFechaCracionLote(), conf.getFormatDateDecode());
					
					String[] folders= {lote.getFechaCracionLote(),lote.getNombreLote()};
					try {
						
						met.makeFolders(conf.getPath(), folders);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					lote.getConfiguracionEscaner().setIdConfiguracionEscaner(listconfifEscaner.get(currentConfiguration).getIdConfiguracionEscaner());
					lote.setConfiguracionEscaner(listconfifEscaner.get(currentConfiguration));
					
					/*********seccion para manejar el scanner individualmente***************/
					
					int consecutivo = getNextNumberOfImage();
					boolean continuar=true;
//					if(consecutivo==0&&countNumberOfImages()>0) {
//						int confirm = JOptionPane.showConfirmDialog(null,"La última imágen no cumple con la nomenclatura apropiada, ¿Desea continuar con la digitalización a partir de la numeracion: "+(countNumberOfImages()+1)+"?");
//						if(confirm==JOptionPane.YES_OPTION) {
//							consecutivo=countNumberOfImages();
//						}else {
//							continuar=false;
//						}
//					}
					
					lote.setNextNumber(consecutivo+1);
					
					ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
					String lote_json=null;
					try {
						lote_json = ow.writeValueAsString(lote);
					} catch (JsonProcessingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					met.saveDataFile("C:\\TECLO_PROCESSING\\", "lote.txt", lote_json);
//					/*********seccion para manejar el scanner individualmente***************/
					
					
					if(continuar) { 
						scannerService.scan(lote, consecutivo+1, false, null);
						listenFiles();
						
//						setLista(); 
						boolean isSaved = persistenciaLote();
						if(!isSaved) {
//							JOptionPane.showMessageDialog(null, "¡No se puede establecer conexion intentelo mas tarde!");
						}else {
							disableFields();
							lote.setNumeroTotalImagenes(countNumberOfImages());
//							JOptionPane.showMessageDialog(null, "¡Se han cargado las imágenes satisfactoriamente!");
						}
					}else {
						JOptionPane.showMessageDialog(null, "no se hace nada");
					}
					
					
					
				}
			}
		});
		label_13.setToolTipText("Digitalizar con escáner");
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Scanner_35px.png")));
		label_13.setBounds(0, 0, 142, 51);
		btn_digitalizar.add(label_13);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBounds(0, 0, 351, 24);
		panel_4.add(panel_13);
		panel_13.setBackground(new Color(211, 26, 43));
		
		JLabel lblNewLabel_1 = new JLabel("Configuración del Escaner");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setForeground(Color.WHITE);
		panel_13.add(lblNewLabel_1);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.WHITE);
		panel_8.setBounds(10, 156, 103, 50);
		panel_4.add(panel_8);
		panel_8.setLayout(null);
		lblFormato.setBounds(0, 25, 103, 18);
		panel_8.add(lblFormato);
		
		
		lblFormato.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDpi.setBounds(0, 0, 103, 18);
		panel_8.add(lblDpi);
		
		
		lblDpi.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.WHITE);
		panel_9.setBounds(127, 156, 214, 50);
		panel_4.add(panel_9);
		panel_9.setLayout(null);
		txt_formato.setBounds(0, 25, 103, 18);
		panel_9.add(txt_formato);
		
		
		txt_formato.setForeground(new Color(0, 51, 0));
		txt_formato.setHorizontalAlignment(SwingConstants.LEFT);
		txt_resolucion.setBounds(0, 0, 103, 18);
		panel_9.add(txt_resolucion);
		
		
		txt_resolucion.setForeground(new Color(0, 51, 0));
		txt_resolucion.setHorizontalAlignment(SwingConstants.LEFT);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBackground(Color.WHITE);
		panel_15.setBounds(10, 70, 103, 43);
		panel_4.add(panel_15);
		panel_15.setLayout(null);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setBackground(Color.WHITE);
		lblMarca.setBounds(0, 0, 103, 18);
		panel_15.add(lblMarca);
		lblMarca.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setBounds(0, 25, 103, 18);
		panel_15.add(lblModelo);
		lblModelo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JPanel panel_17 = new JPanel();
		panel_17.setBackground(Color.WHITE);
		panel_17.setBounds(127, 70, 214, 43);
		panel_4.add(panel_17);
		panel_17.setLayout(null);
		txt_marca.setBounds(0, 0, 94, 20);
		panel_17.add(txt_marca);
		
		
		txt_marca.setForeground(new Color(0, 51, 0));
		txt_marca.setHorizontalAlignment(SwingConstants.LEFT);
		txt_modelo.setBounds(0, 21, 153, 22);
		panel_17.add(txt_modelo);
		
		
		txt_modelo.setForeground(new Color(0, 51, 0));
		txt_modelo.setHorizontalAlignment(SwingConstants.LEFT);
		
		JPanel panel_18 = new JPanel();
		panel_18.setBackground(Color.WHITE);
		panel_18.setBounds(10, 113, 103, 43);
		panel_4.add(panel_18);
		panel_18.setLayout(null);
		
		JLabel lblLadosDeImgen = new JLabel("Lados de imágen:");
		lblLadosDeImgen.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLadosDeImgen.setBounds(0, 25, 103, 18);
		panel_18.add(lblLadosDeImgen);
		lblLadosDeImgen.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblAnchoImg = new JLabel("Papel:");
		lblAnchoImg.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAnchoImg.setBounds(0, 2, 103, 18);
		panel_18.add(lblAnchoImg);
		lblAnchoImg.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JPanel panel_20 = new JPanel();
		panel_20.setBackground(Color.WHITE);
		panel_20.setBounds(127, 113, 214, 43);
		panel_4.add(panel_20);
		panel_20.setLayout(null);
		txt_ladospapel.setBounds(0, 24, 214, 19);
		panel_20.add(txt_ladospapel);
		
		
		txt_ladospapel.setForeground(new Color(0, 51, 0));
		txt_ladospapel.setHorizontalAlignment(SwingConstants.LEFT);
		txt_tipopapel.setBounds(0, 0, 214, 19);
		panel_20.add(txt_tipopapel);
		
		
		txt_tipopapel.setForeground(new Color(0, 51, 0));
		txt_tipopapel.setHorizontalAlignment(SwingConstants.LEFT);
		
		JPanel panel_wizard = new JPanel();
		panel_wizard.setBounds(395, 53, 606, 74);
		panel_contenedorMain.add(panel_wizard);
		panel_wizard.setLayout(null);
		panel_wizard.setBackground(new java.awt.Color(236, 240, 245));
		
		JLabel label_14 = new JLabel("");       
		label_14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/wizard.png")));
		label_14.setBounds(43, 0, 524, 74);
		panel_wizard.add(label_14);
		contentPane.setLayout(gl_contentPane);
		
		int min = 1;
	    int max = 31;
	    int step = 1;
	    int initValue = 1;
	    SpinnerModel model = new SpinnerNumberModel(initValue, min, max, step);
	    spinner_1.setModel(model);
	    JComponent editor = new JSpinner.NumberEditor(spinner_1, "00");
		spinner_1.setEditor(editor);
		
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.getTime();
	      int day = calendar.get(Calendar.DATE);
		new Date();
		spinner_1.setValue(day);
		
		combo_configuracion.setBounds(119, 104, 223, 20);
		panel_10.add(combo_configuracion);
		
		JLabel label_15 = new JLabel("Configuración:");
		label_15.setHorizontalAlignment(SwingConstants.RIGHT);
		label_15.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_15.setBounds(11, 98, 103, 31);
		panel_10.add(label_15);
		
		JLabel labelFolioInicial = new JLabel("Folio Inicial:");
		labelFolioInicial.setHorizontalAlignment(SwingConstants.RIGHT);
		labelFolioInicial.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelFolioInicial.setBounds(1, 162, 112, 28);
		panel_10.add(labelFolioInicial);
		
		
		spinnerFolioInicial.setBounds(119, 165, 52, 25);
		panel_10.add(spinnerFolioInicial);
		

		
		JLabel lblFolioFinal = new JLabel("Folio Final:");
		lblFolioFinal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFolioFinal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFolioFinal.setBounds(201, 162, 75, 28);
		panel_10.add(lblFolioFinal);
		
		
		spinnerFolioFinal.setBounds(289, 165, 52, 25);
		panel_10.add(spinnerFolioFinal);
		combo_configuracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setConfigurationData();
			}
		});

		btn_pausarlote.setVisible(false);
		btn_guardarLote.setVisible(false);
		
		//Metodos al iniciar la aplicación
		showImageOptions(false);
//		PlantillaVO plantilla = restclientservice.getPlantilla();
		configuracionRestclientservice = new ConfiguracionRestClientService();
		this.listconfifEscaner = configuracionRestclientservice.buscarConfigEscaners(conf);
		
		this.conf = conf; 
		lote.setEstatusProceso(new EstatusProcesoVO());
		lote.setConfiguracionEscaner(new ConfiguracionEscanerVO());
		lote.getEstatusProceso().setIdEstatusProceso(0L);//Se agrega aqui para el control de inserts y updates
		
		new ArrayList<String>();
		for(int i=0;i<listconfifEscaner.size();i++) {
			combo_configuracion.addItem(listconfifEscaner.get(i).getNombreConfiguracionEscaner());
		}
		
//		ConfigScannerVO configscann= configscann_list.get(0);
//		System.out.println("id plantilla: "+plantilla.getId());
//		System.out.println("nomenclatura: "+configscann.getNomenclatura());
//		setConfigurationData(configscann);
		
		//Fin Metodos al iniciar la aplicacion
		
		/***********************EVENTOS***************************/
		
		
		
		combo_metodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int value=combo_metodo.getSelectedIndex();
				if(value==1) {
					currentMetDigitalizacion=ConfiguracionEnum.metDigitalizacionExterna.getConstante();
					btn_explorarFolder.setVisible(true);
					panel_3.setVisible(true);
					text_path.setVisible(true);
				}
				if(value==0) {
					currentMetDigitalizacion=ConfiguracionEnum.metDigitalizacionDirecta.getConstante();
					btn_explorarFolder.setVisible(false);
					panel_3.setVisible(false);
					text_path.setVisible(false);
					btn_cargarImage.setVisible(false);
				}
			}
		});
		
		btn_explorarFolder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				jfc.setDialogTitle("Selecciona la carpeta de lotes a cargar: ");
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				int returnValue = jfc.showSaveDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					if (jfc.getSelectedFile().isDirectory()) {
						btn_cargarImage.setVisible(true);
						text_path.setVisible(true);
						System.out.println("You selected the directory: " + jfc.getSelectedFile());					
						text_path.setText(""+jfc.getSelectedFile());
						text_path.setToolTipText(""+jfc.getSelectedFile());
					}
				}
			}
		});
		
		
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
	
	
	/***********************METODOS***************************/
	 public void setTextTotal(String total){
		text_totalimg.setText(total);		
	 }
	
	 public void setLista(){
		 
		 cleanModel();	 
		 if(isNotEmptyName(lote.getRutaAlmacenamiento())) {
			 List<String> images = met.getImages(lote.getRutaAlmacenamiento(), extensionesvalidas, true);		       
		        for(int i=0; i<images.size(); i++){
//		            System.out.println("imageeen: "+images.get(i)); 
		            dlm.addElement(images.get(i));            
		            list.setModel(dlm);
		        }
		        setTextTotal(images.size()+"");
		 }else {
			 System.out.println("Error, Directorio no definido");
		 }      
	 }
	 
	 public void cleanModel(){
	        ((DefaultListModel<String>)list.getModel()).clear();
	 }
	 
//	 public void escanea(String path, String name, ConfigScannerVO configscann) {
//		 scannerservice.scan(path, "P######A", configscann);	
//		 setLista();
//	 }
	 
	 public void changeToConsultaLotes() {
		 System.out.println("vas a cambiar a consulta lotes");
		 ConsultaLotesFrame frame = new ConsultaLotesFrame();
		 frame.setVisible(true);	 
		 this.dispose();
	 }
	 
	 public void changeToValidacion() {	 
		 ValidacionFrame validacion = new ValidacionFrame(lote, conf, usuario);
		 validacion.setVisible(true); 
		 this.dispose();
	 }
	 
	 public boolean searchLoteExistente(String nombreLote) {
		 for(int i=0;i<lotesAll.size();i++) {
			 if(lotesAll.get(i).getNombreLote().toUpperCase().equals(nombreLote.toUpperCase())) {
				 //Si existe regresamos un false para cancelar la operacion
				 return false;
			 }
		 }
		 return true;
	 }
	 
	 public boolean validaCampos() {
		 boolean isValid=true;
		 
		 this.folioInicial = ((Integer)spinnerFolioInicial.getValue()).longValue();
		 this.folioFinal = ((Integer) spinnerFolioFinal.getValue()).longValue();
		 
		 if (this.folioInicial > this.folioFinal)
			 return false;
		
		 lote.setNuFolioInicial(this.folioInicial);
		 lote.setNuFolioFinal(this.folioFinal);
		 
		 NumberFormat formatter = new DecimalFormat("00");
		 String dia = formatter.format(spinner_1.getValue());
		 String mes = formatter.format(monthChooser.getMonth()+1);
		 String currentDate = dia+"-"+mes+"-"+yearChooser.getYear();
		 
		 if(isNotEmptyName(txt_nombreLote.getText())&&isNotEmptyName(currentDate)&&isNotEmptyName(conf.getPath())) {
			 String currentName=txt_nombreLote.getText().trim();
			 if(!searchLoteExistente(currentName)) {
				 JOptionPane.showMessageDialog(null, "El lote que intentas crear ya existe.");
				 return false;
			 }
			 
			 //Aqui hay que validar la fecha
			 boolean isvaliddate=Methods.isDateValid(currentDate,conf.getFormatDateDecode());
			 if(!isvaliddate) {
				 JOptionPane.showMessageDialog(null, "La fecha no es correcta");
				 isValid=false;
			 }else {
				 lote.setRutaAlmacenamiento(conf.getPath()+""+currentDate+"\\"+currentName+"\\");
				 lote.setNombreLote(currentName);
				 
				 lote.setFechaCracionLote(currentDate);	
				 System.out.println("met configuracion: "+listconfifEscaner.get(currentConfiguration).getIdConfiguracionEscaner());
				 if(currentMetDigitalizacion==0) {
					 currentMetDigitalizacion=1; 
				 }
				 lote.setMetDigitalizacion(currentMetDigitalizacion);
				 lote.getConfiguracionEscaner().setIdConfiguracionEscaner(listconfifEscaner.get(currentConfiguration).getIdConfiguracionEscaner());
			}		 
			 
		 }else {
			 if(!isNotEmptyName(conf.getPath())) {
				 JOptionPane.showMessageDialog(null, "Error, El directorio base de archivos no se encuentra");
			 }else {
				 JOptionPane.showMessageDialog(null, "Todos los campos son requeridos");
			 }
			 
			 isValid=false;
		 }
		 return isValid;
	 }
	 
	 public void setImageFile(String path, String fileName){
		 txt_imagen.setIcon(new javax.swing.ImageIcon(""));
	     try {
		 myPicture = ImageIO.read(new File(path+fileName));
		 txt_imagen.setIcon(new javax.swing.ImageIcon(met.ScaleImage(myPicture,txt_imagen.getWidth(),1900)));
	     } catch (IOException e) {
	    	 // TODO Auto-generated catch block
	    	 e.printStackTrace();
	     }               
	 }
	 
	 public void showImageOptions(boolean valor) {
		 btn_rotate.setVisible(valor);
		 btn_delete.setVisible(valor);
		 btn_addImage.setVisible(valor);
	 }
	 
	 public boolean isNotEmptyName(String variable) {
		 boolean hasValue=true;
		 if(variable.equals(null)||variable.trim().equals("")) {
			 hasValue=false;
		 }
		 return hasValue;
	 }
	 
	 public void disableFields() {
		 spinnerFolioInicial.setEnabled(false);
		 spinnerFolioFinal.setEnabled(false);
		 txt_nombreLote.setEnabled(false);
		 combo_configuracion.setEnabled(false);
		 combo_metodo.setEnabled(false);
		 monthChooser.setEnabled(false);
		 yearChooser.setEnabled(false);
		 spinner_1.setEnabled(false);
//		 btn_explorarFolder.setVisible(false);
//		 btn_cargarImage.setVisible(false);
	 }
	 
	 public void setConfigurationData() {
		 cleanConfigurationData();
		 this.currentConfiguration=combo_configuracion.getSelectedIndex();
		 System.out.println("este index de configuracion: "+currentConfiguration);
		 combo_configuracion.setSelectedIndex(currentConfiguration);
//		 combo_metodo.setSelectedIndex(currentConfiguration);
		 txt_nombreconfig.setText(listconfifEscaner.get(currentConfiguration).getNombreConfiguracionEscaner());
		 txt_marca.setText(listconfifEscaner.get(currentConfiguration).getEscaner().getMarca());
		 txt_modelo.setText(listconfifEscaner.get(currentConfiguration).getEscaner().getModelo());
		 txt_resolucion.setText(listconfifEscaner.get(currentConfiguration).getResolucion().getNombre());
		 txt_formato.setText(listconfifEscaner.get(currentConfiguration).getTipoArchivos().getNombre());
		 txt_tipopapel.setText(listconfifEscaner.get(currentConfiguration).getTamanioPapael().getNombre());
		 txt_ladospapel.setText(listconfifEscaner.get(currentConfiguration).getSuministroPapel().getNombre());
		 
	 }
	 
	 public void cleanConfigurationData() {
		 txt_nombreconfig.setText("");
		 txt_marca.setText("");
		 txt_modelo.setText("");
		 txt_resolucion.setText("");
		 txt_formato.setText("");
		 txt_tipopapel.setText("");
		 txt_ladospapel.setText("");
	 }
	 
	 public int countNumberOfImages(){
		 List<String> listImages = met.getImages(lote.getRutaAlmacenamiento(), extensionesvalidas, true);
		 return listImages.size();
	 }
	 
	 public int getNextNumberOfImage(){
		 
		 List<String> listImages = met.getImages(lote.getRutaAlmacenamiento(), extensionesvalidas, true);
		 String nomenclatura=(lote.getConfiguracionEscaner().getNombreNomenclatura()).toLowerCase();
		 char caracter = '#';
		 int total=0;
		 if(listImages.size()>0) {
			 for(int i=listImages.size()-1;i>=0;i--) {
				 if(met.validaNombreImagenCorrespondaNomenclatura(nomenclatura, listImages.get(i).toLowerCase(), caracter) ) {
					 total= met.retornaNumeroDeImagenRespectoANomenclatura(nomenclatura, listImages.get(i), caracter);
					 break;
				 }
			 }
		 }
		  
		 return total;
	 }
	 
	 public boolean persistenciaLote() {
		 
		 if(!validaCampos())
			 return false;
		 
		 if(lote.getEstatusProceso().getIdEstatusProceso()== (int) (long) ConfiguracionEnum.loteNoCreado.getConstante()) {
			System.out.println("Se crea el lote");
			lote.getEstatusProceso().setIdEstatusProceso(1L);//
			lote.setNumeroTotalImagenes(countimages);
			
			
			PersistirLoteLoteVO lotepersis = new PersistirLoteLoteVO();
			lotepersis.setFechaCracionLote(Methods.convertDateToLong(lote.getFechaCracionLote(), conf.getFormatDateDecode()));
			lotepersis.setIdConfiguracionEscaner(lote.getConfiguracionEscaner().getIdConfiguracionEscaner());
			lotepersis.setIdEtatusProceso(lote.getEstatusProceso().getIdEstatusProceso());
			lotepersis.setIdLote(lote.getIdLote());
			lotepersis.setMetDigitalizacion(lote.getMetDigitalizacion());
			lotepersis.setNombreLote(lote.getNombreLote());
			lotepersis.setNumeroTotalAceptadas(0);
			lotepersis.setNumeroTotalImagenes(lote.getNumeroTotalImagenes());
			lotepersis.setNumeroTotalRechazadas(0);
			lotepersis.setRutaAlmacenamiento(lote.getRutaAlmacenamiento());
			
			lotepersis.setNuFolioInicial(this.folioInicial);
			lotepersis.setNuFolioFinal(this.folioFinal);
////			String newDate=met.convertDateToString(configgeneral.getLote().getFechaCracionLote(), conf.formatDateDecode);
//			lote.setFechaCracionLote(configgeneral.getLote().getFechaCracionLote());
			
			BitacoraVO bitacoraVO = new BitacoraVO();
			
			if (this.usuario == null) {
				this.usuario = new UsuarioVO();
				this.usuario.setUsuarioId((long)2);
				this.usuario.setUsuarioNombre("dperez");
			}
			
			bitacoraVO.setTabla(ParametrosBitacoraEnum.TDP_BITACORA_CAMBIOS.getParametro());
			bitacoraVO.setComponenteId(2L);
			bitacoraVO.setConceptoId(2L);
			bitacoraVO.setValorOriginal(null);
			bitacoraVO.setValorFinal("LOTE CREADO");
			bitacoraVO.setModificadoPor(this.usuario.getUsuarioId());
			//bitacoraVO.setIdRegistro(lote.getIdLote()+"");
			bitacoraVO.setRegistroDescripcion(lote.getNombreLote());
			bitacoraVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			
			lotesRestclientservice = new LotesRestClientService();
			lote = lotesRestclientservice.guardarLote(lotepersis, bitacoraVO, conf);
			if(lote==null) {
				lote.getEstatusProceso().setIdEstatusProceso(0L);//
				System.out.println("el lote se hizo nulo en guardar");
				return false;
			}else {//Si el lote se guardó correctamente entonces guardamos en bitácora
				

//				
//				bitacoraRestClientService = new BitacoraRestClientService();
//				boolean result=bitacoraRestClientService.guardarBitacora(bitacoraVO, conf);
			}
			btn_pausarlote.setVisible(true);
			btn_guardarLote.setVisible(true);
			return true;
			
		 }
		 if(lote.getEstatusProceso().getIdEstatusProceso() == (int) (long) ConfiguracionEnum.actualizarLote.getConstante()){
			 
			 	BitacoraVO bitacoraVO = new BitacoraVO();
				bitacoraVO.setTabla(ParametrosBitacoraEnum.TDP_BITACORA_CAMBIOS.getParametro());
				bitacoraVO.setComponenteId(2L);
				bitacoraVO.setConceptoId(2L);
				bitacoraVO.setValorOriginal("LOTE CREADO");
				bitacoraVO.setValorFinal("LOTE EN PAUSA");
				Long valorId = usuario.getUsuarioId() == null ? 2 : usuario.getUsuarioId();
				bitacoraVO.setModificadoPor(valorId);
				bitacoraVO.setIdRegistro(lote.getIdLote()+"");
				bitacoraVO.setRegistroDescripcion(lote.getNombreLote());
				bitacoraVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				
				bitacoraVO.setNuFolioInicial(lote.getNuFolioInicial());
				bitacoraVO.setNuFolioFinal(lote.getNuFolioFinal());
				
//				bitacoraRestClientService = new BitacoraRestClientService();
//				boolean result=bitacoraRestClientService.guardarBitacora(bitacoraVO, conf);
			 
			 if(updateLote(1L,bitacoraVO)) {
				
					return true;
			 }
		 }
		 System.out.println("algo fallo y va retirnar falso en persistencia");
		return false;
	 }
	 
	 public boolean updateLote(Long idEstatus, BitacoraVO bitacoraVO) {
		 System.out.println("Se actualiza el lote");
		 lotesRestclientservice = new LotesRestClientService();
		 boolean isSaved = lotesRestclientservice.actualizarEstTolImgLote(lote.getIdLote(),countimages,idEstatus, conf, bitacoraVO);
		 if(!isSaved) {
			 JOptionPane.showMessageDialog(null, "¡No se pudo establecer conexion intentelo mas tarde");
			 return false;
		 }
		 return true;
	 }
	 
	 int count = 0;
	 int countAnterior=0;
	 public void listenFiles() {
		/********************/
		new Thread(new Runnable(){
		@Override
		public void run() {
			while(count!=-10){
				List<String> listImages=met.getImages(lote.getRutaAlmacenamiento(), extensionesvalidas, true);
				count=listImages.size();
				if(count!=countAnterior){
					countAnterior=count;
					setLista();
				}
			}
		}
		}).start();
		/****************/	
	}
	 
	 public void minimizeWin() {
			this.setState(ConsultaLotesFrame.ICONIFIED);
		}
	 
	 public void makeBaseFolders() {
			//Creamos carpetas de trabajo
			String path="C:\\";
			String[] folders= {"TECLO_PROCESSING","lib"};
			for(int i=0;i<folders.length;i++) {
				path=path+folders[i]+"\\";
				File f = new File(path);
				if (f.exists() && f.isDirectory()) {

				}else {
					f.mkdir();
				}
			}
			path="C:\\";
			String[] folders2= {"SistemaDigitalizacion"};
			for(int i=0;i<folders2.length;i++) {
				path=path+folders2[i]+"\\";
				File f = new File(path);
				if (f.exists() && f.isDirectory()) {

				}else {
					f.mkdir();
				}
			}
			
			/*
			 * Resource resource = applicationContext.getResource("classpath:xyz.xml");
	InputStream is = resource.getInputStream();
			 * */
			//Creamos todos los archivos que va a usar el Scanner
			
//			met.unzip();
			File f = new File("C:\\TECLO_PROCESSING\\lib\\FiScn.dll");
			if(!f.exists()||!f.isFile()) {
				met.copyFileToPath(System.getProperty("user.dir")+"\\src\\main\\resources\\zipScan\\", "C:\\TECLO_PROCESSING\\lib\\", "FiScn.dll", "FiScn.dll");
			}
		}
	 
	 public void changeIcon(){
			this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/Isotipo_DPIv2r2.png")));
		}
}
