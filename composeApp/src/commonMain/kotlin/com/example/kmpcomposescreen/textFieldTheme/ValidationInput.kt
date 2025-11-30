package com.example.kmpcomposescreen.textFieldTheme

interface ValidationInput  {

    val errorText: Lazy<String?>
        get() = lazy {
            getErrorText()
        }

    fun getErrorText(): String?

    fun isValid(): Boolean = errorText.value.isNullOrEmpty()

    val title: String
    val placeholder: String
    fun getValue(): String
}