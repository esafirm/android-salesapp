package com.arx.android.salesapp.utils.mvp;

import android.support.annotation.Nullable;
import android.view.View;
import com.arx.android.salesapp.ui.base.BasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.hannesdorfmann.mosby.mvp.conductor.delegate.MvpConductorDelegateCallback;

/**
 * Created by esafirm on 6/29/16.
 */
public interface BetterMvpConductorDelegateCallback<V extends MvpView, P extends BasePresenter<V>>
    extends MvpConductorDelegateCallback<V, P> {
  void onInject();

  void onViewCreated(View view);

  @Nullable @Override P getPresenter();
}
