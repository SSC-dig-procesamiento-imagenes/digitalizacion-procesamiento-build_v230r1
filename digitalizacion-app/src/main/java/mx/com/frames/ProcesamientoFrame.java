package mx.com.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialException;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import java.awt.event.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
//import com.sun.glass.ui.Timer;

import mx.com.app.services.BitacoraRestClientService;
import mx.com.app.services.ConfiguracionRestClientService;
import mx.com.app.services.LotesRestClientService;
import mx.com.app.services.OmrService;
import mx.com.app.services.PlantillaRestService;
import mx.com.teclo.base.excel.DataExcel;
import mx.com.teclo.base.excel.ExcelWriter;
import mx.com.teclo.base.util.enumerados.ConfiguracionEnum;
import mx.com.teclo.base.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclo.base.util.methods.Methods;
import mx.com.teclo.base.vo.bitacora.BitacoraVO;
import mx.com.teclo.base.vo.configuracion.ConfiguracionVO;
import mx.com.teclo.base.vo.images.ImagenBlobPersistenciaVO;
import mx.com.teclo.base.vo.images.ImagenPersistenciaVO;
import mx.com.teclo.base.vo.lotes.LoteVO;
import mx.com.teclo.base.vo.plantilla.PlantillaCompletaVO;
import mx.com.teclo.base.vo.plantilla.PlantillaVO;
import mx.com.teclo.base.vo.usuario.UsuarioVO;

import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.SwingConstants;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JList;
import mx.com.app.frames.reanudar.CustomPanel2;
import javax.swing.border.BevelBorder;
import java.awt.Cursor;

public class ProcesamientoFrame extends JFrame {

	LotesRestClientService lotesRestclientservice;
	ConfiguracionRestClientService configuracionRestclientservice;
	List<LoteVO> lotes;
	
	OmrService omrservice = new OmrService();
	PlantillaVO plantilla = null;
	
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
	CustomPanel2 jp_progress = new CustomPanel2();
	
	JLabel txt_totalimgs = new JLabel("0");
	JLabel txt_totalavance = new JLabel("0");
	JLabel label_16 = new JLabel("");
	JLabel lblIniciar = new JLabel("Iniciar");
	JPanel btn_excelDownload = new JPanel();
	JLabel lblTranscurrido = new JLabel("Hora en transcurso");
	
	JList<String> list = new JList<String>();
	DefaultListModel<String> dlm = new DefaultListModel<String>();
	JPanel btn_iniciarprocessing = new JPanel();
	ExcelWriter creaExcel = new ExcelWriter();
	
	JPanel panel_22 = new JPanel();
	
	ConfiguracionVO conf;
	LoteVO lote;
	Methods met = new Methods();
	
//	int posX=0,posY=0;
	int totalImages=0;
	int totalImagesEnviadas=0;	
	int totalImagesNoEnviadas=0;	
	
	List<String> extensionesvalidas = new ArrayList<String>();

	JLabel ini_time = new JLabel("00:00:00");
	JLabel fini_time = new JLabel("00:00:00");
	Thread t=null;  
	int hours=0, minutes=0, seconds=0;  
	String timeString = "";
	boolean startWatch=true;
	boolean startWatch2=true;
	
	boolean lastConnectionBroken=false;
	
	LotesRestClientService loteservice = new LotesRestClientService();
	BitacoraRestClientService bitacoraRestClientService;
	
	UsuarioVO usuario = new UsuarioVO();
	
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
	public ProcesamientoFrame(final LoteVO lote, final ConfiguracionVO conf, final UsuarioVO usuario) {
		
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
		
		changeIcon();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(-6, 0, 1381, 734);
		this.setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_contenedorMain = new JPanel();
		panel_contenedorMain.setBounds(0, 0, 1381, 734);
		contentPane.add(panel_contenedorMain);
		panel_contenedorMain.setBackground(new java.awt.Color(236, 240, 245));
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
		label_3.setBounds(133, 0, 471, 53);
		panel_footer.add(label_3);
		
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
		btn_menuProcesamiento.setLayout(null);
		btn_menuProcesamiento.setBackground(new Color(211, 26, 43));
		btn_menuProcesamiento.setBounds(0, 74, 122, 75);
		btn_menuProcesamiento.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btn_menuProcesamiento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panel_menu.add(btn_menuProcesamiento);
		
		JLabel label_6 = new JLabel("");
		label_6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Gears_36px.png")));
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(37, 11, 46, 36);
		btn_menuProcesamiento.add(label_6);
		
		JLabel lblLote = new JLabel("Lote");
		lblLote.setHorizontalAlignment(SwingConstants.CENTER);
		lblLote.setForeground(Color.WHITE);
		lblLote.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLote.setBounds(0, 50, 122, 25);
		btn_menuProcesamiento.add(lblLote);
		
		JPanel panel_header = new JPanel();
		panel_header.setLayout(null);
		panel_header.setBackground(Color.WHITE);
		panel_header.setBounds(0, 0, 1381, 54);
		panel_contenedorMain.add(panel_header);
		panel_header.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		
		JLabel label = new JLabel("");
		label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logoApp.png")));
		label.setBounds(117, 0, 133, 44);
		panel_header.add(label);
		
		JPanel btn_closeWindow = new JPanel();
		btn_closeWindow.setToolTipText("Cerrar ventana");
		btn_closeWindow.setVisible(true);
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
		btn_closeWindow.setBounds(1296, 1, 57, 43);
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
		label_1.setBounds(0, 0, 50, 43);
		btn_closeWindow.add(label_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(30, 0, 57, 43);
		panel_header.add(panel_2);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Menu_35px.png")));
		label_2.setBounds(10, 0, 35, 44);
		panel_2.add(label_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(360, 0, 703, 44);
		panel_header.add(panel_3);
		
		JLabel lblAplicacinDeDigitalizacin = new JLabel("Aplicación de Digitalización y Procesamiento de Imágenes");
		lblAplicacinDeDigitalizacin.setHorizontalAlignment(SwingConstants.CENTER);
		lblAplicacinDeDigitalizacin.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		lblAplicacinDeDigitalizacin.setBounds(10, 0, 683, 44);
		panel_3.add(lblAplicacinDeDigitalizacin);
		
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
		btn_minimize.setBounds(1247, 0, 51, 44);
		panel_header.add(btn_minimize);
		
		JLabel label_11 = new JLabel("");
		label_11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				minimizeWin();
			}
		});
		label_11.setToolTipText("Minimizar ventana");
		label_11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Minus_50px.png")));
		label_11.setBounds(0, 0, 50, 44);
		btn_minimize.add(label_11);
		
