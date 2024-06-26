package org.whispersystems.signalservice.api.messages.calls;

import org.whispersystems.signalservice.internal.push.CallMessage;

public class OfferMessage {

  private final long   id;
  private final Type   type;
  private final byte[] opaque;

  public OfferMessage(long id, Type type, byte[] opaque) {
    this.id     = id;
    this.type   = type;
    this.opaque = opaque;
  }

  public long getId() {
    return id;
  }

  public Type getType() {
    return type;
  }

  public byte[] getOpaque() {
    return opaque;
  }

  public enum Type {
    AUDIO_CALL("audio_call", CallMessage.Offer.Type.OFFER_AUDIO_CALL),
    VIDEO_CALL("video_call", CallMessage.Offer.Type.OFFER_VIDEO_CALL);

    private final String code;
    private final CallMessage.Offer.Type protoType;

    Type(String code, CallMessage.Offer.Type protoType) {
      this.code      = code;
      this.protoType = protoType;
    }

    public String getCode() {
      return code;
    }

    public CallMessage.Offer.Type getProtoType() {
      return protoType;
    }

    public static Type fromProto(CallMessage.Offer.Type offerType) {
      for (Type type : Type.values()) {
        if (type.getProtoType().equals(offerType)) {
          return type;
        }
      }

      throw new IllegalArgumentException("Unexpected type: " + offerType.name());
    }

    public static Type fromCode(String code) {
      for (Type type : Type.values()) {
        if (type.getCode().equals(code)) {
          return type;
        }
      }

      throw new IllegalArgumentException("Unexpected code: " + code);
    }
  }
}
