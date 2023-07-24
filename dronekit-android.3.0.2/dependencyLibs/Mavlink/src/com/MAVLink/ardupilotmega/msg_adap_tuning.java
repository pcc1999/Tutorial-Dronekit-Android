/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE ADAP_TUNING PACKING
package com.MAVLink.ardupilotmega;
import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;
import com.MAVLink.Messages.Units;
import com.MAVLink.Messages.Description;

/**
 * Adaptive Controller tuning information.
 */
public class msg_adap_tuning extends MAVLinkMessage {

    public static final int MAVLINK_MSG_ID_ADAP_TUNING = 11010;
    public static final int MAVLINK_MSG_LENGTH = 49;
    private static final long serialVersionUID = MAVLINK_MSG_ID_ADAP_TUNING;

    
    /**
     * Desired rate.
     */
    @Description("Desired rate.")
    @Units("deg/s")
    public float desired;
    
    /**
     * Achieved rate.
     */
    @Description("Achieved rate.")
    @Units("deg/s")
    public float achieved;
    
    /**
     * Error between model and vehicle.
     */
    @Description("Error between model and vehicle.")
    @Units("")
    public float error;
    
    /**
     * Theta estimated state predictor.
     */
    @Description("Theta estimated state predictor.")
    @Units("")
    public float theta;
    
    /**
     * Omega estimated state predictor.
     */
    @Description("Omega estimated state predictor.")
    @Units("")
    public float omega;
    
    /**
     * Sigma estimated state predictor.
     */
    @Description("Sigma estimated state predictor.")
    @Units("")
    public float sigma;
    
    /**
     * Theta derivative.
     */
    @Description("Theta derivative.")
    @Units("")
    public float theta_dot;
    
    /**
     * Omega derivative.
     */
    @Description("Omega derivative.")
    @Units("")
    public float omega_dot;
    
    /**
     * Sigma derivative.
     */
    @Description("Sigma derivative.")
    @Units("")
    public float sigma_dot;
    
    /**
     * Projection operator value.
     */
    @Description("Projection operator value.")
    @Units("")
    public float f;
    
    /**
     * Projection operator derivative.
     */
    @Description("Projection operator derivative.")
    @Units("")
    public float f_dot;
    
    /**
     * u adaptive controlled output command.
     */
    @Description("u adaptive controlled output command.")
    @Units("")
    public float u;
    
    /**
     * Axis.
     */
    @Description("Axis.")
    @Units("")
    public short axis;
    

    /**
     * Generates the payload for a mavlink message for a message of this type
     * @return
     */
    @Override
    public MAVLinkPacket pack() {
        MAVLinkPacket packet = new MAVLinkPacket(MAVLINK_MSG_LENGTH,isMavlink2);
        packet.sysid = sysid;
        packet.compid = compid;
        packet.msgid = MAVLINK_MSG_ID_ADAP_TUNING;

        packet.payload.putFloat(desired);
        packet.payload.putFloat(achieved);
        packet.payload.putFloat(error);
        packet.payload.putFloat(theta);
        packet.payload.putFloat(omega);
        packet.payload.putFloat(sigma);
        packet.payload.putFloat(theta_dot);
        packet.payload.putFloat(omega_dot);
        packet.payload.putFloat(sigma_dot);
        packet.payload.putFloat(f);
        packet.payload.putFloat(f_dot);
        packet.payload.putFloat(u);
        packet.payload.putUnsignedByte(axis);
        
        if (isMavlink2) {
            
        }
        return packet;
    }

    /**
     * Decode a adap_tuning message into this class fields
     *
     * @param payload The message to decode
     */
    @Override
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();

        this.desired = payload.getFloat();
        this.achieved = payload.getFloat();
        this.error = payload.getFloat();
        this.theta = payload.getFloat();
        this.omega = payload.getFloat();
        this.sigma = payload.getFloat();
        this.theta_dot = payload.getFloat();
        this.omega_dot = payload.getFloat();
        this.sigma_dot = payload.getFloat();
        this.f = payload.getFloat();
        this.f_dot = payload.getFloat();
        this.u = payload.getFloat();
        this.axis = payload.getUnsignedByte();
        
        if (isMavlink2) {
            
        }
    }

    /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_adap_tuning() {
        this.msgid = MAVLINK_MSG_ID_ADAP_TUNING;
    }

    /**
     * Constructor for a new message, initializes msgid and all payload variables
     */
    public msg_adap_tuning( float desired, float achieved, float error, float theta, float omega, float sigma, float theta_dot, float omega_dot, float sigma_dot, float f, float f_dot, float u, short axis) {
        this.msgid = MAVLINK_MSG_ID_ADAP_TUNING;

        this.desired = desired;
        this.achieved = achieved;
        this.error = error;
        this.theta = theta;
        this.omega = omega;
        this.sigma = sigma;
        this.theta_dot = theta_dot;
        this.omega_dot = omega_dot;
        this.sigma_dot = sigma_dot;
        this.f = f;
        this.f_dot = f_dot;
        this.u = u;
        this.axis = axis;
        
    }

    /**
     * Constructor for a new message, initializes everything
     */
    public msg_adap_tuning( float desired, float achieved, float error, float theta, float omega, float sigma, float theta_dot, float omega_dot, float sigma_dot, float f, float f_dot, float u, short axis, int sysid, int compid, boolean isMavlink2) {
        this.msgid = MAVLINK_MSG_ID_ADAP_TUNING;
        this.sysid = sysid;
        this.compid = compid;
        this.isMavlink2 = isMavlink2;

        this.desired = desired;
        this.achieved = achieved;
        this.error = error;
        this.theta = theta;
        this.omega = omega;
        this.sigma = sigma;
        this.theta_dot = theta_dot;
        this.omega_dot = omega_dot;
        this.sigma_dot = sigma_dot;
        this.f = f;
        this.f_dot = f_dot;
        this.u = u;
        this.axis = axis;
        
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a mavlink packet
     *
     */
    public msg_adap_tuning(MAVLinkPacket mavLinkPacket) {
        this.msgid = MAVLINK_MSG_ID_ADAP_TUNING;

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
        return "MAVLINK_MSG_ID_ADAP_TUNING - sysid:"+sysid+" compid:"+compid+" desired:"+desired+" achieved:"+achieved+" error:"+error+" theta:"+theta+" omega:"+omega+" sigma:"+sigma+" theta_dot:"+theta_dot+" omega_dot:"+omega_dot+" sigma_dot:"+sigma_dot+" f:"+f+" f_dot:"+f_dot+" u:"+u+" axis:"+axis+"";
    }

    /**
     * Returns a human-readable string of the name of the message
     */
    @Override
    public String name() {
        return "MAVLINK_MSG_ID_ADAP_TUNING";
    }
}
        