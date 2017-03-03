package boxfox.templates;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.boxfox.dms.utilities.actions.RouteRegisteration;

import freemarker.template.TemplateException;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegisteration(path = "test/aa", method={HttpMethod.POST})
public class TestRouter implements Handler<RoutingContext> {
	public void handle(RoutingContext context) {
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("title", "Vogella example");
		try {
			System.out.println(new DmsTemplate("hello").process(input));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
}
