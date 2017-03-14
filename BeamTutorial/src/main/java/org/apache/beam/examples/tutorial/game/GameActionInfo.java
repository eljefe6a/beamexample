package org.apache.beam.examples.tutorial.game;

import org.apache.beam.sdk.coders.SerializableCoder;
import org.apache.beam.sdk.coders.DefaultCoder;
import org.joda.time.Instant;
import java.io.Serializable;

/**
 * Class to hold info about a game event.
 */
@DefaultCoder(SerializableCoder.class)
public class GameActionInfo implements Serializable {
  private String user;
  private String team;
  private Integer score;
  private Instant timestamp;

  public GameActionInfo() {
  }

  public GameActionInfo(String user, String team, Integer score, Instant timestamp) {
    this.user = user;
    this.team = team;
    this.score = score;
    this.timestamp = timestamp;
  }

  public String getUser() {
    return this.user;
  }

  public String getTeam() {
    return this.team;
  }

  public Integer getScore() {
    return this.score;
  }

  public Instant getTimestamp() {
    return this.timestamp;
  }

  /**
   * The kinds of key fields that can be extracted from a
   * {@link GameActionInfo}.
   */
  public enum KeyField {
    TEAM {
      @Override
      public String extract(GameActionInfo g) {
        return g.team;
      }
    },
    USER {
      @Override
      public String extract(GameActionInfo g) {
        return g.user;
      }
    };

    public abstract String extract(GameActionInfo g);
  }
}