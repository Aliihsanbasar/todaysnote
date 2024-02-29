package com.jotquill.components

fun String.replaceEmptyTitle(): String {
    return this.ifEmpty {
        "A New Note"
    }
}