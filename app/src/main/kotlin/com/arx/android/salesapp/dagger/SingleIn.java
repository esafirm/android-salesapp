package com.arx.android.salesapp.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

/**
 * Created by esa on 24/05/16, with awesomeness
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface SingleIn {
  Class<?> value();
}
