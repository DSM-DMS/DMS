package com.dms.boxfox.networking;

import com.dms.boxfox.networking.datamodel.Response;

/**
 * Created by boxfox on 2017-04-19.
 */

public interface HttpBoxCallback {
    public void done(Response response);
    public void err(Exception e);
}
