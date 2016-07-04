package com.beebox.blood.shewalfare;

import android.content.Intent;
import android.content.SharedPreferences;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by rampr on 4/22/2016.
 */
public class GCMTokenRefreshListenerService extends InstanceIDListenerService {

    SharedPreferences tokenrefresh;
    @Override
    public void onTokenRefresh()
    {
        Intent intent = new Intent(this,GCMRegistrationIntentService.class);

        tokenrefresh = getSharedPreferences("checktoken",MODE_PRIVATE);

        startService(intent);

    }

}