		JPanel panel_contenedor = new JPanel();
		panel_contenedor.setBackground(Color.WHITE);
		panel_contenedor.setBounds(132, 127, 1218, 543);
		panel_contenedor.setBorder(new LineBorder(Color.GRAY));
		panel_contenedorMain.add(panel_contenedor);
		panel_contenedor.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.GRAY));
		panel.setBounds(326, 35, 320, 442);
		panel.setBackground(Color.WHITE);
		panel_contenedor.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(SystemColor.inactiveCaptionBorder);
		panel_1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		panel_1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		panel_1.setBounds(20, 292, 272, 38);
		panel.add(panel_1);
		
		JLabel lblTotalDeImgenes = new JLabel("Total de imágenes");
		lblTotalDeImgenes.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalDeImgenes.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotalDeImgenes.setBounds(10, 0, 155, 43);
		panel_1.add(lblTotalDeImgenes);
		
		
		txt_totalimgs.setForeground(new Color(47, 79, 79));
		txt_totalimgs.setHorizontalAlignment(SwingConstants.CENTER);
		txt_totalimgs.setFont(new Font("Tahoma", Font.BOLD, 15));
		txt_totalimgs.setBounds(161, 0, 64, 43);
		panel_1.add(txt_totalimgs);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(SystemColor.inactiveCaptionBorder);
		panel_4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		panel_4.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		panel_4.setBounds(20, 341, 272, 38);
		panel.add(panel_4);
		
		JLabel lblProcesadas = new JLabel("Procesadas");
		lblProcesadas.setHorizontalAlignment(SwingConstants.LEFT);
		lblProcesadas.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblProcesadas.setBounds(10, 0, 86, 36);
		panel_4.add(lblProcesadas);
		
		JLabel label_14 = new JLabel("");
		label_14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Checked_25px.png")));
		label_14.setBounds(235, 0, 30, 36);
		panel_4.add(label_14);
		
		
		txt_totalavance.setHorizontalAlignment(SwingConstants.CENTER);
		txt_totalavance.setForeground(new Color(47, 79, 79));
		txt_totalavance.setFont(new Font("Tahoma", Font.BOLD, 15));
		txt_totalavance.setBounds(106, 0, 119, 36);
		panel_4.add(txt_totalavance);
		
		
		btn_iniciarprocessing.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(lote.getEstatusProceso().getIdEstatusProceso()==(int) (long) ConfiguracionEnum.loteProcesado.getConstante()) {
					
				}else {
					btn_iniciarprocessing.setVisible(false);
					JOptionPane.showMessageDialog(null, "El procesamiento puede tomar unos minutos en comenzar, por favor espere.");
					label_16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Process_25px_1.png")));
					lblIniciar.setText("Procesando");
					
					startWatch=false;
					finiTime();
					
					List<String> images = getAllImages();
					String[] images_array = new String[images.size()];
					images_array = images.toArray(images_array);
					
					System.out.println("Comienza el sistema de reconocimiento");  	
//					OmrService omrservice = new OmrService();
//					closeFrame();
					reconocimientoOMR(images_array, lote, conf);
					startWatch2=false;
					
					List<String> imagesFaltantes = getAllImages();
			    	if(imagesFaltantes.size()>0) {
			    		JOptionPane.showMessageDialog(null, "Hay "+imagesFaltantes.size()+" imágenes cuya informaión no ha sido enviada, para reanudar clic en el botón 'Reaundar'");
			    		lblIniciar.setText("Reanudar");
			    		btn_iniciarprocessing.setVisible(true);
			    		
			    		
			    	}else {
			    		label_16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Checked_25px.png")));
			    	
			    		BitacoraVO bitacoraVO = new BitacoraVO();
						
						bitacoraVO.setTabla(ParametrosBitacoraEnum.TDP_BITACORA_CAMBIOS.getParametro());
						bitacoraVO.setComponenteId(2L);
						bitacoraVO.setConceptoId(2L);
						
						
						String valorOriginal="LOTE EN PROCESAMIENTO";
						
						bitacoraVO.setValorOriginal(valorOriginal);
						bitacoraVO.setValorFinal("LOTE PROCESADO");
						Long valorId = usuario.getUsuarioId() == null ? 2 : usuario.getUsuarioId();
						bitacoraVO.setModificadoPor(valorId);
						bitacoraVO.setIdRegistro(lote.getIdLote()+"");
						bitacoraVO.setRegistroDescripcion(lote.getNombreLote());
						bitacoraVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
						
						bitacoraVO.setNuFolioInicial(lote.getNuFolioInicial());
						bitacoraVO.setNuFolioFinal(lote.getNuFolioFinal());
						bitacoraVO.setNombreLote(lote.getNombreLote());
						
						
			    		if(updateLote(7L, bitacoraVO)){//Actualizamos el estado a procesado							
							
//							bitacoraRestClientService = new BitacoraRestClientService();
//							boolean result=bitacoraRestClientService.guardarBitacora(bitacoraVO, conf);
						}
			    		lblIniciar.setText("Completado!");
			    		btn_iniciarprocessing.setVisible(true);
			    		lblTranscurrido.setText("Hora de término");
			    		lote.getEstatusProceso().setIdEstatusProceso(7L);
			    		btn_excelDownload.setVisible(true);
			    		btn_iniciarprocessing.setBackground(Color.WHITE);
			    		lblIniciar.setForeground(new Color(139, 0, 0));
			    		btn_iniciarprocessing.setBorder(new EmptyBorder(5, 5, 5, 5));
			    		btn_iniciarprocessing.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
			    		panel_22.setVisible(true);
			    		
			    	}
				}
				
				
		    	
