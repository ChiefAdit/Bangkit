package com.example.try1.Retrofit.Response

import android.provider.ContactsContract

class ArticleResponse (
    val code: Int,
    val `data`: List<ContactsContract.Contacts.Data>,
    val message: String,
    val success: Boolean

        )