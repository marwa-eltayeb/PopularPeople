package com.marwaeltayeb.popularpeople

import com.marwaeltayeb.popularpeople.utils.Const.Companion.IMAGE_LINK
import com.marwaeltayeb.popularpeople.utils.ImageUtils.createImageLink
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ImageLinkTest {

    @Test
    fun isImageLinkCreated() {
        assertEquals(createImageLink("/picture.jpg"), IMAGE_LINK + "/picture.jpg")
    }
}