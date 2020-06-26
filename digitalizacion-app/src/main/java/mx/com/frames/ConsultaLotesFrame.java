package mx.com.frames;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.RowSorter;
import javax.swing.ScrollPaneConstants;
import javax.swing.SortOrder;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import com.toedter.components.JSpinField;

import mx.com.app.frames.reanudar.ReanudarLoteFrame;
import mx.com.app.services.ConfiguracionRestClientService;
import mx.com.app.services.LotesRestClientService;
import mx.com.teclo.base.excel.ExcelWriter;
import mx.com.teclo.base.util.enumerados.ConfiguracionEnum;
import mx.com.teclo.base.util.methods.Methods;
import mx.com.teclo.base.vo.configuracion.ConfiguracionVO;
import mx.com.teclo.base.vo.lotes.EstadosLotesVO;
import mx.com.teclo.base.vo.lotes.LoteVO;
import mx.com.teclo.base.vo.usuario.UsuarioVO;

import java.awt.Cursor;


public class ConsultaLotesFrame extends JFrame {
	
	int min = 1;
    int max = 31;
    int step = 1;
    int initValue = 1;
    SpinnerModel model1 = new SpinnerNumberModel(initValue, min, max, step);
    SpinnerModel model2 = new SpinnerNumberModel(initValue, min, max, step);

	private JPanel contentPane;
	private JTextField filterText;
	private JTable table_1;
	int statuslote;
	JPanel panel_busqueda_avanzada = new JPanel();
    JMonthChooser mes2 = new JMonthChooser();
    JYearChooser anio2 = new JYearChooser();
    
    JRadioButton rdbtnPorRangoDefecha = new JRadioButton("Por Rango de Fecha");
	//Variables globales

	LotesRestClientService lotesRestclientservice;
	ConfiguracionRestClientService configuracionRestclientservice;
	ConfiguracionVO conf;
	Methods met = new Methods();
	int currentlote;
	List<LoteVO> lotes;
	
//	int posX=0,posY=0;
	TableModel modeltbl;
	TableRowSorter<TableModel> sorter;
	
	String currentEstatus="";
	
	JComboBox comboBox_estatus = new JComboBox();
	JRadioButton rdbtnTodos = new JRadioButton("Todos");
	JRadioButton rdbtnAvanzado = new JRadioButton("Avanzada");
	
	List<EstadosLotesVO> estadosLotes=null;
	static UsuarioVO usuario = new UsuarioVO();
	
	JPanel panel_9 = new JPanel();
	JLabel lbl = new JLabel();
	JScrollPane scrollPane = new JScrollPane();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		

		//Pase de argumentos
		
		String[] argumentos= new String[2]; 
		int cont=0; 
		for (String value: args) {
		  argumentos[cont]=value; 
		  cont++; 
		}
		  
		String id=argumentos[1].trim(); 
		int result = Integer.parseInt(id); 
		Long l= new Long(result); 
		usuario.setUsuarioId(l);
		usuario.setUsuarioNombre(argumentos[0]);
		 