//	        	try {
//	        		//OMR
//	        		reconocimientoOMR(images_array, lote, conf);
//					omrservice.procesoReconocimiento(images_array, lote, conf);
//				} catch (SerialException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//	        	openFrame();
	        	
			}
		});
		btn_iniciarprocessing.setLayout(null);
		btn_iniciarprocessing.setBackground(new Color(211, 26, 43));
		btn_iniciarprocessing.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btn_iniciarprocessing.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btn_iniciarprocessing.setBounds(71, 390, 171, 36);
		panel.add(btn_iniciarprocessing);
		
		
		lblIniciar.setForeground(Color.WHITE);
		lblIniciar.setHorizontalAlignment(SwingConstants.CENTER);
		lblIniciar.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIniciar.setBounds(34, 0, 127, 36);
		btn_iniciarprocessing.add(lblIniciar);
		
		
		label_16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Play_25px.png")));
		label_16.setBounds(10, 0, 30, 36);
		btn_iniciarprocessing.add(label_16);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBounds(26, 35, 290, 121);
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_8.setBackground(Color.WHITE);
		panel_contenedor.add(panel_8);
		panel_8.setLayout(null);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(211, 26, 43));
		panel_9.setBounds(0, 0, 290, 25);
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
		panel_10.setBounds(26, 167, 290, 296);
		
		
		panel_10.setLayout(null);
		panel_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_10.setBackground(Color.WHITE);
		panel_contenedor.add(panel_10);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBackground(new Color(211, 26, 43));
		panel_12.setBounds(0, 0, 290, 24);
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
		label_20.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_20.setHorizontalAlignment(SwingConstants.RIGHT);
		label_20.setBounds(0, 25, 103, 18);
		panel_13.add(label_20);
		
		JLabel label_21 = new JLabel("Dpi:");
		label_21.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_21.setHorizontalAlignment(SwingConstants.RIGHT);
		label_21.setBounds(0, 0, 103, 18);
		panel_13.add(label_21);
		
		JPanel panel_14 = new JPanel();
		panel_14.setLayout(null);
		panel_14.setBackground(Color.WHITE);
		panel_14.setBounds(127, 147, 153, 50);
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
		label_24.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_24.setHorizontalAlignment(SwingConstants.RIGHT);
		label_24.setBackground(Color.WHITE);
		label_24.setBounds(0, 28, 103, 18);
		panel_15.add(label_24);
		
		JLabel label_25 = new JLabel("Modelo:");
		label_25.setFont(new Font("Tahoma", Font.BOLD, 11));
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
		panel_16.setBounds(127, 35, 153, 69);
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
		label_30.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_30.setBounds(-11, 25, 114, 18);
		panel_17.add(label_30);
		
		JLabel label_31 = new JLabel("Papel:");
		label_31.setHorizontalAlignment(SwingConstants.RIGHT);
		label_31.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_31.setBounds(0, 2, 103, 18);
		panel_17.add(label_31);
		
		JPanel panel_18 = new JPanel();
		panel_18.setLayout(null);
		panel_18.setBackground(Color.WHITE);
		panel_18.setBounds(127, 104, 153, 43);
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
		label_9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/wizard3.png")));
		label_9.setBounds(43, 0, 524, 74);
		panel_wizard.add(label_9);
		
		btn_excelDownload.setVisible(false);
		
		//Metodos al iniciar la aplicacion
		plantilla=omrservice.getPlantilla(conf);
		 deleteCache();
		setConfigurationData();
		setLoteData();
		List<String> images = getAllImages();
		List<String> imagesEnviadas =getAllImagesEnviadas();
		totalImages=images.size();
		System.out.println("total images enviadas: "+imagesEnviadas);
		if(imagesEnviadas!=null) {
			System.out.println("si hay imagenes");
			totalImagesEnviadas=imagesEnviadas.size();
//			lbl_datosEnviados.setText(totalImagesEnviadas+"");
			totalImages=totalImages+totalImagesEnviadas;
			if ( (totalImagesEnviadas%2)!=0) {//
				lastConnectionBroken=true;
			}	
		}
		int result=(totalImagesEnviadas*100)/totalImages;
		if(result==100) {
			label_16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Checked_25px.png")));
//    		updateLote(7L);//Actualizamos el estado a procesado
    		lblIniciar.setText("Completado!");
    		btn_iniciarprocessing.setVisible(true);
    		lblTranscurrido.setText("Hora de término");
    		lote.getEstatusProceso().setIdEstatusProceso(7L);
    		btn_excelDownload.setVisible(true);
    		btn_iniciarprocessing.setBackground(Color.WHITE);
    		lblIniciar.setForeground(new Color(139, 0, 0));
    		btn_iniciarprocessing.setBorder(new EmptyBorder(5, 5, 5, 5));
    		btn_iniciarprocessing.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		}
		updateGraphics(result);
		txt_totalimgs.setText(totalImages+"");
		txt_totalavance.setText(totalImagesEnviadas+"");
		iniTime();
		
		
		
		jp_progress.setBackground(Color.WHITE);
		jp_progress.setBounds(10, 11, 300, 270);
		panel.add(jp_progress);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(658, 35, 526, 29);
		panel_7.setBorder(new LineBorder(Color.GRAY));
		panel_7.setBackground(new Color(211, 26, 43));
		panel_contenedor.add(panel_7);
		panel_7.setLayout(null);
		
		JLabel lblResultadosDelProcesamiento = new JLabel("Resultados del Procesamiento");
		lblResultadosDelProcesamiento.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultadosDelProcesamiento.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblResultadosDelProcesamiento.setForeground(Color.WHITE);
		lblResultadosDelProcesamiento.setBounds(10, 0, 506, 29);
		panel_7.add(lblResultadosDelProcesamiento);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBounds(658, 63, 526, 472);
		panel_11.setLayout(null);
		panel_11.setBorder(new LineBorder(Color.GRAY));
		panel_contenedor.add(panel_11);
		
		JPanel panel_19 = new JPanel();
		panel_19.setLayout(null);
		panel_19.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_19.setBackground(Color.WHITE);
		panel_19.setBounds(10, 11, 506, 404);
		panel_11.add(panel_19);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 506, 404);
		panel_19.add(scrollPane_1);
		scrollPane_1.setViewportView(list);
		
		JPanel panel_20 = new JPanel();
		panel_20.setLayout(null);
		panel_20.setBounds(56, 426, 36, 38);
		panel_11.add(panel_20);
		
		JLabel label_17 = new JLabel("");
		label_17.setBounds(0, 0, 36, 36);
		panel_20.add(label_17);
		
		JPanel panel_21 = new JPanel();
		panel_21.setLayout(null);
		panel_21.setBounds(102, 426, 36, 38);
		panel_11.add(panel_21);
		
		JLabel label_22 = new JLabel("");
		label_22.setBounds(0, 0, 36, 38);
		panel_21.add(label_22);
		
		
		
		btn_excelDownload.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btn_excelDownload.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				JOptionPane.showMessageDialog(null, "Descarga de excel");
				creaExcel.openExcel(lote.getRutaAlmacenamiento()+"Reporte_Digitalización_"+lote.getNombreLote().toUpperCase()+".xlsx");
			}
		});
		btn_excelDownload.setLayout(null);
		btn_excelDownload.setBounds(10, 426, 43, 38);
		panel_11.add(btn_excelDownload);
		
		JLabel label_23 = new JLabel("");
		label_23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Microsoft_Excel_45px.png")));
		label_23.setBounds(0, 0, 43, 38);
		btn_excelDownload.add(label_23);
		panel_22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		panel_22.setVisible(false);
		panel_22.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lotesRestclientservice = new LotesRestClientService();
				configuracionRestclientservice = new ConfiguracionRestClientService();
				
