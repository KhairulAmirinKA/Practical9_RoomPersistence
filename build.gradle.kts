// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.4" apply false
}

ext{
    set("roomVersion","2.4.3")
    set("lifecycleVersion", "2.3.1")
}

