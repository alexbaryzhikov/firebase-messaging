/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.alexbaryzhikov.squawker.provider

import android.content.SharedPreferences
import net.simonvt.schematic.annotation.*

/**
 * Uses the Schematic (https://github.com/SimonVT/schematic) library to define the columns in a
 * content provider baked by a database
 */
object SquawkContract {
    @Suppress("unused")
    @DataType(DataType.Type.INTEGER)
    @PrimaryKey(onConflict = ConflictResolutionType.REPLACE)
    @AutoIncrement
    const val COLUMN_ID = "_id"

    @DataType(DataType.Type.TEXT)
    @NotNull
    const val COLUMN_AUTHOR = "author"

    @DataType(DataType.Type.TEXT)
    @NotNull
    const val COLUMN_AUTHOR_KEY = "authorKey"

    @DataType(DataType.Type.TEXT)
    @NotNull
    const val COLUMN_MESSAGE = "message"

    @DataType(DataType.Type.INTEGER)
    @NotNull
    const val COLUMN_DATE = "date"

    // Topic keys as matching what is found in the database
    const val ASSER_KEY = "key_asser"
    const val CEZANNE_KEY = "key_cezanne"
    const val JLIN_KEY = "key_jlin"
    const val LYLA_KEY = "key_lyla"
    const val NIKITA_KEY = "key_nikita"
    private const val TEST_ACCOUNT_KEY = "key_test"
    private val INSTRUCTOR_KEYS = arrayOf(ASSER_KEY, CEZANNE_KEY, JLIN_KEY, LYLA_KEY, NIKITA_KEY)

    /**
     * Creates a SQLite SELECTION parameter that filters just the rows for the authors you are
     * currently following.
     */
    fun createSelectionForCurrentFollowers(preferences: SharedPreferences) = StringBuilder()
        .append("$COLUMN_AUTHOR_KEY IN ('$TEST_ACCOUNT_KEY'")
        .append(INSTRUCTOR_KEYS.fold("") { acc, key ->
            if (preferences.getBoolean(key, false)) "$acc,'$key'" else acc
        })
        .append(")").toString()
}