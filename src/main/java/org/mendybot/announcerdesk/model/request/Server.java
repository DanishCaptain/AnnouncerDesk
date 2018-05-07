package org.mendybot.announcerdesk.model.request;

import java.net.MalformedURLException;
import java.net.URL;

public class Server
{
  private String name;
  private String scheme;
  private String host;
  private int port;

  public Server(String name, String scheme, String host, int port)
  {
    this.name = name;
    this.scheme = scheme;
    this.host = host;
    this.port = port;
  }

  @Override
  public String toString()
  {
    return name;
  }

  public URL getUrl()
  {
    try
    {
      return new URL(scheme+"://"+host+":"+port);
    }
    catch (MalformedURLException e)
    {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  public String getName()
  {
    return name;
  }
}
