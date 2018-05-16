package org.mendybot.announcer.desk.model.request;

import java.util.List;

public interface RequestStatusListener
{

  void newRequestStatus(List<String> status);

}
