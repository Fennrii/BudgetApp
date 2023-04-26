package com.example.litebudgeting.ui.income;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class IncomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public IncomeViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }


}