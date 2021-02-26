package com.alorma.puppies.data

import com.alorma.puppies.ui.model.UserId
import com.alorma.puppies.ui.model.UserModel

object UserProvider {

    val user: UserModel = UserModel(
        id = UserId("abcd123"),
        name = "Andrina Durand",
        avatar = "https://randomuser.me/api/portraits/women/65.jpg"
    )

}