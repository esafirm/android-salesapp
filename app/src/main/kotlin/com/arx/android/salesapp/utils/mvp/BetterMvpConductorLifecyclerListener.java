package com.arx.android.salesapp.utils.mvp;

import android.support.annotation.NonNull;
import android.view.View;
import com.arx.android.salesapp.ui.base.BasePresenter;
import com.bluelinelabs.conductor.Controller;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.hannesdorfmann.mosby.mvp.conductor.delegate.MvpConductorDelegateCallback;
import com.hannesdorfmann.mosby.mvp.conductor.delegate.MvpConductorLifecycleListener;

/**
 * Created by esafirm on 6/29/16.
 */
public class BetterMvpConductorLifecyclerListener<V extends MvpView, P extends BasePresenter<V>>
    extends MvpConductorLifecycleListener<V, P> {
  /**
   * Instantiate a new Mosby MVP Listener
   *
   * @param callback {@link MvpConductorDelegateCallback} to set presenter. Typically the
   * controller
   * himself.
   */
  public BetterMvpConductorLifecyclerListener(BetterMvpConductorDelegateCallback<V, P> callback) {
    super(callback);
  }

  @Override public void postCreateView(@NonNull Controller controller, @NonNull View view) {
    ((BetterMvpConductorDelegateCallback) callback).onInject();
    super.postCreateView(controller, view);
    ((BetterMvpConductorDelegateCallback) callback).onViewCreated(view);
  }
}
