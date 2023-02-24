package com.eduardo.rdguez.zssn.util

import com.eduardo.rdguez.zssn.constant.ApiConstants

class ArithmeticUtil {
  companion object {
    fun percentage(
      part: Double,
      whole: Double,
      format: String = ApiConstants.TWO_DECIMAL_FORMAT
    ): String {
      val percentage = (part / whole) * ApiConstants.ONE_HUNDRED
      return String.format(format, percentage)
    }

    fun average(
      sum: Double,
      count: Double,
      format: String = ApiConstants.TWO_DECIMAL_FORMAT
    ): String {
      val average = sum / count
      return String.format(format, average)
    }
  }
}