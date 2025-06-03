package org.morecup.jimmerddd.kotlin.sample.domain.base

open class DomainException : RuntimeException{
    constructor(cause: Throwable?) : super(cause)

    constructor(message: String?, cause: Throwable?) : super(message, cause)

    constructor(message: String?) : super(message)
}