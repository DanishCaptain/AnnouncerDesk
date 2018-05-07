package org.mendybot.announcerdesk.model.request;

import java.util.ArrayList;
import java.util.List;

public class RequestModel
{
  private ArrayList<Server> servers = new ArrayList<>();
  private ArrayList<ServersListener> serversListeners = new ArrayList<>();
  private ArrayList<RequestStatusListener> requestStatusListeners = new ArrayList<>();

  public RequestModel()
  {
    init();
  }

  private void init()
  {
    /*
    addServer("Home", "http", "localhost", 8000);
    addServer("Phoenix", "http", "192.168.100.33", 8000);
    addServer("New York", "http", "192.168.100.34", 8000);
    addServer("Las Angeles", "http", "192.168.100.35", 8000);
    addServer("Denver", "http", "192.168.100.36", 8000);
    addServer("Seattle", "http", "192.168.100.37", 8000);
    */
    addServer("Home", "http", "localhost", 8000);
    addServer("Phoenix", "http", "192.168.1.182", 8000);

    
    /*
ANTHEM.wav  ASSEMBLY.wav  MESSCALL.wav
     */
  }

  private void addServer(String name, String a, String host, int port)
  {
    Server s = new Server(name, a, host, port);
    servers.add(s);
    notifyNew(s);
  }

  private synchronized void notifyNew(Server s)
  {
    for (ServersListener lis : serversListeners)
    {
      lis.addNew(s);
    }
  }

  public synchronized void addServersListener(ServersListener lis)
  {
    for (Server s : servers)
    {
      lis.addNew(s);
    }
    serversListeners.add(lis);
  }

  public void removeServersListener(ServersListener lis)
  {
    serversListeners.remove(lis);
  }

  public synchronized void addRequestStatusListener(RequestStatusListener lis)
  {
    requestStatusListeners.add(lis);
  }

  public void removeRequestStatusListener(RequestStatusListener lis)
  {
    requestStatusListeners.remove(lis);
  }

  public void setRequestStatus(List<String> status)
  {
    for (RequestStatusListener lis : requestStatusListeners) {
      lis.newRequestStatus(status);
    }
  }

}