		//Fin pase de argumentos
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaLotesFrame frame = new ConsultaLotesFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConsultaLotesFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(-6, 0, 1381, 734);
		this.setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(153, 0, 0), 7));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		changeIcon();
		
		JPanel panel_contenedorMain = new JPanel();
		panel_contenedorMain.setBorder(new LineBorder(new Color(153, 0, 0)));
		
		panel_contenedorMain.setBounds(0, 0, 1381, 734);
		contentPane.add(panel_contenedorMain);
		panel_contenedorMain.setBackground(new java.awt.Color(236, 240, 245));
		panel_contenedorMain.setLayout(null);
		
		JPanel panel_footer = new JPanel();
		panel_footer.setLayout(null);
		panel_footer.setBackground(new Color(211, 26, 43));
		panel_footer.setBounds(0, 681, 1381, 53);
		panel_contenedorMain.add(panel_footer);
		
		JLabel label_3 = new JLabel("Derechos de autor © 2018 Teclo Mexicana. Todos los derechos reservados.");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_3.setBounds(133, 0, 471, 53);
		panel_footer.add(label_3);
		
		JLabel lblVersin = new JLabel("Versión 2.3.0");
		lblVersin.setForeground(Color.WHITE);
		lblVersin.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblVersin.setBounds(1230, 0, 151, 53);
		panel_footer.add(lblVersin);
		
		JPanel panel_menu = new JPanel();
		panel_menu.setLayout(null);
		panel_menu.setBorder(null);
		panel_menu.setBackground(Color.WHITE);
		panel_menu.setBounds(0, 53, 122, 631);
		panel_contenedorMain.add(panel_menu);
		
		JPanel btn_menuConsultaLotes = new JPanel();
		btn_menuConsultaLotes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				 
			}
		});
		btn_menuConsultaLotes.setLayout(null);
		
		btn_menuConsultaLotes.setBackground(new Color(211, 26, 43));
		btn_menuConsultaLotes.setBounds(0, 0, 122, 75);
		btn_menuConsultaLotes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btn_menuConsultaLotes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panel_menu.add(btn_menuConsultaLotes);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Search_36px_1.png")));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(37, 11, 46, 36);
		btn_menuConsultaLotes.add(label_4);
		
		JLabel label_5 = new JLabel("Consulta de Lotes");
		label_5.setForeground(Color.WHITE);
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_5.setBounds(0, 47, 122, 14);
		btn_menuConsultaLotes.add(label_5);
		
		JPanel btn_menuProcesamiento = new JPanel();
		btn_menuProcesamiento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeToDigitalizacion(usuario);
			}
		});
		
		btn_menuProcesamiento.setLayout(null);
		btn_menuProcesamiento.setBackground(SystemColor.menu);
		btn_menuProcesamiento.setBounds(0, 74, 122, 75);
		btn_menuProcesamiento.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btn_menuProcesamiento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panel_menu.add(btn_menuProcesamiento);
		
		JLabel label_6 = new JLabel("");
		label_6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Gears_36px_1.png")));
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(37, 11, 53, 36);
		btn_menuProcesamiento.add(label_6);
		
		JLabel lblProcesamiento = new JLabel("Lote");
		lblProcesamiento.setHorizontalAlignment(SwingConstants.CENTER);
		lblProcesamiento.setForeground(Color.BLACK);
		lblProcesamiento.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProcesamiento.setBounds(0, 45, 122, 30);
		btn_menuProcesamiento.add(lblProcesamiento);
		
		JPanel panel_header = new JPanel();
		panel_header.setLayout(null);
		panel_header.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		panel_header.setBackground(Color.WHITE);
		panel_header.setBounds(0, 0, 1381, 54);
		panel_contenedorMain.add(panel_header);
		
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
				int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres salir de la aplicación?");
				if (confirm == 0) {
		            System.exit(1);
		        }
			}
		});
		label_1.setToolTipText("Cerrar ventana");
		label_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Cancel_50px.png")));
		label_1.setBounds(0, 0, 57, 43);
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
			public void mouseClicked(MouseEvent arg0) {
				minimizeWin();
			}
		});
		btn_minimize.setBackground(Color.WHITE);
		btn_minimize.setBounds(1244, 0, 51, 44);
		panel_header.add(btn_minimize);
		btn_minimize.setLayout(null);
		
		JLabel label_7 = new JLabel("");
		label_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				minimizeWin();
			}
		});
		label_7.setToolTipText("Minimizar ventana");
		label_7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Minus_50px.png")));
		label_7.setBounds(0, 0, 50, 44);
		btn_minimize.add(label_7);
		
		JPanel panel_contenedor = new JPanel();
		panel_contenedor.setBackground(Color.WHITE);
		panel_contenedor.setBounds(132, 65, 1218, 605);
		panel_contenedor.setBorder(new LineBorder(Color.GRAY));
		panel_contenedorMain.add(panel_contenedor);
		panel_contenedor.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new LineBorder(Color.DARK_GRAY));
		panel.setBounds(31, 22, 1151, 550);
		panel_contenedor.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 1151, 40);
		panel_1.setBackground(new Color(211, 26, 43));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblConsultaDeLotes = new JLabel("Consulta de Lotes");
		lblConsultaDeLotes.setForeground(Color.WHITE);
		lblConsultaDeLotes.setBounds(432, 0, 193, 40);
		panel_1.add(lblConsultaDeLotes);
		lblConsultaDeLotes.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 40, 1131, 40);
		panel_4.setBackground(Color.WHITE);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.WHITE);
		panel_8.setBounds(854, 0, 267, 41);
		panel_4.add(panel_8);
		panel_8.setLayout(null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(0, 11, 224, 29);
		panel_8.add(panel_6);
		panel_6.setLayout(null);
		
		filterText = new JTextField();
		filterText.setBounds(0, 0, 224, 29);
		panel_6.add(filterText);
		filterText.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(234, 11, 31, 29);
		panel_8.add(panel_5);
		panel_5.setBackground(Color.WHITE);
		panel_5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		panel_5.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		panel_5.setLayout(null);
		
		JLabel label_9 = new JLabel("");
		
		label_9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Search_25px.png")));
		label_9.setBounds(0, 0, 31, 29);
		panel_5.add(label_9);
		
		JLabel lblNoExistenLotes = new JLabel(".");
		lblNoExistenLotes.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNoExistenLotes.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoExistenLotes.setBounds(296, 11, 548, 30);
		panel_4.add(lblNoExistenLotes);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(288, 81, 853, 404);
		panel_7.setBackground(Color.WHITE);
		panel.add(panel_7);
		panel_7.setLayout(null);
		
		scrollPane.setBounds(10, 11, 833, 371);
		panel_7.add(scrollPane);
		
		table_1 = new JTable();
		
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String selectedData = null;
				
		        int[] selectedRow = table_1.getSelectedRows();
		        int[] selectedColumns = table_1.getSelectedColumns();
		        LoteVO milote=null;
		        for (int i = 0; i < selectedRow.length; i++) {
		          for (int j = 0; j < selectedColumns.length; j++) {
//		        	  int seli=selectedRow[i];
				      int selj=selectedColumns[j];
				      
		        	  if(selj==5) { 
//		        		  JOptionPane.showMessageDialog(null, "id: "+table_1.getValueAt(selectedRow[i], 0));
					      String va=table_1.getValueAt(selectedRow[i], 0)+"";
				    	  int id=Integer.parseInt(va);
//				    	  Long idl=new Long(id);
//				    	  System.out.println("idl: "+idl);
				    	  milote=getCurrentLote(id);
//				    	  System.out.println("milote: "+milote);
				    	  if(milote!=null&&milote.getEstatusProceso().getIdEstatusProceso()==(int) (long)ConfiguracionEnum.loteEnPausa.getConstante()) {
//				    		  System.out.println("reanudar");
				    		  changeToReanudarLote(milote, usuario);
				    	  }else if(milote!=null&&milote.getEstatusProceso().getIdEstatusProceso()==(int) (long)ConfiguracionEnum.loteEnValidacion.getConstante()) {
//				    		  System.out.println("validar");
				    		  changeToValidarLote(milote, usuario);
				    	  }else if(milote!=null&&milote.getEstatusProceso().getIdEstatusProceso()==(int) (long)ConfiguracionEnum.loteEnProcesamiento.getConstante()) {
//				    		  System.out.println("procesar");
				    		  changeToProcesarLote(milote, usuario);
				    	  }else if(milote!=null){
	
					    	  ExcelWriter creaExcel = new ExcelWriter();
			  	        		File excel = new File(milote.getRutaAlmacenamiento()+"Reporte_Digitalización_"+milote.getNombreLote().toUpperCase()+".xlsx");
			  	        		if(excel.exists()&&excel.isFile()) {
			  	        			creaExcel.openExcel(milote.getRutaAlmacenamiento()+"Reporte_Digitalización_"+milote.getNombreLote().toUpperCase()+".xlsx");
			  	        		}else {
			  	        			JOptionPane.showMessageDialog(null,"El archivo ya no se encuentra alojado en este equipo");
			  	        		}
				    	  }else if(milote==null) {
				    		  JOptionPane.showMessageDialog(null,"El archivo ya no se encuentra alojado en este equipo");
				    	  }			    	  
				      }
		        	  
		          }
		        }
			}
		});
		
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setColumnHeaderView(table_1);
		table_1.setRowHeight(25);
		JTableHeader header = table_1.getTableHeader();
		header.setPreferredSize(new Dimension(25,25));
		header.setFont(new Font("Tahoma", Font.BOLD, 14));
		
   
	    /******************metodos al iniciar la aplicacion****************/
		lotesRestclientservice = new LotesRestClientService();
		configuracionRestclientservice = new ConfiguracionRestClientService();
		
		this.conf = configuracionRestclientservice.obtenerConfiguracion(); 
        this.lotes = lotesRestclientservice.obtenerTodosLotes(conf, -10L, "", "");//Todos los lotes
        estadosLotes = lotesRestclientservice.obtenerCatalogoEstatus(conf);
        fillEstadosLotes(estadosLotes);
        
        /********************SECCIÓN PARA LLENADO DE TABLA**********************/
