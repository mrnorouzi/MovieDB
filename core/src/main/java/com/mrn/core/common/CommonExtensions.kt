package com.mrn.core.common

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


fun Disposable.disposeWith(compositeDisposable: CompositeDisposable) = compositeDisposable.add(this)

fun String.toAbsolutePath(size: ImageSize): String {
    val path: String = when (size) {
        ImageSize.SMALL -> Constants.SMALL_SIZE_IMAGE_PATH
        ImageSize.BIG -> Constants.BIG_SIZE_IMAGE_PATH
    }
    return "$path$this"
}

