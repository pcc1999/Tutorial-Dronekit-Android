package com.upc.tutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.o3dr.android.client.ControlTower;
import com.o3dr.android.client.Drone;
import com.o3dr.android.client.apis.ControlApi;
import com.o3dr.android.client.apis.VehicleApi;
import com.o3dr.android.client.interfaces.DroneListener;
import com.o3dr.android.client.interfaces.LinkListener;
import com.o3dr.android.client.interfaces.TowerListener;
import com.o3dr.services.android.lib.drone.attribute.AttributeEvent;
import com.o3dr.services.android.lib.drone.attribute.AttributeType;
import com.o3dr.services.android.lib.drone.connection.ConnectionParameter;
import com.o3dr.services.android.lib.drone.property.State;
import com.o3dr.services.android.lib.drone.property.VehicleMode;
import com.o3dr.services.android.lib.gcs.link.LinkConnectionStatus;
import com.o3dr.services.android.lib.model.AbstractCommandListener;
import com.o3dr.services.android.lib.model.SimpleCommandListener;
import com.upc.tutorial.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements DroneListener, TowerListener, LinkListener {

    private ActivityMainBinding mainBinding;
    private ControlTower controlTower;
    private Drone drone;
    private final Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Se inicializa el binding de datos entre la clase de la Activity y su archivo de layout
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //Se inicializan las variables booleanas (no es estrictamente necesario)
        mainBinding.setIsArmed(false);
        mainBinding.setIsConnected(false);
        mainBinding.setIsFlying(false);

        //Se inicializa la Control Tower y el dron
        this.controlTower = new ControlTower(this);
        this.drone = new Drone(this);
        if (!this.controlTower.isTowerConnected()) {
            //Se conecta la control tower a su listener (esta activity)
            this.controlTower.connect(this);
        }
    }

    @Override
    public void onDroneEvent(String event, Bundle extras) {
        switch (event) {
            case AttributeEvent.STATE_CONNECTED:
                Log.d("Connection", "onDroneEvent: Connected!");
                break;

            case AttributeEvent.STATE_DISCONNECTED:
                Log.d("Connection", "onDroneEvent: Disconnected!");
                break;

            default:
                //Simple gestión de estados: cuando se recibe un cualquier evento
                // se comprueba si el dron esta conectado/armado para informar al binding
                State stateAtt = drone.getAttribute(AttributeType.STATE);
                boolean change = false;
                if (!mainBinding.getIsFlying().equals(stateAtt.isFlying())) {
                    Toast.makeText(this, stateAtt.isFlying() ? "Flying" : "On earth", Toast.LENGTH_SHORT).show();
                    mainBinding.setIsFlying(stateAtt.isFlying());
                    change = true;
                }
                if (!mainBinding.getIsArmed().equals(stateAtt.isArmed())) {
                    Toast.makeText(this, stateAtt.isArmed() ? "Armed" : "Disarmed", Toast.LENGTH_SHORT).show();
                    mainBinding.setIsArmed(stateAtt.isArmed());
                    change = true;
                }
                if (!mainBinding.getIsConnected().equals(stateAtt.isConnected())) {
                    Toast.makeText(this, stateAtt.isConnected() ? "Connected" : "Disconnected", Toast.LENGTH_SHORT).show();
                    mainBinding.setIsConnected(stateAtt.isConnected());
                    change = true;
                }
                if (change) {
                    mainBinding.invalidateAll();
                }
                break;
        }
    }

    @Override
    public void onDroneServiceInterrupted(String errorMsg) {
        this.drone.disconnect();
    }

    @Override
    public void onLinkStateUpdated(@NonNull LinkConnectionStatus connectionStatus) {
        if (LinkConnectionStatus.FAILED.equals(connectionStatus.getStatusCode())) {
            Bundle extras = connectionStatus.getExtras();
            String msg = null;
            if (extras != null) {
                msg = extras.getString(LinkConnectionStatus.EXTRA_ERROR_MSG);
            }
            Log.d("failed", "connectionfailed: " + msg);
        }
    }

    @Override
    public void onTowerConnected() {
        this.controlTower.registerDrone(this.drone, this.handler);
        this.drone.registerDroneListener(this);
    }

    @Override
    public void onTowerDisconnected() {

    }

    public void onConnectClick(View v) {
        if (!drone.isConnected()) {
            //TESTING
            //ConnectionParameter connectionParams = ConnectionParameter.newTcpConnection("192.168.1.69", 5762, null, 1);
            //ConnectionParameter connectionParams = ConnectionParameter.newTcpConnection("192.168.1.54", 5762, null, 1);
            //ConnectionParameter connectionParams = ConnectionParameter.newTcpConnection("192.168.1.42", 5762, null, 1);
            //PROD
            ConnectionParameter connectionParams = ConnectionParameter.newUsbConnection(57600, null);
            try {
                this.controlTower.registerDrone(this.drone, this.handler);
                this.drone.registerDroneListener(this);
                this.drone.connect(connectionParams, new LinkListener() {
                    @Override
                    public void onLinkStateUpdated(@NonNull LinkConnectionStatus connectionStatus) {
                        //Toast.makeText(mainActivity, "connectionStatusChanged: " + connectionStatus, Toast.LENGTH_LONG).show();
                        Log.d(getClass().getCanonicalName(), "New connection status: " + connectionStatus.getStatusCode());
                        if (Objects.equals(connectionStatus.getStatusCode(), LinkConnectionStatus.CONNECTED)) {
                        }
                    }
                });
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onArmClick(View v) {
        //Seteamos el control manual del dron
        ControlApi.getApi(this.drone).enableManualControl(true, new ControlApi.ManualControlStateListener() {
            @Override
            public void onManualControlToggled(boolean isEnabled) {
                Log.d("ManualControl", "onManualControlToggled: Manual Control is " + isEnabled);
            }
        });
        VehicleApi.getApi(this.drone).arm(true, true, new SimpleCommandListener() {
            @Override
            public void onError(int executionError) {
                //Error when arming
                Log.d("error", "error on arming");
            }

            @Override
            public void onTimeout() {
                //Timeout on arming
                Log.d("timeout", "timeout on arming");
            }

            @Override
            public void onSuccess() {
                //Success arming
                Log.d("success", "success on arming");
            }
        });
    }

    public void onTakeOffRTLClick(View v) {
        if (mainBinding.getIsFlying()) {
            //Si está volando, RTL
            VehicleApi.getApi(this.drone).setVehicleMode(VehicleMode.COPTER_RTL, new AbstractCommandListener() {
                @Override
                public void onSuccess() {
                    Log.d("success", "success start RTL");
                }

                @Override
                public void onError(int executionError) {
                    Log.d("error", "error on return to launch");
                }

                @Override
                public void onTimeout() {
                    Log.d("timeout", "timeout on return to launch");
                }
            });
        } else {
            //Si no está volando, despega
            ControlApi.getApi(this.drone).takeoff(4, new AbstractCommandListener() {
                @Override
                public void onSuccess() {
                    //Taking off
                    Log.d("success", "success taking off");
                }

                @Override
                public void onError(int i) {
                    //Error when taking off
                    Log.d("error", "error on taking off");
                }

                @Override
                public void onTimeout() {
                    //Timeout taking off
                    Log.d("timeout", "timeout on taking off");
                }
            });
        }
    }
}