//        Object [][] obj = new Object [lotes.size()][7];
//        lbl.setText("hola");
//        lbl.setHorizontalAlignment(SwingConstants.LEFT);
        /*******************************************/
        lotes=fillLotesInTable(lotes);
        
        JLabel lbl_rndr = new JLabel();
//        lbl_rndr.setText("holas");
//        lbl_rndr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Gears_36px_1.png"));
//        Icon  icon = new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Gears_36px_1.png");
        

        lblNoExistenLotes.setText("No existen lotes de imágenes cargados en el sistema.");
    	lblNoExistenLotes.setVisible(false);
    	
        if(lotes.size()<=0) {
        	rdbtnTodos.setEnabled(false);
        	rdbtnAvanzado.setEnabled(false);
        	table_1.setAutoCreateRowSorter(false);
        	table_1.setCellSelectionEnabled(false);
        	table_1.setVisible(false);     	
        	scrollPane.removeAll();
        	lblNoExistenLotes.setVisible(true);
        }
        
        //Esto es para el filtrado rapido por texto
        if(lotes.size()>=1) {
			filterText.addKeyListener(new java.awt.event.KeyAdapter() {
	            public void keyPressed(java.awt.event.KeyEvent evt) {
	            	if(lotes.size()>=1) {
//	            		JOptionPane.showMessageDialog(null, "it works!");
	            		filterTextKeyPressed(evt);
	            	}
	                
	            }
	        });
    	}
        
        makeBaseFolders();
        
        /*****************************************************************************/
        
		
		if(lotes.size()>1) {
			restorSortTable();
		}
		
        panel_7.add(scrollPane);
        
        panel_busqueda_avanzada.setBounds(10, 177, 268, 288);
        panel_busqueda_avanzada.setBorder(new LineBorder(Color.GRAY));
        panel_busqueda_avanzada.setBackground(Color.WHITE);
        panel.add(panel_busqueda_avanzada);
        panel_busqueda_avanzada.setLayout(null);
        panel_busqueda_avanzada.setVisible(false);
        
        JPanel panel_11 = new JPanel();
        panel_11.setBackground(Color.WHITE);
        panel_11.setBounds(10, 42, 242, 38);
        panel_busqueda_avanzada.add(panel_11);
        panel_11.setLayout(null);
        
        final JSpinner dia1 = new JSpinner();
        dia1.setBounds(0, 21, 64, 30);
        panel_9.add(dia1);
        
       
        dia1.setModel(model1);
        JComponent ne_dia1 = new JSpinner.NumberEditor(dia1, "00");
        dia1.setEditor(ne_dia1);
        
        Calendar calendar1 = Calendar.getInstance(TimeZone.getDefault());
	    int day = calendar1.get(Calendar.DATE);
	    dia1.setValue(day);
        
        final JSpinner dia2 = new JSpinner();
        dia2.setBounds(0, 79, 64, 30);
        panel_9.add(dia2);
        dia2.setEnabled(false);
        
        dia2.setModel(model2);
        JComponent ne_dia2 = new JSpinner.NumberEditor(dia2, "00");
    	dia2.setEditor(ne_dia2);
    	
    	Calendar calendar2 = Calendar.getInstance(TimeZone.getDefault());
	    int day2 = calendar2.get(Calendar.DATE);
	    dia2.setValue(day2);
        
        JRadioButton rdbtnPorFecha = new JRadioButton("Por Fecha");
        rdbtnPorFecha.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
