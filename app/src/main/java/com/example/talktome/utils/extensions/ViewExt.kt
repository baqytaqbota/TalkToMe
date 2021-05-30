package com.example.talktome.utils.extensions

import android.text.TextWatcher
import android.widget.TextView
import androidx.core.widget.addTextChangedListener

/**
 * Add an action which will be invoked when the text is changing.
 *
 * @return the [TextWatcher] added to the TextView
 */
inline fun TextView.doOnTextChanged(
    crossinline action: (
        text: CharSequence?,
        start: Int,
        before: Int,
        count: Int
    ) -> Unit
) = addTextChangedListener(onTextChanged = action)