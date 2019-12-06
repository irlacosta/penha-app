package br.edu.ifrs.projetoexemplomd.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public void onCardOneClick() {

    }

    public void onCardTwoClick() {

    }

    public LiveData<String> getText() {
        return mText;
    }
}