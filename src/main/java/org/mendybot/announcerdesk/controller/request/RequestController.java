package org.mendybot.announcerdesk.controller.request;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.JSONObject;
import org.mendybot.announcerdesk.model.request.RequestModel;
import org.mendybot.announcerdesk.model.request.Server;

public class RequestController
{
  public static final int DEFAULT_SOUND_LEVEL = 65;
  public static final String P_SOUND_LEVEL = "sound-level";
  public static final String P_SAY_TEXT = "say";
  public static final String P_SAY_TEXT_REPEAT = "say-repeat";
  public static final String P_DISPLAY_TEXT = "display";
  public static final String P_DISPLAY_TEXT_FONT = "display-font";
  public static final String P_DISPLAY_TEXT_RGB = "display-rgb";
  public static final String P_DISPLAY_TEXT_REPEAT = "display-repeat";
  private RequestModel model;

  public RequestController(RequestModel model)
  {
    this.model = model;
  }

  public void announce(List<Server> servers, int soundLevel, String sayText, String displayText, String displayFont, Color displayColor, Integer displayRepeat)
  {
    JSONObject json = new JSONObject();
    json.put(P_SOUND_LEVEL, soundLevel);
    json.put(P_SAY_TEXT, sayText);
    json.put(P_SAY_TEXT_REPEAT, 1);
    json.put(P_DISPLAY_TEXT, displayText);
    json.put(P_DISPLAY_TEXT_FONT, displayFont);
    json.put(P_DISPLAY_TEXT_RGB, computeRGB(displayColor));
    json.put(P_DISPLAY_TEXT_REPEAT, displayRepeat);
    
    send(servers, json.toString());
  }

  private String computeRGB(Color c)
  {
    return c.getRed()+","+c.getGreen()+","+c.getBlue();
  }

  private void send(List<Server> servers, String json)
  {
    ArrayList<String> status = new ArrayList<>();
    for (Server s : servers) {
      URL urlx = s.getUrl();
      try
      {
        String endPoint = "/request";
        URL url = new URL(urlx.toString()+endPoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput( true );
        connection.setInstanceFollowRedirects( false );
        connection.setRequestMethod( "POST" );
        connection.setRequestProperty( "Content-Type", "application/json"); 
        connection.setRequestProperty( "charset", "utf-8");
        connection.setRequestProperty( "Content-Length", Integer.toString( json.length() ));
        connection.setUseCaches( false );       
        
        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write(json);
        writer.flush();
        InputStream in  = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder result = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null) {
            result.append(line);
        }
        status.add(s.getName()+" : success");
      }
      catch (MalformedURLException e)
      {
        status.add(s.getName()+" : failed - bad address");
        e.printStackTrace();
      }
      catch (IOException e)
      {
        status.add(s.getName()+" : failed - "+e.getMessage());
      }
    }
    model.setRequestStatus(status);
  }

}
