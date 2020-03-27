package pt.afsmeira.ffadventuresheet.model

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import pt.afsmeira.ffadventuresheet.model.Stat.Type


@RunWith(JUnit4::class)
class StatTest {

    @JvmField
    @Rule
    val expectedException: ExpectedException = ExpectedException.none()

    @Test
    fun valueOfTest() {
        assertThat(Type.valueOf(0), `is`(Type.INT))
        assertThat(Type.valueOf(1), `is`(Type.TEXT))
        assertThat(Type.valueOf(2), `is`(Type.SINGLE_OPT))
        assertThat(Type.valueOf(3), `is`(Type.MULTI_OPT))
        assertThat(Type.valueOf(4), `is`(Type.MULTI_OPT_REPEAT))

        expectedException.expect(IllegalArgumentException::class.java)
        Type.valueOf(5)
    }
}
