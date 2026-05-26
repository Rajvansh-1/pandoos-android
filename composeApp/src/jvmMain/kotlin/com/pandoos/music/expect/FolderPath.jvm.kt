package com.pandoos.music.expect

actual fun getDownloadFolderPath(): String = System.getProperty("user.home") + "/Downloads"