package org.mendybot.announcerdesk.controller.announcement;

import org.mendybot.announcer.common.engine.EngineClient;
import org.mendybot.announcer.common.model.dto.QuickAnnouncementRequest;
import org.mendybot.announcer.common.model.dto.QuickAnnouncementResponse;
import org.mendybot.announcer.fault.ExecuteException;
import org.mendybot.announcerdesk.model.announcement.AnnouncementModel;

public class AnnouncementController
{
  public static final String P_IS_ALARM = "is-alarm";
  public static final int DEFAULT_SOUND_LEVEL = 65;
  public static final String P_SOUND_LEVEL = "sound-level";
  public static final String P_SAY_TEXT = "say";
  public static final String P_SAY_TEXT_REPEAT = "say-repeat";
  public static final String P_DISPLAY_TEXT = "display";
  public static final String P_DISPLAY_TEXT_FONT = "display-font";
  public static final String P_DISPLAY_TEXT_RGB = "display-rgb";
  public static final String P_DISPLAY_TEXT_REPEAT = "display-repeat";
  private EngineClient client;

  public AnnouncementController(AnnouncementModel model)
  {
    client = new EngineClient();
  }

  public QuickAnnouncementResponse announceQuick(String text) throws ExecuteException
  {
    QuickAnnouncementRequest request = new QuickAnnouncementRequest();
    request.setText(text);
    return send(request);
  }

  private QuickAnnouncementResponse send(QuickAnnouncementRequest request) throws ExecuteException
  {
    return client.send(request);
  }

}
