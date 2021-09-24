package com.corbellini.presentation.models

import com.corbellini.domain.models.Owner
import org.junit.Test
import kotlin.test.assertEquals

class OwnerPresentationTest {


    @Test
    fun `OwnerPresentation should success mapper from Owner (Domain)`() {
        //arrange
        val entity = Owner(login = "1", avatarUrl = "http://")
        val (login, avatarUrl) = entity

        //act
        val result = entity.toPresentation()

        //assert
        assertEquals(login, result.login)
        assertEquals(avatarUrl, result.avatarUrl)
    }


}