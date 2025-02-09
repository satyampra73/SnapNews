package com.satyam.snapnews.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.satyam.snapnews.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert (article: Article)

    @Query("SELECT * FROM articles")
    fun getAllArticles(): Flow<List<Article>>
}