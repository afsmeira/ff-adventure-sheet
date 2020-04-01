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

    private val converter = StatPossibleValuesConverter()

    @Test
    fun testConvertUndefinedPossibleValues() {
        val json = converter.toString(Stat.PossibleValues.Undefined)
        val possibleValues = converter.fromString(json)

        assertThat(json, `is`("""{"type":"undefined"}"""))
        assertThat(possibleValues, instanceOf(Stat.PossibleValues.Undefined::class.java))
    }

    @Test
    fun testConvertDefinedPossibleValues() {
        val definedValues = Stat.PossibleValues.Defined(listOf("X", "Y", "Z"))
        val json = converter.toString(definedValues)
        val possibleValues = converter.fromString(json)

        assertThat(json, `is`("""{"type":"defined","values":["X","Y","Z"]}"""))
        assertThat(possibleValues, instanceOf(Stat.PossibleValues.Defined::class.java))
        assertThat(
            (possibleValues as Stat.PossibleValues.Defined).values,
            `is`(listOf("X", "Y", "Z"))
        )
    }
}
