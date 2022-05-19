package com.kotlinapi.exception

class RequiredObjectIsNullException : RuntimeException {

    constructor() : super("It is not allowed to persist a null object!")

    constructor(exception: String?) : super(exception)
}
