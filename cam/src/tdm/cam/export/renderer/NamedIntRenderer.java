package tdm.cam.export.renderer;

import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.text.NumberFormatter;

import com.floreysoft.jmte.NamedRenderer;
import com.floreysoft.jmte.RenderFormatInfo;
import com.floreysoft.jmte.renderer.OptionRenderFormatInfo;

public final class NamedIntRenderer implements NamedRenderer {

	@Override
	public RenderFormatInfo getFormatInfo() {
		return new OptionRenderFormatInfo(new String[] { "" });
	}

	@Override
	public String getName() {
		return "int";
	}

	@Override
	public Class<?>[] getSupportedClasses() {
		return new Class[] { Integer.class, Long.class, Float.class, Double.class, Byte.class };
	}

	@Override
	public String render(Object value, String pattern) {
		String format = null;
		NumberFormatter formatter = new NumberFormatter(NumberFormat.getIntegerInstance());
		if (value != null) {
			try {
				format = formatter.valueToString(value);
			} catch (ParseException e) {
				throw new RuntimeException("illegal value '" + value + "'to format as int.", e);
			}
		}
		return format;
	}
}
