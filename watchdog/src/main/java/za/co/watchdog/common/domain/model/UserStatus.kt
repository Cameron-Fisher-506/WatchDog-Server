package za.co.watchdog.common.domain.model

enum class UserStatus {
    PENDING_VERIFICATION,
    ACTIVE,
    DEACTIVATED,
    LOCKED,
    BANNED
}