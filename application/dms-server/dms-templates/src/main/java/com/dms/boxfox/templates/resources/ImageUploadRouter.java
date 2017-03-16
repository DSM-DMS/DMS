package com.dms.boxfox.templates.resources;

import com.google.common.io.Files;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.RoutingContext;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;

import java.io.*;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by boxfox on 2017-03-15.
 */

@RouteRegistration(path = "/upload/image/", method = {HttpMethod.POST})
public class ImageUploadRouter implements Handler<RoutingContext> {
    private static final String[] extensions = {"jpg", "png", "gif"};
    private UserManager userManager;

    public ImageUploadRouter() {
        userManager = new UserManager();
    }

    @Override
    public void handle(RoutingContext context) {
        if (userManager.isLogined(context)) {
            String id = userManager.getIdFromSession(context);
            for (FileUpload upload : context.fileUploads()) {
                String extension = Files.getFileExtension(upload.fileName());
                File file = new File(upload.uploadedFileName());
                if (checkExtensions(extension)) {
                    File files = new File(file.getParent() + "\\" + id);
                    try {
                        if (files.exists()) {
                            files.delete();
                        }
                        files.createNewFile();
                        FileInputStream fin = new FileInputStream(file);
                        FileOutputStream fout = new FileOutputStream(files);
                        byte[] bytes = new byte[1024];
                        int len = 0;
                        while ((len = fin.read(bytes)) > -1) {
                            fout.write(bytes, 0, len);
                        }
                        fin.close();
                        fout.flush();
                        fout.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    context.response().setStatusCode(400);
                    context.response().setStatusMessage("It's Not Image File").end();
                    context.response().close();
                }
                file.delete();
            }
            if (!context.response().ended()) {
                context.response().setStatusCode(200).end();
                context.response().close();
            }
        } else {
            context.response().setStatusCode(400);
            context.response().setStatusMessage("Need Login").end();
            context.response().close();
        }
    }

    private boolean checkExtensions(String extension) {
        extension = extension.toLowerCase();
        for (String str : extensions) {
            if (extension.equals(str))
                return true;
        }
        return false;
    }
}
