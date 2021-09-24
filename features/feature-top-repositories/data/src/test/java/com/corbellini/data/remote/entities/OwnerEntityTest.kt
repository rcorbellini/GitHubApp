package com.corbellini.data.remote.entities

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import org.junit.Test
import kotlin.test.assertEquals

class OwnerEntityTest {

    @Test
    fun `OwnerEntity should success mapper to Owner (Domain)`() {
        //arrange
        val entity = OwnerEntity(login = "1", avatarUrl = "http://")
        val (login, avatarUrl) = entity

        //act
        val result = entity.toModel()

        //assert
        assertEquals(login, result.login)
        assertEquals(avatarUrl, result.avatarUrl)
    }

    @Test
    fun `Json should success mapper to OwnerEntity`() {
        //arrange
        val entityJson = "{\n" +
                "      \"login\": \"Aaron-Ge\",\n" +
                "      \"id\": 22882546,\n" +
                "      \"node_id\": \"MDQ6VXNlcjIyODgyNTQ2\",\n" +
                "      \"avatar_url\": \"https://avatars.githubusercontent.com/u/22882546?v=4\",\n" +
                "      \"gravatar_id\": \"\",\n" +
                "      \"url\": \"https://api.github.com/users/Aaron-Ge\",\n" +
                "      \"html_url\": \"https://github.com/Aaron-Ge\",\n" +
                "      \"followers_url\": \"https://api.github.com/users/Aaron-Ge/followers\",\n" +
                "      \"following_url\": \"https://api.github.com/users/Aaron-Ge/following{/other_user}\",\n" +
                "      \"gists_url\": \"https://api.github.com/users/Aaron-Ge/gists{/gist_id}\",\n" +
                "      \"starred_url\": \"https://api.github.com/users/Aaron-Ge/starred{/owner}{/repo}\",\n" +
                "      \"subscriptions_url\": \"https://api.github.com/users/Aaron-Ge/subscriptions\",\n" +
                "      \"organizations_url\": \"https://api.github.com/users/Aaron-Ge/orgs\",\n" +
                "      \"repos_url\": \"https://api.github.com/users/Aaron-Ge/repos\",\n" +
                "      \"events_url\": \"https://api.github.com/users/Aaron-Ge/events{/privacy}\",\n" +
                "      \"received_events_url\": \"https://api.github.com/users/Aaron-Ge/received_events\",\n" +
                "      \"type\": \"User\",\n" +
                "      \"site_admin\": false\n" +
                "    }"
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .serializeNulls()
            .create()

        //act
        val result = gson.fromJson(entityJson, OwnerEntity::class.java)

        //assert
        assertEquals("Aaron-Ge", result.login)
        assertEquals("https://avatars.githubusercontent.com/u/22882546?v=4", result.avatarUrl)
    }
}