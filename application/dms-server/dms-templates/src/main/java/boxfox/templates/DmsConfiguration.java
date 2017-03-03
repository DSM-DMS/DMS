package boxfox.templates;

import java.util.Locale;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

public class DmsConfiguration {
	
	public static Configuration getConfiguration(){
		Configuration cfg = new Configuration();
		cfg.setClassForTemplateLoading(TestRouter.class, "/templates");
		
		cfg.setIncompatibleImprovements(new Version(2, 3, 20));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setLocale(Locale.KOREA);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		return cfg;
	}

}
