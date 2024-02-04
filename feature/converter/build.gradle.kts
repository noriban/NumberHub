/*
 * Unitto is a calculator for Android
 * Copyright (c) 2023-2024 Elshan Agaev
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

plugins {
    id("com.google.devtools.ksp")
    id("unitto.library")
    id("unitto.library.compose")
    id("unitto.library.feature")
    id("unitto.android.hilt")
    id("unitto.android.library.jacoco")
}

android.namespace = "com.sadellie.unitto.feature.converter"
android.testOptions.unitTests.isIncludeAndroidResources = true

dependencies {
    testImplementation(libs.org.robolectric.robolectric)
    testImplementation(libs.org.jetbrains.kotlinx.kotlinx.coroutines.test)
    testImplementation(libs.androidx.room.runtime)
    testImplementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    testImplementation(libs.androidx.datastore.datastore.preferences)

    implementation(project(":data:common"))
    implementation(project(":data:database"))
    implementation(project(":data:evaluatto"))
    implementation(project(":data:model"))
    implementation(project(":data:userprefs"))
    implementation(project(":data:converter"))
}
