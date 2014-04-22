package ru.RealRace.camera;


import ru.RealRace.console.ConsoleHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CameraHelper {

    public void startCamera() {
        List<String> attributes = new ArrayList<String>();
        attributes.add("gst-launch");
        attributes.add("udpsrc port=3000");
        attributes.add("! \"application/x-rtp,");
        attributes.add("media=(string)video,");
        attributes.add("clock-rate=(int)90000,");
        attributes.add("encoding-name=(string)H264,");
        attributes.add("sprop-parameter-sets=(string)\\\"Z2QAKK2EBUViuKxUdCAqKxXFYqOhAVFYrisVHQgKisVxWKjoQFRWK4rFR0ICorFcVio6ECSFITk8nyfk/k/J8nm5s00IEkKQnJ5Pk/J/J+T5PNzZprQFAeyA\\,aO48sA\\=\\=\\\",");
        attributes.add("payload=(int)96,");
        attributes.add("ssrc=(guint)2498431066,");
        attributes.add("clock-base=(guint)297251943,");
        attributes.add("seqnum-base=(guint)34949\"");
        attributes.add("! rtph264depay");
        attributes.add("! queue");
        attributes.add("! ffdec_h264");
        attributes.add("! ffmpegcolorspace");
        attributes.add("! videoscale");
        attributes.add("! ximagesink");
        attributes.add("sync=false");
        attributes.add("show-preroll-frame=false");

        StringBuilder s = new StringBuilder();
        for (String attr : attributes) {
            s.append(attr).append(" ");
        }

        try {
            ConsoleHelper.useCommand(s.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
