/*
 * Sonar Groovy Plugin
 * Copyright (C) 2010-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package io.gitlab.arturbosch.detekt.sonar.surefire.data

import java.util.*

class UnitTestIndex {
    private val indexByClassname: MutableMap<String, UnitTestClassReport> = HashMap()

    fun index(classname: String): UnitTestClassReport {
        var classReport: UnitTestClassReport? = indexByClassname[classname]
        if (classReport == null) {
            classReport = UnitTestClassReport()
            indexByClassname.put(classname, classReport)
        }
        return classReport
    }

    operator fun get(classname: String): UnitTestClassReport? {
        return indexByClassname[classname]
    }

    val classnames: Set<String>
        get() = HashSet(indexByClassname.keys)

    fun getIndexByClassname(): Map<String, UnitTestClassReport> {
        return indexByClassname
    }

    fun size(): Int {
        return indexByClassname.size
    }

    fun merge(classname: String, intoClassname: String): UnitTestClassReport? {
        val from = indexByClassname[classname]
        if (from != null) {
            val to = index(intoClassname)
            to.add(from)
            indexByClassname.remove(classname)
            return to
        }
        return null
    }

    fun remove(classname: String) {
        indexByClassname.remove(classname)
    }

}
