package mx.com.app.services;

import mx.com.teclo.base.context.BeanLocator;
import mx.com.teclo.base.vo.lotes.LoteVO;
import mx.com.teclo.escanner.Scanner;

public class ScannerService {

	private Scanner scanner;
	
	public void scan(LoteVO lote, int startNumber, boolean isReemplazoImg, String currentImage) {
		scanner = (Scanner)BeanLocator.getService("scanner");
		 scanner.consultaTest(lote, startNumber, isReemplazoImg, currentImage);
	}
	
}
