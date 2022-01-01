package com.mrn.core.common

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


fun Disposable.disposeWith(compositeDisposable: CompositeDisposable) = compositeDisposable.add(this)