package com.dms.dmssevent.viewmodel;

import com.dms.dmssevent.models.TotalBookingGamesResonse;

public interface StatusInterface {

    public void onSuccess(TotalBookingGamesResonse gamesResonse);

    public void onError(String error);
}
