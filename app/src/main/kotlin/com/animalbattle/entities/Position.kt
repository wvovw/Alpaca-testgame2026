package com.animalbattle.entities

/**
 * 位置数据类 - Position data class
 */
data class Position(
    var x: Float,
    var y: Float
) {
    fun distanceTo(other: Position): Float {
        val dx = x - other.x
        val dy = y - other.y
        return kotlin.math.sqrt(dx * dx + dy * dy)
    }
}
