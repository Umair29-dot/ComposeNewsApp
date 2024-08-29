package com.myapplication.util

fun extractDate(timeStamp: String): String {
	val sp = timeStamp.split("T")
	return sp[0]
}