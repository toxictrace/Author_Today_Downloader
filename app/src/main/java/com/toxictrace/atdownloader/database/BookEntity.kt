data class BookEntity(
    val bookId: String,
    val title: String,
    val author: String,
    val description: String,
    val coverUrl: String,
    val status: String,
    val downloadPath: String,
    val lastUpdated: String = "2026-03-23 17:52:51",
    var isFavorite: Boolean = false
)