//				this.conf = configuracionRestclientservice.obtenerConfiguracion(); 
		        lotes = lotesRestclientservice.obtenerTodosLotes(conf,-10L,"","");
		        changeToDigitalizacion();
				
			}
		});
		panel_22.setLayout(null);
		panel_22.setBackground(new Color(211, 26, 43));
		panel_22.setBounds(364, 426, 152, 36);
		panel_11.add(panel_22);
		
		JLabel lblNuevoLote = new JLabel("Nuevo lote");
		lblNuevoLote.setHorizontalAlignment(SwingConstants.LEFT);
		lblNuevoLote.setForeground(Color.WHITE);
		lblNuevoLote.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNuevoLote.setBounds(49, 0, 97, 36);
		panel_22.add(lblNuevoLote);
		
		JLabel label_12 = new JLabel("");
		label_12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Add_File_28px.png")));
		label_12.setBounds(10, 0, 34, 36);
		panel_22.add(label_12);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(Color.GRAY));
		panel_5.setBounds(326, 476, 320, 59);
		panel_contenedor.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel label_10 = new JLabel("");
		label_10.setBounds(10, 25, 27, 30);
		label_10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Clock_27px.png")));
		panel_5.add(label_10);
		
		
		ini_time.setFont(new Font("Tahoma", Font.BOLD, 12));
		ini_time.setBounds(48, 25, 65, 30);
		panel_5.add(ini_time);
		
		
		fini_time.setFont(new Font("Tahoma", Font.BOLD, 12));
		fini_time.setBounds(213, 25, 65, 30);
		panel_5.add(fini_time);
		
		JLabel label_29 = new JLabel("");
		label_29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Clock_27px.png")));
		label_29.setBounds(176, 25, 27, 30);
		panel_5.add(label_29);
		
		JLabel lblInicio = new JLabel("Hora de inicio:");
		lblInicio.setHorizontalAlignment(SwingConstants.LEFT);
		lblInicio.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInicio.setBounds(10, 0, 124, 24);
		panel_5.add(lblInicio);
		
		
		lblTranscurrido.setHorizontalAlignment(SwingConstants.LEFT);
		lblTranscurrido.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTranscurrido.setBounds(176, 0, 134, 24);
		panel_5.add(lblTranscurrido);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(166, 0, 13, 55);
		panel_5.add(separator);
		
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
	
	public List<String> getAllImages(){
		new ArrayList<String>();
		return met.getImages(lote.getRutaAlmacenamiento(), extensionesvalidas, true);
	}
	
	public List<String> getAllImagesEnviadas(){
		File env = new File(lote.getRutaAlmacenamiento()+"\\enviadas\\");
		List<String> imagesall =null;
		if(env.exists()&&env.isDirectory()) {
			imagesall=met.getImages(lote.getRutaAlmacenamiento()+"\\enviadas\\", extensionesvalidas, true);
		}
		return imagesall;
	}
	
	public void changeToConsultaLotes() {
		int confirm = JOptionPane.showConfirmDialog(null, "¿Desea salir del módulo de procesamiento?");
		 if (confirm == JOptionPane.YES_OPTION) {
			 ConsultaLotesFrame frame = new ConsultaLotesFrame();
			 frame.setVisible(true);	 
			 this.dispose();
	     }	 
	 }
	
	public void changeToDigitalizacion() {	 
		
		if(usuario == null) {
			usuario = new UsuarioVO();
			usuario.setUsuarioId((long)2);
			usuario.setUsuarioNombre("dperez");
		}
		
		 DigitalizacionFrame digitalizacion = new DigitalizacionFrame(conf, lotes, usuario);
		 digitalizacion.setVisible(true); 
//		 System.out.println("pathhh: "+System.getProperty("user.dir")+"\\src\\main\\resources\\zipScan\\scan.zip");
		 this.dispose();
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
		 txt_nb_lote.setToolTipText(lote.getNombreLote());
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
//	 public void openFrame() {
////		 setIconPercentage();
//		 List<String> images = getAllImages();
//		 txt_totalimgs.setText(images.size()+"");
//		 txt_totalavance.setText(images.size()+"");
//		 this.setVisible(true);
//	 }
	 
	 //Metodos visuales
	 public void setTotal(String text) {
		 txt_totalimgs.setText(text);
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
	 public void setTotalAvance(String text) {
		 txt_totalavance.setText(text);
	 }
	 
	 public void updateGraphics(int num) {
		 jp_progress.UpdateProgress(num);
         jp_progress.repaint();
		 
	 }
	 
	 //Esta es la parte interesante
	 public void reconocimientoOMR(String[] images, LoteVO lote, ConfiguracionVO config) {
if(lote.getConfiguracionEscaner().getSuministroPapel().getValor()==ConfiguracionEnum.ladoambos.getConstante()) {
	    }
		new ArrayList<ImagenPersistenciaVO>(); 
		
		ImagenPersistenciaVO imagenp;
    	List<ImagenBlobPersistenciaVO> imagenesBl= new ArrayList<ImagenBlobPersistenciaVO>();
    	ImagenBlobPersistenciaVO imagenBl= null;
    	byte[] imagenA=null;
    	int cantidadObjetosRespuesta=0;
        double area=0.0, densidad= 0.0;
    	String dia="", mes="", anio="";
    	Object[] response=null;
    	
    	String matriz="";
    	String respuesta="";
    	float result=0.0f;
    	
    	
    	omrservice.loadNativeLibrary();
//    	PlantillaCompletaVO plantilla=omrservice.getPlantilla2(config);
    	
		long lStartTime = System.nanoTime();
		//Recorremos cada una de las imagenes para extraer sus datos
		int contador=0;
		for (int i = totalImagesEnviadas; i < totalImages; i++) {
			imagenp = new ImagenPersistenciaVO();
        	imagenBl= new ImagenBlobPersistenciaVO();
        	setTextToLogResultados("/**************"+images[contador]+"*********************");
        	
        	imagenp.setNombreImagen(lote.getIdLote()+"_"+images[contador]);//Revisar
        	
        	if(lote.getConfiguracionEscaner().getSuministroPapel().getValor()==ConfiguracionEnum.ladoambos.getConstante()) {
    			if ( (i & 1) == 0 &&!lastConnectionBroken) {
//    				System.out.println("Es una imagen anversa, no se procesa solo se persiste");
            		//Asignamos datos para el blob (Imagen Blob) -- Objeto para Persistencia de Blob
    				
//                    SerialBlob imagenA=met.convertFileToBlob(lote.getRutaAlmacenamiento()+images[i]);   
                    try {
						imagenA = Methods.convertFileContentToBlob(lote.getRutaAlmacenamiento()+images[contador]);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
                    Base64.encodeBase64String(imagenA);
                    imagenBl.setLbImagen(imagenA);
                    imagenBl.setCodigoImagen("A"); 
                    imagenBl.setNombreImagen(lote.getIdLote()+"_"+images[contador]); 
                    imagenesBl.add(imagenBl);  
                    
//                    File f = new File(lote.getRutaAlmacenamiento()+"enviadas\\");
//            		  if (!f.exists() && !f.isDirectory()) {
//            			f.mkdir();
//            		  }
//            		  met.moveToPath(lote.getRutaAlmacenamiento(), lote.getRutaAlmacenamiento()+"enviadas\\", images[contador], images[contador]);
//            		  
            	}//Fin de cara Anverso
    			else {
//    				if(lastConnectionBroken) {
//    					i=i-1;
//    				}
    				lastConnectionBroken=false;
//            		System.out.println("Es una imagen reversa, se procesa y persiste");
            		cantidadObjetosRespuesta=plantilla.getCampos().size();
       	         	area= plantilla.getAlveolo().getArea();
                	densidad= plantilla.getAlveolo().getDensidad();
                	response=omrservice.procesoReconocimiento2(lote, images[contador], cantidadObjetosRespuesta, area, densidad);
                	if(response!=null) {
                		for(int j=0;j<response.length;j++){
                            matriz=(response[j].toString().replace("[", "")).replace("]", "");
                            respuesta = omrservice.extractResults(matriz, plantilla.getCampos().get(j).getOrientaciones_id().intValue(),
                            		plantilla.getCampos().get(j).getPlantillarespuesta());
                                            
                            setTextToLogResultados(plantilla.getCampos().get(j).getNombre()+": "+respuesta);
//                            System.out.println(""+plantilla.getCampos().get(j).getNombre()+": "+respuesta); 
                            
                            /***ASIGNAMOS RESULTADOS DE RECONOCIMIENTO A CAMPOS**/
                            imagenp = omrservice.setValueCampo(plantilla.getCampos().get(j).getNombre(), respuesta, imagenp);
                            
                            if(plantilla.getCampos().get(j).getNombre().equals("DIA")) {
                            	dia=respuesta;
                            }
                            if(plantilla.getCampos().get(j).getNombre().equals("MES")) {
                            	mes=respuesta;
                            }
                            if(plantilla.getCampos().get(j).getNombre().equals("AÑO")) {
                            	anio=respuesta;
                            } 
                            if(plantilla.getCampos().get(j).getNombre().equals("NUMERO DE FOLIO")) {
                            }
                            /***ASIGNAMOS RESULTADOS DE RECONOCIMIENTO A CAMPOS**/   
                         }
                		System.out.println("esta imagen: "+imagenp.toString());
                		/***************************Persistencia de campos********************************/         
                        //Asignamos datos para el blob (Imagen Blob) -- Objeto para Persistencia de Blob
                          imagenBl.setNombreImagen(lote.getIdLote()+"_"+images[contador]);
                          System.out.println(images[contador]);
//                          SerialBlob imagenA=met.convertFileToBlob(lote.getRutaAlmacenamiento()+images[i]); 
                          try {
							imagenA = Methods.convertFileContentToBlob(lote.getRutaAlmacenamiento()+images[contador]);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
                          Base64.encodeBase64String(imagenA);
                          imagenBl.setLbImagen(imagenA);
                          imagenBl.setCodigoImagen("R");  
                          imagenesBl.add(imagenBl);
                          
                       //  Persistencia de Imagen 
                          imagenp.setFechaInfraccion(Methods.convertStringToDate(dia+"-"+mes+"-"+anio, config.getFormatDateDecode()));
                          imagenp.setIdlote(lote.getIdLote());
                          imagenp.setNombreImagen(lote.getIdLote()+"_"+images[contador]);      
                          imagenp.setLbImagenes(imagenesBl);
                          
                          String toExcel=imagenp.getNombreImagen()+","+imagenp.getPlaca()+","+
                          imagenp.getNumeroLicencia()+","+
                          imagenp.getTipoLicencia()+","+
                          imagenp.getLicenciaExpedidaEn()+","+
                          imagenp.getPlacaExpedidaEn()+","+
                          imagenp.getNumeroArticuloInfraccion()+","+
                          imagenp.getNumeroFraccion()+","+
                          imagenp.getNumeroInciso()+","+
                          imagenp.getNumeroParrafo()+","+
                          Methods.convertDateToString(imagenp.getFechaInfraccion(), conf.getFormatDateDecode())+","+
                          imagenp.getPlacaOficial()+","+
                          imagenp.getUtDelegacion()+","+
                          imagenp.getNumeroFolio()+","+
                          imagenp.getIdlote()+";";
                          
//                          datosExcel.add(imagenp);
                          
                          /*****************seccion escribir objetos en json para excel*****************************************/
                          
//                          ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//                          String excel_json=null;
//                          try {
//      						excel_json = ow.writeValueAsString(imagenp);
//	                  	 } catch (JsonProcessingException e1) {
//	      						// TODO Auto-generated catch block
//	      					e1.printStackTrace();
//	                  	 }
                         
//                          if(fexcel.exists()&&fexcel.isFile()) {
                        	  
            				
//                          }
//                	else {
//                        	  met.saveStringToFile(excel_json, lote.getRutaAlmacenamiento()+"enviadas\\excel.txt");
//                          }
                          
                          
      					/**********************************************************/
      					
                          boolean estatus=loteservice.guardarImagen(imagenp, config);
//                          boolean estatus=true;
                          if(!estatus) {
                        	  int confirm =JOptionPane.showConfirmDialog(null,"No se pudo guardar los resultados del procesamiento. ¿Desea cancelar el proceso e intentarlo más tarde?"); 
                        	  if(confirm == JOptionPane.YES_OPTION){
                        		  
                        		  break;
                        	  }else {
                        		  /**aqui json**/
                        		  
                        		  i=i-2;
                        		  contador=contador-2;
                        	  }
//                          
                          }else {
                        	  
                        	  File fexcel = new File(lote.getRutaAlmacenamiento()+"enviadas\\", "excel.txt");
                              if(!fexcel.exists()&&!fexcel.isFile()) {
                            	  File file = new File(lote.getRutaAlmacenamiento()+"excel.txt");
                            	  
                            	//Create the file
                            	try {
    								if (file.createNewFile())
    								{
//    								    System.out.println("File is created!");
    								} else {
//    								    System.out.println("File already exists.");
    								}
    							} catch (IOException e) {
    								// TODO Auto-generated catch block
    								e.printStackTrace();
    							}
                            	 
                              }
                              try(FileWriter fw = new FileWriter(lote.getRutaAlmacenamiento()+"excel.txt", true);
                      			    BufferedWriter bw = new BufferedWriter(fw);
                      			    PrintWriter out = new PrintWriter(bw))
                      			{
                      			    out.println(toExcel);
                      			    //more code
                      			} catch (IOException e) {
                      			    //exception handling left as an exercise for the reader
                      			}
                              
                        	  totalImagesEnviadas=totalImagesEnviadas+2;
//                        	  lbl_datosEnviados.setText(totalImagesEnviadas+"");
                        	  File f = new File(lote.getRutaAlmacenamiento()+"enviadas\\");
	                  		  if (!f.exists() && !f.isDirectory()) {
	                  			f.mkdir();
	                  		  }
	                  		  met.moveToPath(lote.getRutaAlmacenamiento(), lote.getRutaAlmacenamiento()+"enviadas\\", images[contador-1], images[contador-1]);
	                  		  met.moveToPath(lote.getRutaAlmacenamiento(), lote.getRutaAlmacenamiento()+"enviadas\\", images[contador], images[contador]);
                          }
                          
                          imagenesBl.clear();
                      //  End Persistencia de Imagen   
                          setTotalAvance((i+1)+"");
                          result=((i+1)*100)/totalImages;
                          updateGraphics((int) result);
                          setTextToLogResultados("/***********************************");
                	}else {
                		System.out.println("No hubo respuesta de reconocimiento");
                	}	
            	}//Fin de cara Reverso
    		}//Fin de ambos lados
        	else {
        		
        	}
        	contador++;
		}//Fin del recorrido de todas las imagenes
		long lEndTime = System.nanoTime(); 
    	long output = lEndTime - lStartTime; 
    	System.out.println("Tiempo  total de procesamiento: " + ((output / 1000000)/1000));
    	
    	
    	String[] columns = {"NOMBRE DE IMAGEN","PLACA", "NÚMERO DE LICENCIA", "TIPO DE LICENCIA", "LICENCIA EXPEDIDA EN",
    			"PLACA EXPEDIDA EN", "ARTÍCULO", "FRACCIÓN", "INCISO","PÁRRAFO", 
    			"FECHA DE INFRACCIÓN","PLACA DEL OFICIAL", "UT DELEGACIÓN", "FOLIO", "ID LOTE"};
    	try {
    		List<String> imgToSen = getAllImages();
    		if(imgToSen.size()==0) {
    			
    			String excelString = Methods.readFile(lote.getRutaAlmacenamiento()+"excel.txt",StandardCharsets.UTF_8);
    			String[] excelRows = excelString.split(";");
    			List<DataExcel> dataExcel = new ArrayList<DataExcel>();
    			for(int exceli=0;exceli<excelRows.length-1;exceli++) {
    				DataExcel data = new DataExcel();
    				String[] excelCols = excelRows[exceli].split(",");
    				
    				for(int excelj=0;excelj<excelCols.length-1;excelj++) {
    					if(excelCols[excelj].equals("null")) {
    						excelCols[excelj]="";
    					}
    				}
    				
    				data.setNombredeimagen(excelCols[0]);
    				data.setPlaca(excelCols[1]);
    				data.setNumerodelicencia(excelCols[2]);
    				data.setTipodelicencia(excelCols[3]);
    				data.setLicenciaexpedidaen(excelCols[4]);
    				data.setPlacaexpedidaen(excelCols[5]);
    				data.setArticulo(excelCols[6]);
    				data.setFraccion(excelCols[7]);
    				data.setInciso(excelCols[8]);
    				data.setParrafo(excelCols[9]);
    				data.setFechadeinfraccion(excelCols[10]);
    				data.setPlacadeloficial(excelCols[11]);
    				data.setUtdelegacion(excelCols[12]);
    				data.setFolio(excelCols[13]);
    				data.setIdlote(excelCols[14]);
    				
    				
    				dataExcel.add(data);
    			}
    			
    			
//    			datosExcel=met.convertFileToJson(lote.getRutaAlmacenamiento()+"enviadas\\excel.txt");
//    			 datosExcel.add(imagenp);
    			 
//    			creaExcel.crearExcel(lote,columns,datosExcel);
    			creaExcel.crearExcel2(lote,columns,dataExcel);
    		}
			
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
	 }
	 
	 //Este método se encarga de preguntar cuales son las imagenes que se deben procesar
	 public void obtieneImagenesAProcesar() {
		 /*Lo correcto es que vaya a preguntar a un servicio cuales imagenes faltan por procesar
		 Pero en caso de que no de tiempo realizarlo, la primeravez va a procesar todas las imagenes
		 y que el servicio se encargue de validar la duplicidad.
		 Si el proceso se detiene por alguna situacion es decir si falla la conexion entonces quehalla
		 una lista de imagenes no persistidas en bd y cuando el usuario quiera reanudar la conexion
		 simplemente le de reintentar y se vaya a procesar aquellas que quedaron pendientes para agilizar el proceso.
		 //
		  * Tambien se puede manejar una carpeta de enviadas y eso puede facilitar el trabajo
		 */
	 }
	 
	 
	 
	 
	 
	 
	 public void iniTime() {
			/********************/
			new Thread(new Runnable(){
			@Override
			public void run() {
			/****************/	
				try {  
			         while (startWatch) {  
			  
			            Calendar cal = Calendar.getInstance();  
			            hours = cal.get( Calendar.HOUR_OF_DAY );  
			            if ( hours > 12 ) hours -= 12;  
			            minutes = cal.get( Calendar.MINUTE );  
			            seconds = cal.get( Calendar.SECOND );  
			  
			            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");  
			            Date date = cal.getTime();  
			            timeString = formatter.format( date );  
			  
			            ini_time.setText(timeString); 			  
			            Thread.sleep( 1000 );  // interval given in milliseconds  
			         }  
			      }  
			      catch (Exception e) { }  
			/****************/	
			}
			}).start();
			/****************/	
		}
	 
	 public void finiTime() {
			/********************/
			new Thread(new Runnable(){
			@Override
			public void run() {
			/****************/	
				try {  
			         while (startWatch2) {  
			  
			            Calendar cal = Calendar.getInstance();  
			            hours = cal.get( Calendar.HOUR_OF_DAY );  
			            if ( hours > 12 ) hours -= 12;  
			            minutes = cal.get( Calendar.MINUTE );  
			            seconds = cal.get( Calendar.SECOND );  
			  
			            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");  
			            Date date = cal.getTime();  
			            timeString = formatter.format( date );  
			  
			            fini_time.setText(timeString); 			  
			            Thread.sleep( 1000 );  // interval given in milliseconds  
			         }  
			      }  
			      catch (Exception e) { }  
			/****************/	
			}
			}).start();
			/****************/	
		}
	 
	 public boolean updateLote(Long idEstatus, BitacoraVO bitacoraVO) {
		 System.out.println("Se actualiza el lote");
		 loteservice = new LotesRestClientService();
		 File f = new File(lote.getRutaAlmacenamiento()+"enviadas\\");
		  if (!f.exists() && !f.isDirectory()) {
			f.mkdir();
		  }
		  List<String> listImages = met.getImages(lote.getRutaAlmacenamiento()+"enviadas\\", extensionesvalidas, true);
		 boolean isSaved = loteservice.actualizarEstTolImgLote(lote.getIdLote(),listImages.size()/2,idEstatus, conf, bitacoraVO);
		 if(!isSaved) {
			 JOptionPane.showMessageDialog(null, "¡No se pudo establecer conexion intentelo mas tarde");
			 return false;
		 }
		 return true;
	 }
	 
	public void deleteCache() {
		String pathOfCacheMatlab = System.getProperty("user.home")+"\\AppData\\Local\\Temp\\"+System.getProperty("user.name")+"\\mcrCache9.0";
		met.deleteCompleteFolder(pathOfCacheMatlab);
		System.out.println("pathhh: "+pathOfCacheMatlab);
		System.out.println("pathhh: "+System.getProperty("user.dir"));
	}
	 
	 public void minimizeWin() {
			this.setState(ConsultaLotesFrame.ICONIFIED);
	}
	 
	 public void changeIcon(){
			this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/Isotipo_DPIv2r2.png")));
		}
}
