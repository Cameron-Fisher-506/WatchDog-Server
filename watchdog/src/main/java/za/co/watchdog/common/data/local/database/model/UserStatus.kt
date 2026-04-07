package za.co.watchdog.common.data.local.database.model

enum class UserStatus {
    PENDING_VERIFICATION,
    ACTIVE,
    DEACTIVATED,
    LOCKED,
    BANNED
}