/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE RAW_PRESSURE PACKING
package com.MAVLink.common;
import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;
import com.MAVLink.Messages.Units;
import com.MAVLink.Messages.Description;

/**
 * The RAW pressure readings for the typical setup of one absolute pressure and one differential pressure sensor. The sensor values should be the raw, UNSCALED ADC values.
 */
public class msg_raw_pressure extends MAVLinkMessage {

    public static final int MAVLINK_MSG_ID_RAW_PRESSURE = 28;
    public static final int MAVLINK_MSG_LENGTH = 16;
    private static final long serialVersionUID = MAVLINK_MSG_ID_RAW_PRESSURE;

    
    /**
     * Timestamp (UNIX Epoch time or time since system boot). The receiving end can infer timestamp format (since 1.1.1970 or since system boot) by checking for the magnitude of the number.
     */
    @Description("Timestamp (UNIX Epoch time or time since system boot). The receiving end can infer timestamp format (since 1.1.1970 or since system boot) by checking for the magnitude of the number.")
    @Units("us")
    public long time_usec;
    
    /**
     * Absolute pressure (raw)
     */
    @Description("Absolute pressure (raw)")
    @Units("")
    public short press_abs;
    
    /**
     * Differential pressure 1 (raw, 0 if nonexistent)
     */
    @Description("Differential pressure 1 (raw, 0 if nonexistent)")
    @Units("")
    public short press_diff1;
    
    /**
     * Differential pressure 2 (raw, 0 if nonexistent)
     */
    @Description("Differential pressure 2 (raw, 0 if nonexistent)")
    @Units("")
    public short press_diff2;
    
    /**
     * Raw Temperature measurement (raw)
     */
    @Description("Raw Temperature measurement (raw)")
    @Units("")
    public short temperature;
    

    /**
     * Generates the payload for a mavlink message for a message of this type
     * @return
     */
    @Override
    public MAVLinkPacket pack() {
        MAVLinkPacket packet = new MAVLinkPacket(MAVLINK_MSG_LENGTH,isMavlink2);
        packet.sysid = sysid;
        packet.compid = compid;
        packet.msgid = MAVLINK_MSG_ID_RAW_PRESSURE;

        packet.payload.putUnsignedLong(time_usec);
        packet.payload.putShort(press_abs);
        packet.payload.putShort(press_diff1);
        packet.payload.putShort(press_diff2);
        packet.payload.putShort(temperature);
        
        if (isMavlink2) {
            
        }
        return packet;
    }

    /**
     * Decode a raw_pressure message into this class fields
     *
     * @param payload The message to decode
     */
    @Override
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();

        this.time_usec = payload.getUnsignedLong();
        this.press_abs = payload.getShort();
        this.press_diff1 = payload.getShort();
        this.press_diff2 = payload.getShort();
        this.temperature = payload.getShort();
        
        if (isMavlink2) {
            
        }
    }

    /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_raw_pressure() {
        this.msgid = MAVLINK_MSG_ID_RAW_PRESSURE;
    }

    /**
     * Constructor for a new message, initializes msgid and all payload variables
     */
    public msg_raw_pressure( long time_usec, short press_abs, short press_diff1, short press_diff2, short temperature) {
        this.msgid = MAVLINK_MSG_ID_RAW_PRESSURE;

        this.time_usec = time_usec;
        this.press_abs = press_abs;
        this.press_diff1 = press_diff1;
        this.press_diff2 = press_diff2;
        this.temperature = temperature;
        
    }

    /**
     * Constructor for a new message, initializes everything
     */
    public msg_raw_pressure( long time_usec, short press_abs, short press_diff1, short press_diff2, short temperature, int sysid, int compid, boolean isMavlink2) {
        this.msgid = MAVLINK_MSG_ID_RAW_PRESSURE;
        this.sysid = sysid;
        this.compid = compid;
        this.isMavlink2 = isMavlink2;

        this.time_usec = time_usec;
        this.press_abs = press_abs;
        this.press_diff1 = press_diff1;
        this.press_diff2 = press_diff2;
        this.temperature = temperature;
        
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a mavlink packet
     *
     */
    public msg_raw_pressure(MAVLinkPacket mavLinkPacket) {
        this.msgid = MAVLINK_MSG_ID_RAW_PRESSURE;

        this.sysid = mavLinkPacket.sysid;
        this.compid = mavLinkPacket.compid;
        this.isMavlink2 = mavLinkPacket.isMavlink2;
        unpack(mavLinkPacket.payload);
    }

              
    /**
     * Returns a string with the MSG name and data
     */
    @Override
    public String toString() {
        return "MAVLINK_MSG_ID_RAW_PRESSURE - sysid:"+sysid+" compid:"+compid+" time_usec:"+time_usec+" press_abs:"+press_abs+" press_diff1:"+press_diff1+" press_diff2:"+press_diff2+" temperature:"+temperature+"";
    }

    /**
     * Returns a human-readable string of the name of the message
     */
    @Override
    public String name() {
        return "MAVLINK_MSG_ID_RAW_PRESSURE";
    }
}
        