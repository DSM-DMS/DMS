package com.dms.templates;

import java.io.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

public class DmsTemplate {
    private static Configuration configuration;
    private String name;
    private Map<String, Object> input;

    public DmsTemplate(String name) {
        if (!name.endsWith(".tls"))
            name += ".tls";
        this.name = name;
        this.input = new HashMap<String, Object>();
    }

    public void put(String key, Object value) {
        input.put(key, value);
    }

    public String process() throws IOException, TemplateException {
        if (new File("WEB-INF/" + name).exists())
            try {
                Template template = getConfiguration().getTemplate(name);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
                template.process(input, writer);
                return out.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        name = name.substring(0, name.lastIndexOf(".tls")) + ".html";
        return readFile("WEB-INF/" + name);
    }

    private String readFile(String path) throws IOException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        String text = scanner.useDelimiter("\\A").next();
        scanner.close();
        return text;
    }

    private static Configuration getConfiguration() {
        if (configuration == null) {
            configuration = new Configuration();
            try {
                if (!new File("WEB-INF").exists())
                    new File("WEB-INF").mkdir();
                configuration.setDirectoryForTemplateLoading(new File("WEB-INF"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            configuration.setIncompatibleImprovements(new Version(2, 3, 20));
            configuration.setDefaultEncoding("UTF-8");
            configuration.setLocale(Locale.KOREA);
            configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        }
        return configuration;
    }

}
