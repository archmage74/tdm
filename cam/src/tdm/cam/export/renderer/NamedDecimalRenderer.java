package tdm.cam.export.renderer;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

import javax.swing.text.NumberFormatter;

import com.floreysoft.jmte.NamedRenderer;
import com.floreysoft.jmte.RenderFormatInfo;

public final class NamedDecimalRenderer implements NamedRenderer {

	public static final String DEFAULT_PATTERN = "0.##";
	
	@Override
	public RenderFormatInfo getFormatInfo() {
		return new RenderFormatInfo() {
		};
	}

	@Override
	public String getName() {
		return "decimal";
	}

	@Override
	public Class<?>[] getSupportedClasses() {
		return new Class[] { Integer.class, Long.class, Float.class, Double.class, Byte.class };
	}

	@Override
	public String render(Object value, String pattern) {
		String patternToUse = DEFAULT_PATTERN;
		if (pattern != null) {
			patternToUse = pattern;
		}
		
		String format = null;
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator('.');
		symbols.setGroupingSeparator(',');
		DecimalFormat decimalFormat = new DecimalFormat(patternToUse, symbols);
		NumberFormatter formatter = new NumberFormatter(decimalFormat);
		if (value != null) {
			try {
				format = formatter.valueToString(value);
			} catch (ParseException e) {
				throw new RuntimeException("illegal value or pattern '" + value + "'to format decimal number.", e);
			}
		}
		return format;
	}
}
