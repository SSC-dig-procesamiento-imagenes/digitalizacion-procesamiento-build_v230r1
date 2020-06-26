package mx.com.app.services;

import mx.com.teclo.base.context.BeanLocator;
import mx.com.teclo.base.vo.bitacora.BitacoraVO;
import mx.com.teclo.base.vo.configuracion.ConfiguracionVO;
import mx.com.teclo.restclient.BitacoraRestClient;



public class BitacoraRestClientService {
	private BitacoraRestClient bitacoraRestClient;

	public boolean guardarBitacora(BitacoraVO bitacoraVO, ConfiguracionVO conf){
		System.out.println("llegaste aqui a guardar bitácora");
		bitacoraRestClient = (BitacoraRestClient) BeanLocator.getService("bitacoraRestClient");
		boolean resultado = bitacoraRestClient.guardarBitacora(bitacoraVO, conf);
		return resultado;
	}
}
