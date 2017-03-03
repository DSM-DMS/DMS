package boxfox.templates;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;

@RouteRegister(Path="test/aa")
public class TestRouter implements Handler<RoutingContext>
{
	public void handle(RoutingContext context) {
		Configuration cfg = DmsConfiguration.getConfiguration();

		Map<String, Object> input = new HashMap<String, Object>();
		input.put("title", "Vogella example");

		Template template = cfg.getTemplate("helloworld.ftl");

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
		template.process(input, writer);
		System.out.println(out.toString());
	}
}
