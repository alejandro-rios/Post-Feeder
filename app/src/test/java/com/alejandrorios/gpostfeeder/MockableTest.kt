package com.alejandrorios.gpostfeeder

import io.mockk.MockKAnnotations
import org.junit.Before

/**
 * Created by Alejandro Rios on 7/25/20
 */
interface MockableTest {

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }
}
