package mx.com.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.io.FileUtils;

import mx.com.app.frames.reanudar.CustomPanel2;
import mx.com.teclo.base.util.methods.Methods;
import mx.com.teclo.base.vo.configuracion.ConfiguracionVO;
import mx.com.teclo.base.vo.lotes.LoteVO;

import java.awt.Panel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JProgressBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;

public class LoadingFrame extends JFrame {

	private JPanel contentPane;
	CustomPanel2 jprogress = new CustomPanel2();
	
	Methods met = new Methods();
	ConfiguracionVO conf;
	LoteVO lote;
	
	JPanel btn_close = new JPanel();
	JLabel lbl_msj = new JLabel("Mensaje");
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LoadingFrame frame = new LoadingFrame();
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
	public LoadingFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		setBounds(100, 100, 602, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		jprogress.setBounds(140, 65, 308, 260);
		contentPane.add(jprogress);
		btn_close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				closeWindow();
			}
		});
		btn_close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		btn_close.setBounds(518, 11, 74, 74);
		contentPane.add(btn_close);
		btn_close.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, 74, 50);
		label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Cancel_50px.png")));
		btn_close.add(label);
		
		JLabel lblCerrar = new JLabel("Cerrar");
		lblCerrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCerrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCerrar.setBounds(0, 49, 74, 14);
		btn_close.add(lblCerrar);
		
		
		lbl_msj.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_msj.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_msj.setBounds(10, 344, 582, 37);
		contentPane.add(lbl_msj);
		
		btn_close.setVisible(false);
		lbl_msj.setVisible(false);
	}
	
	public LoadingFrame(final LoteVO lote, final ConfiguracionVO conf) {
		this.conf=conf;
		this.lote=lote;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		setBounds(100, 100, 602, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		jprogress.setBounds(140, 65, 308, 260);
		contentPane.add(jprogress);
		btn_close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				closeWindow();
			}
		});
		btn_close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		btn_close.setBounds(518, 11, 74, 74);
		contentPane.add(btn_close);
		btn_close.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, 74, 50);
		label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Cancel_50px.png")));
		btn_close.add(label);
		
		JLabel lblCerrar = new JLabel("Cerrar");
		lblCerrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCerrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCerrar.setBounds(0, 49, 74, 14);
		btn_close.add(lblCerrar);
		
		
		lbl_msj.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_msj.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_msj.setBounds(10, 344, 582, 37);
		contentPane.add(lbl_msj);
		
		btn_close.setVisible(false);
		lbl_msj.setVisible(false);
	}
	
	public void updateGraphics(int num) {		
		 jprogress.UpdateProgress(num);
		 jprogress.repaint();
	 }
	
	public void showMensaje(String msj) {
		lbl_msj.setText(msj);
		lbl_msj.setVisible(true);
	}
	
	public void closeWindow() {
		this.dispose();
	}
	
	
	
	public void startFormato() {

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
				 showMensaje("Espere mientras se modifica en los archivos el formato de compresión.");
				 
	             for(int i=0;i<images.size();i++) {
	            	 countimages++;
	            	 met.moveToPath(lote.getRutaAlmacenamiento(), lote.getRutaAlmacenamiento()+"\\temporal\\", 
							 images.get(i).toString(), images.get(i).toString());
					 String[] parts = images.get(i).toString().split("\\.");
					 met.writeImagesFromPathToAnotherCustomFormat(lote.getRutaAlmacenamiento()+"\\temporal\\", lote.getRutaAlmacenamiento(), 
							 images.get(i).toString(), parts[0], lote.getConfiguracionEscaner().getTipoArchivos().getExtencion());
					 		 			            			 
					 float result=((i+1)*100)/images.size();
	 					updateGraphics((int) result);
	            	 			            	 
	             }	 
	             try {
					FileUtils.deleteDirectory(new File(lote.getRutaAlmacenamiento()+"\\temporal\\"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	             btn_close.setVisible(true);
	             showMensaje("El proceso de cambio formato de copresión se completó satisfactoriamente.");
			 }
		 }).start();
		/****************/
		
	}
	
	public void copyFilesFromDirectoryToAnother(final List<String> listImages, final String pathOriginal, final String pathDestino) {
		/********************/
		new Thread(new Runnable(){
			 @Override
			public void run() {
				 showMensaje("Espere mientras se copian los archivos desde la ruta indicada.");
				 
				 for(int i=0;i<listImages.size();i++) {
//						System.out.println("nomenclatura a generar: "+listconfifEscaner.get(currentConfiguration).getNombreNomenclatura());
//						String nomim=met.parsearNumeroANomenclatura(countimages, listconfifEscaner.get(currentConfiguration).getNombreNomenclatura(), '#');
						String[] parts = listImages.get(i).toString().split("\\.");
						met.copyToPath(pathOriginal, pathDestino, 
								listImages.get(i).toString(), parts[0]);
					
						float result=((i+1)*100)/listImages.size();
						updateGraphics((int) result);
					}
				 btn_close.setVisible(true);
	             showMensaje("El proceso de copiado de archivos se completó satisfactoriamente.");
			 }
		 }).start();
		/****************/
		
	}
	
	public void startRenombrado() {

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
				 showMensaje("Espere mientras se realiza el nombrado de los archivos conforme a la nomenclatura indicada.");
				 
	             for(int i=0;i<images.size();i++) {
	            	 countimages++;
	            	 String nomim=met.parsearNumeroANomenclatura(countimages, lote.getConfiguracionEscaner().getNombreNomenclatura(), '#');
					 met.moveToPath(lote.getRutaAlmacenamiento(), lote.getRutaAlmacenamiento()+"\\temporal\\", 
							 images.get(i).toString(), images.get(i).toString());
					 
					 met.copyToPath(lote.getRutaAlmacenamiento()+"\\temporal\\", lote.getRutaAlmacenamiento(), 
							 images.get(i).toString(), nomim);
					 
					 float result=((i+1)*100)/images.size();
	 					updateGraphics((int) result);
	            	 			            	 
	             }	 
	             try {
					FileUtils.deleteDirectory(new File(lote.getRutaAlmacenamiento()+"\\temporal\\"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	             btn_close.setVisible(true);
	             showMensaje("El proceso de renombrado de los archivos se completó satisfactoriamente.");
			 }
		 }).start();
		/****************/
	}
	
	
	public List<String> getAllImages(){
		List<String> imagesall = new ArrayList<String>();	
		imagesall=met.getImages(lote.getRutaAlmacenamiento(), null, false);
		System.out.println("hay en total: "+imagesall.size());
		return imagesall;
	}
}
