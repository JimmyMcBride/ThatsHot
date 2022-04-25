package com.example.thatshot.extensions

import android.view.LayoutInflater
import android.view.ViewGroup

val ViewGroup.layoutInflater: LayoutInflater get() = LayoutInflater.from(context)