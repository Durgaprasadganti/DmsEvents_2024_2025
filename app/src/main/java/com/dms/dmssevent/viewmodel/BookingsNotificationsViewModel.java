package com.dms.dmssevent.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dms.dmssevent.models.BookingStatusResonse;
import com.dms.dmssevent.models.TotalBookingGamesResonse;

public class BookingsNotificationsViewModel extends ViewModel {

    private MutableLiveData<TotalBookingGamesResonse> mutableLiveData;
    private MutableLiveData<BookingStatusResonse> mutableLiveDataStatusResonse;
    private TotalBookingsRepository totalBookingsRepository;

    public void init(String userId,String callFrom,int bookingPositionId,String bookingStatus){
       /* if (mutableLiveData != null){
            return;
        }*/
        totalBookingsRepository = TotalBookingsRepository.getInstance();
        if(callFrom.equalsIgnoreCase("getTotalBookings")){
            mutableLiveData = totalBookingsRepository.getTotalBookingsData(userId);
        }else if(callFrom.equalsIgnoreCase("getInvitations")){
            mutableLiveDataStatusResonse = totalBookingsRepository.getBookingStatusData(userId,bookingPositionId,bookingStatus);
        }
    }

    public LiveData<TotalBookingGamesResonse> getTotalGamesData() {
        return mutableLiveData;
    }

    public LiveData<BookingStatusResonse> getInvitationResponseData() {
        return mutableLiveDataStatusResonse;
    }
}
