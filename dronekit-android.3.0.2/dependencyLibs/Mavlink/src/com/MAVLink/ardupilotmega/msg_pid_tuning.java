/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE PID_TUNING PACKING
package com.MAVLink.ardupilotmega;
import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;
import com.MAVLink.Messages.Units;
import com.MAVLink.Messages.Description;

/**
 * PID tuning information.
 */
public class msg_pid_tuning extends MAVLinkMessage {

    public static final int MAVLINK_MSG_ID_PID_TUNING = 194;
    public static final int MAVLINK_MSG_LENGTH = 33;
    private static final long serialVersionUID = MAVLINK_MSG_ID_PID_TUNING;

    
    /**
     * Desired rate.
     */
    @Description("Desired rate.")
    @Units("")
    public float desired;
    
    /**
     * Achieved rate.
     */
    @Description("Achieved rate.")
    @Units("")
    public float achieved;
    
    /**
     * FF component.
     */
    @Description("FF component.")
    @Units("")
    public float FF;
    
    /**
     * P component.
     */
    @Description("P component.")
    @Units("")
    public float P;
    
    /**
     * I component.
     */
    @Description("I component.")
    @Units("")
    public float I;
    
    /**
     * D component.
     */
    @Description("D component.")
    @Units("")
    public float D;
    
    /**
     * Axis.
     */
    @Description("Axis.")
    @Units("")
    public short axis;
    
    /**
     * Slew rate.
     */
    @Description("Slew rate.")
    @Units("")
    public float SRate;
    
    /**
     * P/D oscillation modifier.
     */
    @Description("P/D oscillation modifier.")
    @Units("")
    public float PDmod;
    

    /**
     * Generates the payload for a mavlink message for a message of this type
     * @return
     */
    @Override
    public MAVLinkPacket pack() {
        MAVLinkPacket packet = new MAVLinkPacket(MAVLINK_MSG_LENGTH,isMavlink2);
        packet.sysid = sysid;
        packet.compid = compid;
        packet.msgid = MAVLINK_MSG_ID_PID_TUNING;

        packet.payload.putFloat(desired);
        packet.payload.putFloat(achieved);
        packet.payload.putFloat(FF);
        packet.payload.putFloat(P);
        packet.payload.putFloat(I);
        packet.payload.putFloat(D);
        packet.payload.putUnsignedByte(axis);
        
        if (isMavlink2) {
             packet.payload.putFloat(SRate);
             packet.payload.putFloat(PDmod);
            
        }
        return packet;
    }

    /**
     * Decode a pid_tuning message into this class fields
     *
     * @param payload The message to decode
     */
    @Override
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();

        this.desired = payload.getFloat();
        this.achieved = payload.getFloat();
        this.FF = payload.getFloat();
        this.P = payload.getFloat();
        this.I = payload.getFloat();
        this.D = payload.getFloat();
        this.axis = payload.getUnsignedByte();
        
        if (isMavlink2) {
             this.SRate = payload.getFloat();
             this.PDmod = payload.getFloat();
            
        }
    }

    /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_pid_tuning() {
        this.msgid = MAVLINK_MSG_ID_PID_TUNING;
    }

    /**
     * Constructor for a new message, initializes msgid and all payload variables
     */
    public msg_pid_tuning( float desired, float achieved, float FF, float P, float I, float D, short axis, float SRate, float PDmod) {
        this.msgid = MAVLINK_MSG_ID_PID_TUNING;

        this.desired = desired;
        this.achieved = achieved;
        this.FF = FF;
        this.P = P;
        this.I = I;
        this.D = D;
        this.axis = axis;
        this.SRate = SRate;
        this.PDmod = PDmod;
        
    }

    /**
     * Constructor for a new message, initializes everything
     */
    public msg_pid_tuning( float desired, float achieved, float FF, float P, float I, float D, short axis, float SRate, float PDmod, int sysid, int compid, boolean isMavlink2) {
        this.msgid = MAVLINK_MSG_ID_PID_TUNING;
        this.sysid = sysid;
        this.compid = compid;
        this.isMavlink2 = isMavlink2;

        this.desired = desired;
        this.achieved = achieved;
        this.FF = FF;
        this.P = P;
        this.I = I;
        this.D = D;
        this.axis = axis;
        this.SRate = SRate;
        this.PDmod = PDmod;
        
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a mavlink packet
     *
     */
    public msg_pid_tuning(MAVLinkPacket mavLinkPacket) {
        this.msgid = MAVLINK_MSG_ID_PID_TUNING;

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
        return "MAVLINK_MSG_ID_PID_TUNING - sysid:"+sysid+" compid:"+compid+" desired:"+desired+" achieved:"+achieved+" FF:"+FF+" P:"+P+" I:"+I+" D:"+D+" axis:"+axis+" SRate:"+SRate+" PDmod:"+PDmod+"";
    }

    /**
     * Returns a human-readable string of the name of the message
     */
    @Override
    public String name() {
        return "MAVLINK_MSG_ID_PID_TUNING";
    }
}
        