package no.nrk.nrk_kotlin.business.api

data class TitleDto(
        val type: String,
        val text: String
)

data class LayerItemDto(
        val id: String,
        val type: String,
        val title: TitleDto
)

data class NewscenterDto(
        var layerItems: List<LayerItemDto>
)