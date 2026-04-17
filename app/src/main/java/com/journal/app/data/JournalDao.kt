package com.journal.app.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface JournalDao {
    @Query("SELECT * FROM journal_entries ORDER BY date DESC")
    fun getAllEntries(): Flow<List<JournalEntry>>

    @Query("SELECT * FROM journal_entries WHERE date = :date")
    suspend fun getEntry(date: String): JournalEntry?

    @Query("SELECT date FROM journal_entries")
    fun getAllDates(): Flow<List<String>>

    @Query("SELECT * FROM journal_entries WHERE notes LIKE '%' || :query || '%' OR tags LIKE '%' || :query || '%'")
    fun searchEntries(query: String): Flow<List<JournalEntry>>

    @Upsert
    suspend fun upsert(entry: JournalEntry)

    @Delete
    suspend fun delete(entry: JournalEntry)

    @Query("SELECT COUNT(*) FROM journal_entries")
    suspend fun getTotalCount(): Int

    @Query("SELECT * FROM journal_entries ORDER BY date DESC")
    suspend fun getAllEntriesOnce(): List<JournalEntry>
}
