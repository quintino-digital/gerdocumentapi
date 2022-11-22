package digital.quintino.gerdocumentapi.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility {
	
	public static final String FORMATO_DDMMAAAA = "dd/MM/yyyy";
	public static final String FORMATO_DDMMAAAAHHMMSS = "dd/MM/yyyy HH:mm:ss";
	
	public static String formatarData(Date date, String formato) {
		return new SimpleDateFormat(formato).format(date);
	}

}