//        		spinner.setEnabled(false);
        	dia2.setEnabled(false);
       	     mes2.getComboBox().setEnabled(false);
       	     anio2.getSpinner().setEnabled(false);
        	}
        });
        rdbtnPorFecha.setBackground(Color.WHITE);
        rdbtnPorFecha.setFont(new Font("Tahoma", Font.BOLD, 11));
        rdbtnPorFecha.setBounds(6, 7, 82, 23);
        panel_11.add(rdbtnPorFecha);
        rdbtnPorFecha.setSelected(true); 
        
        
        rdbtnPorRangoDefecha.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dia2.setEnabled(true);
        	     mes2.getComboBox().setEnabled(true);
        	     anio2.getSpinner().setEnabled(true);
        	}
        });
        rdbtnPorRangoDefecha.setBackground(Color.WHITE);
        rdbtnPorRangoDefecha.setFont(new Font("Tahoma", Font.BOLD, 11));
        rdbtnPorRangoDefecha.setBounds(90, 7, 136, 23);
        panel_11.add(rdbtnPorRangoDefecha);
        
        
        
        panel_9.setBounds(10, 86, 242, 111);
        panel_busqueda_avanzada.add(panel_9);
        panel_9.setBackground(Color.WHITE);
        panel_9.setLayout(null);
        
        final JMonthChooser mes1 = new JMonthChooser();
        mes1.setBackground(Color.WHITE);
        mes1.getSpinner().setBackground(Color.WHITE);
        mes1.getComboBox().setBackground(Color.WHITE);
        mes1.setBounds(66, 21, 99, 30);
        panel_9.add(mes1);
        
        final JYearChooser anio1 = new JYearChooser();
        anio1.setBounds(168, 21, 64, 30);
        panel_9.add(anio1);
        
        
        mes2.getComboBox().setEnabled(false);
        mes2.setBackground(Color.WHITE);
        mes2.setBounds(66, 79, 99, 30);
        panel_9.add(mes2);
        
        anio2.getSpinner().setEnabled(false);
        anio2.setBounds(168, 79, 64, 30);
        panel_9.add(anio2);
        
        JLabel lblDe = new JLabel("De:");
        lblDe.setHorizontalAlignment(SwingConstants.LEFT);
        lblDe.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDe.setBounds(0, 0, 32, 15);
        panel_9.add(lblDe);
        
        JLabel lblA = new JLabel("A:");
        lblA.setHorizontalAlignment(SwingConstants.LEFT);
        lblA.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblA.setBounds(0, 51, 42, 30);
        panel_9.add(lblA);
        
        
        
        JPanel panel_10 = new JPanel();
        panel_10.setBackground(Color.WHITE);
        panel_10.setBounds(10, 195, 242, 45);
        panel_busqueda_avanzada.add(panel_10);
        panel_10.setLayout(null);
        comboBox_estatus.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		currentEstatus=comboBox_estatus.getSelectedItem().toString();
        	}
        });
        
        
        comboBox_estatus.setBounds(72, 11, 160, 23);
        panel_10.add(comboBox_estatus);
        
        JLabel lblEstatus = new JLabel("Estatus:");
        lblEstatus.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblEstatus.setBounds(0, 11, 62, 22);
        panel_10.add(lblEstatus);
        
        JPanel panel_15 = new JPanel();
        panel_15.setLayout(null);
        panel_15.setBackground(new Color(211, 26, 43));
        panel_15.setBounds(0, 0, 268, 31);
        panel_busqueda_avanzada.add(panel_15);
        
        JLabel lblBusquedaAvanzada = new JLabel("Búsqueda Avanzada");
        lblBusquedaAvanzada.setHorizontalAlignment(SwingConstants.CENTER);
        lblBusquedaAvanzada.setForeground(Color.WHITE);
        lblBusquedaAvanzada.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblBusquedaAvanzada.setBounds(10, 0, 248, 28);
        panel_15.add(lblBusquedaAvanzada);
        
        JPanel btn_buscar = new JPanel();
        btn_buscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_buscar.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		NumberFormat formatter = new DecimalFormat("00");
         		String dia=formatter.format(dia1.getValue());
         		String mes=formatter.format(mes1.getMonth()+1);
         		String currentDate=anio1.getYear()+"-"+mes+"-"+dia;
         		 
         		String dia22=formatter.format(dia2.getValue());
         		String mes22=formatter.format(mes2.getMonth()+1);
         		String currentDate22=anio2.getYear()+"-"+mes22+"-"+dia22;
         		 
         		busquedaAvanzada(currentDate, currentDate22);
         		lotes = fillLotesInTable(lotes);
        	
        	}
        });
        btn_buscar.setBounds(72, 246, 133, 31);
        btn_buscar.setBackground(new Color(211, 26, 43));
        panel_busqueda_avanzada.add(btn_buscar);
        btn_buscar.setLayout(null);
        
        JLabel lblBuscar = new JLabel("Buscar");
        lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
        lblBuscar.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblBuscar.setForeground(Color.WHITE);
        lblBuscar.setBounds(10, 0, 113, 31);
        btn_buscar.add(lblBuscar);
        
        JPanel panel_13 = new JPanel();
        panel_13.setBounds(10, 91, 268, 75);
        panel_13.setBorder(new LineBorder(Color.GRAY));
        panel_13.setBackground(Color.WHITE);
        panel.add(panel_13);
        panel_13.setLayout(null);
        
        ButtonGroup group = new ButtonGroup();
        ButtonGroup group2 = new ButtonGroup();
        
        
        rdbtnTodos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
                lotes = lotesRestclientservice.obtenerTodosLotes(conf, -10L, "", "");//Todos los lotes
                lotes=fillLotesInTable(lotes);
                restorSortTable();
        		panel_busqueda_avanzada.setVisible(false);
        		filterText.setEditable(true);
        	}
        });
        rdbtnTodos.setBackground(Color.WHITE);
        rdbtnTodos.setBounds(27, 38, 73, 23);
        panel_13.add(rdbtnTodos);
        rdbtnTodos.setSelected(true);
        
        
        rdbtnAvanzado.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		panel_busqueda_avanzada.setVisible(true);
        		filterText.setEditable(false);
        	}
        });
        rdbtnAvanzado.setBackground(Color.WHITE);
        rdbtnAvanzado.setBounds(149, 38, 86, 23);
        panel_13.add(rdbtnAvanzado);
        
        group.add(rdbtnTodos);
        group.add(rdbtnAvanzado);
        
        group2.add(rdbtnPorRangoDefecha);
        group2.add(rdbtnPorFecha);
        
        JPanel panel_14 = new JPanel();
        panel_14.setBackground(new Color(211, 26, 43));
        panel_14.setBounds(0, 0, 268, 31);
        panel_13.add(panel_14);
        panel_14.setLayout(null);
        
        JLabel lblBusqueda = new JLabel("Tipo de Búsqueda");
        lblBusqueda.setHorizontalAlignment(SwingConstants.CENTER);
        lblBusqueda.setForeground(Color.WHITE);
        lblBusqueda.setBounds(10, 0, 248, 28);
        panel_14.add(lblBusqueda);
        lblBusqueda.setFont(new Font("Tahoma", Font.BOLD, 14));
        
        JLabel txt_usuarioNombre = new JLabel("");
        txt_usuarioNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        txt_usuarioNombre.setBounds(999, 0, 183, 21);
        panel_contenedor.add(txt_usuarioNombre);
        txt_usuarioNombre.setText(usuario.getUsuarioNombre());

        
	}
	
	
	/*************METODOS*********************************************************/
	
	public void busquedaAvanzada(String fechaIni, String fechaFin) {
		String currentEstatus_=(currentEstatus.trim()).toUpperCase();
		Long idstatus=getIdEstatusLotes(currentEstatus_);
		if(rdbtnPorRangoDefecha.isSelected()) {
			this.lotes = lotesRestclientservice.obtenerTodosLotes(conf, idstatus, fechaIni, fechaFin);//Todos los lotes			
		}else {
			this.lotes = lotesRestclientservice.obtenerTodosLotes(conf, idstatus, fechaIni, fechaIni);//Todos los lotes
		}
		
	}
	
	public void changeToDigitalizacion(UsuarioVO usuario) {	 
		
		 DigitalizacionFrame digitalizacion = new DigitalizacionFrame(conf, lotes, usuario);
		 digitalizacion.setVisible(true); 
//		 System.out.println("pathhh: "+System.getProperty("user.dir")+"\\src\\main\\resources\\zipScan\\scan.zip");
		 this.dispose();
	 }
	
	public void changeToReanudarLote(LoteVO lote, UsuarioVO usuario) {
		 ReanudarLoteFrame reanudarLote = new ReanudarLoteFrame(lote, conf, usuario);
		 reanudarLote.setVisible(true); 
		 this.dispose();
	 }
	
	public void changeToValidarLote(LoteVO lote, UsuarioVO usuario) {
		 ValidacionFrame validacionLote = new ValidacionFrame(lote, conf, usuario);
		 validacionLote.setVisible(true); 
		 this.dispose();
	 }
	
	public void changeToProcesarLote(LoteVO lote, UsuarioVO usuario) {
		 ProcesamientoFrame validacionLote = new ProcesamientoFrame(lote, conf, usuario);
		 validacionLote.setVisible(true); 
		 this.dispose();
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
		//Creamos todos los archivos que va a usar el Scanner	
		File jar = new File("C:\\TECLO_PROCESSING\\TecloScan.jar");
		File lib = new File("C:\\TECLO_PROCESSING\\lib\\");
		met.getFile2();
		if(!lib.isDirectory()||lib.listFiles().length<4||
				!jar.exists()||!jar.isFile()) {
			met.getFile2();
		}

	}
	
	public void minimizeWin() {
		this.setState(ConsultaLotesFrame.ICONIFIED);
	}
	
	private void filterTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filterTextKeyPressed
        // TODO add your handling code here:
        String text = filterText.getText().toString().toUpperCase();
        sorter.setRowFilter (RowFilter.regexFilter(text.trim()));
        sorter.setSortKeys (null);
    }//GEN-LAST:event_filterTextKeyPressed
	
	public void filterBy2Days(Date startDate, Date endDate){
	    List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(3);
	    filters.add( RowFilter.dateFilter(ComparisonType.AFTER, startDate) );
	    filters.add( RowFilter.dateFilter(ComparisonType.BEFORE, endDate) );
	    modeltbl = (DefaultTableModel) table_1.getModel();
	    TableRowSorter<TableModel> tr = new TableRowSorter<>(modeltbl);
	    table_1.setRowSorter(tr);
	    RowFilter<Object, Object> rf = RowFilter.andFilter(filters);
	    tr.setRowFilter(rf);
	}
	
	public void filterByDate(Date searchDate) {
		List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(3);
		filters.add( RowFilter.dateFilter(ComparisonType.EQUAL, searchDate) );
		modeltbl = (DefaultTableModel) table_1.getModel();
	    TableRowSorter<TableModel> tr = new TableRowSorter<>(modeltbl);
	    table_1.setRowSorter(tr);
	    RowFilter<Object, Object> rf = RowFilter.andFilter(filters);
	    tr.setRowFilter(rf);
	}
	
	public void filterByStatus() {
		List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(4);
		filters.add(RowFilter.regexFilter(currentEstatus));
		modeltbl = (DefaultTableModel) table_1.getModel();
	    TableRowSorter<TableModel> tr = new TableRowSorter<>(modeltbl);
	    table_1.setRowSorter(tr);
	    RowFilter<Object, Object> rf = RowFilter.andFilter(filters);
	    tr.setRowFilter(rf);
	}
	
	public void restorSortTable() {
		sorter = new TableRowSorter<>(table_1.getModel()); 
		table_1.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
		int columnIndexToSort = 0;
		sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
		sorter.setSortKeys(sortKeys);
		sorter.sort();
	}
	
	public void fillEstadosLotes(List<EstadosLotesVO> estados){
		String[] comboBoxArray=new String[estados.size()+1];
		comboBoxArray[0]="TODOS";
		for(int i=0;i<estados.size();i++) {
			comboBoxArray[i+1] = estados.get(i).getNombreEstatus().toUpperCase();
		}
		
		comboBox_estatus.setModel(new DefaultComboBoxModel(comboBoxArray));
	}
	
	public Long getIdEstatusLotes(String estatus) {
		Long estatusl=-13L;
		for(int i=0;i<estadosLotes.size();i++) {
			if(estatus.equals(estadosLotes.get(i).getNombreEstatus().toUpperCase())) {
				estatusl=estadosLotes.get(i).getIdEstatusProceso();
			}
		}
		return estatusl;
	}
	
	
	public LoteVO getCurrentLote(int id) {
		for(int i=0;i<lotes.size();i++) {
			int valid=new Integer((int) (long) lotes.get(i).getIdLote());
			if(valid==id) {
				return lotes.get(i);
			}
		}
		return null;
	}
	
	public void changeIcon(){
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/Isotipo_DPIv2r2.png")));
	}
	
	public List<LoteVO> fillLotesInTable(List<LoteVO> lotesIn){
//		List<LoteVO> lotesOut = new ArrayList<LoteVO>();
		Object [][] obj = new Object [lotesIn.size()][7];
		Object [][] obj2 = new Object [lotesIn.size()][7];
		lbl.setText("hola");
	    lbl.setHorizontalAlignment(SwingConstants.LEFT);
	    
        for(int i=0;i<lotesIn.size();i++) {
//        	Date dd=met.convertStringToDate(lotes.get(i).getFechaCracionLote().toString(), conf.getFormatDateEncode());
        	
        	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        	Date c=null;
			try {
				c = sdf.parse(lotesIn.get(i).getFechaCracionLote());
//				Date fin=new Date(sdf.format(c));
				obj[i][0]=new Integer((int) (long) lotesIn.get(i).getIdLote());
	        	obj[i][1]=lotesIn.get(i).getNombreLote().toUpperCase();
	        	obj[i][2]=lotesIn.get(i).getNumeroTotalImagenes();
	        	obj[i][3]=c;
	        	obj[i][4]=lotesIn.get(i).getFechaCracionLote();
	        	if(lotesIn.get(i).getEstatusProceso().getIdEstatusProceso()==(long) ConfiguracionEnum.loteEnPausa.getConstante()||
	        			lotesIn.get(i).getEstatusProceso().getIdEstatusProceso()==(long) ConfiguracionEnum.loteEnProcesamiento.getConstante()||
	        					lotesIn.get(i).getEstatusProceso().getIdEstatusProceso()==(long) ConfiguracionEnum.loteEnValidacion.getConstante()
	        			) {
	        		obj[i][5]=lotesIn.get(i).getEstatusProceso().getNombreEstatus().toUpperCase();
	        		lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Play_25px_1.png")));
	        		     		
	        	}else {
	        		obj[i][5]="PROCESADO";
	        		lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Microsoft_Excel_25px.png")));
	        	}
	        	obj[i][6]=lbl.getIcon();  
	        	
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
        	
        }
        
        
        if(obj.length>0) {
        	modeltbl=new javax.swing.table.DefaultTableModel(
            		obj,
                    new String [] {//La primera fecha es para ordenamiento y la ocultamos y la segunda se muestra al usuario
                        "ID", "NOMBRE", "TOTAL","FECHA","FECHA", "ESTATUS", ""
                    }
                ) {
            	
            		public Class getColumnClass(int column)
                    {
                        return getValueAt(0, column).getClass();
                    }
            	
            	
            };
        }else {
        	modeltbl=new javax.swing.table.DefaultTableModel(
            		obj,
                    new String [] {//La primera fecha es para ordenamiento y la ocultamos y la segunda se muestra al usuario
                        "ID", "NOMBRE", "TOTAL","FECHA","FECHA", "ESTATUS", ""
                    }
                ) {          	
            	
            };
        }

        
        
        
        table_1.setModel(modeltbl);
        
        
        
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.CENTER);
		table_1.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		table_1.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		table_1.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		table_1.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
//		table_1.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);//Estatus

		//Seteamos las dimenciones de ancho para cada columna
		table_1.getColumnModel().getColumn(0).setMaxWidth(100);
		table_1.getColumnModel().getColumn(1).setMinWidth(100);
		table_1.getColumnModel().getColumn(2).setMaxWidth(100);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(1);
		table_1.getColumnModel().getColumn(4).setPreferredWidth(1);
		table_1.getColumnModel().getColumn(5).setMinWidth(100);
		table_1.getColumnModel().getColumn(6).setMaxWidth(100);//Reanudar
		
		table_1.removeColumn(table_1.getColumnModel().getColumn(3));
		
		scrollPane.setViewportView(table_1);
		
		table_1.setAutoCreateRowSorter(true);
		
		

		table_1.setCellSelectionEnabled(true);
	    ListSelectionModel cellSelectionModel = table_1.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        return lotesIn;
	}
	
	
}

	
