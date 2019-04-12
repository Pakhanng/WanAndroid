package me.pakhang.wanandroid.model

/*
* {
    "data": {
        "chapterTops": [],
        "collectIds": [
            8151
        ],
        "email": "",
        "icon": "",
        "id": 21486,
        "password": "",
        "token": "",
        "type": 0,
        "username": "pakhang"
    },
    "errorCode": 0,
    "errorMsg": ""
}
*/

/*
* {
    "data": null,
    "errorCode": -1,
    "errorMsg": "账号密码不匹配！"
}
*/
data class User(
    val id: Int,
    val username: String,
    val collectIds: List<Int>
)
