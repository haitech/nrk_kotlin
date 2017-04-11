package no.nrk.nrk_kotlin.business

import io.reactivex.Observable
import no.nrk.nrk_kotlin.business.api.NrkApi
import no.nrk.nrk_kotlin.business.api.LayerItemDto
import no.nrk.nrk_kotlin.business.api.NewscenterDto
import no.nrk.nrk_kotlin.business.api.TitleDto
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class DataSupplierTest {
    @Mock
    lateinit var nrkApiMock: NrkApi

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun supplier_should_return_view_models() {
        val testList: MutableList<LayerItemDto> = ArrayList()
        testList.add(LayerItemDto("testId1", "testType1", TitleDto("typeString1", "testString1")))
        testList.add(LayerItemDto("testId2", "testType2", TitleDto("typeString2", "testString2")))
        `when`(nrkApiMock.getNewsCenter()).thenReturn(Observable.just(NewscenterDto(testList)))

        val dataSupplier = DataSupplier(nrkApiMock)
        val testSub = dataSupplier.getNewsMessages().test()

        testSub.assertNoErrors()
        testSub.assertValueCount(1)
        assertEquals(testSub.values()[0][0].id, "testId1")
        assertEquals(testSub.values()[0][0].title, "testString1")
    }
}