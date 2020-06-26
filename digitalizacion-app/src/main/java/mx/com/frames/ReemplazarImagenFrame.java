package mx.com.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;

import mx.com.app.services.ScannerService;
import mx.com.teclo.base.util.methods.Methods;
import mx.com.teclo.base.vo.configuracion.ConfiguracionVO;
import mx.com.teclo.base.vo.configuracionescaner.ConfiguracionEscanerVO;
import mx.com.teclo.base.vo.lotes.LoteVO;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class ReemplazarImagenFrame extends JFrame {

	private JPanel contentPane;
	String currentImage;
	ConfiguracionVO conf;
	LoteVO lote;
	Methods met = new Methods();
	ScannerService scannerService = new ScannerService();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ReemplazarImagenFrame frame = new ReemplazarImagenFrame();
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
	public ReemplazarImagenFrame(final ConfiguracionVO conf, final LoteVO lote, final String currentImage) {
		this.conf=conf;
		this.lote=lote;
		this.currentImage=currentImage;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		setBounds(100, 100, 566, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel btn_cargarImagen = new JPanel();
		btn_cargarImagen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				/**************************************/
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				jfc.setDialogTitle("Selecciona la carpeta de lotes a cargar: ");
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);

				int returnValue = jfc.showSaveDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					if (jfc.getSelectedFile().isFile()) {
//						btn_cargarImage.setVisible(true);

						String path=""+jfc.getSelectedFile().getAbsolutePath();
						String name=""+jfc.getSelectedFile().getName();
						path=path.replaceAll(name, "");
//						name=jfc.getSelectedFile().getName().split("\\.")[0];
						
						
						System.out.println("You selected the jfc.getSelectedFile().getAbsolutePath(): " + jfc.getSelectedFile().getAbsolutePath());	
						met.replaceToPath(jfc.getSelectedFile().getAbsolutePath(), lote.getRutaAlmacenamiento(), 
								jfc.getSelectedFile().getName(), currentImage);
//						txt_imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LogotipoTeclo.png")));
//						text_path.setText(""+jfc.getSelectedFile());
//						text_path.setToolTipText(""+jfc.getSelectedFile());
					}
				}
				closewin();
				/**************************************/
			}
		});
		btn_cargarImagen.setBounds(116, 164, 148, 105);
		contentPane.add(btn_cargarImagen);
		btn_cargarImagen.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btn_cargarImagen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btn_cargarImagen.setLayout(null);
		
		JLabel lblCargarDeDirectorio = new JLabel("Cargar de Directorio");
		lblCargarDeDirectorio.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargarDeDirectorio.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCargarDeDirectorio.setBounds(0, 72, 148, 22);
		btn_cargarImagen.add(lblCargarDeDirectorio);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Upload_25px.png")));
		label.setBounds(43, 11, 75, 50);
		btn_cargarImagen.add(label);
		
		JPanel btn_digitalizar = new JPanel();
		btn_digitalizar.setVisible(false);
		btn_digitalizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String[] parts = currentImage.split("\\.");
//				scannerService.scan(lote, 0, true, parts[0]);
				closewin();
			}
		});
		btn_digitalizar.setBounds(303, 164, 148, 105);
		contentPane.add(btn_digitalizar);
		btn_digitalizar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		btn_digitalizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btn_digitalizar.setLayout(null);
		
		JLabel lblDigitalizarEscaner = new JLabel("Digitalizar Escaner");
		lblDigitalizarEscaner.setHorizontalAlignment(SwingConstants.CENTER);
		lblDigitalizarEscaner.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDigitalizarEscaner.setBounds(0, 72, 148, 22);
		btn_digitalizar.add(lblDigitalizarEscaner);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Scanner_35px.png")));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(43, 11, 75, 50);
		btn_digitalizar.add(label_1);
		
		JPanel btn_close = new JPanel();
		btn_close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				closewin();
			}
		});
		btn_close.setLayout(null);
		btn_close.setBounds(480, 11, 76, 66);
		btn_close.setBorder(null);
		btn_close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btn_close);
		
		JLabel lblCerrar = new JLabel("Cerrar");
		lblCerrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCerrar.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCerrar.setBounds(0, 46, 76, 20);
		btn_close.add(lblCerrar);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Cancel_50px.png")));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(0, 0, 75, 50);
		btn_close.add(label_3);
		
		JLabel lblSeleccioneUnaOpcin = new JLabel("SELECCIONE UNA OPCIÓN PARA REEMPLAZAR LA IMAGEN");
		lblSeleccioneUnaOpcin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSeleccioneUnaOpcin.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccioneUnaOpcin.setBounds(10, 94, 546, 38);
		contentPane.add(lblSeleccioneUnaOpcin);
	}
	
	public void closewin(){
		this.dispose();
	}

}
