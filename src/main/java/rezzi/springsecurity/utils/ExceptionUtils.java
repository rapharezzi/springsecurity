package rezzi.springsecurity.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ExceptionUtils {

	private static List<String> errors = new ArrayList<>();

	public static String getStackTrace(Throwable throwable) {
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		throwable.printStackTrace(printWriter);
		throwable.printStackTrace();
		return writer.toString();
	}

	private static Throwable getErrorByException(Throwable exception) {
		if (exception != null && exception.getCause() != null) {
			errors.add("\n" + exception.getCause().getMessage());
			return getErrorByException(exception.getCause());
		}
		return exception;
	}

	private static String getErrors() {
		String erros = "";
		if (errors != null && !errors.isEmpty()) {
			int errorCount = errors.size();
			for (int i = (errorCount - 1); i >= 0; i--) {
				erros += "\n" + errors.get(i);
			}
		}
		return erros;
	}

	public static String getStackTraceException(Throwable exception) {
		errors.clear();
		if (exception != null) {
			getErrorByException(exception);
			return "(Exceptions) " + getErrors();			
		}
		return "";
	}

}
