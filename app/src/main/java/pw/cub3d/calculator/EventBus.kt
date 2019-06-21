package pw.cub3d.calculator

import org.greenrobot.eventbus.EventBus

fun Any.post() {
    EventBus.getDefault().post(this)
}

fun Any.subscribe() {
    EventBus.getDefault().register(this)
}

fun Any.unsubscribe() {
    EventBus.getDefault().unregister(this)
}