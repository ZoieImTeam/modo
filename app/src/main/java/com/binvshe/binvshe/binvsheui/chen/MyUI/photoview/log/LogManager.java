/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.binvshe.binvshe.binvsheui.chen.MyUI.photoview.log;

import android.util.Log;

import com.binvshe.binvshe.binvsheui.chen.MyUI.photoview.log.Logger;
import com.binvshe.binvshe.binvsheui.chen.MyUI.photoview.log.LoggerDefault;

/**
 * class that holds the {@link com.binvshe.binvshe.binvsheui.chen.MyUI.photoview.log.Logger} for this library, defaults to {@link com.binvshe.binvshe.binvsheui.chen.MyUI.photoview.log.LoggerDefault} to send logs to android {@link Log}
 */
public final class LogManager {

    private static com.binvshe.binvshe.binvsheui.chen.MyUI.photoview.log.Logger logger = new com.binvshe.binvshe.binvsheui.chen.MyUI.photoview.log.LoggerDefault();

    public static void setLogger(com.binvshe.binvshe.binvsheui.chen.MyUI.photoview.log.Logger newLogger) {
        logger = newLogger;
    }

    public static com.binvshe.binvshe.binvsheui.chen.MyUI.photoview.log.Logger getLogger() {
        return logger;
    }

}
