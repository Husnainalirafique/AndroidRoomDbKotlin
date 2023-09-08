package com.example.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.room.entities.User

@Dao
interface UserDao {
    @Insert
    suspend fun userName(user:User)
    @Delete
    suspend fun delUserName(user: User)
}