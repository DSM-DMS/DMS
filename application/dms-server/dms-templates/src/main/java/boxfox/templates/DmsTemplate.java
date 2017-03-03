package boxfox.templates;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Locale;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;
import freemarker.template.Version;

public class DmsTemplate {
	private static Configuration configuration;
	private String name;

	public DmsTemplate(String name) {
		if (!name.endsWith(".tls"))
			name += ".tls";
		this.name = name;
	}

	public String process(Object datamodel) throws IOException, TemplateException {
		Template template = getConfiguration().getTemplate("helloworld.ftl");

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
		template.process(datamodel, writer);
		return out.toString();
	}

	private static Configuration getConfiguration() {
		if (configuration == null) {
			configuration = new Configuration();
			configuration.setClassForTemplateLoading(TestRouter.class, "/templates");

			configuration.setIncompatibleImprovements(new Version(2, 3, 20));
			configuration.setDefaultEncoding("UTF-8");
			configuration.setLocale(Locale.KOREA);
			configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		}
		return configuration;
	}

}
