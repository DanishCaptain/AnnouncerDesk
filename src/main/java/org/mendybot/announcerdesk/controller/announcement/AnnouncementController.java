package org.mendybot.announcerdesk.controller.announcement;

import org.mendybot.announcer.common.engine.EngineClient;
import org.mendybot.announcer.common.model.dto.ArchiveResource;
import org.mendybot.announcer.common.model.dto.LongAnnouncementRequest;
import org.mendybot.announcer.common.model.dto.LongAnnouncementResponse;
import org.mendybot.announcer.common.model.dto.QuickAnnouncementRequest;
import org.mendybot.announcer.common.model.dto.QuickAnnouncementResponse;
import org.mendybot.announcer.fault.ExecuteException;
import org.mendybot.announcerdesk.model.announcement.AnnouncementModel;

import java.util.UUID;

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
    request.setUuid(UUID.randomUUID());
    request.setText(text);
    return sendQuick(request);
  }

  public LongAnnouncementResponse announceLong(String displayText, String sayText, ArchiveResource sound) throws ExecuteException {
    LongAnnouncementRequest request = new LongAnnouncementRequest();
    request.setUuid(UUID.randomUUID());
    request.setDisplayText(displayText);
    request.setSayText(sayText);
    request.setSound(sound);
    return sendLong(request);
  }

  private QuickAnnouncementResponse sendQuick(QuickAnnouncementRequest request) throws ExecuteException
  {
    return client.send(request);
  }

  private LongAnnouncementResponse sendLong(LongAnnouncementRequest request) throws ExecuteException
  {
    return client.send(request);
  }

}
