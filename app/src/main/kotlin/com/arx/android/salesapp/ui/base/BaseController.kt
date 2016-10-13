package com.arx.android.salesapp.ui.base

import android.os.Bundle
import android.support.annotation.CheckResult
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arx.android.salesapp.utils.mvp.BetterMvpConductorDelegateCallback
import com.arx.android.salesapp.utils.mvp.BetterMvpConductorLifecyclerListener
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.rxlifecycle.ControllerEvent
import com.bluelinelabs.conductor.rxlifecycle.ControllerLifecycleProvider
import com.bluelinelabs.conductor.rxlifecycle.ControllerLifecycleSubjectHelper
import com.bluelinelabs.conductor.rxlifecycle.RxControllerLifecycle
import com.hannesdorfmann.mosby.mvp.MvpView
import com.trello.rxlifecycle.RxLifecycle
import rx.Observable
import rx.subjects.BehaviorSubject

/**
 * Created by esafirm on 6/22/16.
 */
abstract class BaseController<V : MvpView, P : BasePresenter<V>>(args: Bundle?) : Controller(args),
		ControllerLifecycleProvider, BetterMvpConductorDelegateCallback<V, P> {

	private var mPresenter: P? = null

	constructor() : this(null)

	/* --------------------------------------------------- */
	/* > lifeCycle */
	/* --------------------------------------------------- */

	private var lifecycleSubject: BehaviorSubject<ControllerEvent>

	init {
		lifecycleSubject = ControllerLifecycleSubjectHelper.create(this)
		addLifecycleListener(BetterMvpConductorLifecyclerListener<V, P>(this))
	}

	@CheckResult
	override fun lifecycle(): Observable<ControllerEvent> = lifecycleSubject.asObservable()

	@CheckResult
	override fun <T> bindUntilEvent(event: ControllerEvent): Observable.Transformer<T, T> {
		return RxLifecycle.bindUntilEvent<T, ControllerEvent>(lifecycleSubject, event)
	}

	@CheckResult
	override fun <T> bindToLifecycle(): Observable.Transformer<T, T> {
		return RxControllerLifecycle.bindController<T>(lifecycleSubject)
	}

	/* --------------------------------------------------- */
	/* > Event */
	/* --------------------------------------------------- */

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
		return inflater.inflate(getLayoutResId(), container, false);
	}

	abstract fun getLayoutResId(): Int

	override fun onInject() {
	}

	override fun onViewCreated(view: View) {
	}

	/* --------------------------------------------------- */
	/* > MVP */
	/* --------------------------------------------------- */

	abstract override fun getMvpView(): V
	abstract override fun createPresenter(): P

	override fun setPresenter(presenter: P) {
		mPresenter = presenter
	}

	override fun getPresenter(): P = mPresenter ?: createPresenter()

	/* --------------------------------------------------- */
	/* > Convenience Methods */
	/* --------------------------------------------------- */

	protected fun setSupportActionBar(toolbar: Toolbar) {
		(activity as AppCompatActivity).setSupportActionBar(toolbar)
	}
}