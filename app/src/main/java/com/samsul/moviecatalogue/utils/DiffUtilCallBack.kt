package com.samsul.moviecatalogue.utils

import androidx.recyclerview.widget.DiffUtil
import com.samsul.moviecatalogue.data.local.entity.DataLocalMovie

class DiffUtilCallBack(private val mOldNoteList: List<DataLocalMovie>, private val mNewNoteList: List<DataLocalMovie>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldNoteList.size
    }
    override fun getNewListSize(): Int {
        return mNewNoteList.size
    }
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldNoteList[oldItemPosition].movieId == mNewNoteList[newItemPosition].movieId
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldNoteList[oldItemPosition]
        val newEmployee = mNewNoteList[newItemPosition]
        return oldEmployee.titleMovie == newEmployee.titleMovie && oldEmployee.imagePoster == newEmployee.imagePoster
    }
}