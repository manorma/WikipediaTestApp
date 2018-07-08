package com.example.manorma.testapplication

import com.example.manorma.testapplication.model.Page

interface SearchScreenListener {
    fun navigateToDetaiScreen(page:Page?);
}