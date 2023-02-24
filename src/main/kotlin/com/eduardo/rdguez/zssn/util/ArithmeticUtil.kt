package com.eduardo.rdguez.zssn.util

import com.eduardo.rdguez.zssn.constant.ApiConstants

class ArithmeticUtil {
  companion object {
    fun percentage(
      part: Double,
      whole: Double
    ): Double {
      return (part / whole) * ApiConstants.ONE_HUNDRED
    }

    fun average(
      sum: Double,
      count: Double,
      format: String = ApiConstants.TWO_DECIMAL_FORMAT
    ): Double {
      return sum / count
    }
  }
}