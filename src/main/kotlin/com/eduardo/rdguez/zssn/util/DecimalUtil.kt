package com.eduardo.rdguez.zssn.util

import com.eduardo.rdguez.zssn.constant.ApiConstants

class DecimalUtil {
  companion object {
    fun truncate(
      number: Double,
      format: String = ApiConstants.TWO_DECIMAL_FORMAT
    ): String {
      return String.format(format, number)
    }
  }
}