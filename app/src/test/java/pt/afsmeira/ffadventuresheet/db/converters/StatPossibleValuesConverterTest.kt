package pt.afsmeira.ffadventuresheet.db.converters

import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import pt.afsmeira.ffadventuresheet.model.Stat

@RunWith(JUnit4::class)
class StatPossibleValuesConverterTest {

    @Test
    fun testConvertUndefinedPossibleValuesToString() {
        val json = StatPossibleValuesConverter().toString(Stat.PossibleValues.Undefined)

        assertThat(json, `is`("""{"type":"undefined"}"""))
    }

    @Test
    fun testConvertUndefinedPossibleValuesFromString() {
        val undefined =
            StatPossibleValuesConverter().fromString("""{"type":"undefined"}""")

        assertThat(undefined, instanceOf(Stat.PossibleValues.Undefined::class.java))
    }

    @Test
    fun testConvertDefinedPossibleValuesToString() {
        val possibleValues = Stat.PossibleValues.Defined(listOf("X", "Y", "Z"))
        val json = StatPossibleValuesConverter().toString(possibleValues)

        assertThat(json, `is`("""{"type":"defined","values":["X","Y","Z"]}"""))
    }

    @Test
    fun testConvertDefinedPossibleValuesFromString() {
        val defined =
            StatPossibleValuesConverter().fromString("""{"type":"defined","values":["X","Y","Z"]}""")

        assertThat(defined, instanceOf(Stat.PossibleValues.Defined::class.java))
        assertThat((defined as Stat.PossibleValues.Defined).values, `is`(listOf("X", "Y", "Z")))
    }
}
